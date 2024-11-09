package ricky.terrariamod.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import ricky.terrariamod.util.ModTags;

import java.util.List;

public class MetalDetectorItem extends Item {

    public MetalDetectorItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        // クライアントサイドのみで処理
        if (world.isClient && entity instanceof PlayerEntity player) {
            // プレイヤーを中心に 5 ブロックの範囲をスキャン
            boolean foundBlock = false;
            BlockPos playerPos = player.getBlockPos();

            for (int x = -5; x <= 5; x++) {
                for (int y = -5; y <= 5; y++) {
                    for (int z = -5; z <= 5; z++) {
                        BlockPos scanPos = playerPos.add(x, y, z);
                        BlockState state = world.getBlockState(scanPos);

                        // 鉱石ブロックかどうか判定
                        if (isValuableBlock(state)) {
                            outValuableCoordinates(scanPos, player, state.getBlock());
                            foundBlock = true;
                            break;
                        }
                    }
                    if (foundBlock) break;
                }
                if (foundBlock) break;
            }

            // 鉱石が見つからなかった場合
            if (!foundBlock) {
                player.sendMessage(Text.literal("No Valuables Nearby!"), true);
            }
        }
    }


    private void outValuableCoordinates(BlockPos blockPos, PlayerEntity player, Block block) {
        player.sendMessage(Text.literal("Found " + block.asItem().getName().getString() + " at (" +
                blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + ")"), true);
    }

    private boolean isValuableBlock(BlockState state) {
        return state.isIn(ModTags.Blocks.METAL_DETECTOR_DETECTABLE_BLOCKS);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.terrariamod.metal_detector.tooltip"));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
