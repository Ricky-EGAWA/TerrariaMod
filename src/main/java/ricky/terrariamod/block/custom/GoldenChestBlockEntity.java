package ricky.terrariamod.block.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.util.math.BlockPos;

public class GoldenChestBlockEntity extends ChestBlockEntity {
    public GoldenChestBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.GOLDEN_CHEST_ENTITY, blockPos, blockState);
    }
}
