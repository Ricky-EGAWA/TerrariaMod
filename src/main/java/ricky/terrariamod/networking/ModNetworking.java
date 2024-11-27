package ricky.terrariamod.networking;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import ricky.terrariamod.TerrariaMod;
import ricky.terrariamod.item.bows.ShotgunItem;
import ricky.terrariamod.item.bows.SniperRifleItem;

public class ModNetworking {
    public static final Identifier RELOAD_PACKET = new Identifier(TerrariaMod.MOD_ID, "reload_packet");
    public static void register() {
        ServerPlayNetworking.registerGlobalReceiver(RELOAD_PACKET, (server, player, handler, buf, responseSender) -> {
            boolean attackFlag = buf.readBoolean();
            if (attackFlag) {
                // リロードを実行
                if (player.getMainHandStack().getItem() instanceof ShotgunItem) {
                    ShotgunItem shotgunItem = (ShotgunItem) player.getMainHandStack().getItem();
                    shotgunItem.reload(player.getWorld(), player, Hand.MAIN_HAND);
                }
                if (player.getMainHandStack().getItem() instanceof SniperRifleItem) {
                    SniperRifleItem sniperRifleItem = (SniperRifleItem) player.getMainHandStack().getItem();
                    sniperRifleItem.reload(player.getWorld(), player, Hand.MAIN_HAND);
                }
            }
        });
    }
}
