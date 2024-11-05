package ricky.terrariamod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import ricky.terrariamod.entity.ModEntities;
import ricky.terrariamod.entity.client.*;
import ricky.terrariamod.entity.client.slimes.IceSlimeModel;
import ricky.terrariamod.entity.client.slimes.IceSlimeRenderer;
import ricky.terrariamod.entity.client.slimes.SandSlimeModel;
import ricky.terrariamod.entity.client.slimes.SandSlimeRenderer;

public class TerrariaModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(ModEntities.PORCUPINE, PorcupineRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.PORCUPINE, PorcupineModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.FROZENZOMBIE, FrozenZombieRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.FROZENZOMBIE_LAYER, ricky.terrariamod.entity.client.FrozenZombieModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.ICE_SLIME, IceSlimeRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.ICE_SLIME_LAYER, IceSlimeModel::getInnerTexturedModelData);

        EntityRendererRegistry.register(ModEntities.SAND_SLIME, SandSlimeRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.SAND_SLIME_LAYER, SandSlimeModel::getInnerTexturedModelData);

    }
}
