package ricky.terrariamod.item.custom;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import ricky.terrariamod.item.ModItems;

import java.util.function.Supplier;

public enum ModToolMaterial implements ToolMaterial {
    COBALT_INGOT(5,2200, 11f,6f,20,
            () -> Ingredient.ofItems(ModItems.COBALT_INGOT)),

    ORICHALCUM_INGOT(6,2500, 13f,7.5f,23,
                         () -> Ingredient.ofItems(ModItems.ORICHALCUM_INGOT)),

    ADAMANTITE_INGOT(7,3000, 15f,9f,25,
            () -> Ingredient.ofItems(ModItems.ADAMANTITE_INGOT)),

    HELLSTONE_INGOT(4,1800, 10f,5f,20,
                             () -> Ingredient.ofItems(ModItems.HELLSTONE_INGOT));

    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;

    ModToolMaterial(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient){
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = repairIngredient;
    }
    @Override
    public int getDurability() {
        return this.itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getMiningLevel() {
        return this.miningLevel;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
