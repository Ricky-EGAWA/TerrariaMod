package ricky.terrariamod.world;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MushroomBlock;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.placementmodifier.BlockFilterPlacementModifier;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import ricky.terrariamod.TerrariaMod;
import ricky.terrariamod.block.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> COBALT_ORE_KEY = registerKey("cobalt_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORICHALCUM_ORE_KEY = registerKey("orichalcum_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ADAMANTITE_ORE_KEY = registerKey("adamantite_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> NETHER_HELLSTONE_ORE_KEY = registerKey("hellstone_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> END_COBALT_ORE_KEY = registerKey("end_temp_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> EBON_TREE_KEY = (registerKey("ebon_tree"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> CRIM_TREE_KEY = (registerKey("crim_tree"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> PEARL_TREE_KEY = (registerKey("pearl_tree"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> DEATH_WEED_KEY = (registerKey("death_weed"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> SHIVER_THORN = (registerKey("shiver_thorn"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> GLOWING_MUSHROOM = (registerKey("glowing_mushroom"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> HUGE_GLOWING_MUSHROOM = registerKey("huge_glowing_mushroom");
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

        register(context, EBON_TREE_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlocks.EBON_LOG),
                //幹　基本的な高さ　ランダムな追加高さ　最大高さ
                new StraightTrunkPlacer(5,4,3),
                BlockStateProvider.of(ModBlocks.EBON_LEAVES),
                //葉の半径　相互との高さ　最大高さ
                new BlobFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(2),3),
                //地面近くの葉の層の高さ  葉が生成されない高さのオフセット  幹の上部の葉の層の高さ。
                new TwoLayersFeatureSize(2,0,2)).build());
        register(context, CRIM_TREE_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlocks.CRIM_LOG),
                //幹　基本的な高さ　ランダムな追加高さ　最大高さ
                new StraightTrunkPlacer(5,4,3),
                BlockStateProvider.of(ModBlocks.CRIM_LEAVES),
                //葉の半径　相互との高さ　最大高さ
                new BlobFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(2),3),
                //地面近くの葉の層の高さ  葉が生成されない高さのオフセット  幹の上部の葉の層の高さ。
                new TwoLayersFeatureSize(2,0,2)).build());
        register(context, PEARL_TREE_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlocks.PEARL_LOG),
                //幹　基本的な高さ　ランダムな追加高さ　最大高さ
                new StraightTrunkPlacer(5,4,3),
                BlockStateProvider.of(ModBlocks.PEARL_LEAVES),
                //葉の半径　相互との高さ　最大高さ
                new BlobFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(2),3),
                //地面近くの葉の層の高さ  葉が生成されない高さのオフセット  幹の上部の葉の層の高さ。
                new TwoLayersFeatureSize(2,0,2)).build());

        BlockStateProvider flowerProvider = BlockStateProvider.of(ModBlocks.DEATH_WEED.getDefaultState());
        SimpleBlockFeatureConfig flowerConfig = new SimpleBlockFeatureConfig(flowerProvider);

        ConfiguredFeature<SimpleBlockFeatureConfig, ?> flowerConfiguredFeature = new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, flowerConfig);
        PlacedFeature flowerPlacedFeature = new PlacedFeature(RegistryEntry.of(flowerConfiguredFeature), List.of(BlockFilterPlacementModifier.of(BlockPredicate.matchingBlocks(Blocks.AIR))));

        BlockStateProvider shiverProvider = BlockStateProvider.of(ModBlocks.SHIVER_THORN.getDefaultState());
        SimpleBlockFeatureConfig shiverConfig = new SimpleBlockFeatureConfig(shiverProvider);

        ConfiguredFeature<SimpleBlockFeatureConfig, ?> shiverConfiguredFeature = new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, shiverConfig);
        PlacedFeature shiverPlacedFeature = new PlacedFeature(RegistryEntry.of(shiverConfiguredFeature), List.of(BlockFilterPlacementModifier.of(BlockPredicate.matchingBlocks(Blocks.AIR))));

        BlockStateProvider gMushroomProvider = BlockStateProvider.of(ModBlocks.GLOWING_MUSHROOM.getDefaultState());
        SimpleBlockFeatureConfig gMushroomConfig = new SimpleBlockFeatureConfig(gMushroomProvider);

        ConfiguredFeature<SimpleBlockFeatureConfig, ?> gMushroomConfiguredFeature = new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, gMushroomConfig);
        PlacedFeature gMushroomPlacedFeature = new PlacedFeature(RegistryEntry.of(gMushroomConfiguredFeature), List.of(BlockFilterPlacementModifier.of(BlockPredicate.matchingBlocks(Blocks.AIR))));
        //試行回数　xzの広がり　ｙの広がり
        register(context, DEATH_WEED_KEY, Feature.FLOWER, new RandomPatchFeatureConfig(32,10,3, RegistryEntry.of(flowerPlacedFeature)));
        register(context, SHIVER_THORN, Feature.FLOWER, new RandomPatchFeatureConfig(32,10,3, RegistryEntry.of(shiverPlacedFeature)));
        register(context, GLOWING_MUSHROOM, Feature.RANDOM_PATCH, new RandomPatchFeatureConfig(32,10,3, RegistryEntry.of(gMushroomPlacedFeature)));
        register(context, HUGE_GLOWING_MUSHROOM, Feature.HUGE_RED_MUSHROOM, new HugeMushroomFeatureConfig(BlockStateProvider.of((BlockState)ModBlocks.GLOWING_MUSHROOM_BLOCK.getDefaultState().with(MushroomBlock.DOWN, false)), BlockStateProvider.of((BlockState)((BlockState)ModBlocks.GLOWING_MUSHROOM_STEM.getDefaultState().with(MushroomBlock.UP, false)).with(MushroomBlock.DOWN, false)), 2));

    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(TerrariaMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
