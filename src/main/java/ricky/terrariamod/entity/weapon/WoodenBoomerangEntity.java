package ricky.terrariamod.entity.weapon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import ricky.terrariamod.entity.ModEntities;
import ricky.terrariamod.item.ModItems;

public class WoodenBoomerangEntity extends PersistentProjectileEntity {
    private Vec3d initialPosition; // 投げられた位置
    private boolean returning; // プレイヤーに戻る状態フラグ
    private int tickNum = 0;
    private ItemStack boomerangStack; // ブーメランの状態を保持するアイテムスタック

    public WoodenBoomerangEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
        this.boomerangStack = new ItemStack(ModItems.WOODEN_BOOMERANG); // デフォルトのアイテムスタックを設定
    }

    public WoodenBoomerangEntity(World world, LivingEntity owner, ItemStack stack) {
        super(ModEntities.WOODEN_BOOMERANG, owner, world);
        this.initialPosition = owner.getPos(); // プレイヤーの位置を初期値として設定
        this.boomerangStack = stack.copy(); // 渡されたスタックをコピーして保持
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.put("Boomerang", this.boomerangStack.writeNbt(new NbtCompound())); // アイテムスタックを保存
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("Boomerang", 10)) { // "Boomerang" が存在するか確認
            this.boomerangStack = ItemStack.fromNbt(nbt.getCompound("Boomerang")); // アイテムスタックを復元
        }
    }

    @Override
    protected ItemStack asItemStack() {
        return this.boomerangStack.copy(); // 現在のスタックをコピーして返す
    }

    @Override
    public void tick() {
        super.tick();
        this.setNoGravity(true); // 重力を完全無効化

        // 回転を適用（X軸周りの回転）
        this.setPitch((tickNum * 20) % 360); // ピッチを更新（360度でループ）
        tickNum++;

        if (!this.getWorld().isClient) {
            if (!returning) {
                if (initialPosition == null) {
                    this.discard();
                } else if (this.getPos().distanceTo(initialPosition) > 10) { // 10ブロック離れたら帰ってくる
                    this.returning = true;
                }
            } else {
                this.noClip = true;
                LivingEntity owner = (LivingEntity) this.getOwner();
                if (owner == null) {
                    this.discard();
                } else {
                    Vec3d directionToOwner = owner.getPos().add(0, owner.getStandingEyeHeight(), 0).subtract(this.getPos()).normalize();
                    this.setVelocity(directionToOwner.multiply(0.5));
                }
            }
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        if (!returning) {
            Vec3d velocity = this.getVelocity();
            this.setVelocity(velocity.multiply(-1));
            Vec3d currentPos = this.getPos();
            Vec3d previousPos = currentPos.subtract(velocity); // 1ティック前の位置を計算
            this.setPosition(previousPos.x, previousPos.y, previousPos.z);
            this.returning = true;
        }
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        if (!returning) {
            if (entityHitResult.getEntity() != this.getOwner()) {
                this.returning = true;
                Entity entity2 = this.getOwner();
                DamageSource damageSource = this.getDamageSources().trident(this, (entity2 == null ? this : entity2));
                entityHitResult.getEntity().damage(damageSource, 5.0F);  // 5ダメージを与える

                // 耐久値を減少
                this.boomerangStack.damage(1, this.getWorld().random, null);

                // 耐久値が0になったら削除
                if (this.boomerangStack.getDamage() >= this.boomerangStack.getMaxDamage()) {
                    this.discard();
                }
            }
        }
    }

    @Override
    public boolean isAttackable() {
        return false; // 攻撃されない
    }
}
