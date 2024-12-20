package ricky.terrariamod.item.boomerang;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import ricky.terrariamod.entity.weapon.WoodenBoomerangEntity;
import ricky.terrariamod.item.AttackableItem;

public class WoodenBoomerangItem extends Item implements AttackableItem {
    public WoodenBoomerangItem(Settings settings) {
        super(settings);
    }

    public void attack(World world, PlayerEntity playerEntity, Hand hand) {
        if (!world.isClient) {
            ItemStack boomerang = playerEntity.getStackInHand(hand);

            // アイテムの耐久値を減らす
            boomerang.damage(1, playerEntity, p -> p.sendToolBreakStatus(hand));

            // WoodenBoomerangEntity を生成
            WoodenBoomerangEntity boomerangEntity = new WoodenBoomerangEntity(world, playerEntity, boomerang);
            boomerangEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, 0.9F, 1.0F);

            // クリエイティブモードの場合のピックアップ設定
            if (playerEntity.getAbilities().creativeMode) {
                boomerangEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
            }

            // エンティティをスポーン
            world.spawnEntity(boomerangEntity);

            // サバイバルモードの場合、アイテムを1つ消費
            if (!playerEntity.getAbilities().creativeMode) {
                boomerang.decrement(1);
            }
        }
    }
}
