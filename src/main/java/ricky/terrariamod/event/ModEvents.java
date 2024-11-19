package ricky.terrariamod.event;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.ActionResult;
import ricky.terrariamod.effect.ModEffects;

public class ModEvents {
    public static void registerEvents() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            // プレイヤーが UnPlaceableEffect の影響下かどうかを確認
            StatusEffectInstance effect = player.getStatusEffect(ModEffects.UN_PLACEABLE);
            if (effect != null) {
                // 配置をキャンセル
                return ActionResult.FAIL;
            }

            // 通常の配置を許可
            return ActionResult.PASS;
        });
    }
}
