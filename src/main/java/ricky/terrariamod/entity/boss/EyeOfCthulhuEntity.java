package ricky.terrariamod.entity.boss;

import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import ricky.terrariamod.entity.ModEntities;

import java.util.EnumSet;

public class EyeOfCthulhuEntity extends FlyingEntity implements Monster {
    private int phase = 1;
    private final ServerBossBar bossBar = new ServerBossBar(
            Text.literal("Eye Of Cthulhu"),
            BossBar.Color.RED,
            BossBar.Style.PROGRESS
    );

    public EyeOfCthulhuEntity(EntityType<? extends FlyingEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new FlyMoveControl(this);
    }

    public static DefaultAttributeContainer.Builder createEyeCthulhuAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 300)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2f)
                .add(EntityAttributes.GENERIC_ARMOR, 12f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 7);
    }
    public int getCurrentPhase() {
        return phase;
    }
    // フェーズ切り替え処理
    private void switchToPhaseTwo() {
        if (phase == 2) return; // 二重切り替え防止
        phase = 2;

        // 属性の変更
        this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(0.4f);
        this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(15);

        // ボスバーの色変更
        this.bossBar.setColor(BossBar.Color.YELLOW);

        // パーティクルやエフェクトの発生
        this.getWorld().sendEntityStatus(this, (byte) 63);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(5, new FlyRandomlyGoal(this));
        this.targetSelector.add(1, new TrackPlayerGoal(this));
    }

    @Override
    public void tick() {
        super.tick();

        // Update boss bar progress based on health
        float healthProgress = this.getHealth() / this.getMaxHealth();
        bossBar.setPercent(healthProgress);
        if (this.getHealth() <= this.getMaxHealth() / 2 && phase == 1) {
            switchToPhaseTwo();
        }
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        boolean damaged = super.damage(source, amount);
        if (damaged) {
            float healthProgress = this.getHealth() / this.getMaxHealth();
            bossBar.setPercent(healthProgress);
        }
        return damaged;
    }

    @Override
    public void onDeath(DamageSource source) {
        super.onDeath(source);
        bossBar.clearPlayers();
    }

    @Override
    public void onStartedTrackingBy(ServerPlayerEntity player) {
        super.onStartedTrackingBy(player);
        bossBar.addPlayer(player);
    }

    @Override
    public void onStoppedTrackingBy(ServerPlayerEntity player) {
        super.onStoppedTrackingBy(player);
        bossBar.removePlayer(player);
    }

    public static void summonBoss(ServerWorld world, BlockPos pos) {
        EyeOfCthulhuEntity boss = ModEntities.EYE_OF_CTHULHU.create(world);
        if (boss != null) {
            boss.refreshPositionAndAngles(pos, 0.0F, 0.0F);
            world.spawnEntity(boss);
        }
    }

    static class TrackPlayerGoal extends Goal {
        private final EyeOfCthulhuEntity eye;
        private PlayerEntity target;

        public TrackPlayerGoal(EyeOfCthulhuEntity eye) {
            this.eye = eye;
            this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
        }

        @Override
        public boolean canStart() {
            this.target = this.eye.getWorld().getClosestPlayer(this.eye, 15.0);
            return this.target != null && !this.target.isCreative() && !this.target.isSpectator();
        }

        @Override
        public void start() {
            this.eye.getMoveControl().moveTo(target.getX(), target.getY(), target.getZ(), 1.5);
        }

        @Override
        public void tick() {
            if (this.target != null) {
                this.eye.lookAtEntity(target, 30.0F, 30.0F);
                double distance = this.eye.squaredDistanceTo(target);
                if (distance < 3.0) {
                    this.eye.tryAttack(target);
                } else {
                    this.eye.getMoveControl().moveTo(target.getX(), target.getY(), target.getZ(), 1.5);
                }
            }
        }

        @Override
        public boolean shouldContinue() {
            return this.target != null && this.target.isAlive()
                    && !this.target.isCreative() && !this.target.isSpectator()
                    && this.eye.squaredDistanceTo(target) < 225.0;
        }
    }

    private static class FlyRandomlyGoal extends Goal {
        private final EyeOfCthulhuEntity eye;

        public FlyRandomlyGoal(EyeOfCthulhuEntity eye) {
            this.eye = eye;
            this.setControls(EnumSet.of(Control.MOVE));
        }

        public boolean canStart() {
            return this.eye.getTarget() == null && !this.eye.getMoveControl().isMoving();
        }

        public void start() {
            Random random = this.eye.getRandom();
            double currentY = this.eye.getY();
            double x = this.eye.getX() + (random.nextDouble() * 32 - 16);
            double y = currentY + (random.nextDouble() * 2 - 1) * 2;
            double z = this.eye.getZ() + (random.nextDouble() * 32 - 16);
            y = MathHelper.clamp(y, this.eye.getWorld().getBottomY() + 5, this.eye.getWorld().getTopY() - 5);
            this.eye.getMoveControl().moveTo(x, y, z, 1.0);
        }
    }

    private static class FlyMoveControl extends MoveControl {
        private final EyeOfCthulhuEntity eye;

        public FlyMoveControl(EyeOfCthulhuEntity eye) {
            super(eye);
            this.eye = eye;
        }

        @Override
        public void tick() {
            if (this.state == State.MOVE_TO) {
                Vec3d targetVec = new Vec3d(this.targetX - eye.getX(), this.targetY - eye.getY(), this.targetZ - eye.getZ());
                double distance = targetVec.length();
                if (distance < eye.getBoundingBox().getAverageSideLength()) {
                    this.state = State.WAIT;
                } else {
                    double speed = this.speed * eye.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED);
                    eye.setVelocity(targetVec.normalize().multiply(speed));
                    eye.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET, new Vec3d(this.targetX, this.targetY, this.targetZ));
                }
            }
        }
    }
}
