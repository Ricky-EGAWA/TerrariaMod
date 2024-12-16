package ricky.terrariamod.block.custom;

import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.block.*;
import net.minecraft.block.enums.ChestType;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.block.entity.ChestBlockEntityRenderer;
import net.minecraft.client.render.block.entity.LightmapCoordinatesRetriever;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;
import ricky.terrariamod.TerrariaMod;

import static net.minecraft.client.render.TexturedRenderLayers.CHEST_ATLAS_TEXTURE;

public class LockedGoldenChestBlockEntityRenderer extends ChestBlockEntityRenderer<LockedGoldenChestBlockEntity> {
    private final ModelPart singleChestLid;
    private final ModelPart singleChestBase;
    private final ModelPart singleChestLatch;
    private final ModelPart doubleChestLeftLid;
    private final ModelPart doubleChestLeftBase;
    private final ModelPart doubleChestLeftLatch;
    private final ModelPart doubleChestRightLid;
    private final ModelPart doubleChestRightBase;
    private final ModelPart doubleChestRightLatch;

    private static final SpriteIdentifier SINGLE_CHEST_TEXTURE = new SpriteIdentifier(
            CHEST_ATLAS_TEXTURE,
            new Identifier(TerrariaMod.MOD_ID, "entity/chest/locked_golden_chest")
    );

    private static final SpriteIdentifier DOUBLE_CHEST_TEXTURE_LEFT = new SpriteIdentifier(
            CHEST_ATLAS_TEXTURE,
            new Identifier(TerrariaMod.MOD_ID, "entity/chest/golden_chest_left")
    );

    private static final SpriteIdentifier DOUBLE_CHEST_TEXTURE_RIGHT = new SpriteIdentifier(
            CHEST_ATLAS_TEXTURE,
            new Identifier(TerrariaMod.MOD_ID, "entity/chest/golden_chest_right")
    );

    public LockedGoldenChestBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        super(ctx);
        ModelPart modelPart = ctx.getLayerModelPart(EntityModelLayers.CHEST);
        this.singleChestBase = modelPart.getChild("bottom");
        this.singleChestLid = modelPart.getChild("lid");
        this.singleChestLatch = modelPart.getChild("lock");
        ModelPart modelPart2 = ctx.getLayerModelPart(EntityModelLayers.DOUBLE_CHEST_LEFT);
        this.doubleChestLeftBase = modelPart2.getChild("bottom");
        this.doubleChestLeftLid = modelPart2.getChild("lid");
        this.doubleChestLeftLatch = modelPart2.getChild("lock");
        ModelPart modelPart3 = ctx.getLayerModelPart(EntityModelLayers.DOUBLE_CHEST_RIGHT);
        this.doubleChestRightBase = modelPart3.getChild("bottom");
        this.doubleChestRightLid = modelPart3.getChild("lid");
        this.doubleChestRightLatch = modelPart3.getChild("lock");
    }

    protected SpriteIdentifier getTexture(LockedGoldenChestBlockEntity blockEntity, ChestType chestType) {
        if (chestType == ChestType.LEFT) {
            return DOUBLE_CHEST_TEXTURE_LEFT;
        } else if (chestType == ChestType.RIGHT) {
            return DOUBLE_CHEST_TEXTURE_RIGHT;
        } else {
            return SINGLE_CHEST_TEXTURE;
        }
    }

    @Override
    public void render(LockedGoldenChestBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        boolean bl = entity.getWorld() != null;
        BlockState blockState = bl ? entity.getCachedState() : Blocks.CHEST.getDefaultState().with(ChestBlock.FACING, Direction.SOUTH);
        ChestType chestType = blockState.contains(ChestBlock.CHEST_TYPE) ? blockState.get(ChestBlock.CHEST_TYPE) : ChestType.SINGLE;
        Block block = blockState.getBlock();
        if (block instanceof AbstractChestBlock<?> abstractChestBlock) {
            boolean bl2 = chestType != ChestType.SINGLE;
            matrices.push();
            float f = blockState.get(ChestBlock.FACING).asRotation();
            matrices.translate(0.5F, 0.5F, 0.5F);
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-f));
            matrices.translate(-0.5F, -0.5F, -0.5F);
            DoubleBlockProperties.PropertySource propertySource;
            if (bl) {
                propertySource = abstractChestBlock.getBlockEntitySource(blockState, entity.getWorld(), entity.getPos(), true);
            }
            else {
                propertySource = abstractChestBlock.getBlockEntitySource(blockState, entity.getWorld(), entity.getPos(), true);
//                propertySource = DoubleBlockProperties.PropertyRetriever::getFallback; //TODO　本当はこうだがエラーが治らない
            }

            float g = ((Float2FloatFunction)propertySource.apply(ChestBlock.getAnimationProgressRetriever(entity))).get(tickDelta);
            g = 1.0F - g;
            g = 1.0F - g * g * g;
            int i = ((Int2IntFunction)propertySource.apply(new LightmapCoordinatesRetriever())).applyAsInt(light);
            SpriteIdentifier spriteIdentifier = getTexture(entity, chestType);
            VertexConsumer vertexConsumer = spriteIdentifier.getVertexConsumer(vertexConsumers, RenderLayer::getEntityCutout);
            if (bl2) {
                if (chestType == ChestType.LEFT) {
                    this.render(matrices, vertexConsumer, this.doubleChestLeftLid, this.doubleChestLeftLatch, this.doubleChestLeftBase, g, i, overlay);
                } else {
                    this.render(matrices, vertexConsumer, this.doubleChestRightLid, this.doubleChestRightLatch, this.doubleChestRightBase, g, i, overlay);
                }
            } else {
                this.render(matrices, vertexConsumer, this.singleChestLid, this.singleChestLatch, this.singleChestBase, g, i, overlay);
            }

            matrices.pop();
        }
    }
    private void render(MatrixStack matrices, VertexConsumer vertices, ModelPart lid, ModelPart latch, ModelPart base, float openFactor, int light, int overlay) {
        lid.pitch = -(openFactor * 1.5707964F);
        latch.pitch = lid.pitch;
        lid.render(matrices, vertices, light, overlay);
        latch.render(matrices, vertices, light, overlay);
        base.render(matrices, vertices, light, overlay);
    }
}
