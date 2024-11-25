package ricky.terrariamod.entity.ammo;

import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import ricky.terrariamod.entity.ModEntities;

public class RocketEntity extends PersistentProjectileEntity {

    public RocketEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public RocketEntity(World world, LivingEntity owner) {
        super(ModEntities.ROCKET, owner, world);  // ModEntities.ROCKET は EntityType のインスタンス
    }

    // PersistentProjectileEntity で必要な asItemStack メソッドを実装
    @Override
    public ItemStack asItemStack() {
        return new ItemStack(Items.FIREWORK_ROCKET); // ItemStack を生成して返す
    }
}
