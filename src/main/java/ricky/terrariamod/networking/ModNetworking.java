package ricky.terrariamod.networking;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import ricky.terrariamod.TerrariaMod;
import ricky.terrariamod.item.bows.ShotgunItem;

public class ModNetworking {
    public static final Identifier ATTACK_PACKET = new Identifier(TerrariaMod.MOD_ID, "reload_packet");
    public static void register() {
        ServerPlayNetworking.registerGlobalReceiver(ATTACK_PACKET, (server, player, handler, buf, responseSender) -> {
            boolean attackFlag = buf.readBoolean();
            if (attackFlag) {
                // 攻撃を実行
                if (player.getMainHandStack().getItem() instanceof ShotgunItem) {
                    ShotgunItem shotgunItem = (ShotgunItem) player.getMainHandStack().getItem();
                    shotgunItem.reload(player.getWorld(), player, Hand.MAIN_HAND);  // attack処理
                }
            }
        });
    }
}
