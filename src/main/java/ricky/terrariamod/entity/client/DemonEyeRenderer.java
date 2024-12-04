package ricky.terrariamod.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import ricky.terrariamod.TerrariaMod;
import ricky.terrariamod.entity.custom.DemonEyeEntity;

public class DemonEyeRenderer extends MobEntityRenderer<DemonEyeEntity, DemonEyeModel<DemonEyeEntity>> {
    private static final Identifier TEXTURE = new Identifier(TerrariaMod.MOD_ID, "textures/entity/demon_eye.png");
    public DemonEyeRenderer(EntityRendererFactory.Context context) {
        super(context, new DemonEyeModel<>(context.getPart(ModModelLayers.DEMON_EYE)), 0.6f);
    }

    @Override
    public Identifier getTexture(DemonEyeEntity entity) {
        return TEXTURE;
    }
}
