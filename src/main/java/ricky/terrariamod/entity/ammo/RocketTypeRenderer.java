package ricky.terrariamod.entity.ammo;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

@Environment(EnvType.CLIENT)
public abstract class RocketTypeRenderer<T extends PersistentProjectileEntity> extends EntityRenderer<T> {
    public RocketTypeRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    public void render(T persistentProjectileEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(g, persistentProjectileEntity.prevYaw, persistentProjectileEntity.getYaw()) - 90.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(g, persistentProjectileEntity.prevPitch, persistentProjectileEntity.getPitch())));
//        int j = false;
        float h = 0.0F;
        float k = 0.5F;
        float l = 0.0F;
        float m = 0.15625F;
        float n = 0.0F;
        float o = 0.15625F;
        float p = 0.15625F;
        float q = 0.3125F;
        float r = 0.05625F;
        float s = (float)persistentProjectileEntity.shake - g;
        if (s > 0.0F) {
            float t = -MathHelper.sin(s * 3.0F) * s;
            matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(t));
        }

        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(45.0F));
        matrixStack.scale(0.05625F, 0.05625F, 0.05625F);
        matrixStack.translate(-4.0F, 0.0F, 0.0F);
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutout(this.getTexture(persistentProjectileEntity)));
        MatrixStack.Entry entry = matrixStack.peek();
        Matrix4f matrix4f = entry.getPositionMatrix();
        Matrix3f matrix3f = entry.getNormalMatrix();
        this.vertex(matrix4f, matrix3f, vertexConsumer, 2, -3, -3, 0.0F, 0.21875F, -1, 0, 0, i);
        this.vertex(matrix4f, matrix3f, vertexConsumer, 2, -3, 3, 0.21875F, 0.21875F, -1, 0, 0, i);
        this.vertex(matrix4f, matrix3f, vertexConsumer, 2, 3, 3, 0.21875F, 0.4375F, -1, 0, 0, i);
        this.vertex(matrix4f, matrix3f, vertexConsumer, 2, 3, -3, 0.0F, 0.4375F, -1, 0, 0, i);
        this.vertex(matrix4f, matrix3f, vertexConsumer, 2, 3, -3, 0.0F, 0.21875F, 1, 0, 0, i);
        this.vertex(matrix4f, matrix3f, vertexConsumer, 2, 3, 3, 0.21875F, 0.21875F, 1, 0, 0, i);
        this.vertex(matrix4f, matrix3f, vertexConsumer, 2, -3, 3, 0.21875F, 0.4375F, 1, 0, 0, i);
        this.vertex(matrix4f, matrix3f, vertexConsumer, 2, -3, -3, 0.0F, 0.4375F, 1, 0, 0, i);

        for(int u = 0; u < 4; ++u) {
            matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90.0F));
            this.vertex(matrix4f, matrix3f, vertexConsumer, 0, -3, 0, 0.0F, 0.0F, 0, 1, 0, i);
            this.vertex(matrix4f, matrix3f, vertexConsumer, 10, -3, 0, 0.3125F, 0.0F, 0, 1, 0, i);
            this.vertex(matrix4f, matrix3f, vertexConsumer, 10, 3, 0, 0.3125F, 0.21875F, 0, 1, 0, i);
            this.vertex(matrix4f, matrix3f, vertexConsumer, 0, 3, 0, 0.0F, 0.21875F, 0, 1, 0, i);
        }

        matrixStack.pop();
        super.render(persistentProjectileEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    public void vertex(Matrix4f positionMatrix, Matrix3f normalMatrix, VertexConsumer vertexConsumer, int x, int y, int z, float u, float v, int normalX, int normalZ, int normalY, int light) {
        vertexConsumer.vertex(positionMatrix, (float)x, (float)y, (float)z).color(255, 255, 255, 255).texture(u, v).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(normalMatrix, (float)normalX, (float)normalY, (float)normalZ).next();
    }
}
