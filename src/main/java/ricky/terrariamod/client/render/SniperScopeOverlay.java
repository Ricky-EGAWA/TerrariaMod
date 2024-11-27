package ricky.terrariamod.client.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

@Environment(EnvType.CLIENT)
public class SniperScopeOverlay {
    public static void renderIfUsingScope(DrawContext context) {
        MinecraftClient client = MinecraftClient.getInstance();
        ClientPlayerEntity player = client.player;

        // プレイヤーが存在し、アイテムを使用中か確認
        if (player != null && player.isUsingItem()) {
            // 使用中のアイテムがスナイパーライフルか確認
            ItemStack activeItem = player.getStackInHand(Hand.MAIN_HAND);  // メインハンドのアイテム
            if (isSniperRifle(activeItem)) {
                // スコープのオーバーレイを描画
                ScopeRenderer.renderScope(context, 0.5F);
            }
        }
    }

    // スナイパーライフルかどうかを判定するメソッド（アイテムを特定するロジックを記述）
    private static boolean isSniperRifle(ItemStack stack) {
        return stack != null && stack.getItem().getTranslationKey().equals("item.terrariamod.sniper_rifle");
    }
}

