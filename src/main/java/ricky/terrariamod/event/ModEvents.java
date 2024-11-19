package ricky.terrariamod.event;

import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.entity.player.PlayerEntity;
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
}
