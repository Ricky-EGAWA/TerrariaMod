package ricky.terrariamod.entity;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.biome.BiomeKeys;
import ricky.terrariamod.entity.custom.PiranhaEntity;

public class ModEntitySpawn {
    public static void addEntitySpawn() {
        // 砂漠でのSand Slimeのスポーン設定
        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(BiomeKeys.DESERT),
                SpawnGroup.MONSTER,
                ModEntities.SAND_SLIME,
                70, 2, 4 // ウェイト100、2〜4体
        );

        // 雪原でのIce Slimeのスポーン設定
        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(BiomeKeys.SNOWY_PLAINS),
                SpawnGroup.MONSTER,
                ModEntities.ICE_SLIME,
                70, 2, 4 // ウェイト100、2〜4体
        );

        // 繫茂した洞窟でのJungle Batのスポーン設定
        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(BiomeKeys.LUSH_CAVES),
                SpawnGroup.MONSTER,
                ModEntities.JUNGLE_BAT,
                60, 2, 3 // ウェイト80、1〜3体
        );

        // ネザーでのlava Batのスポーン設定
        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(BiomeKeys.NETHER_WASTES),
                SpawnGroup.MONSTER,
                ModEntities.LAVA_BAT,
                80, 2, 3 // ウェイト80、1〜3体
        );
//        // 繫茂した洞窟でのPiranhaの水中スポーン設定
//        BiomeModifications.addSpawn(
//                BiomeSelectors.includeByKey(BiomeKeys.LUSH_CAVES),
//                SpawnGroup.WATER_CREATURE,
//                ModEntities.PIRANHA,
//                100, 3, 3
//        );
    }
}
