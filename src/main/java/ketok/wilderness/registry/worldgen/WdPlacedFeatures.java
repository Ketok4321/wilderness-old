package ketok.wilderness.registry.worldgen;

import ketok.wilderness.Wilderness;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.MiscOverworldFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WdPlacedFeatures {
    public static DeferredRegister<PlacedFeature> HELPER = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, Wilderness.MOD_ID);

    public static final RegistryObject<PlacedFeature> FALLEN_OAK = surfaceFeature("fallen_oak", WdConfiguredFeatures.FALLEN_OAK.getHolder().get(), RarityFilter.onAverageOnceEvery(6));
    public static final RegistryObject<PlacedFeature> FALLEN_BIRCH = surfaceFeature("fallen_birch", WdConfiguredFeatures.FALLEN_BIRCH.getHolder().get(), RarityFilter.onAverageOnceEvery(6));
    public static final RegistryObject<PlacedFeature> FALLEN_SPRUCE = surfaceFeature("fallen_spruce", WdConfiguredFeatures.FALLEN_SPRUCE.getHolder().get(), RarityFilter.onAverageOnceEvery(6));
    public static final RegistryObject<PlacedFeature> FALLEN_JUNGLE_TREE = surfaceFeature("fallen_jungle_tree", WdConfiguredFeatures.FALLEN_JUNGLE_TREE.getHolder().get(), RarityFilter.onAverageOnceEvery(6));

    public static final RegistryObject<PlacedFeature> TREES_OLD_GROWTH_FOREST = surfaceFeature("trees_old_growth_forest", WdConfiguredFeatures.TREES_OLD_GROWTH_FOREST.getHolder().get(), PlacementUtils.countExtra(8, 0.25F, 2));
    public static final RegistryObject<PlacedFeature> MUSHROOM_OLD_GROWTH_FOREST = surfaceFeature("mushroom_old_growth_forest", WdConfiguredFeatures.BROWN_RED_MUSHROOM_PATCH.getHolder().get(), CountPlacement.of(3), RarityFilter.onAverageOnceEvery(2));
    public static final RegistryObject<PlacedFeature> ROCK_OLD_GROWTH_FOREST = surfaceFeature("rock_old_growth_forest", MiscOverworldFeatures.FOREST_ROCK, RarityFilter.onAverageOnceEvery(4));

    private static RegistryObject<PlacedFeature> surfaceFeature(String name, Holder<? extends ConfiguredFeature<?, ?>> feature, PlacementModifier... placementModifiers) {
        ArrayList<PlacementModifier> modifiers = new ArrayList<>();
        Collections.addAll(modifiers, placementModifiers);
        modifiers.addAll(List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome(), VegetationPlacements.TREE_THRESHOLD));
        return HELPER.register(name, () -> new PlacedFeature(Holder.hackyErase(feature), modifiers));
    }
}
