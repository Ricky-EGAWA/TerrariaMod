package ricky.terrariamod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import ricky.terrariamod.block.ModBlocks;
import ricky.terrariamod.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ModTags.Blocks.METAL_DETECTOR_DETECTABLE_BLOCKS)
                .add(ModBlocks.COBALT_ORE)
                .add(ModBlocks.DEEPSLATE_COBALT_ORE)
                .forceAddTag(BlockTags.GOLD_ORES)
                .forceAddTag(BlockTags.EMERALD_ORES)
                .forceAddTag(BlockTags.REDSTONE_ORES)
                .forceAddTag(BlockTags.LAPIS_ORES)
                .forceAddTag(BlockTags.DIAMOND_ORES)
                .forceAddTag(BlockTags.IRON_ORES)
                .forceAddTag(BlockTags.COPPER_ORES)
                .forceAddTag(BlockTags.COAL_ORES);

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.COBALT_ORE)
                .add(ModBlocks.DEEPSLATE_COBALT_ORE)
                .add(ModBlocks.COBALT_BLOCK)
                .add(ModBlocks.ORICHALCUM_ORE)
                .add(ModBlocks.ORICHALCUM_BLOCK)
                .add(ModBlocks.ADAMANTITE_ORE)
                .add(ModBlocks.ADAMANTITE_BLOCK)
                .add(ModBlocks.HELLSTONE_ORE)
                .add(ModBlocks.HELLSTONE_BLOCK);

        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.SOUND_BLOCK);

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.COBALT_BLOCK)
                .add(ModBlocks.ORICHALCUM_BLOCK)
                .add(ModBlocks.ADAMANTITE_BLOCK);

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.HELLSTONE_BLOCK);

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, new Identifier("fabric", "needs_tool_level_4")))
                .add(ModBlocks.COBALT_ORE);

    }
}
