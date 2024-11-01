// Made with Blockbench 4.11.1
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
package ricky.terrariamod.entity.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import ricky.terrariamod.entity.custom.IceSlimeEntity;

public class IceSlimeModel<T extends IceSlimeEntity> extends SinglePartEntityModel<T> {
	private final ModelPart ice_slime;
//	private final ModelPart eye0;=
//	private final ModelPart eye1;
//	private final ModelPart mouth;
	public IceSlimeModel(ModelPart root) {
		this.ice_slime = root.getChild("ice_slime");
//		this.eye0 = root.getChild("eye0");
//		this.eye1 = root.getChild("eye1");
//		this.mouth = root.getChild("mouth");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData ice_slime = modelPartData.addChild("ice_slime", ModelPartBuilder.create().uv(0, 16).cuboid(-3.0F, 17.0F, -3.0F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-4.0F, 16.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData eye0 = ice_slime.addChild("eye0", ModelPartBuilder.create().uv(32, 0).cuboid(-3.3F, 18.0F, -3.5F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData eye1 = ice_slime.addChild("eye1", ModelPartBuilder.create().uv(32, 4).cuboid(1.3F, 18.0F, -3.5F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData mouth = ice_slime.addChild("mouth", ModelPartBuilder.create().uv(32, 8).cuboid(0.0F, 21.0F, -3.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 32);
	}

@Override
public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
	// 透明度を考慮して色を設定
	vertexConsumer = vertexConsumer.color(red, green, blue, alpha); // alphaは透明度の値

	// 半透明を適用するための設定
	// これにより、半透明なオブジェクトの描画が適切に行われます
	RenderSystem.enableBlend();
	RenderSystem.defaultBlendFunc(); // デフォルトのブレンド関数を使用

	// スライムモデルを描画
	ice_slime.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);

	// ブレンドを無効に戻します
	RenderSystem.disableBlend();
}


	@Override
	public ModelPart getPart() {
		return ice_slime;
	}

	@Override
	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

	}
}