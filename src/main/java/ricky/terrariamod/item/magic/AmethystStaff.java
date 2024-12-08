package ricky.terrariamod.item.magic;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import ricky.terrariamod.entity.magic.MagicBallEntity;
import ricky.terrariamod.util.IEntityDataSaver;
import ricky.terrariamod.util.ManaData;

public class AmethystStaff extends SwordItem {
    public AmethystStaff(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Item.Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }
    public void attack(World world, PlayerEntity playerEntity, Hand hand) {
        if (!world.isClient) {
            int currentMana = 0; // 初期値を設定
            if (MinecraftClient.getInstance().player != null) {
                currentMana = ((IEntityDataSaver) MinecraftClient.getInstance().player).getPersistentData().getInt("mana");
            }
            if(currentMana>=40){
                IEntityDataSaver dataPlayer = ((IEntityDataSaver) playerEntity);
                ManaData.removeMana(dataPlayer,40);

                ItemStack enchanted = playerEntity.getStackInHand(hand);
                enchanted.damage(1, playerEntity, p -> p.sendToolBreakStatus(hand));

                // 魔法弾 を生成
                MagicBallEntity ballEntity  = new MagicBallEntity(world, playerEntity, enchanted);
                ballEntity .setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, 1.5F, 1.0F);
                if (playerEntity.getAbilities().creativeMode) {
                    ballEntity .pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                }
                world.spawnEntity(ballEntity);
            }
        }
    }
}
