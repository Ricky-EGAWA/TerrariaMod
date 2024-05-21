package ricky.terrariamod;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ricky.terrariamod.item.ModItemGroups;
import ricky.terrariamod.item.ModItems;

public class TerrariaMod implements ModInitializer {
	public static final String MOD_ID = "terrariamod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");

		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
	}
}