package ketok.wilderness;

import com.mojang.logging.LogUtils;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import ketok.wilderness.registry.worldgen.WdConfiguredFeatures;
import ketok.wilderness.registry.worldgen.WdFeatures;
import ketok.wilderness.registry.worldgen.WdPlacedFeatures;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Wilderness.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Wilderness {
    public static final String MOD_ID = "wilderness";
    public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MOD_ID);
    public static final Logger LOGGER = LogUtils.getLogger();

    public Wilderness() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        REGISTRY_HELPER.register(bus);
        WdFeatures.HELPER.register(bus);
        WdFeatures.TreeDecorators.HELPER.register(bus);
        WdConfiguredFeatures.HELPER.register(bus);
        WdPlacedFeatures.HELPER.register(bus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, WdConfig.COMMON_SPEC);
    }
}
