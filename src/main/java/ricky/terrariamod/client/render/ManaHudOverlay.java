package ricky.terrariamod.client.render;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.util.Identifier;
import ricky.terrariamod.TerrariaMod;
import ricky.terrariamod.util.IEntityDataSaver;

public class ManaHudOverlay implements HudRenderCallback {
    private static final Identifier MANA = new Identifier(TerrariaMod.MOD_ID, "textures/misc/mana.png");
    private static final Identifier NO_MANA = new Identifier(TerrariaMod.MOD_ID, "textures/misc/no_mana.png");

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null) {
            // クライアントが存在しない、またはプレイヤーが存在しない場合は描画しない
            return;
        }

        // 画面の幅を取得
        int x = client.getWindow().getScaledWidth();

        RenderSystem.setShader(GameRenderer::getPositionTexColorProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        // NO_MANAのアイコンを描画
        RenderSystem.setShaderTexture(0, NO_MANA);
        for (int i = 0; i < 10; i++) {
            drawContext.drawTexture(NO_MANA, x - 20, 90 - (i * 9), 0, 0, 12, 12, 12, 12);
        }

        // プレイヤーのMana残量を取得
        int currentMana = ((IEntityDataSaver) client.player).getPersistentData().getInt("mana");

        // MANAのアイコンを描画
        RenderSystem.setShaderTexture(0, MANA);
        for (int i = 0; i < 10; i++) {
            // Mana残量に応じて描画
            if (currentMana / 20.0 - 0.5 > i) {
                drawContext.drawTexture(MANA, x - 20, 90 - (i * 9), 0, 0, 12, 12, 12, 12);
            } else {
                break;
            }
        }
    }
}
