package ricky.terrariamod.entity.boss;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.util.Identifier;
import ricky.terrariamod.TerrariaMod;

public class EyeOfCthulhuRenderer extends MobEntityRenderer<EyeOfCthulhuEntity, EyeOfCthulhuModelBase<EyeOfCthulhuEntity>> {
    private final EyeOfCthulhuModelOne<EyeOfCthulhuEntity> modelPhaseOne;
    private final EyeOfCthulhuModelTwo<EyeOfCthulhuEntity> modelPhaseTwo;

    public EyeOfCthulhuRenderer(EntityRendererFactory.Context context) {
        super(context, new EyeOfCthulhuModelOne<>(context.getPart(EyeOfCthulhuModelOne.LAYER_LOCATION)), 0.5f);
        this.modelPhaseOne = new EyeOfCthulhuModelOne<>(context.getPart(EyeOfCthulhuModelOne.LAYER_LOCATION));
        this.modelPhaseTwo = new EyeOfCthulhuModelTwo<>(context.getPart(EyeOfCthulhuModelTwo.LAYER_LOCATION));
    }

    @Override
    public void render(EyeOfCthulhuEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        // モデルの切り替え
        if (entity.getCurrentPhase() == 2) {
            this.model = modelPhaseTwo; // キャストを追加
        } else {
            this.model = modelPhaseOne;
        }

        // レンダリング
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(EyeOfCthulhuEntity entity) {
        return entity.getCurrentPhase() == 1
                ? new Identifier(TerrariaMod.MOD_ID, "textures/entity/eye_of_cthulhu.png")
                : new Identifier(TerrariaMod.MOD_ID, "textures/entity/eye_of_cthulhu2.png");
    }
}
