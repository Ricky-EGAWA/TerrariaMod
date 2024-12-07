package ricky.terrariamod.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.block.Blocks;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import ricky.terrariamod.util.IEntityDataSaver;
import ricky.terrariamod.util.ManaData;

public class ManaC2SPacket {
    private static final String MESSAGE_DRINKING_WATER = "message.terrariamod.drank_water";
    private static final String MESSAGE_NO_MANA = "message.terrariamod.no_mana";

    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) {
        // Everything here happens ONLY on the Server!
        ServerWorld world = (ServerWorld) player.getWorld();
        if(isAroundWaterThem(player, world, 2)) {
            // Notify the player
            player.sendMessage(Text.translatable(MESSAGE_DRINKING_WATER)
                    .fillStyle(Style.EMPTY.withColor(Formatting.DARK_AQUA)), false);

            // Play the drinking sound
            world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.PLAYERS,
                    0.5F, world.random.nextFloat() * 0.1F + 0.9F);

            // actually add the water level to the player
            ManaData.addMana(((IEntityDataSaver) player), 1);

            // outputting the current thirst level of player
            player.sendMessage(Text.literal("Mana: " + ((IEntityDataSaver) player).getPersistentData().getInt("mana"))
                    .fillStyle(Style.EMPTY.withColor(Formatting.AQUA)), true);

        } else {
            // Notify the player
            player.sendMessage(Text.translatable(MESSAGE_NO_MANA)
                    .fillStyle(Style.EMPTY.withColor(Formatting.RED)), false);

            // outputting the current thirst level of player
            player.sendMessage(Text.literal("Mana: " + ((IEntityDataSaver) player).getPersistentData().getInt("mana"))
                    .fillStyle(Style.EMPTY.withColor(Formatting.AQUA)), true);

            // Sync thirst
            ManaData.syncMana(((IEntityDataSaver) player).getPersistentData().getInt("mana"), player);
        }
    }

    private static boolean isAroundWaterThem(ServerPlayerEntity player, ServerWorld world, int size) {
        return BlockPos.stream(player.getBoundingBox().expand(size))
                .map(world::getBlockState).filter(state -> state.isOf(Blocks.WATER)).toArray().length > 0;
    }
}
