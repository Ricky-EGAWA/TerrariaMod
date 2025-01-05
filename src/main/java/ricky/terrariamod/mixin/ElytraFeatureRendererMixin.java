package ricky.terrariamod.mixin;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ElytraFeatureRenderer;
import net.minecraft.client.render.entity.model.ElytraEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.item.ItemRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import ricky.terrariamod.TerrariaMod;
import ricky.terrariamod.item.ModItems;

@Mixin(ElytraFeatureRenderer.class)
public abstract class ElytraFeatureRendererMixin<T extends LivingEntity, M extends EntityModel<T>> {

    @Shadow
    @Final
    private ElytraEntityModel<T> elytra; //TODO カスタムモデルの実装


    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    public void renderCustomElytra(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light, T livingEntity, float limbAngle, float limbDistance, float tickDelta, float customAngle, float headYaw, float headPitch, CallbackInfo ci) {
        ItemStack chestItem = livingEntity.getEquippedStack(EquipmentSlot.CHEST);

        if (chestItem.isOf(ModItems.FLAME_WING)) {
            Identifier customTexture = new Identifier(TerrariaMod.MOD_ID, "textures/entity/flame_wing.png");

            // ElytraEntityModel を直接利用する
            matrixStack.push();
            matrixStack.translate(0.0F, 0.0F, 0.125F);
            this.elytra.setAngles(livingEntity, limbAngle, limbDistance, customAngle, headYaw, headPitch);
            VertexConsumer vertexConsumer = ItemRenderer.getArmorGlintConsumer(vertexConsumerProvider, RenderLayer.getArmorCutoutNoCull(customTexture), false, chestItem.hasGlint());
            this.elytra.render(matrixStack, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
            matrixStack.pop();

            // 通常のエリトラ描画をキャンセル
            ci.cancel();
        }
    }
}

