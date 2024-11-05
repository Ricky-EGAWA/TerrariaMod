package ricky.terrariamod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ricky.terrariamod.block.ModBlocks;
import ricky.terrariamod.entity.ModEntities;
import ricky.terrariamod.entity.custom.*;
import ricky.terrariamod.item.ModItemGroups;
import ricky.terrariamod.item.ModItems;
import ricky.terrariamod.util.ModLootTableModifiers;
import ricky.terrariamod.entity.ModEntitySpawn;
import ricky.terrariamod.world.gen.ModWorldGeneration;

public class TerrariaMod implements ModInitializer {
	public static final String MOD_ID = "terrariamod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");

		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModLootTableModifiers.modifyLootTables();

		ModWorldGeneration.generateModWorldGen();

		//エンティティの登録
		LOGGER.info("Registering attributes for entities");
		FabricDefaultAttributeRegistry.register(ModEntities.FROZENZOMBIE, FrozenZombieEntity.createFrozenZombieAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.PORCUPINE, PorcupineEntity.createPorcupineAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.ICE_SLIME, IceSlimeEntity.createIceSlimeAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.SAND_SLIME, SandSlimeEntity.createSandSlimeAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.JUNGLE_BAT, JungleBatEntity.createJungleBatAttributes());

		// エンティティのスポーンを追加
		ModEntitySpawn.addEntitySpawn();
	}
}