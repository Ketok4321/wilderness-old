package ketok.wilderness.registry.worldgen;

import com.teamabnormals.blueprint.core.util.registry.BiomeSubRegistryHelper;
import ketok.wilderness.Wilderness;
import ketok.wilderness.util.WdBiomeBuilder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.fml.common.Mod;

import static ketok.wilderness.registry.worldgen.WdPlacedFeatures.*;
import static net.minecraft.data.worldgen.BiomeDefaultFeatures.*;
import static net.minecraft.world.level.levelgen.GenerationStep.Decoration.*;

@Mod.EventBusSubscriber(modid = Wilderness.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WdBiomes {
    public static final BiomeSubRegistryHelper HELPER = Wilderness.REGISTRY_HELPER.getBiomeSubHelper();

    public static final BiomeSubRegistryHelper.KeyedBiome OLD_GROWTH_FOREST = HELPER.createBiome("old_growth_forest", WdBiomes::createOldGrowthForestBiome);

    private static Biome createOldGrowthForestBiome() {
        BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(generation);

        addForestFlowers(generation);
        addDefaultOres(generation);
        addDefaultSoftDisks(generation);
        addDefaultFlowers(generation);
        addForestGrass(generation);
        addPlainGrass(generation);
        addDefaultExtraVegetation(generation);
        generation.addFeature(VEGETAL_DECORATION, TREES_OLD_GROWTH_FOREST.getHolder().get());
        generation.addFeature(VEGETAL_DECORATION, MUSHROOM_OLD_GROWTH_FOREST.getHolder().get());
        generation.addFeature(LOCAL_MODIFICATIONS, FOREST_ROCK.getHolder().get());

        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        farmAnimals(spawns);
        commonSpawns(spawns);
        spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 8, 4, 4));
        spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 2, 2, 3));
        spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 8, 2, 4));

        //TODO: Override water or foliage color?
        return new WdBiomeBuilder()
                .generationAndSpawns(generation, spawns)
                .category(BiomeCategory.FOREST)
                .temperatureAndDownfall(0.7F, 0.8F)
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
