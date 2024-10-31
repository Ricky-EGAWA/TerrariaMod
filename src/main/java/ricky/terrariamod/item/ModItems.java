package ricky.terrariamod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import ricky.terrariamod.TerrariaMod;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import ricky.terrariamod.item.custom.*;

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
//    public static final Item COBALT_DRILL = registerItem("cobalt_drill",
//            new PickaxeItem(ModToolMaterial.COBALT_INGOT, 2, 2f, new FabricItemSettings().maxCount(1)));

    public static final Item ORICHALCUM_PICKAXE = registerItem("orichalcum_pickaxe",
            new PickaxeItem(ModToolMaterial.ORICHALCUM_INGOT, 2, 2f, new FabricItemSettings().maxCount(1)));
    public static final Item ORICHALCUM_AXE = registerItem("orichalcum_axe",
            new AxeItem(ModToolMaterial.ORICHALCUM_INGOT, 2, 2f, new FabricItemSettings().maxCount(1)));
    public static final Item ORICHALCUM_SHOVEL = registerItem("orichalcum_shovel",
            new ShovelItem(ModToolMaterial.ORICHALCUM_INGOT, 2, 2f, new FabricItemSettings().maxCount(1)));
    public static final Item ORICHALCUM_SWORD = registerItem("orichalcum_sword",
            new SwordItem(ModToolMaterial.ORICHALCUM_INGOT, 2, 2f, new FabricItemSettings().maxCount(1)));
//    public static final Item ORICHALCUM_DRILL = registerItem("orichalcum_drill",
//            new PickaxeItem(ModToolMaterial.ORICHALCUM_INGOT, 2, 2f, new FabricItemSettings().maxCount(1)));

    public static final Item ADAMANTITE_PICKAXE = registerItem("adamantite_pickaxe",
            new PickaxeItem(ModToolMaterial.ADAMANTITE_INGOT, 2, 2f, new FabricItemSettings().maxCount(1)));
    public static final Item ADAMANTITE_AXE = registerItem("adamantite_axe",
            new AxeItem(ModToolMaterial.ADAMANTITE_INGOT, 2, 2f, new FabricItemSettings().maxCount(1)));
    public static final Item ADAMANTITE_SHOVEL = registerItem("adamantite_shovel",
            new ShovelItem(ModToolMaterial.ADAMANTITE_INGOT, 2, 2f, new FabricItemSettings().maxCount(1)));
    public static final Item ADAMANTITE_SWORD = registerItem("adamantite_sword",
            new SwordItem(ModToolMaterial.ADAMANTITE_INGOT, 2, 2f, new FabricItemSettings().maxCount(1)));
//    public static final Item ADAMANTITE_DRILL = registerItem("adamantite_drill",
//            new PickaxeItem(ModToolMaterial.ADAMANTITE_INGOT, 2, 2f, new FabricItemSettings().maxCount(1)));

    public static final Item HELLSTONE_PICKAXE = registerItem("hellstone_pickaxe",
            new PickaxeItem(ModToolMaterial.HELLSTONE_INGOT, 2, 2f, new FabricItemSettings().maxCount(1)));
    public static final Item HELLSTONE_AXE = registerItem("hellstone_axe",
            new AxeItem(ModToolMaterial.HELLSTONE_INGOT, 2, 2f, new FabricItemSettings().maxCount(1)));
    public static final Item HELLSTONE_SHOVEL = registerItem("hellstone_shovel",
            new ShovelItem(ModToolMaterial.HELLSTONE_INGOT, 2, 2f, new FabricItemSettings().maxCount(1)));
    public static final Item HELLSTONE_SWORD = registerItem("hellstone_sword",
            new SwordItem(ModToolMaterial.HELLSTONE_INGOT, 2, 2f, new FabricItemSettings().maxCount(1)));
//    public static final Item HELLSTONE_DRILL = registerItem("hellstone_drill",
//            new PickaxeItem(ModToolMaterial.HELLSTONE_INGOT, 2, 2f, new FabricItemSettings().maxCount(1)));

    public static final Item CACTUS_HELMET = registerItem("cactus_helmet",
            new ModArmorItem(ModArmorMaterials.CACTUS, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item CACTUS_CHESTPLATE = registerItem("cactus_chestplate",
            new ArmorItem(ModArmorMaterials.CACTUS, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item CACTUS_LEGGINGS = registerItem("cactus_leggings",
            new ArmorItem(ModArmorMaterials.CACTUS, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item CACTUS_BOOTS = registerItem("cactus_boots",
            new ArmorItem(ModArmorMaterials.CACTUS, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    public static final Item PUMPKIN_HELMET = registerItem("pumpkin_helmet",
            new ModArmorItem(ModArmorMaterials.PUMPKIN, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item PUMPKIN_CHESTPLATE = registerItem("pumpkin_chestplate",
            new ArmorItem(ModArmorMaterials.PUMPKIN, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item PUMPKIN_LEGGINGS = registerItem("pumpkin_leggings",
            new ArmorItem(ModArmorMaterials.PUMPKIN, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item PUMPKIN_BOOTS = registerItem("pumpkin_boots",
            new ArmorItem(ModArmorMaterials.PUMPKIN, ArmorItem.Type.BOOTS, new FabricItemSettings()));
    public static final Item COBALT_HELMET = registerItem("cobalt_helmet",
            new ModArmorItem(ModArmorMaterials.COBALT, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item COBALT_CHESTPLATE = registerItem("cobalt_chestplate",
            new ArmorItem(ModArmorMaterials.COBALT, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item COBALT_LEGGINGS = registerItem("cobalt_leggings",
            new ArmorItem(ModArmorMaterials.COBALT, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item COBALT_BOOTS = registerItem("cobalt_boots",
            new ArmorItem(ModArmorMaterials.COBALT, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    public static final Item ORICHALCUM_HELMET = registerItem("orichalcum_helmet",
            new ModArmorItem(ModArmorMaterials.ORICHALCUM, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item ORICHALCUM_CHESTPLATE = registerItem("orichalcum_chestplate",
            new ArmorItem(ModArmorMaterials.ORICHALCUM, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item ORICHALCUM_LEGGINGS = registerItem("orichalcum_leggings",
            new ArmorItem(ModArmorMaterials.ORICHALCUM, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item ORICHALCUM_BOOTS = registerItem("orichalcum_boots",
            new ArmorItem(ModArmorMaterials.ORICHALCUM, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    public static final Item ADAMANTITE_HELMET = registerItem("adamantite_helmet",
            new ModArmorItem(ModArmorMaterials.ADAMANTITE, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item ADAMANTITE_CHESTPLATE = registerItem("adamantite_chestplate",
            new ArmorItem(ModArmorMaterials.ADAMANTITE, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item ADAMANTITE_LEGGINGS = registerItem("adamantite_leggings",
            new ArmorItem(ModArmorMaterials.ADAMANTITE, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item ADAMANTITE_BOOTS = registerItem("adamantite_boots",
            new ArmorItem(ModArmorMaterials.ADAMANTITE, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    public static final Item HELLSTONE_HELMET = registerItem("hellstone_helmet",
            new ModArmorItem(ModArmorMaterials.HELLSTONE, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item HELLSTONE_CHESTPLATE = registerItem("hellstone_chestplate",
            new ArmorItem(ModArmorMaterials.HELLSTONE, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item HELLSTONE_LEGGINGS = registerItem("hellstone_leggings",
            new ArmorItem(ModArmorMaterials.HELLSTONE, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item HELLSTONE_BOOTS = registerItem("hellstone_boots",
            new ArmorItem(ModArmorMaterials.HELLSTONE, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    public static final Item INFINITE_WATER_BUCKET = registerItem("infinite_water_bucket",
            new InfiniteWaterBucketItem(new FabricItemSettings().maxCount(1)));

    public static final Item INFINITE_LAVA_BUCKET = registerItem("infinite_lava_bucket",
            new InfiniteLavaBucketItem(new FabricItemSettings().maxCount(1)));

    public static final Item MAGIC_MIRROR = registerItem("magic_mirror",
            new MagicMirrorItem(new Item.Settings()));


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
