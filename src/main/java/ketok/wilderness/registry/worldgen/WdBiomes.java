package ketok.wilderness.registry.worldgen;

import com.teamabnormals.blueprint.core.util.registry.BiomeSubRegistryHelper;
import ketok.wilderness.Wilderness;
import ketok.wilderness.util.WdBiomeBuilder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.minecraftforge.fml.common.Mod;

import static ketok.wilderness.registry.worldgen.WdPlacedFeatures.*;
import static net.minecraft.data.worldgen.placement.VegetationPlacements.*;
import static net.minecraft.data.worldgen.BiomeDefaultFeatures.*;
import static net.minecraft.world.level.levelgen.GenerationStep.Decoration.*;
import static net.minecraft.world.entity.EntityType.*;
import static net.minecraft.world.entity.MobCategory.*;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@Mod.EventBusSubscriber(modid = Wilderness.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WdBiomes {
    public static final BiomeSubRegistryHelper HELPER = Wilderness.REGISTRY_HELPER.getBiomeSubHelper();

    public static final BiomeSubRegistryHelper.KeyedBiome OLD_GROWTH_FOREST = HELPER.createBiome("old_growth_forest", WdBiomes::createOldGrowthForestBiome);
    public static final BiomeSubRegistryHelper.KeyedBiome MIXED_FOREST = HELPER.createBiome("mixed_forest", WdBiomes::createMixedForestBiome);

    private static Biome createOldGrowthForestBiome() {
        BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(generation);

        addForestFlowers(generation);
        addDefaultOres(generation);
        addDefaultSoftDisks(generation);
        addDefaultFlowers(generation);
        addForestGrass(generation);
        generation.addFeature(VEGETAL_DECORATION, PATCH_TALL_GRASS);
        generation.addFeature(VEGETAL_DECORATION, PATCH_DEAD_BUSH);
        addDefaultExtraVegetation(generation);
        generation.addFeature(VEGETAL_DECORATION, TREES_OLD_GROWTH_FOREST.getHolder().get());
        generation.addFeature(VEGETAL_DECORATION, BROWN_RED_MUSHROOM_PATCH.getHolder().get());
        generation.addFeature(LOCAL_MODIFICATIONS, FOREST_ROCK_RARE.getHolder().get());

        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        farmAnimals(spawns);
        commonSpawns(spawns);
        spawns.addSpawn(CREATURE, new SpawnerData(WOLF, 8, 4, 4));
        spawns.addSpawn(CREATURE, new SpawnerData(RABBIT, 2, 2, 3));

        return new WdBiomeBuilder()
                .generationAndSpawns(generation, spawns)
                .category(BiomeCategory.FOREST)
                .temperatureAndDownfall(0.7F, 0.8F)
                .build();
    }

    private static Biome createMixedForestBiome() {
        BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(generation);

        addDefaultOres(generation);
        addDefaultSoftDisks(generation);
        addDefaultFlowers(generation);
        addForestGrass(generation);
        generation.addFeature(VEGETAL_DECORATION, PATCH_DEAD_BUSH_2);
        addDefaultMushrooms(generation);
        addDefaultExtraVegetation(generation);
        generation.addFeature(VEGETAL_DECORATION, TREES_MIXED_FOREST.getHolder().get());
        generation.addFeature(LOCAL_MODIFICATIONS, FOREST_ROCK_RARE.getHolder().get());
        generation.addFeature(LOCAL_MODIFICATIONS, PATCH_COARSE_DIRT.getHolder().get());
        generation.addFeature(LOCAL_MODIFICATIONS, PATCH_PODZOL.getHolder().get());

        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        farmAnimals(spawns);
        commonSpawns(spawns);
        spawns.addSpawn(CREATURE, new SpawnerData(WOLF, 8, 4, 4));
        spawns.addSpawn(CREATURE, new SpawnerData(RABBIT, 2, 2, 3));
        spawns.addSpawn(CREATURE, new SpawnerData(FOX, 8, 2, 4));

        return new WdBiomeBuilder()
                .generationAndSpawns(generation, spawns)
                .category(BiomeCategory.FOREST)
                .temperatureAndDownfall(0.5F, 0.8F)
                .build();
    }

    private static void globalOverworldGeneration(BiomeGenerationSettings.Builder generation) {
        addDefaultCarversAndLakes(generation);
        addDefaultCrystalFormations(generation);
        addDefaultMonsterRoom(generation);
        addDefaultUndergroundVariety(generation);
        addDefaultSprings(generation);
        addSurfaceFreezing(generation);
    }
}
