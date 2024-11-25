package ricky.terrariamod.entity.ammo;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class MusketBallEntityRenderer extends RoundTypeRenderer<MusketBallEntity> {
    // ここで使用するテクスチャを指定します
    public static final Identifier TEXTURE = new Identifier("terrariamod", "textures/entity/projectiles/musket_ball.png");

    public MusketBallEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(MusketBallEntity entity) {
        return TEXTURE; // RocketEntity 用のテクスチャ
    }
}
