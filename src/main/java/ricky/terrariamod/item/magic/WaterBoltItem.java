package ricky.terrariamod.item.magic;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import ricky.terrariamod.entity.magic.WaterBoltEntity;
import ricky.terrariamod.util.IEntityDataSaver;
import ricky.terrariamod.util.ManaData;

public class WaterBoltItem extends SwordItem {
    public WaterBoltItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }
    public void attack(World world, PlayerEntity playerEntity, Hand hand) {
        if (!world.isClient) {
            //十分なマナがあるかクリエイティブモードの場合
            if(ManaData.useMana((IEntityDataSaver) playerEntity,40) || playerEntity.getAbilities().creativeMode){
                ItemStack enchanted = playerEntity.getStackInHand(hand);
                enchanted.damage(1, playerEntity, p -> p.sendToolBreakStatus(hand));

                // 魔法弾 を生成
                WaterBoltEntity ballEntity  = new WaterBoltEntity(world, playerEntity, enchanted);
                ballEntity .setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, 1F, 1.0F);
                if (playerEntity.getAbilities().creativeMode) {
                    ballEntity .pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                }
                world.spawnEntity(ballEntity);
            }
        }
    }
}
