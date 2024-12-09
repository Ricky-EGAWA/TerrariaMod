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

public class WaterBoltEntity extends PersistentProjectileEntity {
    private ItemStack tridentStack;
    private boolean dealtDamage;

    // 寿命を管理するタイマー（初期値: 100 ticks = 5秒）
    private int lifeTime = 200;

    public WaterBoltEntity(EntityType<? extends WaterBoltEntity> entityType, World world) {
        super(entityType, world);
        this.tridentStack = new ItemStack(ModItems.WATER_BOLT);
    }

    public WaterBoltEntity(World world, LivingEntity owner, ItemStack stack) {
        super(ModEntities.WATER_BOLT, owner, world);
        this.tridentStack = new ItemStack(ModItems.WATER_BOLT);
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

                // 水の色 (RGB)
                float red = 0f;
                float green = 0.4f;
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

            // 水の色 (RGB)
            float red = 0f;
            float green = 0.4f;
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
    }
    private int hitCount = 0;
    protected void onHit(LivingEntity target) {
        // ヒット回数をチェック
        if (hitCount >= 10) {
            this.discard(); // ヒットが10回に達したらエンティティを消滅
            return;
        }
        hitCount++;
    }

    protected boolean tryPickup(PlayerEntity player) {
        return super.tryPickup(player) || player.getInventory().insertStack(this.asItemStack());
    }

    // 反射回数を追跡するフィールドを追加
    private int reflectionCount = 0;

    protected void onBlockHit(BlockHitResult blockHitResult) {
        // 反射回数をチェック
        if (reflectionCount >= 5) {
            this.discard(); // 反射回数が5回に達したらエンティティを消滅
            return;
        }

        // 衝突時の位置とブロックの法線を取得
        Vec3d velocity = this.getVelocity();
        Vec3d normal = Vec3d.of(blockHitResult.getSide().getVector()); // ブロックの面法線（正規化ベクトル）

        // 速度ベクトルを反射計算
        Vec3d reflectedVelocity = velocity.subtract(normal.multiply(2 * velocity.dotProduct(normal)));

        // 減速を適用（反射後の速度を調整）
        reflectedVelocity = reflectedVelocity.multiply(0.9); // 0.9の速度で反射

        // 新しい速度をセット
        this.setVelocity(reflectedVelocity);

        // 反射の位置を微調整（埋まり防止）
        Vec3d hitPos = blockHitResult.getPos();
        this.setPos(hitPos.x + reflectedVelocity.x * 0.01,
                hitPos.y + reflectedVelocity.y * 0.01,
                hitPos.z + reflectedVelocity.z * 0.01);

        // Ground Time をリセット（必要に応じて追加）
        this.inGroundTime = 0;

        // 反射回数を増加
        reflectionCount++;
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
