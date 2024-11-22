package ricky.terrariamod.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import ricky.terrariamod.world.ModPlacedFeatures;
import ricky.terrariamod.world.biome.ModBiomes;

public class ModTreeGeneration {
    public static void  generateTrees() {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(ModBiomes.EBON_BIOME),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.EBON_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(ModBiomes.CRIM_BIOME),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.CRIM_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(ModBiomes.PEARL_BIOME),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.PEARL_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(ModBiomes.EBON_BIOME,ModBiomes.CRIM_BIOME),
                GenerationStep.Feature.TOP_LAYER_MODIFICATION, ModPlacedFeatures.DEATH_WEED_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.SNOWY_PLAINS),
                GenerationStep.Feature.TOP_LAYER_MODIFICATION, ModPlacedFeatures.SHIVER_THORN_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(ModBiomes.GLOWING_MUSHROOM_BIOME),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.GLOWING_HUGE_MUSHROOM_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(ModBiomes.GLOWING_MUSHROOM_BIOME),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.GLOWING_MUSHROOM_PLACED_KEY);
    }
}
