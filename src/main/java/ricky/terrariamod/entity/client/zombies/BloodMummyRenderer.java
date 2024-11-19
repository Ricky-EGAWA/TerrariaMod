package ricky.terrariamod.entity.client.zombies;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.Identifier;
import ricky.terrariamod.TerrariaMod;
import ricky.terrariamod.entity.client.ModModelLayers;

@Environment(EnvType.CLIENT)
public class BloodMummyRenderer extends MobEntityRenderer<ZombieEntity, ZombieTypeModel<ZombieEntity>> {
    private static final Identifier TEXTURE = new Identifier(TerrariaMod.MOD_ID, "textures/entity/blood_mummy.png");

    public BloodMummyRenderer(EntityRendererFactory.Context context) {
        super(context, new ZombieTypeModel<>(context.getPart(ModModelLayers.BLOOD_MUMMY_LAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(ZombieEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(ZombieEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if (mobEntity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(1f, 1f, 1f);
        }
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
