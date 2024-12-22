package ricky.terrariamod.item.accessories;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;

public class BlindFoldItem extends Item implements AccessoriesItem{
    public BlindFoldItem(Settings settings) {
        super(settings);
    }
    @Override
    public boolean cancelEffect(StatusEffect effect){
        return (effect == StatusEffects.DARKNESS);
    }
}
