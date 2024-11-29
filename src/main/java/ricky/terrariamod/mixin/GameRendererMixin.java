package ricky.terrariamod.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ricky.terrariamod.item.bows.SniperRifleItem;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {

    @Shadow
    private MinecraftClient client;

    @Inject(method = "getFov", at = @At("HEAD"), cancellable = true)
    private void modifyFov(Camera camera, float tickDelta, boolean changingFov, CallbackInfoReturnable<Double> info) {
        // プレイヤーがスナイパーライフルを使用している場合にズーム
        if (client.player != null && client.player.isUsingItem()) {
            ItemStack activeItem = client.player.getActiveItem();
            if (activeItem.getItem() instanceof SniperRifleItem) {
                // ズームFOVを設定
                info.setReturnValue(10.0); // ズーム時のFOVを指定
            }
        }
    }
}
