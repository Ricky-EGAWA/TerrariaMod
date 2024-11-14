package ricky.terrariamod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
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
        addDrop(ModBlocks.VILE_MUSHROOM);
        addPottedPlantDrops(ModBlocks.POTTED_VILE_MUSHROOM);
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
