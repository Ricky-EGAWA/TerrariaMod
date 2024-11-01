package ricky.terrariamod.entity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import ricky.terrariamod.TerrariaMod;
import ricky.terrariamod.entity.custom.IceSlimeEntity;

@Environment(EnvType.CLIENT)
public class IceSlimeRenderer extends MobEntityRenderer<IceSlimeEntity, IceSlimeModel<IceSlimeEntity>> {
    private static final Identifier TEXTURE = new Identifier(TerrariaMod.MOD_ID, "textures/entity/custom_slime.png");

    public IceSlimeRenderer(EntityRendererFactory.Context context) {
        super(context, new IceSlimeModel<>(context.getPart(ModModelLayers.ICE_SLIME_LAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(IceSlimeEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(IceSlimeEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        // スライムのサイズに基づいて影のサイズを設定
        this.shadowRadius = 0.25f * mobEntity.getSize();

        // スライムの透明度とスケールを適用
        float stretchProgress = MathHelper.lerp(g, mobEntity.lastStretch, mobEntity.stretch) / (mobEntity.getSize() * 0.5f + 1.0f);
        float scale = 1.0f / (stretchProgress + 1.0f);
        matrixStack.scale(scale * mobEntity.getSize(), 1.0f / scale * mobEntity.getSize(), scale * mobEntity.getSize());

        // スライムの描画
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
