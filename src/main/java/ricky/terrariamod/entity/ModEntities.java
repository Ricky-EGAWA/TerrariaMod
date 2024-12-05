package ricky.terrariamod.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import ricky.terrariamod.TerrariaMod;
import ricky.terrariamod.entity.ammo.MusketBallEntity;
import ricky.terrariamod.entity.ammo.RocketEntity;
import ricky.terrariamod.entity.custom.*;

public class ModEntities {
    public static final EntityType<PorcupineEntity> PORCUPINE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(TerrariaMod.MOD_ID, "porcupine"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, PorcupineEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 1f)).build());

    public static final EntityType<FrozenZombieEntity> FROZENZOMBIE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(TerrariaMod.MOD_ID, "frozenzombie"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, FrozenZombieEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6f, 1.96f)).build());

    public static final EntityType<IceSlimeEntity> ICE_SLIME = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(TerrariaMod.MOD_ID, "ice_slime"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, IceSlimeEntity::new)
                    .dimensions(EntityDimensions.fixed(1.02f, 1.02f)).build());

    public static final EntityType<SandSlimeEntity> SAND_SLIME = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(TerrariaMod.MOD_ID, "sand_slime"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, SandSlimeEntity::new)
                    .dimensions(EntityDimensions.fixed(1.02f, 1.02f)).build());
    public static final EntityType<CorruptSlimeEntity> CORRUPT_SLIME = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(TerrariaMod.MOD_ID, "corrupt_slime"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, CorruptSlimeEntity::new)
                    .dimensions(EntityDimensions.fixed(1.02f, 1.02f)).build());
    public static final EntityType<CrimSlimeEntity> CRIM_SLIME = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(TerrariaMod.MOD_ID, "crim_slime"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, CrimSlimeEntity::new)
                    .dimensions(EntityDimensions.fixed(1.02f, 1.02f)).build());

    public static final EntityType<JungleBatEntity> JUNGLE_BAT = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(TerrariaMod.MOD_ID, "jungle_bat"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, JungleBatEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.9f)).build());

    public static final EntityType<LavaBatEntity> LAVA_BAT = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(TerrariaMod.MOD_ID, "lava_bat"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, LavaBatEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.9f)).build());

    public static final EntityType<PiranhaEntity> PIRANHA = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(TerrariaMod.MOD_ID, "piranha"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, PiranhaEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.3f)).build());

    public static final EntityType<MummyEntity> MUMMY = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(TerrariaMod.MOD_ID, "mummy"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, MummyEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build());
    public static final EntityType<DarkMummyEntity> DARK_MUMMY = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(TerrariaMod.MOD_ID, "dark_mummy"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, DarkMummyEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build());
    public static final EntityType<BloodMummyEntity> BLOOD_MUMMY = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(TerrariaMod.MOD_ID, "blood_mummy"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, BloodMummyEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build());
    public static final EntityType<LightMummyEntity> LIGHT_MUMMY = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(TerrariaMod.MOD_ID, "light_mummy"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, LightMummyEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build());
    public static final EntityType<EaterOfSoulEntity> EATER_OF_SOUL = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(TerrariaMod.MOD_ID, "eater_of_soul"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, EaterOfSoulEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 0.5f)).build());

    public static final EntityType<CrimeraEntity> CRIMERA = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(TerrariaMod.MOD_ID, "crimera"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, CrimeraEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 0.5f)).build());
    public static final EntityType<DemonEyeEntity> DEMON_EYE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(TerrariaMod.MOD_ID, "demon_eye"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, DemonEyeEntity::new)
                    .dimensions(EntityDimensions.fixed(0.7f, 0.7f)).build());
    public static final EntityType<PossessedArmorEntity> POSSESSED_ARMOR = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(TerrariaMod.MOD_ID, "possessed_armor"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, PossessedArmorEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6f, 1.9f)).build());



    public static final EntityType<RocketEntity> ROCKET = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(TerrariaMod.MOD_ID, "rocket"),
            FabricEntityTypeBuilder.<RocketEntity>create(SpawnGroup.MISC, RocketEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.5f))
                    .trackRangeBlocks(80)
                    .trackedUpdateRate(10)
                    .build()
    );
    public static final EntityType<MusketBallEntity> MUSKET_BALL = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(TerrariaMod.MOD_ID, "musket_ball"),
            FabricEntityTypeBuilder.<MusketBallEntity>create(SpawnGroup.MISC, MusketBallEntity::new)
                    .dimensions(EntityDimensions.fixed(0.05f, 0.05f))
                    .trackRangeBlocks(80)
                    .trackedUpdateRate(10)
                    .build()
    );



}
