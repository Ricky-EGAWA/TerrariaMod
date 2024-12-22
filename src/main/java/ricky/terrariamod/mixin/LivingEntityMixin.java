package ricky.terrariamod.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ricky.terrariamod.effect.ModEffects;
import ricky.terrariamod.item.AttackableItem;
import ricky.terrariamod.item.accessories.AccessoriesItem;
import ricky.terrariamod.item.accessories.AdhesiveBandageItem;
import ricky.terrariamod.item.accessories.CobaltShieldItem;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Inject(method = "swingHand", at = @At("HEAD"), cancellable = true)
    private void onHandleInputEvents(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;

        // entity が PlayerEntity のインスタンスか確認
        if (entity instanceof PlayerEntity player) {
            ItemStack mainHandItem = player.getMainHandStack();

            // アイテムが AttackableItem を実装している場合
            if (mainHandItem.getItem() instanceof AttackableItem attackableItem) {
                attackableItem.attack(player.getWorld(), player, Hand.MAIN_HAND);
                ci.cancel(); // 通常の手を振る動作をキャンセル
            }
        }
    }
    @Inject(method = "takeKnockback", at = @At("HEAD"), cancellable = true)
    private void disableKnockback(double strength, double x, double z, CallbackInfo ci) {
        if ((Object) this instanceof PlayerEntity player) {
            for (ItemStack stack : player.getInventory().main) {
                if (stack.getItem() instanceof CobaltShieldItem) {
                    ci.cancel(); // ノックバック処理をキャンセル
                }
            }
        }
    }
    @Inject(method = "canHaveStatusEffect", at = @At("HEAD"), cancellable = true)
    private void cancelEffect(StatusEffectInstance effect, CallbackInfoReturnable<Boolean> cir) {
        if ((Object) this instanceof PlayerEntity player) {
            //アクセサリによりデバフを無効化
            StatusEffect statusEffect = effect.getEffectType();
            for (ItemStack stack : player.getInventory().main) {
                if (stack.getItem() instanceof AccessoriesItem accessoriesItem) {
                    if (accessoriesItem.cancelEffect(statusEffect)){
                        cir.cancel();
                    }
                }
            }
        }
    }
}
