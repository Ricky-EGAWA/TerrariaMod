package ricky.terrariamod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import ricky.terrariamod.TerrariaMod;

public class ModItemGroups {
    public static final ItemGroup MOD_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(TerrariaMod.MOD_ID, "mod"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.spyder_fang"))
                    .icon(()->new ItemStack(ModItems.SPIDER_FANG)).entries((displayContext, entries) -> {
                        entries.add(ModItems.SPIDER_FANG);
                    }).build());
    public static void registerItemGroups(){
        TerrariaMod.LOGGER.info("Registering Item Groups for " + TerrariaMod.MOD_ID);
    }
}
