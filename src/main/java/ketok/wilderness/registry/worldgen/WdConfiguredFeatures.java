package ketok.wilderness.registry.worldgen;

import ketok.wilderness.Wilderness;
import ketok.wilderness.common.worldgen.feature.FallenTreeFeature;
import ketok.wilderness.common.worldgen.feature.treedecorators.BlockOnFallenLogDecorator;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.TreePlacements;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TrunkVineDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.OptionalInt;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class WdConfiguredFeatures {
    public static DeferredRegister<ConfiguredFeature<?, ?>> HELPER = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, Wilderness.MOD_ID);

    // Fallen trees
    private static BlockOnFallenLogDecorator block(Block block, float chance) {
        return new BlockOnFallenLogDecorator(block, chance);
    }
    public static final RegistryObject<ConfiguredFeature<?, ?>> FALLEN_OAK = fallenTree("fallen_oak", Blocks.OAK_LOG, block(Blocks.MOSS_CARPET, 0.4F), block(Blocks.BROWN_MUSHROOM, 0.2F), block(Blocks.RED_MUSHROOM, 0.2F));
    public static final RegistryObject<ConfiguredFeature<?, ?>> FALLEN_BIRCH = fallenTree("fallen_birch", Blocks.BIRCH_LOG, block(Blocks.MOSS_CARPET, 0.4F), block(Blocks.BROWN_MUSHROOM, 0.2F), block(Blocks.RED_MUSHROOM, 0.2F));
    public static final RegistryObject<ConfiguredFeature<?, ?>> FALLEN_SPRUCE = fallenTree("fallen_spruce", Blocks.SPRUCE_LOG, block(Blocks.MOSS_CARPET, 0.3F), block(Blocks.BROWN_MUSHROOM, 0.3F), block(Blocks.RED_MUSHROOM, 0.3F));
    public static final RegistryObject<ConfiguredFeature<?, ?>> FALLEN_JUNGLE_TREE = fallenTree("fallen_jungle_tree", Blocks.JUNGLE_LOG, TrunkVineDecorator.INSTANCE, block(Blocks.MOSS_CARPET, 0.5F));

    // Trees
    public static final RegistryObject<ConfiguredFeature<?, ?>> MEDIUM_OAK = HELPER.register("medium_oak",
            () -> new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(Blocks.OAK_LOG),
                    new FancyTrunkPlacer(6, 2, 0),
                    BlockStateProvider.simple(Blocks.OAK_LEAVES),
                    new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4),
                    new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))
            ).ignoreVines().build()));

    // Trees for biomes
    public static final RegistryObject<ConfiguredFeature<?, ?>> TREES_OLD_GROWTH_FOREST = HELPER.register("trees_old_growth_forest",
            () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
                    List.of(
                            new WeightedPlacedFeature(TreePlacements.OAK_BEES_002, 0.1F),
                            new WeightedPlacedFeature(TreePlacements.BIRCH_BEES_002, 0.1F)
                    ), TreePlacements.FANCY_OAK_BEES_002
            ))
    );

    public static final RegistryObject<ConfiguredFeature<?, ?>> TREES_MIXED_FOREST = HELPER.register("trees_mixed_forest",
            () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
                    List.of(new WeightedPlacedFeature(TreePlacements.SPRUCE_CHECKED, 0.5F),
                            new WeightedPlacedFeature(TreePlacements.OAK_BEES_002, 0.1F)),
                    WdPlacedFeatures.MEDIUM_OAK.getHolder().get()
            ))
    );

    // Other
    public static final RegistryObject<ConfiguredFeature<?, ?>> BROWN_RED_MUSHROOM_PATCH = HELPER.register("brown_red_mushroom_patch",
        () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH,
            FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
                    SimpleWeightedRandomList.<BlockState>builder()
                            .add(Blocks.RED_MUSHROOM.defaultBlockState(), 1)
                            .add(Blocks.BROWN_MUSHROOM.defaultBlockState(), 1)
                            .build()
            ))))
    );

    public static final RegistryObject<ConfiguredFeature<?, ?>> PATCH_COARSE_DIRT = surfacePatch("patch_coarse_dirt", Blocks.COARSE_DIRT);
    public static final RegistryObject<ConfiguredFeature<?, ?>> PATCH_PODZOL = surfacePatch("patch_podzol", Blocks.PODZOL);

    private static RegistryObject<ConfiguredFeature<?, ?>> fallenTree(String name, Block log, TreeDecorator... decorators) {
        return HELPER.register(name,
                () -> new ConfiguredFeature<>(WdFeatures.FALLEN_TREE.get(), new FallenTreeFeature.Config(
                        BlockStateProvider.simple(log),
                        UniformInt.of(5, 6),
                        List.of(decorators)
                ))
        );
    }

    private static RegistryObject<ConfiguredFeature<?, ?>> surfacePatch(String name, Block block) {
        return HELPER.register(name,
                () -> new ConfiguredFeature<>(Feature.ORE,
                        new OreConfiguration(
                                new BlockMatchTest(Blocks.GRASS_BLOCK),
                                block.defaultBlockState(),
                                33
                        )
                )
        );
    }
}
