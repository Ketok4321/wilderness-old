package ketok.wilderness.data;

import ketok.wilderness.Wilderness;
import ketok.wilderness.data.server.modifiers.WdAdvancementModifierProvider;
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
            dataGenerator.addProvider(new WdAdvancementModifierProvider(dataGenerator));
        }

        if (event.includeClient()) {

        }
    }
}
