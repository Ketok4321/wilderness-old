package ketok.wilderness.registry.worldgen;

import ketok.wilderness.Wilderness;
import ketok.wilderness.common.worldgen.feature.FallenTreeFeature;
import net.minecraft.core.Registry;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class WdConfiguredFeatures {
    public static DeferredRegister<ConfiguredFeature<?, ?>> HELPER = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, Wilderness.MOD_ID);

    public static final RegistryObject<ConfiguredFeature<?, ?>> FALLEN_OAK = HELPER.register("fallen_oak",
            () -> new ConfiguredFeature<>(WdFeatures.FALLEN_TREE.get(), new FallenTreeFeature.Config(
                    BlockStateProvider.simple(Blocks.OAK_LOG),
                    UniformInt.of(5, 6),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                            .add(Blocks.MOSS_CARPET.defaultBlockState(), 2)
                            .add(Blocks.BROWN_MUSHROOM.defaultBlockState(), 1)
                            .add(Blocks.RED_MUSHROOM.defaultBlockState(), 1)),
                    0.5F
            ))
    );
}
