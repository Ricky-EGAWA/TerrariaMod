package ricky.terrariamod.item.gun;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.function.Predicate;
import net.minecraft.advancement.criterion.Criteria;
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
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import ricky.terrariamod.entity.ammo.MusketBallEntity;
import ricky.terrariamod.item.ModItems;
import ricky.terrariamod.item.gun.ammo.MusketBallItem;

public class ShotgunItem extends RangedWeaponItem implements Vanishable {
    private boolean charged = false;
    private boolean loaded = false;

    public ShotgunItem(Item.Settings settings) {
        super(settings);
    }

    public Predicate<ItemStack> getHeldProjectiles() {
        return CROSSBOW_HELD_PROJECTILES;
    }

    public Predicate<ItemStack> getProjectiles() {
        return BOW_PROJECTILES;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        user.setCurrentHand(hand);
        return TypedActionResult.fail(itemStack);
    }
    public void attack(World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);
        if (isCharged(itemStack)) {
            shootAll(world, user, hand, itemStack, getSpeed(itemStack), 1.0F);
            setCharged(itemStack, false);
        }
    }

    public void reload(PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        World world = player.getWorld();
        if (loadProjectiles(player, stack)) {
            setCharged(stack, true);  // チャージ状態をセット
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, SoundCategory.PLAYERS, 1.0F, 1.0F);

            // アイテムを更新
            stack.setNbt(stack.getNbt());  // これでNBTを明示的に保存
        } else {
            player.sendMessage(Text.of("No ammo to reload!"));
        }
    }




    private static float getSpeed(ItemStack stack) {
        return hasProjectile(stack, Items.FIREWORK_ROCKET) ? 1.6F : 3.15F;
    }

    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
    }

    private static boolean loadProjectiles(LivingEntity shooter, ItemStack crossbow) {
        int j = 9; // 発射する弾の数
        boolean bl = shooter instanceof PlayerEntity && ((PlayerEntity)shooter).getAbilities().creativeMode;
        ItemStack musketBall = shooter instanceof PlayerEntity ? findMusketBall((PlayerEntity)shooter) : ItemStack.EMPTY;
        // Nullチェック
        if (musketBall == null) {
            musketBall = ItemStack.EMPTY;
        }
        ItemStack itemStack2 = musketBall.copy();

        for (int k = 0; k < j; ++k) {
            if (k > 0) {
                musketBall = itemStack2.copy();
            }

            if (musketBall.isEmpty() && bl) {
                musketBall = new ItemStack(ModItems.MUSKET_BALL);
                itemStack2 = musketBall.copy();
            }

            if (!loadProjectile(shooter, crossbow, musketBall, k > 0, bl)) {
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
        stack.setNbt(nbtCompound);
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

    private static void clearProjectiles(ItemStack crossbow) {
        NbtCompound nbtCompound = crossbow.getNbt();
        if (nbtCompound != null) {
            NbtList nbtList = nbtCompound.getList("ChargedProjectiles", 9);
            nbtList.clear();
            nbtCompound.put("ChargedProjectiles", nbtList);
        }

    }

    public static boolean hasProjectile(ItemStack crossbow, Item projectile) {
        return getProjectiles(crossbow).stream().anyMatch((s) -> s.isOf(projectile));
    }

    private static void shoot(World world, LivingEntity shooter, Hand hand, ItemStack crossbow, float soundPitch, float speed, float divergence, float horizontalSpread, float verticalSpread) {
        if (!world.isClient) {
            // カスタム弾丸エンティティを生成
            PersistentProjectileEntity musketBallEntity = createMusketBall(world, shooter);

            // 矢の挙動を削除
            musketBallEntity.pickupType = PickupPermission.DISALLOWED;

            Vec3d direction = shooter.getRotationVec(1.0F); // 発射方向
            Quaternionf horizontalQuaternion = new Quaternionf().setAngleAxis(Math.toRadians(horizontalSpread), 0, 1, 0); // 左右
            Quaternionf verticalQuaternion = new Quaternionf().setAngleAxis(Math.toRadians(verticalSpread), 1, 0, 0); // 上下

            // 発射方向に回転を適用
            Vector3f rotatedDirection = new Vector3f((float) direction.x, (float) direction.y, (float) direction.z);
            rotatedDirection.rotate(horizontalQuaternion).rotate(verticalQuaternion);

            musketBallEntity.setVelocity(rotatedDirection.x(), rotatedDirection.y(), rotatedDirection.z(), speed, divergence);

            // 弾丸を発射
            crossbow.damage(1, shooter, (e) -> e.sendToolBreakStatus(hand));
            world.spawnEntity(musketBallEntity);
            world.playSound(null, shooter.getX(), shooter.getY(), shooter.getZ(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 0.6F, soundPitch);
        }
    }





    private static PersistentProjectileEntity createMusketBall(World world, LivingEntity entity) {
        MusketBallEntity musketBallEntity = new MusketBallEntity(world, entity);

        musketBallEntity.setVelocity(entity, entity.getPitch(), entity.getYaw(), 0.0F, 3.15F, 1.0F);
        musketBallEntity.setCritical(false);
        musketBallEntity.setPierceLevel((byte) 0);
        musketBallEntity.pickupType = PersistentProjectileEntity.PickupPermission.DISALLOWED;

        return musketBallEntity;
    }




    public static void shootAll(World world, LivingEntity entity, Hand hand, ItemStack stack, float speed, float divergence) {
        List<ItemStack> projectiles = getProjectiles(stack);
        float[] soundPitches = getSoundPitches(entity.getRandom());

        // 発射する弾丸の最大数
        int horizontalShots = 3; // 横方向の発射数
        int verticalShots = 3; // 縦方向の発射数

        int soundIndex = 0; // サウンドのインデックス

        for (int v = 0; v < verticalShots; v++) { // 縦方向
            for (int h = 0; h < horizontalShots; h++) { // 横方向
                if (soundIndex >= projectiles.size()) break;

                ItemStack projectile = projectiles.get(soundIndex++);

                if (!projectile.isEmpty()) {
                    // 水平角度 (左 -3度, 中央 0度, 右 +3度)
                    float horizontalSpread = (h - 1) * 3.0F;
                    // 垂直角度 (下 -3度, 中央 0度, 上 +3度)
                    float verticalSpread = (v - 1) * 3.0F;

                    // 発射処理
                    shoot(world, entity, hand, stack, soundPitches[soundIndex % soundPitches.length], speed, divergence, horizontalSpread, verticalSpread);
                }
            }
        }

        postShoot(world, entity, stack);
    }



    private static float[] getSoundPitches(Random random) {
        boolean bl = random.nextBoolean();
        return new float[]{1.0F, getSoundPitch(bl, random), getSoundPitch(!bl, random)};
    }

    private static float getSoundPitch(boolean flag, Random random) {
        float f = flag ? 0.63F : 0.43F;
        return 1.0F / (random.nextFloat() * 0.5F + 1.8F) + f;
    }

    private static void postShoot(World world, LivingEntity entity, ItemStack stack) {
        if (entity instanceof ServerPlayerEntity serverPlayerEntity) {
            if (!world.isClient) {
                Criteria.SHOT_CROSSBOW.trigger(serverPlayerEntity, stack);
            }

            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
        }

        clearProjectiles(stack);
    }

    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (!world.isClient) {
            int i = EnchantmentHelper.getLevel(Enchantments.QUICK_CHARGE, stack);
            SoundEvent soundEvent = this.getQuickChargeSound(i);
            SoundEvent soundEvent2 = i == 0 ? SoundEvents.ITEM_CROSSBOW_LOADING_MIDDLE : null;
            float f = (float)(stack.getMaxUseTime() - remainingUseTicks) / (float)getPullTime(stack);
            if (f < 0.2F) {
                this.charged = false;
                this.loaded = false;
            }

            if (f >= 0.2F && !this.charged) {
                this.charged = true;
                world.playSound(null, user.getX(), user.getY(), user.getZ(), soundEvent, SoundCategory.PLAYERS, 0.5F, 1.0F);
            }

            if (f >= 0.5F && soundEvent2 != null && !this.loaded) {
                this.loaded = true;
                world.playSound(null, user.getX(), user.getY(), user.getZ(), soundEvent2, SoundCategory.PLAYERS, 0.5F, 1.0F);
            }
        }

    }

    public int getMaxUseTime(ItemStack stack) {
        return 7200;
    }

    public static int getPullTime(ItemStack stack) {
        int i = EnchantmentHelper.getLevel(Enchantments.QUICK_CHARGE, stack);
        return i == 0 ? 25 : 25 - 5 * i;
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    private SoundEvent getQuickChargeSound(int stage) {
        return switch (stage) {
            case 1 -> SoundEvents.ITEM_CROSSBOW_QUICK_CHARGE_1;
            case 2 -> SoundEvents.ITEM_CROSSBOW_QUICK_CHARGE_2;
            case 3 -> SoundEvents.ITEM_CROSSBOW_QUICK_CHARGE_3;
            default -> SoundEvents.ITEM_CROSSBOW_LOADING_START;
        };
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.terrariamod.gun.tooltip"));
        super.appendTooltip(stack, world, tooltip, context);
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
