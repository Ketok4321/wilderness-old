package ketok.wilderness.registry.worldgen;

import ketok.wilderness.Wilderness;
import ketok.wilderness.common.worldgen.feature.FallenTreeFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class WdFeatures {
    public static DeferredRegister<Feature<?>> HELPER = DeferredRegister.create(ForgeRegistries.FEATURES, Wilderness.MOD_ID);

    public static final RegistryObject<Feature<FallenTreeFeature.Config>> FALLEN_TREE = HELPER.register("fallen_tree", FallenTreeFeature::new);
}
