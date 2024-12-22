package ricky.terrariamod.item.accessories;

import net.minecraft.entity.effect.StatusEffect;
import ricky.terrariamod.effect.ModEffects;

public interface AccessoriesItem {
    boolean cancelEffect(StatusEffect effect);
}
