package ricky.terrariamod.item.custom;

import com.google.common.collect.ImmutableMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ricky.terrariamod.item.ModArmorMaterials;

import java.util.Map;

public class ModArmorItem extends ArmorItem {
    public static final Map<ArmorMaterial, StatusEffectInstance> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<ArmorMaterial, StatusEffectInstance>())
                    .put(ModArmorMaterials.COBALT, new StatusEffectInstance(StatusEffects.HASTE, 219, 1,
                            false, false, true))
                    .put(ModArmorMaterials.ORICHALCUM, new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 219, 1,
                            false, false, true))
                    .put(ModArmorMaterials.ADAMANTITE, new StatusEffectInstance(StatusEffects.STRENGTH, 219, 1,
                            false, false, true))
                    .put(ModArmorMaterials.HELLSTONE, new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 219, 1,
                            false, false, true))
                    .put(ModArmorMaterials.GLASS, new StatusEffectInstance(StatusEffects.WATER_BREATHING, 219, 1,
                            false, false, true))
                    .put(ModArmorMaterials.NIGHT, new StatusEffectInstance(StatusEffects.NIGHT_VISION, 219, 1,
                            false, false, true))
                    .put(ModArmorMaterials.OBSIDIAN, new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 219, 1,
                            false, false, true))
                    .put(ModArmorMaterials.PUMPKIN, new StatusEffectInstance(StatusEffects.STRENGTH, 219, 1,
                            false, false, true))
                    .build();

    public ModArmorItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient()) {
            if (entity instanceof PlayerEntity player) {
                // Glass素材またはNight素材のヘルメットが装備されているか、またはフル装備の場合に効果を評価
                if (isWearingGlassHelmet(player) || isWearingNightHelmet(player) || hasFullSuitOfArmorOn(player)) {
                    evaluateArmorEffects(player);
                }
            }
        }

        super.inventoryTick(stack, world, entity, slot, selected);
    }

    private void evaluateArmorEffects(PlayerEntity player) {
        for (Map.Entry<ArmorMaterial, StatusEffectInstance> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            ArmorMaterial mapArmorMaterial = entry.getKey();
            StatusEffectInstance mapStatusEffect = entry.getValue();

            if (hasCorrectArmorOn(mapArmorMaterial, player)) {
                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffect);
            }
        }
    }

    private void addStatusEffectForMaterial(PlayerEntity player, ArmorMaterial mapArmorMaterial, StatusEffectInstance mapStatusEffect) {
        boolean hasPlayerEffect = player.hasStatusEffect(mapStatusEffect.getEffectType());

        if (hasCorrectArmorOn(mapArmorMaterial, player)) {
            player.addStatusEffect(new StatusEffectInstance(mapStatusEffect));
        }
    }

    // Glassのヘルメットを装備しているか確認
    private boolean isWearingGlassHelmet(PlayerEntity player) {
        ItemStack helmet = player.getInventory().getArmorStack(3);
        return !helmet.isEmpty() && ((ArmorItem) helmet.getItem()).getMaterial() == ModArmorMaterials.GLASS;
    }

    // Nightのヘルメットを装備しているか確認
    private boolean isWearingNightHelmet(PlayerEntity player) {
        ItemStack helmet = player.getInventory().getArmorStack(3);
        return !helmet.isEmpty() && ((ArmorItem) helmet.getItem()).getMaterial() == ModArmorMaterials.NIGHT;
    }

    private boolean hasFullSuitOfArmorOn(PlayerEntity player) {
        ItemStack boots = player.getInventory().getArmorStack(0);
        ItemStack leggings = player.getInventory().getArmorStack(1);
        ItemStack breastplate = player.getInventory().getArmorStack(2);
        ItemStack helmet = player.getInventory().getArmorStack(3);

        return !helmet.isEmpty() && !breastplate.isEmpty()
                && !leggings.isEmpty() && !boots.isEmpty();
    }

    private boolean hasCorrectArmorOn(ArmorMaterial material, PlayerEntity player) {
        // GlassおよびNight素材であれば各ヘルメットのみのチェックを行う
        if (material == ModArmorMaterials.GLASS) {
            return isWearingGlassHelmet(player);
        }
        if (material == ModArmorMaterials.NIGHT) {
            return isWearingNightHelmet(player);
        }

        for (ItemStack armorStack : player.getInventory().armor) {
            if (!(armorStack.getItem() instanceof ArmorItem)) {
                return false;
            }
        }

        ArmorItem boots = ((ArmorItem) player.getInventory().getArmorStack(0).getItem());
        ArmorItem leggings = ((ArmorItem) player.getInventory().getArmorStack(1).getItem());
        ArmorItem breastplate = ((ArmorItem) player.getInventory().getArmorStack(2).getItem());
        ArmorItem helmet = ((ArmorItem) player.getInventory().getArmorStack(3).getItem());

        return helmet.getMaterial() == material && breastplate.getMaterial() == material &&
                leggings.getMaterial() == material && boots.getMaterial() == material;
    }
}
