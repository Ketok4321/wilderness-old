package ketok.wilderness.data.server;

import ketok.wilderness.Wilderness;
import ketok.wilderness.registry.WdBlocks;
import ketok.wilderness.registry.WdItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

import static net.minecraft.data.recipes.ShapelessRecipeBuilder.shapeless;

public class WdRecipeProvider extends RecipeProvider {
    public WdRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        shapeless(WdBlocks.MOSSY_OAK_LOG.get())
                .requires(Blocks.OAK_LOG)
                .requires(Blocks.MOSS_BLOCK)
                .unlockedBy("has_moss", has(Blocks.MOSS_BLOCK))
                .save(consumer);

        woodFromLogs(consumer, WdBlocks.MOSSY_OAK_WOOD.get(), WdBlocks.MOSSY_OAK_LOG.get());

        shapeless(Items.PURPLE_DYE)
                .requires(WdItems.BLACKBERRIES.get())
                .group("purple_dye")
                .unlockedBy("has_blackberries", has(WdItems.BLACKBERRIES.get()))
                .save(consumer, Wilderness.REGISTRY_HELPER.prefix(getConversionRecipeName(Items.PURPLE_DYE, WdItems.BLACKBERRIES.get())));
    }
}
