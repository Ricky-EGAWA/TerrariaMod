package ricky.terrariamod.block.custom;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import ricky.terrariamod.TerrariaMod;
import ricky.terrariamod.block.ModBlocks;


public class ModBlockEntities {
    public static BlockEntityType<GoldenChestBlockEntity> GOLDEN_CHEST_ENTITY;
    public static BlockEntityType<LockedGoldenChestBlockEntity> LOCKED_GOLDEN_CHEST_ENTITY;

    public static void registerBlockEntities() {
        GOLDEN_CHEST_ENTITY = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier(TerrariaMod.MOD_ID, "entity/chest/golden_chest"),
                FabricBlockEntityTypeBuilder.create(GoldenChestBlockEntity::new, ModBlocks.GOLDEN_CHEST).build()
        );
        LOCKED_GOLDEN_CHEST_ENTITY = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier(TerrariaMod.MOD_ID, "locked_golden_chest"),
                FabricBlockEntityTypeBuilder.create(LockedGoldenChestBlockEntity::new, ModBlocks.LOCKED_GOLDEN_CHEST).build()
        );
    }
}
