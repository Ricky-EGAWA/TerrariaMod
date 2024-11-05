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
import ricky.terrariamod.entity.custom.JungleBatEntity;

@Environment(value=EnvType.CLIENT)
public class JungleBatEntityRenderer
extends MobEntityRenderer<JungleBatEntity, JungleBatEntityModel> {
    private static final Identifier TEXTURE = new Identifier(TerrariaMod.MOD_ID,"textures/entity/jungle_bat.png");

    public JungleBatEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new JungleBatEntityModel(context.getPart(ModModelLayers.JUNGLE_BAT_LAYER)), 0.25f);
    }

    @Override
    public Identifier getTexture(JungleBatEntity batEntity) {
        return TEXTURE;
    }

    @Override
    protected void scale(JungleBatEntity batEntity, MatrixStack matrixStack, float f) {
        matrixStack.scale(0.35f, 0.35f, 0.35f);
    }

    @Override
    protected void setupTransforms(JungleBatEntity batEntity, MatrixStack matrixStack, float f, float g, float h) {
        if (batEntity.isRoosting()) {
            matrixStack.translate(0.0f, -0.1f, 0.0f);
        } else {
            matrixStack.translate(0.0f, MathHelper.cos(f * 0.3f) * 0.1f, 0.0f);
        }
        super.setupTransforms(batEntity, matrixStack, f, g, h);
    }
}

