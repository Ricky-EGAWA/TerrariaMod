package ricky.terrariamod.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class CursedInfernoEffect extends StatusEffect {
    protected CursedInfernoEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }
    @Override
    public void applyUpdateEffect(LivingEntity pLivingEntity, int pAmplifier) {
        // 毎 tick ダメージを与える（火のダメージをシミュレーション）
        if (!pLivingEntity.getWorld().isClient()) {
            pLivingEntity.damage(pLivingEntity.getDamageSources().onFire(), 1.0F + pAmplifier); // ダメージ量はエフェクトの強さに応じて増加
        }
        // 色を変えた炎のパーティクルを生成 TODO


        super.applyUpdateEffect(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int pDuration, int pAmplifier) {
        return true;
    }
}