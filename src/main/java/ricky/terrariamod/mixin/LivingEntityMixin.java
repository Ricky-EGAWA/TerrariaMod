package ricky.terrariamod.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ricky.terrariamod.item.bows.ShotgunItem;
import ricky.terrariamod.item.bows.SniperRifleItem;

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

//    @Inject(method = "travel", at = @At("HEAD"), cancellable = true)
//    public void travel(Vec3d movementInput, CallbackInfo info) {
//        LivingEntity entity = (LivingEntity) (Object) this;
//
//        // Confusedエフェクトが適用されているか確認
//        if (entity.hasStatusEffect(ModEffects.CONFUSED)) {
//            // 移動方向を反転
//            Vec3d reversedInput = new Vec3d(
//                    -movementInput.x,  // 左右反転
//                    movementInput.y,   // 上下はそのまま
//                    -movementInput.z   // 前後反転
//            );
//
//            // 移動処理を実行
//            this.customTravel(entity, reversedInput);
//
//            // オリジナルのtravelメソッドを無効化
//            info.cancel();
//        }
//    }
//
//    private void customTravel(LivingEntity entity, Vec3d movementInput) {
//        // 流体判定と速度適用
//        boolean isInWater = entity.updateMovementInFluid(FluidTags.WATER, 0.014); // 水中での動き
//        boolean isInLava = entity.updateMovementInFluid(FluidTags.LAVA, 0.014);  // 溶岩での動き
//
//        if (!isInWater && !isInLava) {
//            // 通常の速度処理を行う（例: 地上の場合）
//            entity.setVelocity(entity.getVelocity().add(movementInput));
//        }
//    }
}
