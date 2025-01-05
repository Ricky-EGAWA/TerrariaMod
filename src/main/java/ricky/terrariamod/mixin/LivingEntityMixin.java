package ricky.terrariamod.mixin;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ricky.terrariamod.item.AttackableItem;
import ricky.terrariamod.item.ModItems;
import ricky.terrariamod.item.accessories.AccessoriesItem;
import ricky.terrariamod.item.accessories.CobaltShieldItem;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin{
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
    private int roll = 0;

    @Inject(method = "tickFallFlying", at = @At("HEAD"), cancellable = true)
    private void customTickFallFlying(CallbackInfo ci) {
        if ((Object) this instanceof PlayerEntity player) {
            boolean bl = ((FlagInvoker) player).invokeGetFlag(7); // フラグ7を取得
            if (bl && !player.isOnGround() && !player.hasVehicle() && !player.hasStatusEffect(StatusEffects.LEVITATION)) {
                ItemStack itemStack = player.getEquippedStack(EquipmentSlot.CHEST);

                if ((itemStack.isOf(Items.ELYTRA) && ElytraItem.isUsable(itemStack)) || itemStack.isOf(ModItems.FLAME_WING)) {
                    bl = true;

                    // 炎のエフェクトを生成 (FLAME_WING装着時のみ)
                    if (itemStack.isOf(ModItems.FLAME_WING)) {
                        // プレイヤーの速度を取得
                        Vec3d velocity = player.getVelocity();
                        double speed = Math.sqrt(velocity.x * velocity.x + velocity.z * velocity.z); // XY平面の速度
                        if (speed > 0.01) { // 動いている場合のみ処理
                            // 正規化して方向を求める
                            double normX = velocity.x / speed;
                            double normZ = velocity.z / speed;

                            // 左右の羽のオフセット位置（進行方向の左右）
                            double wingOffset = 0.5;

                            // パーティクルの座標計算
                            double leftWingX = player.getX() - normZ * wingOffset;
                            double leftWingZ = player.getZ() + normX * wingOffset;
                            double rightWingX = player.getX() + normZ * wingOffset;
                            double rightWingZ = player.getZ() - normX * wingOffset;
                            double wingY = player.getY() + 0.6; // プレイヤーの少し上

                            // サーバー側で左の羽にパーティクルを生成
                            player.getWorld().addParticle(
                                    ParticleTypes.FLAME, // パーティクルの種類
                                    leftWingX, wingY, leftWingZ, // 左羽の座標
                                    0, 0, 0 // パーティクルの速度
                            );

                            // サーバー側で右の羽にパーティクルを生成
                            player.getWorld().addParticle(
                                    ParticleTypes.FLAME, // パーティクルの種類
                                    rightWingX, wingY, rightWingZ, // 右羽の座標
                                    0, 0, 0 // パーティクルの速度
                            );
                        }
                    }

                    if (!player.getWorld().isClient && roll % 10 == 0) {
                        int j = roll / 10;
                        if (j % 2 == 0 && itemStack.isOf(Items.ELYTRA)) {
                            itemStack.damage(1, player, (p) -> p.sendEquipmentBreakStatus(EquipmentSlot.CHEST));
                        }
                        player.emitGameEvent(GameEvent.ELYTRA_GLIDE);
                    }
                    roll++;
                } else {
                    bl = false;
                }
            } else {
                bl = false;
            }

            if (!player.getWorld().isClient) {
                ((FlagInvoker) player).invokeSetFlag(7, bl); // フラグ7を設定
            }
            ci.cancel();
        }
    }
}
