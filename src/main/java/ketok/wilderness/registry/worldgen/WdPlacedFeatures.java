package ketok.wilderness.registry.worldgen;

import ketok.wilderness.Wilderness;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
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

    private static RegistryObject<PlacedFeature> surfaceFeature(String name, Holder<ConfiguredFeature<?, ?>> feature, PlacementModifier... placementModifiers) {
        ArrayList<PlacementModifier> modifiers = new ArrayList<>();
        Collections.addAll(modifiers, placementModifiers);
        modifiers.addAll(List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome()));
        return HELPER.register(name, () -> new PlacedFeature(feature, modifiers));
    }
}
