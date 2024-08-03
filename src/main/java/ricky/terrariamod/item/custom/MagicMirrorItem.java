package ricky.terrariamod.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.server.network.ServerPlayerEntity;

public class MagicMirrorItem extends Item {

    public MagicMirrorItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClient) {
            BlockPos respawnPos = null;

            // Check if the player is an instance of ServerPlayerEntity
            if (player instanceof ServerPlayerEntity) {
                ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
                respawnPos = serverPlayer.getSpawnPointPosition();
                if (respawnPos == null) {
                    respawnPos = world.getSpawnPos();
                } else {
                    // Ensure the respawn position is in the correct dimension
                    if (!serverPlayer.getSpawnPointDimension().equals(world.getRegistryKey())) {
                        respawnPos = world.getSpawnPos();
                    }
                }
            }

            if (respawnPos != null) {
                // Teleport the player to the respawn position
                player.teleport(respawnPos.getX() + 0.5, respawnPos.getY(), respawnPos.getZ() + 0.5);
                player.fallDistance = 0; // Reset fall distance to prevent fall damage
                return new TypedActionResult<>(ActionResult.SUCCESS, player.getStackInHand(hand));
            }
        }
        return new TypedActionResult<>(ActionResult.FAIL, player.getStackInHand(hand));
    }
}
