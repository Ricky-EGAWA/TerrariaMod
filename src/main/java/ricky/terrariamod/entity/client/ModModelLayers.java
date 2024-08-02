package ricky.terrariamod.entity.client;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;
import ricky.terrariamod.TerrariaMod;

public class ModModelLayers {
    public static final EntityModelLayer FROZENZOMBIE_LAYER =
            new EntityModelLayer(new Identifier(TerrariaMod.MOD_ID, "frozenzombie"), "main");
    public static final EntityModelLayer PORCUPINE =
            new EntityModelLayer(new Identifier(TerrariaMod.MOD_ID, "porcupine"), "main2");


}
