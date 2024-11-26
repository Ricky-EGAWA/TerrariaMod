package ricky.terrariamod.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ricky.terrariamod.item.ModItems;

@Mixin(GameRenderer.class)
public class TelescopeZoomMixin {
    @Inject(method = "getFov", at = @At("HEAD"), cancellable = true)
    private void modifyFov(Camera camera, float tickDelta, boolean changingFov, CallbackInfoReturnable<Double> cir) {
        if (MinecraftClient.getInstance().player != null) {
            ItemStack itemStack = MinecraftClient.getInstance().player.getMainHandStack();
            if (itemStack.getItem() == ModItems.SNIPER_RIFLE) {
                cir.setReturnValue(30.0); // 望遠鏡使用中はFOVを30に変更
            }
        }
    }
}
