package ricky.terrariamod.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ricky.terrariamod.item.boomerang.BoomerangItem;
import ricky.terrariamod.item.custom.CobaltShieldItem;
import ricky.terrariamod.item.gun.HandGunItem;
import ricky.terrariamod.item.gun.PhoenixBlasterItem;
import ricky.terrariamod.item.gun.ShotgunItem;
import ricky.terrariamod.item.gun.SniperRifleItem;
import ricky.terrariamod.item.magic.*;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Inject(method = "swingHand", at = @At("HEAD"), cancellable = true)
    private void onHandleInputEvents(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;

        // entity が PlayerEntity のインスタンスか確認
        if (entity instanceof PlayerEntity player) {

            // プレイヤーが Shotgun を持っているか確認
            if (player.getMainHandStack().getItem() instanceof ShotgunItem shotgun) {
                // ショットガンを発射
                shotgun.attack(player.getWorld(), player, Hand.MAIN_HAND);

                ci.cancel(); // 通常の手を振る動作をキャンセル
            }

            // プレイヤーが Sniper を持っているか確認
            if (player.getMainHandStack().getItem() instanceof SniperRifleItem sniper) {
                // ショットガンを発射
                sniper.attack(player.getWorld(), player, Hand.MAIN_HAND);

                ci.cancel(); // 通常の手を振る動作をキャンセル
            }
            // プレイヤーが enchanted sword を持っているか確認
            if (player.getMainHandStack().getItem() instanceof EnchantedSwordItem enchanted_sword) {
                // 魔法弾を発射
                enchanted_sword.attack(player.getWorld(), player, Hand.MAIN_HAND);
            }
            // プレイヤーが amethyst staff を持っているか確認
            if (player.getMainHandStack().getItem() instanceof AmethystStaffItem staff) {
                // 魔法弾を発射
                staff.attack(player.getWorld(), player, Hand.MAIN_HAND);
            }
            // プレイヤーが emerald staff を持っているか確認
            if (player.getMainHandStack().getItem() instanceof EmeraldStaffItem staff) {
                // 魔法弾を発射
                staff.attack(player.getWorld(), player, Hand.MAIN_HAND);
            }
            // プレイヤーが diamond staff を持っているか確認
            if (player.getMainHandStack().getItem() instanceof DiamondStaffItem staff) {
                // 魔法弾を発射
                staff.attack(player.getWorld(), player, Hand.MAIN_HAND);
            }
            // プレイヤーが water bolt を持っているか確認
            if (player.getMainHandStack().getItem() instanceof WaterBoltItem waterBolt) {
                // 魔法弾を発射
                waterBolt.attack(player.getWorld(), player, Hand.MAIN_HAND);
            }
            // プレイヤーが magic missile を持っているか確認
            if (player.getMainHandStack().getItem() instanceof MagicMissileItem magicMissileItem) {
                // 魔法弾を発射
                magicMissileItem.attack(player.getWorld(), player, Hand.MAIN_HAND);
            }
            // プレイヤーが hand gun を持っているか確認
            if (player.getMainHandStack().getItem() instanceof HandGunItem handGunItem) {
                // 弾を発射
                handGunItem.attack(player.getWorld(), player, Hand.MAIN_HAND);
                ci.cancel(); // 通常の手を振る動作をキャンセル
            }
            // プレイヤーが phoenixBlasterItem を持っているか確認
            if (player.getMainHandStack().getItem() instanceof PhoenixBlasterItem phoenixBlasterItem) {
                // 弾を発射
                phoenixBlasterItem.attack(player.getWorld(), player, Hand.MAIN_HAND);
                ci.cancel(); // 通常の手を振る動作をキャンセル
            }
            // プレイヤーが boomerangItem を持っているか確認
            if (player.getMainHandStack().getItem() instanceof BoomerangItem boomerangItem) {
                // 弾を発射
                boomerangItem.attack(player.getWorld(), player, Hand.MAIN_HAND);
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
