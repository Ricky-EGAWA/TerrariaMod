package ricky.terrariamod.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import ricky.terrariamod.TerrariaMod;

public class ModEffects {
    // エフェクトフィールドを追加
    public static StatusEffect PETRIFICATION;
    public static StatusEffect UN_PLACEABLE;
    public static StatusEffect BLEEDING;
    public static StatusEffect CURSED;
    public static StatusEffect CONFUSED;

    // エフェクト登録メソッド
    public static StatusEffect registerStatusEffect(String name, StatusEffect effect) {
        return Registry.register(Registries.STATUS_EFFECT,
                new Identifier(TerrariaMod.MOD_ID, name),
                effect);
    }

    // エフェクトを登録
    public static void registerEffects() {
        PETRIFICATION = registerStatusEffect("petrification",
                new PetrificationEffect(StatusEffectCategory.HARMFUL, 3124687));
        UN_PLACEABLE = registerStatusEffect("un_placeable",
                new UnPlaceableEffect(StatusEffectCategory.HARMFUL, 0xFFFFFF));
        BLEEDING = registerStatusEffect("bleeding",
                new BleedingEffect(StatusEffectCategory.HARMFUL, 0xFFFFFF));
        CURSED = registerStatusEffect("cursed",
                new CursedEffect(StatusEffectCategory.HARMFUL, 0xFFFFFF));
        CONFUSED = registerStatusEffect("confused",
                new CursedEffect(StatusEffectCategory.HARMFUL, 0xFFFFFF));
    }
}
