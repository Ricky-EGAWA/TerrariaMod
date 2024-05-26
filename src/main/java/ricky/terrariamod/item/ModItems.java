package ricky.terrariamod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import ricky.terrariamod.TerrariaMod;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import ricky.terrariamod.item.custom.MetalDetectorItem;

public class ModItems {
    public static final Item MetalDetectorItem = registerItem("metal_detector",
            new MetalDetectorItem(new FabricItemSettings().maxDamage(64)));

    public static final Item SPIDER_FANG = registerItem("spider_fang", new Item(new FabricItemSettings()));
    public static final Item COBALT_INGOT = registerItem("cobalt_ingot", new Item(new FabricItemSettings()));
    public static final Item ORICHALCUM_INGOT = registerItem("orichalcum_ingot", new Item(new FabricItemSettings()));
    public static final Item ADAMANTITE_INGOT = registerItem("adamantite_ingot", new Item(new FabricItemSettings()));
    public static final Item HELLSTONE_INGOT = registerItem("hellstone_ingot", new Item(new FabricItemSettings()));
    public static final Item COBALT_RAW = registerItem("cobalt_raw", new Item(new FabricItemSettings()));
    public static final Item ORICHALCUM_RAW = registerItem("orichalcum_raw", new Item(new FabricItemSettings()));
    public static final Item ADAMANTITE_RAW = registerItem("adamantite_raw", new Item(new FabricItemSettings()));
    public static final Item HELLSTONE_RAW = registerItem("hellstone_raw", new Item(new FabricItemSettings()));
    private static void addItemsToIngredientTabItemGroup(FabricItemGroupEntries entries){
        entries.add(SPIDER_FANG);
    }

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(TerrariaMod.MOD_ID,name),item);
    }
    public static void registerModItems(){
        TerrariaMod.LOGGER.info("Regisytering Mod Items for " + TerrariaMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientTabItemGroup);
    }
}
