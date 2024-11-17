package ricky.terrariamod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import ricky.terrariamod.block.ModBlocks;
import ricky.terrariamod.entity.ModEntities;
import ricky.terrariamod.entity.client.*;
import ricky.terrariamod.entity.client.bats.JungleBatEntityModel;
import ricky.terrariamod.entity.client.bats.JungleBatEntityRenderer;
import ricky.terrariamod.entity.client.bats.LavaBatEntityModel;
import ricky.terrariamod.entity.client.bats.LavaBatEntityRenderer;
import ricky.terrariamod.entity.client.slimes.IceSlimeModel;
import ricky.terrariamod.entity.client.slimes.IceSlimeRenderer;
import ricky.terrariamod.entity.client.slimes.SandSlimeModel;
import ricky.terrariamod.entity.client.slimes.SandSlimeRenderer;

public class TerrariaModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DEATH_WEED, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_DEATH_WEED, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SHIVER_THORN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_SHIVER_THORN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.VILE_MUSHROOM, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_VILE_MUSHROOM, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.VICIOUS_MUSHROOM, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_VICIOUS_MUSHROOM, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GLOWING_MUSHROOM, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_GLOWING_MUSHROOM, RenderLayer.getCutout());
        //tree
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EBON_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EBON_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CRIM_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CRIM_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PEARL_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PEARL_SAPLING, RenderLayer.getCutout());

        EntityRendererRegistry.register(ModEntities.PORCUPINE, PorcupineRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.PORCUPINE, PorcupineModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.FROZENZOMBIE, FrozenZombieRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.FROZENZOMBIE_LAYER, ricky.terrariamod.entity.client.FrozenZombieModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.ICE_SLIME, IceSlimeRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.ICE_SLIME_LAYER, IceSlimeModel::getInnerTexturedModelData);

        EntityRendererRegistry.register(ModEntities.SAND_SLIME, SandSlimeRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.SAND_SLIME_LAYER, SandSlimeModel::getInnerTexturedModelData);

        EntityRendererRegistry.register(ModEntities.JUNGLE_BAT, JungleBatEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.JUNGLE_BAT_LAYER, JungleBatEntityModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.LAVA_BAT, LavaBatEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.LAVA_BAT_LAYER, LavaBatEntityModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.PIRANHA, PiranhaEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.PIRANHA_LAYER, PiranhaEntityModel::getTexturedModelData);

    }
}
