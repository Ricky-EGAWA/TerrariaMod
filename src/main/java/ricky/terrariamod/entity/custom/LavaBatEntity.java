package ricky.terrariamod.entity.custom;

import net.minecraft.entity.Entity;
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

public class LavaBatEntity extends BatEntity {

    public LavaBatEntity(EntityType<? extends BatEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createLavaBatAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 35.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2);
    }

    @Override
    protected void initGoals() {
        // 自作の攻撃ゴールを追加
        this.goalSelector.add(2, new LavaBatAttackGoal(this, 0.6)); // 速度を適宜調整
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    // 攻撃が成功した場合にターゲットを燃やす
    @Override
    public boolean tryAttack(Entity target) {
        boolean success = super.tryAttack(target);
        if (success && target instanceof LivingEntity) {
            // 攻撃が成功した場合、ターゲットを燃やす
            target.setOnFireFor(5); // 5秒間炎上させる
        }
        return success;
    }

    // 攻撃用のゴールクラス
    class LavaBatAttackGoal extends Goal {
        private final LavaBatEntity lavaBat;
        private LivingEntity target;
        private final double speed;
        private final double attackRange = 1.0D;

        public LavaBatAttackGoal(LavaBatEntity lavaBat, double speed) {
            this.lavaBat = lavaBat;
            this.speed = speed;
        }

        @Override
        public boolean canStart() {
            // ターゲットを取得し、生存確認
            this.target = this.lavaBat.getTarget();
            return this.target != null && this.target.isAlive();
        }

        @Override
        public void stop() {
            this.target = null;
            this.lavaBat.setVelocity(Vec3d.ZERO);
        }

        @Override
        public void tick() {
            if (this.target != null) {
                // ターゲット方向に向けて速度をセット
                Vec3d direction = target.getPos().subtract(lavaBat.getPos()).normalize().multiply(speed);
                lavaBat.setVelocity(direction);

                // ターゲットに向かって顔を向ける
                lavaBat.getLookControl().lookAt(target, 30.0F, 30.0F);

                // 攻撃範囲に入ったら攻撃
                if (lavaBat.squaredDistanceTo(target) < attackRange * attackRange) {
                    lavaBat.tryAttack(target);
                }
            }
        }
    }
}
