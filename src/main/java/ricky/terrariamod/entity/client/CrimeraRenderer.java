package ricky.terrariamod.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import ricky.terrariamod.TerrariaMod;
import ricky.terrariamod.entity.custom.CrimeraEntity;

public class CrimeraRenderer extends MobEntityRenderer<CrimeraEntity, CrimeraModel<CrimeraEntity>> {
    private static final Identifier TEXTURE = new Identifier(TerrariaMod.MOD_ID, "textures/entity/crimera.png");
    public CrimeraRenderer(EntityRendererFactory.Context context) {
        super(context, new CrimeraModel<>(context.getPart(ModModelLayers.CRIMERA)), 0.6f);
    }

    @Override
    public Identifier getTexture(CrimeraEntity entity) {
        return TEXTURE;
    }
}
