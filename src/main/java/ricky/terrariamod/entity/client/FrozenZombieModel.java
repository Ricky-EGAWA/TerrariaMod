// Made with Blockbench 4.11.1
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package ricky.terrariamod.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import ricky.terrariamod.entity.custom.FrozenZombieEntity;

public class FrozenZombieModel<T extends FrozenZombieEntity> extends SinglePartEntityModel<T> {
	private final ModelPart frozenzombie;
//	private final ModelPart head;

//	private final ModelPart body;
//	private final ModelPart head;
//	private final ModelPart hat;
//	private final ModelPart rightArm;
//	private final ModelPart rightItem;
//	private final ModelPart leftArm;
//	private final ModelPart leftItem;
//	private final ModelPart rightLeg;
//	private final ModelPart leftLeg;
	public FrozenZombieModel(ModelPart root) {
		this.frozenzombie = root.getChild("frozenzombie");
//		this.head = frozenzombie.getChild("body").getChild("body").getChild("head");
//		this.body = root.getChild("body");
//		this.head = root.getChild("head");
//		this.hat = root.getChild("hat");
//		this.rightArm = root.getChild("rightArm");
//		this.rightItem = root.getChild("rightItem");
//		this.leftArm = root.getChild("leftArm");
//		this.leftItem = root.getChild("leftItem");
//		this.rightLeg = root.getChild("rightLeg");
//		this.leftLeg = root.getChild("leftLeg");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData waist = modelPartData.addChild("frozenzombie", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 12.0F, 0.0F));

		ModelPartData body = waist.addChild("body", ModelPartBuilder.create().uv(16, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -12.0F, 0.0F));

		ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData hat = head.addChild("hat", ModelPartBuilder.create().uv(32, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.5F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData rightArm = body.addChild("rightArm", ModelPartBuilder.create().uv(40, 16).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, 2.0F, 0.0F));

		ModelPartData rightItem = rightArm.addChild("rightItem", ModelPartBuilder.create(), ModelTransform.pivot(-1.0F, 7.0F, 1.0F));

		ModelPartData leftArm = body.addChild("leftArm", ModelPartBuilder.create().uv(40, 16).mirrored().cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(5.0F, 2.0F, 0.0F));

		ModelPartData leftItem = leftArm.addChild("leftItem", ModelPartBuilder.create(), ModelTransform.pivot(1.0F, 7.0F, 1.0F));

		ModelPartData rightLeg = body.addChild("rightLeg", ModelPartBuilder.create().uv(0, 16).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.9F, 12.0F, 0.0F));

		ModelPartData leftLeg = body.addChild("leftLeg", ModelPartBuilder.create().uv(0, 16).mirrored().cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(1.9F, 12.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 32);
	}
	@Override
	public void setAngles(FrozenZombieEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		// 頭の回転
		this.frozenzombie.getChild("body").getChild("head").yaw = headPitch * ((float) Math.PI / 180F);
		this.frozenzombie.getChild("body").getChild("head").pitch = netHeadYaw * ((float) Math.PI / 180F);

		// 脚の動き
		this.frozenzombie.getChild("body").getChild("rightLeg").pitch = MathHelper.cos(limbSwing * 0.6662F) * limbSwingAmount;
		this.frozenzombie.getChild("body").getChild("leftLeg").pitch = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;

		// 腕の動き
		this.frozenzombie.getChild("body").getChild("rightArm").pitch = MathHelper.cos(limbSwing * 0.6662F) * limbSwingAmount;
		this.frozenzombie.getChild("body").getChild("leftArm").pitch = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;

		// スピードに応じた頭の動き
		this.frozenzombie.getChild("body").getChild("head").yaw = netHeadYaw * ((float) Math.PI / 180F);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		frozenzombie.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return frozenzombie;
	}
}