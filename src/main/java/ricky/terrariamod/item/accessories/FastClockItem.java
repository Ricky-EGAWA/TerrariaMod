package ricky.terrariamod.item.accessories;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;

public class FastClockItem extends Item implements AccessoriesItem{
    public FastClockItem(Settings settings) {
        super(settings);
    }
    @Override
    public boolean cancelEffect(StatusEffect effect){
        return (effect == StatusEffects.SLOWNESS);
    }
}
