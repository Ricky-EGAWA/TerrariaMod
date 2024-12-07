package ricky.terrariamod.entity.ammo;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class EnchantedSwordModel<T extends EnchantedSwordEntity> extends SinglePartEntityModel<T> {
	private final ModelPart sword;
	public EnchantedSwordModel(ModelPart root) {
		this.sword = root.getChild("sword");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData sword = modelPartData.addChild("sword", ModelPartBuilder.create().uv(0, 21).cuboid(5.9F, -0.5F, 6.0333F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
				.uv(0, 5).cuboid(-0.1F, -0.5F, 4.0333F, 3.0F, 1.0F, 3.0F, new Dilation(0.0F))
				.uv(12, 9).cuboid(-0.1F, -0.5F, 0.0333F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
				.uv(0, 13).cuboid(-1.1F, -0.5F, -0.9667F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
				.uv(8, 13).cuboid(-2.1F, -0.5F, -1.9667F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
				.uv(16, 13).cuboid(-3.1F, -0.5F, -2.9667F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
				.uv(0, 17).cuboid(-4.1F, -0.5F, -3.9667F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
				.uv(8, 17).cuboid(-5.1F, -0.5F, -4.9667F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
				.uv(16, 17).cuboid(-6.1F, -0.5F, -5.9667F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
				.uv(20, 2).cuboid(-7.1F, -0.5F, -6.9667F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
				.uv(20, 9).cuboid(-8.1F, -0.5F, -7.9667F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
				.uv(0, 9).cuboid(3.9F, -0.5F, 0.0333F, 3.0F, 1.0F, 3.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(0.9F, -0.5F, 0.0333F, 2.0F, 1.0F, 4.0F, new Dilation(0.0F))
				.uv(22, 6).cuboid(-0.1F, -0.5F, -0.9667F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 24).cuboid(-1.1F, -0.5F, -1.9667F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(24, 0).cuboid(-2.1F, -0.5F, -2.9667F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(24, 13).cuboid(-3.1F, -0.5F, -3.9667F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(24, 15).cuboid(-4.1F, -0.5F, -4.9667F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(24, 17).cuboid(-5.1F, -0.5F, -5.9667F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(24, 19).cuboid(-6.1F, -0.5F, -6.9667F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(14, 25).cuboid(-7.1F, -0.5F, -7.9667F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(12, 7).cuboid(2.9F, -0.5F, 5.0333F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 26).cuboid(2.9F, -0.5F, 1.0333F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
				.uv(12, 7).cuboid(4.9F, -0.5F, 3.0333F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(13, 0).cuboid(1.9F, -0.5F, 4.0333F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(20, 25).cuboid(4.9F, -0.5F, 6.0333F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(25, 21).cuboid(4.9F, -1.0F, 1.0333F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
				.uv(25, 21).cuboid(3.9F, -1.0F, 2.0333F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.1F, 16.5F, -0.0333F, 1.5708F, 0.0F, 2.3562F));

		ModelPartData cube_r1 = sword.addChild("cube_r1", ModelPartBuilder.create().uv(25, 21).cuboid(-1.0F, -2.5F, -2.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(2.9F, 1.5F, 5.0333F, 0.0F, 1.5708F, 0.0F));

		ModelPartData cube_r2 = sword.addChild("cube_r2", ModelPartBuilder.create().uv(25, 21).cuboid(-1.0F, -2.5F, -2.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(3.9F, 1.5F, 4.0333F, 0.0F, 1.5708F, 0.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}
	@Override
	public void setAngles(EnchantedSwordEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		sword.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return sword;
	}
}