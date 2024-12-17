package ricky.terrariamod.item.gun;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.joml.Vector3f;
import ricky.terrariamod.entity.ammo.MusketBallEntity;
import ricky.terrariamod.item.ModItems;

public class PhoenixBlasterItem extends SwordItem {
    public PhoenixBlasterItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }
    public void attack(World world, PlayerEntity playerEntity, Hand hand) {
        if (!world.isClient) {
            ItemStack itemStack = findMusketBall((playerEntity));
            if(playerEntity.getAbilities().creativeMode){
                shoot(world,playerEntity,hand);
            }else if (itemStack != null){
                itemStack.decrement(1);
                shoot(world,playerEntity,hand);
            }
        }
    }
    private static PersistentProjectileEntity createBullet(World world, LivingEntity entity) {
        MusketBallEntity musketBallEntity = new MusketBallEntity(world, entity,4);

        musketBallEntity.setVelocity(entity, entity.getPitch(), entity.getYaw(), 0.0F, 2F, 1.0F);
        musketBallEntity.setCritical(false);
        musketBallEntity.setPierceLevel((byte) 0);
        musketBallEntity.pickupType = PersistentProjectileEntity.PickupPermission.DISALLOWED;

        return musketBallEntity;
    }
    private static void shoot(World world, LivingEntity shooter, Hand hand) {
        if (!world.isClient) {
            // カスタム弾丸エンティティを生成
            PersistentProjectileEntity bullet = createBullet(world, shooter);

            // 矢の挙動を削除
            bullet.pickupType = PersistentProjectileEntity.PickupPermission.DISALLOWED;

            Vec3d direction = shooter.getRotationVec(1.0F); // 発射方向
            // 発射方向に回転を適用
            Vector3f rotatedDirection = new Vector3f((float) direction.x, (float) direction.y, (float) direction.z);

            bullet.setVelocity(rotatedDirection.x(), rotatedDirection.y(), rotatedDirection.z(), 4, 0);

            // 弾丸を発射
            ItemStack gun = shooter.getStackInHand(hand);
            gun.damage(1, shooter, (e) -> e.sendToolBreakStatus(hand));
            world.spawnEntity(bullet);
            world.playSound(null, shooter.getX(), shooter.getY(), shooter.getZ(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 0.7F, 1.0F);
        }
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
