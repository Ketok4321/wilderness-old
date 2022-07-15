package ketok.wilderness.registry;

import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import ketok.wilderness.Wilderness;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Wilderness.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WdItems {
    public static final ItemSubRegistryHelper HELPER = Wilderness.REGISTRY_HELPER.getItemSubHelper();

    public static final RegistryObject<Item> BLACKBERRIES = HELPER.createItem("blackberries", () -> new ItemNameBlockItem(WdBlocks.BLACKBERRY_BUSH.get(), new Item.Properties().food(Foods.SWEET_BERRIES).tab(CreativeModeTab.TAB_FOOD)));

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            DataUtil.registerCompostable(BLACKBERRIES.get(), 0.3F);
        });
    }
}
