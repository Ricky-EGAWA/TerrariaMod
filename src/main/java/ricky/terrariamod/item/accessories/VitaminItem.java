package ricky.terrariamod.item.accessories;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;

public class VitaminItem extends Item implements AccessoriesItem{
    public VitaminItem(Settings settings) {
        super(settings);
    }
    @Override
    public boolean cancelEffect(StatusEffect effect){
        return (effect == StatusEffects.WEAKNESS);
    }
}
