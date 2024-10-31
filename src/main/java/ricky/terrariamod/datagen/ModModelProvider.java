package ricky.terrariamod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.ArmorItem;
import ricky.terrariamod.block.ModBlocks;
import ricky.terrariamod.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.COBALT_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.COBALT_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ORICHALCUM_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ORICHALCUM_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ADAMANTITE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ADAMANTITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.HELLSTONE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.HELLSTONE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SOUND_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.COBALT_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.COBALT_RAW, Models.GENERATED);
        itemModelGenerator.register(ModItems.ORICHALCUM_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.ORICHALCUM_RAW, Models.GENERATED);
        itemModelGenerator.register(ModItems.ADAMANTITE_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.ADAMANTITE_RAW, Models.GENERATED);
        itemModelGenerator.register(ModItems.HELLSTONE_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.HELLSTONE_RAW, Models.GENERATED);
        itemModelGenerator.register(ModItems.COBALT_PICKAXE, Models.GENERATED);
        itemModelGenerator.register(ModItems.COBALT_AXE, Models.GENERATED);
        itemModelGenerator.register(ModItems.COBALT_SHOVEL, Models.GENERATED);
        itemModelGenerator.register(ModItems.COBALT_SWORD, Models.GENERATED);
//        itemModelGenerator.register(ModItems.COBALT_DRILL, Models.GENERATED);
        itemModelGenerator.register(ModItems.ORICHALCUM_PICKAXE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ORICHALCUM_AXE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ORICHALCUM_SHOVEL, Models.GENERATED);
        itemModelGenerator.register(ModItems.ORICHALCUM_SWORD, Models.GENERATED);
//        itemModelGenerator.register(ModItems.ORICHALCUM_DRILL, Models.GENERATED);
        itemModelGenerator.register(ModItems.ADAMANTITE_PICKAXE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ADAMANTITE_AXE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ADAMANTITE_SHOVEL, Models.GENERATED);
        itemModelGenerator.register(ModItems.ADAMANTITE_SWORD, Models.GENERATED);
//        itemModelGenerator.register(ModItems.ADAMANTITE_DRILL, Models.GENERATED);
        itemModelGenerator.register(ModItems.HELLSTONE_PICKAXE, Models.GENERATED);
        itemModelGenerator.register(ModItems.HELLSTONE_AXE, Models.GENERATED);
        itemModelGenerator.register(ModItems.HELLSTONE_SHOVEL, Models.GENERATED);
        itemModelGenerator.register(ModItems.HELLSTONE_SWORD, Models.GENERATED);
//        itemModelGenerator.register(ModItems.HELLSTONE_DRILL, Models.GENERATED);

        itemModelGenerator.register(ModItems.MetalDetectorItem, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPIDER_FANG, Models.GENERATED);
        itemModelGenerator.register(ModItems.INFINITE_WATER_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.INFINITE_LAVA_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.MAGIC_MIRROR, Models.GENERATED);

        itemModelGenerator.registerArmor(((ArmorItem) ModItems.COBALT_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.COBALT_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.COBALT_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.COBALT_BOOTS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ORICHALCUM_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ORICHALCUM_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ORICHALCUM_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ORICHALCUM_BOOTS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ADAMANTITE_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ADAMANTITE_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ADAMANTITE_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ADAMANTITE_BOOTS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.HELLSTONE_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.HELLSTONE_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.HELLSTONE_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.HELLSTONE_BOOTS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.CACTUS_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.CACTUS_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.CACTUS_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.CACTUS_BOOTS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.PUMPKIN_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.PUMPKIN_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.PUMPKIN_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.PUMPKIN_BOOTS));
    }
}
