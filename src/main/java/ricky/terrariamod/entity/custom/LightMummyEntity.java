package ricky.terrariamod.entity.custom;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import ricky.terrariamod.TerrariaMod;

public class LightMummyEntity extends ZombieEntity  {
    public LightMummyEntity(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
        TerrariaMod.LOGGER.info("light Mummy created");
    }

    public boolean canSpawn(EntityType<? extends ZombieEntity> type, ServerWorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        BlockPos blockPos = pos;

        // Powder Snowブロックの上に移動
        while (world.getBlockState(blockPos = blockPos.up()).isOf(Blocks.POWDER_SNOW)) {
            // POWDER_SNOWの上にいる限り、blockPosを更新
        }

        // スポーン条件を確認
        return ZombieEntity.canSpawnInDark(type, world, spawnReason, pos, random) &&
                (spawnReason == SpawnReason.SPAWNER || world.isSkyVisible(blockPos.down()));
    }

    @Override
    protected boolean burnsInDaylight() {
        return false;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_STRAY_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_STRAY_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_STRAY_DEATH;
    }

    @Override
    protected SoundEvent getStepSound() {
        return SoundEvents.ENTITY_STRAY_STEP;
    }

    public static DefaultAttributeContainer.Builder createMummyAttributes() {
        TerrariaMod.LOGGER.info("Creating mummy attributes");
        return ZombieEntity.createZombieAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 30)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 35.0)
                .add(EntityAttributes.GENERIC_ARMOR, 0.5f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4);
    }
    @Override
    protected void initGoals() {
        super.initGoals(); // ゾンビのデフォルトのAIを呼び出す

        // ゾンビのAIタスクを追加
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.0D, true)); // 近接攻撃
        this.goalSelector.add(2, new WanderAroundGoal(this, 1.0D)); // ランダム移動
        this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F)); // プレイヤーを見る
        this.goalSelector.add(4, new LookAroundGoal(this)); // 周囲を見る
        this.targetSelector.add(1, new RevengeGoal(this)); // 復讐ターゲット
//        this.targetSelector.add(2, new FollowTargetGoal<>(this, PlayerEntity.class, true)); // プレイヤーを追いかける
    }


    @Override
public boolean tryAttack(Entity target) {
    // 攻撃する対象がLivingEntity（生物）であることを確認
    if (target instanceof LivingEntity livingTarget) {
        // 攻撃を試みる
        boolean success = super.tryAttack(target); // ZombieEntityのtryAttackを呼び出す

        if (success) {
            // 攻撃成功時の処理
            // 例えば、ターゲットにスロウ効果を付与する
            float difficultyFactor = this.getWorld().getLocalDifficulty(this.getBlockPos()).getLocalDifficulty();
            livingTarget.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 140 * (int)difficultyFactor), this);
        } else {
            this.setAttacking(false); // 攻撃していない状態に戻す
        }
        return success;
    }
    return false; // ターゲットが生物でなければ攻撃をしない
}

}

