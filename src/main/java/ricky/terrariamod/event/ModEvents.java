package ricky.terrariamod.event;

import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.util.ActionResult;
import net.minecraft.util.TypedActionResult;
import ricky.terrariamod.effect.ModEffects;

public class ModEvents {
    public static void registerEvents() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            // プレイヤーが UnPlaceableEffect または CursedEffect の影響下かどうかを確認
            if (isUnPlaceable(player) || isCursed(player)) {
                return ActionResult.FAIL; // 配置をキャンセル
            }

            // 通常の配置を許可
            return ActionResult.PASS;
        });

        UseItemCallback.EVENT.register((player, world, hand) -> {
            if (isCursed(player)) {
                return TypedActionResult.fail(player.getStackInHand(hand)); // アイテム使用をキャンセル
            }
            ItemStack stack = player.getStackInHand(hand);
            if((stack.isOf(Items.POTION) || stack.isOf(Items.SPLASH_POTION) || stack.isOf(Items.LINGERING_POTION)) && isBleeding(player)) {
                // ポーションの効果を取得
                var potionEffects = PotionUtil.getPotionEffects(stack);
                for (StatusEffectInstance effect : potionEffects) {
                    if (effect.getEffectType() == StatusEffects.REGENERATION || effect.getEffectType() == StatusEffects.INSTANT_HEALTH) {
                        return TypedActionResult.fail(stack);
                    }
                }
            }
            if(isBleeding(player) && (stack.isOf(Items.GOLDEN_APPLE) || stack.isOf(Items.ENCHANTED_GOLDEN_APPLE))){
                return TypedActionResult.fail(stack);
            }
            return TypedActionResult.pass(player.getStackInHand(hand)); // 通常のアイテム使用
        });

        // ブロック攻撃の左クリックを無効化
        AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) -> {
            if (isCursed(player)) {
                return ActionResult.FAIL; // アクションをキャンセル
            }
            return ActionResult.PASS;
        });

        // エンティティ攻撃の左クリックを無効化
        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (isCursed(player)) {
                return ActionResult.FAIL; // アクションをキャンセル
            }
            return ActionResult.PASS;
        });
    }

    private static boolean isCursed(PlayerEntity player) {
        // プレイヤーが呪いのエフェクトを持っているかを判定
        return player.hasStatusEffect(ModEffects.CURSED);
    }

    private static boolean isUnPlaceable(PlayerEntity player) {
        // プレイヤーが設置不可エフェクトを持っているかを判定
        return player.hasStatusEffect(ModEffects.UN_PLACEABLE);
    }
    private static boolean isBleeding(PlayerEntity player) {
        // プレイヤーが出血しているかを判定
        return player.hasStatusEffect(ModEffects.BLEEDING);
    }
}
