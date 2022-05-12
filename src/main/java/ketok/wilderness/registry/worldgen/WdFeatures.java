package ketok.wilderness.registry.worldgen;

import ketok.wilderness.Wilderness;
import ketok.wilderness.common.worldgen.feature.FallenTreeFeature;
import ketok.wilderness.common.worldgen.feature.treedecorators.BlockOnFallenLogDecorator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class WdFeatures {
    public static DeferredRegister<Feature<?>> HELPER = DeferredRegister.create(ForgeRegistries.FEATURES, Wilderness.MOD_ID);

    public static final RegistryObject<Feature<FallenTreeFeature.Config>> FALLEN_TREE = HELPER.register("fallen_tree", FallenTreeFeature::new);

    public static class TreeDecorators {
        public static DeferredRegister<TreeDecoratorType<?>> HELPER = DeferredRegister.create(ForgeRegistries.TREE_DECORATOR_TYPES, Wilderness.MOD_ID);

        public static final RegistryObject<TreeDecoratorType<BlockOnFallenLogDecorator>> BLOCK_ON_FALLEN_LOG = HELPER.register("block_on_fallen_log", () -> new TreeDecoratorType<>(BlockOnFallenLogDecorator.CODEC));
    }
}
