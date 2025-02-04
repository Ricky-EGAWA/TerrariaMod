package ricky.terrariamod.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import ricky.terrariamod.util.IEntityDataSaver;

public class ManaSyncDataS2CPacket {
    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) {
        // クライアントプレイヤーが null の場合は処理をスキップ
        if (client.player == null) {
            return;
        }
        ((IEntityDataSaver) client.player).getPersistentData().putInt("mana", buf.readInt());
    }
}
