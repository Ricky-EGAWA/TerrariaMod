package ricky.terrariamod.world.dimension;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;
import ricky.terrariamod.TerrariaMod;

import java.util.OptionalLong;

public class ModDimensions {
    public static final RegistryKey<DimensionOptions> TERRARIA_KEY = RegistryKey.of(RegistryKeys.DIMENSION,
            new Identifier(TerrariaMod.MOD_ID, "terraria"));
    public static final RegistryKey<World> TERRARIA_LEVEL_KEY = RegistryKey.of(RegistryKeys.WORLD,
            new Identifier(TerrariaMod.MOD_ID, "terraria"));
    public static final RegistryKey<DimensionType> TERRARIA_DIM_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
            new Identifier(TerrariaMod.MOD_ID, "terraria_type"));
    public static final RegistryKey<DimensionType> TERRARIA_UNDERGROUND_DIM_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
            new Identifier(TerrariaMod.MOD_ID, "terraria_underground_type"));

    public static void bootstrapType(Registerable<DimensionType> context) {
        context.register(TERRARIA_DIM_TYPE, new DimensionType(
                OptionalLong.empty(), // fixedTime
                false, // hasSkylight
                false, // hasCeiling
                false, // ultraWarm
                true, // natural
                1.0, // coordinateScale
                true, // bedWorks
                false, // respawnAnchorWorks
                -64, // minY
                256, // height
                256, // logicalHeight
                BlockTags.INFINIBURN_OVERWORLD, // infiniburn
                DimensionTypes.OVERWORLD_ID, // effectsLocation
                1.0f, // ambientLight
                new DimensionType.MonsterSettings(false, false, UniformIntProvider.create(0, 0), 0)));
        context.register(TERRARIA_UNDERGROUND_DIM_TYPE, new DimensionType(
                OptionalLong.empty(), // fixedTime
                false, // hasSkylight
                false, // hasCeiling
                false, // ultraWarm
                true, // natural
                1.0, // coordinateScale
                true, // bedWorks
                false, // respawnAnchorWorks
                -64, // minY
                256, // height
                256, // logicalHeight
                BlockTags.INFINIBURN_OVERWORLD, // infiniburn
                DimensionTypes.OVERWORLD_ID, // effectsLocation
                1.0f, // ambientLight
                new DimensionType.MonsterSettings(false, false, UniformIntProvider.create(0, 0), 0)));
    }
}
