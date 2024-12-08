package ricky.terrariamod.item.custom;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import org.jetbrains.annotations.Nullable;
import ricky.terrariamod.util.IEntityDataSaver;
import ricky.terrariamod.util.ManaData;

import java.util.List;

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
            int currentMana = 0; // 初期値を設定
            if (MinecraftClient.getInstance().player != null) {
                currentMana = ((IEntityDataSaver) MinecraftClient.getInstance().player).getPersistentData().getInt("mana");
            }
            if(currentMana>=10){
                IEntityDataSaver dataPlayer = ((IEntityDataSaver) player);
                ManaData.removeMana(dataPlayer,10);
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
                        // 2秒間スリープする TODO この間他の操作をできなくする
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
        }

        return new TypedActionResult<>(ActionResult.FAIL, player.getStackInHand(hand));
    }
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.terrariamod.magic_mirror.tooltip"));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
