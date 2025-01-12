package ricky.terrariamod.entity.monster.cursed_hammer;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class CursedHammerModel<T extends CursedHammerEntity> extends SinglePartEntityModel<T> {
	private final ModelPart cursed_hammer;
	private final ModelPart hammer_overlay;
	private final ModelPart hammer;
	public CursedHammerModel(ModelPart root) {
		this.cursed_hammer = root.getChild("cursed_hammer");
		this.hammer_overlay = cursed_hammer.getChild("hammer_overlay");
		this.hammer = cursed_hammer.getChild("hammer");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData cursed_hammer = modelPartData.addChild("cursed_hammer", ModelPartBuilder.create(), ModelTransform.of(1.0F, 16.0F, 0.0F, 1.5708F, 0.0F, 1.5708F));

		ModelPartData hammer_overlay = cursed_hammer.addChild("hammer_overlay", ModelPartBuilder.create().uv(0, 50).cuboid(-9.0F, -22.0F, -3.0F, 6.0F, 9.0F, 5.0F, new Dilation(0.3F))
				.uv(0, 50).cuboid(2.0F, -22.0F, -3.0F, 6.0F, 9.0F, 5.0F, new Dilation(0.3F))
				.uv(22, 55).cuboid(-3.0F, -21.0F, -2.0F, 5.0F, 6.0F, 3.0F, new Dilation(0.3F))
				.uv(51, 46).cuboid(-2.0F, -15.0F, -2.0F, 3.0F, 15.0F, 3.0F, new Dilation(0.3F))
				.uv(42, 30).cuboid(-2.0F, -22.0F, -2.0F, 3.0F, 1.0F, 3.0F, new Dilation(0.3F)), ModelTransform.pivot(0.0F, 14.0F, 0.0F));

		ModelPartData hammer = cursed_hammer.addChild("hammer", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-9.0F, -22.0F, -3.0F, 6.0F, 9.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
				.uv(0, 0).cuboid(2.0F, -22.0F, -3.0F, 6.0F, 9.0F, 5.0F, new Dilation(0.0F))
				.uv(12, 14).cuboid(-3.0F, -20.0F, -2.0F, 5.0F, 5.0F, 3.0F, new Dilation(0.0F))
				.uv(0, 14).cuboid(-2.0F, -15.0F, -2.0F, 3.0F, 15.0F, 3.0F, new Dilation(0.0F))
				.uv(22, 0).cuboid(-2.0F, -22.0F, -2.0F, 3.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 14.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	public static int rotateAngle=0;
	public static void addRotate(){
		rotateAngle++;
	}
	@Override
	public void setAngles(CursedHammerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		// モデルのピッチとヨーをエンティティの回転角度に対応させる
		this.cursed_hammer.yaw = netHeadYaw * ((float) Math.PI / 180) - (float) Math.PI * rotateAngle* 30 / 180; // ヨーの回転
		this.cursed_hammer.pitch = headPitch * ((float) Math.PI / 180) + (float) Math.PI / 2; // ピッチの回転
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		cursed_hammer.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return cursed_hammer;
	}
}