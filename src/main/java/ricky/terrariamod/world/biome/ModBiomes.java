package ricky.terrariamod.world.biome;

import net.minecraft.client.sound.MusicType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import ricky.terrariamod.TerrariaMod;
import ricky.terrariamod.entity.ModEntities;

public class ModBiomes {
    public static final RegistryKey<Biome> CRIM_DESERT_BIOME = RegistryKey.of(RegistryKeys.BIOME,
            new Identifier(TerrariaMod.MOD_ID, "crim_desert_biome"));
    public static final RegistryKey<Biome> EBON_DESERT_BIOME = RegistryKey.of(RegistryKeys.BIOME,
            new Identifier(TerrariaMod.MOD_ID, "ebon_desert_biome"));
    public static final RegistryKey<Biome> PEARL_DESERT_BIOME = RegistryKey.of(RegistryKeys.BIOME,
            new Identifier(TerrariaMod.MOD_ID, "pearl_desert_biome"));
    public static final RegistryKey<Biome> CRIM_BIOME = RegistryKey.of(RegistryKeys.BIOME,
            new Identifier(TerrariaMod.MOD_ID, "crim_biome"));
    public static final RegistryKey<Biome> EBON_BIOME = RegistryKey.of(RegistryKeys.BIOME,
            new Identifier(TerrariaMod.MOD_ID, "ebon_biome"));
    public static final RegistryKey<Biome> PEARL_BIOME = RegistryKey.of(RegistryKeys.BIOME,
            new Identifier(TerrariaMod.MOD_ID, "pearl_biome"));
    public static final RegistryKey<Biome> CRIM_ICE_BIOME = RegistryKey.of(RegistryKeys.BIOME,
            new Identifier(TerrariaMod.MOD_ID, "crim_ice_biome"));
    public static final RegistryKey<Biome> EBON_ICE_BIOME = RegistryKey.of(RegistryKeys.BIOME,
            new Identifier(TerrariaMod.MOD_ID, "ebon_ice_biome"));
    public static final RegistryKey<Biome> PEARL_ICE_BIOME = RegistryKey.of(RegistryKeys.BIOME,
            new Identifier(TerrariaMod.MOD_ID, "pearl_ice_biome"));
    //地下
    public static final RegistryKey<Biome> GLOWING_MUSHROOM_BIOME = RegistryKey.of(RegistryKeys.BIOME,
            new Identifier(TerrariaMod.MOD_ID, "glowing_mushroom_biome"));

    public static void bootstrap(Registerable<Biome> context) {
        context.register(CRIM_DESERT_BIOME, crimDesertBiome(context));
        context.register(EBON_DESERT_BIOME, ebonDesertBiome(context));
        context.register(PEARL_DESERT_BIOME, pearlDesertBiome(context));
        context.register(CRIM_BIOME, crimBiome(context));
        context.register(EBON_BIOME, ebonBiome(context));
        context.register(PEARL_BIOME, pearlBiome(context));
        context.register(CRIM_ICE_BIOME, crimIceBiome(context));
        context.register(EBON_ICE_BIOME, ebonIceBiome(context));
        context.register(PEARL_ICE_BIOME, pearlIceBiome(context));
        context.register(GLOWING_MUSHROOM_BIOME, glowingMushroomBiome(context));
    }
    public static void globalOverworldGeneration(GenerationSettings.LookupBackedBuilder builder) {
        DefaultBiomeFeatures.addLandCarvers(builder);
        DefaultBiomeFeatures.addAmethystGeodes(builder);
        DefaultBiomeFeatures.addDungeons(builder);
        DefaultBiomeFeatures.addMineables(builder);
        DefaultBiomeFeatures.addSprings(builder);
        DefaultBiomeFeatures.addFrozenTopLayer(builder);
    }

    public static Biome glowingMushroomBiome(Registerable<Biome> context) {
        //モブのスポーン
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();

        DefaultBiomeFeatures.addBatsAndMonsters(spawnBuilder);
        //地形の生成
        GenerationSettings.LookupBackedBuilder biomeBuilder =
                new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        globalOverworldGeneration(biomeBuilder);
        DefaultBiomeFeatures.addDefaultOres(biomeBuilder);

        // 植生デコレーション
        DefaultBiomeFeatures.addDefaultMushrooms(biomeBuilder);
        DefaultBiomeFeatures.addDefaultVegetation(biomeBuilder);

        return new Biome.Builder()
                .precipitation(true)
                .downfall(0.4f)
                .temperature(0.8f)
                .generationSettings(biomeBuilder.build())
                .spawnSettings(spawnBuilder.build())
                .effects((new BiomeEffects.Builder())
                        .waterColor(4159204)
                        .waterFogColor(329011)
                        .skyColor(7907327)
                        .grassColor(5721318)
//                        .foliageColor(0xd203fc)
                        .fogColor(12638463)
                        .moodSound(BiomeMoodSound.CAVE).build())
//                        .music(MusicType.createIngameMusic(RegistryEntry.of(ModSounds.BAR_BRAWL))).build())
                .build();
    }
    public static Biome crimDesertBiome(Registerable<Biome> context) {
        //モブのスポーン
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(ModEntities.PORCUPINE, 2, 3, 5));

        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 5, 4, 4));

        DefaultBiomeFeatures.addFarmAnimals(spawnBuilder);
        DefaultBiomeFeatures.addBatsAndMonsters(spawnBuilder);
        //地形の生成
        GenerationSettings.LookupBackedBuilder biomeBuilder =
                new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        globalOverworldGeneration(biomeBuilder);
        DefaultBiomeFeatures.addMossyRocks(biomeBuilder);
        DefaultBiomeFeatures.addDefaultOres(biomeBuilder);
        DefaultBiomeFeatures.addExtraGoldOre(biomeBuilder);

        // 植生デコレーション
        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.TREES_PLAINS);
        DefaultBiomeFeatures.addForestFlowers(biomeBuilder);
        DefaultBiomeFeatures.addLargeFerns(biomeBuilder);

        DefaultBiomeFeatures.addDefaultMushrooms(biomeBuilder);
        DefaultBiomeFeatures.addDefaultVegetation(biomeBuilder);

        return new Biome.Builder()
                .precipitation(true)
                .downfall(0.4f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .spawnSettings(spawnBuilder.build())
                .effects((new BiomeEffects.Builder())
                        .waterColor(0xe82e3b)
                        .waterFogColor(0xbf1b26)
                        .skyColor(0xe82e3b)
                        .grassColor(0x7f03fc)
                        .foliageColor(0xd203fc)
                        .fogColor(0xbf1b26)
                        .moodSound(BiomeMoodSound.CAVE).build())
//                        .music(MusicType.createIngameMusic(RegistryEntry.of(ModSounds.BAR_BRAWL))).build())
                .build();
    }
    public static Biome ebonDesertBiome(Registerable<Biome> context) {
        //モブのスポーン
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(ModEntities.JUNGLE_BAT, 100, 3, 5));

        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 5, 4, 4));

        DefaultBiomeFeatures.addFarmAnimals(spawnBuilder);
        DefaultBiomeFeatures.addBatsAndMonsters(spawnBuilder);
        //地形の生成
        GenerationSettings.LookupBackedBuilder biomeBuilder =
                new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        globalOverworldGeneration(biomeBuilder);
        DefaultBiomeFeatures.addDefaultOres(biomeBuilder);
        DefaultBiomeFeatures.addExtraGoldOre(biomeBuilder);

        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.TREES_PLAINS);
        DefaultBiomeFeatures.addForestFlowers(biomeBuilder);
        DefaultBiomeFeatures.addLargeFerns(biomeBuilder);

        DefaultBiomeFeatures.addDefaultMushrooms(biomeBuilder);
        DefaultBiomeFeatures.addDefaultVegetation(biomeBuilder);

        return new Biome.Builder()
                .precipitation(true)
                .downfall(0.4f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .spawnSettings(spawnBuilder.build())
                .effects((new BiomeEffects.Builder())
                        .waterColor(0x53527D)
                        .waterFogColor(0x53527D)
                        .skyColor(0x53527D)
                        .grassColor(0x53527D)
                        .foliageColor(0x53527D)
                        .fogColor(0x53527D)
                        .moodSound(BiomeMoodSound.CAVE).build())
//                        .music(MusicType.createIngameMusic(RegistryEntry.of(ModSounds.BAR_BRAWL))).build())
                .build();
    }
    public static Biome pearlDesertBiome(Registerable<Biome> context) {
        //モブのスポーン
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(ModEntities.JUNGLE_BAT, 100, 3, 5));

        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 5, 4, 4));

        DefaultBiomeFeatures.addFarmAnimals(spawnBuilder);
        DefaultBiomeFeatures.addBatsAndMonsters(spawnBuilder);
        //地形の生成
        GenerationSettings.LookupBackedBuilder biomeBuilder =
                new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        globalOverworldGeneration(biomeBuilder);
        DefaultBiomeFeatures.addDefaultOres(biomeBuilder);
        DefaultBiomeFeatures.addExtraGoldOre(biomeBuilder);

        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.TREES_PLAINS);
        DefaultBiomeFeatures.addForestFlowers(biomeBuilder);
        DefaultBiomeFeatures.addLargeFerns(biomeBuilder);

        DefaultBiomeFeatures.addDefaultMushrooms(biomeBuilder);
        DefaultBiomeFeatures.addDefaultVegetation(biomeBuilder);

        return new Biome.Builder()
                .precipitation(true)
                .downfall(0.4f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .spawnSettings(spawnBuilder.build())
                .effects((new BiomeEffects.Builder())
                        .waterColor(0xFF63DA)
                        .waterFogColor(0xFF63DA)
                        .skyColor(0xFF63DA)
                        .grassColor(0xFF63DA)
                        .foliageColor(0xFF63DA)
                        .fogColor(0xFF63DA)
                        .moodSound(BiomeMoodSound.CAVE).build())
//                        .music(MusicType.createIngameMusic(RegistryEntry.of(ModSounds.BAR_BRAWL))).build())
                .build();
    }
    public static Biome crimBiome(Registerable<Biome> context) {
        //モブのスポーン
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(ModEntities.PORCUPINE, 2, 3, 5));

        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 5, 4, 4));

        DefaultBiomeFeatures.addFarmAnimals(spawnBuilder);
        DefaultBiomeFeatures.addBatsAndMonsters(spawnBuilder);
        //地形の生成
        GenerationSettings.LookupBackedBuilder biomeBuilder =
                new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        globalOverworldGeneration(biomeBuilder);
        DefaultBiomeFeatures.addMossyRocks(biomeBuilder);
        DefaultBiomeFeatures.addDefaultOres(biomeBuilder);
        DefaultBiomeFeatures.addExtraGoldOre(biomeBuilder);

        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.TREES_PLAINS);
        DefaultBiomeFeatures.addForestFlowers(biomeBuilder);
        DefaultBiomeFeatures.addLargeFerns(biomeBuilder);

        DefaultBiomeFeatures.addDefaultMushrooms(biomeBuilder);
        DefaultBiomeFeatures.addDefaultVegetation(biomeBuilder);

        return new Biome.Builder()
                .precipitation(true)
                .downfall(0.4f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .spawnSettings(spawnBuilder.build())
                .effects((new BiomeEffects.Builder())
                        .waterColor(0xe82e3b)
                        .waterFogColor(0xbf1b26)
                        .skyColor(0xe82e3b)
                        .grassColor(11797508)
                        .foliageColor(0xd203fc)
                        .fogColor(0xbf1b26)
                        .moodSound(BiomeMoodSound.CAVE).build())
//                        .music(MusicType.createIngameMusic(RegistryEntry.of(ModSounds.BAR_BRAWL))).build())
                .build();
    }
    public static Biome ebonBiome(Registerable<Biome> context) {
        //モブのスポーン
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(ModEntities.JUNGLE_BAT, 100, 3, 5));

        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 5, 4, 4));

        DefaultBiomeFeatures.addFarmAnimals(spawnBuilder);
        DefaultBiomeFeatures.addBatsAndMonsters(spawnBuilder);
        //地形の生成
        GenerationSettings.LookupBackedBuilder biomeBuilder =
                new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        globalOverworldGeneration(biomeBuilder);
        DefaultBiomeFeatures.addDefaultOres(biomeBuilder);
        DefaultBiomeFeatures.addExtraGoldOre(biomeBuilder);

        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.TREES_PLAINS);
        DefaultBiomeFeatures.addForestFlowers(biomeBuilder);
        DefaultBiomeFeatures.addLargeFerns(biomeBuilder);

        DefaultBiomeFeatures.addDefaultMushrooms(biomeBuilder);
        DefaultBiomeFeatures.addDefaultVegetation(biomeBuilder);

        return new Biome.Builder()
                .precipitation(true)
                .downfall(0.4f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .spawnSettings(spawnBuilder.build())
                .effects((new BiomeEffects.Builder())
                        .waterColor(0x53527D)
                        .waterFogColor(0x53527D)
                        .skyColor(0x53527D)
                        .grassColor(0x53527D)
                        .foliageColor(0x53527D)
                        .fogColor(0x53527D)
                        .moodSound(BiomeMoodSound.CAVE).build())
//                        .music(MusicType.createIngameMusic(RegistryEntry.of(ModSounds.BAR_BRAWL))).build())
                .build();
    }
    public static Biome pearlBiome(Registerable<Biome> context) {
        //モブのスポーン
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(ModEntities.JUNGLE_BAT, 100, 3, 5));

        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 5, 4, 4));

        DefaultBiomeFeatures.addFarmAnimals(spawnBuilder);
        DefaultBiomeFeatures.addBatsAndMonsters(spawnBuilder);
        //地形の生成
        GenerationSettings.LookupBackedBuilder biomeBuilder =
                new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        globalOverworldGeneration(biomeBuilder);
        DefaultBiomeFeatures.addDefaultOres(biomeBuilder);
        DefaultBiomeFeatures.addExtraGoldOre(biomeBuilder);

        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.TREES_PLAINS);
        DefaultBiomeFeatures.addForestFlowers(biomeBuilder);
        DefaultBiomeFeatures.addLargeFerns(biomeBuilder);

        DefaultBiomeFeatures.addDefaultMushrooms(biomeBuilder);
        DefaultBiomeFeatures.addDefaultVegetation(biomeBuilder);

        return new Biome.Builder()
                .precipitation(true)
                .downfall(0.4f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .spawnSettings(spawnBuilder.build())
                .effects((new BiomeEffects.Builder())
                        .waterColor(0xFF63DA)
                        .waterFogColor(0xFF63DA)
                        .skyColor(0xFF63DA)
                        .grassColor(0xFF63DA)
                        .foliageColor(0xFF63DA)
                        .fogColor(0xFF63DA)
                        .moodSound(BiomeMoodSound.CAVE).build())
//                        .music(MusicType.createIngameMusic(RegistryEntry.of(ModSounds.BAR_BRAWL))).build())
                .build();
    }
    public static Biome crimIceBiome(Registerable<Biome> context) {
        //モブのスポーン
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(ModEntities.PORCUPINE, 2, 3, 5));

        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 5, 4, 4));

        DefaultBiomeFeatures.addFarmAnimals(spawnBuilder);
        DefaultBiomeFeatures.addBatsAndMonsters(spawnBuilder);
        //地形の生成
        GenerationSettings.LookupBackedBuilder biomeBuilder =
                new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        globalOverworldGeneration(biomeBuilder);
        DefaultBiomeFeatures.addMossyRocks(biomeBuilder);
        DefaultBiomeFeatures.addDefaultOres(biomeBuilder);
        DefaultBiomeFeatures.addExtraGoldOre(biomeBuilder);

        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.TREES_PLAINS);
        DefaultBiomeFeatures.addForestFlowers(biomeBuilder);
        DefaultBiomeFeatures.addLargeFerns(biomeBuilder);

        DefaultBiomeFeatures.addDefaultMushrooms(biomeBuilder);
        DefaultBiomeFeatures.addDefaultVegetation(biomeBuilder);

        return new Biome.Builder()
                .precipitation(true)
                .downfall(0.4f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .spawnSettings(spawnBuilder.build())
                .effects((new BiomeEffects.Builder())
                        .waterColor(0xe82e3b)
                        .waterFogColor(0xbf1b26)
                        .skyColor(0xe82e3b)
                        .grassColor(0x7f03fc)
                        .foliageColor(0xd203fc)
                        .fogColor(0xbf1b26)
                        .moodSound(BiomeMoodSound.CAVE).build())
//                        .music(MusicType.createIngameMusic(RegistryEntry.of(ModSounds.BAR_BRAWL))).build())
                .build();
    }
    public static Biome ebonIceBiome(Registerable<Biome> context) {
        //モブのスポーン
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(ModEntities.JUNGLE_BAT, 100, 3, 5));

        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 5, 4, 4));

        DefaultBiomeFeatures.addFarmAnimals(spawnBuilder);
        DefaultBiomeFeatures.addBatsAndMonsters(spawnBuilder);
        //地形の生成
        GenerationSettings.LookupBackedBuilder biomeBuilder =
                new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        globalOverworldGeneration(biomeBuilder);
        DefaultBiomeFeatures.addDefaultOres(biomeBuilder);
        DefaultBiomeFeatures.addExtraGoldOre(biomeBuilder);

        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.TREES_PLAINS);
        DefaultBiomeFeatures.addForestFlowers(biomeBuilder);
        DefaultBiomeFeatures.addLargeFerns(biomeBuilder);

        DefaultBiomeFeatures.addDefaultMushrooms(biomeBuilder);
        DefaultBiomeFeatures.addDefaultVegetation(biomeBuilder);

        return new Biome.Builder()
                .precipitation(true)
                .downfall(0.4f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .spawnSettings(spawnBuilder.build())
                .effects((new BiomeEffects.Builder())
                        .waterColor(0x53527D)
                        .waterFogColor(0x53527D)
                        .skyColor(0x53527D)
                        .grassColor(0x53527D)
                        .foliageColor(0x53527D)
                        .fogColor(0x53527D)
                        .moodSound(BiomeMoodSound.CAVE).build())
//                        .music(MusicType.createIngameMusic(RegistryEntry.of(ModSounds.BAR_BRAWL))).build())
                .build();
    }
    public static Biome pearlIceBiome(Registerable<Biome> context) {
        //モブのスポーン
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(ModEntities.JUNGLE_BAT, 100, 3, 5));

        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 5, 4, 4));

        DefaultBiomeFeatures.addFarmAnimals(spawnBuilder);
        DefaultBiomeFeatures.addBatsAndMonsters(spawnBuilder);
        //地形の生成
        GenerationSettings.LookupBackedBuilder biomeBuilder =
                new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        globalOverworldGeneration(biomeBuilder);
        DefaultBiomeFeatures.addDefaultOres(biomeBuilder);
        DefaultBiomeFeatures.addExtraGoldOre(biomeBuilder);

        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.TREES_PLAINS);
        DefaultBiomeFeatures.addForestFlowers(biomeBuilder);
        DefaultBiomeFeatures.addLargeFerns(biomeBuilder);

        DefaultBiomeFeatures.addDefaultMushrooms(biomeBuilder);
        DefaultBiomeFeatures.addDefaultVegetation(biomeBuilder);

        return new Biome.Builder()
                .precipitation(true)
                .downfall(0.4f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .spawnSettings(spawnBuilder.build())
                .effects((new BiomeEffects.Builder())
                        .waterColor(0xFF63DA)
                        .waterFogColor(0xFF63DA)
                        .skyColor(0xFF63DA)
                        .grassColor(0xFF63DA)
                        .foliageColor(0xFF63DA)
                        .fogColor(0xFF63DA)
                        .moodSound(BiomeMoodSound.CAVE).build())
//                        .music(MusicType.createIngameMusic(RegistryEntry.of(ModSounds.BAR_BRAWL))).build())
                .build();
    }
}
