package ricky.terrariamod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import ricky.terrariamod.entity.ModEntities;
import ricky.terrariamod.entity.client.*;

public class TerrariaModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(ModEntities.PORCUPINE, PorcupineRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.PORCUPINE, PorcupineModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.FROZENZOMBIE, FrozenZombieRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.FROZENZOMBIE_LAYER, FrozenZombieModel::getTexturedModelData);

    }
}
