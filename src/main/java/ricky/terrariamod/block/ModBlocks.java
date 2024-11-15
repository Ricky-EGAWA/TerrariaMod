package ricky.terrariamod.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.TreeConfiguredFeatures;
import ricky.terrariamod.TerrariaMod;
import ricky.terrariamod.block.custom.SoundBlock;
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
    public static final Block VILE_MUSHROOM = registerBlock("vile_mushroom",
            new FlowerBlock(StatusEffects.INSTANT_DAMAGE,10,FabricBlockSettings.copyOf(Blocks.BROWN_MUSHROOM)));
    public static final Block POTTED_VILE_MUSHROOM = Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MOD_ID, "potted_vile_mushroom"),
            new FlowerPotBlock(VILE_MUSHROOM, FabricBlockSettings.copyOf(Blocks.POTTED_ALLIUM).nonOpaque()));
    public static final Block VICIOUS_MUSHROOM = registerBlock("vicious_mushroom",
            new FlowerBlock(StatusEffects.INSTANT_DAMAGE,10,FabricBlockSettings.copyOf(Blocks.BROWN_MUSHROOM)));
    public static final Block POTTED_VICIOUS_MUSHROOM = Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MOD_ID, "potted_vicious_mushroom"),
            new FlowerPotBlock(VICIOUS_MUSHROOM, FabricBlockSettings.copyOf(Blocks.POTTED_ALLIUM).nonOpaque()));
    public static final Block GLOWING_MUSHROOM = registerBlock("glowing_mushroom",
            new MushroomPlantBlock(FabricBlockSettings.copyOf(Blocks.BROWN_MUSHROOM), ModConfiguredFeatures.HUGE_GLOWING_MUSHROOM));
    public static final Block POTTED_GLOWING_MUSHROOM = Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MOD_ID, "potted_glowing_mushroom"),
            new FlowerPotBlock(GLOWING_MUSHROOM, FabricBlockSettings.copyOf(Blocks.POTTED_ALLIUM).nonOpaque()));
    public static final Block GLOWING_MUSHROOM_BLOCK = registerBlock("glowing_mushroom_block",
            new MushroomBlock(FabricBlockSettings.copyOf(Blocks.RED_MUSHROOM_BLOCK)));
    public static final Block GLOWING_MUSHROOM_STEM = registerBlock("glowing_mushroom_stem",
            new MushroomBlock(FabricBlockSettings.copyOf(Blocks.MUSHROOM_STEM)));
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
