package ricky.terrariamod.entity.magic;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Direction.Axis;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.event.GameEvent.Emitter;
import org.jetbrains.annotations.Nullable;
import ricky.terrariamod.entity.ModEntities;
import ricky.terrariamod.item.ModItems;

public class MagicMissileEntity extends ProjectileEntity {
    @Nullable
    private Entity target;
    @Nullable
    private Direction direction;
    private int stepCount;
    private double targetX;
    private double targetY;
    private double targetZ;
    @Nullable
    private UUID targetUuid;

    public MagicMissileEntity(EntityType<? extends MagicMissileEntity> entityType, World world) {
        super(entityType, world);
        this.noClip = true;
    }

    public MagicMissileEntity(World world, LivingEntity owner, LivingEntity target, Direction.Axis axis) {
        this(ModEntities.MAGIC_MISSILE, world);
        this.setOwner(owner);
        BlockPos blockPos = owner.getBlockPos();
        double d = (double)blockPos.getX() + 0.5;
        double e = (double)blockPos.getY() + 0.5;
        double f = (double)blockPos.getZ() + 0.5;
        this.refreshPositionAndAngles(d, e, f, this.getYaw(), this.getPitch());
        this.target = target;
        this.direction = Direction.UP;
        this.changeTargetDirection(axis);
    }

    public SoundCategory getSoundCategory() {
        return SoundCategory.HOSTILE;
    }

    protected void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        if (this.target != null) {
            nbt.putUuid("Target", this.target.getUuid());
        }

        if (this.direction != null) {
            nbt.putInt("Dir", this.direction.getId());
        }

        nbt.putInt("Steps", this.stepCount);
        nbt.putDouble("TXD", this.targetX);
        nbt.putDouble("TYD", this.targetY);
        nbt.putDouble("TZD", this.targetZ);
    }

    protected void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.stepCount = nbt.getInt("Steps");
        this.targetX = nbt.getDouble("TXD");
        this.targetY = nbt.getDouble("TYD");
        this.targetZ = nbt.getDouble("TZD");
        if (nbt.contains("Dir", 99)) {
            this.direction = Direction.byId(nbt.getInt("Dir"));
        }

        if (nbt.containsUuid("Target")) {
            this.targetUuid = nbt.getUuid("Target");
        }

    }

    protected void initDataTracker() {
    }

    @Nullable
    private Direction getDirection() {
        return this.direction;
    }

    private void setDirection(@Nullable Direction direction) {
        this.direction = direction;
    }

    private void changeTargetDirection(@Nullable Direction.Axis axis) {
        double d = 0.5;
        BlockPos blockPos;
        if (this.target == null) {
            blockPos = this.getBlockPos().down();
        } else {
            d = (double) this.target.getHeight() * 0.5;
            blockPos = BlockPos.ofFloored(this.target.getX(), this.target.getY() + d, this.target.getZ());
        }

        double e = (double) blockPos.getX() + 0.5;
        double f = (double) blockPos.getY() + d;
        double g = (double) blockPos.getZ() + 0.5;

        // ランダム方向を削除し、ターゲットへの方向のみを使用
        BlockPos blockPos2 = this.getBlockPos();

        if (!blockPos.isWithinDistance(this.getPos(), 2.0)) {
            if (axis != Axis.X) {
                if (blockPos2.getX() < blockPos.getX() && this.getWorld().isAir(blockPos2.east())) {
                    e = this.getX() + 1;
                } else if (blockPos2.getX() > blockPos.getX() && this.getWorld().isAir(blockPos2.west())) {
                    e = this.getX() - 1;
                }
            }

            if (axis != Axis.Y) {
                if (blockPos2.getY() < blockPos.getY() && this.getWorld().isAir(blockPos2.up())) {
                    f = this.getY() + 1;
                } else if (blockPos2.getY() > blockPos.getY() && this.getWorld().isAir(blockPos2.down())) {
                    f = this.getY() - 1;
                }
            }

            if (axis != Axis.Z) {
                if (blockPos2.getZ() < blockPos.getZ() && this.getWorld().isAir(blockPos2.south())) {
                    g = this.getZ() + 1;
                } else if (blockPos2.getZ() > blockPos.getZ() && this.getWorld().isAir(blockPos2.north())) {
                    g = this.getZ() - 1;
                }
            }
        }

        double h = e - this.getX();
        double j = f - this.getY();
        double k = g - this.getZ();
        double l = Math.sqrt(h * h + j * j + k * k);

        if (l == 0.0) {
            this.targetX = 0.0;
            this.targetY = 0.0;
            this.targetZ = 0.0;
        } else {
            this.targetX = h / l * 0.15;
            this.targetY = j / l * 0.15;
            this.targetZ = k / l * 0.15;
        }

        this.velocityDirty = true;
        this.stepCount = 10 + this.random.nextInt(5) * 10;
    }

    private void spawnCircleParticles() {
        World world = this.getWorld();
        if (world.isClient) { // クライアントサイドでのみ実行
            Vec3d center = this.getPos();

            // 半径 0.2 ブロックの範囲でパーティクルを生成
            double radius = 0.2;

            for (int i = 0; i < 10; i++) { // パーティクル数を増やす
                double angle = this.random.nextDouble() * 2 * Math.PI; // ランダムな角度
                double offsetX = Math.cos(angle) * radius;
                double offsetZ = Math.sin(angle) * radius;

                // 高さを少しランダム化
                double offsetY = this.random.nextGaussian() * 0.02;

                // パーティクルを生成
                world.addParticle(
                        new net.minecraft.particle.DustParticleEffect(
                                new org.joml.Vector3f(0.5f, 0.9f, 0.9f), // カラー
                                1.0F // スケール
                        ),
                        center.x + offsetX,
                        center.y + offsetY,
                        center.z + offsetZ,
                        0, 0, 0 // パーティクルの移動速度は 0
                );
            }
        }
    }
    private void spawnTailParticles() {
        World world = this.getWorld();
        if (world.isClient) { // クライアントサイドでのみ実行
            Vec3d pos = this.getPos();
            double x = pos.x;
            double y = pos.y;
            double z = pos.z;

            // 複数の色のパーティクルを生成
            for (int i = 0; i < 5; i++) {
                double offsetX = this.random.nextGaussian() * 0.05;
                double offsetY = this.random.nextGaussian() * 0.05;
                double offsetZ = this.random.nextGaussian() * 0.05;

                // DustParticleEffectを生成（Vector3fで色を指定）
                world.addParticle(
                        new net.minecraft.particle.DustParticleEffect(
                                new org.joml.Vector3f(0.5f, 0.9f, 0.9f), // カラー
                                1.0F // スケール
                        ),
                        x, y, z,
                        offsetX, offsetY, offsetZ
                );
            }
        }
    }


    public void checkDespawn() {
    }

    public void tick() {
        super.tick();
        Vec3d vec3d;
        if (!this.getWorld().isClient) {
            // 周囲5ブロック以内のモンスターを探索
            List<LivingEntity> nearbyMonsters = this.getWorld().getEntitiesByClass(
                    LivingEntity.class,
                    this.getBoundingBox().expand(5.0), // 周囲5ブロックの範囲
                    entity -> entity.isAlive() && entity instanceof Monster // 条件: 生存している, Monster のインスタンス
            );

            // ターゲットの設定
            if (!nearbyMonsters.isEmpty()) {
                // 最も近いモンスターをターゲットに設定
                this.target = nearbyMonsters.get(0);
            }

            // ターゲットがいる場合は追尾
            if (this.target != null && this.target.isAlive()) {
                double d = (double)this.target.getHeight() * 0.5;
                BlockPos targetPos = this.target.getBlockPos().add(0, (int) d, 0);

                double h = targetPos.getX() + 0.5 - this.getX();
                double j = targetPos.getY() + 0.5 - this.getY();
                double k = targetPos.getZ() + 0.5 - this.getZ();
                double l = Math.sqrt(h * h + j * j + k * k);

                if (l == 0.0) {
                    this.targetX = 0.0;
                    this.targetY = 0.0;
                    this.targetZ = 0.0;
                } else {
                    this.targetX = h / l * 0.15;
                    this.targetY = j / l * 0.15;
                    this.targetZ = k / l * 0.15;
                }

                vec3d = this.getVelocity();
                this.setVelocity(vec3d.add(
                        (this.targetX - vec3d.x) * 0.2,
                        (this.targetY - vec3d.y) * 0.2,
                        (this.targetZ - vec3d.z) * 0.2
                ));
            } else {
                // ターゲットがいない場合はそのまま直進
                if (!this.hasNoGravity()) {
                    this.setVelocity(this.getVelocity().add(0.0, -0.04, 0.0)); // 重力で下降
                }
            }

            // 衝突判定
            HitResult hitResult = ProjectileUtil.getCollision(this, this::canHit);
            if (hitResult.getType() != Type.MISS) {
                this.onCollision(hitResult);
            }
        }

        // 弾丸の位置更新とパーティクル生成
        this.checkBlockCollision();
        vec3d = this.getVelocity();
        this.setPosition(this.getX() + vec3d.x, this.getY() + vec3d.y, this.getZ() + vec3d.z);
        ProjectileUtil.setRotationFromVelocity(this, 0.5F);

        if (this.getWorld().isClient) {
            //パーティクル生成
            spawnCircleParticles();
            spawnTailParticles();
//            this.getWorld().addParticle(ParticleTypes.END_ROD, this.getX() - vec3d.x, this.getY() - vec3d.y + 0.15, this.getZ() - vec3d.z, 0.0, 0.0, 0.0);
        }
    }


    protected boolean canHit(Entity entity) {
        return super.canHit(entity) && !entity.noClip;
    }

    public boolean isOnFire() {
        return false;
    }

    public boolean shouldRender(double distance) {
        return distance < 16384.0;
    }

    public float getBrightnessAtEyes() {
        return 1.0F;
    }

    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        Entity entity2 = this.getOwner();
        LivingEntity livingEntity = entity2 instanceof LivingEntity ? (LivingEntity)entity2 : null;
        boolean bl = entity.damage(this.getDamageSources().mobProjectile(this, livingEntity), 8.0F);
        if (bl) {
            this.applyDamageEffects(livingEntity, entity);
        }

    }

    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        ((ServerWorld)this.getWorld()).spawnParticles(ParticleTypes.EXPLOSION, this.getX(), this.getY(), this.getZ(), 2, 0.2, 0.2, 0.2, 0.0);
        this.playSound(SoundEvents.ENTITY_SHULKER_BULLET_HIT, 1.0F, 1.0F);
    }

    private void destroy() {
        this.discard();
        this.getWorld().emitGameEvent(GameEvent.ENTITY_DAMAGE, this.getPos(), Emitter.of(this));
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        this.destroy();
    }

    public boolean canHit() {
        return true;
    }

    public boolean damage(DamageSource source, float amount) {
        if (!this.getWorld().isClient) {
            this.playSound(SoundEvents.ENTITY_SHULKER_BULLET_HURT, 1.0F, 1.0F);
            ((ServerWorld)this.getWorld()).spawnParticles(ParticleTypes.CRIT, this.getX(), this.getY(), this.getZ(), 15, 0.2, 0.2, 0.2, 0.0);
            this.destroy();
        }

        return true;
    }

    public void onSpawnPacket(EntitySpawnS2CPacket packet) {
        super.onSpawnPacket(packet);
        double d = packet.getVelocityX();
        double e = packet.getVelocityY();
        double f = packet.getVelocityZ();
        this.setVelocity(d, e, f);
    }
}

