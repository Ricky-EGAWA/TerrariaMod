package ricky.terrariamod.item.magic;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import ricky.terrariamod.entity.magic.MagicMissileEntity;
import ricky.terrariamod.util.ManaData;

import java.util.List;

public class MagicMissileItem extends SwordItem {
    public MagicMissileItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Item.Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    public void attack(World world, PlayerEntity playerEntity, Hand hand) {
        if (!world.isClient) { // サーバー側のみ実行
            // マナを消費（40マナ）できるかチェック
            if (ManaData.useMana(playerEntity, 40)) {
                // 武器の耐久値を減少
                ItemStack enchanted = playerEntity.getStackInHand(hand);
                enchanted.damage(1, playerEntity, p -> p.sendToolBreakStatus(hand));

                // 近くのモンスターをターゲットに選択
                List<LivingEntity> nearbyMonsters = world.getEntitiesByClass(
                        LivingEntity.class, // LivingEntity 型のエンティティを探索
                        playerEntity.getBoundingBox().expand(10.0), // プレイヤー周囲10ブロック以内
                        entity -> entity != playerEntity && entity.isAlive() && entity instanceof Monster // 条件: プレイヤーではない, 生存している, Monster のインスタンス
                );

                if (!nearbyMonsters.isEmpty()) {
                    // 一番近いモンスターをターゲットにする
                    LivingEntity target = nearbyMonsters.get(0);

                    // 魔法弾を生成してターゲットを設定
                    Direction.Axis playerFacingAxis = playerEntity.getHorizontalFacing().getAxis();
                    MagicMissileEntity ballEntity = new MagicMissileEntity(world, playerEntity, target, playerFacingAxis);
//                    System.out.print("target is "+target);

                    // ミサイルの発射速度を設定
                    ballEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, 0.15F, 0.0F);

                    // 世界にミサイルをスポーン
                    world.spawnEntity(ballEntity);
                } else{
                    // 魔法弾を生成してターゲットを設定
                    Direction.Axis playerFacingAxis = playerEntity.getHorizontalFacing().getAxis();
                    MagicMissileEntity ballEntity = new MagicMissileEntity(world, playerEntity, null, playerFacingAxis);
//                    System.out.print("target is "+target);

                    // ミサイルの発射速度を設定
                    ballEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, 0.15F, 0.0F);

                    // 世界にミサイルをスポーン
                    world.spawnEntity(ballEntity);
                }
            }
        }
    }
}
