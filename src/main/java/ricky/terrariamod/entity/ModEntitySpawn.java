package ricky.terrariamod.entity;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.BiomeKeys;
import ricky.terrariamod.world.biome.ModBiomes;

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
        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(ModBiomes.EBON_BIOME),
                SpawnGroup.MONSTER,
                ModEntities.CORRUPT_SLIME,
                100,1,2
        );
        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(ModBiomes.CRIM_BIOME),
                SpawnGroup.MONSTER,
                ModEntities.CRIM_SLIME,
                100,1,2
        );

        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(ModBiomes.EBON_DESERT_BIOME),
                SpawnGroup.MONSTER,
                ModEntities.DARK_MUMMY,
                100,1,2
        );
        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(ModBiomes.CRIM_DESERT_BIOME),
                SpawnGroup.MONSTER,
                ModEntities.BLOOD_MUMMY,
                100,1,2
        );
        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(ModBiomes.PEARL_DESERT_BIOME),
                SpawnGroup.MONSTER,
                ModEntities.LIGHT_MUMMY,
                100,1,2
        );
    }
}
