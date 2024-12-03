package ricky.terrariamod.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import ricky.terrariamod.entity.custom.EaterOfSoulEntity;

public class EaterOfSoulModel<T extends EaterOfSoulEntity> extends SinglePartEntityModel<T> {
	private final ModelPart eater;
	public EaterOfSoulModel(ModelPart root) {
		this.eater = root.getChild("eater");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData eater = modelPartData.addChild("eater", ModelPartBuilder.create(), ModelTransform.of(0.0F, 21.0F, 0.0F, -1.1781F, 0.0F, 0.0F));

		ModelPartData body = eater.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -15.0F, -2.0F, 8.0F, 8.0F, 5.0F, new Dilation(0.0F))
				.uv(14, 20).cuboid(4.0F, -14.0F, -1.0F, 2.0F, 6.0F, 3.0F, new Dilation(0.0F))
				.uv(24, 20).cuboid(-6.0F, -14.0F, -1.0F, 2.0F, 6.0F, 3.0F, new Dilation(0.0F))
				.uv(0, 13).cuboid(-3.0F, -19.0F, -1.0F, 6.0F, 4.0F, 3.0F, new Dilation(0.0F))
				.uv(20, 29).cuboid(-2.0F, -22.0F, 0.0F, 4.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(30, 29).cuboid(-1.0F, -11.0F, -3.5F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
				.uv(0, 32).cuboid(-1.0F, -25.0F, 0.0F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(18, 13).cuboid(-3.0F, -14.0F, 3.0F, 6.0F, 6.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 20).cuboid(-3.0F, -14.0F, -3.0F, 6.0F, 6.0F, 1.0F, new Dilation(0.0F))
				.uv(34, 13).cuboid(1.0F, -7.0F, 0.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(34, 13).cuboid(-1.0F, -7.0F, 0.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 15.0F, 0.0F));

		ModelPartData horn = eater.addChild("horn", ModelPartBuilder.create().uv(26, 0).cuboid(2.0F, -9.0F, -1.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F))
				.uv(26, 5).cuboid(3.0F, -7.0F, -1.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F))
				.uv(26, 10).cuboid(3.0F, -5.0F, 0.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(34, 24).cuboid(3.0F, -3.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(34, 33).cuboid(2.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 17.0F, 0.0F));

		ModelPartData horn2 = eater.addChild("horn2", ModelPartBuilder.create().uv(0, 27).cuboid(2.0F, -9.0F, -1.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F))
				.uv(10, 29).cuboid(1.0F, -7.0F, -1.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F))
				.uv(32, 10).cuboid(1.0F, -5.0F, 0.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(24, 35).cuboid(2.0F, -3.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 36).cuboid(3.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-6.0F, 17.0F, 0.0F));

		ModelPartData bone = eater.addChild("bone", ModelPartBuilder.create().uv(6, 32).cuboid(7.0F, -16.0F, 0.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(20, 33).cuboid(7.0F, -23.0F, 0.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(16, 34).cuboid(-8.0F, -23.0F, 0.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(36, 0).cuboid(6.0F, -20.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(8, 36).cuboid(-7.0F, -20.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(36, 3).cuboid(4.0F, -23.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(12, 36).cuboid(-5.0F, -23.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(4, 36).cuboid(5.0F, -25.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(36, 16).cuboid(-6.0F, -25.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(30, 33).cuboid(3.0F, -28.0F, 0.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(34, 20).cuboid(-4.0F, -28.0F, 0.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(36, 6).cuboid(0.0F, -31.5F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(32, 16).cuboid(-8.0F, -16.0F, 0.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(10, 27).cuboid(6.0F, -13.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(34, 36).cuboid(3.0F, -21.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(38, 11).cuboid(-4.0F, -21.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(20, 37).cuboid(2.0F, -25.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(38, 13).cuboid(-3.0F, -25.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(28, 37).cuboid(0.0F, -29.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(38, 9).cuboid(-1.0F, -29.5F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(24, 33).cuboid(4.0F, -18.0F, 0.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(10, 34).cuboid(-6.0F, -18.0F, 0.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(34, 27).cuboid(-7.0F, -13.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 17.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(EaterOfSoulEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		eater.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return eater;
	}
}