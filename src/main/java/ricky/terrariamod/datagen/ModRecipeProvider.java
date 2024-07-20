package ricky.terrariamod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
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
            ModBlocks.COBALT_ORE);
    private static final List<ItemConvertible> ORICHALCUM_SMELTABLE = List.of(ModItems.ORICHALCUM_RAW,
            ModBlocks.ORICHALCUM_ORE);
    private static final List<ItemConvertible> ADAMANTITE_SMELTABLE = List.of(ModItems.ADAMANTITE_RAW,
            ModBlocks.ADAMANTITE_ORE);
    private static final List<ItemConvertible> HELLSTONE_SMELTABLE = List.of(ModItems.HELLSTONE_RAW,
            ModBlocks.HELLSTONE_ORE);

    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        offerSmelting(exporter, COBALT_SMELTABLE, RecipeCategory.MISC, ModItems.COBALT_INGOT,
                0.7f,200,"cobalt");
        offerBlasting(exporter, COBALT_SMELTABLE, RecipeCategory.MISC, ModItems.COBALT_INGOT,
                0.7f,100,"cobalt");
        offerSmelting(exporter, ORICHALCUM_SMELTABLE, RecipeCategory.MISC, ModItems.ORICHALCUM_INGOT,
                0.7f,200,"orichalcum");
        offerBlasting(exporter, ORICHALCUM_SMELTABLE, RecipeCategory.MISC, ModItems.ORICHALCUM_INGOT,
                0.7f,100,"orichalcum");
        offerSmelting(exporter, ADAMANTITE_SMELTABLE, RecipeCategory.MISC, ModItems.ADAMANTITE_INGOT,
                0.7f,200,"adamantite");
        offerBlasting(exporter, ADAMANTITE_SMELTABLE, RecipeCategory.MISC, ModItems.ADAMANTITE_INGOT,
                0.7f,100,"adamantite");
        offerSmelting(exporter, HELLSTONE_SMELTABLE, RecipeCategory.MISC, ModItems.HELLSTONE_INGOT,
                0.7f,200,"hellstone");
        offerBlasting(exporter, HELLSTONE_SMELTABLE, RecipeCategory.MISC, ModItems.HELLSTONE_INGOT,
                0.7f,100,"hellstone");

        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.COBALT_INGOT, RecipeCategory.DECORATIONS,
                ModBlocks.COBALT_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.ORICHALCUM_INGOT, RecipeCategory.DECORATIONS,
                ModBlocks.ORICHALCUM_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.ADAMANTITE_INGOT, RecipeCategory.DECORATIONS,
                ModBlocks.ADAMANTITE_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.HELLSTONE_INGOT, RecipeCategory.DECORATIONS,
                ModBlocks.HELLSTONE_BLOCK);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.COBALT_RAW, 1)
                .pattern("SSS")
                .pattern("SRS")
                .pattern("SSS")
                .input('S', Items.STONE)
                .input('R', ModItems.COBALT_INGOT)
                .criterion(hasItem(Items.STONE), conditionsFromItem(Items.STONE))
                .criterion(hasItem(ModItems.COBALT_INGOT), conditionsFromItem(ModItems.COBALT_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.COBALT_RAW)));
//      Pickaxe recipe
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.COBALT_PICKAXE, 1)
                .pattern("III")
                .pattern(" S ")
                .pattern(" S ")
                .input('S', Items.STICK)
                .input('I', ModItems.COBALT_INGOT)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.COBALT_INGOT), conditionsFromItem(ModItems.COBALT_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.COBALT_PICKAXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ORICHALCUM_PICKAXE, 1)
                .pattern("III")
                .pattern(" S ")
                .pattern(" S ")
                .input('S', Items.STICK)
                .input('I', ModItems.ORICHALCUM_INGOT)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.ORICHALCUM_INGOT), conditionsFromItem(ModItems.ORICHALCUM_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ORICHALCUM_PICKAXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ADAMANTITE_PICKAXE, 1)
                .pattern("III")
                .pattern(" S ")
                .pattern(" S ")
                .input('S', Items.STICK)
                .input('I', ModItems.ADAMANTITE_INGOT)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.ADAMANTITE_INGOT), conditionsFromItem(ModItems.ADAMANTITE_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ADAMANTITE_PICKAXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HELLSTONE_PICKAXE, 1)
                .pattern("III")
                .pattern(" S ")
                .pattern(" S ")
                .input('S', Items.STICK)
                .input('I', ModItems.HELLSTONE_INGOT)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.HELLSTONE_INGOT), conditionsFromItem(ModItems.HELLSTONE_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.HELLSTONE_PICKAXE)));
//        AXE Recipe
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.COBALT_AXE, 1)
                .pattern(" II")
                .pattern(" SI")
                .pattern(" S ")
                .input('S', Items.STICK)
                .input('I', ModItems.COBALT_INGOT)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.COBALT_INGOT), conditionsFromItem(ModItems.COBALT_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.COBALT_AXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ORICHALCUM_AXE, 1)
                .pattern(" II")
                .pattern(" SI")
                .pattern(" S ")
                .input('S', Items.STICK)
                .input('I', ModItems.ORICHALCUM_INGOT)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.ORICHALCUM_INGOT), conditionsFromItem(ModItems.ORICHALCUM_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ORICHALCUM_AXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ADAMANTITE_AXE, 1)
                .pattern(" II")
                .pattern(" SI")
                .pattern(" S ")
                .input('S', Items.STICK)
                .input('I', ModItems.ADAMANTITE_INGOT)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.ADAMANTITE_INGOT), conditionsFromItem(ModItems.ADAMANTITE_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ADAMANTITE_AXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HELLSTONE_AXE, 1)
                .pattern(" II")
                .pattern(" SI")
                .pattern(" S ")
                .input('S', Items.STICK)
                .input('I', ModItems.HELLSTONE_INGOT)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.HELLSTONE_INGOT), conditionsFromItem(ModItems.HELLSTONE_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.HELLSTONE_AXE)));
//      sword recipe
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.COBALT_SWORD, 1)
                .pattern(" I ")
                .pattern(" I ")
                .pattern(" S ")
                .input('S', Items.STICK)
                .input('I', ModItems.COBALT_INGOT)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.COBALT_INGOT), conditionsFromItem(ModItems.COBALT_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.COBALT_SWORD)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ORICHALCUM_SWORD, 1)
                .pattern(" I ")
                .pattern(" I ")
                .pattern(" S ")
                .input('S', Items.STICK)
                .input('I', ModItems.ORICHALCUM_INGOT)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.ORICHALCUM_INGOT), conditionsFromItem(ModItems.ORICHALCUM_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ORICHALCUM_SWORD)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ADAMANTITE_SWORD, 1)
                .pattern(" I ")
                .pattern(" I ")
                .pattern(" S ")
                .input('S', Items.STICK)
                .input('I', ModItems.ADAMANTITE_INGOT)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.ADAMANTITE_INGOT), conditionsFromItem(ModItems.ADAMANTITE_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ADAMANTITE_SWORD)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HELLSTONE_SWORD, 1)
                .pattern(" I ")
                .pattern(" I ")
                .pattern(" S ")
                .input('S', Items.STICK)
                .input('I', ModItems.HELLSTONE_INGOT)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.HELLSTONE_INGOT), conditionsFromItem(ModItems.HELLSTONE_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.HELLSTONE_SWORD)));
//      shovel recipe
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.COBALT_SHOVEL, 1)
                .pattern(" I ")
                .pattern(" S ")
                .pattern(" S ")
                .input('S', Items.STICK)
                .input('I', ModItems.COBALT_INGOT)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.COBALT_INGOT), conditionsFromItem(ModItems.COBALT_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.COBALT_SHOVEL)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ORICHALCUM_SHOVEL, 1)
                .pattern(" I ")
                .pattern(" S ")
                .pattern(" S ")
                .input('S', Items.STICK)
                .input('I', ModItems.ORICHALCUM_INGOT)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.ORICHALCUM_INGOT), conditionsFromItem(ModItems.ORICHALCUM_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ORICHALCUM_SHOVEL)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ADAMANTITE_SHOVEL, 1)
                .pattern(" I ")
                .pattern(" S ")
                .pattern(" S ")
                .input('S', Items.STICK)
                .input('I', ModItems.ADAMANTITE_INGOT)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.ADAMANTITE_INGOT), conditionsFromItem(ModItems.ADAMANTITE_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ADAMANTITE_SHOVEL)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HELLSTONE_SHOVEL, 1)
                .pattern(" I ")
                .pattern(" S ")
                .pattern(" S ")
                .input('S', Items.STICK)
                .input('I', ModItems.HELLSTONE_INGOT)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.HELLSTONE_INGOT), conditionsFromItem(ModItems.HELLSTONE_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.HELLSTONE_SHOVEL)));
    }
}
