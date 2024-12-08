package ricky.terrariamod.item.magic;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import ricky.terrariamod.entity.ammo.EnchantedSwordEntity;
import ricky.terrariamod.util.IEntityDataSaver;
import ricky.terrariamod.util.ManaData;

public class EnchantedSwordItem extends SwordItem {
    public EnchantedSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }
    public void attack(World world, PlayerEntity playerEntity, Hand hand) {
        if (!world.isClient) {
            ItemStack enchanted = playerEntity.getStackInHand(hand);
            enchanted.damage(1, playerEntity, p -> p.sendToolBreakStatus(hand));

            // EnchantedSwordEntity を生成
            EnchantedSwordEntity swordEntity  = new EnchantedSwordEntity(world, playerEntity, enchanted);
            swordEntity .setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, 1.5F, 1.0F);
            if (playerEntity.getAbilities().creativeMode) {
                swordEntity .pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
            }
            world.spawnEntity(swordEntity);
        }
    }
}
