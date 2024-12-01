package ricky.terrariamod.entity.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.World;

public class IceSlimeEntity extends SlimeEntity {
    public IceSlimeEntity(EntityType<? extends SlimeEntity> entityType, World world) {
        super(entityType, world);
        this.setSize(2, true); // サイズを1に固定
        this.reinitDimensions();
    }

    public static DefaultAttributeContainer.Builder createIceSlimeAttributes() {
        return ZombieEntity.createZombieAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 24)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 10.0)
                .add(EntityAttributes.GENERIC_ARMOR, 15f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 10);
    }
    @Override
    public void remove(Entity.RemovalReason reason) {
        // スライムが死亡したときの分裂を防ぐためにサイズを1に固定
        this.setSize(1, true); // サイズを1に固定
        super.remove(reason);  // 通常の死亡処理を呼び出す
    }
    @Override
    public void setSize(int size, boolean resetHealth) {
        // サイズは1に固定
        super.setSize(2, resetHealth);
        this.reinitDimensions();
        // サイズ変更後に体力を50に再設定
        // 体力の設定
        this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(24);
        this.setHealth(24);
        this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(6.0);
        this.getAttributeInstance(EntityAttributes.GENERIC_FOLLOW_RANGE).setBaseValue(40);
    }
}
