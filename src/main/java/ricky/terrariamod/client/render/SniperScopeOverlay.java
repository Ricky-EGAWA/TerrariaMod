package ricky.terrariamod.client.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.Perspective;
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
            if (isSniperRifle(activeItem) && isFirstPerson(client)) {
                // スコープのオーバーレイを描画
                ScopeRenderer.renderScope(context, 0.7F);//TODO 進捗やメッセージが表示されていると描画の透明度が０になってしまう
            }
        }
    }

    // スナイパーライフルかどうかを判定するメソッド（アイテムを特定するロジックを記述）
    private static boolean isSniperRifle(ItemStack stack) {
        return stack != null && stack.getItem().getTranslationKey().equals("item.terrariamod.sniper_rifle");
    }
    // プレイヤーが一人称視点かをチェックするメソッド
    private static boolean isFirstPerson(MinecraftClient client) {
        return client.options.getPerspective() == Perspective.FIRST_PERSON;
    }
}

