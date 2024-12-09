package ricky.terrariamod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FragileBlock extends Block {
    public FragileBlock(Settings settings) {
        super(settings);
    }
    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        super.onSteppedOn(world, pos, state, entity);

        // プレイヤーが上に乗ったか確認
        if (!world.isClient && entity instanceof PlayerEntity) {
            // ブロックを壊す
            world.breakBlock(pos, true);

            // 必要なら音を再生
            world.playSound(null, pos, SoundEvents.BLOCK_STONE_BREAK, SoundCategory.BLOCKS, 1.0f, 1.0f);
        }
    }
}
