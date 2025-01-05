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
import net.minecraft.util.Hand;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
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

    @Shadow
    public abstract boolean isFallFlying();

    @Shadow
    public abstract boolean hasStatusEffect(StatusEffect effect);

    @Inject(method = "tickFallFlying", at = @At("HEAD"), cancellable = true)
    private void customTickFallFlying(CallbackInfo ci) {
        if ((Object) this instanceof PlayerEntity player){
            boolean bl = ((FlagInvoker) player).invokeGetFlag(7);  // フラグ7を取得
            if (bl && !player.isOnGround() && !player.hasVehicle() && !player.hasStatusEffect(StatusEffects.LEVITATION)) {
                ItemStack itemStack = player.getEquippedStack(EquipmentSlot.CHEST);

                if ((itemStack.isOf(Items.ELYTRA) && ElytraItem.isUsable(itemStack) || itemStack.isOf(ModItems.FLAME_WING))) {
                    bl = true;
                    int roll = ((LivingEntityAccessor) player).getRoll() + 1;
                    ((LivingEntityAccessor) player).setRoll(roll);
                    System.out.println("have wings");

                    if (!player.getWorld().isClient && roll % 10 == 0) {
                        int j = roll / 10;
                        if (j % 2 == 0) {
                            itemStack.damage(1, player, (p) -> p.sendEquipmentBreakStatus(EquipmentSlot.CHEST));
                        }
                        player.emitGameEvent(GameEvent.ELYTRA_GLIDE);
                        System.out.println("gliding");
                    }
                } else {
                    bl = false;
                }
            } else {
                bl = false;
            }

            if (!player.getWorld().isClient) {
                ((FlagInvoker) player).invokeSetFlag(7, bl);  // フラグ7を設定
            }
            ci.cancel();
        }
    }
}
