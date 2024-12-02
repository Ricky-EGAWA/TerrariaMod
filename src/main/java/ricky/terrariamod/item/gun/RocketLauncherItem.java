package ricky.terrariamod.item.gun;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import ricky.terrariamod.entity.ammo.RocketEntity;
import ricky.terrariamod.item.ModItems;

public class RocketLauncherItem extends BowItem {
    private final int explosionPower; // 爆発力
    private final double speedMultiplier; // スピード倍率

    public RocketLauncherItem(Settings settings, int explosionPower, double speedMultiplier) {
        super(settings);
        this.explosionPower = explosionPower;
        this.speedMultiplier = speedMultiplier;
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity playerEntity) {
            boolean bl = playerEntity.getAbilities().creativeMode || EnchantmentHelper.getLevel(Enchantments.INFINITY, stack) > 0;

            ItemStack rocketStack = findRocket(playerEntity);

            if (rocketStack != null || bl) {
                int useTime = this.getMaxUseTime(stack) - remainingUseTicks;
                float pullProgress = customGetPullProgress(useTime);

                if (pullProgress >= 0.1F) {
                    Vec3d lookDirection = user.getRotationVec(1.0F);

                    // ロケットのスピードを計算
                    double speedX = lookDirection.x * pullProgress * this.speedMultiplier;
                    double speedY = lookDirection.y * pullProgress * this.speedMultiplier;
                    double speedZ = lookDirection.z * pullProgress * this.speedMultiplier;

                    // ロケットを作成
                    RocketEntity rocket = new RocketEntity(world, user, this.explosionPower);

                    // ロケットの初期位置をプレイヤー前方に調整
                    rocket.setPos(
                            user.getX() + lookDirection.x * 1.5,
                            user.getEyeY() - 0.1 + lookDirection.y * 1.5,
                            user.getZ() + lookDirection.z * 1.5
                    );

                    // ロケットの速度を設定
                    rocket.setVelocity(speedX, speedY, speedZ);

                    // ワールドにロケットをスポーン
                    world.spawnEntity(rocket);

                    // 耐久値を減らす
                    stack.damage(1, user, (e) -> e.sendToolBreakStatus(user.getActiveHand()));

                    // ロケットを消費
                    if (!bl) {
                        rocketStack.decrement(1);
                        if (rocketStack.isEmpty()) {
                            playerEntity.getInventory().removeOne(rocketStack);
                        }
                    }

                    // サウンドを再生
                    world.playSound(null, user.getX(), user.getY(), user.getZ(),
                            SoundEvents.ENTITY_GHAST_SHOOT,
                            SoundCategory.PLAYERS, 1.0F,
                            1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + pullProgress * 0.5F);

                    // 使用回数の統計を更新
                    playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                }
            }
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        ItemStack rocketStack = findRocket(user);
        boolean hasRocket = rocketStack != null && !rocketStack.isEmpty();
        if (!user.getAbilities().creativeMode && !hasRocket) {
            return TypedActionResult.fail(itemStack);
        } else {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(itemStack);
        }
    }

    public float customGetPullProgress(int useTicks) {
        float progress = (float) useTicks / 15.0F;
        progress = (progress * progress + progress * 2.0F) / 3.0F;
        return Math.min(progress, 1.0F);
    }

    private ItemStack findRocket(PlayerEntity player) {
        for (ItemStack stack : player.getInventory().main) {
            if (stack.isOf(ModItems.ROCKET)) {
                return stack;
            }
        }
        return null;
    }
}
