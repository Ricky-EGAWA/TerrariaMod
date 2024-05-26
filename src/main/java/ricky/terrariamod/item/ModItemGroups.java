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
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.spyder_fang"))
                    .icon(()->new ItemStack(ModItems.SPIDER_FANG)).entries((displayContext, entries) -> {
                        entries.add(ModItems.SPIDER_FANG);

                        entries.add(ModItems.MetalDetectorItem);
                        entries.add(ModBlocks.SOUND_BLOCK);
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
                        entries.add(ModBlocks.ORICHALCUM_ORE);
                        entries.add(ModBlocks.ADAMANTITE_ORE);
                        entries.add(ModBlocks.HELLSTONE_ORE);
                    }).build());

    public static void registerItemGroups(){
        TerrariaMod.LOGGER.info("Registering Item Groups for " + TerrariaMod.MOD_ID);
    }
}
