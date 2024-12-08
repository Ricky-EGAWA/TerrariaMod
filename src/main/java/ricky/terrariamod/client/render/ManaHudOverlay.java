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
    public void onHudRender(DrawContext drawContext, float tickDelta) {//TODO 場所の調整
        int x = 0;
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null) {
            x = client.getWindow().getScaledWidth();
        }

        RenderSystem.setShader(GameRenderer::getPositionTexColorProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, NO_MANA);
        for(int i = 0; i < 10; i++) {
            drawContext.drawTexture(NO_MANA,x - 20 ,90 - (i * 9),0,0,12,12,
                    12,12);
        }

        int currentMana = ((IEntityDataSaver) MinecraftClient.getInstance().player).getPersistentData().getInt("mana");

        RenderSystem.setShaderTexture(0, MANA);
        for(int i = 0; i < 10; i++) {
            //Mana 残量を確認し残量に応じて描画
            if(currentMana/20.0 -0.5 > i) {
                drawContext.drawTexture(MANA,x - 20 ,90 - (i * 9),0,0,12,12,
                        12,12);
            } else {
                break;
            }
        }
    }
}
