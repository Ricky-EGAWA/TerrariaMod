package ricky.terrariamod.entity.boss;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import ricky.terrariamod.TerrariaMod;
import ricky.terrariamod.entity.client.ModModelLayers;

public class EyeOfCthulhuRenderer extends MobEntityRenderer<EyeOfCthulhuEntity, EyeOfCthulhuModel<EyeOfCthulhuEntity>> {
    private static final Identifier TEXTURE = new Identifier(TerrariaMod.MOD_ID, "textures/entity/demon_eye.png");
    public EyeOfCthulhuRenderer(EntityRendererFactory.Context context) {
        super(context, new EyeOfCthulhuModel<>(context.getPart(ModModelLayers.DEMON_EYE)), 0.6f);
    }

    @Override
    public Identifier getTexture(EyeOfCthulhuEntity entity) {
        return TEXTURE;
    }
}
