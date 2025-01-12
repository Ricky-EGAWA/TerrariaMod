package ricky.terrariamod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import ricky.terrariamod.block.ModBlocks;
import ricky.terrariamod.block.custom.GoldenChestBlockEntityRenderer;
import ricky.terrariamod.block.custom.LockedGoldenChestBlockEntityRenderer;
import ricky.terrariamod.block.custom.ModBlockEntities;
import ricky.terrariamod.client.render.ManaHudOverlay;
import ricky.terrariamod.client.render.SniperScopeOverlay;
import ricky.terrariamod.entity.ModEntities;
import ricky.terrariamod.entity.ammo.EnchantedSwordModel;
import ricky.terrariamod.entity.ammo.EnchantedSwordRenderer;
import ricky.terrariamod.entity.ammo.MusketBallEntityRenderer;
import ricky.terrariamod.entity.ammo.RocketEntityRenderer;
import ricky.terrariamod.entity.boss.EyeOfCthulhuModelOne;
import ricky.terrariamod.entity.boss.EyeOfCthulhuModelTwo;
import ricky.terrariamod.entity.boss.EyeOfCthulhuRenderer;
import ricky.terrariamod.entity.client.*;
import ricky.terrariamod.entity.client.bats.JungleBatEntityModel;
import ricky.terrariamod.entity.client.bats.JungleBatEntityRenderer;
import ricky.terrariamod.entity.client.bats.LavaBatEntityModel;
import ricky.terrariamod.entity.client.bats.LavaBatEntityRenderer;
import ricky.terrariamod.entity.client.slimes.*;
import ricky.terrariamod.entity.client.zombies.*;
import ricky.terrariamod.entity.magic.MagicBallModel;
import ricky.terrariamod.entity.magic.MagicBallRenderer;
import ricky.terrariamod.entity.magic.MagicMissileModel;
import ricky.terrariamod.entity.magic.MagicMissileRenderer;
import ricky.terrariamod.entity.monster.cursed_hammer.CursedHammerModel;
import ricky.terrariamod.entity.monster.cursed_hammer.CursedHammerRenderer;
import ricky.terrariamod.entity.weapon.*;
import ricky.terrariamod.entity.worm.devourer.DevourerModel;
import ricky.terrariamod.entity.worm.devourer.DevourerRenderer;
import ricky.terrariamod.event.KeyInputHandler;
import ricky.terrariamod.item.ModItems;
import ricky.terrariamod.networking.ModNetworking;

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
        EntityRendererRegistry.register(ModEntities.DUNGEON_SLIME, DungeonSlimeRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.DUNGEON_SLIME_LAYER, SlimeModel::getInnerTexturedModelData);
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
        //その他
        EntityRendererRegistry.register(ModEntities.EATER_OF_SOUL, EaterOfSoulRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.EATER_OF_SOUL, EaterOfSoulModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.CRIMERA, CrimeraRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.CRIMERA, CrimeraModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.DEMON_EYE, DemonEyeRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.DEMON_EYE, DemonEyeModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.POSSESSED_ARMOR, PossessedArmorRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.POSSESSED_ARMOR, PossessedArmorModel::getTexturedModelData);
        //magic
        EntityRendererRegistry.register(ModEntities.ENCHANTED_SWORD, EnchantedSwordRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.ENCHANTED_SWORD, EnchantedSwordModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.WATER_BOLT, MagicBallRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.WATER_BOLT, MagicBallModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.MAGIC_MISSILE, MagicMissileRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.MAGIC_MISSILE, MagicMissileModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.ROCKET, RocketEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.MUSKET_BALL, MusketBallEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.WOODEN_BOOMERANG, WoodenBoomerangRenderer::new);
        EntityRendererRegistry.register(ModEntities.CANDY_CANE_CHAKRAM, FruitcakeChakramRenderer::new);
        EntityRendererRegistry.register(ModEntities.ENCHANTED_BOOMERANG, EnchantedBoomerangRenderer::new);
        EntityRendererRegistry.register(ModEntities.FLAMARANG, FlamarangRenderer::new);
        EntityRendererRegistry.register(ModEntities.ICE_BOOMERANG, IceBoomerangRenderer::new);
        EntityRendererRegistry.register(ModEntities.SHROOMERANG, ShroomerangRenderer::new);
        EntityRendererRegistry.register(ModEntities.THORN_CHAKRAM, ThornChakramRenderer::new);
        EntityRendererRegistry.register(ModEntities.TRIMARANG, TrimarangRenderer::new);

        EntityRendererRegistry.register(ModEntities.EYE_OF_CTHULHU, EyeOfCthulhuRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(EyeOfCthulhuModelOne.LAYER_LOCATION, EyeOfCthulhuModelOne::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(EyeOfCthulhuModelTwo.LAYER_LOCATION, EyeOfCthulhuModelTwo::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.DEVOURER, DevourerRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.DEVOURER, DevourerModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.CURSED_HAMMER, CursedHammerRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.CURSED_HAMMER, CursedHammerModel::getTexturedModelData);

        registerCustomBow(ModItems.IRON_BOW); // IRON_BOW 用の登録
        registerCustomBow(ModItems.GOLD_BOW); // GOLD_BOW 用の登録
        registerCustomBow(ModItems.DIAMOND_BOW); // DIAMOND_BOW 用の登録

        //key bind
        KeyInputHandler.register();
        HudRenderCallback.EVENT.register((context, tickDelta) -> SniperScopeOverlay.renderIfUsingScope(context));
        HudRenderCallback.EVENT.register(new ManaHudOverlay());

        ModNetworking.registerS2CPackets();

        ModBlockEntities.registerBlockEntities();
        // Golden Chest のレンダラーを登録
        BlockEntityRendererRegistry.register(
                ModBlockEntities.GOLDEN_CHEST_ENTITY,
                GoldenChestBlockEntityRenderer::new // メソッド参照を使用してコンストラクタを参照
        );
        BlockEntityRendererRegistry.register(
                ModBlockEntities.LOCKED_GOLDEN_CHEST_ENTITY,
                LockedGoldenChestBlockEntityRenderer::new // メソッド参照を使用してコンストラクタを参照
        );
    }
    private static void registerCustomBow(Item item) {
        ModelPredicateProviderRegistry.register(item, new Identifier("pull"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            }
            return entity.getActiveItem() != stack ? 0.0F :
                    (float)(stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / 20.0F;
        });

        ModelPredicateProviderRegistry.register(item, new Identifier("pulling"),
                (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F
        );
    }
}
