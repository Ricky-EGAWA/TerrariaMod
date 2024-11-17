package ricky.terrariamod.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CactusBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ricky.terrariamod.block.ModBlocks;

import java.util.Iterator;

@Mixin(CactusBlock.class)
public abstract class CactusBlockMixin {

    @Inject(method = "canPlaceAt", at = @At("HEAD"), cancellable = true)
    public void canPlaceAt(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        Iterator var4 = Direction.Type.HORIZONTAL.iterator();
        Block blockBelow = world.getBlockState(pos.down()).getBlock();
        // `Ebon Sand` の上にもサボテンが生成可能にする
        if (!var4.hasNext()) {
            if ((blockBelow == ModBlocks.EBON_SAND || blockBelow == ModBlocks.CRIM_SAND || blockBelow == ModBlocks.PEARL_SAND)){
                cir.setReturnValue(true);
            }
        }
    }
}
