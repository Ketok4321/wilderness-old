package ketok.wilderness;

import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod(Wilderness.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Wilderness {
    public static final String MOD_ID = "wilderness";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Wilderness() {

    }
}
