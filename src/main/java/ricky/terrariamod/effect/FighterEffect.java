package ricky.terrariamod.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;

public class FighterEffect extends StatusEffect {
    protected FighterEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyUpdateEffect(LivingEntity player, int amplifier) {
        // サーバー側でのみ処理を行う
        if (player.getWorld() instanceof ServerWorld serverWorld) {
            // プレイヤーの位置から30ブロックの範囲を計算
            Box range = new Box(
                    player.getX() - 30, player.getY() - 30, player.getZ() - 30,
                    player.getX() + 30, player.getY() + 30, player.getZ() + 30
            );

            // 範囲内のエンティティを取得
            serverWorld.getEntitiesByClass(LivingEntity.class, range, entity ->
                    entity != player // 自分以外
            ).forEach(entity -> {
                // モブに光の矢エフェクトを付与
                entity.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(
                        StatusEffects.GLOWING, // 光の矢エフェクト
                        100, // 効果時間 (tick: 100 = 5秒)
                        0 // 効果レベル
                ));
            });
        }

        super.applyUpdateEffect(player, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        // 20 tick（1秒）ごとに更新
        return duration % 20 == 0;
    }
}
