package ricky.terrariamod.entity.worm.devourer;

import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import ricky.terrariamod.entity.custom.DemonEyeEntity;

import java.util.EnumSet;

public class DevourerEntity extends FlyingEntity implements Monster {
    private final DevourerPartEntity head;
    private final DevourerPartEntity[] bodyParts;

    public DevourerEntity(EntityType<? extends FlyingEntity> type, World world) {
        super(type, world);
        this.moveControl = new DevourerEntity.FlyMoveControl(this); // 飛行制御をカスタム
        this.head = new DevourerPartEntity(this, "head", 1.0F, 1.0F);
        this.bodyParts = new DevourerPartEntity[20]; // 胴体パーツを用意
        for (int i = 0; i < bodyParts.length; i++) {
            bodyParts[i] = new DevourerPartEntity(this, "body" + i, 3.0F, 10.0F);
        }
    }

    public static DefaultAttributeContainer.Builder createDevourerAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 40)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2f)
                .add(EntityAttributes.GENERIC_ARMOR, 0f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4);
    }

    @Override
    protected void initGoals() {
        // ランダム飛行ゴール
        this.goalSelector.add(5, new DevourerEntity.FlyRandomlyGoal(this));
        // プレイヤーを狙うゴール
        this.targetSelector.add(1, new DevourerEntity.TrackPlayerGoal(this));
    }

    @Override
    public void tick() {
        super.tick();
        updatePartPositions();
    }

    private void updatePartPositions() {
        Vec3d previousPosition = this.getPos(); // 親エンティティ（頭）の位置を基準にする
        double previousYaw = this.getYaw(); // 現在の頭のYaw角度
        float segmentDistance = 0.375F; // 各セグメント間の距離

        // 頭パーツを更新
        this.head.updatePosition(previousPosition.x, previousPosition.y, previousPosition.z);

        // 各腹部パーツを更新
        for (DevourerPartEntity part : bodyParts) {
            Vec3d partPos = part.getPos();
            // パーツの位置計算
            double offsetX = previousPosition.x;
            double offsetZ = previousPosition.z;
            double offsetY = previousPosition.y; // 高さは一定
//            System.out.println("pos: "+offsetX +" "+offsetY+" "+offsetZ);
//            System.out.println(part);
            part.updatePosition(offsetX, offsetY, offsetZ);
            previousPosition = partPos;
        }
    }



    public DevourerPartEntity getHead() {
        return this.head;
    }

    public DevourerPartEntity[] getBodyParts() {
        DevourerPartEntity[] parts = new DevourerPartEntity[bodyParts.length + 1];
        parts[0] = head;  // 頭部パーツ
        System.arraycopy(bodyParts, 0, parts, 1, bodyParts.length);  // 胴体パーツをコピー
        return parts;
    }
    @Override
    public boolean isCollidable() {
        return true;
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        return super.damage(source, amount);
    }

    /**
     * ランダムに飛行するゴール
     */
    private static class FlyRandomlyGoal extends Goal {
        private final DevourerEntity demon;

        public FlyRandomlyGoal(DevourerEntity demon) {
            this.demon = demon;
            this.setControls(EnumSet.of(Control.MOVE));
        }

        public boolean canStart() {
            return this.demon.getTarget() == null && !this.demon.getMoveControl().isMoving();
        }

        public void start() {
            Random random = this.demon.getRandom();
            double currentY = this.demon.getY();
            double x = this.demon.getX() + (random.nextDouble() * 32 - 16);
            double y = currentY + (random.nextDouble() * 2 - 1) * 2; // 現在の高さ±2
            double z = this.demon.getZ() + (random.nextDouble() * 32 - 16);

            // 上昇しすぎを防ぐ（例えば、地面から50ブロック以上離れない）
            y = MathHelper.clamp(y, this.demon.getWorld().getBottomY() + 5, this.demon.getWorld().getTopY() - 5);

            this.demon.getMoveControl().moveTo(x, y, z, 1.0);
        }

    }

    /**
     * カスタムの飛行制御クラス
     */
    private static class FlyMoveControl extends MoveControl {
        private final DevourerEntity demon;

        public FlyMoveControl(DevourerEntity demon) {
            super(demon);
            this.demon = demon;
        }

        @Override
        public void tick() {
            if (this.state == State.MOVE_TO) {
                Vec3d targetVec = new Vec3d(this.targetX - demon.getX(), this.targetY - demon.getY(), this.targetZ - demon.getZ());
                double distance = targetVec.length();
                if (distance < demon.getBoundingBox().getAverageSideLength()) {
                    this.state = State.WAIT;
                } else {
                    double speed = this.speed * demon.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED);
                    demon.setVelocity(targetVec.normalize().multiply(speed));
                    demon.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET, new Vec3d(this.targetX, this.targetY, this.targetZ));
                }
            }
        }
    }
    /**
     * プレイヤーを追尾し攻撃するためのゴール
     */
    static class TrackPlayerGoal extends Goal {
        private final DevourerEntity demon;
        private PlayerEntity target;

        public TrackPlayerGoal(DevourerEntity demon) {
            this.demon = demon;
            this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
        }

        @Override
        public boolean canStart() {
            this.target = this.demon.getWorld().getClosestPlayer(this.demon, 15.0);
            // ターゲットがプレイヤーで、クリエイティブ/スペクテイターモードではない場合のみ敵対
            return this.target != null && !this.target.isCreative() && !this.target.isSpectator();
        }

        @Override
        public void start() {
            this.demon.getMoveControl().moveTo(target.getX(), target.getY(), target.getZ(), 1.5);
        }

        @Override
        public void tick() {
            if (this.target != null) {
                this.demon.lookAtEntity(target, 30.0F, 30.0F);
                double distance = this.demon.squaredDistanceTo(target);
                if (distance < 2.0) {
                    this.demon.tryAttack(target);
                } else {
                    this.demon.getMoveControl().moveTo(target.getX(), target.getY(), target.getZ(), 1.5);
                }
            }
        }

        @Override
        public boolean shouldContinue() {
            return this.target != null && this.target.isAlive()
                    && !this.target.isCreative() && !this.target.isSpectator()
                    && this.demon.squaredDistanceTo(target) < 225.0;
        }
    }

}
