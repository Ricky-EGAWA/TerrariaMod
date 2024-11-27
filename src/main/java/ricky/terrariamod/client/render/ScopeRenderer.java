package ricky.terrariamod.client.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)  // クライアント専用として指定
public class ScopeRenderer {
    private static final Identifier SCOPE_TEXTURE = new Identifier("textures/misc/spyglass_scope.png");

    public static void renderScope(DrawContext context, float scale) {
        MinecraftClient client = MinecraftClient.getInstance();
        int scaledWidth = client.getWindow().getScaledWidth();
        int scaledHeight = client.getWindow().getScaledHeight();

        // スコープのサイズを決定
        float f = (float)Math.min(scaledWidth, scaledHeight);
        float h = Math.min(scaledWidth / f, scaledHeight / f) * scale;
        int i = (int)(f * h);
        int j = (int)(f * h);

        // スコープのオーバーレイの位置とサイズを計算
        int k = (scaledWidth - i) / 2;
        int l = (scaledHeight - j) / 2;

        // スコープテクスチャを描画
        context.drawTexture(SCOPE_TEXTURE, k, l, -90, 0.0F, 0.0F, i, j, i, j);

        // スコープの外側の黒いフレームを描画
        context.fill(0, 0, scaledWidth, l, -16777216);  // 上の黒い部分
        context.fill(0, l, k, scaledHeight, -16777216);  // 左の黒い部分
        context.fill(k + i, l, scaledWidth, scaledHeight, -16777216);  // 右の黒い部分
        context.fill(0, l + j, scaledWidth, scaledHeight, -16777216);  // 下の黒い部分
    }
}

