package ricky.terrariamod.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import ricky.terrariamod.TerrariaMod;
import ricky.terrariamod.block.custom.FragileBlock;
import ricky.terrariamod.block.custom.SoundBlock;
import ricky.terrariamod.block.custom.SpikeBlock;
import ricky.terrariamod.world.ModConfiguredFeatures;
import ricky.terrariamod.world.tree.CrimSaplingGenerator;
import ricky.terrariamod.world.tree.EbonSaplingGenerator;
import ricky.terrariamod.world.tree.PearlSaplingGenerator;

public class ModBlocks {
    //採掘力 = ( ツールの基礎採掘力 + ( 1 + 効率強化レベル ^2)) × ( 1 + 採掘速度上昇 × 0.2)
    //採掘時間 = ブロックの硬度 × 30 / 採掘力
    public static final Block SOUND_BLOCK = registerBlock("sound_block",
            new SoundBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));

    public static final Block COBALT_BLOCK = registerBlock("cobalt_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block COBALT_ORE = registerBlock("cobalt_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DIAMOND_ORE).strength(50f,1200f), UniformIntProvider.create(2,5)));
    public static final Block DEEPSLATE_COBALT_ORE = registerBlock("deepslate_cobalt_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DIAMOND_ORE).strength(50f,1200f), UniformIntProvider.create(2,5)));
    public static final Block ORICHALCUM_BLOCK = registerBlock("orichalcum_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block ORICHALCUM_ORE = registerBlock("orichalcum_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DIAMOND_ORE).strength(50f,1200f), UniformIntProvider.create(2,5)));
    public static final Block DEEPSLATE_ORICHALCUM_ORE = registerBlock("deepslate_orichalcum_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DIAMOND_ORE).strength(50f,1200f), UniformIntProvider.create(2,5)));
    public static final Block ADAMANTITE_BLOCK = registerBlock("adamantite_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block ADAMANTITE_ORE = registerBlock("adamantite_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DIAMOND_ORE).strength(50f,1200f), UniformIntProvider.create(2,5)));
    public static final Block DEEPSLATE_ADAMANTITE_ORE = registerBlock("deepslate_adamantite_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DIAMOND_ORE).strength(50f,1200f), UniformIntProvider.create(2,5)));
    public static final Block HELLSTONE_BLOCK = registerBlock("hellstone_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block HELLSTONE_ORE = registerBlock("hellstone_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DIAMOND_ORE).strength(50f,1200f), UniformIntProvider.create(2,5)));

    public static final Block EBON_STONE = registerBlock("ebon_stone",
            new Block(FabricBlockSettings.copyOf(Blocks.STONE)));
    public static final Block EBON_SAND = registerBlock("ebon_sand",
            new SandBlock(10947717,FabricBlockSettings.copyOf(Blocks.SAND)));
    public static final Block EBON_SANDSTONE = registerBlock("ebon_sandstone",
            new Block(FabricBlockSettings.copyOf(Blocks.SANDSTONE)));
    public static final Block EBON_ICE = registerBlock("ebon_ice",
            new IceBlock(FabricBlockSettings.copyOf(Blocks.ICE)));
    public static final Block CRIM_STONE = registerBlock("crim_stone",
            new Block(FabricBlockSettings.copyOf(Blocks.STONE)));
    public static final Block CRIM_SAND = registerBlock("crim_sand",
            new SandBlock(11732246, FabricBlockSettings.copyOf(Blocks.SAND)));
    public static final Block CRIM_SANDSTONE = registerBlock("crim_sandstone",
            new Block(FabricBlockSettings.copyOf(Blocks.SANDSTONE)));
    public static final Block CRIM_ICE = registerBlock("crim_ice",
            new IceBlock(FabricBlockSettings.copyOf(Blocks.ICE)));
    public static final Block PEARL_STONE = registerBlock("pearl_stone",
            new Block(FabricBlockSettings.copyOf(Blocks.STONE)));
    public static final Block PEARL_SAND = registerBlock("pearl_sand",
            new SandBlock(15987186, FabricBlockSettings.copyOf(Blocks.SAND)));
    public static final Block PEARL_SANDSTONE = registerBlock("pearl_sandstone",
            new Block(FabricBlockSettings.copyOf(Blocks.SANDSTONE)));
    public static final Block PEARL_ICE = registerBlock("pearl_ice",
            new IceBlock(FabricBlockSettings.copyOf(Blocks.ICE)));
    public static final Block DEATH_WEED = registerBlock("death_weed",
            new FlowerBlock(StatusEffects.INSTANT_DAMAGE,10,FabricBlockSettings.copyOf(Blocks.ALLIUM)));
    public static final Block POTTED_DEATH_WEED = Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MOD_ID, "potted_death_weed"),
            new FlowerPotBlock(DEATH_WEED, FabricBlockSettings.copyOf(Blocks.POTTED_ALLIUM).nonOpaque()));
    public static final Block SHIVER_THORN = registerBlock("shiver_thorn",
            new FlowerBlock(StatusEffects.INSTANT_DAMAGE,10,FabricBlockSettings.copyOf(Blocks.ALLIUM)));
    public static final Block POTTED_SHIVER_THORN = Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MOD_ID, "potted_shiver_thorn"),
            new FlowerPotBlock(SHIVER_THORN, FabricBlockSettings.copyOf(Blocks.POTTED_ALLIUM).nonOpaque()));
    public static final Block VILE_MUSHROOM = registerBlock("vile_mushroom",
            new FlowerBlock(StatusEffects.INSTANT_DAMAGE,10,FabricBlockSettings.copyOf(Blocks.BROWN_MUSHROOM)));
    public static final Block POTTED_VILE_MUSHROOM = Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MOD_ID, "potted_vile_mushroom"),
            new FlowerPotBlock(VILE_MUSHROOM, FabricBlockSettings.copyOf(Blocks.POTTED_ALLIUM).nonOpaque()));
    public static final Block VICIOUS_MUSHROOM = registerBlock("vicious_mushroom",
            new FlowerBlock(StatusEffects.INSTANT_DAMAGE,10,FabricBlockSettings.copyOf(Blocks.BROWN_MUSHROOM)));
    public static final Block POTTED_VICIOUS_MUSHROOM = Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MOD_ID, "potted_vicious_mushroom"),
            new FlowerPotBlock(VICIOUS_MUSHROOM, FabricBlockSettings.copyOf(Blocks.POTTED_ALLIUM).nonOpaque()));
    public static final Block GLOWING_MUSHROOM = registerBlock("glowing_mushroom",
            new MushroomPlantBlock(FabricBlockSettings.copyOf(Blocks.BROWN_MUSHROOM).luminance(state -> 6), ModConfiguredFeatures.HUGE_GLOWING_MUSHROOM));
    public static final Block GLOWING_MOSS = registerBlock("glowing_moss_block",
            new MossBlock(FabricBlockSettings.copyOf(Blocks.MOSS_BLOCK).luminance(state -> 6).mapColor(MapColor.BLUE)));//TODO 骨粉をかけたときコケブロックが生成されている
    public static final Block ICICLE = registerBlock("icicle",
            new PointedDripstoneBlock(FabricBlockSettings.copyOf(Blocks.POINTED_DRIPSTONE)));
    public static final Block POTTED_GLOWING_MUSHROOM = Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MOD_ID, "potted_glowing_mushroom"),
            new FlowerPotBlock(GLOWING_MUSHROOM, FabricBlockSettings.copyOf(Blocks.POTTED_ALLIUM).nonOpaque()));
    public static final Block GLOWING_MUSHROOM_BLOCK = registerBlock("glowing_mushroom_block",
            new MushroomBlock(FabricBlockSettings.copyOf(Blocks.RED_MUSHROOM_BLOCK).luminance(state -> 6)));
    public static final Block GLOWING_MUSHROOM_STEM = registerBlock("glowing_mushroom_stem",
            new MushroomBlock(FabricBlockSettings.copyOf(Blocks.MUSHROOM_STEM).luminance(state -> 6)));
    //tree
    public static final Block EBON_LOG = registerBlock("ebon_log",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG)));
    public static final Block EBON_WOOD = registerBlock("ebon_wood",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_WOOD)));
    public static final Block STRIPPED_EBON_LOG = registerBlock("stripped_ebon_log",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_LOG)));
    public static final Block STRIPPED_EBON_WOOD = registerBlock("stripped_ebon_wood",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_WOOD)));
    public static final Block EBON_PLANKS = registerBlock("ebon_planks",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)));
    public static final Block EBON_LEAVES = registerBlock("ebon_leaves",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES).nonOpaque()));
    public static final Block EBON_SAPLING = registerBlock("ebon_sapling",
            new SaplingBlock(new EbonSaplingGenerator(),FabricBlockSettings.copyOf(Blocks.OAK_SAPLING).nonOpaque()));
    public static final Block CRIM_LOG = registerBlock("crim_log",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG)));
    public static final Block CRIM_WOOD = registerBlock("crim_wood",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_WOOD)));
    public static final Block STRIPPED_CRIM_LOG = registerBlock("stripped_crim_log",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_LOG)));
    public static final Block STRIPPED_CRIM_WOOD = registerBlock("stripped_crim_wood",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_WOOD)));
    public static final Block CRIM_PLANKS = registerBlock("crim_planks",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)));
    public static final Block CRIM_LEAVES = registerBlock("crim_leaves",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES).nonOpaque()));
    public static final Block CRIM_SAPLING = registerBlock("crim_sapling",
            new SaplingBlock(new CrimSaplingGenerator(),FabricBlockSettings.copyOf(Blocks.OAK_SAPLING).nonOpaque()));
    public static final Block PEARL_LOG = registerBlock("pearl_log",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG)));
    public static final Block PEARL_WOOD = registerBlock("pearl_wood",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_WOOD)));
    public static final Block STRIPPED_PEARL_LOG = registerBlock("stripped_pearl_log",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_LOG)));
    public static final Block STRIPPED_PEARL_WOOD = registerBlock("stripped_pearl_wood",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_WOOD)));
    public static final Block PEARL_PLANKS = registerBlock("pearl_planks",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)));
    public static final Block PEARL_LEAVES = registerBlock("pearl_leaves",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES).nonOpaque()));
    public static final Block PEARL_SAPLING = registerBlock("pearl_sapling",
            new SaplingBlock(new PearlSaplingGenerator(),FabricBlockSettings.copyOf(Blocks.OAK_SAPLING).nonOpaque()));
    //stairs slab etc
    public static final Block EBON_STAIRS = registerBlock("ebon_stairs",
            new StairsBlock(ModBlocks.EBON_PLANKS.getDefaultState(),FabricBlockSettings.copyOf(Blocks.OAK_STAIRS)));
    public static final Block EBON_SLAB = registerBlock("ebon_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.OAK_SLAB)));
    public static final Block EBON_BUTTON = registerBlock("ebon_button",
            new ButtonBlock(FabricBlockSettings.copyOf(Blocks.OAK_BUTTON), BlockSetType.IRON, 10, true));
    public static final Block EBON_PRESSURE_PLATE = registerBlock("ebon_pressure_plate",
            new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING,FabricBlockSettings.copyOf(Blocks.OAK_PRESSURE_PLATE),BlockSetType.IRON));
    public static final Block EBON_FENCE = registerBlock("ebon_fence",
            new FenceBlock(FabricBlockSettings.copyOf(Blocks.OAK_FENCE)));
    public static final Block EBON_FENCE_GATE = registerBlock("ebon_fence_gate",
            new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.OAK_FENCE_GATE), WoodType.ACACIA));
    public static final Block CRIM_STAIRS = registerBlock("crim_stairs",
            new StairsBlock(ModBlocks.CRIM_PLANKS.getDefaultState(),FabricBlockSettings.copyOf(Blocks.OAK_STAIRS)));
    public static final Block CRIM_SLAB = registerBlock("crim_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.OAK_SLAB)));
    public static final Block CRIM_BUTTON = registerBlock("crim_button",
            new ButtonBlock(FabricBlockSettings.copyOf(Blocks.OAK_BUTTON), BlockSetType.IRON, 10, true));
    public static final Block CRIM_PRESSURE_PLATE = registerBlock("crim_pressure_plate",
            new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING,FabricBlockSettings.copyOf(Blocks.OAK_PRESSURE_PLATE),BlockSetType.IRON));
    public static final Block CRIM_FENCE = registerBlock("crim_fence",
            new FenceBlock(FabricBlockSettings.copyOf(Blocks.OAK_FENCE)));
    public static final Block CRIM_FENCE_GATE = registerBlock("crim_fence_gate",
            new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.OAK_FENCE_GATE), WoodType.ACACIA));
    public static final Block PEARL_STAIRS = registerBlock("pearl_stairs",
            new StairsBlock(ModBlocks.PEARL_PLANKS.getDefaultState(),FabricBlockSettings.copyOf(Blocks.OAK_STAIRS)));
    public static final Block PEARL_SLAB = registerBlock("pearl_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.OAK_SLAB)));
    public static final Block PEARL_BUTTON = registerBlock("pearl_button",
            new ButtonBlock(FabricBlockSettings.copyOf(Blocks.OAK_BUTTON), BlockSetType.IRON, 10, true));
    public static final Block PEARL_PRESSURE_PLATE = registerBlock("pearl_pressure_plate",
            new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING,FabricBlockSettings.copyOf(Blocks.OAK_PRESSURE_PLATE),BlockSetType.IRON));
    public static final Block PEARL_FENCE = registerBlock("pearl_fence",
            new FenceBlock(FabricBlockSettings.copyOf(Blocks.OAK_FENCE)));
    public static final Block PEARL_FENCE_GATE = registerBlock("pearl_fence_gate",
            new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.OAK_FENCE_GATE), WoodType.ACACIA));

    //ダンジョン用
    public static final Block DUNGEON_TILE_GREEN_FRAGILE = registerBlock("dungeon_tile_green_fragile",
            new FragileBlock(FabricBlockSettings.copyOf(Blocks.BRICKS)));
    public static final Block DUNGEON_TILE_GREEN = registerBlock("dungeon_tile_green",
            new Block(FabricBlockSettings.copyOf(Blocks.STONE)));
    public static final Block DUNGEON_TILE_GREEN_STAIRS = registerBlock("dungeon_tile_green_stairs",
            new StairsBlock(ModBlocks.PEARL_PLANKS.getDefaultState(),FabricBlockSettings.copyOf(Blocks.STONE_STAIRS)));
    public static final Block DUNGEON_TILE_GREEN_SLAB = registerBlock("dungeon_tile_green_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.STONE_SLAB)));
    public static final Block DUNGEON_TILE_GREEN_WALL = registerBlock("dungeon_tile_green_wall",
            new WallBlock(FabricBlockSettings.copyOf(Blocks.STONE_BRICK_WALL)));

    public static final Block DUNGEON_BRICK_GREEN_FRAGILE = registerBlock("dungeon_brick_green_fragile",
            new FragileBlock(FabricBlockSettings.copyOf(Blocks.BRICKS)));
    public static final Block DUNGEON_BRICK_GREEN = registerBlock("dungeon_brick_green",
            new Block(FabricBlockSettings.copyOf(Blocks.STONE)));
    public static final Block DUNGEON_BRICK_GREEN_STAIRS = registerBlock("dungeon_brick_green_stairs",
            new StairsBlock(ModBlocks.PEARL_PLANKS.getDefaultState(),FabricBlockSettings.copyOf(Blocks.STONE_STAIRS)));
    public static final Block DUNGEON_BRICK_GREEN_SLAB = registerBlock("dungeon_brick_green_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.STONE_SLAB)));
    public static final Block DUNGEON_BRICK_GREEN_WALL = registerBlock("dungeon_brick_green_wall",
            new WallBlock(FabricBlockSettings.copyOf(Blocks.STONE_BRICK_WALL)));

    public static final  Block SPIKE_BLOCK = registerBlock("spike_block",
            new SpikeBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).nonOpaque()));

    public static Block getSlab(String materialName) {
        return switch (materialName.toLowerCase()) {
            case "ebon" -> EBON_SLAB;
            case "crim" -> CRIM_SLAB;
            case "pearl" -> PEARL_SLAB;
            default -> throw new IllegalArgumentException("Unknown material: " + materialName);
        };
    }
    public static Block getStairs(String materialName) {
        return switch (materialName.toLowerCase()) {
            case "ebon" -> EBON_STAIRS;
            case "crim" -> CRIM_STAIRS;
            case "pearl" -> PEARL_STAIRS;
            default -> throw new IllegalArgumentException("Unknown material: " + materialName);
        };
    }
    public static Block getFence(String materialName) {
        return switch (materialName.toLowerCase()) {
            case "ebon" -> EBON_FENCE;
            case "crim" -> CRIM_FENCE;
            case "pearl" -> PEARL_FENCE;
            default -> throw new IllegalArgumentException("Unknown material: " + materialName);
        };
    }
    public static Block getFenceGate(String materialName) {
        return switch (materialName.toLowerCase()) {
            case "ebon" -> EBON_FENCE_GATE;
            case "crim" -> CRIM_FENCE_GATE;
            case "pearl" -> PEARL_FENCE_GATE;
            default -> throw new IllegalArgumentException("Unknown material: " + materialName);
        };
    }
    public static Block getButton(String materialName) {
        return switch (materialName.toLowerCase()) {
            case "ebon" -> EBON_BUTTON;
            case "crim" -> CRIM_BUTTON;
            case "pearl" -> PEARL_BUTTON;
            default -> throw new IllegalArgumentException("Unknown material: " + materialName);
        };
    }
    public static Block getPressurePlate(String materialName) {
        return switch (materialName.toLowerCase()) {
            case "ebon" -> EBON_PRESSURE_PLATE;
            case "crim" -> CRIM_PRESSURE_PLATE;
            case "pearl" -> PEARL_PRESSURE_PLATE;
            default -> throw new IllegalArgumentException("Unknown material: " + materialName);
        };
    }
    public static Block getWood(String materialName) {
        return switch (materialName.toLowerCase()) {
            case "ebon" -> EBON_WOOD;
            case "crim" -> CRIM_WOOD;
            case "pearl" -> PEARL_WOOD;
            case "stripped_ebon" -> STRIPPED_EBON_WOOD;
            case "stripped_crim" -> STRIPPED_CRIM_WOOD;
            case "stripped_pearl" -> STRIPPED_PEARL_WOOD;
            default -> throw new IllegalArgumentException("Unknown material: " + materialName);
        };
    }
    public static Block getPlanks(String materialName) {
        return switch (materialName.toLowerCase()) {
            case "ebon", "ebon_wood", "stripped_ebon", "stripped_ebon_wood" -> EBON_PLANKS;
            case "crim", "crim_wood", "stripped_crim", "stripped_crim_wood" -> CRIM_PLANKS;
            case "pearl", "pearl_wood", "stripped_pearl", "stripped_pearl_wood" -> PEARL_PLANKS;
            default -> throw new IllegalArgumentException("Unknown material: " + materialName);
        };
    }


    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MOD_ID, name), block);
    }
    private static Item registerBlockItem(String name, Block block){
        return Registry.register(Registries.ITEM, new Identifier(TerrariaMod.MOD_ID,name),
                new BlockItem(block, new FabricItemSettings()));
    }
    public static void registerModBlocks(){
        TerrariaMod.LOGGER.info("Registering ModBlocks for " + TerrariaMod.MOD_ID);
    }
}
