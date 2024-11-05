package ricky.terrariamod.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class InfiniteWaterBucketItem extends BucketItem {

    public InfiniteWaterBucketItem(Item.Settings settings) {
        super(Fluids.WATER, settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        Direction direction = context.getSide();
        BlockPos offsetPos = pos.offset(direction);

        if (!world.canPlayerModifyAt(player, pos) || !player.canPlaceOn(offsetPos, direction, context.getStack())) {
            return ActionResult.FAIL;
        }

        BlockHitResult blockHitResult = new BlockHitResult(context.getHitPos(), direction, pos, false);

        if (this.placeFluid(player, world, offsetPos, blockHitResult)) {
            this.onEmptied(player, world, context.getStack(), offsetPos);
            player.incrementStat(Stats.USED.getOrCreateStat(this));
            return ActionResult.SUCCESS;
        }

        return ActionResult.FAIL;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        BlockHitResult blockHitResult = BucketItem.raycast(world, user, RaycastContext.FluidHandling.SOURCE_ONLY);
        if (blockHitResult.getType() == HitResult.Type.MISS) {
            return TypedActionResult.pass(itemStack);
        } else if (blockHitResult.getType() == HitResult.Type.BLOCK) {
            BlockPos blockPos = blockHitResult.getBlockPos();
            if (!world.canPlayerModifyAt(user, blockPos) || !user.canPlaceOn(blockPos, blockHitResult.getSide(), itemStack)) {
                return TypedActionResult.fail(itemStack);
            } else if (this.placeFluid(user, world, blockPos, blockHitResult)) {
                this.onEmptied(user, world, itemStack, blockPos);
                user.incrementStat(Stats.USED.getOrCreateStat(this));
                return TypedActionResult.success(itemStack);
            }
        }

        return TypedActionResult.fail(itemStack);
    }
}
