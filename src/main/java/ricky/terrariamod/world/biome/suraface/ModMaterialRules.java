package ricky.terrariamod.world.biome.suraface;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import ricky.terrariamod.block.ModBlocks;
import ricky.terrariamod.world.biome.ModBiomes;

public class ModMaterialRules {

    private static final MaterialRules.MaterialRule DIRT = makeStateRule(Blocks.DIRT);
    private static final MaterialRules.MaterialRule GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final MaterialRules.MaterialRule CRIM_STONE = makeStateRule(ModBlocks.CRIM_STONE);
    private static final MaterialRules.MaterialRule CRIME_ICE = makeStateRule(ModBlocks.CRIM_ICE);
    private static final MaterialRules.MaterialRule EBON_SAND = makeStateRule(ModBlocks.EBON_SAND);
    private static final MaterialRules.MaterialRule EBON_SANDSTONE = makeStateRule(ModBlocks.EBON_SANDSTONE);
    private static final MaterialRules.MaterialRule CRIM_SAND = makeStateRule(ModBlocks.CRIM_SAND);
    private static final MaterialRules.MaterialRule CRIM_SANDSTONE = makeStateRule(ModBlocks.CRIM_SANDSTONE);

    public static MaterialRules.MaterialRule makeCustomSurfaceRules() {
        // 地表部分に適用するための条件
        MaterialRules.MaterialCondition isSurface = MaterialRules.surface();
        // 表層 (surface) 3ブロックの設定
//        MaterialRules.MaterialCondition topLayer = MaterialRules.stoneDepth(0, false, VerticalSurfaceType.FLOOR);
//        MaterialRules.MaterialCondition secondLayer = MaterialRules.stoneDepth(1, false, VerticalSurfaceType.FLOOR);
//        MaterialRules.MaterialCondition thirdLayer = MaterialRules.stoneDepth(2, false, VerticalSurfaceType.FLOOR);

        // TEST_BIOME 用の条件
        MaterialRules.MaterialCondition testBiomeCondition = MaterialRules.biome(ModBiomes.TEST_BIOME);
        // EBON_BIOME 用の条件
        MaterialRules.MaterialCondition ebonBiomeCondition = MaterialRules.biome(ModBiomes.EBON_BIOME);

        // サブサーフェス (subsurface) 5ブロックの設定
//        MaterialRules.MaterialCondition subSurfaceLayer = MaterialRules.stoneDepth(3, true, VerticalSurfaceType.FLOOR);
//        MaterialRules.MaterialCondition subSurfaceLayer_up = MaterialRules.stoneDepth(3, true, VerticalSurfaceType.CEILING);


        // 表層のルール (3ブロックを EBON_SAND)
//        MaterialRules.MaterialRule surfaceRule = MaterialRules.sequence(
//                MaterialRules.condition(topLayer, CRIM_SAND),
//                MaterialRules.condition(secondLayer, CRIM_SAND),
//                MaterialRules.condition(thirdLayer, CRIM_SAND),
//                MaterialRules.condition(subSurfaceLayer, CRIM_SANDSTONE),
//                MaterialRules.condition(subSurfaceLayer_up, CRIM_SANDSTONE)
//        );
        MaterialRules.MaterialRule surfaceRule_crim = surfaceRuleMaker(CRIM_SAND, CRIM_SANDSTONE);
        MaterialRules.MaterialRule surfaceRule_ebon = surfaceRuleMaker(EBON_SAND, EBON_SANDSTONE);

        // TEST_BIOME の場合に CRIM_SANDSTONE を適用
        MaterialRules.MaterialRule testBiomeRule = MaterialRules.condition(
                testBiomeCondition,
                surfaceRule_crim
        );

        // EBON_BIOME の場合に EBON_SANDSTONE を適用
        MaterialRules.MaterialRule ebonBiomeRule = MaterialRules.condition(
                ebonBiomeCondition,
                surfaceRule_ebon
        );

        // ルールをシーケンスで組み合わせて返す
        return MaterialRules.sequence(
                testBiomeRule,
                ebonBiomeRule
        );
    }

    // MaterialRule の作成
    private static MaterialRules.MaterialRule makeStateRule(Block block) {
        return MaterialRules.block(block.getDefaultState());
    }
    private static MaterialRules.MaterialRule surfaceRuleMaker(MaterialRules.MaterialRule first, MaterialRules.MaterialRule second){
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
