package ricky.terrariamod.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import ricky.terrariamod.item.ModItems;

import java.util.concurrent.ThreadLocalRandom;

public class PresentItem extends Item {
    public PresentItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        // 現在使用中のアイテムスタックを取得
        ItemStack itemStack = player.getStackInHand(hand);

        // サーバー側でのみ処理を実行
        if (!world.isClient) {
            // ランダムで付与するアイテムのリスト
            Item[] possibleItems = new Item[] {
                    ModItems.CANDY_CANE_PICKAXE,
                    ModItems.CANDY_CANE_SWORD,
                    ModItems.FRUITCAKE_CHAKRAM,
                    Items.COAL
            };

            // ランダムなインデックスを選択
            int randomIndex = ThreadLocalRandom.current().nextInt(possibleItems.length);
            ItemStack newItem = new ItemStack(possibleItems[randomIndex]);

            // プレイヤーに選択されたアイテムを付与
            player.giveItemStack(newItem);

            // 現在のアイテムスタックを1つ減らす
            itemStack.decrement(1);
        }

        // アクションの結果を返す
        return TypedActionResult.success(itemStack, world.isClient);
    }
}
