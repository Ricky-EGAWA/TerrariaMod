package ricky.terrariamod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import ricky.terrariamod.block.ModBlocks;
import ricky.terrariamod.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.CACTUS_HELMET, ModItems.CACTUS_CHESTPLATE, ModItems.CACTUS_LEGGINGS, ModItems.CACTUS_BOOTS,
                        ModItems.OAK_HELMET, ModItems.OAK_CHESTPLATE, ModItems.OAK_LEGGINGS, ModItems.OAK_BOOTS,
                        ModItems.COPPER_HELMET, ModItems.COPPER_CHESTPLATE, ModItems.COPPER_LEGGINGS, ModItems.COPPER_BOOTS,
                        ModItems.OBSIDIAN_HELMET, ModItems.OBSIDIAN_CHESTPLATE, ModItems.OBSIDIAN_LEGGINGS, ModItems.OBSIDIAN_BOOTS,
                        ModItems.PUMPKIN_HELMET, ModItems.PUMPKIN_CHESTPLATE, ModItems.PUMPKIN_LEGGINGS, ModItems.PUMPKIN_BOOTS,
                        ModItems.COBALT_HELMET, ModItems.COBALT_CHESTPLATE, ModItems.COBALT_LEGGINGS, ModItems.COBALT_BOOTS,
                        ModItems.ORICHALCUM_HELMET, ModItems.ORICHALCUM_CHESTPLATE, ModItems.ORICHALCUM_LEGGINGS, ModItems.ORICHALCUM_BOOTS,
                        ModItems.ADAMANTITE_HELMET, ModItems.ADAMANTITE_CHESTPLATE, ModItems.ADAMANTITE_LEGGINGS, ModItems.ADAMANTITE_BOOTS,
                        ModItems.HELLSTONE_HELMET, ModItems.HELLSTONE_CHESTPLATE, ModItems.HELLSTONE_LEGGINGS, ModItems.HELLSTONE_BOOTS,
                        ModItems.GLASS_HELMET, ModItems.NIGHT_HELMET);
        getOrCreateTagBuilder(ItemTags.PLANKS)
                .add(ModBlocks.EBON_PLANKS.asItem());
        getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.EBON_LOG.asItem())
                .add(ModBlocks.EBON_WOOD.asItem())
                .add(ModBlocks.STRIPPED_EBON_LOG.asItem())
                .add(ModBlocks.STRIPPED_EBON_WOOD.asItem());
    }
}
