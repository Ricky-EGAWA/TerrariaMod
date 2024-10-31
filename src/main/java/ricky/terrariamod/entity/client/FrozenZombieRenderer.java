package ricky.terrariamod.entity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import ricky.terrariamod.TerrariaMod;
import ricky.terrariamod.entity.custom.FrozenZombieEntity;

@Environment(EnvType.CLIENT)
public class FrozenZombieRenderer extends MobEntityRenderer<FrozenZombieEntity, FrozenZombieModel<FrozenZombieEntity>> {
    private static final Identifier TEXTURE = new Identifier(TerrariaMod.MOD_ID, "textures/entity/frozenzombie.png");

    public FrozenZombieRenderer(EntityRendererFactory.Context context) {
        super(context, new FrozenZombieModel<>(context.getPart(ModModelLayers.FROZENZOMBIE_LAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(FrozenZombieEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(FrozenZombieEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if (mobEntity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(1f, 1f, 1f);
        }
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
