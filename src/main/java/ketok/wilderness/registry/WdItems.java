package ketok.wilderness.registry;

import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import ketok.wilderness.WdConfig;
import ketok.wilderness.Wilderness;
import net.minecraft.world.food.FoodProperties;
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

    public static final RegistryObject<Item> BLACKBERRIES = HELPER.createItem("blackberries", () -> new ItemNameBlockItem(WdBlocks.BLACKBERRY_BUSH.get(), new Item.Properties().food(WdFoods.BLACKBERRIES).tab(CreativeModeTab.TAB_FOOD)));

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            DataUtil.registerCompostable(BLACKBERRIES.get(), 0.3F);

            if(WdConfig.COMMON.fastSweetBerries.get()) Foods.SWEET_BERRIES.fastFood = true;
            if(WdConfig.COMMON.fastBlackberries.get()) WdFoods.BLACKBERRIES.fastFood = true;
        });
    }

    private static class WdFoods {
        public static final FoodProperties BLACKBERRIES = food(3, 0.1F).build();

        private static FoodProperties.Builder food(int nutrition, float saturationMod) {
            return new FoodProperties.Builder()
                    .nutrition(nutrition)
                    .saturationMod(saturationMod);
        }
    }
}
