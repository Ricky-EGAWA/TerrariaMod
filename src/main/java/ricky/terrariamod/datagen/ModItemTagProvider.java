package ricky.terrariamod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
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
                        ModItems.PUMPKIN_HELMET, ModItems.PUMPKIN_CHESTPLATE, ModItems.PUMPKIN_LEGGINGS, ModItems.PUMPKIN_BOOTS,
                        ModItems.COBALT_HELMET, ModItems.COBALT_CHESTPLATE, ModItems.COBALT_LEGGINGS, ModItems.COBALT_BOOTS,
                        ModItems.ORICHALCUM_HELMET, ModItems.ORICHALCUM_CHESTPLATE, ModItems.ORICHALCUM_LEGGINGS, ModItems.ORICHALCUM_BOOTS,
                        ModItems.ADAMANTITE_HELMET, ModItems.ADAMANTITE_CHESTPLATE, ModItems.ADAMANTITE_LEGGINGS, ModItems.ADAMANTITE_BOOTS,
                        ModItems.HELLSTONE_HELMET, ModItems.HELLSTONE_CHESTPLATE, ModItems.HELLSTONE_LEGGINGS, ModItems.HELLSTONE_BOOTS);
    }
}
