/*
 * Decompiled with CFR 0.2.2 (FabricMC 7c48b8c4).
 */
package ricky.terrariamod.entity.custom;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import ricky.terrariamod.TerrariaMod;
import ricky.terrariamod.entity.client.bats.JungleBatAttackGoal;

public class JungleBatEntity extends BatEntity {

    public JungleBatEntity(EntityType<? extends BatEntity> entityType, World world) {
        super(entityType, world);
//        this.setRoosting(false); // 常に攻撃的に動かす場合、ロースティング（止まる動き）を無効化
    }
    public static DefaultAttributeContainer.Builder createJungleBatAttributes() {
        TerrariaMod.LOGGER.info("Creating SandSlime attributes");
        return ZombieEntity.createZombieAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 30)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 35.0)
                .add(EntityAttributes.GENERIC_ARMOR, 0.5f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4);
    }
    @Override
    protected void initGoals() {
        this.goalSelector.add(2, new JungleBatAttackGoal(this, 1.2, true)); // 攻撃ゴールを追加
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true)); // プレイヤーをターゲットに
    }

}

