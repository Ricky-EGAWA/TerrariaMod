package ricky.terrariamod.world.biome.suraface;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import ricky.terrariamod.block.ModBlocks;
import ricky.terrariamod.world.biome.ModBiomes;

public class ModMaterialRules {
    private static final MaterialRules.MaterialRule SNOW = makeStateRule(Blocks.SNOW_BLOCK);
    private static final MaterialRules.MaterialRule GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final MaterialRules.MaterialRule EBON_SAND = makeStateRule(ModBlocks.EBON_SAND);
    private static final MaterialRules.MaterialRule EBON_SANDSTONE = makeStateRule(ModBlocks.EBON_SANDSTONE);
    private static final MaterialRules.MaterialRule EBON_STONE = makeStateRule(ModBlocks.EBON_STONE);
    private static final MaterialRules.MaterialRule EBON_ICE = makeStateRule(ModBlocks.EBON_ICE);
    private static final MaterialRules.MaterialRule CRIM_SAND = makeStateRule(ModBlocks.CRIM_SAND);
    private static final MaterialRules.MaterialRule CRIM_SANDSTONE = makeStateRule(ModBlocks.CRIM_SANDSTONE);
    private static final MaterialRules.MaterialRule CRIM_STONE = makeStateRule(ModBlocks.CRIM_STONE);
    private static final MaterialRules.MaterialRule CRIM_ICE = makeStateRule(ModBlocks.CRIM_ICE);
    private static final MaterialRules.MaterialRule PEARL_SAND = makeStateRule(ModBlocks.PEARL_SAND);
    private static final MaterialRules.MaterialRule PEARL_SANDSTONE = makeStateRule(ModBlocks.PEARL_SANDSTONE);
    private static final MaterialRules.MaterialRule PEARL_STONE = makeStateRule(ModBlocks.PEARL_STONE);
    private static final MaterialRules.MaterialRule PEARL_ICE = makeStateRule(ModBlocks.PEARL_ICE);

    public static MaterialRules.MaterialRule makeCustomSurfaceRules() {
        //biome判定
        MaterialRules.MaterialCondition crimDesertBiomeCondition = MaterialRules.biome(ModBiomes.CRIM_DESERT_BIOME);
        MaterialRules.MaterialCondition ebonDesertBiomeCondition = MaterialRules.biome(ModBiomes.EBON_DESERT_BIOME);
        MaterialRules.MaterialCondition pearlDesertBiomeCondition = MaterialRules.biome(ModBiomes.PEARL_DESERT_BIOME);
        MaterialRules.MaterialCondition crimBiomeCondition = MaterialRules.biome(ModBiomes.CRIM_BIOME);
        MaterialRules.MaterialCondition ebonBiomeCondition = MaterialRules.biome(ModBiomes.EBON_BIOME);
        MaterialRules.MaterialCondition pearlBiomeCondition = MaterialRules.biome(ModBiomes.PEARL_BIOME);
        MaterialRules.MaterialCondition crimIceBiomeCondition = MaterialRules.biome(ModBiomes.CRIM_ICE_BIOME);
        MaterialRules.MaterialCondition ebonIceBiomeCondition = MaterialRules.biome(ModBiomes.EBON_ICE_BIOME);
        MaterialRules.MaterialCondition pearlIceBiomeCondition = MaterialRules.biome(ModBiomes.PEARL_ICE_BIOME);

        MaterialRules.MaterialRule surfaceRule_crim_desert = surfaceRuleMaker(CRIM_SAND, CRIM_SANDSTONE);
        MaterialRules.MaterialRule surfaceRule_ebon_desert = surfaceRuleMaker(EBON_SAND, EBON_SANDSTONE);
        MaterialRules.MaterialRule surfaceRule_pearl_desert = surfaceRuleMaker(PEARL_SAND, PEARL_SANDSTONE);
        MaterialRules.MaterialRule surfaceRule_crim = surfaceRuleMaker(GRASS_BLOCK, CRIM_STONE);
        MaterialRules.MaterialRule surfaceRule_ebon = surfaceRuleMaker(GRASS_BLOCK, EBON_STONE);
        MaterialRules.MaterialRule surfaceRule_pearl = surfaceRuleMaker(GRASS_BLOCK, PEARL_STONE);
        MaterialRules.MaterialRule surfaceRule_crim_ice = surfaceRuleMaker(SNOW, CRIM_ICE);
        MaterialRules.MaterialRule surfaceRule_ebon_ice = surfaceRuleMaker(SNOW, EBON_ICE);
        MaterialRules.MaterialRule surfaceRule_pearl_ice = surfaceRuleMaker(SNOW, PEARL_ICE);

        MaterialRules.MaterialRule crimDesertBiomeRule = MaterialRules.condition(crimDesertBiomeCondition, surfaceRule_crim_desert);
        MaterialRules.MaterialRule ebonDesertBiomeRule = MaterialRules.condition(ebonDesertBiomeCondition, surfaceRule_ebon_desert);
        MaterialRules.MaterialRule pearlDesertBiomeRule = MaterialRules.condition(pearlDesertBiomeCondition, surfaceRule_pearl_desert);
        MaterialRules.MaterialRule crimBiomeRule = MaterialRules.condition(crimBiomeCondition, surfaceRule_crim);
        MaterialRules.MaterialRule ebonBiomeRule = MaterialRules.condition(ebonBiomeCondition, surfaceRule_ebon);
        MaterialRules.MaterialRule pearlBiomeRule = MaterialRules.condition(pearlBiomeCondition, surfaceRule_pearl);
        MaterialRules.MaterialRule crimIceBiomeRule = MaterialRules.condition(crimIceBiomeCondition, surfaceRule_crim_ice);
        MaterialRules.MaterialRule ebonIceBiomeRule = MaterialRules.condition(ebonIceBiomeCondition, surfaceRule_ebon_ice);
        MaterialRules.MaterialRule pearlIceBiomeRule = MaterialRules.condition(pearlIceBiomeCondition, surfaceRule_pearl_ice);

        // ルールをシーケンスで組み合わせて返す
        return MaterialRules.sequence(
                crimDesertBiomeRule,
                ebonDesertBiomeRule,
                pearlDesertBiomeRule,
                crimBiomeRule,
                ebonBiomeRule,
                pearlBiomeRule,
                crimIceBiomeRule,
                ebonIceBiomeRule,
                pearlIceBiomeRule
        );
    }

    // MaterialRule の作成
    private static MaterialRules.MaterialRule makeStateRule(Block block) {
        return MaterialRules.block(block.getDefaultState());
    }
    private static MaterialRules.MaterialRule surfaceRuleMaker(MaterialRules.MaterialRule first, MaterialRules.MaterialRule second){
        // 地表部分に適用するための条件
        MaterialRules.MaterialCondition isSurface = MaterialRules.surface();

        MaterialRules.MaterialCondition topLayer = MaterialRules.stoneDepth(0, false, VerticalSurfaceType.FLOOR);
        MaterialRules.MaterialCondition secondLayer = MaterialRules.stoneDepth(1, false, VerticalSurfaceType.FLOOR);
        MaterialRules.MaterialCondition thirdLayer = MaterialRules.stoneDepth(2, false, VerticalSurfaceType.FLOOR);
        MaterialRules.MaterialCondition subSurfaceLayer = MaterialRules.stoneDepth(3, true, VerticalSurfaceType.FLOOR);
        MaterialRules.MaterialCondition subSurfaceLayer_up = MaterialRules.stoneDepth(3, true, VerticalSurfaceType.CEILING);
        return MaterialRules.sequence(
                MaterialRules.condition(topLayer, first),
                MaterialRules.condition(secondLayer, first),
                MaterialRules.condition(thirdLayer, first),
                MaterialRules.condition(subSurfaceLayer, second),
                MaterialRules.condition(subSurfaceLayer_up, second)
        );
    }
}
