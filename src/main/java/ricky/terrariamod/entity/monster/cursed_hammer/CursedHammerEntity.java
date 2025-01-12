package ricky.terrariamod.entity.monster.cursed_hammer;

import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.EnumSet;

public class CursedHammerEntity extends HostileEntity implements Monster {
    public CursedHammerEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new FlyMoveControl(this); // 飛行制御をカスタム
    }

    // 属性設定
    public static DefaultAttributeContainer.Builder createCursedHammerAttributes() {
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
    @Override
    public void tick() {
        super.tick();
        this.noClip=true; // 壁をすり抜け可能にする
    }
    @Override
    protected void pushOutOfBlocks(double x, double y, double z) {
        // 壁から押し出されないようにする（空実装）
    }
    @Override
    public boolean isPushable() {
        return false; // エンティティが押されないように設定
    }
    @Override
    public boolean hasNoGravity() {
        return true; // 重力の影響を無効化
    }
    private boolean rotating = false;

    public boolean isRotating() {
        return rotating;
    }

    public void setRotating(boolean rotating) {
        this.rotating = rotating;
    }
    private boolean thrusting = false;  // 突進中を判定する変数

    public boolean isThrusting() {
        return thrusting;
    }

    public void setThrusting(boolean thrusting) {
        this.thrusting = thrusting;
    }

    /**
     * プレイヤーを追尾し攻撃するためのゴール
     */
    static class TrackPlayerGoal extends Goal {
        private final CursedHammerEntity hammer;
        private PlayerEntity target;


        public TrackPlayerGoal(CursedHammerEntity hammer) {
            this.hammer = hammer;
            this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
        }

        @Override
        public boolean canStart() {
            this.target = this.hammer.getWorld().getClosestPlayer(this.hammer, 30.0);
            // ターゲットがプレイヤーで、クリエイティブ/スペクテイターモードではない場合のみ敵対
            return this.target != null && !this.target.isCreative() && !this.target.isSpectator();
        }

        @Override
        public void start() {
            this.hammer.getMoveControl().moveTo(target.getX(), target.getY(), target.getZ(), 1.5);
        }
        int tickNum = 0;

        @Override
        public void tick() {
            if (this.target != null) {
                double distance = this.hammer.squaredDistanceTo(target);
                if (distance < 4.0) {
                    this.hammer.tryAttack(target);
                }
                if (!this.hammer.isThrusting()){
                    if (distance < 100 && tickNum<36){
                        // 回転中はその場で停止
                        this.hammer.setVelocity(Vec3d.ZERO);  // 速度をゼロに設定
                        // 回転を適用（X軸周りの回転）
                        CursedHammerModel.addRotate();
                        this.hammer.setRotating(true);
                        tickNum++;
                    }else if (tickNum == 36) {
                        this.hammer.setRotating(false);
                        this.hammer.setThrusting(true);  // 突進開始
                        Vec3d direction = new Vec3d(target.getX() - hammer.getX(), target.getY() - hammer.getY(), target.getZ() - hammer.getZ()).normalize();
                        Vec3d thrustPosition = hammer.getPos().add(direction.multiply(20.0));
                        this.hammer.getMoveControl().moveTo(thrustPosition.x, thrustPosition.y, thrustPosition.z, 2.0);
                        tickNum = 0;
                    } else {
                        if (tickNum==0){
                            this.hammer.getMoveControl().moveTo(target.getX(), target.getY()+1, target.getZ(), 1.5);
                        }else{
                            // 回転中はその場で停止
                            this.hammer.setVelocity(Vec3d.ZERO);  // 速度をゼロに設定
                            // 回転を適用（X軸周りの回転）
                            CursedHammerModel.addRotate();
                            this.hammer.setRotating(true);
                            tickNum++;
                        }
                    }
                }

            }
        }

        @Override
        public boolean shouldContinue() {
            return this.target != null && this.target.isAlive()
                    && !this.target.isCreative() && !this.target.isSpectator()
                    && this.hammer.squaredDistanceTo(target) < 225.0;
        }
    }




    /**
     * ランダムに飛行するゴール
     */
    private static class FlyRandomlyGoal extends Goal {
        private final CursedHammerEntity hammer;

        public FlyRandomlyGoal(CursedHammerEntity hammer) {
            this.hammer = hammer;
            this.setControls(EnumSet.of(Control.MOVE));
        }

        public boolean canStart() {
            return this.hammer.getTarget() == null && !this.hammer.getMoveControl().isMoving();
        }

        public void start() {
            Random random = this.hammer.getRandom();
            double currentY = this.hammer.getY();
            double x = this.hammer.getX() + (random.nextDouble() * 32 - 16);
            double y = currentY + (random.nextDouble() * 2 - 1) * 2; // 現在の高さ±2
            double z = this.hammer.getZ() + (random.nextDouble() * 32 - 16);

            // 上昇しすぎを防ぐ（例えば、地面から50ブロック以上離れない）
            y = MathHelper.clamp(y, this.hammer.getWorld().getBottomY() + 5, this.hammer.getWorld().getTopY() - 5);

            this.hammer.getMoveControl().moveTo(x, y, z, 1.0);
        }

    }

    /**
     * カスタムの飛行制御クラス
     */
    private static class FlyMoveControl extends MoveControl {
        private final CursedHammerEntity hammer;

        public FlyMoveControl(CursedHammerEntity hammer) {
            super(hammer);
            this.hammer = hammer;
        }

        @Override
        public void tick() {
            if (this.state == State.MOVE_TO) {
                if (hammer.isRotating()) {  // 回転中かどうかを判定するフラグメソッド
                    hammer.setVelocity(Vec3d.ZERO);  // 回転中はその場で停止
                } else {
                    Vec3d targetVec = new Vec3d(this.targetX - hammer.getX(), this.targetY - hammer.getY(), this.targetZ - hammer.getZ());
                    double distance = targetVec.length();
                    if (distance < hammer.getBoundingBox().getAverageSideLength()) {
                        this.state = State.WAIT;
                        hammer.setThrusting(false);  // 突進完了
                    } else {
                        double speed = this.speed * hammer.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED);
                        hammer.setVelocity(targetVec.normalize().multiply(speed));
                        hammer.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET, new Vec3d(this.targetX, this.targetY, this.targetZ));
                    }
                }
            }
        }

    }
}
