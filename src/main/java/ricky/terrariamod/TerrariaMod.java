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
import ricky.terrariamod.effect.ModEffects;
import ricky.terrariamod.entity.ModEntities;
import ricky.terrariamod.entity.custom.*;
import ricky.terrariamod.event.ModEvents;
import ricky.terrariamod.item.ModItemGroups;
import ricky.terrariamod.item.ModItems;
import ricky.terrariamod.networking.ModNetworking;
import ricky.terrariamod.util.ModLootTableModifiers;
import ricky.terrariamod.entity.ModEntitySpawn;
import ricky.terrariamod.util.ModTrades;
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

		ModEffects.registerEffects();
		ModEvents.registerEvents();

		ModWorldGeneration.generateModWorldGen();

		// サーバー側でパケットの登録を行う
		ModNetworking.register();
		//tree
		StrippableBlockRegistry.register(ModBlocks.EBON_LOG, ModBlocks.STRIPPED_EBON_LOG);
		StrippableBlockRegistry.register(ModBlocks.EBON_WOOD, ModBlocks.STRIPPED_EBON_WOOD);
		StrippableBlockRegistry.register(ModBlocks.CRIM_LOG, ModBlocks.STRIPPED_CRIM_LOG);
		StrippableBlockRegistry.register(ModBlocks.CRIM_WOOD, ModBlocks.STRIPPED_CRIM_WOOD);
		StrippableBlockRegistry.register(ModBlocks.PEARL_LOG, ModBlocks.STRIPPED_PEARL_LOG);
		StrippableBlockRegistry.register(ModBlocks.PEARL_WOOD, ModBlocks.STRIPPED_PEARL_WOOD);
		//可燃ブロック //TODO 階段などを追加
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.EBON_LOG,5,5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.EBON_WOOD,5,5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_EBON_LOG,5,5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_EBON_WOOD,5,5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.EBON_PLANKS,5,5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.EBON_LEAVES,5,5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.CRIM_LOG,5,5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.CRIM_WOOD,5,5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_CRIM_LOG,5,5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_CRIM_WOOD,5,5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.CRIM_PLANKS,5,5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.CRIM_LEAVES,5,5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.PEARL_LOG,5,5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.PEARL_WOOD,5,5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_PEARL_LOG,5,5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_PEARL_WOOD,5,5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.PEARL_PLANKS,5,5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.PEARL_LEAVES,5,5);


		//エンティティの登録
		LOGGER.info("Registering attributes for entities");
		FabricDefaultAttributeRegistry.register(ModEntities.FROZENZOMBIE, FrozenZombieEntity.createFrozenZombieAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.PORCUPINE, PorcupineEntity.createPorcupineAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.ICE_SLIME, IceSlimeEntity.createIceSlimeAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.SAND_SLIME, SandSlimeEntity.createSandSlimeAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.CORRUPT_SLIME, CorruptSlimeEntity.createCorruptSlimeAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.CRIM_SLIME, CrimSlimeEntity.createCrimSlimeAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.JUNGLE_BAT, JungleBatEntity.createJungleBatAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.LAVA_BAT, LavaBatEntity.createLavaBatAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.PIRANHA, PiranhaEntity.createPiranhaAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.MUMMY, MummyEntity.createMummyAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.DARK_MUMMY, DarkMummyEntity.createMummyAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.BLOOD_MUMMY, BloodMummyEntity.createMummyAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.LIGHT_MUMMY, LightMummyEntity.createMummyAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.EATER_OF_SOUL, EaterOfSoulEntity.createEaterOfSoulAttributes());

		// エンティティのスポーンを追加
		ModEntitySpawn.addEntitySpawn();
		//交易
		ModTrades.registerCustomTrades();

		CustomPortalBuilder.beginPortal()
				.frameBlock(Blocks.END_STONE)
				.lightWithItem(ModItems.COBALT_INGOT)
				.destDimID(new Identifier(TerrariaMod.MOD_ID, "terraria"))
				.tintColor(0xc76efa)
				.registerPortal();
	}
}