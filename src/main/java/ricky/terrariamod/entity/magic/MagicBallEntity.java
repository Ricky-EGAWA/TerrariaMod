package ricky.terrariamod.entity.magic;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import ricky.terrariamod.entity.ModEntities;
import ricky.terrariamod.item.ModItems;

public class MagicBallEntity extends PersistentProjectileEntity {
    private static final TrackedData<Byte> LOYALTY;
    private static final TrackedData<Boolean> BALL;
    private ItemStack tridentStack;
    private boolean dealtDamage;
    public int returnTimer;

    // 寿命を管理するタイマー（初期値: 100 ticks = 5秒）
    private int lifeTime = 100;

    public MagicBallEntity(EntityType<? extends MagicBallEntity> entityType, World world) {
        super(entityType, world);
        this.tridentStack = new ItemStack(ModItems.AMETHYST_STAFF);
    }

    public MagicBallEntity(World world, LivingEntity owner, ItemStack stack) {
        super(ModEntities.AMETHYST_BALL, owner, world);
        this.tridentStack = new ItemStack(ModItems.AMETHYST_STAFF);
        this.tridentStack = stack.copy();
        this.dataTracker.set(LOYALTY, (byte) EnchantmentHelper.getLoyalty(stack));
        this.dataTracker.set(BALL, stack.hasGlint());
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(LOYALTY, (byte)0);
        this.dataTracker.startTracking(BALL, false);
    }

    public void tick() {
        super.tick();
        this.setNoGravity(true); // 重力を完全無効化

        // エンティティの寿命を管理
        if (this.lifeTime > 0) {
            this.lifeTime--;
        } else {
            this.discard(); // 寿命が尽きたら消滅
        }

        // パーティクルのスポーン処理
        if (this.getWorld().isClient) {
            spawnTailParticles();
            spawnCircleParticles();
        }

        if (this.inGroundTime > 4) {
            this.dealtDamage = true;
        }

        Entity entity = this.getOwner();
        int i = this.dataTracker.get(LOYALTY);
        if (i > 0 && (this.dealtDamage || this.isNoClip()) && entity != null) {
            if (!this.isOwnerAlive()) {
                if (!this.getWorld().isClient && this.pickupType == PickupPermission.ALLOWED) {
                    this.dropStack(this.asItemStack(), 0.1F);
                }

                this.discard();
            } else {
                this.setNoClip(true);
                Vec3d vec3d = entity.getEyePos().subtract(this.getPos());
                this.setPos(this.getX(), this.getY() + vec3d.y * 0.015 * (double)i, this.getZ());
                if (this.getWorld().isClient) {
                    this.lastRenderY = this.getY();
                }

                double d = 0.05 * (double)i;
                this.setVelocity(this.getVelocity().multiply(0.95).add(vec3d.normalize().multiply(d)));
                if (this.returnTimer == 0) {
                    this.playSound(SoundEvents.ITEM_TRIDENT_RETURN, 10.0F, 1.0F);
                }

                ++this.returnTimer;
            }
        }
    }

    // パーティクル生成処理
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

                // アメジストの紫色 (RGB)
                float red = 0.6f;
                float green = 0.2f;
                float blue = 0.8f;

                // DustParticleEffectを生成（Vector3fで色を指定）
                world.addParticle(
                        new net.minecraft.particle.DustParticleEffect(
                                new org.joml.Vector3f(red, green, blue), // カラー
                                1.0F // スケール
                        ),
                        x, y, z,
                        offsetX, offsetY, offsetZ
                );
            }
        }
    }
    private void spawnCircleParticles() {
        World world = this.getWorld();
        if (world.isClient) { // クライアントサイドでのみ実行
            Vec3d center = this.getPos();

            // アメジストの紫色 (RGB)
            float red = 0.6f;
            float green = 0.2f;
            float blue = 0.8f;

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
                                new org.joml.Vector3f(red, green, blue), // カラー
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


    private boolean isOwnerAlive() {
        Entity entity = this.getOwner();
        if (entity != null && entity.isAlive()) {
            return !(entity instanceof ServerPlayerEntity) || !entity.isSpectator();
        } else {
            return false;
        }
    }

    protected ItemStack asItemStack() {
        return this.tridentStack.copy();
    }

    @Nullable
    protected EntityHitResult getEntityCollision(Vec3d currentPosition, Vec3d nextPosition) {
        return this.dealtDamage ? null : super.getEntityCollision(currentPosition, nextPosition);
    }

    protected void onEntityHit(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();
        float f = 8.0F;
        if (entity instanceof LivingEntity livingEntity) {
            f += EnchantmentHelper.getAttackDamage(this.tridentStack, livingEntity.getGroup());
        }

        Entity entity2 = this.getOwner();
        DamageSource damageSource = this.getDamageSources().trident(this, (entity2 == null ? this : entity2));
        this.dealtDamage = true;
        SoundEvent soundEvent = SoundEvents.ITEM_TRIDENT_HIT;
        if (entity.damage(damageSource, f)) {
            if (entity.getType() == EntityType.ENDERMAN) {
                return;
            }

            if (entity instanceof LivingEntity) {
                LivingEntity livingEntity2 = (LivingEntity)entity;
                if (entity2 instanceof LivingEntity) {
                    EnchantmentHelper.onUserDamaged(livingEntity2, entity2);
                    EnchantmentHelper.onTargetDamaged((LivingEntity)entity2, livingEntity2);
                }

                this.onHit(livingEntity2);
            }
        }

        this.setVelocity(this.getVelocity().multiply(-0.01, -0.1, -0.01));
        float g = 1.0F;
        if (this.getWorld() instanceof ServerWorld && this.getWorld().isThundering() && this.hasChanneling()) {
            BlockPos blockPos = entity.getBlockPos();
            if (this.getWorld().isSkyVisible(blockPos)) {
                LightningEntity lightningEntity = EntityType.LIGHTNING_BOLT.create(this.getWorld());
                if (lightningEntity != null) {
                    lightningEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(blockPos));
                    lightningEntity.setChanneler(entity2 instanceof ServerPlayerEntity ? (ServerPlayerEntity)entity2 : null);
                    this.getWorld().spawnEntity(lightningEntity);
                    soundEvent = SoundEvents.ITEM_TRIDENT_THUNDER;
                    g = 5.0F;
                }
            }
        }

        this.playSound(soundEvent, g, 1.0F);
    }

    public boolean hasChanneling() {
        return EnchantmentHelper.hasChanneling(this.tridentStack);
    }

    protected boolean tryPickup(PlayerEntity player) {
        return super.tryPickup(player) || this.isNoClip() && this.isOwner(player) && player.getInventory().insertStack(this.asItemStack());
    }

    protected SoundEvent getHitSound() {
        return SoundEvents.ITEM_TRIDENT_HIT_GROUND;
    }

    public void onPlayerCollision(PlayerEntity player) {
        if (this.isOwner(player) || this.getOwner() == null) {
            super.onPlayerCollision(player);
        }
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("Trident", 10)) {
            this.tridentStack = ItemStack.fromNbt(nbt.getCompound("Trident"));
        }

        this.dealtDamage = nbt.getBoolean("DealtDamage");
        this.dataTracker.set(LOYALTY, (byte)EnchantmentHelper.getLoyalty(this.tridentStack));
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.put("Trident", this.tridentStack.writeNbt(new NbtCompound()));
        nbt.putBoolean("DealtDamage", this.dealtDamage);
    }

    public void age() {
        int i = this.dataTracker.get(LOYALTY);
        if (this.pickupType != PickupPermission.ALLOWED || i <= 0) {
            super.age();
        }
    }

    protected float getDragInWater() {
        return 0.99F;
    }

    public boolean shouldRender(double cameraX, double cameraY, double cameraZ) {
        return true;
    }

    static {
        LOYALTY = DataTracker.registerData(net.minecraft.entity.projectile.TridentEntity.class, TrackedDataHandlerRegistry.BYTE);
        BALL = DataTracker.registerData(net.minecraft.entity.projectile.TridentEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        this.discard();
    }

    @Override
    protected void onHit(LivingEntity target) {
        this.discard();
    }
}
