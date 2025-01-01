package ricky.terrariamod.item.tools;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ModPickaxeItem extends PickaxeItem {
    public int r;

    public ModPickaxeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings,int r) {
        super(material, attackDamage, attackSpeed, settings);
        this.r = r;
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, net.minecraft.entity.LivingEntity miner) {
        if (!world.isClient && miner instanceof net.minecraft.entity.player.PlayerEntity) {
            // 範囲破壊処理を実行
            breakAdjacentBlocks((ServerWorld) world, pos, stack, (net.minecraft.entity.player.PlayerEntity) miner);
        }
        return super.postMine(stack, world, state, pos, miner);
    }

    private void breakAdjacentBlocks(ServerWorld world, BlockPos center, ItemStack stack, net.minecraft.entity.player.PlayerEntity player) {
        // 半径1ブロックの範囲を探索
        for (int dx = -r; dx <= r; dx++) {
            for (int dy = -r; dy <= r; dy++) {
                for (int dz = -r; dz <= r; dz++) {
                    BlockPos targetPos = center.add(dx, dy, dz);
                    if (targetPos.equals(center)) continue; // 中心は無視
                    BlockState targetState = world.getBlockState(targetPos);
                    Block targetBlock = targetState.getBlock();

                    // 空気や岩盤は破壊しない
                    if (targetBlock.getHardness() < 0 || targetState.isAir()) continue;

                    // シルクタッチや通常破壊の処理
                    if (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) > 0) {
                        // シルクタッチの効果を適用してアイテムをドロップ
                        targetBlock.afterBreak(world, player, targetPos, targetState, null, stack);
                        world.breakBlock(targetPos, false, player);
                    } else {
                        // 通常のドロップ
                        world.breakBlock(targetPos, true, player);
                    }
                }
            }
        }
    }
}
