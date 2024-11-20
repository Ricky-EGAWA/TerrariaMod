package ricky.terrariamod.entity.client.zombies;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.ZombieEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.Identifier;
import ricky.terrariamod.TerrariaMod;
import ricky.terrariamod.entity.client.ModModelLayers;

@Environment(EnvType.CLIENT)
public class MummyRenderer extends ZombieEntityRenderer {
    private static final Identifier TEXTURE = new Identifier(TerrariaMod.MOD_ID, "textures/entity/mummy.png");

    public MummyRenderer(EntityRendererFactory.Context context) {
        super(context, EntityModelLayers.HUSK, EntityModelLayers.HUSK_INNER_ARMOR, EntityModelLayers.HUSK_OUTER_ARMOR);
    }

    @Override
    public Identifier getTexture(ZombieEntity entity) {
        return TEXTURE;
    }
}