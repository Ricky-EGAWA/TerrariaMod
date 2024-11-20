package ricky.terrariamod.entity.client.zombies;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ZombieEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.Identifier;
import ricky.terrariamod.TerrariaMod;

@Environment(EnvType.CLIENT)
public class DarkMummyRenderer extends ZombieEntityRenderer {
    private static final Identifier TEXTURE = new Identifier(TerrariaMod.MOD_ID, "textures/entity/dark_mummy.png");

    public DarkMummyRenderer(EntityRendererFactory.Context context) {
        super(context, EntityModelLayers.HUSK, EntityModelLayers.HUSK_INNER_ARMOR, EntityModelLayers.HUSK_OUTER_ARMOR);
    }

    @Override
    public Identifier getTexture(ZombieEntity entity) {
        return TEXTURE;
    }
}