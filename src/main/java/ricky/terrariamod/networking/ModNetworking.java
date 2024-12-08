package ricky.terrariamod.networking;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import ricky.terrariamod.TerrariaMod;
import ricky.terrariamod.item.gun.ShotgunItem;
import ricky.terrariamod.item.gun.SniperRifleItem;
import ricky.terrariamod.networking.packet.ManaC2SPacket;
import ricky.terrariamod.networking.packet.ManaSyncDataS2CPacket;

// ネットワークパケットの登録を管理するクラス
public class ModNetworking {
    public static final Identifier RELOAD_PACKET = new Identifier(TerrariaMod.MOD_ID, "reload_packet");
    public static final Identifier MANA_ID = new Identifier(TerrariaMod.MOD_ID, "mana_packet");

    // サーバー側のパケット登録
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

    // クライアント側のパケット登録（クライアント専用）
    @Environment(EnvType.CLIENT)
    public static void registerS2CPackets() {
        net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking.registerGlobalReceiver(MANA_ID, ManaSyncDataS2CPacket::receive);
    }
}
