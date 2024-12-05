// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package ricky.terrariamod.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import ricky.terrariamod.entity.custom.CrimeraEntity;

public class CrimeraModel<T extends CrimeraEntity> extends SinglePartEntityModel<T> {
	private final ModelPart crimera;
	public CrimeraModel(ModelPart root) {
		this.crimera = root.getChild("crimera");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData crimera = modelPartData.addChild("crimera", ModelPartBuilder.create(), ModelTransform.of(0.0F, 20.0F, 0.0F, -1.2654F, 0.0F, 0.0F));

		ModelPartData body = crimera.addChild("body", ModelPartBuilder.create().uv(0, 38).cuboid(0.0F, -35.0F, -1.5F, 1.0F, 5.0F, 2.0F, new Dilation(0.0F))
		.uv(30, 0).cuboid(-1.0F, -30.0F, -2.0F, 3.0F, 5.0F, 3.0F, new Dilation(0.0F))
		.uv(3, 21).cuboid(-2.0F, -25.0F, -2.5F, 5.0F, 3.0F, 4.0F, new Dilation(0.0F))
		.uv(0, 20).cuboid(-3.0F, -22.0F, -3.0F, 7.0F, 3.0F, 5.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-4.0F, -19.0F, -3.5F, 9.0F, 6.0F, 6.0F, new Dilation(0.0F))
		.uv(0, 12).cuboid(-3.0F, -13.0F, -3.5F, 7.0F, 2.0F, 6.0F, new Dilation(0.0F))
		.uv(24, 20).cuboid(-2.0F, -11.0F, -2.5F, 5.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 19.0F, 0.0F));

		ModelPartData bone = body.addChild("bone", ModelPartBuilder.create().uv(0, 28).cuboid(5.0F, -19.0F, -2.5F, 4.0F, 1.0F, 4.0F, new Dilation(0.0F))
		.uv(0, 33).cuboid(5.0F, -18.0F, -2.5F, 3.0F, 1.0F, 4.0F, new Dilation(0.0F))
		.uv(14, 33).cuboid(5.0F, -17.0F, -2.5F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData bone2 = body.addChild("bone2", ModelPartBuilder.create().uv(0, 28).cuboid(5.0F, -19.0F, -2.5F, 4.0F, 1.0F, 4.0F, new Dilation(0.0F))
		.uv(0, 33).cuboid(5.0F, -18.0F, -2.5F, 3.0F, 1.0F, 4.0F, new Dilation(0.0F))
		.uv(14, 33).cuboid(5.0F, -17.0F, -2.5F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.0F, -1.0F, 0.0F, 3.1416F, 0.0F));

		ModelPartData bone3 = body.addChild("bone3", ModelPartBuilder.create().uv(30, 8).cuboid(3.0F, -23.0F, -2.0F, 4.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(34, 35).cuboid(4.0F, -22.0F, -2.0F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(16, 28).cuboid(4.0F, -21.0F, -2.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData bone4 = body.addChild("bone4", ModelPartBuilder.create().uv(30, 8).cuboid(-2.3333F, -1.5F, -1.5F, 4.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(34, 35).cuboid(-1.3333F, -0.5F, -1.5F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(16, 28).cuboid(-1.3333F, 0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-4.1667F, -21.5F, -0.5F, 0.0F, 3.1416F, 0.0F));

		ModelPartData bone5 = body.addChild("bone5", ModelPartBuilder.create().uv(38, 27).cuboid(4.0F, -28.0F, -1.5F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(6, 38).cuboid(2.0F, -27.0F, -1.5F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData bone6 = body.addChild("bone6", ModelPartBuilder.create().uv(38, 27).cuboid(4.0F, -28.0F, -1.5F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(6, 38).cuboid(2.0F, -27.0F, -1.5F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.0F, -1.0F, 0.0F, 3.1416F, 0.0F));

		ModelPartData bone7 = body.addChild("bone7", ModelPartBuilder.create().uv(26, 17).cuboid(-3.0F, -31.0F, -1.0F, 5.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(38, 39).cuboid(-4.0F, -32.0F, -1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(38, 39).cuboid(2.0F, -32.0F, -1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, 0.0F, 0.0F));

		ModelPartData bone8 = body.addChild("bone8", ModelPartBuilder.create().uv(38, 17).cuboid(-2.0F, -31.0F, -1.0F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(22, 40).cuboid(-3.0F, -32.0F, -1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(26, 40).cuboid(1.0F, -32.0F, -1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(34, 39).cuboid(-1.0F, -35.0F, -1.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F))
		.uv(34, 39).cuboid(0.0F, -37.0F, -0.5F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, -4.0F, 0.0F));

		ModelPartData eye = crimera.addChild("eye", ModelPartBuilder.create().uv(24, 27).cuboid(4.0F, -13.0F, -2.5F, 3.0F, 4.0F, 4.0F, new Dilation(0.0F))
		.uv(24, 35).cuboid(5.5F, -9.0F, -2.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F))
		.uv(38, 31).cuboid(5.0F, -5.0F, -1.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(38, 33).cuboid(6.5F, -4.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(38, 33).cuboid(-6.5F, -4.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(38, 31).cuboid(-6.0F, -5.0F, -1.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(14, 38).cuboid(4.5F, -7.0F, -1.5F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(14, 38).cuboid(-5.5F, -7.0F, -1.5F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(24, 35).cuboid(-6.5F, -9.0F, -2.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F))
		.uv(24, 27).cuboid(-6.0F, -13.0F, -2.5F, 3.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 19.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(CrimeraEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		crimera.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return crimera;
	}
}