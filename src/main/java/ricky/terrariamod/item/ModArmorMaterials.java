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

    OAK("oak", 5, new int[] {1,2,2,1}, 10,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0f, 0f, () -> Ingredient.ofItems(Blocks.OAK_LOG)),
    COPPER("copper", 12, new int[] {2,5,4,2}, 9,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0f, 0f, () -> Ingredient.ofItems(Items.COPPER_INGOT)),
    OBSIDIAN("obsidian", 35, new int[] {3,8,6,3}, 7,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0f, 0f, () -> Ingredient.ofItems(Blocks.OBSIDIAN)),
    CACTUS("cactus", 8, new int[] {2,5,3,1}, 19,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0f, 0f, () -> Ingredient.ofItems(Blocks.CACTUS)),
    PUMPKIN("pumpkin", 8, new int[] {2,5,3,1}, 19,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0f, 0f, () -> Ingredient.ofItems(Blocks.PUMPKIN)),
    GLASS("glass", 25, new int[] {2,0,0,0}, 9,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0f, 0f, () -> Ingredient.ofItems(Blocks.GLASS)),
    NIGHT("night", 25, new int[] {2,0,0,0}, 19,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0f, 0f, () -> Ingredient.ofItems(Blocks.GLASS)),

    COBALT("cobalt", 35, new int[] {4,8,6,4}, 15,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1f, () -> Ingredient.ofItems(ModItems.COBALT_INGOT)),
    ORICHALCUM("orichalcum", 45, new int[] {5,12,9,4}, 15,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.2f, () -> Ingredient.ofItems(ModItems.ORICHALCUM_INGOT)),

    ADAMANTITE("adamantite", 50, new int[] {6,16,12,6}, 15,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 5f, 0.3f, () -> Ingredient.ofItems(ModItems.ADAMANTITE_INGOT)),

    HELLSTONE("hellstone", 40, new int[] {3,8,6,3}, 15,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 3f, 0.1f, () -> Ingredient.ofItems(ModItems.ADAMANTITE_INGOT));

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
