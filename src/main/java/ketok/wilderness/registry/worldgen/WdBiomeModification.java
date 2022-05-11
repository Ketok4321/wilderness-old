package ketok.wilderness.registry.worldgen;

import com.teamabnormals.blueprint.core.util.DataUtil;
import ketok.wilderness.Wilderness;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.MobSpawnSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.minecraft.world.level.levelgen.GenerationStep.Decoration.VEGETAL_DECORATION;

@Mod.EventBusSubscriber(modid = Wilderness.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class WdBiomeModification {
    @SubscribeEvent
    public static void biomeModification(BiomeLoadingEvent event) {
        ResourceLocation biome = event.getName();
        BiomeGenerationSettingsBuilder generation = event.getGeneration();
        MobSpawnSettingsBuilder spawns = event.getSpawns();

        if(DataUtil.matchesKeys(biome, Biomes.FOREST)) {
            generation.getFeatures(VEGETAL_DECORATION).add(WdPlacedFeatures.FALLEN_OAK.getHolder().get());
        }
    }
}
