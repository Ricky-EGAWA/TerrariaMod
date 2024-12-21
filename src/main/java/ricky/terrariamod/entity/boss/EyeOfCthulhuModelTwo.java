// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package ricky.terrariamod.entity.boss;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import ricky.terrariamod.TerrariaMod;

public class EyeOfCthulhuModelTwo<T extends EyeOfCthulhuEntity> extends EyeOfCthulhuModelBase<T> {
	public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier(TerrariaMod.MOD_ID, "eye_of_cthulhu_phase_two"), "main");
	private final ModelPart eye_of_cthulhu;
	public EyeOfCthulhuModelTwo(ModelPart root) {
		this.eye_of_cthulhu = root.getChild("eye_of_cthulhu");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData eye_of_cthulhu = modelPartData.addChild("eye_of_cthulhu", ModelPartBuilder.create(), ModelTransform.pivot(-2.0F, 36.0F, -7.0F));

		ModelPartData bone = eye_of_cthulhu.addChild("bone", ModelPartBuilder.create().uv(138, 251).cuboid(-34.2275F, -6.0125F, 0.0475F, 68.0F, 12.0F, 10.0F, new Dilation(0.0F))
		.uv(0, 49).cuboid(-29.8875F, -9.9525F, -0.0525F, 60.0F, 20.0F, 18.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-25.8875F, -13.9525F, 0.9475F, 52.0F, 28.0F, 21.0F, new Dilation(0.0F))
		.uv(0, 87).cuboid(-26.1475F, -18.1325F, 0.1275F, 52.0F, 36.0F, 18.0F, new Dilation(0.0F))
		.uv(156, 42).cuboid(-30.1875F, -14.0925F, 2.0875F, 60.0F, 28.0F, 12.0F, new Dilation(0.0F))
		.uv(282, 273).cuboid(-33.8875F, -9.9525F, -0.0525F, 68.0F, 20.0F, 6.0F, new Dilation(0.0F))
		.uv(0, 193).cuboid(-30.5875F, -17.7725F, -0.1525F, 61.0F, 36.0F, 10.0F, new Dilation(0.0F))
		.uv(140, 135).cuboid(-25.8875F, -21.9525F, -0.0525F, 52.0F, 44.0F, 14.0F, new Dilation(0.0F)), ModelTransform.pivot(5.8875F, -46.0475F, 6.0525F));

		ModelPartData bone2 = eye_of_cthulhu.addChild("bone2", ModelPartBuilder.create().uv(294, 229).cuboid(12.7725F, -6.0125F, -9.9525F, 21.0F, 12.0F, 20.0F, new Dilation(0.0F))
		.uv(282, 299).cuboid(12.1125F, -9.9525F, -18.0525F, 18.0F, 20.0F, 36.0F, new Dilation(0.0F))
		.uv(272, 135).cuboid(16.8519F, -14.0925F, -21.8308F, 9.0F, 28.0F, 44.0F, new Dilation(0.0F))
		.uv(0, 285).cuboid(14.8525F, -18.1325F, -17.8725F, 11.0F, 36.0F, 36.0F, new Dilation(0.0F))
		.uv(174, 305).cuboid(24.8125F, -14.0925F, -13.9125F, 5.0F, 28.0F, 28.0F, new Dilation(0.0F))
		.uv(376, 0).cuboid(22.1125F, -9.9525F, -6.0525F, 12.0F, 20.0F, 12.0F, new Dilation(0.0F))
		.uv(94, 305).cuboid(10.1125F, -17.9525F, -10.0525F, 20.0F, 36.0F, 20.0F, new Dilation(0.0F))
		.uv(300, 0).cuboid(16.1125F, -21.9525F, -14.0525F, 10.0F, 44.0F, 28.0F, new Dilation(0.0F)), ModelTransform.of(5.8875F, -46.0475F, 6.0525F, 0.0F, -1.5708F, 0.0F));

		ModelPartData bone3 = eye_of_cthulhu.addChild("bone3", ModelPartBuilder.create().uv(272, 207).cuboid(-34.2275F, -6.0125F, 0.0475F, 68.0F, 12.0F, 10.0F, new Dilation(0.0F))
		.uv(138, 273).cuboid(-29.8875F, -9.9525F, 5.9475F, 60.0F, 20.0F, 12.0F, new Dilation(0.0F))
		.uv(140, 87).cuboid(-25.8875F, -13.9525F, 1.9475F, 52.0F, 28.0F, 20.0F, new Dilation(0.0F))
		.uv(0, 141).cuboid(-26.1475F, -18.1325F, 3.1275F, 52.0F, 36.0F, 15.0F, new Dilation(0.0F))
		.uv(146, 0).cuboid(-30.1875F, -14.0925F, 0.0875F, 60.0F, 28.0F, 14.0F, new Dilation(0.0F))
		.uv(284, 82).cuboid(-33.8875F, -9.9525F, -0.0525F, 68.0F, 20.0F, 6.0F, new Dilation(0.0F))
		.uv(0, 239).cuboid(-29.1891F, -18.1319F, 0.0475F, 59.0F, 36.0F, 10.0F, new Dilation(0.0F))
		.uv(142, 193).cuboid(-25.1491F, -22.1718F, 0.0875F, 51.0F, 44.0F, 14.0F, new Dilation(0.0F)), ModelTransform.of(5.8875F, -46.0475F, 6.0525F, 0.0F, 0.0F, 1.5708F));

		ModelPartData mouth_up = eye_of_cthulhu.addChild("mouth_up", ModelPartBuilder.create().uv(378, 176).cuboid(-57.08F, -33.88F, 11.92F, 9.0F, 5.0F, 14.0F, new Dilation(0.0F))
		.uv(94, 285).cuboid(-13.08F, -33.88F, 11.92F, 8.0F, 5.0F, 14.0F, new Dilation(0.0F))
		.uv(378, 140).cuboid(-61.78F, -29.7F, 13.82F, 13.0F, 6.0F, 12.0F, new Dilation(0.0F))
		.uv(390, 299).cuboid(-13.845F, -29.73F, 14.875F, 13.0F, 6.0F, 11.0F, new Dilation(0.0F))
		.uv(272, 229).cuboid(-65.08F, -21.88F, 22.92F, 7.0F, 4.0F, 3.0F, new Dilation(0.0F))
		.uv(272, 236).cuboid(-5.08F, -21.88F, 22.92F, 7.0F, 4.0F, 3.0F, new Dilation(0.0F))
		.uv(294, 261).cuboid(-61.82F, -25.72F, 18.855F, 8.0F, 4.0F, 7.0F, new Dilation(0.0F))
		.uv(324, 261).cuboid(-8.78F, -25.7F, 18.82F, 8.0F, 4.0F, 7.0F, new Dilation(0.0F))
		.uv(154, 403).cuboid(-57.34F, -30.06F, 6.1F, 13.0F, 4.0F, 11.0F, new Dilation(0.0F))
		.uv(0, 446).cuboid(-18.34F, -30.06F, 6.1F, 13.0F, 4.0F, 11.0F, new Dilation(0.0F)), ModelTransform.pivot(37.08F, -34.12F, -19.92F));

		ModelPartData up = mouth_up.addChild("up", ModelPartBuilder.create().uv(284, 108).cuboid(-34.2275F, -6.0125F, 0.0475F, 13.0F, 12.0F, 10.0F, new Dilation(0.0F))
		.uv(376, 229).cuboid(-29.2594F, -11.0043F, -1.8725F, 9.0F, 20.0F, 12.0F, new Dilation(0.0F))
		.uv(240, 355).cuboid(-26.3043F, -14.2038F, -10.2525F, 5.0F, 29.0F, 20.0F, new Dilation(0.0F))
		.uv(36, 357).cuboid(-26.1475F, -18.1325F, -6.8725F, 5.0F, 36.0F, 15.0F, new Dilation(0.0F))
		.uv(376, 32).cuboid(-21.1475F, -18.1325F, -13.8725F, 5.0F, 36.0F, 10.0F, new Dilation(0.0F))
		.uv(116, 361).cuboid(-30.1875F, -14.0925F, -3.9125F, 8.0F, 28.0F, 14.0F, new Dilation(0.0F))
		.uv(376, 401).cuboid(-33.8875F, -9.9525F, 3.9475F, 13.0F, 20.0F, 6.0F, new Dilation(0.0F))
		.uv(240, 305).cuboid(-29.4023F, -17.7013F, -0.0025F, 8.0F, 36.0F, 10.0F, new Dilation(0.0F))
		.uv(340, 355).cuboid(-25.1491F, -22.1718F, -3.9125F, 4.0F, 44.0F, 14.0F, new Dilation(0.0F)), ModelTransform.of(-31.1925F, -11.9275F, 15.9725F, 0.0F, 0.0F, 1.5708F));

		ModelPartData teeth = mouth_up.addChild("teeth", ModelPartBuilder.create(), ModelTransform.pivot(-61.0F, -16.5F, 23.0F));

		ModelPartData cube_r1 = teeth.addChild("cube_r1", ModelPartBuilder.create().uv(320, 72).cuboid(-1.0F, -2.5F, -2.0F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F))
		.uv(100, 423).cuboid(55.84F, -2.5F, -2.0F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.6545F, 0.0F, 0.0F));

		ModelPartData cube_r2 = teeth.addChild("cube_r2", ModelPartBuilder.create().uv(332, 72).cuboid(-1.0F, -2.5F, -2.0F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F))
		.uv(218, 423).cuboid(51.84F, -2.5F, -2.0F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, -5.0F, -4.0F, -0.5236F, 0.0F, 0.0F));

		ModelPartData cube_r3 = teeth.addChild("cube_r3", ModelPartBuilder.create().uv(128, 429).cuboid(-0.48F, -2.5F, -2.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F))
		.uv(330, 429).cuboid(52.32F, -2.5F, -2.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, -1.0F, -6.0F, -0.5236F, 0.0F, 0.0F));

		ModelPartData cube_r4 = teeth.addChild("cube_r4", ModelPartBuilder.create().uv(344, 72).cuboid(-1.0F, -2.5F, -2.0F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F))
		.uv(424, 176).cuboid(43.84F, -2.5F, -2.0F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(6.0F, -8.0F, -9.0F, -0.48F, 0.0F, 0.0F));

		ModelPartData cube_r5 = teeth.addChild("cube_r5", ModelPartBuilder.create().uv(136, 429).cuboid(-0.48F, -2.5F, -2.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F))
		.uv(338, 429).cuboid(44.32F, -2.5F, -2.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(6.0F, -5.0F, -10.0F, -0.48F, 0.0F, 0.0F));

		ModelPartData cube_r6 = teeth.addChild("cube_r6", ModelPartBuilder.create().uv(356, 72).cuboid(-1.0F, -2.5F, -2.0F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F))
		.uv(424, 185).cuboid(33.84F, -2.5F, -2.0F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(11.0F, -8.0F, -15.0F, -0.3054F, 0.0F, 0.0F));

		ModelPartData cube_r7 = teeth.addChild("cube_r7", ModelPartBuilder.create().uv(144, 429).cuboid(-0.48F, -2.5F, -2.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F))
		.uv(346, 429).cuboid(34.32F, -2.5F, -2.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(11.0F, -4.0F, -16.0F, -0.3054F, 0.0F, 0.0F));

		ModelPartData cube_r8 = teeth.addChild("cube_r8", ModelPartBuilder.create().uv(384, 261).cuboid(-1.0F, -4.5F, -2.0F, 3.0F, 8.0F, 3.0F, new Dilation(0.0F))
		.uv(408, 195).cuboid(19.84F, -4.5F, -2.0F, 3.0F, 8.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(18.0F, -8.0F, -17.0F, -0.3054F, 0.0F, 0.0F));

		ModelPartData cube_r9 = teeth.addChild("cube_r9", ModelPartBuilder.create().uv(368, 72).cuboid(-0.48F, -4.5F, -2.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F))
		.uv(428, 149).cuboid(20.32F, -4.5F, -2.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(18.0F, -5.0F, -17.0F, -0.3054F, 0.0F, 0.0F));

		ModelPartData cube_r10 = teeth.addChild("cube_r10", ModelPartBuilder.create().uv(396, 261).cuboid(-1.0F, -4.5F, -2.0F, 3.0F, 8.0F, 3.0F, new Dilation(0.0F))
		.uv(408, 261).cuboid(5.84F, -4.5F, -2.0F, 3.0F, 8.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(25.0F, -8.0F, -19.0F, -0.3054F, 0.0F, 0.0F));

		ModelPartData cube_r11 = teeth.addChild("cube_r11", ModelPartBuilder.create().uv(332, 403).cuboid(-0.48F, -4.5F, -2.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F))
		.uv(428, 159).cuboid(6.32F, -4.5F, -2.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(25.0F, -4.0F, -19.0F, -0.3054F, 0.0F, 0.0F));

		ModelPartData mouth_up2 = eye_of_cthulhu.addChild("mouth_up2", ModelPartBuilder.create().uv(390, 316).cuboid(-57.08F, 28.88F, 11.92F, 9.0F, 5.0F, 14.0F, new Dilation(0.0F))
		.uv(288, 403).cuboid(-13.08F, 28.88F, 11.92F, 8.0F, 5.0F, 14.0F, new Dilation(0.0F))
		.uv(378, 158).cuboid(-61.845F, 23.67F, 13.88F, 13.0F, 6.0F, 12.0F, new Dilation(0.0F))
		.uv(390, 335).cuboid(-13.845F, 23.67F, 14.875F, 13.0F, 6.0F, 11.0F, new Dilation(0.0F))
		.uv(272, 243).cuboid(-65.08F, 17.88F, 22.92F, 7.0F, 4.0F, 3.0F, new Dilation(0.0F))
		.uv(300, 72).cuboid(-5.08F, 17.88F, 22.92F, 7.0F, 4.0F, 3.0F, new Dilation(0.0F))
		.uv(354, 261).cuboid(-61.78F, 21.7F, 18.82F, 8.0F, 4.0F, 7.0F, new Dilation(0.0F))
		.uv(378, 195).cuboid(-8.78F, 21.7F, 18.82F, 8.0F, 4.0F, 7.0F, new Dilation(0.0F))
		.uv(406, 32).cuboid(-57.34F, 26.06F, 6.1F, 13.0F, 4.0F, 11.0F, new Dilation(0.0F))
		.uv(406, 47).cuboid(-18.34F, 26.06F, 6.1F, 13.0F, 4.0F, 11.0F, new Dilation(0.0F)), ModelTransform.pivot(37.08F, -57.88F, -19.92F));

		ModelPartData down = mouth_up2.addChild("down", ModelPartBuilder.create().uv(330, 108).cuboid(-34.2275F, -5.9875F, 0.0475F, 13.0F, 12.0F, 10.0F, new Dilation(0.0F))
		.uv(378, 108).cuboid(-29.8875F, -10.0475F, -2.0525F, 9.0F, 20.0F, 12.0F, new Dilation(0.0F))
		.uv(290, 355).cuboid(-25.6791F, -13.9532F, -9.9525F, 5.0F, 28.0F, 20.0F, new Dilation(0.0F))
		.uv(76, 361).cuboid(-26.1475F, -17.8675F, -6.8725F, 5.0F, 36.0F, 15.0F, new Dilation(0.0F))
		.uv(376, 355).cuboid(-21.1475F, -17.8675F, -13.8725F, 5.0F, 36.0F, 10.0F, new Dilation(0.0F))
		.uv(160, 361).cuboid(-30.1875F, -13.9075F, -3.9125F, 8.0F, 28.0F, 14.0F, new Dilation(0.0F))
		.uv(116, 403).cuboid(-33.8875F, -10.0475F, 3.9475F, 13.0F, 20.0F, 6.0F, new Dilation(0.0F))
		.uv(204, 361).cuboid(-29.4023F, -17.9387F, -0.0025F, 8.0F, 36.0F, 10.0F, new Dilation(0.0F))
		.uv(0, 357).cuboid(-25.1491F, -21.8282F, -3.9125F, 4.0F, 44.0F, 14.0F, new Dilation(0.0F)), ModelTransform.of(-31.1925F, 11.9275F, 15.9725F, 0.0F, 0.0F, -1.5708F));

		ModelPartData teeth2 = mouth_up2.addChild("teeth2", ModelPartBuilder.create(), ModelTransform.pivot(-61.0F, 16.5F, 23.0F));

		ModelPartData cube_r12 = teeth2.addChild("cube_r12", ModelPartBuilder.create().uv(428, 140).cuboid(-1.0F, -3.5F, -2.0F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F))
		.uv(364, 424).cuboid(55.84F, -3.5F, -2.0F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.6545F, 0.0F, 0.0F));

		ModelPartData cube_r13 = teeth2.addChild("cube_r13", ModelPartBuilder.create().uv(88, 428).cuboid(-1.0F, -3.5F, -2.0F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F))
		.uv(376, 427).cuboid(51.84F, -3.5F, -2.0F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 5.0F, -4.0F, 0.5236F, 0.0F, 0.0F));

		ModelPartData cube_r14 = teeth2.addChild("cube_r14", ModelPartBuilder.create().uv(8, 431).cuboid(-0.48F, -3.5F, -2.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F))
		.uv(354, 429).cuboid(52.32F, -3.5F, -2.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 1.0F, -6.0F, 0.5236F, 0.0F, 0.0F));

		ModelPartData cube_r15 = teeth2.addChild("cube_r15", ModelPartBuilder.create().uv(76, 428).cuboid(-1.0F, -3.5F, -2.0F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F))
		.uv(388, 427).cuboid(43.84F, -3.5F, -2.0F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(6.0F, 8.0F, -9.0F, 0.48F, 0.0F, 0.0F));

		ModelPartData cube_r16 = teeth2.addChild("cube_r16", ModelPartBuilder.create().uv(0, 431).cuboid(-0.48F, -3.5F, -2.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F))
		.uv(430, 272).cuboid(44.32F, -3.5F, -2.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(6.0F, 5.0F, -10.0F, 0.48F, 0.0F, 0.0F));

		ModelPartData cube_r17 = teeth2.addChild("cube_r17", ModelPartBuilder.create().uv(64, 428).cuboid(-1.0F, -3.5F, -2.0F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F))
		.uv(400, 427).cuboid(33.84F, -3.5F, -2.0F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(11.0F, 8.0F, -15.0F, 0.3054F, 0.0F, 0.0F));

		ModelPartData cube_r18 = teeth2.addChild("cube_r18", ModelPartBuilder.create().uv(430, 288).cuboid(-0.48F, -3.5F, -2.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F))
		.uv(430, 280).cuboid(34.32F, -3.5F, -2.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(11.0F, 4.0F, -16.0F, 0.3054F, 0.0F, 0.0F));

		ModelPartData cube_r19 = teeth2.addChild("cube_r19", ModelPartBuilder.create().uv(420, 261).cuboid(-1.0F, -3.5F, -2.0F, 3.0F, 8.0F, 3.0F, new Dilation(0.0F))
		.uv(100, 412).cuboid(19.84F, -3.5F, -2.0F, 3.0F, 8.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(18.0F, 8.0F, -17.0F, 0.3054F, 0.0F, 0.0F));

		ModelPartData cube_r20 = teeth2.addChild("cube_r20", ModelPartBuilder.create().uv(120, 429).cuboid(-0.48F, -3.5F, -2.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F))
		.uv(428, 206).cuboid(20.32F, -3.5F, -2.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(18.0F, 5.0F, -17.0F, 0.3054F, 0.0F, 0.0F));

		ModelPartData cube_r21 = teeth2.addChild("cube_r21", ModelPartBuilder.create().uv(420, 195).cuboid(-1.0F, -3.5F, -2.0F, 3.0F, 8.0F, 3.0F, new Dilation(0.0F))
		.uv(364, 413).cuboid(5.84F, -3.5F, -2.0F, 3.0F, 8.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(25.0F, 8.0F, -19.0F, 0.3054F, 0.0F, 0.0F));

		ModelPartData cube_r22 = teeth2.addChild("cube_r22", ModelPartBuilder.create().uv(112, 429).cuboid(-0.48F, -3.5F, -2.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F))
		.uv(428, 216).cuboid(6.32F, -3.5F, -2.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(25.0F, 4.0F, -19.0F, 0.3054F, 0.0F, 0.0F));

		ModelPartData tail = eye_of_cthulhu.addChild("tail", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData bone4 = tail.addChild("bone4", ModelPartBuilder.create(), ModelTransform.pivot(-2.0F, -38.0F, 38.0F));

		ModelPartData cube_r23 = bone4.addChild("cube_r23", ModelPartBuilder.create().uv(202, 407).cuboid(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(16.0F, -12.0F, -4.0F, 0.2182F, 0.3491F, 0.0F));

		ModelPartData cube_r24 = bone4.addChild("cube_r24", ModelPartBuilder.create().uv(406, 384).cuboid(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -8.0F, -4.0F, -0.1745F, -0.3927F, 0.0F));

		ModelPartData cube_r25 = bone4.addChild("cube_r25", ModelPartBuilder.create().uv(406, 368).cuboid(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(8.0F, -4.0F, 0.0F, -0.3491F, -0.3927F, 0.0F));

		ModelPartData cube_r26 = bone4.addChild("cube_r26", ModelPartBuilder.create().uv(406, 352).cuboid(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.4363F, -0.48F, 0.0F));

		ModelPartData cube_r27 = bone4.addChild("cube_r27", ModelPartBuilder.create().uv(406, 62).cuboid(-1.847F, -2.4755F, 0.1168F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(-6.0F, 6.0F, 10.0F, -0.0873F, -0.48F, 0.0F));

		ModelPartData bone5 = tail.addChild("bone5", ModelPartBuilder.create(), ModelTransform.of(18.0F, -62.0F, 30.0F, 0.6473F, -0.4878F, 0.6674F));

		ModelPartData cube_r28 = bone5.addChild("cube_r28", ModelPartBuilder.create().uv(68, 412).cuboid(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.4363F, 0.48F, 0.0F));

		ModelPartData cube_r29 = bone5.addChild("cube_r29", ModelPartBuilder.create().uv(36, 408).cuboid(-2.0F, -0.8F, 2.0F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, 4.0F, 8.0F, -0.0873F, 0.48F, 0.0F));

		ModelPartData bone6 = tail.addChild("bone6", ModelPartBuilder.create(), ModelTransform.of(-0.6579F, -64.4511F, 42.5857F, 0.6473F, -0.4878F, 0.6674F));

		ModelPartData cube_r30 = bone6.addChild("cube_r30", ModelPartBuilder.create().uv(414, 400).cuboid(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(-1.1945F, -0.6368F, -10.7038F, -0.3927F, -0.48F, -0.9599F));

		ModelPartData cube_r31 = bone6.addChild("cube_r31", ModelPartBuilder.create().uv(332, 413).cuboid(-3.8524F, -1.0879F, 2.0F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(0.6579F, 3.6511F, -2.7038F, -0.0436F, -0.48F, -0.9599F));

		ModelPartData bone7 = tail.addChild("bone7", ModelPartBuilder.create(), ModelTransform.of(18.0F, -38.0F, 38.0F, 0.0F, 0.0F, -1.309F));

		ModelPartData cube_r32 = bone7.addChild("cube_r32", ModelPartBuilder.create().uv(414, 416).cuboid(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.4363F, -0.48F, 0.0F));

		ModelPartData cube_r33 = bone7.addChild("cube_r33", ModelPartBuilder.create().uv(0, 415).cuboid(-1.847F, -2.4755F, 0.1168F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(-6.0F, 6.0F, 10.0F, -0.8636F, -0.6627F, 0.2237F));

		ModelPartData bone8 = tail.addChild("bone8", ModelPartBuilder.create(), ModelTransform.pivot(-10.0F, -50.0F, 30.0F));

		ModelPartData cube_r34 = bone8.addChild("cube_r34", ModelPartBuilder.create().uv(418, 229).cuboid(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.4363F, -0.48F, 0.0F));

		ModelPartData cube_r35 = bone8.addChild("cube_r35", ModelPartBuilder.create().uv(154, 418).cuboid(-1.847F, -2.4755F, 0.1168F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(-6.0F, 6.0F, 10.0F, -0.1603F, -0.2648F, -0.039F));

		ModelPartData bone9 = tail.addChild("bone9", ModelPartBuilder.create(), ModelTransform.of(26.0F, -50.0F, 30.0F, 0.0F, 0.0F, -1.7017F));

		ModelPartData cube_r36 = bone9.addChild("cube_r36", ModelPartBuilder.create().uv(418, 245).cuboid(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.4363F, -0.48F, 0.0F));

		ModelPartData cube_r37 = bone9.addChild("cube_r37", ModelPartBuilder.create().uv(234, 418).cuboid(-1.847F, -2.4755F, 0.1168F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(-6.0F, 6.0F, 10.0F, -0.3054F, -0.48F, 0.0F));

		ModelPartData bone10 = tail.addChild("bone10", ModelPartBuilder.create(), ModelTransform.of(6.0F, -66.0F, 30.0F, 0.3054F, 0.0F, 2.5744F));

		ModelPartData cube_r38 = bone10.addChild("cube_r38", ModelPartBuilder.create().uv(420, 124).cuboid(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.4363F, -0.48F, 0.0F));

		ModelPartData cube_r39 = bone10.addChild("cube_r39", ModelPartBuilder.create().uv(420, 108).cuboid(-1.847F, -2.4755F, 0.1168F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(-6.0F, 6.0F, 10.0F, -0.829F, -0.48F, 0.0F));

		ModelPartData bone11 = tail.addChild("bone11", ModelPartBuilder.create(), ModelTransform.of(10.0F, -30.0F, 34.0F, 0.0F, 0.0F, -0.8727F));

		ModelPartData cube_r40 = bone11.addChild("cube_r40", ModelPartBuilder.create().uv(298, 422).cuboid(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.4363F, -0.48F, 0.0F));

		ModelPartData cube_r41 = bone11.addChild("cube_r41", ModelPartBuilder.create().uv(266, 422).cuboid(-1.847F, -2.4755F, 0.1168F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(-6.0F, 6.0F, 10.0F, -0.0873F, -0.48F, 0.0F));

		ModelPartData bone12 = tail.addChild("bone12", ModelPartBuilder.create(), ModelTransform.of(10.0F, -50.0F, 34.0F, 0.6545F, 0.0F, 0.0F));

		ModelPartData cube_r42 = bone12.addChild("cube_r42", ModelPartBuilder.create().uv(424, 0).cuboid(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.4363F, -0.48F, 0.0F));

		ModelPartData cube_r43 = bone12.addChild("cube_r43", ModelPartBuilder.create().uv(186, 423).cuboid(-1.847F, -2.4755F, 0.1168F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(-6.0F, 6.0F, 10.0F, -0.0873F, -0.48F, 0.0F));

		ModelPartData bone13 = tail.addChild("bone13", ModelPartBuilder.create(), ModelTransform.of(14.0F, -46.0F, 34.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData cube_r44 = bone13.addChild("cube_r44", ModelPartBuilder.create().uv(32, 424).cuboid(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.4363F, -0.48F, 0.0F));

		ModelPartData cube_r45 = bone13.addChild("cube_r45", ModelPartBuilder.create().uv(424, 16).cuboid(-1.847F, -2.4755F, 0.1168F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(-6.0F, 6.0F, 10.0F, -0.2435F, -0.3109F, -0.0472F));
		return TexturedModelData.of(modelData, 512, 512);
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