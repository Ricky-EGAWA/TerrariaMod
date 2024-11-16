package ricky.terrariamod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.MossBlock;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.UndergroundConfiguredFeatures;
import ricky.terrariamod.world.ModConfiguredFeatures;

public class GlowingMossBlock extends MossBlock {

    public GlowingMossBlock(Settings settings) {
        super(settings);
    }

//    @Override
//    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
//        // カスタムフィーチャー `GLOWING_MOSS_PATCH_BONEMEAL` を使用
//        world.getRegistryManager().getOptional(RegistryKeys.CONFIGURED_FEATURE).flatMap((registry) -> {
//            return registry.getEntry(ModConfiguredFeatures.GLOWING_MOSS_PATCH_BONEMEAL);
//        }).ifPresent((reference) -> {
//            ((ConfiguredFeature) reference.value()).generate(world, world.getChunkManager().getChunkGenerator(), random, pos.up());
//        });
//    }
}
