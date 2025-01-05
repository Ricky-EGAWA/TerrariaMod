package ricky.terrariamod.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
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
        if ((itemStack.isOf(ModItems.FLAME_WING))) {
            this.startFallFlying();
            cir.setReturnValue(true);
            cir.cancel();
        }
    }
    @Inject(method = "handleFallDamage", at = @At("HEAD"), cancellable = true)
    public void onHandleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        // 胸当てスロットにFLAME_WINGが装備されている場合
        ItemStack chestItem = player.getEquippedStack(EquipmentSlot.CHEST);
        if (chestItem.isOf(ModItems.FLAME_WING)) {
            // 落下ダメージを無効化
            cir.setReturnValue(false);
            cir.cancel();
        }
    }

    @Unique
    private int flameWingFlightCounter = 0; // 上昇可能時間のカウンター
    @Unique
    private boolean isFlameWingBoosting = false; // 上昇中フラグ

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        // チェストスロットのアイテムがFlameWingかチェック
        ItemStack chestItem = player.getEquippedStack(EquipmentSlot.CHEST);
        if (chestItem.isOf(ModItems.FLAME_WING)) {
            MinecraftClient client = MinecraftClient.getInstance();
            // ジャンプキーが押されており、上昇可能状態かどうか
            if (client.options.jumpKey.isPressed() && flameWingFlightCounter > 0 && !isFlameWingBoosting) {
                isFlameWingBoosting = true; // 上昇開始
                flameWingFlightCounter--; // 残り時間を減らす

                // 上昇効果を付与
                Vec3d velocity = player.getVelocity();
                player.setVelocity(velocity.x, 0.8, velocity.z); // 上昇速度を調整
                player.velocityModified = true;

                // クールダウン用にカウンターを再設定
                if (flameWingFlightCounter == 0) {
                    System.out.println("FlameWing boost finished!");
                }
            }

            // ジャンプキーが離されたらフラグをリセット
            if (!client.options.jumpKey.isPressed()) {
                isFlameWingBoosting = false;
            }

            // フライト中でない場合はカウンターをリセット（10秒間のみ上昇可能）
            if (player.isOnGround()) {
                flameWingFlightCounter = 10;
            }
        }
    }
}
