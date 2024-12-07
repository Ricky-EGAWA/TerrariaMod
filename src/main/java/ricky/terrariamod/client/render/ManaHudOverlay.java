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
        int y = 0;
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null) {
            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();

            x = width / 2;
            y = height;
        }

        RenderSystem.setShader(GameRenderer::getPositionTexColorProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, NO_MANA);
        for(int i = 0; i < 10; i++) {
            drawContext.drawTexture(NO_MANA,x - 94 + (i * 9),y - 54,0,0,12,12,
                    12,12);
        }

        RenderSystem.setShaderTexture(0, MANA);
        for(int i = 0; i < 10; i++) {
            if(((IEntityDataSaver) MinecraftClient.getInstance().player).getPersistentData().getInt("mana") > i) {
                drawContext.drawTexture(MANA,x - 94 + (i * 9),y - 54,0,0,12,12,
                        12,12);
            } else {
                break;
            }
        }
    }
}
