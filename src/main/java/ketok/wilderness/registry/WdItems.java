package ketok.wilderness.registry;

import com.teamabnormals.blueprint.common.item.InjectedItem;
import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import ketok.wilderness.WdConfig;
import ketok.wilderness.Wilderness;
import ketok.wilderness.common.item.ItemNameInjectedBlockItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Wilderness.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WdItems {
    public static final ItemSubRegistryHelper HELPER = Wilderness.REGISTRY_HELPER.getItemSubHelper();

    public static final RegistryObject<Item> BLACKBERRIES = HELPER.createItem("blackberries", () -> new ItemNameInjectedBlockItem(Items.GLOW_BERRIES, WdBlocks.BLACKBERRY_BUSH.get(), new Item.Properties().food(WdFoods.BLACKBERRIES).tab(CreativeModeTab.TAB_FOOD)));
    public static final RegistryObject<Item> BLACKBERRY_PIE = HELPER.createItem("blackberry_pie", () -> new InjectedItem(Items.PUMPKIN_PIE, new Item.Properties().food(WdFoods.BLACKBERRY_PIE).tab(CreativeModeTab.TAB_FOOD)));

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            DataUtil.registerCompostable(BLACKBERRIES.get(), 0.3F);
            DataUtil.registerCompostable(BLACKBERRY_PIE.get(), 1.0F);

            if(WdConfig.COMMON.fastSweetBerries.get()) Foods.SWEET_BERRIES.fastFood = true;
            if(WdConfig.COMMON.fastBlackberries.get()) WdFoods.BLACKBERRIES.fastFood = true;
        });
    }

    private static class WdFoods {

        public static final FoodProperties BLACKBERRIES = food(3, 0.1F).build();
        public static final FoodProperties BLACKBERRY_PIE = food(8, 0.6F).build();

        private static FoodProperties.Builder food(int nutrition, float saturationMod) {
            return new FoodProperties.Builder()
                    .nutrition(nutrition)
                    .saturationMod(saturationMod);
        }
    }
}
