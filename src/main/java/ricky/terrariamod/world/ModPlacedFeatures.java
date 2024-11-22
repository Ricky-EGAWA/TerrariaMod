package ricky.terrariamod.world;

import com.google.common.collect.ImmutableList;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.placementmodifier.*;
import org.jetbrains.annotations.Nullable;
import ricky.terrariamod.TerrariaMod;
import ricky.terrariamod.block.ModBlocks;

import java.util.List;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> COBALT_ORE_PLACED_KEY = registerKey("cobalt_ore_placed");
    public static final RegistryKey<PlacedFeature> ORICHALCUM_ORE_PLACED_KEY = registerKey("orichalcum_ore_placed");
    public static final RegistryKey<PlacedFeature> ADAMANTITE_ORE_PLACED_KEY = registerKey("adamantite_ore_placed");
    public static final RegistryKey<PlacedFeature> NETHER_HELLSTONE_ORE_PLACED_KEY = registerKey("nether_hellstone_ore_placed");
    public static final RegistryKey<PlacedFeature> END_COBALT_ORE_PLACED_KEY = registerKey("end_temp_ore_placed");

    public static final RegistryKey<PlacedFeature> EBON_PLACED_KEY = registerKey("ebon_tree_placed");
    public static final RegistryKey<PlacedFeature> CRIM_PLACED_KEY = registerKey("crim_tree_placed");
    public static final RegistryKey<PlacedFeature> PEARL_PLACED_KEY = registerKey("pearl_tree_placed");
    public static final RegistryKey<PlacedFeature> DEATH_WEED_PLACED_KEY = registerKey("death_weed_placed");
    public static final RegistryKey<PlacedFeature> SHIVER_THORN_PLACED_KEY = registerKey("shiver_thorn");
    public static final RegistryKey<PlacedFeature> GLOWING_MUSHROOM_PLACED_KEY = registerKey("glowing_mushroom");
    public static final RegistryKey<PlacedFeature> GLOWING_HUGE_MUSHROOM_PLACED_KEY = registerKey("glowing_huge_mushroom_placed");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, COBALT_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.COBALT_ORE_KEY),
                ModOrePlacement.modifiersWithCount(5,
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-60), YOffset.fixed(20))));
        register(context, ORICHALCUM_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ORICHALCUM_ORE_KEY),
                ModOrePlacement.modifiersWithCount(12,
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-60), YOffset.fixed(10))));
        register(context, ADAMANTITE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ADAMANTITE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(12,
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-60), YOffset.fixed(5))));
        register(context, NETHER_HELLSTONE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.NETHER_HELLSTONE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(3,
                        HeightRangePlacementModifier.uniform(YOffset.fixed(5), YOffset.fixed(25))));
        register(context, END_COBALT_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.END_COBALT_ORE_KEY),
                ModOrePlacement.modifiersWithCount(12,
                        HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(80))));
        register(context, EBON_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.EBON_TREE_KEY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(2,0.1f,2),
                        ModBlocks.EBON_SAPLING));
        register(context, CRIM_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.CRIM_TREE_KEY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(2,0.1f,2),
                        ModBlocks.CRIM_SAPLING));
        register(context, PEARL_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.PEARL_TREE_KEY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(2,0.1f,2),
                        ModBlocks.PEARL_SAPLING));
        register(context, DEATH_WEED_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.DEATH_WEED_KEY),
                VegetationPlacedFeatures.modifiers(1));
        register(context, SHIVER_THORN_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.SHIVER_THORN),
                VegetationPlacedFeatures.modifiers(1));
        register(context, GLOWING_MUSHROOM_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.GLOWING_MUSHROOM),
                mushroomModifiers(1, CountPlacementModifier.of(32)));
        register(context, GLOWING_HUGE_MUSHROOM_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.HUGE_GLOWING_MUSHROOM),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(3,0.1f,2),
                        ModBlocks.GLOWING_MUSHROOM));
    }

    private static List<PlacementModifier> mushroomModifiers(int chance, @Nullable PlacementModifier modifier) {
        ImmutableList.Builder<PlacementModifier> builder = ImmutableList.builder();
        if (modifier != null) {
            builder.add(modifier);
        }

        if (chance != 0) {
            builder.add(RarityFilterPlacementModifier.of(chance));
        }

        builder.add(SquarePlacementModifier.of());
        builder.add(PlacedFeatures.BOTTOM_TO_TOP_RANGE);
        builder.add(BiomePlacementModifier.of());
        return builder.build();
    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(TerrariaMod.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
