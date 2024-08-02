package ricky.terrariamod.entity.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HuskEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import ricky.terrariamod.TerrariaMod;

public class FrozenZombieEntity extends ZombieEntity  {
    public FrozenZombieEntity(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
        TerrariaMod.LOGGER.info("FrozenZombieEntity created");
    }
//
//    public static boolean canSpawn(EntityType<net.minecraft.entity.mob.HuskEntity> type, ServerWorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
//        return net.minecraft.entity.mob.HuskEntity.canSpawnInDark(type, world, spawnReason, pos, random) && (spawnReason == SpawnReason.SPAWNER || world.isSkyVisible(pos));
//    }
//
//    @Override
//    protected boolean burnsInDaylight() {
//        return false;
//    }
//
//    @Override
//    protected SoundEvent getAmbientSound() {
//        return SoundEvents.ENTITY_HUSK_AMBIENT;
//    }
//
//    @Override
//    protected SoundEvent getHurtSound(DamageSource source) {
//        return SoundEvents.ENTITY_HUSK_HURT;
//    }
//
//    @Override
//    protected SoundEvent getDeathSound() {
//        return SoundEvents.ENTITY_HUSK_DEATH;
//    }
//
//    @Override
//    protected SoundEvent getStepSound() {
//        return SoundEvents.ENTITY_HUSK_STEP;
//    }
//
//    @Override
//    public boolean tryAttack(Entity target) {
//        TerrariaMod.LOGGER.info("FrozenZombieEntity tryAttack called");
//        boolean bl = super.tryAttack(target);
//        if (bl && this.getMainHandStack().isEmpty() && target instanceof LivingEntity) {
//            float f = this.getWorld().getLocalDifficulty(this.getBlockPos()).getLocalDifficulty();
//            ((LivingEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 140 * (int)f), this);
//        }
//        return bl;
//    }
//
    public static DefaultAttributeContainer.Builder createFrozenZombieAttributes() {
        TerrariaMod.LOGGER.info("Creating FrozenZombie attributes");
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.8f)
                .add(EntityAttributes.GENERIC_ARMOR, 0.5f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4);
    }
//
//    @Override
//    protected boolean canConvertInWater() {
//        return true;
//    }
//
//    @Override
//    protected void convertInWater() {
//        this.convertTo(EntityType.ZOMBIE);
//        if (!this.isSilent()) {
//            this.getWorld().syncWorldEvent(null, WorldEvents.HUSK_CONVERTS_TO_ZOMBIE, this.getBlockPos(), 0);
//        }
//    }
//
//    @Override
//    protected ItemStack getSkull() {
//        return ItemStack.EMPTY;
//    }
}

