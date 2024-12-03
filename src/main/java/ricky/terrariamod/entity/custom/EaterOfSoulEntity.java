package ricky.terrariamod.entity.custom;

import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.control.MoveControl;
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
import net.minecraft.entity.ai.goal.*;

import java.util.EnumSet;

public class EaterOfSoulEntity extends FlyingEntity implements Monster {
    public EaterOfSoulEntity(EntityType<? extends FlyingEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new FlyMoveControl(this); // 飛行制御をカスタム
    }

    // 属性設定
    public static DefaultAttributeContainer.Builder createEaterOfSoulAttributes() {
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
        private final EaterOfSoulEntity eater;
        private PlayerEntity target;

        public TrackPlayerGoal(EaterOfSoulEntity eater) {
            this.eater = eater;
            this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
        }

        @Override
        public boolean canStart() {
            this.target = this.eater.getWorld().getClosestPlayer(this.eater, 15.0);
            // ターゲットがプレイヤーで、クリエイティブ/スペクテイターモードではない場合のみ敵対
            return this.target != null && !this.target.isCreative() && !this.target.isSpectator();
        }

        @Override
        public void start() {
            this.eater.getMoveControl().moveTo(target.getX(), target.getY(), target.getZ(), 1.5);
        }

        @Override
        public void tick() {
            if (this.target != null) {
                this.eater.lookAtEntity(target, 30.0F, 30.0F);
                double distance = this.eater.squaredDistanceTo(target);
                if (distance < 2.0) {
                    this.eater.tryAttack(target);
                } else {
                    this.eater.getMoveControl().moveTo(target.getX(), target.getY(), target.getZ(), 1.5);
                }
            }
        }

        @Override
        public boolean shouldContinue() {
            return this.target != null && this.target.isAlive()
                    && !this.target.isCreative() && !this.target.isSpectator()
                    && this.eater.squaredDistanceTo(target) < 225.0;
        }
    }


    /**
     * ランダムに飛行するゴール
     */
    private static class FlyRandomlyGoal extends Goal {
        private final EaterOfSoulEntity eater;

        public FlyRandomlyGoal(EaterOfSoulEntity eater) {
            this.eater = eater;
            this.setControls(EnumSet.of(Control.MOVE));
        }

        public boolean canStart() {
            return this.eater.getTarget() == null && !this.eater.getMoveControl().isMoving();
        }

        public void start() {
            Random random = this.eater.getRandom();
            double currentY = this.eater.getY();
            double x = this.eater.getX() + (random.nextDouble() * 32 - 16);
            double y = currentY + (random.nextDouble() * 2 - 1) * 2; // 現在の高さ±2
            double z = this.eater.getZ() + (random.nextDouble() * 32 - 16);

            // 上昇しすぎを防ぐ（例えば、地面から50ブロック以上離れない）
            y = MathHelper.clamp(y, this.eater.getWorld().getBottomY() + 5, this.eater.getWorld().getTopY() - 5);

            this.eater.getMoveControl().moveTo(x, y, z, 1.0);
        }

    }

    /**
     * カスタムの飛行制御クラス
     */
    private static class FlyMoveControl extends MoveControl {
        private final EaterOfSoulEntity eater;

        public FlyMoveControl(EaterOfSoulEntity eater) {
            super(eater);
            this.eater = eater;
        }

        @Override
        public void tick() {
            if (this.state == State.MOVE_TO) {
                Vec3d targetVec = new Vec3d(this.targetX - eater.getX(), this.targetY - eater.getY(), this.targetZ - eater.getZ());
                double distance = targetVec.length();
                if (distance < eater.getBoundingBox().getAverageSideLength()) {
                    this.state = State.WAIT;
                } else {
                    double speed = this.speed * eater.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED);
                    eater.setVelocity(targetVec.normalize().multiply(speed));
                    eater.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET, new Vec3d(this.targetX, this.targetY, this.targetZ));
                }
            }
        }
    }
}
