package ricky.terrariamod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import ricky.terrariamod.TerrariaMod;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import ricky.terrariamod.item.boomerang.*;
import ricky.terrariamod.item.bows.CustomBowItem;
import ricky.terrariamod.item.gun.HandGunItem;
import ricky.terrariamod.item.gun.RocketLauncherItem;
import ricky.terrariamod.item.gun.ShotgunItem;
import ricky.terrariamod.item.gun.SniperRifleItem;
import ricky.terrariamod.item.custom.*;
import ricky.terrariamod.item.gun.ammo.MusketBallItem;
import ricky.terrariamod.item.magic.*;

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
            new PickaxeItem(ModToolMaterial.COBALT_INGOT, 2, -3f, new FabricItemSettings().maxCount(1)));
    public static final Item COBALT_AXE = registerItem("cobalt_axe",
            new AxeItem(ModToolMaterial.COBALT_INGOT, 7, -3f, new FabricItemSettings().maxCount(1)));
    public static final Item COBALT_SHOVEL = registerItem("cobalt_shovel",
            new ShovelItem(ModToolMaterial.COBALT_INGOT, 1, -3f, new FabricItemSettings().maxCount(1)));
    public static final Item COBALT_SWORD = registerItem("cobalt_sword",
            new SwordItem(ModToolMaterial.COBALT_INGOT, 5, -2.4f, new FabricItemSettings().maxCount(1)));

    public static final Item ORICHALCUM_PICKAXE = registerItem("orichalcum_pickaxe",
            new PickaxeItem(ModToolMaterial.ORICHALCUM_INGOT, 2, -3f, new FabricItemSettings().maxCount(1)));
    public static final Item ORICHALCUM_AXE = registerItem("orichalcum_axe",
            new AxeItem(ModToolMaterial.ORICHALCUM_INGOT, 7, -3f, new FabricItemSettings().maxCount(1)));
    public static final Item ORICHALCUM_SHOVEL = registerItem("orichalcum_shovel",
            new ShovelItem(ModToolMaterial.ORICHALCUM_INGOT, 1, -3f, new FabricItemSettings().maxCount(1)));
    public static final Item ORICHALCUM_SWORD = registerItem("orichalcum_sword",
            new SwordItem(ModToolMaterial.ORICHALCUM_INGOT, 5, -2.4f, new FabricItemSettings().maxCount(1)));

    public static final Item ADAMANTITE_PICKAXE = registerItem("adamantite_pickaxe",
            new PickaxeItem(ModToolMaterial.ADAMANTITE_INGOT, 2, -3f, new FabricItemSettings().maxCount(1)));
    public static final Item ADAMANTITE_AXE = registerItem("adamantite_axe",
            new AxeItem(ModToolMaterial.ADAMANTITE_INGOT, 7, -3f, new FabricItemSettings().maxCount(1)));
    public static final Item ADAMANTITE_SHOVEL = registerItem("adamantite_shovel",
            new ShovelItem(ModToolMaterial.ADAMANTITE_INGOT, 1, -3f, new FabricItemSettings().maxCount(1)));
    public static final Item ADAMANTITE_SWORD = registerItem("adamantite_sword",
            new SwordItem(ModToolMaterial.ADAMANTITE_INGOT, 5, -2.4f, new FabricItemSettings().maxCount(1)));

    public static final Item HELLSTONE_PICKAXE = registerItem("hellstone_pickaxe",
            new PickaxeItem(ModToolMaterial.HELLSTONE_INGOT, 2, -3f, new FabricItemSettings().maxCount(1)));
    public static final Item HELLSTONE_AXE = registerItem("hellstone_axe",
            new AxeItem(ModToolMaterial.HELLSTONE_INGOT, 7, -3f, new FabricItemSettings().maxCount(1)));
    public static final Item HELLSTONE_SHOVEL = registerItem("hellstone_shovel",
            new ShovelItem(ModToolMaterial.HELLSTONE_INGOT, 1, -3f, new FabricItemSettings().maxCount(1)));
    public static final Item HELLSTONE_SWORD = registerItem("hellstone_sword",
            new SwordItem(ModToolMaterial.HELLSTONE_INGOT, 5, -2.4f, new FabricItemSettings().maxCount(1)));

    public static final Item OAK_HELMET = registerItem("oak_helmet",
            new ModArmorItem(ModArmorMaterials.OAK, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item OAK_CHESTPLATE = registerItem("oak_chestplate",
            new ArmorItem(ModArmorMaterials.OAK, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item OAK_LEGGINGS = registerItem("oak_leggings",
            new ArmorItem(ModArmorMaterials.OAK, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item OAK_BOOTS = registerItem("oak_boots",
            new ArmorItem(ModArmorMaterials.OAK, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    public static final Item COPPER_HELMET = registerItem("copper_helmet",
            new ModArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item COPPER_CHESTPLATE = registerItem("copper_chestplate",
            new ArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item COPPER_LEGGINGS = registerItem("copper_leggings",
            new ArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item COPPER_BOOTS = registerItem("copper_boots",
            new ArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    public static final Item OBSIDIAN_HELMET = registerItem("obsidian_helmet",
            new ModArmorItem(ModArmorMaterials.OBSIDIAN, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item OBSIDIAN_CHESTPLATE = registerItem("obsidian_chestplate",
            new ArmorItem(ModArmorMaterials.OBSIDIAN, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item OBSIDIAN_LEGGINGS = registerItem("obsidian_leggings",
            new ArmorItem(ModArmorMaterials.OBSIDIAN, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item OBSIDIAN_BOOTS = registerItem("obsidian_boots",
            new ArmorItem(ModArmorMaterials.OBSIDIAN, ArmorItem.Type.BOOTS, new FabricItemSettings()));


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
    //水中ヘルメット
    public static final Item GLASS_HELMET = registerItem("glass_helmet",
            new ModArmorItem(ModArmorMaterials.GLASS, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item NIGHT_HELMET = registerItem("night_helmet",
            new ModArmorItem(ModArmorMaterials.NIGHT, ArmorItem.Type.HELMET, new FabricItemSettings()));
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

    public static final Item IRON_BOW = registerItem("iron_bow",
            new CustomBowItem(new Item.Settings().maxDamage(640),30,72000));
    public static final Item GOLD_BOW = registerItem("gold_bow",
            new CustomBowItem(new Item.Settings().maxDamage(384),20,36000));
    public static final Item DIAMOND_BOW = registerItem("diamond_bow",
            new CustomBowItem(new Item.Settings().maxDamage(1024),40,72000));
    public static final Item ROCKET_LAUNCHER = registerItem("rocket_launcher",
            new RocketLauncherItem(new Item.Settings().maxDamage(500),3,2));
    public static final Item SHOTGUN = registerItem("shotgun",
            new ShotgunItem(new Item.Settings().maxDamage(1024)));
    public static final Item ROCKET = registerItem("rocket", new Item(new Item.Settings()));
    public static final Item MUSKET_BALL = registerItem("musket_ball", new MusketBallItem(new Item.Settings()));
    public static final Item SNIPER_RIFLE = registerItem("sniper_rifle",
            new SniperRifleItem(new Item.Settings().maxDamage(1024)));

    public static final Item ENCHANTED_SWORD = registerItem("enchanted_sword",
            new EnchantedSwordItem(ToolMaterials.DIAMOND, 5, -2.4f, new FabricItemSettings().maxCount(1)));
    public static final Item AMETHYST_STAFF = registerItem("amethyst_staff",
            new AmethystStaffItem(ToolMaterials.WOOD, 1, -2.4f, new FabricItemSettings().maxCount(1)));
    public static final Item EMERALD_STAFF = registerItem("emerald_staff",
            new EmeraldStaffItem(ToolMaterials.WOOD, 1, -2.4f, new FabricItemSettings().maxCount(1)));
    public static final Item DIAMOND_STAFF = registerItem("diamond_staff",
            new DiamondStaffItem(ToolMaterials.WOOD, 1, -2.4f, new FabricItemSettings().maxCount(1)));
    public static final Item WATER_BOLT = registerItem("water_bolt",
            new WaterBoltItem(ToolMaterials.WOOD, 1, -2.4f, new FabricItemSettings().maxCount(1)));

    public static final Item GOLDEN_KEY = registerItem("golden_key", new Item(new FabricItemSettings()));

    public static final Item MURAMASA = registerItem("muramasa",
            new SwordItem(ToolMaterials.NETHERITE, 5, 3f, new FabricItemSettings().maxCount(1)));
    public static final Item MAGIC_MISSILE = registerItem("magic_missile",
            new MagicMissileItem(ToolMaterials.WOOD, 1, -2.4f, new FabricItemSettings().maxCount(1)));
    public static final Item COBALT_SHIELD = registerItem("cobalt_shield", new CobaltShieldItem(new FabricItemSettings()));
    public static final Item HANDGUN = registerItem("handgun",
            new HandGunItem(ToolMaterials.IRON, -1, -2.4f,new FabricItemSettings()));
    public static final Item PHOENIX_BLASTER = registerItem("phoenix_blaster",
            new HandGunItem(ToolMaterials.IRON, -1, -2.4f,new FabricItemSettings()));
    public static final Item WOODEN_BOOMERANG = registerItem("wooden_boomerang", new WoodenBoomerangItem(new Item.Settings().maxDamage(256)));
    public static final Item THORN_CHAKRAM = registerItem("thorn_chakram", new ThornChakramItem(new Item.Settings().maxDamage(256)));
    public static final Item ENCHANTED_BOOMERANG = registerItem("enchanted_boomerang", new EnchantedBoomerangItem(new Item.Settings().maxDamage(256)));
    public static final Item ICE_BOOMERANG = registerItem("ice_boomerang", new IceBoomerangItem(new Item.Settings().maxDamage(256)));
    public static final Item FLAMARANG = registerItem("flamarang", new FlamarangItem(new Item.Settings().maxDamage(256)));
    public static final Item SHROOMERANG = registerItem("shroomerang", new ShroomerangItem(new Item.Settings().maxDamage(256)));
    public static final Item TRIMARANG = registerItem("trimarang", new TrimarangItem(new Item.Settings().maxDamage(256)));

    // 各ツールを取得するメソッド
    public static Item getPickaxe(String materialName) {
        return switch (materialName.toLowerCase()) {
            case "cobalt" -> COBALT_PICKAXE;
            case "orichalcum" -> ORICHALCUM_PICKAXE;
            case "adamantite" -> ADAMANTITE_PICKAXE;
            case "hellstone" -> HELLSTONE_PICKAXE;
            default -> throw new IllegalArgumentException("Unknown material: " + materialName);
        };
    }

    public static Item getAxe(String materialName) {
        return switch (materialName.toLowerCase()) {
            case "cobalt" -> COBALT_AXE;
            case "orichalcum" -> ORICHALCUM_AXE;
            case "adamantite" -> ADAMANTITE_AXE;
            case "hellstone" -> HELLSTONE_AXE;
            default -> throw new IllegalArgumentException("Unknown material: " + materialName);
        };
    }

    public static Item getSword(String materialName) {
        return switch (materialName.toLowerCase()) {
            case "cobalt" -> COBALT_SWORD;
            case "orichalcum" -> ORICHALCUM_SWORD;
            case "adamantite" -> ADAMANTITE_SWORD;
            case "hellstone" -> HELLSTONE_SWORD;
            default -> throw new IllegalArgumentException("Unknown material: " + materialName);
        };
    }

    public static Item getShovel(String materialName) {
        return switch (materialName.toLowerCase()) {
            case "cobalt" -> COBALT_SHOVEL;
            case "orichalcum" -> ORICHALCUM_SHOVEL;
            case "adamantite" -> ADAMANTITE_SHOVEL;
            case "hellstone" -> HELLSTONE_SHOVEL;
            default -> throw new IllegalArgumentException("Unknown material: " + materialName);
        };
    }

    public static Item getHelmet(String materialName) {
        return switch (materialName) {
            case "cobalt" -> COBALT_HELMET;
            case "orichalcum" -> ORICHALCUM_HELMET;
            case "adamantite" -> ADAMANTITE_HELMET;
            case "hellstone" -> HELLSTONE_HELMET;
            case "cactus" -> CACTUS_HELMET;
            case "pumpkin" -> PUMPKIN_HELMET;
            case "oak" -> OAK_HELMET;
            case "obsidian" -> OBSIDIAN_HELMET;
            case "glass" -> GLASS_HELMET;
            case "night" -> NIGHT_HELMET;
            default -> throw new IllegalArgumentException("Unknown material: " + materialName);
        };
    }

    public static Item getChestplate(String materialName) {
        return switch (materialName) {
            case "cobalt" -> COBALT_CHESTPLATE;
            case "orichalcum" -> ORICHALCUM_CHESTPLATE;
            case "adamantite" -> ADAMANTITE_CHESTPLATE;
            case "hellstone" -> HELLSTONE_CHESTPLATE;
            case "cactus" -> CACTUS_CHESTPLATE;
            case "pumpkin" -> PUMPKIN_CHESTPLATE;
            case "oak" -> OAK_CHESTPLATE;
            case "obsidian" -> OBSIDIAN_CHESTPLATE;
            default -> throw new IllegalArgumentException("Unknown material: " + materialName);
        };
    }

    public static Item getLeggings(String materialName) {
        return switch (materialName) {
            case "cobalt" -> COBALT_LEGGINGS;
            case "orichalcum" -> ORICHALCUM_LEGGINGS;
            case "adamantite" -> ADAMANTITE_LEGGINGS;
            case "hellstone" -> HELLSTONE_LEGGINGS;
            case "cactus" -> CACTUS_LEGGINGS;
            case "pumpkin" -> PUMPKIN_LEGGINGS;
            case "oak" -> OAK_LEGGINGS;
            case "obsidian" -> OBSIDIAN_LEGGINGS;
            default -> throw new IllegalArgumentException("Unknown material: " + materialName);
        };
    }

    public static Item getBoots(String materialName) {
        return switch (materialName) {
            case "cobalt" -> COBALT_BOOTS;
            case "orichalcum" -> ORICHALCUM_BOOTS;
            case "adamantite" -> ADAMANTITE_BOOTS;
            case "hellstone" -> HELLSTONE_BOOTS;
            case "cactus" -> CACTUS_BOOTS;
            case "pumpkin" -> PUMPKIN_BOOTS;
            case "oak" -> OAK_BOOTS;
            case "obsidian" -> OBSIDIAN_BOOTS;
            default -> throw new IllegalArgumentException("Unknown material: " + materialName);
        };
    }


    private static void addItemsToIngredientTabItemGroup(FabricItemGroupEntries entries){
        entries.add(SPIDER_FANG);
    }

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(TerrariaMod.MOD_ID,name),item);
    }
    public static void registerModItems(){
        TerrariaMod.LOGGER.info("Registering Mod Items for " + TerrariaMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientTabItemGroup);
    }
}
