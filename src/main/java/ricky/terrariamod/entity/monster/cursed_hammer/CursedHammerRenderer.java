package ricky.terrariamod.entity.monster.cursed_hammer;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import ricky.terrariamod.TerrariaMod;
import ricky.terrariamod.entity.client.ModModelLayers;

public class CursedHammerRenderer extends MobEntityRenderer<CursedHammerEntity, CursedHammerModel<CursedHammerEntity>> {
    private static final Identifier TEXTURE = new Identifier(TerrariaMod.MOD_ID, "textures/entity/cursed_hammer.png");
    public CursedHammerRenderer(EntityRendererFactory.Context context) {
        super(context, new CursedHammerModel<>(context.getPart(ModModelLayers.CURSED_HAMMER)), 0.6f);
    }

    @Override
    public Identifier getTexture(CursedHammerEntity entity) {
        return TEXTURE;
    }

    @Override
    protected RenderLayer getRenderLayer(CursedHammerEntity entity, boolean showBody, boolean translucent, boolean showOutline) {
        return RenderLayer.getEntityTranslucent(this.getTexture(entity));
    }
}
