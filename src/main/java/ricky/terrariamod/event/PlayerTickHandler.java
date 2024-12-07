package ricky.terrariamod.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import ricky.terrariamod.util.IEntityDataSaver;
import ricky.terrariamod.util.ManaData;

import java.util.HashMap;
import java.util.Map;

public class PlayerTickHandler implements ServerTickEvents.StartTick {
    // プレイヤーごとに最後にマナを回復したティックを追跡する
    private final Map<ServerPlayerEntity, Integer> playerLastManaUpdate = new HashMap<>();

    @Override
    public void onStartTick(MinecraftServer server) {
        // 現在のティック数を取得
        int currentTick = server.getTicks();

        // サーバー上の全プレイヤーをループ
        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            // 最後に回復したティック数を取得（初回は0と見なす）
            int lastUpdateTick = playerLastManaUpdate.getOrDefault(player, 0);

            // 10秒（200ティック）経過したか確認
            if (currentTick - lastUpdateTick >= 200) {
                // マナを回復
                IEntityDataSaver dataPlayer = ((IEntityDataSaver) player);
                ManaData.addMana(dataPlayer, 1);

                // 現在のティックを記録
                playerLastManaUpdate.put(player, currentTick);
            }
        }
    }
}
