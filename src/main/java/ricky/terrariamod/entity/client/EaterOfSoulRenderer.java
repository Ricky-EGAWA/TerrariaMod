package ricky.terrariamod.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import ricky.terrariamod.TerrariaMod;
import ricky.terrariamod.entity.custom.EaterOfSoulEntity;

public class EaterOfSoulRenderer extends MobEntityRenderer<EaterOfSoulEntity, EaterOfSoulModel<EaterOfSoulEntity>> {
    private static final Identifier TEXTURE = new Identifier(TerrariaMod.MOD_ID, "textures/entity/eater_of_soul.png");
    public EaterOfSoulRenderer(EntityRendererFactory.Context context) {
        super(context, new EaterOfSoulModel<>(context.getPart(ModModelLayers.EATER_OF_SOUL)), 0.6f);
    }

    @Override
    public Identifier getTexture(EaterOfSoulEntity entity) {
        return TEXTURE;
    }
}
