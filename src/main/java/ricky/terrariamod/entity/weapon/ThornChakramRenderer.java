package ricky.terrariamod.entity.weapon;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import ricky.terrariamod.TerrariaMod;

public class ThornChakramRenderer extends EntityRenderer<ThornChakramEntity> {
    private final ItemStack boomerangStack;

    public ThornChakramRenderer(EntityRendererFactory.Context context) {
        super(context);
        // boomerangモデルを使用
        this.boomerangStack = new ItemStack(Registries.ITEM.get(new Identifier(TerrariaMod.MOD_ID, "thorn_chakram")));
    }

    @Override
    public void render(ThornChakramEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();

        // エンティティの位置調整（必要に応じて変更）
        matrices.translate(0, 0, 0);

        // エンティティのYawとPitchを適用
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(entity.getYaw())); // Yawを適用
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(entity.getPitch())); // Pitchを適用

        // エンティティの回転を調整
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90));

        // JSONモデルを描画
        MinecraftClient.getInstance().getItemRenderer().renderItem(
                boomerangStack,
                ModelTransformationMode.GROUND,
                light,
                OverlayTexture.DEFAULT_UV,
                matrices,
                vertexConsumers,
                entity.getWorld(), // ワールドを渡す必要がある場合
                0 // シード値（ランダムエフェクトなどに使用）
        );

        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(ThornChakramEntity entity) {
        return null; // テクスチャはJSONモデルが管理
    }
}
