package ricky.terrariamod.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import ricky.terrariamod.TerrariaMod;
import ricky.terrariamod.entity.custom.PossessedArmorEntity;

public class PossessedArmorRenderer extends MobEntityRenderer<PossessedArmorEntity, PossessedArmorModel<PossessedArmorEntity>> {
    private static final Identifier TEXTURE = new Identifier(TerrariaMod.MOD_ID, "textures/entity/possessed_armor.png");
    public PossessedArmorRenderer(EntityRendererFactory.Context context) {
        super(context, new PossessedArmorModel<>(context.getPart(ModModelLayers.POSSESSED_ARMOR)), 0.6f);
    }

    @Override
    public Identifier getTexture(PossessedArmorEntity entity) {
        return TEXTURE;
    }
}
