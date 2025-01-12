package ricky.terrariamod.entity.worm.devourer;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import ricky.terrariamod.TerrariaMod;
import ricky.terrariamod.entity.client.ModModelLayers;

public class DevourerRenderer extends EntityRenderer<DevourerEntity> {
    private final DevourerModel model;

    public DevourerRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new DevourerModel(context.getPart(ModModelLayers.DEVOURER)); // モデルを登録
    }

    @Override
    public void render(DevourerEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        // テクスチャを取得
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutout(this.getTexture(entity)));

        // 頭を描画
        matrices.push();
        this.model.getHead().render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
        matrices.pop();
        DevourerPartEntity head = entity.getHead();

        // 体パーツを順番に描画
        for (int i = 0; i < entity.getBodyParts().length; i++) {
            DevourerPartEntity part = entity.getBodyParts()[i];

            // 各パーツの位置に基づいて描画する
            matrices.push();
//            matrices.translate(0, i*0.375f, 0); // 必要に応じて体の位置を調整
            matrices.translate(head.getX()-part.getX(), head.getY()-part.getY(), head.getZ()-part.getZ()); // 必要に応じて体の位置を調整
//            System.out.println("render pos: "+part.getX()+" "+part.getY()+" "+part.getZ());
            this.model.getBody().render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
            matrices.pop();
        }

        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }


    @Override
    public Identifier getTexture(DevourerEntity entity) {
        return new Identifier(TerrariaMod.MOD_ID, "textures/entity/devourer.png"); // テクスチャパス
    }
}
