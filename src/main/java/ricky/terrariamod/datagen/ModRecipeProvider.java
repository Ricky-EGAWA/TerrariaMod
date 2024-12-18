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
import ricky.terrariamod.TerrariaMod;
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

        //階段　ハーフブロックなど
        generateBlockRecipes(exporter, ModBlocks.EBON_PLANKS,"ebon");
        generateBlockRecipes(exporter, ModBlocks.CRIM_PLANKS,"crim");
        generateBlockRecipes(exporter, ModBlocks.PEARL_PLANKS,"pearl");
        //原木から木
        generateWoodBlockRecipes(exporter, ModBlocks.EBON_LOG,"ebon");
        generateWoodBlockRecipes(exporter, ModBlocks.CRIM_LOG,"crim");
        generateWoodBlockRecipes(exporter, ModBlocks.PEARL_LOG,"pearl");
        generateWoodBlockRecipes(exporter, ModBlocks.STRIPPED_EBON_LOG,"stripped_ebon");
        generateWoodBlockRecipes(exporter, ModBlocks.STRIPPED_CRIM_LOG,"stripped_crim");
        generateWoodBlockRecipes(exporter, ModBlocks.STRIPPED_PEARL_LOG,"stripped_pearl");
        //木材
        generatePlankBlockRecipes(exporter, ModBlocks.EBON_WOOD,"ebon");
        generatePlankBlockRecipes(exporter, ModBlocks.CRIM_WOOD,"crim");
        generatePlankBlockRecipes(exporter, ModBlocks.PEARL_WOOD,"pearl");
        generatePlankBlockRecipes(exporter, ModBlocks.EBON_LOG,"ebon_wood");
        generatePlankBlockRecipes(exporter, ModBlocks.CRIM_LOG,"crim_wood");
        generatePlankBlockRecipes(exporter, ModBlocks.PEARL_LOG,"pearl_wood");
        generatePlankBlockRecipes(exporter, ModBlocks.STRIPPED_EBON_WOOD,"stripped_ebon");
        generatePlankBlockRecipes(exporter, ModBlocks.STRIPPED_CRIM_WOOD,"stripped_crim");
        generatePlankBlockRecipes(exporter, ModBlocks.STRIPPED_PEARL_WOOD,"stripped_pearl");
        generatePlankBlockRecipes(exporter, ModBlocks.STRIPPED_EBON_LOG,"stripped_ebon_wood");
        generatePlankBlockRecipes(exporter, ModBlocks.STRIPPED_CRIM_LOG,"stripped_crim_wood");
        generatePlankBlockRecipes(exporter, ModBlocks.STRIPPED_PEARL_LOG,"stripped_pearl_wood");

        //その他
        createTwoItemRecipe(exporter,RecipeCategory.COMBAT,ModItems.ROCKET,"ABA","BAB","ABA",Items.GUNPOWDER,Items.IRON_INGOT,"rocket",3);
        createTwoItemRecipe(exporter,RecipeCategory.COMBAT,ModItems.IRON_BOW," AB","A B"," AB",Items.IRON_INGOT,Items.STRING,"iron_bow",1);
        createTwoItemRecipe(exporter,RecipeCategory.COMBAT,ModItems.GOLD_BOW," AB","A B"," AB",Items.GOLD_INGOT,Items.STRING,"gold_bow",1);
        createTwoItemRecipe(exporter,RecipeCategory.COMBAT,ModItems.DIAMOND_BOW," AB","A B"," AB",Items.DIAMOND,Items.STRING,"diamond_bow",1);

        createTwoItemRecipe(exporter,RecipeCategory.COMBAT,ModItems.AMETHYST_STAFF,"  B"," A ","A  ",Items.COPPER_INGOT,Items.AMETHYST_SHARD,"amethyst_staff",1);
        createTwoItemRecipe(exporter,RecipeCategory.COMBAT,ModItems.EMERALD_STAFF,"  B"," A ","A  ",Items.IRON_INGOT,Items.EMERALD,"emerald_staff",1);
        createTwoItemRecipe(exporter,RecipeCategory.COMBAT,ModItems.PHOENIX_BLASTER,"AAA","ABA","AAA",ModItems.HELLSTONE_INGOT,ModItems.HANDGUN,"phoenix_blaster",1);
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
        createStickItemRecipe(exporter, RecipeCategory.TOOLS, ModItems.getPickaxe(materialName), "III", " S ", " S ", ingot, materialName, "_pickaxe",1);
        // アックス
        createStickItemRecipe(exporter, RecipeCategory.TOOLS, ModItems.getAxe(materialName), " II", " SI", " S ", ingot, materialName, "_axe",1);
        // ソード
        createStickItemRecipe(exporter, RecipeCategory.COMBAT, ModItems.getSword(materialName), " I ", " I ", " S ", ingot, materialName, "_sword",1);
        // シャベル
        createStickItemRecipe(exporter, RecipeCategory.TOOLS, ModItems.getShovel(materialName), " I ", " S ", " S ", ingot, materialName, "_shovel",1);
    }

    private void createStickItemRecipe(Consumer<RecipeJsonProvider> exporter, RecipeCategory category, ItemConvertible tool, String row1, String row2, String row3, ItemConvertible ingot, String materialName, String toolType, int num) {
        ShapedRecipeJsonBuilder.create(category, tool, num)
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
        createOneItemRecipe(exporter, RecipeCategory.COMBAT, ModItems.getHelmet(materialName), "III", "I I", "   ", ingot, materialName, "_helmet",1);
        // チェストプレートのレシピ
        createOneItemRecipe(exporter, RecipeCategory.COMBAT, ModItems.getChestplate(materialName), "I I", "III", "III", ingot, materialName, "_chestplate",1);
        // レギンスのレシピ
        createOneItemRecipe(exporter, RecipeCategory.COMBAT, ModItems.getLeggings(materialName), "III", "I I", "I I", ingot, materialName, "_leggings",1);
        // ブーツのレシピ
        createOneItemRecipe(exporter, RecipeCategory.COMBAT, ModItems.getBoots(materialName), "   ", "I I", "I I", ingot, materialName, "_boots",1);
    }

    // 防具レシピを個別に作成する関数
    private void createOneItemRecipe(Consumer<RecipeJsonProvider> exporter, RecipeCategory category, ItemConvertible armor, String row1, String row2, String row3, ItemConvertible ingot, String materialName, String armorType, int num) {
        ShapedRecipeJsonBuilder.create(category, armor, num)
                .pattern(row1)
                .pattern(row2)
                .pattern(row3)
                .input('I', ingot)
                .criterion(hasItem(ingot), conditionsFromItem(ingot))
                .offerTo(exporter, new Identifier(TerrariaMod.MOD_ID, materialName + armorType));
    }
    private void createTwoItemRecipe(Consumer<RecipeJsonProvider> exporter, RecipeCategory category, ItemConvertible armor, String row1, String row2, String row3, ItemConvertible item1, ItemConvertible item2, String materialName, int num) {
        ShapedRecipeJsonBuilder.create(category, armor, num)
                .pattern(row1)
                .pattern(row2)
                .pattern(row3)
                .input('A', item1)
                .input('B', item2)
                .criterion(hasItem(item1), conditionsFromItem(item1))
                .criterion(hasItem(item2), conditionsFromItem(item2))
                .offerTo(exporter, new Identifier(TerrariaMod.MOD_ID, materialName));
    }

    //ブロックレシピ
    private void generateBlockRecipes(Consumer<RecipeJsonProvider> exporter, ItemConvertible block, String materialName) {
        // ハーフブロック
        createOneItemRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.getSlab(materialName), "   ", "III", "   ", block, materialName, "_slab",6);
        // 階段
        createOneItemRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.getStairs(materialName), "I  ", "II ", "III", block, materialName, "_stairs",4);
        // フェンス
        createStickItemRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.getFence(materialName), "   ", "ISI", "ISI", block, materialName, "_fence",3);
        // フェンスゲート
        createStickItemRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.getFenceGate(materialName), "   ", "SIS", "SIS", block, materialName, "_fence_gate",1);
        // ボタン
        createOneItemRecipe(exporter, RecipeCategory.REDSTONE, ModBlocks.getButton(materialName), "   ", " I ", "   ", block, materialName, "_button",1);
        // 感圧版
        createOneItemRecipe(exporter, RecipeCategory.REDSTONE, ModBlocks.getPressurePlate(materialName), "   ", " II", "   ", block, materialName, "_pressure_plate",1);
    }
    private void generateWoodBlockRecipes(Consumer<RecipeJsonProvider> exporter, ItemConvertible block, String materialName) {
        // 木
        createOneItemRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.getWood(materialName), "II ", "II ", "   ", block, materialName, "_wood",3);
    }
    private void generatePlankBlockRecipes(Consumer<RecipeJsonProvider> exporter, ItemConvertible block, String materialName) {
        // 板材
        createOneItemRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.getPlanks(materialName), "   ", " I ", "   ", block, materialName, "_planks",4);
    }
}
