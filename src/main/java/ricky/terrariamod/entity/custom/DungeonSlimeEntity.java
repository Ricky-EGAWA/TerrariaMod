package ricky.terrariamod.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import ricky.terrariamod.item.ModItems;

public class DungeonSlimeEntity extends SlimeEntity {
    public DungeonSlimeEntity(EntityType<? extends SlimeEntity> entityType, World world) {
        super(entityType, world);
        this.setSize(2, true);
        this.reinitDimensions();
    }
    public static DefaultAttributeContainer.Builder createDungeonSlimeAttributes() {
        return ZombieEntity.createZombieAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 24)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 40.0)
                .add(EntityAttributes.GENERIC_ARMOR, 0.5f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4);
    }
    @Override
    public void remove(RemovalReason reason) {
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
        this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(40);
        this.setHealth(40);
        this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(10.0);
        this.getAttributeInstance(EntityAttributes.GENERIC_FOLLOW_RANGE).setBaseValue(40);
    }
    @Override
    public void onDeath(net.minecraft.entity.damage.DamageSource source) {
        super.onDeath(source);

        // アイテムをドロップする処理
        if (!this.getWorld().isClient) { // サーバー側でのみ実行
            // ドロップするアイテムの例
//            this.dropStack(new ItemStack(Items.SLIME_BALL, MathHelper.nextInt(this.random, 2, 4))); // スライムボール2～4個
            this.dropStack(new ItemStack(ModItems.GOLDEN_KEY, 1)); // 金のインゴット1個

//            // レアドロップ（ランダム確率）
//            if (this.random.nextFloat() < 0.2f) { // 20%の確率
//                this.dropStack(new ItemStack(Items.DIAMOND, 1)); // ダイヤモンド1個
//            }
        }
    }

}

