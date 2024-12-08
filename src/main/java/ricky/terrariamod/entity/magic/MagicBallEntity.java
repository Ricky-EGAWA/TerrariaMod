package ricky.terrariamod.entity.magic;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import ricky.terrariamod.entity.ModEntities;
import ricky.terrariamod.item.ModItems;

public class MagicBallEntity extends PersistentProjectileEntity {
    private ItemStack tridentStack;
    private boolean dealtDamage;

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
    }

    protected void initDataTracker() {
        super.initDataTracker();
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

    protected ItemStack asItemStack() {
        return this.tridentStack.copy();
    }

    @Nullable
    protected EntityHitResult getEntityCollision(Vec3d currentPosition, Vec3d nextPosition) {
        return this.dealtDamage ? null : super.getEntityCollision(currentPosition, nextPosition);
    }

    protected void onEntityHit(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();
        float damage = 8.0F;

        DamageSource damageSource = this.getDamageSources().trident(this, this.getOwner());
        this.dealtDamage = true;

        if (entity.damage(damageSource, damage)) {
            if (entity instanceof LivingEntity livingEntity) {
                this.onHit(livingEntity);
            }
        }

        this.setVelocity(this.getVelocity().multiply(-0.01, -0.1, -0.01));
    }

    protected boolean tryPickup(PlayerEntity player) {
        return super.tryPickup(player) || player.getInventory().insertStack(this.asItemStack());
    }

    protected void onBlockHit(BlockHitResult blockHitResult) {
        this.discard();
    }

    protected void onHit(LivingEntity target) {
        this.discard();
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("Trident", 10)) {
            this.tridentStack = ItemStack.fromNbt(nbt.getCompound("Trident"));
        }

        this.dealtDamage = nbt.getBoolean("DealtDamage");
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.put("Trident", this.tridentStack.writeNbt(new NbtCompound()));
        nbt.putBoolean("DealtDamage", this.dealtDamage);
    }

    public boolean shouldRender(double cameraX, double cameraY, double cameraZ) {
        return true;
    }
}
