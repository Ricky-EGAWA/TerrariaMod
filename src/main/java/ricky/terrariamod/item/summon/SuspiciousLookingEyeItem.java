package ricky.terrariamod.item.summon;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import ricky.terrariamod.entity.boss.EyeOfCthulhuEntity;

import java.util.List;

public class SuspiciousLookingEyeItem extends Item {
    public SuspiciousLookingEyeItem(Settings settings) {
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (world.isClient) {
            return new TypedActionResult<>(ActionResult.FAIL, player.getStackInHand(hand));
        }
        if (player instanceof ServerPlayerEntity) {
            long timeOfDay = world.getTimeOfDay() % 24000; // 現在の時間を取得（0〜23999の範囲）

            if (isNight(timeOfDay)) {
                // 夜の場合の処理（ボス召喚など）
                player.sendMessage(Text.translatable("message.terrariamod.summon_success"), true);
                EyeOfCthulhuEntity.summonBoss(((ServerPlayerEntity) player).getServerWorld(), player.getBlockPos().add(10, 7, 0));
                // アイテムを1つ減らす
                player.getStackInHand(hand).decrement(1);

                return new TypedActionResult<>(ActionResult.SUCCESS, player.getStackInHand(hand));
            } else {
                // 昼の場合の処理（召喚失敗メッセージなど）
                player.sendMessage(Text.translatable("message.terrariamod.not_night"), true);
                return new TypedActionResult<>(ActionResult.FAIL, player.getStackInHand(hand));
            }
        }

        return new TypedActionResult<>(ActionResult.FAIL, player.getStackInHand(hand));
    }
    private boolean isNight(long timeOfDay) {
        // Minecraftの夜の時間範囲（おおよそ 12541〜23458）
        return timeOfDay >= 12541 && timeOfDay <= 23458;
    }
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.terrariamod.summon_eye.tooltip"));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
