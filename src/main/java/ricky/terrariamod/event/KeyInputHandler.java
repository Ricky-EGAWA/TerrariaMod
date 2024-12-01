package ricky.terrariamod.event;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;
import ricky.terrariamod.TerrariaMod;
import ricky.terrariamod.item.gun.ShotgunItem;
import ricky.terrariamod.item.gun.SniperRifleItem;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_TUTORIAL = "key.category.terrariamod.mod_key";
    public static final String KEY_RELOAD = "key.terrariamod.reload";

    public static KeyBinding reloadKey;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return; // プレイヤーがnullの場合は処理を終了

            if (reloadKey.wasPressed()) {
                ItemStack itemStack = client.player.getMainHandStack();

                // ShotgunItem のリロード処理
                if (itemStack.getItem() instanceof ShotgunItem shotgunItem) {
                    PlayerEntity player = client.player;
                    shotgunItem.reload(player, Hand.MAIN_HAND); // reload処理

                    // リロード情報をサーバーに送信
                    PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
                    buf.writeBoolean(true);  // リロードフラグ
                    ClientPlayNetworking.send(new Identifier(TerrariaMod.MOD_ID, "reload_packet"), buf);
                }

                // SniperRifleItem のリロード処理
                if (itemStack.getItem() instanceof SniperRifleItem sniperRifleItem) {
                    PlayerEntity player = client.player;
                    sniperRifleItem.reload(player, Hand.MAIN_HAND); // reload処理

                    // リロード情報をサーバーに送信
                    PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
                    buf.writeBoolean(true);  // リロードフラグ
                    ClientPlayNetworking.send(new Identifier(TerrariaMod.MOD_ID, "reload_packet"), buf);
                }
            }
        });
    }

    public static void register() {
        reloadKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_RELOAD,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                KEY_CATEGORY_TUTORIAL
        ));

        registerKeyInputs();
    }
}
