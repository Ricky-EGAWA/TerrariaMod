package ricky.terrariamod.item.bows;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
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
            // クリエイティブモードか、無限エンチャントを持つかのチェック
            boolean bl = playerEntity.getAbilities().creativeMode || EnchantmentHelper.getLevel(Enchantments.INFINITY, stack) > 0;

            // プレイヤーのインベントリからロケットを探す
            ItemStack rocketStack = findRocket(playerEntity);

            if (rocketStack != null || bl) { // ロケットが見つかったかクリエイティブモードの場合
                int useTime = this.getMaxUseTime(stack) - remainingUseTicks;
                float pullProgress = customGetPullProgress(useTime);

                if (pullProgress >= 0.1F) { // 十分引き絞られた場合のみファイアーボールを発射
                    Vec3d lookDirection = user.getRotationVec(1.0F);

                    // スピードを調整
                    double speedX = lookDirection.x * pullProgress * this.speedMultiplier;
                    double speedY = lookDirection.y * pullProgress * this.speedMultiplier;
                    double speedZ = lookDirection.z * pullProgress * this.speedMultiplier;

                    // ファイアーボールを作成
                    FireballEntity fireball = new FireballEntity(
                            world,
                            user,
                            speedX,
                            speedY,
                            speedZ,
                            this.explosionPower
                    );

                    // ファイアーボールの位置を調整
                    fireball.setPos(user.getX() + lookDirection.x * 2,
                            user.getEyeY() - 0.1 + lookDirection.y * 2,
                            user.getZ() + lookDirection.z * 2);

                    // ファイアーボールをスポーン
                    world.spawnEntity(fireball);

                    // 耐久値を減らす
                    stack.damage(1, user, (e) -> e.sendToolBreakStatus(user.getActiveHand()));

                    // ロケットを消費（クリエイティブモードでは消費しない）
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
        boolean bl = !findRocket(user).isEmpty();
        if (!user.getAbilities().creativeMode && !bl) {
            return TypedActionResult.fail(itemStack);
        } else {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(itemStack);
        }
    }


    public float customGetPullProgress(int useTicks) {
        float progress = (float) useTicks / 5.0F; // 40 ticks = 2秒
        progress = (progress * progress + progress * 2.0F) / 3.0F;
        return Math.min(progress, 1.0F);
    }
    private ItemStack findRocket(PlayerEntity player) {
        for (ItemStack stack : player.getInventory().main) { // インベントリのすべてのスロットを検索
            if (stack.isOf(ModItems.ROCKET)) { // ロケットアイテムに一致するものを探す
                return stack; // 一致するアイテムを返す
            }
        }
        return null; // 見つからない場合は null を返す
    }

}