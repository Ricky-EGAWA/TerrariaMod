package ricky.terrariamod.event;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;
import ricky.terrariamod.TerrariaMod;
import ricky.terrariamod.item.bows.ShotgunItem;
import ricky.terrariamod.item.bows.SniperRifleItem;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_TUTORIAL = "key.category.terrariamod.mod_key";
    public static final String KEY_RELOAD = "key.terrariamod.reload";

    public static KeyBinding drinkingKey;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (drinkingKey.wasPressed()) {
                ItemStack itemStack = client.player.getMainHandStack();
                if (itemStack.getItem() instanceof ShotgunItem) {
                    PlayerEntity player = client.player;
                    ShotgunItem shotgunItem = (ShotgunItem) itemStack.getItem();
                    shotgunItem.reload(player.getWorld(), player, Hand.MAIN_HAND); // reload処理

                    // 攻撃処理をサーバーに通知
                    PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
                    buf.writeBoolean(true);  // 攻撃フラグ（攻撃するかどうかのフラグ）
                    ClientPlayNetworking.send(new Identifier(TerrariaMod.MOD_ID, "reload_packet"), buf);

                    client.player.sendMessage(Text.of("Reloaded"));
                }
                if (itemStack.getItem() instanceof SniperRifleItem) {
                    PlayerEntity player = client.player;
                    SniperRifleItem sniperRifleItem = (SniperRifleItem) itemStack.getItem();
                    sniperRifleItem.reload(player, Hand.MAIN_HAND); // reload処理

                    // 攻撃処理をサーバーに通知
                    PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
                    buf.writeBoolean(true);  // 攻撃フラグ（攻撃するかどうかのフラグ）
                    ClientPlayNetworking.send(new Identifier(TerrariaMod.MOD_ID, "reload_packet"), buf);

                    client.player.sendMessage(Text.of("Reloaded"));
                }
            }
        });
    }

    public static void register() {
        drinkingKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_RELOAD,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                KEY_CATEGORY_TUTORIAL
        ));

        registerKeyInputs();
    }
}
