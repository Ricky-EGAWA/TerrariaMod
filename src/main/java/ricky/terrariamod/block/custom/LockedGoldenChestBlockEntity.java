package ricky.terrariamod.block.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.util.math.BlockPos;

public class LockedGoldenChestBlockEntity extends ChestBlockEntity {
    public LockedGoldenChestBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.LOCKED_GOLDEN_CHEST_ENTITY, blockPos, blockState);
    }
}
