package ricky.terrariamod.entity.ammo;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;
import ricky.terrariamod.entity.ModEntities;

@Environment(EnvType.CLIENT)
public class RocketEntityRenderer extends RocketTypeRenderer<RocketEntity> {
    // ここで使用するテクスチャを指定します
    public static final Identifier TEXTURE = new Identifier("terrariamod", "textures/entity/projectiles/rocket.png");

    public RocketEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(RocketEntity entity) {
        return TEXTURE; // RocketEntity 用のテクスチャ
    }
}
