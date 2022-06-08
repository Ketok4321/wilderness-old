package ketok.wilderness.registry;

import com.teamabnormals.blueprint.core.util.registry.SoundSubRegistryHelper;
import ketok.wilderness.Wilderness;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Wilderness.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)

public class WdSounds {
    public static final SoundSubRegistryHelper HELPER = Wilderness.REGISTRY_HELPER.getSoundSubHelper();

    public static final RegistryObject<SoundEvent> UNMOSS = HELPER.createSoundEvent("item.axe.unmoss");
}
