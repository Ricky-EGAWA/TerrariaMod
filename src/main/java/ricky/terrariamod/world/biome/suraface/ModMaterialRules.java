package ricky.terrariamod.world.biome.suraface;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import ricky.terrariamod.block.ModBlocks;
import ricky.terrariamod.world.biome.ModBiomes;

public class ModMaterialRules {
    private static final MaterialRules.MaterialRule SNOW = makeStateRule(Blocks.SNOW);
    private static final MaterialRules.MaterialRule GRAVEL =makeStateRule(Blocks.GRAVEL);
    private static final MaterialRules.MaterialRule GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final MaterialRules.MaterialRule DIRT = makeStateRule(Blocks.DIRT);
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
    private static final MaterialRules.MaterialRule GLOWING_MOSS = makeStateRule(ModBlocks.GLOWING_MOSS);

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
        //地下
        MaterialRules.MaterialCondition glowingMushroomBiomeCondition = MaterialRules.biome(ModBiomes.GLOWING_MUSHROOM_BIOME);

        //第一ブロック、 表層（一層目　二層目）、　ベース、　水の下、　洞窟（天井　床）
        MaterialRules.MaterialRule surfaceRule_crim_desert = surfaceRuleMaker(CRIM_SAND, CRIM_SAND, CRIM_SANDSTONE, ModBlocks.CRIM_STONE.getDefaultState(), CRIM_SAND, CRIM_STONE, CRIM_STONE);
        MaterialRules.MaterialRule surfaceRule_ebon_desert = surfaceRuleMaker(EBON_SAND, EBON_SAND, EBON_SANDSTONE, ModBlocks.EBON_STONE.getDefaultState(), EBON_SAND, EBON_STONE, EBON_STONE);
        MaterialRules.MaterialRule surfaceRule_pearl_desert = surfaceRuleMaker(PEARL_SAND, PEARL_SAND, PEARL_SANDSTONE, ModBlocks.PEARL_STONE.getDefaultState(), PEARL_SAND, PEARL_STONE, PEARL_STONE);
        MaterialRules.MaterialRule surfaceRule_crim = surfaceRuleMaker(GRASS_BLOCK, DIRT, CRIM_STONE, ModBlocks.CRIM_STONE.getDefaultState(), GRAVEL, CRIM_STONE, CRIM_STONE);
        MaterialRules.MaterialRule surfaceRule_ebon = surfaceRuleMaker(GRASS_BLOCK, DIRT, EBON_STONE, ModBlocks.EBON_STONE.getDefaultState(), GRAVEL, EBON_STONE, EBON_STONE);
        MaterialRules.MaterialRule surfaceRule_pearl = surfaceRuleMaker(GRASS_BLOCK, DIRT, PEARL_STONE, ModBlocks.PEARL_STONE.getDefaultState(), GRAVEL, PEARL_STONE, PEARL_STONE);
        MaterialRules.MaterialRule surfaceRule_crim_ice = surfaceRuleMaker(SNOW, CRIM_ICE, CRIM_ICE, ModBlocks.CRIM_ICE.getDefaultState(), CRIM_ICE, CRIM_STONE, CRIM_STONE);
        MaterialRules.MaterialRule surfaceRule_ebon_ice = surfaceRuleMaker(SNOW, EBON_ICE, EBON_ICE, ModBlocks.EBON_ICE.getDefaultState(), EBON_ICE, EBON_STONE, EBON_STONE);
        MaterialRules.MaterialRule surfaceRule_pearl_ice = surfaceRuleMaker(SNOW, PEARL_ICE, PEARL_ICE, ModBlocks.PEARL_ICE.getDefaultState(), PEARL_STONE, EBON_STONE, PEARL_STONE);
        //地下
        MaterialRules.MaterialRule surfaceRule_glowing_mushroom = surfaceRuleMaker(GLOWING_MOSS,GLOWING_MOSS,GLOWING_MOSS, ModBlocks.GLOWING_MOSS.getDefaultState(), GRAVEL, GLOWING_MOSS, GLOWING_MOSS);

        MaterialRules.MaterialRule crimDesertBiomeRule = MaterialRules.condition(crimDesertBiomeCondition, surfaceRule_crim_desert);
        MaterialRules.MaterialRule ebonDesertBiomeRule = MaterialRules.condition(ebonDesertBiomeCondition, surfaceRule_ebon_desert);
        MaterialRules.MaterialRule pearlDesertBiomeRule = MaterialRules.condition(pearlDesertBiomeCondition, surfaceRule_pearl_desert);
        MaterialRules.MaterialRule crimBiomeRule = MaterialRules.condition(crimBiomeCondition, surfaceRule_crim);
        MaterialRules.MaterialRule ebonBiomeRule = MaterialRules.condition(ebonBiomeCondition, surfaceRule_ebon);
        MaterialRules.MaterialRule pearlBiomeRule = MaterialRules.condition(pearlBiomeCondition, surfaceRule_pearl);
        MaterialRules.MaterialRule crimIceBiomeRule = MaterialRules.condition(crimIceBiomeCondition, surfaceRule_crim_ice);
        MaterialRules.MaterialRule ebonIceBiomeRule = MaterialRules.condition(ebonIceBiomeCondition, surfaceRule_ebon_ice);
        MaterialRules.MaterialRule pearlIceBiomeRule = MaterialRules.condition(pearlIceBiomeCondition, surfaceRule_pearl_ice);
        MaterialRules.MaterialRule glowingMushroomRule = MaterialRules.condition(glowingMushroomBiomeCondition, surfaceRule_glowing_mushroom);

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
                pearlIceBiomeRule,
                glowingMushroomRule
        );
    }

    // MaterialRule の作成
    private static MaterialRules.MaterialRule makeStateRule(Block block) {
        return MaterialRules.block(block.getDefaultState());
    }
    //表層（一層目　二層目）、　ベース、　水の下、　洞窟（天井　床）
    private static MaterialRules.MaterialRule surfaceRuleMaker(MaterialRules.MaterialRule surface,MaterialRules.MaterialRule first, MaterialRules.MaterialRule second, BlockState base, MaterialRules.MaterialRule underwater, MaterialRules.MaterialRule ceiling, MaterialRules.MaterialRule floor){
        // 地表部分に適用するための条件
        MaterialRules.MaterialCondition isSurface = MaterialRules.surface();
        MaterialRules.MaterialCondition subSurfaceLayer_floor = MaterialRules.stoneDepth(3, true, VerticalSurfaceType.FLOOR);
        MaterialRules.MaterialCondition subSurfaceLayer_ceiling = MaterialRules.stoneDepth(3, true, VerticalSurfaceType.CEILING);
        return MaterialRules.sequence(
                MaterialRules.condition(isSurface, surfaceRule(first,second,surface,underwater)),
                MaterialRules.block(base),
                MaterialRules.condition(subSurfaceLayer_floor, floor),
                MaterialRules.condition(subSurfaceLayer_ceiling, ceiling)
        );
    }
    private static MaterialRules.MaterialRule surfaceRule(MaterialRules.MaterialRule first, MaterialRules.MaterialRule second, MaterialRules.MaterialRule surface,MaterialRules.MaterialRule underwater){
        MaterialRules.MaterialCondition topLayer = MaterialRules.stoneDepth(2, true, VerticalSurfaceType.FLOOR);
        MaterialRules.MaterialCondition subSurfaceLayer = MaterialRules.stoneDepth(3, true, VerticalSurfaceType.FLOOR);
        MaterialRules.MaterialCondition isAtOrAboveWaterLevel = MaterialRules.water(-1, 0);
        MaterialRules.MaterialRule waterSurface = MaterialRules.sequence(MaterialRules.condition(isAtOrAboveWaterLevel, surface), underwater);
        return MaterialRules.sequence(
                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, waterSurface),
                MaterialRules.condition(topLayer,first),
                MaterialRules.condition(subSurfaceLayer,second)
        );
    }
}
