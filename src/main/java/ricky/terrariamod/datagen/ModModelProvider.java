package ricky.terrariamod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
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
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_COBALT_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ORICHALCUM_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ORICHALCUM_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_ORICHALCUM_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ADAMANTITE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ADAMANTITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_ADAMANTITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.HELLSTONE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.HELLSTONE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SOUND_BLOCK);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.EBON_STONE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.EBON_ICE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.EBON_SAND);
        blockStateModelGenerator.registerSingleton(ModBlocks.EBON_SANDSTONE, TexturedModel.CUBE_BOTTOM_TOP);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRIM_STONE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRIM_ICE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRIM_SAND);
        blockStateModelGenerator.registerSingleton(ModBlocks.CRIM_SANDSTONE, TexturedModel.CUBE_BOTTOM_TOP);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PEARL_STONE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PEARL_ICE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PEARL_SAND);
        blockStateModelGenerator.registerSingleton(ModBlocks.PEARL_SANDSTONE, TexturedModel.CUBE_BOTTOM_TOP);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GLOWING_MOSS);
//        blockStateModelGenerator.registerPointedDripstone();//TODO ツララのモデリング
        //plants
        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.DEATH_WEED, ModBlocks.POTTED_DEATH_WEED,BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.SHIVER_THORN, ModBlocks.POTTED_SHIVER_THORN,BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.VILE_MUSHROOM, ModBlocks.POTTED_VILE_MUSHROOM,BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.VICIOUS_MUSHROOM, ModBlocks.POTTED_VICIOUS_MUSHROOM,BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.GLOWING_MUSHROOM, ModBlocks.POTTED_GLOWING_MUSHROOM,BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerMushroomBlock(ModBlocks.GLOWING_MUSHROOM_BLOCK);
        blockStateModelGenerator.registerMushroomBlock(ModBlocks.GLOWING_MUSHROOM_STEM);
        //tree
        blockStateModelGenerator.registerLog(ModBlocks.EBON_LOG).log(ModBlocks.EBON_LOG).wood(ModBlocks.EBON_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_EBON_LOG).log(ModBlocks.STRIPPED_EBON_LOG).wood(ModBlocks.STRIPPED_EBON_WOOD);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.EBON_LEAVES);
        blockStateModelGenerator.registerTintableCross(ModBlocks.EBON_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerLog(ModBlocks.CRIM_LOG).log(ModBlocks.CRIM_LOG).wood(ModBlocks.CRIM_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_CRIM_LOG).log(ModBlocks.STRIPPED_CRIM_LOG).wood(ModBlocks.STRIPPED_CRIM_WOOD);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRIM_LEAVES);
        blockStateModelGenerator.registerTintableCross(ModBlocks.CRIM_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerLog(ModBlocks.PEARL_LOG).log(ModBlocks.PEARL_LOG).wood(ModBlocks.PEARL_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_PEARL_LOG).log(ModBlocks.STRIPPED_PEARL_LOG).wood(ModBlocks.STRIPPED_PEARL_WOOD);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PEARL_LEAVES);
        blockStateModelGenerator.registerTintableCross(ModBlocks.PEARL_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);

        BlockStateModelGenerator.BlockTexturePool ebonPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.EBON_PLANKS);
        ebonPool.stairs(ModBlocks.EBON_STAIRS);
        ebonPool.slab(ModBlocks.EBON_SLAB);
        ebonPool.button(ModBlocks.EBON_BUTTON);
        ebonPool.pressurePlate(ModBlocks.EBON_PRESSURE_PLATE);
        ebonPool.fence(ModBlocks.EBON_FENCE);
        ebonPool.fenceGate(ModBlocks.EBON_FENCE_GATE);
        BlockStateModelGenerator.BlockTexturePool crimPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.CRIM_PLANKS);
        crimPool.stairs(ModBlocks.CRIM_STAIRS);
        crimPool.slab(ModBlocks.CRIM_SLAB);
        crimPool.button(ModBlocks.CRIM_BUTTON);
        crimPool.pressurePlate(ModBlocks.CRIM_PRESSURE_PLATE);
        crimPool.fence(ModBlocks.CRIM_FENCE);
        crimPool.fenceGate(ModBlocks.CRIM_FENCE_GATE);
        BlockStateModelGenerator.BlockTexturePool pearlPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.PEARL_PLANKS);
        pearlPool.stairs(ModBlocks.PEARL_STAIRS);
        pearlPool.slab(ModBlocks.PEARL_SLAB);
        pearlPool.button(ModBlocks.PEARL_BUTTON);
        pearlPool.pressurePlate(ModBlocks.PEARL_PRESSURE_PLATE);
        pearlPool.fence(ModBlocks.PEARL_FENCE);
        pearlPool.fenceGate(ModBlocks.PEARL_FENCE_GATE);

        //ダンジョン用
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DUNGEON_TILE_GREEN_FRAGILE);
        BlockStateModelGenerator.BlockTexturePool dungeonTileGreenPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.DUNGEON_TILE_GREEN);
        dungeonTileGreenPool.stairs(ModBlocks.DUNGEON_TILE_GREEN_STAIRS);
        dungeonTileGreenPool.slab(ModBlocks.DUNGEON_TILE_GREEN_SLAB);
        dungeonTileGreenPool.wall(ModBlocks.DUNGEON_TILE_GREEN_WALL);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DUNGEON_BRICK_GREEN_FRAGILE);
        BlockStateModelGenerator.BlockTexturePool dungeonBrickGreenPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.DUNGEON_BRICK_GREEN);
        dungeonBrickGreenPool.stairs(ModBlocks.DUNGEON_BRICK_GREEN_STAIRS);
        dungeonBrickGreenPool.slab(ModBlocks.DUNGEON_BRICK_GREEN_SLAB);
        dungeonBrickGreenPool.wall(ModBlocks.DUNGEON_BRICK_GREEN_WALL);
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
        itemModelGenerator.register(ModItems.COBALT_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.COBALT_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.COBALT_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.COBALT_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ORICHALCUM_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ORICHALCUM_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ORICHALCUM_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ORICHALCUM_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ADAMANTITE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ADAMANTITE_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ADAMANTITE_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ADAMANTITE_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.HELLSTONE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.HELLSTONE_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.HELLSTONE_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.HELLSTONE_SWORD, Models.HANDHELD);

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
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.OAK_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.OAK_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.OAK_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.OAK_BOOTS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.COPPER_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.COPPER_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.COPPER_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.COPPER_BOOTS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.OBSIDIAN_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.OBSIDIAN_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.OBSIDIAN_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.OBSIDIAN_BOOTS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.GLASS_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.NIGHT_HELMET));

        itemModelGenerator.register(ModItems.ENCHANTED_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.AMETHYST_STAFF, Models.HANDHELD);
        itemModelGenerator.register(ModItems.EMERALD_STAFF, Models.HANDHELD);
        itemModelGenerator.register(ModItems.DIAMOND_STAFF, Models.HANDHELD);
        itemModelGenerator.register(ModItems.WATER_BOLT, Models.HANDHELD);

        itemModelGenerator.register(ModItems.GOLDEN_KEY, Models.GENERATED);

        itemModelGenerator.register(ModItems.ROCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.MUSKET_BALL, Models.GENERATED);

        itemModelGenerator.register(ModItems.MAGIC_MISSILE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.MURAMASA, Models.HANDHELD);
        itemModelGenerator.register(ModItems.COBALT_SHIELD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.HANDGUN, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PHOENIX_BLASTER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.WOODEN_BOOMERANG, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ENCHANTED_BOOMERANG, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ICE_BOOMERANG, Models.HANDHELD);
        itemModelGenerator.register(ModItems.FLAMARANG, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SHROOMERANG, Models.HANDHELD);
        itemModelGenerator.register(ModItems.THORN_CHAKRAM, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TRIMARANG, Models.HANDHELD);

        itemModelGenerator.register(ModItems.SUSPICIOUS_LOOKING_EYE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ADHESIVE_BANDAGE, Models.GENERATED);
        itemModelGenerator.register(ModItems.NAZAR ,Models.GENERATED);
        itemModelGenerator.register(ModItems.VITAMIN ,Models.GENERATED);
        itemModelGenerator.register(ModItems.BEZOAR ,Models.GENERATED);
        itemModelGenerator.register(ModItems.BLIND_FOLD ,Models.GENERATED);
        itemModelGenerator.register(ModItems.FAST_CLOCK ,Models.GENERATED);
        itemModelGenerator.register(ModItems.TRIFOLD_MAP ,Models.GENERATED);

        itemModelGenerator.register(ModItems.FRUITCAKE_CHAKRAM, Models.HANDHELD);
        itemModelGenerator.register(ModItems.CANDY_CANE_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.CANDY_CANE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PRESENT, Models.GENERATED);
    }
}
