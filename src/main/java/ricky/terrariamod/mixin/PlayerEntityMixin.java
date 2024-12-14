package ricky.terrariamod.mixin;

import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ricky.terrariamod.effect.ModEffects;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
    @Inject(method = "canFoodHeal", at = @At("HEAD"), cancellable = true)
    private void onHandleInputEvents(CallbackInfoReturnable<Boolean> cir) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        // 出血効果がある場合、常に false を返す
        if (player.hasStatusEffect(ModEffects.BLEEDING)) {
            cir.setReturnValue(false);
        }
    }
}
