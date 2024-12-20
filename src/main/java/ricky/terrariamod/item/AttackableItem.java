package ricky.terrariamod.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public interface AttackableItem {
    void attack(World world, PlayerEntity player, Hand hand);
}
