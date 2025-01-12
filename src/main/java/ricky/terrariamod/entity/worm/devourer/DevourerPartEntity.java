package ricky.terrariamod.entity.worm.devourer;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

public class DevourerPartEntity extends Entity {
    private final DevourerEntity parent;
    private final String partName;
    private final EntityDimensions partDimensions;


    public DevourerPartEntity(DevourerEntity parent, String partName, float width, float height) {
        super(parent.getType(), parent.getWorld());
        this.partDimensions = EntityDimensions.changing(width, height);
        this.calculateDimensions();
        this.parent = parent;
        this.partName = partName;
    }


    @Override
    public void tick() {
        super.tick();
        if (!this.parent.isAlive()) {
            this.discard(); // 親が死んだらこのパーツも消える
        }
        // デバッグログ
        System.out.println("Part: " + this.partName + " Position: " + this.getPos() + " BoundingBox: " + this.getBoundingBox());
    }

    public void updatePosition(double x, double y, double z) {
        this.setPos(x, y, z);
//        System.out.println("part position");
        this.setBoundingBox(this.getBoundingBox().offset(0,2,0));
    }
    @Override
    public boolean damage(DamageSource source, float amount) {
        // 当たり判定のダメージを親エンティティに転送する
        return this.parent.damage(source, amount);
    }

    @Override
    public boolean isCollidable() {
        return true; // 他のエンティティと衝突可能
    }

    @Override
    public boolean isAttackable() {
        return true; // 他のエンティティに攻撃される可能性を有効化
    }

    @Override
    public EntityDimensions getDimensions(EntityPose pose) {
        return this.partDimensions; // 部位の大きさを返す
    }

    @Override
    protected void initDataTracker() {}

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {}

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {}
    public boolean canHit() {
        return true;
    }
}

