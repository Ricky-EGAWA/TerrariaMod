package ricky.terrariamod.item.bows;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class RocketLauncherItem extends BowItem {
    private final int explosionPower; // 爆発力を指定

    public RocketLauncherItem(Settings settings, int explosionPower) {
        super(settings);
        this.explosionPower = explosionPower;
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (!world.isClient) {
            // 引き絞り時間を計算
            int useTime = this.getMaxUseTime(stack) - remainingUseTicks;
            float pullProgress = customGetPullProgress(useTime);

            if (pullProgress >= 0.1F) { // 十分引き絞られた場合のみファイアーボールを放つ
                // ユーザーの視線方向を取得
                Vec3d lookDirection = user.getRotationVec(1.0F);

                // ファイアーボールを作成
                FireballEntity fireball = new FireballEntity(
                        world,
                        user,
                        lookDirection.x * pullProgress * 2,
                        lookDirection.y * pullProgress * 2,
                        lookDirection.z * pullProgress * 2,
                        this.explosionPower // 爆発力を設定
                );

                // ファイアーボールの位置を調整 (ユーザーの位置 + 少し前方)
                fireball.setPos(user.getX() + lookDirection.x * 2,
                        user.getEyeY() - 0.1 + lookDirection.y * 2,
                        user.getZ() + lookDirection.z * 2);

                // ファイアーボールをスポーン
                world.spawnEntity(fireball);

                // 耐久値を減らす
                stack.damage(1, user, (e) -> e.sendToolBreakStatus(user.getActiveHand()));

                // サウンドを再生
                world.playSound(null, user.getX(), user.getY(), user.getZ(),
                        net.minecraft.sound.SoundEvents.ENTITY_GHAST_SHOOT,
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
