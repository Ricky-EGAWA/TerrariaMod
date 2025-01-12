// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package ricky.terrariamod.entity.worm.devourer;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class DevourerModel extends EntityModel<Entity> {
	private final ModelPart head;
	private final ModelPart body;
	public DevourerModel(ModelPart root) {
		this.head = root.getChild("head");
		this.body = root.getChild("body");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.04F, -4.03F, -2.97F, 8.0F, 6.0F, 6.0F, new Dilation(0.0F))
		.uv(0, 12).cuboid(-3.0F, -5.0F, -2.0F, 6.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 15.0F, 0.0F));

		ModelPartData cube_r1 = head.addChild("cube_r1", ModelPartBuilder.create().uv(0, 12).cuboid(-3.03F, -0.505F, -1.98F, 6.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -4.5F, 0.0F, 0.0F, -1.5708F, 0.0F));

		ModelPartData cube_r2 = head.addChild("cube_r2", ModelPartBuilder.create().uv(4, 20).cuboid(-4.04F, -3.03F, -2.97F, 8.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(-0.04F, -1.03F, 0.03F, 0.0F, -1.5708F, 0.0F));

		ModelPartData bone2 = head.addChild("bone2", ModelPartBuilder.create(), ModelTransform.pivot(4.8998F, -18.4451F, 0.0F));

		ModelPartData cube_r3 = bone2.addChild("cube_r3", ModelPartBuilder.create().uv(0, 17).cuboid(-1.8998F, -2.75F, -1.01F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 9.0F, 0.0F, 0.0F, 0.0F, -0.3927F));

		ModelPartData cube_r4 = bone2.addChild("cube_r4", ModelPartBuilder.create().uv(0, 17).cuboid(-2.0F, -5.0F, 0.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-0.8998F, 15.4451F, -1.0F, 0.0F, 0.0F, 0.3927F));

		ModelPartData bone3 = head.addChild("bone3", ModelPartBuilder.create(), ModelTransform.pivot(-4.8998F, -18.4451F, 0.0F));

		ModelPartData cube_r5 = bone3.addChild("cube_r5", ModelPartBuilder.create().uv(0, 17).cuboid(-1.0F, -2.5F, -1.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.927F, 9.1134F, -0.01F, 0.0F, 3.1416F, 0.3927F));

		ModelPartData cube_r6 = bone3.addChild("cube_r6", ModelPartBuilder.create().uv(0, 17).cuboid(-1.0F, -2.5F, -1.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.8669F, 12.7527F, 0.0F, 0.0F, 3.1416F, -0.3927F));

		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-3.985F, 9.015F, -2.98F, 8.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(-0.055F, 7.955F, 0.01F));

		ModelPartData cube_r7 = body.addChild("cube_r7", ModelPartBuilder.create().uv(0, 0).cuboid(-4.04F, -3.03F, -2.97F, 8.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.015F, 12.015F, 0.02F, 0.0F, -1.5708F, 0.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
	// アクセサーメソッドを追加
	public ModelPart getHead() {
		return head;
	}

	public ModelPart getBody() {
		return body;
	}
}