package ricky.terrariamod.item.accessories;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import ricky.terrariamod.effect.ModEffects;

public class AdhesiveBandageItem extends Item implements AccessoriesItem{
    public AdhesiveBandageItem(Settings settings) {
        super(settings);
    }
    @Override
    public boolean cancelEffect(StatusEffect effect){
        return (effect == ModEffects.BLEEDING);
    }
}
