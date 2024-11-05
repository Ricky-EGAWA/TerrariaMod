package ricky.terrariamod.entity;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.BiomeKeys;

public class ModEntitySpawn {
    public static void addEntitySpawn() {
        // 砂漠でのSand Slimeのスポーン設定
        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(BiomeKeys.DESERT),
                SpawnGroup.MONSTER,
                ModEntities.SAND_SLIME,
                100, 2, 4 // ウェイト100、2〜4体
        );

        // 雪原でのIce Slimeのスポーン設定
        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(BiomeKeys.SNOWY_PLAINS),
                SpawnGroup.MONSTER,
                ModEntities.ICE_SLIME,
                100, 2, 4 // ウェイト100、2〜4体
        );

        // 繫茂した洞窟でのJungle Batのスポーン設定
        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(BiomeKeys.LUSH_CAVES),
                SpawnGroup.MONSTER,
                ModEntities.JUNGLE_BAT,
                80, 1, 3 // ウェイト80、1〜3体
        );
    }
}
