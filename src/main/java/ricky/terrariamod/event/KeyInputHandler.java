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
import org.lwjgl.glfw.GLFW;
import ricky.terrariamod.item.bows.ShotgunItem;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_TUTORIAL = "key.category.terrariamod.tutorial";
    public static final String KEY_RELOAD = "key.terrariamod.drink_water";

    public static KeyBinding drinkingKey;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(drinkingKey.wasPressed()) {
                ItemStack itemStack = client.player.getMainHandStack();
                if (itemStack.getItem() instanceof ShotgunItem) {
                    PlayerEntity player = client.player;
                    ShotgunItem shotgunItem = (ShotgunItem) itemStack.getItem();
                    shotgunItem.attack(player.getWorld(), player, Hand.MAIN_HAND);//playerが違うかも
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
