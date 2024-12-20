package ricky.terrariamod.item.magic;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import ricky.terrariamod.entity.magic.MagicBallEntity;
import ricky.terrariamod.item.AttackableItem;
import ricky.terrariamod.util.ManaData;

public class WaterBoltItem extends SwordItem implements AttackableItem {
    public WaterBoltItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }
    @Override
    public void attack(World world, PlayerEntity playerEntity, Hand hand) {
        if (!world.isClient) {
            //十分なマナがあるかクリエイティブモードの場合
            if(ManaData.useMana(playerEntity,40)){
                ItemStack enchanted = playerEntity.getStackInHand(hand);
                enchanted.damage(1, playerEntity, p -> p.sendToolBreakStatus(hand));

                // 魔法弾 を生成
                MagicBallEntity ballEntity  = new MagicBallEntity(world, playerEntity, enchanted,10, 5, 4, 0, 0.4f, 0.8f);
                ballEntity .setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, 1F, 0F);
                if (playerEntity.getAbilities().creativeMode) {
                    ballEntity .pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                }
                world.spawnEntity(ballEntity);
            }
        }
    }
}
