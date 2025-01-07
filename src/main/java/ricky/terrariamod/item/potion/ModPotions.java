package ricky.terrariamod.item.potion;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import ricky.terrariamod.TerrariaMod;
import ricky.terrariamod.effect.ModEffects;

public class ModPotions {
    public static final Potion FIGHTER_POTION = new Potion(new StatusEffectInstance(
            ModEffects.FIGHTER, // カスタムエフェクトを指定
            3600, // 効果時間 (tick: 3600 = 3分)
            0 // 効果レベル
    ));

    public static void registerPotions() {
        Registry.register(Registries.POTION, new Identifier(TerrariaMod.MOD_ID, "fighter_potion"), FIGHTER_POTION);
    }
}
