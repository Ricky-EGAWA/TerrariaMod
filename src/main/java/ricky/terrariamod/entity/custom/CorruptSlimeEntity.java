package ricky.terrariamod.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.World;
import ricky.terrariamod.TerrariaMod;

public class CorruptSlimeEntity extends SlimeEntity {
    public CorruptSlimeEntity(EntityType<? extends SlimeEntity> entityType, World world) {
        super(entityType, world);
        TerrariaMod.LOGGER.info("CorruptSlimeEntity created");
    }
    public static DefaultAttributeContainer.Builder createCorruptSlimeAttributes() {
        TerrariaMod.LOGGER.info("Creating CorruptSlime attributes");
        return ZombieEntity.createZombieAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 12)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 10.0)
                .add(EntityAttributes.GENERIC_ARMOR, 0.5f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4);
    }
}

