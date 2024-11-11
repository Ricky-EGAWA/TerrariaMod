package ricky.terrariamod.world.biome.suraface;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
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

    // TEST_BIOME 用のルール
    public static MaterialRules.MaterialRule makeTestBiomeRules() {
        // 水面レベル以上かどうかの条件
        MaterialRules.MaterialCondition isAtOrAboveWaterLevel = MaterialRules.water(10, 0);

        // TEST_BIOME の表層設定
        MaterialRules.MaterialRule grassSurface = MaterialRules.sequence(
                MaterialRules.condition(isAtOrAboveWaterLevel, GRASS_BLOCK),
                DIRT
        );

        // TEST_BIOME のルールを作成
        return MaterialRules.sequence(
                MaterialRules.condition(
                        MaterialRules.biome(ModBiomes.TEST_BIOME), // TEST_BIOME に適用
                        MaterialRules.sequence(
                                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, CRIME_ICE), // 底の設定
                                MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, CRIM_STONE) // 天井の設定
                        )
                ),
                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, grassSurface) // 表層を設定
        );
    }

    // EBON_BIOME 用のルール
    public static MaterialRules.MaterialRule makeEbonBiomeRules() {
        // 水面レベル以上かどうかの条件
        MaterialRules.MaterialCondition isAtOrAboveWaterLevel = MaterialRules.water(10, 0);

        // EBON_BIOME の表層設定
        MaterialRules.MaterialRule ebonSurface = MaterialRules.sequence(
                MaterialRules.condition(isAtOrAboveWaterLevel, EBON_SANDSTONE),
                EBON_SAND
        );

        // EBON_BIOME のルールを作成
        return MaterialRules.sequence(
                MaterialRules.condition(
                        MaterialRules.biome(ModBiomes.EBON_BIOME), // EBON_BIOME に適用
                        MaterialRules.sequence(
                                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, EBON_SANDSTONE), // 底の設定
                                MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, EBON_SANDSTONE) // 天井の設定
                        )
                ),
                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, ebonSurface) // 表層を設定
        );
    }

    // MaterialRule の作成
    private static MaterialRules.MaterialRule makeStateRule(Block block) {
        return MaterialRules.block(block.getDefaultState());
    }
}
