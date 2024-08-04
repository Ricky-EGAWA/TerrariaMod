package ricky.terrariamod.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;

public class MagicMirrorItem extends Item {

    public MagicMirrorItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (world.isClient) {
            return new TypedActionResult<>(ActionResult.FAIL, player.getStackInHand(hand));
        }

        if (player instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
            BlockPos respawnPos = serverPlayer.getSpawnPointPosition();
            if (respawnPos == null || !serverPlayer.getSpawnPointDimension().equals(world.getRegistryKey())) {
                respawnPos = world.getSpawnPos();
            }

            // Get the Minecraft server
            MinecraftServer server = ((ServerWorld) world).getServer();

            // Schedule a task to run after 2 seconds (40 ticks)
            BlockPos finalRespawnPos = respawnPos;
            server.execute(() -> {
                try {
                    // 2秒間スリープする
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (serverPlayer.isAlive()) {
                    serverPlayer.teleport(
                            serverPlayer.getServerWorld(),
                            finalRespawnPos.getX() + 0.5,
                            finalRespawnPos.getY(),
                            finalRespawnPos.getZ() + 0.5,
                            serverPlayer.getYaw(),
                            serverPlayer.getPitch()
                    );
                    serverPlayer.fallDistance = 0; // Reset fall distance to prevent fall damage
                }
            });

            return new TypedActionResult<>(ActionResult.SUCCESS, player.getStackInHand(hand));
        }

        return new TypedActionResult<>(ActionResult.FAIL, player.getStackInHand(hand));
    }
}
