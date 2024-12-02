package ricky.terrariamod.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.input.KeyboardInput;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.util.InputUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ricky.terrariamod.effect.ModEffects;

@Mixin(KeyboardInput.class)
public abstract class KeyboardInputMixin {
    private static boolean keysSwapped = false;

    @Inject(method = "tick", at = @At("HEAD"))
    public void modifyKeyBindings(boolean slowDown, float slowDownFactor, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        ClientPlayerEntity player = client.player;

        if (player != null && player.hasStatusEffect(ModEffects.CONFUSED)) {
            if (!keysSwapped) {
                // キー割り当てを反転
                swapKeys(client);
                keysSwapped = true;
            }
        } else if (keysSwapped) {
            // キー割り当てを元に戻す
            resetKeys(client);
            keysSwapped = false;
        }
    }

    private void swapKeys(MinecraftClient client) {
        KeyBinding forwardKey = client.options.forwardKey;
        KeyBinding backKey = client.options.backKey;

        KeyBinding leftKey = client.options.leftKey;
        KeyBinding rightKey = client.options.rightKey;


        // 前後入れ替え
        InputUtil.Key tempKey = forwardKey.getDefaultKey();
        forwardKey.setBoundKey(backKey.getDefaultKey());
        backKey.setBoundKey(tempKey);

        // 左右入れ替え
        InputUtil.Key tempKey2 = leftKey.getDefaultKey();
        leftKey.setBoundKey(rightKey.getDefaultKey());
        rightKey.setBoundKey(tempKey2);

        KeyBinding.updateKeysByCode();
    }

    private void resetKeys(MinecraftClient client) {
        KeyBinding forwardKey = client.options.forwardKey;
        KeyBinding backKey = client.options.backKey;
        KeyBinding leftKey = client.options.leftKey;
        KeyBinding rightKey = client.options.rightKey;

        // デフォルトキーに戻す
        forwardKey.setBoundKey(forwardKey.getDefaultKey());
        backKey.setBoundKey(backKey.getDefaultKey());
        leftKey.setBoundKey(leftKey.getDefaultKey());
        rightKey.setBoundKey(rightKey.getDefaultKey());

        KeyBinding.updateKeysByCode();
    }
}
