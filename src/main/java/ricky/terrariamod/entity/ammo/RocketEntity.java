package ricky.terrariamod.entity.ammo;

import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import ricky.terrariamod.entity.ModEntities;

public class RocketEntity extends PersistentProjectileEntity {
    private int explosionPower = 3;
    private int lifeTime = 0; // 発射後の経過時間

    public RocketEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public RocketEntity(World world, LivingEntity owner, int explosionPower) {
        super(ModEntities.ROCKET, owner, world);
        this.explosionPower = explosionPower;
    }

    @Override
    public void tick() {
        super.tick();
        lifeTime++;
    }

    @Override
    public ItemStack asItemStack() {
        return new ItemStack(Items.FIREWORK_ROCKET);
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        // 発射後5ティック間は衝突を無効化
        if (this.lifeTime < 5 || hitResult.getType() == HitResult.Type.MISS) {
            return; // 無効な衝突、または5ティック未満であれば何もしない
        }

        super.onCollision(hitResult); // 標準の衝突処理を実行
        if (!this.getWorld().isClient) {
            // ゲームルールの確認
            boolean bl = this.getWorld().getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING);

            // 爆発を作成
            this.getWorld().createExplosion(
                    this,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    (float) this.explosionPower,
                    bl,
                    World.ExplosionSourceType.MOB
            );

            // ロケットを削除
            this.discard();
        }
    }

}
