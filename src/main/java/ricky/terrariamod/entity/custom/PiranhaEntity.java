/*
 * Decompiled with CFR 0.2.2 (FabricMC 7c48b8c4).
 */
package ricky.terrariamod.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.CodEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class PiranhaEntity extends CodEntity {

    public PiranhaEntity(EntityType<? extends CodEntity> entityType, World world) {
        super(entityType, world);
    }
    public static DefaultAttributeContainer.Builder createPiranhaAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 35.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2);
    }

    @Override
    protected void initGoals() {
        // 自作の攻撃ゴールを追加
        this.goalSelector.add(2, new PiranhaEntity.PiranhaAttackGoal(this, 0.6)); // 速度を適宜調整
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.goalSelector.add(4, new SwimToRandomPlaceGoal(this));
    }

    static class SwimToRandomPlaceGoal
            extends SwimAroundGoal {
        private final PiranhaEntity piranha;

        public SwimToRandomPlaceGoal(PiranhaEntity piranha) {
            super(piranha, 1.0, 40);
            this.piranha = piranha;
        }

        @Override
        public boolean canStart() {
            return this.piranha.hasSelfControl() && super.canStart();
        }
    }

    // 攻撃用のゴールクラス
    class PiranhaAttackGoal extends Goal {
        private final PiranhaEntity piranha;
        private LivingEntity target;
        private final double speed;
        private final double attackRange = 1.0D;

        public PiranhaAttackGoal(PiranhaEntity piranha, double speed) {
            this.piranha = piranha;
            this.speed = speed;
        }

        @Override
        public boolean canStart() {
            // ターゲットを取得し、生存確認
            this.target = this.piranha.getTarget();
            return this.target != null && this.target.isAlive();
        }

        @Override
        public void stop() {
            this.target = null;
            this.piranha.setVelocity(Vec3d.ZERO);
        }

        @Override
        public void tick() {
            if (this.target != null && this.target.isTouchingWater()) {
                // ターゲット方向に向けて速度をセット
                Vec3d direction = target.getPos().subtract(piranha.getPos()).normalize().multiply(speed);
                piranha.setVelocity(direction);

                // ターゲットに向かって顔を向ける
                piranha.getLookControl().lookAt(target, 30.0F, 30.0F);

                // 攻撃範囲に入ったら攻撃
                if (piranha.squaredDistanceTo(target) < attackRange * attackRange) {
                    piranha.tryAttack(target);
                }
            }
        }
    }
}

