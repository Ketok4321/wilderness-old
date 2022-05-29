package ketok.wilderness.data.server.tags;

import com.teamabnormals.blueprint.core.other.tags.BlueprintBiomeTags;
import com.teamabnormals.blueprint.core.util.registry.BiomeSubRegistryHelper;
import ketok.wilderness.Wilderness;
import ketok.wilderness.registry.worldgen.WdBiomes;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class WdBiomeTagsProvider extends BiomeTagsProvider {
    public WdBiomeTagsProvider(DataGenerator dataGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, Wilderness.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(WdBiomes.OLD_GROWTH_FOREST, BlueprintBiomeTags.IS_OVERWORLD, BiomeTags.IS_FOREST, BiomeTags.HAS_STRONGHOLD);
        tag(WdBiomes.MIXED_FOREST, BlueprintBiomeTags.IS_OVERWORLD, BiomeTags.IS_FOREST, BiomeTags.IS_TAIGA, BiomeTags.HAS_STRONGHOLD);
    }

    @SafeVarargs
    private void tag(BiomeSubRegistryHelper.KeyedBiome biome, TagKey<Biome>... tags)
    {
        for(TagKey<Biome> key : tags)
        {
            tag(key).add(biome.getKey());
        }
    }
}
