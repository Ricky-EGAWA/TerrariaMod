package ricky.terrariamod.entity.client;

import com.google.common.collect.Sets;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;
import ricky.terrariamod.TerrariaMod;

import java.util.Set;


public class ModModelLayers {
    private static final String MAIN = "main";
    private static final Set<EntityModelLayer> LAYERS = Sets.newHashSet();
    public static final EntityModelLayer FROZENZOMBIE_LAYER =
            new EntityModelLayer(new Identifier(TerrariaMod.MOD_ID, "frozenzombie"), "main");
    public static final EntityModelLayer PORCUPINE =
            new EntityModelLayer(new Identifier(TerrariaMod.MOD_ID, "porcupine"), "main2");
    public static final EntityModelLayer ICE_SLIME_LAYER =
            new EntityModelLayer(new Identifier(TerrariaMod.MOD_ID, "ice_slime"), "ice_slime_layer");

    public static final EntityModelLayer SAND_SLIME_LAYER =
            new EntityModelLayer(new Identifier(TerrariaMod.MOD_ID, "sand_slime"), "sand_slime_layer");

    public static final EntityModelLayer JUNGLE_BAT_LAYER =
            new EntityModelLayer(new Identifier(TerrariaMod.MOD_ID, "jungle_bat"), "jungle_bat_layer");

    public static final EntityModelLayer LAVA_BAT_LAYER =
            new EntityModelLayer(new Identifier(TerrariaMod.MOD_ID, "lava_bat"), "lava_bat_layer");

    public static final EntityModelLayer PIRANHA_LAYER =
            new EntityModelLayer(new Identifier(TerrariaMod.MOD_ID, "piranha"), "piranha_layer");


    private static EntityModelLayer registerMain(String id) {
        return ModModelLayers.register(id, MAIN);
    }
    private static EntityModelLayer register(String id, String layer) {
        EntityModelLayer entityModelLayer = ModModelLayers.create(id, layer);
        if (!LAYERS.add(entityModelLayer)) {
            throw new IllegalStateException("Duplicate registration for " + entityModelLayer);
        }
        return entityModelLayer;
    }
    private static EntityModelLayer create(String id, String layer) {
        return new EntityModelLayer(new Identifier("minecraft", id), layer);
    }

}
