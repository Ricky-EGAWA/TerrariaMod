package ricky.terrariamod.item.bows;

import com.google.common.collect.Lists;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity.PickupPermission;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import ricky.terrariamod.entity.ammo.MusketBallEntity;
import ricky.terrariamod.item.ModItems;
import ricky.terrariamod.item.gun.ammo.MusketBallItem;

import java.util.List;
import java.util.function.Predicate;

public class SniperRifleItem extends RangedWeaponItem implements Vanishable {
    private boolean loaded = true;

    public SniperRifleItem(Settings settings) {
        super(settings);
    }

    public Predicate<ItemStack> getHeldProjectiles() {
        return CROSSBOW_HELD_PROJECTILES;
    }

    public Predicate<ItemStack> getProjectiles() {
        return BOW_PROJECTILES;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        // サウンドを再生
        user.playSound(SoundEvents.ITEM_SPYGLASS_USE, 1.0F, 1.0F);

        // 使用中の状態を有効化
        user.setCurrentHand(hand);
        ItemStack itemStack = user.getStackInHand(hand);
        return TypedActionResult.fail(itemStack);
    }



    public void attack(World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);
        if (isCharged(itemStack)) {
            shoot(world, user, hand, itemStack);
            setCharged(itemStack, false);
        }
    }

    public void reload(PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        if (loadProjectiles(player, stack)) {
            setCharged(stack, true);  // チャージ状態をセット
            // アイテムを更新
            stack.setNbt(stack.getNbt());  // これでNBTを明示的に保存
        } else {
            player.sendMessage(Text.of("No ammo to reload!"));
        }
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity) {
            user.playSound(SoundEvents.ITEM_SPYGLASS_STOP_USING, 1.0F, 1.0F);
        }
    }

    private static boolean loadProjectiles(LivingEntity shooter, ItemStack crossbow) {
        int j = 9; // 発射する弾の数
        boolean bl = shooter instanceof PlayerEntity && ((PlayerEntity)shooter).getAbilities().creativeMode;

        // 弾を見つける
        ItemStack musketBall = shooter instanceof PlayerEntity ? findMusketBall((PlayerEntity)shooter) : ItemStack.EMPTY;

        // 弾が空でないことを確認しコピー
        ItemStack itemStack2 = musketBall != null && !musketBall.isEmpty() ? musketBall.copy() : ItemStack.EMPTY;

        for (int k = 0; k < j; ++k) {
            if (k > 0) {
                musketBall = itemStack2.copy(); // 再度コピー
            }
            // クリエイティブモードかつ弾が空の場合、新しい弾を生成
            if ((musketBall == null || musketBall.isEmpty()) && bl) {
                musketBall = new ItemStack(ModItems.MUSKET_BALL);
                itemStack2 = musketBall.copy();
            }
            // 弾をロードできない場合、処理を終了
            if (musketBall == null || !loadProjectile(shooter, crossbow, musketBall, k > 0, bl)) {
                return false;
            }
        }
        return true;
    }


    private static boolean loadProjectile(LivingEntity shooter, ItemStack crossbow, ItemStack projectile, boolean simulated, boolean creative) {
        if (projectile.isEmpty()) {
            return false;
        } else {
            boolean bl = creative && projectile.getItem() instanceof MusketBallItem;
            ItemStack itemStack;
            if (!bl && !creative && !simulated) {
                itemStack = projectile.split(1);
                if (projectile.isEmpty() && shooter instanceof PlayerEntity) {
                    ((PlayerEntity)shooter).getInventory().removeOne(projectile);
                }
            } else {
                itemStack = projectile.copy();
            }

            putProjectile(crossbow, itemStack);
            return true;
        }
    }

    public static boolean isCharged(ItemStack stack) {
        NbtCompound nbtCompound = stack.getNbt();
        return nbtCompound != null && nbtCompound.getBoolean("Charged");
    }

    public static void setCharged(ItemStack stack, boolean charged) {
        NbtCompound nbtCompound = stack.getOrCreateNbt();
        nbtCompound.putBoolean("Charged", charged);
    }

    private static void putProjectile(ItemStack crossbow, ItemStack projectile) {
        NbtCompound nbtCompound = crossbow.getOrCreateNbt();
        NbtList nbtList;
        if (nbtCompound.contains("ChargedProjectiles", 9)) {
            nbtList = nbtCompound.getList("ChargedProjectiles", 10);
        } else {
            nbtList = new NbtList();
        }

        NbtCompound nbtCompound2 = new NbtCompound();
        projectile.writeNbt(nbtCompound2);
        nbtList.add(nbtCompound2);
        nbtCompound.put("ChargedProjectiles", nbtList);
    }

    private static List<ItemStack> getProjectiles(ItemStack crossbow) {
        List<ItemStack> list = Lists.newArrayList();
        NbtCompound nbtCompound = crossbow.getNbt();
        if (nbtCompound != null && nbtCompound.contains("ChargedProjectiles", 9)) {
            NbtList nbtList = nbtCompound.getList("ChargedProjectiles", 10);
            if (nbtList != null) {
                for(int i = 0; i < nbtList.size(); ++i) {
                    NbtCompound nbtCompound2 = nbtList.getCompound(i);
                    list.add(ItemStack.fromNbt(nbtCompound2));
                }
            }
        }

        return list;
    }

    private static void shoot(World world, LivingEntity shooter, Hand hand, ItemStack crossbow) {
        if (!world.isClient) {
            // カスタム弾丸エンティティを生成
            PersistentProjectileEntity SniperBulletEntity = createSniperBullet(world, shooter);

            // 矢の挙動を削除
            SniperBulletEntity.pickupType = PickupPermission.DISALLOWED;

            Vec3d direction = shooter.getRotationVec(1.0F); // 発射方向
            // 発射方向に回転を適用
            Vector3f rotatedDirection = new Vector3f((float) direction.x, (float) direction.y, (float) direction.z);

            SniperBulletEntity.setVelocity(rotatedDirection.x(), rotatedDirection.y(), rotatedDirection.z(), 10, 0);

            // 弾丸を発射
            crossbow.damage(1, shooter, (e) -> e.sendToolBreakStatus(hand));
            world.spawnEntity(SniperBulletEntity);
            world.playSound(null, shooter.getX(), shooter.getY(), shooter.getZ(), SoundEvents.ITEM_CROSSBOW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F);
        }
    }





    private static PersistentProjectileEntity createSniperBullet(World world, LivingEntity entity) {
        MusketBallEntity musketBallEntity = new MusketBallEntity(world, entity);

        musketBallEntity.setVelocity(entity, entity.getPitch(), entity.getYaw(), 0.0F, 3.15F, 1.0F);
        musketBallEntity.setCritical(false);
        musketBallEntity.setPierceLevel((byte) 0);
        musketBallEntity.pickupType = PickupPermission.DISALLOWED;

        return musketBallEntity;
    }

    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (MinecraftClient.getInstance().options.attackKey.wasPressed()) {
            // 左クリックが押された時の処理
            PlayerEntity player = (PlayerEntity) user;
            attack(world, player, Hand.MAIN_HAND);
        }
        if (!world.isClient) {
            int i = EnchantmentHelper.getLevel(Enchantments.QUICK_CHARGE, stack);
            SoundEvent soundEvent2 = i == 0 ? SoundEvents.ITEM_CROSSBOW_LOADING_MIDDLE : null;
            float f = (float)(stack.getMaxUseTime() - remainingUseTicks) / (float)getPullTime(stack);

            if (f >= 0.5F && soundEvent2 != null && !this.loaded) {
                this.loaded = true;
                world.playSound(null, user.getX(), user.getY(), user.getZ(), soundEvent2, SoundCategory.PLAYERS, 0.5F, 1.0F);
            }
        }

    }

    public int getMaxUseTime(ItemStack stack) {
        return 1200;
    }

    public static int getPullTime(ItemStack stack) {
        int i = EnchantmentHelper.getLevel(Enchantments.QUICK_CHARGE, stack);
        return i == 0 ? 25 : 25 - 5 * i;
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.SPYGLASS;
    }

    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        List<ItemStack> list = getProjectiles(stack);
        if (isCharged(stack) && !list.isEmpty()) {
            ItemStack itemStack = list.get(0);
            tooltip.add(Text.translatable("item.minecraft.crossbow.projectile").append(ScreenTexts.SPACE).append(itemStack.toHoverableText()));
        }
    }

    public boolean isUsedOnRelease(ItemStack stack) {
        return stack.isOf(this);
    }

    public int getRange() {
        return 8;
    }
    private static ItemStack findMusketBall(PlayerEntity player) {
        for (ItemStack stack : player.getInventory().main) {
            if (stack.isOf(ModItems.MUSKET_BALL)) {
                return stack;
            }
        }
        return null;
    }
}
