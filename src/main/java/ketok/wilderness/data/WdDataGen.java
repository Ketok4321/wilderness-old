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
    public static void dataSetup(GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        if (event.includeServer()) {
            dataGenerator.addProvider(new WdRecipeProvider(dataGenerator));
            dataGenerator.addProvider(new WdLootTableProvider(dataGenerator));
            dataGenerator.addProvider(new WdModdedBiomeSlicesProvider(dataGenerator));

            // Tags
            WdBlockTagsProvider blockTags = new WdBlockTagsProvider(dataGenerator, existingFileHelper);
            dataGenerator.addProvider(blockTags);
            dataGenerator.addProvider(new WdItemTagsProvider(dataGenerator, blockTags, existingFileHelper));
            dataGenerator.addProvider(new WdBiomeTagsProvider(dataGenerator, existingFileHelper));

            // Modifiers
            dataGenerator.addProvider(new WdAdvancementModifiersProvider(dataGenerator));
        }

        if (event.includeClient()) {
            dataGenerator.addProvider(new WdBlockStateProvider(dataGenerator, existingFileHelper));
            dataGenerator.addProvider(new WdItemModelProvider(dataGenerator, existingFileHelper));
        }
    }
}
