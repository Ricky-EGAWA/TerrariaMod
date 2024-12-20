package ricky.terrariamod.entity.boss;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import ricky.terrariamod.entity.custom.DemonEyeEntity;

public class EyeOfCthulhuModel<T extends EyeOfCthulhuEntity> extends SinglePartEntityModel<T> {
	private final ModelPart eye_of_cthulhu;
	public EyeOfCthulhuModel(ModelPart root) {
		this.eye_of_cthulhu = root.getChild("demon_eye");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData demon_eye = modelPartData.addChild("demon_eye", ModelPartBuilder.create().uv(0, 42).cuboid(-3.0F, -22.0F, -3.0F, 7.0F, 7.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 36.0F, 0.0F));

		ModelPartData bone = demon_eye.addChild("bone", ModelPartBuilder.create().uv(32, 14).cuboid(-2.025F, -20.015F, -4.945F, 5.0F, 3.0F, 11.0F, new Dilation(0.0F))
		.uv(32, 28).cuboid(-3.035F, -21.025F, -3.955F, 7.0F, 5.0F, 9.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r1 = bone.addChild("cube_r1", ModelPartBuilder.create().uv(32, 14).cuboid(-2.5F, -1.5F, -5.5F, 5.0F, 3.0F, 11.0F, new Dilation(0.0F))
		.uv(32, 28).cuboid(-3.5F, -2.5F, -4.5F, 7.0F, 5.0F, 9.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, -18.5F, 0.5F, 0.0F, 0.0F, 1.5708F));

		ModelPartData bone2 = demon_eye.addChild("bone2", ModelPartBuilder.create().uv(0, 0).cuboid(-2.51F, -1.505F, -5.47F, 5.0F, 3.0F, 11.0F, new Dilation(0.0F))
		.uv(0, 14).cuboid(-3.52F, -2.515F, -4.48F, 7.0F, 5.0F, 9.0F, new Dilation(0.0F)), ModelTransform.of(0.485F, -18.51F, 0.525F, 0.0F, -1.5708F, 0.0F));

		ModelPartData cube_r2 = bone2.addChild("cube_r2", ModelPartBuilder.create().uv(32, 0).cuboid(-2.5F, -1.5F, -5.5F, 5.0F, 3.0F, 11.0F, new Dilation(0.0F))
		.uv(0, 28).cuboid(-3.5F, -2.5F, -4.5F, 7.0F, 5.0F, 9.0F, new Dilation(0.0F)), ModelTransform.of(0.015F, 0.01F, -0.025F, 0.0F, 0.0F, 1.5708F));

		ModelPartData bone3 = demon_eye.addChild("bone3", ModelPartBuilder.create().uv(0, 0).cuboid(-2.51F, -1.505F, -5.47F, 5.0F, 3.0F, 11.0F, new Dilation(0.0F))
		.uv(0, 14).cuboid(-3.52F, -2.515F, -4.48F, 7.0F, 5.0F, 9.0F, new Dilation(0.0F)), ModelTransform.of(0.485F, -18.51F, 0.525F, -1.5708F, -1.5708F, 0.0F));

		ModelPartData cube_r3 = bone3.addChild("cube_r3", ModelPartBuilder.create().uv(32, 0).cuboid(-2.5F, -1.5F, -5.5F, 5.0F, 3.0F, 11.0F, new Dilation(0.0F))
		.uv(0, 28).cuboid(-3.5F, -2.5F, -4.5F, 7.0F, 5.0F, 9.0F, new Dilation(0.0F)), ModelTransform.of(0.015F, 0.01F, -0.025F, 0.0F, 0.0F, 1.5708F));

		ModelPartData tail = demon_eye.addChild("tail", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -12.0F, 0.0F));

		ModelPartData cube_r4 = tail.addChild("cube_r4", ModelPartBuilder.create().uv(28, 42).cuboid(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, -4.5F, 12.0F, -0.2618F, 0.3491F, 0.0F));

		ModelPartData cube_r5 = tail.addChild("cube_r5", ModelPartBuilder.create().uv(28, 42).cuboid(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(1.5F, -4.5F, 9.0F, -0.2618F, 0.3491F, 0.0F));

		ModelPartData cube_r6 = tail.addChild("cube_r6", ModelPartBuilder.create().uv(28, 42).cuboid(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(2.5F, -6.5F, 6.0F, -0.2618F, 0.3491F, 0.0F));

		ModelPartData cube_r7 = tail.addChild("cube_r7", ModelPartBuilder.create().uv(28, 42).cuboid(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(1.5F, -6.5F, 7.0F, -0.2618F, 0.3491F, 0.0F));

		ModelPartData cube_r8 = tail.addChild("cube_r8", ModelPartBuilder.create().uv(28, 42).cuboid(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.8138F, -4.6771F, 7.6158F, -0.1745F, -0.3491F, -0.1309F));

		ModelPartData cube_r9 = tail.addChild("cube_r9", ModelPartBuilder.create().uv(28, 42).cuboid(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(1.2816F, -7.7776F, 9.6158F, 0.3054F, 0.2182F, 0.0F));

		ModelPartData cube_r10 = tail.addChild("cube_r10", ModelPartBuilder.create().uv(28, 42).cuboid(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -5.0F, 7.0F, 0.3054F, -0.3054F, 0.0F));

		ModelPartData cube_r11 = tail.addChild("cube_r11", ModelPartBuilder.create().uv(28, 42).cuboid(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -7.0F, 11.0F, 0.3054F, -0.3054F, 0.0F));

		ModelPartData cube_r12 = tail.addChild("cube_r12", ModelPartBuilder.create().uv(28, 42).cuboid(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -6.0F, 10.0F, 0.3054F, -0.3054F, 0.0F));

		ModelPartData cube_r13 = tail.addChild("cube_r13", ModelPartBuilder.create().uv(28, 42).cuboid(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(2.2816F, -8.7776F, 5.6158F, 0.3054F, 0.3491F, 0.0F));

		ModelPartData cube_r14 = tail.addChild("cube_r14", ModelPartBuilder.create().uv(28, 42).cuboid(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.2816F, -8.7776F, 6.6158F, 0.3054F, -0.3054F, 0.0F));

		ModelPartData cube_r15 = tail.addChild("cube_r15", ModelPartBuilder.create().uv(28, 42).cuboid(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -9.0F, 5.0F, 0.3054F, -0.3054F, 0.0F));

		ModelPartData cube_r16 = tail.addChild("cube_r16", ModelPartBuilder.create().uv(28, 42).cuboid(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-2.7184F, -5.7776F, 5.6158F, -0.2618F, -0.3491F, 0.0F));

		ModelPartData cube_r17 = tail.addChild("cube_r17", ModelPartBuilder.create().uv(28, 42).cuboid(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -7.0F, 5.0F, 0.3054F, -0.3054F, 0.0F));

		ModelPartData cube_r18 = tail.addChild("cube_r18", ModelPartBuilder.create().uv(28, 42).cuboid(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -6.0F, 7.0F, 0.3054F, -0.3054F, 0.0F));

		ModelPartData cube_r19 = tail.addChild("cube_r19", ModelPartBuilder.create().uv(28, 42).cuboid(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.7184F, -5.7776F, 12.6158F, -0.3054F, -0.4363F, 0.0F));

		ModelPartData cube_r20 = tail.addChild("cube_r20", ModelPartBuilder.create().uv(28, 42).cuboid(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-0.7184F, -3.7776F, 10.6158F, -0.3054F, -0.4363F, 0.0F));

		ModelPartData cube_r21 = tail.addChild("cube_r21", ModelPartBuilder.create().uv(28, 42).cuboid(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, -6.5F, 11.0F, -0.1745F, 0.0436F, 0.0F));

		ModelPartData cube_r22 = tail.addChild("cube_r22", ModelPartBuilder.create().uv(28, 42).cuboid(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(2.4981F, -5.5F, 10.9128F, -0.2618F, 0.2618F, 0.0F));

		ModelPartData cube_r23 = tail.addChild("cube_r23", ModelPartBuilder.create().uv(28, 42).cuboid(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, -6.5F, 8.0F, -0.1745F, 0.0436F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(EyeOfCthulhuEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		eye_of_cthulhu.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return eye_of_cthulhu;
	}
}