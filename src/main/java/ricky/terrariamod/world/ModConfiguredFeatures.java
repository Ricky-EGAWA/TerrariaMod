package ricky.terrariamod.world;

import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import ricky.terrariamod.TerrariaMod;
import ricky.terrariamod.block.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> COBALT_ORE_KEY = registerKey("cobalt_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORICHALCUM_ORE_KEY = registerKey("orichalcum_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ADAMANTITE_ORE_KEY = registerKey("adamantite_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> NETHER_HELLSTONE_ORE_KEY = registerKey("hellstone_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> END_COBALT_ORE_KEY = registerKey("end_temp_ore");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context){
        RuleTest stoneReplacables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplacables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherReplacables = new TagMatchRuleTest(BlockTags.BASE_STONE_NETHER);
        RuleTest endReplacables = new BlockMatchRuleTest(Blocks.END_STONE);

        List<OreFeatureConfig.Target> overworldCobaltOres =
                List.of(OreFeatureConfig.createTarget(stoneReplacables, ModBlocks.COBALT_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.DEEPSLATE_COBALT_ORE.getDefaultState()));
        List<OreFeatureConfig.Target> overworldOrichalcumOres =
                List.of(OreFeatureConfig.createTarget(stoneReplacables, ModBlocks.ORICHALCUM_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.DEEPSLATE_ORICHALCUM_ORE.getDefaultState()));
        List<OreFeatureConfig.Target> overworldAdamantiteOres =
                List.of(OreFeatureConfig.createTarget(stoneReplacables, ModBlocks.ADAMANTITE_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.DEEPSLATE_ADAMANTITE_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> netherHellstoneOres =
                List.of(OreFeatureConfig.createTarget(netherReplacables, ModBlocks.HELLSTONE_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> endCobaltOres =
                List.of(OreFeatureConfig.createTarget(endReplacables, ModBlocks.ADAMANTITE_ORE.getDefaultState()));

        register(context, COBALT_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldCobaltOres, 3));
        register(context, ORICHALCUM_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldOrichalcumOres, 3));
        register(context, ADAMANTITE_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldAdamantiteOres, 3));
        register(context, NETHER_HELLSTONE_ORE_KEY, Feature.ORE, new OreFeatureConfig(netherHellstoneOres, 7));
        register(context, END_COBALT_ORE_KEY, Feature.ORE, new OreFeatureConfig(endCobaltOres, 12));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(TerrariaMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
