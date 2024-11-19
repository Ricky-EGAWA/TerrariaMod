package ricky.terrariamod.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import ricky.terrariamod.TerrariaMod;

public class ModEffects {
    public static StatusEffect PETRIFICATION;

    public static StatusEffect registerStatusEffect(String name) {
        return Registry.register(Registries.STATUS_EFFECT,
                new Identifier(TerrariaMod.MOD_ID, name),
                new PetrificationEffect(StatusEffectCategory.HARMFUL, 3124687));
    }

    public static void registerEffects() {
        PETRIFICATION = registerStatusEffect("petrification");
    }
}
