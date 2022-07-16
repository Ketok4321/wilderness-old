package ketok.wilderness.data;

import ketok.wilderness.Wilderness;
import ketok.wilderness.data.client.WdBlockStateProvider;
import ketok.wilderness.data.client.WdItemModelProvider;
import ketok.wilderness.data.server.WdLootTableProvider;
import ketok.wilderness.data.server.WdModdedBiomeSlicesProvider;
import ketok.wilderness.data.server.WdRecipeProvider;
import ketok.wilderness.data.server.modifiers.WdAdvancementModifiersProvider;
import ketok.wilderness.data.server.tags.WdBiomeTagsProvider;
import ketok.wilderness.data.server.tags.WdBlockTagsProvider;
import ketok.wilderness.data.server.tags.WdItemTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = Wilderness.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WdDataGen {
    @SubscribeEvent
    public static void onDataSetup(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        if (event.includeServer()) {
            generator.addProvider(new WdRecipeProvider(generator));
            generator.addProvider(new WdLootTableProvider(generator));
            generator.addProvider(new WdModdedBiomeSlicesProvider(generator));

            // Tags
            WdBlockTagsProvider blockTags = new WdBlockTagsProvider(generator, existingFileHelper);
            generator.addProvider(blockTags);
            generator.addProvider(new WdItemTagsProvider(generator, blockTags, existingFileHelper));
            generator.addProvider(new WdBiomeTagsProvider(generator, existingFileHelper));

            // Modifiers
            generator.addProvider(new WdAdvancementModifiersProvider(generator));
        }

        if (event.includeClient()) {
            generator.addProvider(new WdBlockStateProvider(generator, existingFileHelper));
            generator.addProvider(new WdItemModelProvider(generator, existingFileHelper));
        }
    }
}
