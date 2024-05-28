package ricky.terrariamod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import ricky.terrariamod.TerrariaMod;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import ricky.terrariamod.item.custom.MetalDetectorItem;
import ricky.terrariamod.item.custom.ModToolMaterial;

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

    public static final Item COBALT_PICKAXE = registerItem("cobalt_pickaxe",
            new PickaxeItem(ModToolMaterial.COBALT_INGOT, 2, 2f, new FabricItemSettings().maxCount(1)));
    public static final Item COBALT_AXE = registerItem("cobalt_axe",
            new AxeItem(ModToolMaterial.COBALT_INGOT, 2, 2f, new FabricItemSettings().maxCount(1)));
    public static final Item COBALT_SHOVEL = registerItem("cobalt_shovel",
            new ShovelItem(ModToolMaterial.COBALT_INGOT, 2, 2f, new FabricItemSettings().maxCount(1)));
    public static final Item COBALT_SWORD = registerItem("cobalt_sword",
            new SwordItem(ModToolMaterial.COBALT_INGOT, 2, 2f, new FabricItemSettings().maxCount(1)));
    public static final Item COBALT_DRILL = registerItem("cobalt_drill",
            new PickaxeItem(ModToolMaterial.COBALT_INGOT, 2, 2f, new FabricItemSettings().maxCount(1)));

    public static final Item COBALT_HELMET = registerItem("cobalt_helmet",
            new ArmorItem(ModArmorMaterials.COBALT, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item COBALT_CHESTPLATE = registerItem("cobalt_chestplate",
            new ArmorItem(ModArmorMaterials.COBALT, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item COBALT_LEGGINGS = registerItem("cobalt_leggings",
            new ArmorItem(ModArmorMaterials.COBALT, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item COBALT_BOOTS = registerItem("cobalt_boots",
            new ArmorItem(ModArmorMaterials.COBALT, ArmorItem.Type.BOOTS, new FabricItemSettings()));

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
