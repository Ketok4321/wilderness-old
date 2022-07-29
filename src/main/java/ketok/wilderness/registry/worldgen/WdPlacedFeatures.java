package ketok.wilderness.registry.worldgen;

import ketok.wilderness.Wilderness;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.MiscOverworldFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class WdPlacedFeatures {
    public static DeferredRegister<PlacedFeature> HELPER = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, Wilderness.MOD_ID);

    // Trees
    public static final RegistryObject<PlacedFeature> MEDIUM_OAK = treeFeature("medium_oak", WdConfiguredFeatures.MEDIUM_OAK.getHolder().get());
    public static final RegistryObject<PlacedFeature> MOSSY_FANCY_OAK = treeFeature("mossy_fancy_oak", WdConfiguredFeatures.MOSSY_FANCY_OAK.getHolder().get());

    // Fallen trees
    public static final RegistryObject<PlacedFeature> FALLEN_OAK = surfaceFeature("fallen_oak", WdConfiguredFeatures.FALLEN_OAK.getHolder().get(), RarityFilter.onAverageOnceEvery(8));
    public static final RegistryObject<PlacedFeature> FALLEN_BIRCH = surfaceFeature("fallen_birch", WdConfiguredFeatures.FALLEN_BIRCH.getHolder().get(), RarityFilter.onAverageOnceEvery(8));
    public static final RegistryObject<PlacedFeature> FALLEN_SPRUCE = surfaceFeature("fallen_spruce", WdConfiguredFeatures.FALLEN_SPRUCE.getHolder().get(), RarityFilter.onAverageOnceEvery(8));
    public static final RegistryObject<PlacedFeature> FALLEN_JUNGLE_TREE = surfaceFeature("fallen_jungle_tree", WdConfiguredFeatures.FALLEN_JUNGLE_TREE.getHolder().get(), RarityFilter.onAverageOnceEvery(8));

    // Trees for biomes
    public static final RegistryObject<PlacedFeature> TREES_OLD_GROWTH_FOREST = surfaceFeature("trees_old_growth_forest", WdConfiguredFeatures.TREES_OLD_GROWTH_FOREST.getHolder().get(), CountPlacement.of(10));
    public static final RegistryObject<PlacedFeature> TREES_MIXED_FOREST = surfaceFeature("trees_mixed_forest", WdConfiguredFeatures.TREES_MIXED_FOREST.getHolder().get(), CountPlacement.of(8));

    // Other
    public static final RegistryObject<PlacedFeature> BROWN_RED_MUSHROOM_PATCH = surfaceFeature("brown_red_mushroom_patch", WdConfiguredFeatures.BROWN_RED_MUSHROOM_PATCH.getHolder().get(), CountPlacement.of(3), RarityFilter.onAverageOnceEvery(2));
    public static final RegistryObject<PlacedFeature> FOREST_ROCK_RARE = surfaceFeature("forest_rock_rare", MiscOverworldFeatures.FOREST_ROCK, RarityFilter.onAverageOnceEvery(4));
    public static final RegistryObject<PlacedFeature> PATCH_COARSE_DIRT = surfaceFeature("patch_coarse_dirt", WdConfiguredFeatures.PATCH_COARSE_DIRT.getHolder().get(), CountPlacement.of(2));
    public static final RegistryObject<PlacedFeature> PATCH_PODZOL = surfaceFeature("patch_podzol", WdConfiguredFeatures.PATCH_PODZOL.getHolder().get(), CountPlacement.of(1));
    public static final RegistryObject<PlacedFeature> PATCH_MOSS = surfaceFeature("patch_moss", WdConfiguredFeatures.PATCH_MOSS.getHolder().get(), CountPlacement.of(2));
    public static final RegistryObject<PlacedFeature> PATCH_BLACKBERRY_BUSH = surfaceFeature("patch_blackberry_bush", WdConfiguredFeatures.PATCH_BLACKBERRY_BUSH.getHolder().get(), RarityFilter.onAverageOnceEvery(16));

    private static RegistryObject<PlacedFeature> surfaceFeature(String name, Holder<? extends ConfiguredFeature<?, ?>> feature, PlacementModifier... placementModifiers) {
        ArrayList<PlacementModifier> modifiers = new ArrayList<>();
        Collections.addAll(modifiers, placementModifiers);
        modifiers.addAll(List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome(), VegetationPlacements.TREE_THRESHOLD));
        return HELPER.register(name, () -> new PlacedFeature(Holder.hackyErase(feature), modifiers));
    }

    private static RegistryObject<PlacedFeature> treeFeature(String name, Holder<ConfiguredFeature<?, ?>> feature) {
        return HELPER.register(name, () -> new PlacedFeature(feature, List.of(PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING))));
    }
}
