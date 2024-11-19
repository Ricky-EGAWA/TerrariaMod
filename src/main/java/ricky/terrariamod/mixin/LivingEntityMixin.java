package ricky.terrariamod.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ricky.terrariamod.effect.ModEffects;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {//TODO 反転してない

    @Inject(method = "travel", at = @At("HEAD"), cancellable = true)
    public void travel(Vec3d movementInput, CallbackInfo info) {
        LivingEntity entity = (LivingEntity) (Object) this;

        // Confusedエフェクトが適用されているか確認
        if (entity.hasStatusEffect(ModEffects.CONFUSED)) {
            // 移動方向を反転
            Vec3d reversedInput = new Vec3d(
                    -movementInput.x,  // 左右反転
                    movementInput.y,   // 上下はそのまま
                    -movementInput.z   // 前後反転
            );

            // 移動処理を実行
            this.customTravel(entity, reversedInput);

            // オリジナルのtravelメソッドを無効化
            info.cancel();
        }
    }

    private void customTravel(LivingEntity entity, Vec3d movementInput) {
        // 流体判定と速度適用
        boolean isInWater = entity.updateMovementInFluid(FluidTags.WATER, 0.014); // 水中での動き
        boolean isInLava = entity.updateMovementInFluid(FluidTags.LAVA, 0.014);  // 溶岩での動き

        if (!isInWater && !isInLava) {
            // 通常の速度処理を行う（例: 地上の場合）
            entity.setVelocity(entity.getVelocity().add(movementInput));
        }
    }
}
