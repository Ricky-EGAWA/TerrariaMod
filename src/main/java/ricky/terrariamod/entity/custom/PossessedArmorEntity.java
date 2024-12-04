package ricky.terrariamod.entity.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.World;

public class PossessedArmorEntity extends ZombieEntity  {//TODO アニメーションの追加
    public PossessedArmorEntity(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected boolean burnsInDaylight() {
        return false;
    }

    public static DefaultAttributeContainer.Builder createPossessedArmorAttributes() {
        return ZombieEntity.createZombieAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 50)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 35.0)
                .add(EntityAttributes.GENERIC_ARMOR, 0.5f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 15);
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
                livingTarget.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 140 * (int)difficultyFactor), this);
            } else {
                this.setAttacking(false); // 攻撃していない状態に戻す
            }
            return success;
        }
        return false; // ターゲットが生物でなければ攻撃をしない
    }
}

