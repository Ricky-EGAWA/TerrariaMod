package ricky.terrariamod.item.bows;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CustomBowItem extends BowItem {
    private final double damage;
    private final int time;

    public CustomBowItem(Item.Settings settings, double damage, int time) {
        super(settings);
        this.damage = damage;
        this.time = time;
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        // デフォルトの処理を呼び出す
        super.onStoppedUsing(stack, world, user, remainingUseTicks);

        // カスタムダメージを適用
        if (!world.isClient) {
            ArrowEntity arrow = new ArrowEntity(world, user);
            arrow.setDamage(this.damage);
            world.spawnEntity(arrow);
        }
    }
    @Override
    public int getMaxUseTime(ItemStack stack) {
        return time;
    }
}
