package ketok.wilderness;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import org.apache.commons.lang3.tuple.Pair;

public class WdConfig {
    public static class Common {
        public final ConfigValue<Boolean> removeLavaLakesFromForests;
        public final ConfigValue<Boolean> generateFallenTrees;

        public final ConfigValue<Boolean> shearableBerries;

        public final ConfigValue<Boolean> fastSweetBerries;
        public final ConfigValue<Boolean> fastBlackberries;

        Common(ForgeConfigSpec.Builder builder) {
            builder.push("biome_modification");
            generateFallenTrees = builder
                    .comment("If true, fallen trees will generate in forest biomes")
                    .define("Fallen trees", true);
            removeLavaLakesFromForests = builder
                    .comment("If true, surface lava lakes will not generate in biomes with forest or taiga category")
                    .define("No lava lakes in forests", true);
            builder.pop();

            builder.push("other");
            shearableBerries = builder
                    .comment("If true, sweet berries and blackberries can be sheared in order to stop their growth")
                    .define("Shearable berries", true);
            fastSweetBerries = builder
                    .comment("If true, sweet berries will be faster to eat")
                    .define("Fast sweet berries", false);
            fastBlackberries = builder
                    .comment("If true, blackberries will be faster to eat")
                    .define("Fast blackberries", false);
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
