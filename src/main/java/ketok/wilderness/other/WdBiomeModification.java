package ketok.wilderness.other;

import com.teamabnormals.blueprint.core.util.DataUtil;
import ketok.wilderness.WdConfig;
import ketok.wilderness.Wilderness;
import ketok.wilderness.registry.worldgen.WdPlacedFeatures;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.MobSpawnSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.minecraft.world.level.biome.Biomes.*;
import static ketok.wilderness.registry.worldgen.WdBiomes.*;
import static net.minecraft.world.level.levelgen.GenerationStep.Decoration.*;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@Mod.EventBusSubscriber(modid = Wilderness.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class WdBiomeModification {
    @SubscribeEvent
    public static void biomeModification(BiomeLoadingEvent event) {
        ResourceLocation biome = event.getName();
        BiomeGenerationSettingsBuilder generation = event.getGeneration();
        MobSpawnSettingsBuilder spawns = event.getSpawns();

        if(WdConfig.COMMON.generateFallenTrees.get()) addFallenTrees(biome, generation);

        if(WdConfig.COMMON.removeLavaLakesFromForests.get()) removeForestLavaLakes(event, generation);
    }

    private static void removeForestLavaLakes(BiomeLoadingEvent event, BiomeGenerationSettingsBuilder generation) {
        if(event.getCategory() == BiomeCategory.FOREST || event.getCategory() == BiomeCategory.TAIGA) {
            generation.getFeatures(LAKES).removeIf((feature) -> feature.is(MiscOverworldPlacements.LAKE_LAVA_SURFACE.unwrapKey().get()));
        }
    }

    private static void addFallenTrees(ResourceLocation biome, BiomeGenerationSettingsBuilder generation) {
        if(DataUtil.matchesKeys(biome, FOREST, OLD_GROWTH_FOREST.getKey(), MIXED_FOREST.getKey())) {
            generation.getFeatures(VEGETAL_DECORATION).add(WdPlacedFeatures.FALLEN_OAK.getHolder().get());
        }
        if(DataUtil.matchesKeys(biome, BIRCH_FOREST, OLD_GROWTH_BIRCH_FOREST)) {
            generation.getFeatures(VEGETAL_DECORATION).add(WdPlacedFeatures.FALLEN_BIRCH.getHolder().get());
        }
        if(DataUtil.matchesKeys(biome, TAIGA, OLD_GROWTH_PINE_TAIGA, OLD_GROWTH_SPRUCE_TAIGA, SNOWY_TAIGA, MIXED_FOREST.getKey())) {
            generation.getFeatures(VEGETAL_DECORATION).add(WdPlacedFeatures.FALLEN_SPRUCE.getHolder().get());
        }
        if(DataUtil.matchesKeys(biome, SPARSE_JUNGLE, JUNGLE)) {
            generation.getFeatures(VEGETAL_DECORATION).add(WdPlacedFeatures.FALLEN_JUNGLE_TREE.getHolder().get());
        }
    }
}
