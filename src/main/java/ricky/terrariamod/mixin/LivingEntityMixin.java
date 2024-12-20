package ricky.terrariamod.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ricky.terrariamod.item.AttackableItem;
import ricky.terrariamod.item.custom.CobaltShieldItem;

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
}
