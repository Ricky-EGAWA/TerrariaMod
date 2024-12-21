package ricky.terrariamod.entity.boss;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;

public abstract class EyeOfCthulhuModelBase<T extends EyeOfCthulhuEntity> extends SinglePartEntityModel<T> {

    // 共通の初期化処理（サブクラスで拡張可能）
    public EyeOfCthulhuModelBase() {
        // 共通の初期化処理（必要なら）
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
    }

    /**
     * アニメーションの更新。サブクラスで上書き可能。
     */
    @Override
    public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        // 共通のアニメーションロジックを記述（必要なら）
    }
}
