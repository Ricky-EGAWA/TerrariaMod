package ricky.terrariamod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import ricky.terrariamod.block.ModBlocks;
import ricky.terrariamod.item.ModItems;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.COBALT_BLOCK);
        addDrop(ModBlocks.ORICHALCUM_BLOCK);
        addDrop(ModBlocks.ADAMANTITE_BLOCK);
        addDrop(ModBlocks.HELLSTONE_BLOCK);

        addDrop(ModBlocks.COBALT_ORE, copperLikeOreDrops(ModBlocks.COBALT_ORE, ModItems.COBALT_RAW));
        addDrop(ModBlocks.DEEPSLATE_COBALT_ORE, copperLikeOreDrops(ModBlocks.DEEPSLATE_COBALT_ORE, ModItems.COBALT_RAW));
        addDrop(ModBlocks.ORICHALCUM_ORE, copperLikeOreDrops(ModBlocks.ORICHALCUM_ORE, ModItems.ORICHALCUM_RAW));
        addDrop(ModBlocks.ADAMANTITE_ORE, copperLikeOreDrops(ModBlocks.ADAMANTITE_ORE, ModItems.ADAMANTITE_RAW));
        addDrop(ModBlocks.HELLSTONE_ORE, copperLikeOreDrops(ModBlocks.HELLSTONE_ORE, ModItems.HELLSTONE_RAW));

        addDrop(ModBlocks.EBON_STONE);
        addDrop(ModBlocks.EBON_ICE);
        addDrop(ModBlocks.EBON_SAND);
        addDrop(ModBlocks.EBON_SANDSTONE);
        addDrop(ModBlocks.CRIM_STONE);
        addDrop(ModBlocks.CRIM_ICE);
        addDrop(ModBlocks.CRIM_SAND);
        addDrop(ModBlocks.CRIM_SANDSTONE);
        addDrop(ModBlocks.PEARL_STONE);
        addDrop(ModBlocks.PEARL_ICE);
        addDrop(ModBlocks.PEARL_SAND);
        addDrop(ModBlocks.PEARL_SANDSTONE);
        addDrop(ModBlocks.DEATH_WEED);
        addPottedPlantDrops(ModBlocks.POTTED_DEATH_WEED);
        addDrop(ModBlocks.SHIVER_THORN);
        addPottedPlantDrops(ModBlocks.POTTED_SHIVER_THORN);
        addDrop(ModBlocks.VILE_MUSHROOM);
        addPottedPlantDrops(ModBlocks.POTTED_VILE_MUSHROOM);
        addDrop(ModBlocks.VICIOUS_MUSHROOM);
        addPottedPlantDrops(ModBlocks.POTTED_VICIOUS_MUSHROOM);
        addDrop(ModBlocks.GLOWING_MUSHROOM);
        addPottedPlantDrops(ModBlocks.POTTED_GLOWING_MUSHROOM);
        addDrop(ModBlocks.GLOWING_MUSHROOM_BLOCK);
        addDrop(ModBlocks.GLOWING_MUSHROOM_STEM);
        addDrop(ModBlocks.GLOWING_MOSS);
        addDrop(ModBlocks.ICICLE);

        addDrop(ModBlocks.EBON_LOG);
        addDrop(ModBlocks.EBON_WOOD);
        addDrop(ModBlocks.STRIPPED_EBON_LOG);
        addDrop(ModBlocks.STRIPPED_EBON_WOOD);
        addDrop(ModBlocks.EBON_PLANKS);
        addDrop(ModBlocks.EBON_SAPLING);
        addDrop(ModBlocks.EBON_LEAVES, leavesDrops(ModBlocks.EBON_LEAVES, ModBlocks.EBON_SAPLING, 0.0025f));
        addDrop(ModBlocks.CRIM_LOG);
        addDrop(ModBlocks.CRIM_WOOD);
        addDrop(ModBlocks.STRIPPED_CRIM_LOG);
        addDrop(ModBlocks.STRIPPED_CRIM_WOOD);
        addDrop(ModBlocks.CRIM_PLANKS);
        addDrop(ModBlocks.CRIM_SAPLING);
        addDrop(ModBlocks.CRIM_LEAVES, leavesDrops(ModBlocks.CRIM_LEAVES, ModBlocks.CRIM_SAPLING, 0.0025f));
        addDrop(ModBlocks.PEARL_LOG);
        addDrop(ModBlocks.PEARL_WOOD);
        addDrop(ModBlocks.STRIPPED_PEARL_LOG);
        addDrop(ModBlocks.STRIPPED_PEARL_WOOD);
        addDrop(ModBlocks.PEARL_PLANKS);
        addDrop(ModBlocks.PEARL_SAPLING);
        addDrop(ModBlocks.PEARL_LEAVES, leavesDrops(ModBlocks.PEARL_LEAVES, ModBlocks.PEARL_SAPLING, 0.0025f));

        addDrop(ModBlocks.EBON_STAIRS);
        addDrop(ModBlocks.EBON_SLAB);
        addDrop(ModBlocks.EBON_BUTTON);
        addDrop(ModBlocks.EBON_PRESSURE_PLATE);
        addDrop(ModBlocks.EBON_FENCE);
        addDrop(ModBlocks.EBON_FENCE_GATE);
        addDrop(ModBlocks.CRIM_STAIRS);
        addDrop(ModBlocks.CRIM_SLAB);
        addDrop(ModBlocks.CRIM_BUTTON);
        addDrop(ModBlocks.CRIM_PRESSURE_PLATE);
        addDrop(ModBlocks.CRIM_FENCE);
        addDrop(ModBlocks.CRIM_FENCE_GATE);
        addDrop(ModBlocks.PEARL_STAIRS);
        addDrop(ModBlocks.PEARL_SLAB);
        addDrop(ModBlocks.PEARL_BUTTON);
        addDrop(ModBlocks.PEARL_PRESSURE_PLATE);
        addDrop(ModBlocks.PEARL_FENCE);
        addDrop(ModBlocks.PEARL_FENCE_GATE);

        addDrop(ModBlocks.DUNGEON_TILE_GREEN);
        addDrop(ModBlocks.DUNGEON_TILE_GREEN_SLAB);
        addDrop(ModBlocks.DUNGEON_TILE_GREEN_STAIRS);
        addDrop(ModBlocks.DUNGEON_TILE_GREEN_WALL);
        addDrop(ModBlocks.DUNGEON_BRICK_GREEN);
        addDrop(ModBlocks.DUNGEON_BRICK_GREEN_SLAB);
        addDrop(ModBlocks.DUNGEON_BRICK_GREEN_STAIRS);
        addDrop(ModBlocks.DUNGEON_BRICK_GREEN_WALL);
        addDrop(ModBlocks.SPIKE_BLOCK);
        addDrop(ModBlocks.GOLDEN_CHEST);

    }
    public LootTable.Builder copperLikeOreDrops(Block drop, Item item) {
        return BlockLootTableGenerator.dropsWithSilkTouch(drop, (LootPoolEntry.Builder)this.applyExplosionDecay(drop,
                ((LeafEntry.Builder)
                        ItemEntry.builder(item)
                        .apply(SetCountLootFunction
                                .builder(UniformLootNumberProvider.create(1.0f, 2.0f))))
                        .apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))));
    }
}
