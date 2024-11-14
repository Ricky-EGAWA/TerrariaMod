package ricky.terrariamod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import ricky.terrariamod.TerrariaMod;
import ricky.terrariamod.block.ModBlocks;

public class ModItemGroups {
    public static final ItemGroup MOD_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(TerrariaMod.MOD_ID, "mod"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.special_item"))
                    .icon(()->new ItemStack(ModItems.MetalDetectorItem)).entries((displayContext, entries) -> {
//                        entries.add(ModItems.SPIDER_FANG);
                        entries.add(ModItems.MetalDetectorItem);
//                        entries.add(ModBlocks.SOUND_BLOCK);
                        entries.add(ModItems.INFINITE_WATER_BUCKET);
                        entries.add(ModItems.INFINITE_LAVA_BUCKET);
                        entries.add(ModItems.MAGIC_MIRROR);
                    }).build());

    //ModBlocks
    public static final ItemGroup MOD_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(TerrariaMod.MOD_ID, "mod_blocks"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.mod_blocks"))
                    .icon(()->new ItemStack(ModBlocks.EBON_STONE)).entries((displayContext, entries) -> {
                        entries.add(ModBlocks.EBON_STONE);
                        entries.add(ModBlocks.EBON_ICE);
                        entries.add(ModBlocks.EBON_SAND);
                        entries.add(ModBlocks.EBON_SANDSTONE);
                        entries.add(ModBlocks.CRIM_STONE);
                        entries.add(ModBlocks.CRIM_ICE);
                        entries.add(ModBlocks.CRIM_SAND);
                        entries.add(ModBlocks.CRIM_SANDSTONE);
                        entries.add(ModBlocks.PEARL_STONE);
                        entries.add(ModBlocks.PEARL_ICE);
                        entries.add(ModBlocks.PEARL_SAND);
                        entries.add(ModBlocks.PEARL_SANDSTONE);
                        entries.add(ModBlocks.DEATH_WEED);
                        entries.add(ModBlocks.VILE_MUSHROOM);
                        entries.add(ModBlocks.EBON_LOG);
                        entries.add(ModBlocks.EBON_WOOD);
                        entries.add(ModBlocks.STRIPPED_EBON_LOG);
                        entries.add(ModBlocks.STRIPPED_EBON_WOOD);
                        entries.add(ModBlocks.EBON_PLANKS);
                        entries.add(ModBlocks.EBON_LEAVES);
                        entries.add(ModBlocks.EBON_SAPLING);
                    }).build());

    public static final ItemGroup MOD_ARMOR_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(TerrariaMod.MOD_ID, "mod_armor"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.cobalt_chestplate"))
                    .icon(()->new ItemStack(ModItems.COBALT_CHESTPLATE)).entries((displayContext, entries) -> {
                        entries.add(ModItems.COBALT_HELMET);
                        entries.add(ModItems.COBALT_CHESTPLATE);
                        entries.add(ModItems.COBALT_LEGGINGS);
                        entries.add(ModItems.COBALT_BOOTS);
                        entries.add(ModItems.ORICHALCUM_HELMET);
                        entries.add(ModItems.ORICHALCUM_CHESTPLATE);
                        entries.add(ModItems.ORICHALCUM_LEGGINGS);
                        entries.add(ModItems.ORICHALCUM_BOOTS);
                        entries.add(ModItems.ADAMANTITE_HELMET);
                        entries.add(ModItems.ADAMANTITE_CHESTPLATE);
                        entries.add(ModItems.ADAMANTITE_LEGGINGS);
                        entries.add(ModItems.ADAMANTITE_BOOTS);
                        entries.add(ModItems.HELLSTONE_HELMET);
                        entries.add(ModItems.HELLSTONE_CHESTPLATE);
                        entries.add(ModItems.HELLSTONE_LEGGINGS);
                        entries.add(ModItems.HELLSTONE_BOOTS);
                        entries.add(ModItems.CACTUS_HELMET);
                        entries.add(ModItems.CACTUS_CHESTPLATE);
                        entries.add(ModItems.CACTUS_LEGGINGS);
                        entries.add(ModItems.CACTUS_BOOTS);
                        entries.add(ModItems.PUMPKIN_HELMET);
                        entries.add(ModItems.PUMPKIN_CHESTPLATE);
                        entries.add(ModItems.PUMPKIN_LEGGINGS);
                        entries.add(ModItems.PUMPKIN_BOOTS);
                        entries.add(ModItems.OAK_HELMET);
                        entries.add(ModItems.OAK_CHESTPLATE);
                        entries.add(ModItems.OAK_LEGGINGS);
                        entries.add(ModItems.OAK_BOOTS);
                        entries.add(ModItems.COPPER_HELMET);
                        entries.add(ModItems.COPPER_CHESTPLATE);
                        entries.add(ModItems.COPPER_LEGGINGS);
                        entries.add(ModItems.COPPER_BOOTS);
                        entries.add(ModItems.OBSIDIAN_HELMET);
                        entries.add(ModItems.OBSIDIAN_CHESTPLATE);
                        entries.add(ModItems.OBSIDIAN_LEGGINGS);
                        entries.add(ModItems.OBSIDIAN_BOOTS);
                        entries.add(ModItems.GLASS_HELMET);
                        entries.add(ModItems.NIGHT_HELMET);
                    }).build());

    public static final ItemGroup MOD_MIN_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(TerrariaMod.MOD_ID, "mod_min"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.cobalt"))
                    .icon(()->new ItemStack(ModItems.COBALT_INGOT)).entries((displayContext, entries) -> {
                        entries.add(ModItems.COBALT_INGOT);
                        entries.add(ModItems.ORICHALCUM_INGOT);
                        entries.add(ModItems.ADAMANTITE_INGOT);
                        entries.add(ModItems.HELLSTONE_INGOT);

                        entries.add(ModItems.COBALT_RAW);
                        entries.add(ModItems.ORICHALCUM_RAW);
                        entries.add(ModItems.ADAMANTITE_RAW);
                        entries.add(ModItems.HELLSTONE_RAW);

                        entries.add(ModBlocks.COBALT_BLOCK);
                        entries.add(ModBlocks.ORICHALCUM_BLOCK);
                        entries.add(ModBlocks.ADAMANTITE_BLOCK);
                        entries.add(ModBlocks.HELLSTONE_BLOCK);

                        entries.add(ModBlocks.COBALT_ORE);
                        entries.add(ModBlocks.DEEPSLATE_COBALT_ORE);
                        entries.add(ModBlocks.ORICHALCUM_ORE);
                        entries.add(ModBlocks.DEEPSLATE_ORICHALCUM_ORE);
                        entries.add(ModBlocks.ADAMANTITE_ORE);
                        entries.add(ModBlocks.DEEPSLATE_ADAMANTITE_ORE);
                        entries.add(ModBlocks.HELLSTONE_ORE);

                        entries.add(ModItems.COBALT_PICKAXE);
                        entries.add(ModItems.COBALT_AXE);
                        entries.add(ModItems.COBALT_SHOVEL);
                        entries.add(ModItems.COBALT_SWORD);
//                        entries.add(ModItems.COBALT_DRILL);

                        entries.add(ModItems.ORICHALCUM_PICKAXE);
                        entries.add(ModItems.ORICHALCUM_AXE);
                        entries.add(ModItems.ORICHALCUM_SHOVEL);
                        entries.add(ModItems.ORICHALCUM_SWORD);
//                        entries.add(ModItems.ORICHALCUM_DRILL);

                        entries.add(ModItems.ADAMANTITE_PICKAXE);
                        entries.add(ModItems.ADAMANTITE_AXE);
                        entries.add(ModItems.ADAMANTITE_SHOVEL);
                        entries.add(ModItems.ADAMANTITE_SWORD);
//                        entries.add(ModItems.ADAMANTITE_DRILL);

                        entries.add(ModItems.HELLSTONE_PICKAXE);
                        entries.add(ModItems.HELLSTONE_AXE);
                        entries.add(ModItems.HELLSTONE_SHOVEL);
                        entries.add(ModItems.HELLSTONE_SWORD);
//                        entries.add(ModItems.HELLSTONE_DRILL);
                    }).build());

    public static void registerItemGroups(){
        TerrariaMod.LOGGER.info("Registering Item Groups for " + TerrariaMod.MOD_ID);
    }
}
