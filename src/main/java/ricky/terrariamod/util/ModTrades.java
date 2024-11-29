package ricky.terrariamod.util;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;
import ricky.terrariamod.item.ModItems;

public class ModTrades {
    public static void registerCustomTrades() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.WEAPONSMITH, 5,
                factories -> factories.add((entity, random) -> new TradeOffer(
                        new ItemStack(Items.EMERALD_BLOCK, 10),
                        new ItemStack(ModItems.SHOTGUN, 1),
                        2, 10, 0.2f)));
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.WEAPONSMITH,5,
                factories -> factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD_BLOCK, 10),
                            new ItemStack(ModItems.SNIPER_RIFLE,1),
                            2,10,0.2f)));
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.WEAPONSMITH,5,
                factories -> factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD_BLOCK, 10),
                            new ItemStack(ModItems.ROCKET_LAUNCHER,1),
                            2,10,0.2f)));
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.WEAPONSMITH,4,
                factories -> factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 10),
                            new ItemStack(ModItems.ROCKET,5),
                            10,10,0.2f)));
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.WEAPONSMITH,4,
                factories -> factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 10),
                            new ItemStack(ModItems.MUSKET_BALL,10),
                            10,10,0.2f)));
    }
}
