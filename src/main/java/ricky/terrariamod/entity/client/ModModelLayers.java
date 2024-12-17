package ricky.terrariamod.entity.client;

import com.google.common.collect.Sets;
import net.minecraft.client.render.entity.model.EntityModelLayer;
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
    public static final EntityModelLayer CORRUPT_SLIME_LAYER =
            new EntityModelLayer(new Identifier(TerrariaMod.MOD_ID, "corrupt_slime"), "corrupt_slime_layer");
    public static final EntityModelLayer CRIM_SLIME_LAYER =
            new EntityModelLayer(new Identifier(TerrariaMod.MOD_ID, "crim_slime"), "crim_slime_layer");
    public static final EntityModelLayer DUNGEON_SLIME_LAYER =
            new EntityModelLayer(new Identifier(TerrariaMod.MOD_ID, "dungeon_slime"), "dungeon_slime_layer");

    public static final EntityModelLayer JUNGLE_BAT_LAYER =
            new EntityModelLayer(new Identifier(TerrariaMod.MOD_ID, "jungle_bat"), "jungle_bat_layer");

    public static final EntityModelLayer LAVA_BAT_LAYER =
            new EntityModelLayer(new Identifier(TerrariaMod.MOD_ID, "lava_bat"), "lava_bat_layer");

    public static final EntityModelLayer PIRANHA_LAYER =
            new EntityModelLayer(new Identifier(TerrariaMod.MOD_ID, "piranha"), "piranha_layer");
    public static final EntityModelLayer MUMMY_LAYER =
            new EntityModelLayer(new Identifier(TerrariaMod.MOD_ID, "mummy"), "mummy_layer");
    public static final EntityModelLayer DARK_MUMMY_LAYER =
            new EntityModelLayer(new Identifier(TerrariaMod.MOD_ID, "dark_mummy"), "dark_mummy_layer");
    public static final EntityModelLayer BLOOD_MUMMY_LAYER =
            new EntityModelLayer(new Identifier(TerrariaMod.MOD_ID, "blood_mummy"), "blood_mummy_layer");
    public static final EntityModelLayer LIGHT_MUMMY_LAYER =
            new EntityModelLayer(new Identifier(TerrariaMod.MOD_ID, "light_mummy"), "light_mummy_layer");
    public static final EntityModelLayer EATER_OF_SOUL =
            new EntityModelLayer(new Identifier(TerrariaMod.MOD_ID, "eater_of_soul"), "eater_of_soul_layer");
    public static final EntityModelLayer DEMON_EYE =
            new EntityModelLayer(new Identifier(TerrariaMod.MOD_ID, "demon_eye"), "demon_eye_layer");
    public static final EntityModelLayer CRIMERA =
            new EntityModelLayer(new Identifier(TerrariaMod.MOD_ID, "crimera"), "crimera_layer");
    public static final EntityModelLayer POSSESSED_ARMOR =
            new EntityModelLayer(new Identifier(TerrariaMod.MOD_ID, "possessed_armor"), "possessed_armor_layer");

    public static final EntityModelLayer ENCHANTED_SWORD =
            new EntityModelLayer(new Identifier(TerrariaMod.MOD_ID, "enchanted_sword"), "enchanted_sword_layer");
    public static final EntityModelLayer AMETHYST_BALL =
            new EntityModelLayer(new Identifier(TerrariaMod.MOD_ID, "amethyst_ball"), "amethyst_ball_layer");
    public static final EntityModelLayer WATER_BOLT =
            new EntityModelLayer(new Identifier(TerrariaMod.MOD_ID, "water_bolt"), "water_bolt_layer");
    public static final EntityModelLayer MAGIC_MISSILE =
            new EntityModelLayer(new Identifier(TerrariaMod.MOD_ID, "magic_missile"), "magic_missile_layer");


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
