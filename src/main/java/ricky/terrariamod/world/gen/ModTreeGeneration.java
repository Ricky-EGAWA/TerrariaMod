package ricky.terrariamod.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import ricky.terrariamod.world.ModPlacedFeatures;
import ricky.terrariamod.world.biome.ModBiomes;

public class ModTreeGeneration {
    public static void  generateTrees() {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(ModBiomes.EBON_BIOME),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.EBON_PLACED_KEY);
    }
}
