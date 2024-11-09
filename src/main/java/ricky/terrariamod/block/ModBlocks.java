package ricky.terrariamod.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import ricky.terrariamod.TerrariaMod;
import ricky.terrariamod.block.custom.SoundBlock;

public class ModBlocks {
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
            new Block(FabricBlockSettings.copyOf(Blocks.DIAMOND_ORE).strength(50f,1200f)));
    public static final Block DEEPSLATE_ORICHALCUM_ORE = registerBlock("deepslate_orichalcum_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DIAMOND_ORE).strength(50f,1200f), UniformIntProvider.create(2,5)));
    public static final Block ADAMANTITE_BLOCK = registerBlock("adamantite_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block ADAMANTITE_ORE = registerBlock("adamantite_ore",
            new Block(FabricBlockSettings.copyOf(Blocks.DIAMOND_ORE).strength(50f,1200f)));
    public static final Block DEEPSLATE_ADAMANTITE_ORE = registerBlock("deepslate_adamantite_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DIAMOND_ORE).strength(50f,1200f), UniformIntProvider.create(2,5)));
    public static final Block HELLSTONE_BLOCK = registerBlock("hellstone_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block HELLSTONE_ORE = registerBlock("hellstone_ore",
            new Block(FabricBlockSettings.copyOf(Blocks.DIAMOND_ORE).strength(50f,1200f)));

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
