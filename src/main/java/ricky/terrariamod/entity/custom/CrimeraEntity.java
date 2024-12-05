package ricky.terrariamod.entity.custom;

import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.EnumSet;

public class CrimeraEntity extends FlyingEntity implements Monster {
    public CrimeraEntity(EntityType<? extends FlyingEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new FlyMoveControl(this); // 飛行制御をカスタム
    }

    // 属性設定
    public static DefaultAttributeContainer.Builder createCrimeraAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 15)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2f)
                .add(EntityAttributes.GENERIC_ARMOR, 0.5f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 7);
    }

    @Override
    protected void initGoals() {
        // ランダム飛行ゴール
        this.goalSelector.add(5, new FlyRandomlyGoal(this));
        // プレイヤーを狙うゴール
        this.targetSelector.add(1, new TrackPlayerGoal(this));
    }

    /**
     * プレイヤーを追尾し攻撃するためのゴール
     */
    static class TrackPlayerGoal extends Goal {
        private final CrimeraEntity crimera;
        private PlayerEntity target;

        public TrackPlayerGoal(CrimeraEntity crimera) {
            this.crimera = crimera;
            this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
        }

        @Override
        public boolean canStart() {
            this.target = this.crimera.getWorld().getClosestPlayer(this.crimera, 15.0);
            // ターゲットがプレイヤーで、クリエイティブ/スペクテイターモードではない場合のみ敵対
            return this.target != null && !this.target.isCreative() && !this.target.isSpectator();
        }

        @Override
        public void start() {
            this.crimera.getMoveControl().moveTo(target.getX(), target.getY(), target.getZ(), 1.5);
        }

        @Override
        public void tick() {
            if (this.target != null) {
                this.crimera.lookAtEntity(target, 30.0F, 30.0F);
                double distance = this.crimera.squaredDistanceTo(target);
                if (distance < 2.0) {
                    this.crimera.tryAttack(target);
                } else {
                    this.crimera.getMoveControl().moveTo(target.getX(), target.getY(), target.getZ(), 1.5);
                }
            }
        }

        @Override
        public boolean shouldContinue() {
            return this.target != null && this.target.isAlive()
                    && !this.target.isCreative() && !this.target.isSpectator()
                    && this.crimera.squaredDistanceTo(target) < 225.0;
        }
    }


    /**
     * ランダムに飛行するゴール
     */
    private static class FlyRandomlyGoal extends Goal {
        private final CrimeraEntity crimera;

        public FlyRandomlyGoal(CrimeraEntity crimera) {
            this.crimera = crimera;
            this.setControls(EnumSet.of(Control.MOVE));
        }

        public boolean canStart() {
            return this.crimera.getTarget() == null && !this.crimera.getMoveControl().isMoving();
        }

        public void start() {
            Random random = this.crimera.getRandom();
            double currentY = this.crimera.getY();
            double x = this.crimera.getX() + (random.nextDouble() * 32 - 16);
            double y = currentY + (random.nextDouble() * 2 - 1) * 2; // 現在の高さ±2
            double z = this.crimera.getZ() + (random.nextDouble() * 32 - 16);

            // 上昇しすぎを防ぐ（例えば、地面から50ブロック以上離れない）
            y = MathHelper.clamp(y, this.crimera.getWorld().getBottomY() + 5, this.crimera.getWorld().getTopY() - 5);

            this.crimera.getMoveControl().moveTo(x, y, z, 1.0);
        }

    }

    /**
     * カスタムの飛行制御クラス
     */
    private static class FlyMoveControl extends MoveControl {
        private final CrimeraEntity crimera;

        public FlyMoveControl(CrimeraEntity crimera) {
            super(crimera);
            this.crimera = crimera;
        }

        @Override
        public void tick() {
            if (this.state == State.MOVE_TO) {
                Vec3d targetVec = new Vec3d(this.targetX - crimera.getX(), this.targetY - crimera.getY(), this.targetZ - crimera.getZ());
                double distance = targetVec.length();
                if (distance < crimera.getBoundingBox().getAverageSideLength()) {
                    this.state = State.WAIT;
                } else {
                    double speed = this.speed * crimera.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED);
                    crimera.setVelocity(targetVec.normalize().multiply(speed));
                    crimera.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET, new Vec3d(this.targetX, this.targetY, this.targetZ));
                }
            }
        }
    }
}
