package ricky.terrariamod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;
import ricky.terrariamod.block.ModBlocks;
import ricky.terrariamod.item.ModItems;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
    private static final List<ItemConvertible> COBALT_SMELTABLE = List.of(ModItems.COBALT_RAW,
            ModBlocks.COBALT_ORE,ModBlocks.DEEPSLATE_COBALT_ORE);
    private static final List<ItemConvertible> ORICHALCUM_SMELTABLE = List.of(ModItems.ORICHALCUM_RAW,
            ModBlocks.ORICHALCUM_ORE, ModBlocks.DEEPSLATE_ORICHALCUM_ORE);
    private static final List<ItemConvertible> ADAMANTITE_SMELTABLE = List.of(ModItems.ADAMANTITE_RAW,
            ModBlocks.ADAMANTITE_ORE, ModBlocks.DEEPSLATE_ADAMANTITE_ORE);
    private static final List<ItemConvertible> HELLSTONE_SMELTABLE = List.of(ModItems.HELLSTONE_RAW, ModBlocks.HELLSTONE_ORE);

    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        // 各種インゴットの生成レシピ
        generateSmeltingAndBlastingRecipes(exporter, COBALT_SMELTABLE, ModItems.COBALT_INGOT, "cobalt");
        generateSmeltingAndBlastingRecipes(exporter, ORICHALCUM_SMELTABLE, ModItems.ORICHALCUM_INGOT, "orichalcum");
        generateSmeltingAndBlastingRecipes(exporter, ADAMANTITE_SMELTABLE, ModItems.ADAMANTITE_INGOT, "adamantite");
        generateSmeltingAndBlastingRecipes(exporter, HELLSTONE_SMELTABLE, ModItems.HELLSTONE_INGOT, "hellstone");

        // 各種インゴットとブロックのコンパクトレシピ
        generateCompactingRecipes(exporter, ModItems.COBALT_INGOT, ModBlocks.COBALT_BLOCK);
        generateCompactingRecipes(exporter, ModItems.ORICHALCUM_INGOT, ModBlocks.ORICHALCUM_BLOCK);
        generateCompactingRecipes(exporter, ModItems.ADAMANTITE_INGOT, ModBlocks.ADAMANTITE_BLOCK);
        generateCompactingRecipes(exporter, ModItems.HELLSTONE_INGOT, ModBlocks.HELLSTONE_BLOCK);

        // 各種ツールのレシピ
        generateToolRecipes(exporter, ModItems.COBALT_INGOT, "cobalt");
        generateToolRecipes(exporter, ModItems.ORICHALCUM_INGOT, "orichalcum");
        generateToolRecipes(exporter, ModItems.ADAMANTITE_INGOT, "adamantite");
        generateToolRecipes(exporter, ModItems.HELLSTONE_INGOT, "hellstone");
        // 各種防具のレシピ
        generateArmorRecipes(exporter, ModItems.COBALT_INGOT, "cobalt");
        generateArmorRecipes(exporter, ModItems.ORICHALCUM_INGOT, "orichalcum");
        generateArmorRecipes(exporter, ModItems.ADAMANTITE_INGOT, "adamantite");
        generateArmorRecipes(exporter, ModItems.HELLSTONE_INGOT, "hellstone");
        generateArmorRecipes(exporter, Blocks.OAK_LOG, "oak");
        generateArmorRecipes(exporter, Blocks.PUMPKIN, "pumpkin");
        generateArmorRecipes(exporter, Blocks.CACTUS, "cactus");
        generateArmorRecipes(exporter, Blocks.OBSIDIAN, "obsidian");
    }

    private void generateSmeltingAndBlastingRecipes(Consumer<RecipeJsonProvider> exporter, List<ItemConvertible> smeltables, ItemConvertible result, String name) {
        offerSmelting(exporter, smeltables, RecipeCategory.MISC, result, 0.7f, 200, name);
        offerBlasting(exporter, smeltables, RecipeCategory.MISC, result, 0.7f, 100, name);
    }

    private void generateCompactingRecipes(Consumer<RecipeJsonProvider> exporter, ItemConvertible ingot, ItemConvertible block) {
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ingot, RecipeCategory.DECORATIONS, block);
    }

    private void generateToolRecipes(Consumer<RecipeJsonProvider> exporter, ItemConvertible ingot, String materialName) {
        // ピッケル
        createToolRecipe(exporter, RecipeCategory.MISC, ModItems.getPickaxe(materialName), "III", " S ", " S ", ingot, materialName, "_pickaxe");

        // アックス
        createToolRecipe(exporter, RecipeCategory.MISC, ModItems.getAxe(materialName), " II", " SI", " S ", ingot, materialName, "_axe");

        // ソード
        createToolRecipe(exporter, RecipeCategory.MISC, ModItems.getSword(materialName), " I ", " I ", " S ", ingot, materialName, "_sword");

        // シャベル
        createToolRecipe(exporter, RecipeCategory.MISC, ModItems.getShovel(materialName), " I ", " S ", " S ", ingot, materialName, "_shovel");
    }

    private void createToolRecipe(Consumer<RecipeJsonProvider> exporter, RecipeCategory category, ItemConvertible tool, String row1, String row2, String row3, ItemConvertible ingot, String materialName, String toolType) {
        ShapedRecipeJsonBuilder.create(category, tool, 1)
                .pattern(row1)
                .pattern(row2)
                .pattern(row3)
                .input('S', Items.STICK)
                .input('I', ingot)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ingot), conditionsFromItem(ingot))
                .offerTo(exporter, new Identifier(getRecipeName(tool)));
    }

    // Armorレシピ生成関数
    private void generateArmorRecipes(Consumer<RecipeJsonProvider> exporter, ItemConvertible ingot, String materialName) {
        // ヘルメットのレシピ
        createArmorRecipe(exporter, RecipeCategory.MISC, ModItems.getHelmet(materialName), "III", "I I", "   ", ingot, materialName, "_helmet");

        // チェストプレートのレシピ
        createArmorRecipe(exporter, RecipeCategory.MISC, ModItems.getChestplate(materialName), "I I", "III", "III", ingot, materialName, "_chestplate");

        // レギンスのレシピ
        createArmorRecipe(exporter, RecipeCategory.MISC, ModItems.getLeggings(materialName), "III", "I I", "I I", ingot, materialName, "_leggings");

        // ブーツのレシピ
        createArmorRecipe(exporter, RecipeCategory.MISC, ModItems.getBoots(materialName), "   ", "I I", "I I", ingot, materialName, "_boots");
    }

    // 防具レシピを個別に作成する関数
    private void createArmorRecipe(Consumer<RecipeJsonProvider> exporter, RecipeCategory category, ItemConvertible armor, String row1, String row2, String row3, ItemConvertible ingot, String materialName, String armorType) {
        ShapedRecipeJsonBuilder.create(category, armor, 1)
                .pattern(row1)
                .pattern(row2)
                .pattern(row3)
                .input('I', ingot)
                .criterion(hasItem(ingot), conditionsFromItem(ingot))
                .offerTo(exporter, new Identifier("terrariamod", materialName + armorType));
    }
}
