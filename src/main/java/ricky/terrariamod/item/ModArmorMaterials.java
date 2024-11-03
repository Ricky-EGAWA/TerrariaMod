package ricky.terrariamod.item;

import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import ricky.terrariamod.TerrariaMod;

import java.util.function.Supplier;


public enum ModArmorMaterials implements ArmorMaterial {

    OAK("oak", 25, new int[] {3,8,6,3}, 19,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2f, 0.1f, () -> Ingredient.ofItems(Blocks.OAK_LOG)),
    COPPER("copper", 25, new int[] {3,8,6,3}, 19,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2f, 0.1f, () -> Ingredient.ofItems(Items.COPPER_INGOT)),
    OBSIDIAN("obsidian", 25, new int[] {3,8,6,3}, 19,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2f, 0.1f, () -> Ingredient.ofItems(Blocks.OBSIDIAN)),
    CACTUS("cactus", 25, new int[] {3,8,6,3}, 19,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2f, 0.1f, () -> Ingredient.ofItems(Blocks.CACTUS)),
    PUMPKIN("pumpkin", 25, new int[] {3,8,6,3}, 19,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2f, 0.1f, () -> Ingredient.ofItems(Blocks.PUMPKIN)),
    GLASS("glass", 25, new int[] {3,8,6,3}, 19,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2f, 0.1f, () -> Ingredient.ofItems(Blocks.GLASS)),
    NIGHT("night", 25, new int[] {3,8,6,3}, 19,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2f, 0.1f, () -> Ingredient.ofItems(Blocks.GLASS)),

    COBALT("cobalt", 25, new int[] {3,8,6,3}, 19,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2f, 0.1f, () -> Ingredient.ofItems(ModItems.COBALT_INGOT)),
    ORICHALCUM("orichalcum", 25, new int[] {3,8,6,3}, 19,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2f, 0.1f, () -> Ingredient.ofItems(ModItems.ORICHALCUM_INGOT)),

    ADAMANTITE("adamantite", 25, new int[] {3,8,6,3}, 19,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2f, 0.1f, () -> Ingredient.ofItems(ModItems.ADAMANTITE_INGOT)),

    HELLSTONE("hellstone", 25, new int[] {3,8,6,3}, 19,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2f, 0.1f, () -> Ingredient.ofItems(ModItems.ADAMANTITE_INGOT));

    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    private static final int[] BASEDURABILITY = {11,16,15,13};

    ModArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability, SoundEvent equipSound,
                      float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient){
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurability(ArmorItem.Type type) {
        return BASEDURABILITY[type.ordinal()] * this.durabilityMultiplier;
    }

    @Override
    public int getProtection(ArmorItem.Type type) {
        return protectionAmounts[type.ordinal()];
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return TerrariaMod.MOD_ID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
