package ricky.terrariamod.entity;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.BiomeKeys;

public class ModEntitySpawn {
    public static void addEntitySpawn() {
        // バイオームをIdentifierで指定してモブのスポーン設定を追加
        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(BiomeKeys.DESERT), // BiomeKeys.PLAINSでプレーンズバイオームを指定
                SpawnGroup.MONSTER,
                ModEntities.SAND_SLIME,
                100, 2, 4 // ウェイト100、2〜4体
        );
        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(BiomeKeys.SNOWY_PLAINS), // BiomeKeys.PLAINSでプレーンズバイオームを指定
                SpawnGroup.MONSTER,
                ModEntities.ICE_SLIME,
                100, 2, 4 // ウェイト100、2〜4体
        );
    }
}
