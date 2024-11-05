package ricky.terrariamod.entity;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.BiomeKeys;
import ricky.terrariamod.entity.ModEntities;

public class ModEntitySpawn {
    public static void addEntitySpawn() {
        // PlainsバイオームをIdentifierで指定してモブのスポーン設定を追加
        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(BiomeKeys.PLAINS), // BiomeKeys.PLAINSでプレーンズバイオームを指定
                SpawnGroup.MONSTER,
                ModEntities.SAND_SLIME,
                100, 2, 4 // ウェイト100、2〜4体
        );
    }
}
