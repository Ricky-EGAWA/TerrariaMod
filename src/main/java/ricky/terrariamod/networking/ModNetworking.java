package ricky.terrariamod.networking;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import ricky.terrariamod.TerrariaMod;
import ricky.terrariamod.item.gun.ShotgunItem;
import ricky.terrariamod.item.gun.SniperRifleItem;

public class ModNetworking {
    public static final Identifier RELOAD_PACKET = new Identifier(TerrariaMod.MOD_ID, "reload_packet");
    public static void register() {
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
    }
}
