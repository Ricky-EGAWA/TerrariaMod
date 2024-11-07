/*
 * Decompiled with CFR 0.2.2 (FabricMC 7c48b8c4).
 */
package ricky.terrariamod.entity.client.bats;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import ricky.terrariamod.TerrariaMod;
import ricky.terrariamod.entity.client.ModModelLayers;
import ricky.terrariamod.entity.custom.LavaBatEntity;

@Environment(value=EnvType.CLIENT)
public class LavaBatEntityRenderer
extends MobEntityRenderer<LavaBatEntity, LavaBatEntityModel> {
    private static final Identifier TEXTURE = new Identifier(TerrariaMod.MOD_ID,"textures/entity/lava_bat.png");

    public LavaBatEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new LavaBatEntityModel(context.getPart(ModModelLayers.LAVA_BAT_LAYER)), 0.25f);
    }

    @Override
    public Identifier getTexture(LavaBatEntity batEntity) {
        return TEXTURE;
    }

    @Override
    protected void scale(LavaBatEntity batEntity, MatrixStack matrixStack, float f) {
        matrixStack.scale(0.35f, 0.35f, 0.35f);
    }

    @Override
    protected void setupTransforms(LavaBatEntity batEntity, MatrixStack matrixStack, float f, float g, float h) {
        if (batEntity.isRoosting()) {
            matrixStack.translate(0.0f, -0.1f, 0.0f);
        } else {
            matrixStack.translate(0.0f, MathHelper.cos(f * 0.3f) * 0.1f, 0.0f);
        }
        super.setupTransforms(batEntity, matrixStack, f, g, h);
    }
}

