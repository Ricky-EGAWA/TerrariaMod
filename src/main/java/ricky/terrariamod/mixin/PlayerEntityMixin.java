package ricky.terrariamod.mixin;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ricky.terrariamod.effect.ModEffects;
import ricky.terrariamod.item.ModItems;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntityMixin{
    @Inject(method = "canFoodHeal", at = @At("HEAD"), cancellable = true)
    private void onHandleInputEvents(CallbackInfoReturnable<Boolean> cir) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        // 出血効果がある場合、常に false を返す
        if (player.hasStatusEffect(ModEffects.BLEEDING)) {
            cir.setReturnValue(false);
        }
    }


    @Shadow
    public abstract ItemStack getEquippedStack(EquipmentSlot slot);

    @Shadow
    public abstract void startFallFlying();

    @Inject(method = "checkFallFlying", at = @At(value = "INVOKE_ASSIGN",
            target = "Lnet/minecraft/entity/player/PlayerEntity;getEquippedStack(Lnet/minecraft/entity/EquipmentSlot;)Lnet/minecraft/item/ItemStack;"),
            cancellable = true)
    public void checkFallFlying(CallbackInfoReturnable<Boolean> cir) {
        ItemStack itemStack = this.getEquippedStack(EquipmentSlot.CHEST);
        System.out.println("mixin");
        if ((itemStack.isOf(ModItems.FLAME_WING))) {
            this.startFallFlying();
            cir.setReturnValue(true);
            cir.cancel();
        }
    }
}
