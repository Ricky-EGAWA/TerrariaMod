package ricky.terrariamod.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ricky.terrariamod.item.gun.ShotgunItem;
import ricky.terrariamod.item.gun.SniperRifleItem;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {//TODO 反転してない
    @Inject(method = "swingHand", at = @At("HEAD"), cancellable = true)
    private void onHandleInputEvents(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;

        // entity が PlayerEntity のインスタンスか確認
        if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity; // プレイヤーとしてキャスト

            // プレイヤーが Shotgun を持っているか確認
            if (player.getMainHandStack().getItem() instanceof ShotgunItem) {
                // ショットガンを発射
                ShotgunItem shotgun = (ShotgunItem) player.getMainHandStack().getItem();
                shotgun.attack(player.getWorld(), player, Hand.MAIN_HAND);

                ci.cancel(); // 通常の手を振る動作をキャンセル
            }

            // プレイヤーが Sniper を持っているか確認
            if (player.getMainHandStack().getItem() instanceof SniperRifleItem) {
                // ショットガンを発射
                SniperRifleItem sniper = (SniperRifleItem) player.getMainHandStack().getItem();
                sniper.attack(player.getWorld(), player, Hand.MAIN_HAND);

                ci.cancel(); // 通常の手を振る動作をキャンセル
            }
        }
    }
}
