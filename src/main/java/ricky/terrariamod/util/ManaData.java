package ricky.terrariamod.util;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import ricky.terrariamod.networking.ModNetworking;

public class ManaData {
    public static void addMana(IEntityDataSaver player, int amount) {
        NbtCompound nbt = player.getPersistentData();
        int mana = nbt.getInt("mana");
        if(mana + amount >= 200) {
            mana = 200;
        } else {
            mana += amount;
        }

        nbt.putInt("mana", mana);
        syncMana(mana, (ServerPlayerEntity) player);
    }

    public static void removeMana(IEntityDataSaver player, int amount) {
        NbtCompound nbt = player.getPersistentData();
        int mana = nbt.getInt("mana");
        if(mana - amount < 0) {
            mana = 0;
        } else {
            mana -= amount;
        }

        nbt.putInt("mana", mana);
        syncMana(mana, (ServerPlayerEntity) player);
    }
    public static boolean useMana(IEntityDataSaver player, int currentMana) {
        int confuseMana = player.getPersistentData().getInt("mana");
        //マナが足りない場合
        if (confuseMana < currentMana) {
            return false;
        }
        removeMana(player,currentMana);
        return true;
    }
    public static void syncMana(int thirst, ServerPlayerEntity player) {
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(thirst);
        ServerPlayNetworking.send(player, ModNetworking.MANA_ID, buffer);
    }

}