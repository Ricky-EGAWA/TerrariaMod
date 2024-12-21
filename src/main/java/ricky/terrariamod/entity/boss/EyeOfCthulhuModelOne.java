package ricky.terrariamod.entity.boss;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import ricky.terrariamod.TerrariaMod;

public class EyeOfCthulhuModelOne<T extends EyeOfCthulhuEntity> extends EyeOfCthulhuModelBase<T> {
	public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier(TerrariaMod.MOD_ID, "eye_of_cthulhu_phase_one"), "main");

	private final ModelPart eye_of_cthulhu;
	public EyeOfCthulhuModelOne(ModelPart root) {
		this.eye_of_cthulhu = root.getChild("eye_of_cthulhu");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData eye_of_cthulhu = modelPartData.addChild("eye_of_cthulhu", ModelPartBuilder.create(), ModelTransform.pivot(-4.0F, -8.0F, 0.0F));

		ModelPartData bone = eye_of_cthulhu.addChild("bone", ModelPartBuilder.create().uv(368, 312).cuboid(-34.2275F, -6.0125F, -9.9525F, 68.0F, 12.0F, 20.0F, new Dilation(0.0F))
				.uv(190, 144).cuboid(-29.8875F, -9.9525F, -18.0525F, 60.0F, 20.0F, 36.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-25.8875F, -13.9525F, -22.0525F, 52.0F, 28.0F, 44.0F, new Dilation(0.0F))
				.uv(192, 0).cuboid(-26.1475F, -18.1325F, -17.8725F, 52.0F, 36.0F, 36.0F, new Dilation(0.0F))
				.uv(0, 216).cuboid(-30.1875F, -14.0925F, -13.9125F, 60.0F, 28.0F, 28.0F, new Dilation(0.0F))
				.uv(352, 416).cuboid(-33.8875F, -9.9525F, -6.0525F, 68.0F, 20.0F, 12.0F, new Dilation(0.0F))
				.uv(368, 256).cuboid(-30.5875F, -17.7725F, -10.1525F, 61.0F, 36.0F, 20.0F, new Dilation(0.0F))
				.uv(0, 328).cuboid(-25.8875F, -21.9525F, -14.0525F, 52.0F, 44.0F, 28.0F, new Dilation(0.0F)), ModelTransform.pivot(3.8875F, -2.0475F, -0.9475F));

		ModelPartData bone2 = eye_of_cthulhu.addChild("bone2", ModelPartBuilder.create().uv(0, 400).cuboid(-34.2275F, -6.0125F, -9.9525F, 68.0F, 12.0F, 20.0F, new Dilation(0.0F))
				.uv(192, 72).cuboid(-29.8875F, -9.9525F, -18.0525F, 60.0F, 20.0F, 36.0F, new Dilation(0.0F))
				.uv(0, 144).cuboid(-25.1481F, -14.0925F, -21.8308F, 51.0F, 28.0F, 44.0F, new Dilation(0.0F))
				.uv(190, 200).cuboid(-26.1475F, -18.1325F, -17.8725F, 52.0F, 36.0F, 36.0F, new Dilation(0.0F))
				.uv(160, 344).cuboid(-30.1875F, -14.0925F, -13.9125F, 60.0F, 28.0F, 28.0F, new Dilation(0.0F))
				.uv(0, 432).cuboid(-33.8875F, -9.9525F, -6.0525F, 68.0F, 20.0F, 12.0F, new Dilation(0.0F))
				.uv(382, 128).cuboid(-29.8875F, -17.9525F, -10.0525F, 60.0F, 36.0F, 20.0F, new Dilation(0.0F))
				.uv(336, 344).cuboid(-25.8875F, -21.9525F, -14.0525F, 52.0F, 44.0F, 28.0F, new Dilation(0.0F)), ModelTransform.of(3.8875F, -2.0475F, -0.9475F, 0.0F, -1.5708F, 0.0F));

		ModelPartData bone3 = eye_of_cthulhu.addChild("bone3", ModelPartBuilder.create().uv(176, 416).cuboid(-34.2275F, -6.0125F, -9.9525F, 68.0F, 12.0F, 20.0F, new Dilation(0.0F))
				.uv(0, 272).cuboid(-29.8875F, -9.9525F, -18.0525F, 60.0F, 20.0F, 36.0F, new Dilation(0.0F))
				.uv(0, 72).cuboid(-25.8875F, -13.9525F, -22.0525F, 52.0F, 28.0F, 44.0F, new Dilation(0.0F))
				.uv(192, 272).cuboid(-26.1475F, -18.1325F, -17.8725F, 52.0F, 36.0F, 36.0F, new Dilation(0.0F))
				.uv(366, 200).cuboid(-30.1875F, -14.0925F, -13.9125F, 60.0F, 28.0F, 28.0F, new Dilation(0.0F))
				.uv(160, 448).cuboid(-33.8875F, -9.9525F, -6.0525F, 68.0F, 20.0F, 12.0F, new Dilation(0.0F))
				.uv(384, 72).cuboid(-29.1891F, -18.1319F, -9.9525F, 59.0F, 36.0F, 20.0F, new Dilation(0.0F))
				.uv(368, 0).cuboid(-25.1491F, -22.1718F, -13.9125F, 51.0F, 44.0F, 28.0F, new Dilation(0.0F)), ModelTransform.of(3.8875F, -2.0475F, -0.9475F, 0.0F, 0.0F, 1.5708F));

		ModelPartData tail = eye_of_cthulhu.addChild("tail", ModelPartBuilder.create(), ModelTransform.pivot(-2.0F, 44.0F, -7.0F));

		ModelPartData bone4 = tail.addChild("bone4", ModelPartBuilder.create(), ModelTransform.pivot(-2.0F, -38.0F, 38.0F));

		ModelPartData cube_r1 = bone4.addChild("cube_r1", ModelPartBuilder.create().uv(64, 464).cuboid(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(16.0F, -12.0F, -4.0F, 0.2182F, 0.3491F, 0.0F));

		ModelPartData cube_r2 = bone4.addChild("cube_r2", ModelPartBuilder.create().uv(96, 464).cuboid(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -8.0F, -4.0F, -0.1745F, -0.3927F, 0.0F));

		ModelPartData cube_r3 = bone4.addChild("cube_r3", ModelPartBuilder.create().uv(32, 464).cuboid(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(8.0F, -4.0F, 0.0F, -0.3491F, -0.3927F, 0.0F));

		ModelPartData cube_r4 = bone4.addChild("cube_r4", ModelPartBuilder.create().uv(192, 128).cuboid(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.4363F, -0.48F, 0.0F));

		ModelPartData cube_r5 = bone4.addChild("cube_r5", ModelPartBuilder.create().uv(224, 128).cuboid(-1.847F, -2.4755F, 0.1168F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(-6.0F, 6.0F, 10.0F, -0.0873F, -0.48F, 0.0F));

		ModelPartData bone5 = tail.addChild("bone5", ModelPartBuilder.create(), ModelTransform.of(18.0F, -62.0F, 30.0F, 0.6473F, -0.4878F, 0.6674F));

		ModelPartData cube_r6 = bone5.addChild("cube_r6", ModelPartBuilder.create().uv(288, 128).cuboid(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.4363F, 0.48F, 0.0F));

		ModelPartData cube_r7 = bone5.addChild("cube_r7", ModelPartBuilder.create().uv(256, 128).cuboid(-2.0F, -0.8F, 2.0F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, 4.0F, 8.0F, -0.0873F, 0.48F, 0.0F));

		ModelPartData bone6 = tail.addChild("bone6", ModelPartBuilder.create(), ModelTransform.of(-0.6579F, -64.4511F, 42.5857F, 0.6473F, -0.4878F, 0.6674F));

		ModelPartData cube_r8 = bone6.addChild("cube_r8", ModelPartBuilder.create().uv(160, 328).cuboid(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(-1.1945F, -0.6368F, -10.7038F, -0.3927F, -0.48F, -0.9599F));

		ModelPartData cube_r9 = bone6.addChild("cube_r9", ModelPartBuilder.create().uv(320, 128).cuboid(-3.8524F, -1.0879F, 2.0F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(0.6579F, 3.6511F, -2.7038F, -0.0436F, -0.48F, -0.9599F));

		ModelPartData bone7 = tail.addChild("bone7", ModelPartBuilder.create(), ModelTransform.of(18.0F, -38.0F, 38.0F, 0.0F, 0.0F, -1.309F));

		ModelPartData cube_r10 = bone7.addChild("cube_r10", ModelPartBuilder.create().uv(176, 400).cuboid(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.4363F, -0.48F, 0.0F));

		ModelPartData cube_r11 = bone7.addChild("cube_r11", ModelPartBuilder.create().uv(382, 184).cuboid(-1.847F, -2.4755F, 0.1168F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(-6.0F, 6.0F, 10.0F, -0.8636F, -0.6627F, 0.2237F));

		ModelPartData bone8 = tail.addChild("bone8", ModelPartBuilder.create(), ModelTransform.pivot(-10.0F, -50.0F, 30.0F));

		ModelPartData cube_r12 = bone8.addChild("cube_r12", ModelPartBuilder.create().uv(240, 400).cuboid(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.4363F, -0.48F, 0.0F));

		ModelPartData cube_r13 = bone8.addChild("cube_r13", ModelPartBuilder.create().uv(208, 400).cuboid(-1.847F, -2.4755F, 0.1168F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(-6.0F, 6.0F, 10.0F, -0.1603F, -0.2648F, -0.039F));

		ModelPartData bone9 = tail.addChild("bone9", ModelPartBuilder.create(), ModelTransform.of(26.0F, -50.0F, 30.0F, 0.0F, 0.0F, -1.7017F));

		ModelPartData cube_r14 = bone9.addChild("cube_r14", ModelPartBuilder.create().uv(304, 400).cuboid(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.4363F, -0.48F, 0.0F));

		ModelPartData cube_r15 = bone9.addChild("cube_r15", ModelPartBuilder.create().uv(272, 400).cuboid(-1.847F, -2.4755F, 0.1168F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(-6.0F, 6.0F, 10.0F, -0.3054F, -0.48F, 0.0F));

		ModelPartData bone10 = tail.addChild("bone10", ModelPartBuilder.create(), ModelTransform.of(6.0F, -66.0F, 30.0F, 0.3054F, 0.0F, 2.5744F));

		ModelPartData cube_r16 = bone10.addChild("cube_r16", ModelPartBuilder.create().uv(446, 184).cuboid(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.4363F, -0.48F, 0.0F));

		ModelPartData cube_r17 = bone10.addChild("cube_r17", ModelPartBuilder.create().uv(414, 184).cuboid(-1.847F, -2.4755F, 0.1168F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(-6.0F, 6.0F, 10.0F, -0.829F, -0.48F, 0.0F));

		ModelPartData bone11 = tail.addChild("bone11", ModelPartBuilder.create(), ModelTransform.of(10.0F, -30.0F, 34.0F, 0.0F, 0.0F, -0.8727F));

		ModelPartData cube_r18 = bone11.addChild("cube_r18", ModelPartBuilder.create().uv(352, 448).cuboid(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.4363F, -0.48F, 0.0F));

		ModelPartData cube_r19 = bone11.addChild("cube_r19", ModelPartBuilder.create().uv(320, 448).cuboid(-1.847F, -2.4755F, 0.1168F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(-6.0F, 6.0F, 10.0F, -0.0873F, -0.48F, 0.0F));

		ModelPartData bone12 = tail.addChild("bone12", ModelPartBuilder.create(), ModelTransform.of(10.0F, -50.0F, 34.0F, 0.6545F, 0.0F, 0.0F));

		ModelPartData cube_r20 = bone12.addChild("cube_r20", ModelPartBuilder.create().uv(416, 448).cuboid(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.4363F, -0.48F, 0.0F));

		ModelPartData cube_r21 = bone12.addChild("cube_r21", ModelPartBuilder.create().uv(384, 448).cuboid(-1.847F, -2.4755F, 0.1168F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(-6.0F, 6.0F, 10.0F, -0.0873F, -0.48F, 0.0F));

		ModelPartData bone13 = tail.addChild("bone13", ModelPartBuilder.create(), ModelTransform.of(14.0F, -46.0F, 34.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData cube_r22 = bone13.addChild("cube_r22", ModelPartBuilder.create().uv(0, 464).cuboid(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.4363F, -0.48F, 0.0F));

		ModelPartData cube_r23 = bone13.addChild("cube_r23", ModelPartBuilder.create().uv(448, 448).cuboid(-1.847F, -2.4755F, 0.1168F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(-6.0F, 6.0F, 10.0F, -0.2435F, -0.3109F, -0.0472F));
		return TexturedModelData.of(modelData, 1024, 1024);
	}
	@Override
	public void setAngles(EyeOfCthulhuEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		// netHeadYaw（ヨー）と headPitch（ピッチ）をモデルの回転に反映
		eye_of_cthulhu.yaw = netHeadYaw * ((float)Math.PI / 180F);
		eye_of_cthulhu.pitch = headPitch * ((float)Math.PI / 180F);
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