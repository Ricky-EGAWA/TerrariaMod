package ricky.terrariamod.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;

public class BleedingEffect extends StatusEffect {
    protected BleedingEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        // 体力の自動回復を停止できてない TODO
        // 回復系ポーション効果を消去
        if (entity.hasStatusEffect(StatusEffects.REGENERATION)) {
            entity.removeStatusEffect(StatusEffects.REGENERATION);
        }
        if (entity.hasStatusEffect(StatusEffects.INSTANT_HEALTH)) {
            entity.removeStatusEffect(StatusEffects.INSTANT_HEALTH);
        }
        if (entity.hasStatusEffect(StatusEffects.HEALTH_BOOST)) {
            entity.removeStatusEffect(StatusEffects.HEALTH_BOOST);
        }

        super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int pDuration, int pAmplifier) {
        return true;
    }
}