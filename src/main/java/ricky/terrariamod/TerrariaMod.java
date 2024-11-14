package ricky.terrariamod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
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
		//tree
		StrippableBlockRegistry.register(ModBlocks.EBON_LOG, ModBlocks.STRIPPED_EBON_LOG);
		StrippableBlockRegistry.register(ModBlocks.EBON_WOOD, ModBlocks.STRIPPED_EBON_WOOD);
		//可燃ブロック
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.EBON_LOG,5,5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.EBON_WOOD,5,5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_EBON_LOG,5,5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_EBON_WOOD,5,5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.EBON_PLANKS,5,5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.EBON_LEAVES,5,5);


		//エンティティの登録
		LOGGER.info("Registering attributes for entities");
		FabricDefaultAttributeRegistry.register(ModEntities.FROZENZOMBIE, FrozenZombieEntity.createFrozenZombieAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.PORCUPINE, PorcupineEntity.createPorcupineAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.ICE_SLIME, IceSlimeEntity.createIceSlimeAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.SAND_SLIME, SandSlimeEntity.createSandSlimeAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.JUNGLE_BAT, JungleBatEntity.createJungleBatAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.LAVA_BAT, LavaBatEntity.createLavaBatAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.PIRANHA, PiranhaEntity.createPiranhaAttributes());

		// エンティティのスポーンを追加
		ModEntitySpawn.addEntitySpawn();

		CustomPortalBuilder.beginPortal()
				.frameBlock(Blocks.END_STONE)
				.lightWithItem(ModItems.COBALT_INGOT)
				.destDimID(new Identifier(TerrariaMod.MOD_ID, "terraria"))
				.tintColor(0xc76efa)
				.registerPortal();
	}
}