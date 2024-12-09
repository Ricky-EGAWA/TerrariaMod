package ricky.terrariamod.entity.magic;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
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
    private int lifeTime = 200;
    private int hitCount = 0;
    private int reflectionCount = 0;

    private static final TrackedData<Integer> MAX_HIT = DataTracker.registerData(MagicBallEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> MAX_REFLECT = DataTracker.registerData(MagicBallEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Float> DAMAGE = DataTracker.registerData(MagicBallEntity.class, TrackedDataHandlerRegistry.FLOAT);
    private static final TrackedData<Float> COLOR_R = DataTracker.registerData(MagicBallEntity.class, TrackedDataHandlerRegistry.FLOAT);
    private static final TrackedData<Float> COLOR_G = DataTracker.registerData(MagicBallEntity.class, TrackedDataHandlerRegistry.FLOAT);
    private static final TrackedData<Float> COLOR_B = DataTracker.registerData(MagicBallEntity.class, TrackedDataHandlerRegistry.FLOAT);


    public MagicBallEntity(EntityType<? extends MagicBallEntity> entityType, World world) {
        super(entityType, world);
        this.tridentStack = new ItemStack(ModItems.WATER_BOLT);
    }

    public MagicBallEntity(World world, LivingEntity owner, ItemStack stack, int hitCount, int reflectionCount, float damage, float r, float g, float b) {
        super(ModEntities.WATER_BOLT, owner, world);
        this.tridentStack = stack.copy();
        this.setCustomValues(hitCount, reflectionCount, damage, r, g, b);
    }

    private void setCustomValues(int hitCount, int reflectionCount, float damage, float r, float g, float b) {
        this.dataTracker.set(MAX_HIT, hitCount);
        this.dataTracker.set(MAX_REFLECT, reflectionCount);
        this.dataTracker.set(DAMAGE, damage);
        this.dataTracker.set(COLOR_R, r);
        this.dataTracker.set(COLOR_G, g);
        this.dataTracker.set(COLOR_B, b);
    }


    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(MAX_HIT, 0);
        this.dataTracker.startTracking(MAX_REFLECT, 0);
        this.dataTracker.startTracking(DAMAGE, 0.0f);
        this.dataTracker.startTracking(COLOR_R, 0.0f);
        this.dataTracker.startTracking(COLOR_G, 0.0f);
        this.dataTracker.startTracking(COLOR_B, 0.0f);
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

                float r = this.dataTracker.get(COLOR_R);
                float g = this.dataTracker.get(COLOR_G);
                float b = this.dataTracker.get(COLOR_B);

                // DustParticleEffectを生成（Vector3fで色を指定）
                world.addParticle(
                        new net.minecraft.particle.DustParticleEffect(
                                new org.joml.Vector3f(r, g, b), // カラー
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

            // 半径 0.2 ブロックの範囲でパーティクルを生成
            double radius = 0.2;

            for (int i = 0; i < 10; i++) { // パーティクル数を増やす
                double angle = this.random.nextDouble() * 2 * Math.PI; // ランダムな角度
                double offsetX = Math.cos(angle) * radius;
                double offsetZ = Math.sin(angle) * radius;

                // 高さを少しランダム化
                double offsetY = this.random.nextGaussian() * 0.02;

                float r = this.dataTracker.get(COLOR_R);
                float g = this.dataTracker.get(COLOR_G);
                float b = this.dataTracker.get(COLOR_B);

                // パーティクルを生成
                world.addParticle(
                        new net.minecraft.particle.DustParticleEffect(
                                new org.joml.Vector3f(r, g, b), // カラー
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

        DamageSource damageSource = this.getDamageSources().trident(this, this.getOwner());
        this.dealtDamage = true;

        if (entity.damage(damageSource, this.dataTracker.get(DAMAGE))) {
            if (entity instanceof LivingEntity livingEntity) {
                this.onHit(livingEntity);
            }
        }
    }
    protected void onHit(LivingEntity target) {
        // ヒット回数をチェック
        if (hitCount >= this.dataTracker.get(MAX_HIT)) {
            this.discard(); // ヒットが10回に達したらエンティティを消滅
            return;
        }
        hitCount++;
    }

    protected boolean tryPickup(PlayerEntity player) {
        return super.tryPickup(player) || player.getInventory().insertStack(this.asItemStack());
    }

    protected void onBlockHit(BlockHitResult blockHitResult) {
        // 反射回数をチェック
        if (reflectionCount >= this.dataTracker.get(MAX_REFLECT)) {
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
