/*
 * Decompiled with CFR 0.2.2 (FabricMC 7c48b8c4).
 */
package ricky.terrariamod.entity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import ricky.terrariamod.TerrariaMod;
import ricky.terrariamod.entity.client.SandSlimeModel;
import ricky.terrariamod.entity.custom.SandSlimeEntity;

@Environment(value=EnvType.CLIENT)
public class SandSlimeRenderer
        extends MobEntityRenderer<SlimeEntity, SandSlimeModel<SlimeEntity>> {
    private static final Identifier TEXTURE = new Identifier(TerrariaMod.MOD_ID,"textures/entity/sand_slime.png");

    public SandSlimeRenderer(EntityRendererFactory.Context context) {
        super(context, new SandSlimeModel(context.getPart(ModModelLayers.SAND_SLIME_LAYER)), 0.25f);
        this.addFeature(new SandSlimeOverlayFeatureRenderer<>(this, context.getModelLoader()));
    }

    @Override
    public void render(SlimeEntity slimeEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        this.shadowRadius = 0.25f * (float)slimeEntity.getSize();
        super.render(slimeEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    protected void scale(SlimeEntity slimeEntity, MatrixStack matrixStack, float f) {
        float g = 0.999f;
        matrixStack.scale(0.999f, 0.999f, 0.999f);
        matrixStack.translate(0.0f, 0.001f, 0.0f);
        float h = slimeEntity.getSize();
        float i = MathHelper.lerp(f, slimeEntity.lastStretch, slimeEntity.stretch) / (h * 0.5f + 1.0f);
        float j = 1.0f / (i + 1.0f);
        matrixStack.scale(j * h, 1.0f / j * h, j * h);
    }

    @Override
    public Identifier getTexture(SlimeEntity slimeEntity) {
        return TEXTURE;
    }
}

