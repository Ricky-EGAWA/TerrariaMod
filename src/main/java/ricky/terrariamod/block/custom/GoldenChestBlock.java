package ricky.terrariamod.block.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class GoldenChestBlock extends ChestBlock {
    public GoldenChestBlock(Settings settings) {
        super(settings, () -> ModBlockEntities.GOLDEN_CHEST_ENTITY);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new GoldenChestBlockEntity(pos, state);
    }
}
