package ketok.wilderness;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import org.apache.commons.lang3.tuple.Pair;

public class WdConfig {
    public static class Common {
        public final ConfigValue<Boolean> noLavaLakesInForests;

        Common(ForgeConfigSpec.Builder builder) {
            builder.push("tweaks");
                noLavaLakesInForests = builder
                        .comment("If true, surface lava lakes will not generate in biomes with forest or taiga category")
                        .define("No lava lakes in forests", true);
            builder.pop();
        }
    }

    public static final ForgeConfigSpec COMMON_SPEC;
    public static final Common COMMON;

    static {
        Pair<Common, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = commonSpecPair.getRight();
        COMMON = commonSpecPair.getLeft();
    }
}
