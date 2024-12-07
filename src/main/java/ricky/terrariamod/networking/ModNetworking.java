package ricky.terrariamod.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import ricky.terrariamod.TerrariaMod;
import ricky.terrariamod.item.gun.ShotgunItem;
import ricky.terrariamod.item.gun.SniperRifleItem;
import ricky.terrariamod.networking.packet.ManaC2SPacket;
import ricky.terrariamod.networking.packet.ManaSyncDataS2CPacket;

public class ModNetworking {
    public static final Identifier RELOAD_PACKET = new Identifier(TerrariaMod.MOD_ID, "reload_packet");
    public static final Identifier MANA_ID = new Identifier(TerrariaMod.MOD_ID, "mana_packet");
    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(RELOAD_PACKET, (server, player, handler, buf, responseSender) -> {
            boolean attackFlag = buf.readBoolean();
            if (attackFlag) {
                // リロードを実行
                if (player.getMainHandStack().getItem() instanceof ShotgunItem shotgunItem) {
                    shotgunItem.reload(player, Hand.MAIN_HAND);
                }
                if (player.getMainHandStack().getItem() instanceof SniperRifleItem sniperRifleItem) {
                    sniperRifleItem.reload(player, Hand.MAIN_HAND);
                }
            }
        });
        ServerPlayNetworking.registerGlobalReceiver(MANA_ID, ManaC2SPacket::receive);
    }
    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(MANA_ID, ManaSyncDataS2CPacket::receive);
    }
}
