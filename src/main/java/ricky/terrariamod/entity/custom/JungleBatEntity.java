package ricky.terrariamod.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class JungleBatEntity extends BatEntity {

    public JungleBatEntity(EntityType<? extends BatEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createJungleBatAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 7)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 35.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3);
    }

    @Override
    protected void initGoals() {
        // 自作の攻撃ゴールを追加
        this.goalSelector.add(2, new JungleBatAttackGoal(this, 0.4)); // 速度を適宜調整
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    // 攻撃用のゴールクラス
    class JungleBatAttackGoal extends Goal {
        private final JungleBatEntity jungleBat;
        private LivingEntity target;
        private final double speed;
        private final double attackRange = 1.8D;
        private int attackCooldown = 0;

        public JungleBatAttackGoal(JungleBatEntity jungleBat, double speed) {
            this.jungleBat = jungleBat;
            this.speed = speed;
        }

        @Override
        public boolean canStart() {
            // ターゲットを取得し、生存確認
            this.target = this.jungleBat.getTarget();
            return this.target != null && this.target.isAlive();
        }

        @Override
        public void stop() {
            this.target = null;
            this.jungleBat.setVelocity(Vec3d.ZERO);
        }

        @Override
        public void tick() {
            if (this.target != null) {
                // ターゲットの顔（目）の位置を取得
                Vec3d targetEyePos = new Vec3d(target.getX(), target.getEyeY(), target.getZ());

                // 現在位置からターゲットの顔の位置への方向ベクトルを計算
                Vec3d direction = targetEyePos.subtract(jungleBat.getPos()).normalize().multiply(speed);

                // 計算した方向に向かって移動
                jungleBat.setVelocity(direction);

                // ターゲットに向かって顔を向ける
                jungleBat.getLookControl().lookAt(target, 30.0F, 30.0F);

                // クールダウン中でない場合にのみ攻撃する
                if (attackCooldown <= 0 && jungleBat.squaredDistanceTo(target) < attackRange * attackRange) {
                    jungleBat.tryAttack(target);
                    attackCooldown = 10; // 10ティック（0.5秒）クールダウンを設定
                }

                // クールダウンが設定されている場合は減らす
                if (attackCooldown > 0) {
                    attackCooldown--;
                }
            }
        }
    }
}
