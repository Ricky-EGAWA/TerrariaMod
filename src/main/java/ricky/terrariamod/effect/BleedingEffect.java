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
        if (entity.getHealth() < entity.getMaxHealth()) {
            entity.setHealth(entity.getHealth()); // 体力を増減させず維持
        }

        // 回復系ポーション効果を消去
        if (entity.hasStatusEffect(StatusEffects.REGENERATION)) {
            entity.removeStatusEffect(StatusEffects.REGENERATION);
        }
        if (entity.hasStatusEffect(StatusEffects.INSTANT_HEALTH)) {
            entity.removeStatusEffect(StatusEffects.INSTANT_HEALTH);
        }

        super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int pDuration, int pAmplifier) {
        return true;
    }
}