package ricky.terrariamod.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import ricky.terrariamod.TerrariaMod;

public class ModTags {
    public static class Blocks{
        public static final TagKey<Block> METAL_DETECTOR_DETECTABLE_BLOCKS =
                create("metal_detector_detectable_blocks");
        private static TagKey<Block> create(String name) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(TerrariaMod.MOD_ID, name));
        }
    }
    public static class Items{
        private static TagKey<Item> create(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(TerrariaMod.MOD_ID, name));
        }
    }
}
