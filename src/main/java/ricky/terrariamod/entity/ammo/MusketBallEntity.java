package ricky.terrariamod.entity.ammo;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;
import ricky.terrariamod.entity.ModEntities;
import ricky.terrariamod.item.ModItems;

public class MusketBallEntity extends PersistentProjectileEntity {
    private static final TrackedData<Integer> COLOR;

    public MusketBallEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public MusketBallEntity(World world, double x, double y, double z) {
        super(ModEntities.MUSKET_BALL, x, y, z, world);
    }

    public MusketBallEntity(World world, LivingEntity owner) {
        super(ModEntities.MUSKET_BALL, owner, world);
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(COLOR, -1);
    }

    public void tick() {
        super.tick();
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
    }

    protected void onHit(LivingEntity target) {
        super.onHit(target);

        // カスタム DamageSource を作成
        DamageSource damageSource = this.getWorld().getDamageSources().arrow(this, this.getOwner());

        // ダメージを与える
        target.damage(damageSource, 2.5F);

        // 無敵時間をリセット
        target.timeUntilRegen = 0;
    }

    protected ItemStack asItemStack() {
        return new ItemStack(ModItems.MUSKET_BALL);
    }

    public void handleStatus(byte status) {
        super.handleStatus(status);
    }

    static {
        COLOR = DataTracker.registerData(MusketBallEntity.class, TrackedDataHandlerRegistry.INTEGER);
    }
    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult); // 必要ならスーパークラスの処理も呼び出す
        this.discard(); // エンティティを消滅させる
    }
}
