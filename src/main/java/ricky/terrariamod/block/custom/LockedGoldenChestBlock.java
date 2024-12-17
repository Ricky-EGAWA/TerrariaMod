package ricky.terrariamod.block.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ricky.terrariamod.TerrariaMod;
import ricky.terrariamod.block.ModBlocks;
import ricky.terrariamod.item.ModItems;

public class LockedGoldenChestBlock extends ChestBlock {
    public LockedGoldenChestBlock(Settings settings) {
        super(settings, () -> ModBlockEntities.LOCKED_GOLDEN_CHEST_ENTITY);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new LockedGoldenChestBlockEntity(pos, state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) { // サーバー側で処理
            ItemStack heldItem = player.getStackInHand(hand);

            // 金の鍵を持っている場合
            if (heldItem.getItem() == ModItems.GOLDEN_KEY) {
                // チェストに置き換える
                player.sendMessage(Text.of("Unlocked!"));
                world.setBlockState(pos, ModBlocks.GOLDEN_CHEST.getDefaultState().with(Properties.HORIZONTAL_FACING, state.get(Properties.HORIZONTAL_FACING)), 3);

                // ブロックエンティティの更新
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (blockEntity instanceof GoldenChestBlockEntity chestBlockEntity) {
                    chestBlockEntity.setLootTable(new Identifier(TerrariaMod.MOD_ID, "chests/locked_golden_chest"), player.getRandom().nextLong());
                    chestBlockEntity.markDirty(); // ブロックエンティティの変更を通知
                }

                // クライアントとサーバーの同期
                world.updateListeners(pos, state, world.getBlockState(pos), 3);

                // 鍵を消費（クリエイティブモードを除く）
                if (!player.isCreative()) {
                    heldItem.decrement(1);
                }

                // サウンドを再生
                world.playSound(null, pos, SoundEvents.BLOCK_CHEST_OPEN, SoundCategory.BLOCKS, 1.0F, 1.0F);

                return ActionResult.SUCCESS;
            } else {
                // プレイヤーに通知
                player.sendMessage(Text.of("This chest is locked!"));
                return ActionResult.CONSUME;
            }
        }

        return ActionResult.SUCCESS;
    }

}
