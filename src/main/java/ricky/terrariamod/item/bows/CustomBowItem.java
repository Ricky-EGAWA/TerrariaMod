package ricky.terrariamod.item.bows;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CustomBowItem extends BowItem {
    private final double damage;
    private final int time;

    public CustomBowItem(Item.Settings settings, double damage, int time) {
        super(settings);
        this.damage = damage;
        this.time = time;
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (!world.isClient) {
            // 引き絞り時間を計算
            int useTime = this.getMaxUseTime(stack) - remainingUseTicks;
            float pullProgress = getPullProgress(useTime);

            if (pullProgress >= 0.1F) { // 十分引き絞られた場合のみ矢を放つ
                ArrowEntity arrow = new ArrowEntity(world, user);
                arrow.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, pullProgress * 3.0F, 1.0F);

                // カスタムダメージを設定
                arrow.setDamage(this.damage);

                // クリティカル判定
                if (pullProgress == 1.0F) {
                    arrow.setCritical(true);
                }

                // 耐久値を減らす
                stack.damage(1, user, (e) -> e.sendToolBreakStatus(user.getActiveHand()));

                // 矢を消費
                ItemStack arrowStack = user.getProjectileType(stack);
                if (!arrowStack.isEmpty() && ((PlayerEntity)user).getAbilities().creativeMode) {
                    arrowStack.decrement(1);
                }

                // 矢をスポーン
                world.spawnEntity(arrow);

                // サウンドを再生
                world.playSound(null, user.getX(), user.getY(), user.getZ(),
                        net.minecraft.sound.SoundEvents.ENTITY_ARROW_SHOOT,
                        net.minecraft.sound.SoundCategory.PLAYERS, 1.0F,
                        1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + pullProgress * 0.5F);
            }
        }
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return time;
    }
}
