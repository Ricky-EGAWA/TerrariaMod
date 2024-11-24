package ricky.terrariamod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import ricky.terrariamod.block.ModBlocks;
import ricky.terrariamod.entity.ModEntities;
import ricky.terrariamod.entity.client.*;
import ricky.terrariamod.entity.client.bats.JungleBatEntityModel;
import ricky.terrariamod.entity.client.bats.JungleBatEntityRenderer;
import ricky.terrariamod.entity.client.bats.LavaBatEntityModel;
import ricky.terrariamod.entity.client.bats.LavaBatEntityRenderer;
import ricky.terrariamod.entity.client.slimes.*;
import ricky.terrariamod.entity.client.zombies.*;
import ricky.terrariamod.item.ModItems;

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
        //zombie type
        EntityRendererRegistry.register(ModEntities.FROZENZOMBIE, FrozenZombieRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.FROZENZOMBIE_LAYER, FrozenZombieModel::getTexturedModelData);
        //slime type
        EntityRendererRegistry.register(ModEntities.ICE_SLIME, IceSlimeRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.ICE_SLIME_LAYER, SlimeModel::getInnerTexturedModelData);

        EntityRendererRegistry.register(ModEntities.SAND_SLIME, SandSlimeRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.SAND_SLIME_LAYER, SlimeModel::getInnerTexturedModelData);

        EntityRendererRegistry.register(ModEntities.CORRUPT_SLIME, CorruptSlimeRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.CORRUPT_SLIME_LAYER, SlimeModel::getInnerTexturedModelData);

        EntityRendererRegistry.register(ModEntities.CRIM_SLIME, CrimSlimeRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.CRIM_SLIME_LAYER, SlimeModel::getInnerTexturedModelData);
        //bat type
        EntityRendererRegistry.register(ModEntities.JUNGLE_BAT, JungleBatEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.JUNGLE_BAT_LAYER, JungleBatEntityModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.LAVA_BAT, LavaBatEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.LAVA_BAT_LAYER, LavaBatEntityModel::getTexturedModelData);
        //fish type
        EntityRendererRegistry.register(ModEntities.PIRANHA, PiranhaEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.PIRANHA_LAYER, PiranhaEntityModel::getTexturedModelData);
        //zombie type
        EntityRendererRegistry.register(ModEntities.MUMMY, MummyRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.MUMMY_LAYER, ZombieTypeModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.DARK_MUMMY, DarkMummyRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.DARK_MUMMY_LAYER, ZombieTypeModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.BLOOD_MUMMY, BloodMummyRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BLOOD_MUMMY_LAYER, ZombieTypeModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.LIGHT_MUMMY, LightMummyRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.LIGHT_MUMMY_LAYER, ZombieTypeModel::getTexturedModelData);

        registerCustomBow(ModItems.IRON_BOW); // IRON_BOW 用の登録
        registerCustomBow(ModItems.GOLD_BOW); // GOLD_BOW 用の登録
        registerCustomBow(ModItems.DIAMOND_BOW); // DIAMOND_BOW 用の登録
    }
    private static void registerCustomBow(Item item) {
        ModelPredicateProviderRegistry.register(item, new Identifier("pull"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            }
            return entity.getActiveItem() != stack ? 0.0F :
                    (float)(stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / 20.0F;
        });

        ModelPredicateProviderRegistry.register(item, new Identifier("pulling"), (stack, world, entity, seed) -> {
            return entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F;
        });
    }
}
