/*
 * Decompiled with CFR 0.2.2 (FabricMC 7c48b8c4).
 */
package ricky.terrariamod.entity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.CodEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.CodEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import ricky.terrariamod.TerrariaMod;
import ricky.terrariamod.entity.custom.PiranhaEntity;

@Environment(value=EnvType.CLIENT)
public class PiranhaEntityRenderer
extends MobEntityRenderer<PiranhaEntity, PiranhaEntityModel<PiranhaEntity>> {
    private static final Identifier TEXTURE = new Identifier(TerrariaMod.MOD_ID,"textures/entity/piranha.png");

    public PiranhaEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new PiranhaEntityModel(context.getPart(ModModelLayers.PIRANHA_LAYER)), 0.3f);
    }

    @Override
    public Identifier getTexture(PiranhaEntity piranhaEntity) {
        return TEXTURE;
    }

    @Override
    protected void setupTransforms(PiranhaEntity piranhaEntity, MatrixStack matrixStack, float f, float g, float h) {
        super.setupTransforms(piranhaEntity, matrixStack, f, g, h);
        float i = 4.3f * MathHelper.sin(0.6f * f);
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(i));
        if (!piranhaEntity.isTouchingWater()) {
            matrixStack.translate(0.1f, 0.1f, -0.1f);
            matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(90.0f));
        }
    }
}

