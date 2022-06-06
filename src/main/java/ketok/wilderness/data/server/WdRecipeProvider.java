package ketok.wilderness.data.server;

import ketok.wilderness.registry.WdBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

import static net.minecraft.data.recipes.ShapelessRecipeBuilder.shapeless;

public class WdRecipeProvider extends RecipeProvider {
    public WdRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        shapeless(WdBlocks.MOSSY_OAK_LOG.get())
                .requires(Blocks.OAK_LOG)
                .requires(Blocks.MOSS_BLOCK)
                .unlockedBy("has_moss", has(Blocks.MOSS_BLOCK))
                .save(consumer);
    }
}
