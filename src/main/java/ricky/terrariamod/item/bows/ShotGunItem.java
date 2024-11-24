package ricky.terrariamod.item.bows;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ShotGunItem extends BowItem {
    private final double damage;
    public ShotGunItem(Settings settings, double damage) {
        super(settings);
        this.damage = damage;
    }
    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (!world.isClient) {
            // 引き絞り時間を計算
            int useTime = this.getMaxUseTime(stack) - remainingUseTicks;
            float pullProgress = customGetPullProgress(useTime);//

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
                ItemStack arrowStack = user.getProjectileType(stack);//TODO　creative の場合矢を消費しないようにする
                if (!arrowStack.isEmpty()) {
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
    public float customGetPullProgress(int useTicks) {
        float progress = (float) useTicks / 5.0F; // 40 ticks = 2秒
        progress = (progress * progress + progress * 2.0F) / 3.0F;
        return Math.min(progress, 1.0F);
    }
}
