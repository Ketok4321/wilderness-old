package ketok.wilderness.registry.worldgen;

import ketok.wilderness.Wilderness;
import ketok.wilderness.common.worldgen.feature.FallenTreeFeature;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.TreePlacements;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class WdConfiguredFeatures {
    public static DeferredRegister<ConfiguredFeature<?, ?>> HELPER = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, Wilderness.MOD_ID);

    //TODO: Old growth fallen trees?
    public static final RegistryObject<ConfiguredFeature<?, ?>> FALLEN_OAK = fallenTree("fallen_oak", Blocks.OAK_LOG, 2, 1);
    public static final RegistryObject<ConfiguredFeature<?, ?>> FALLEN_BIRCH = fallenTree("fallen_birch", Blocks.BIRCH_LOG, 2, 1);
    public static final RegistryObject<ConfiguredFeature<?, ?>> FALLEN_SPRUCE = fallenTree("fallen_spruce", Blocks.SPRUCE_LOG, 1, 1);
    public static final RegistryObject<ConfiguredFeature<?, ?>> FALLEN_JUNGLE_TREE = fallenTree("fallen_jungle_tree", Blocks.JUNGLE_LOG, 1, 0);

    public static final RegistryObject<ConfiguredFeature<?, ?>> TREES_OLD_GROWTH_FOREST = HELPER.register("trees_old_growth_forest",
            () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
                    List.of(new WeightedPlacedFeature(TreePlacements.OAK_BEES_002, 0.1F),
                            new WeightedPlacedFeature(TreePlacements.BIRCH_BEES_002, 0.1F)),
                    TreePlacements.FANCY_OAK_BEES_002
            ))
    );

    public static final RegistryObject<ConfiguredFeature<?, ?>> BROWN_RED_MUSHROOM_PATCH = HELPER.register("brown_red_mushroom_patch",
        () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH,
            FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
                    SimpleWeightedRandomList.<BlockState>builder()
                            .add(Blocks.RED_MUSHROOM.defaultBlockState(), 1)
                            .add(Blocks.BROWN_MUSHROOM.defaultBlockState(), 1)
                            .build()
            ))))
    );

    private static RegistryObject<ConfiguredFeature<?, ?>> fallenTree(String name, Block log, int mossWeight, int mushroomWeight) {
        return HELPER.register(name,
                () -> new ConfiguredFeature<>(WdFeatures.FALLEN_TREE.get(), new FallenTreeFeature.Config(
                        BlockStateProvider.simple(log),
                        UniformInt.of(5, 6),
                        new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                                .add(Blocks.MOSS_CARPET.defaultBlockState(), mossWeight)
                                .add(Blocks.BROWN_MUSHROOM.defaultBlockState(), mushroomWeight)
                                .add(Blocks.RED_MUSHROOM.defaultBlockState(), mushroomWeight)),
                        0.5F
                ))
        );
    }
}
