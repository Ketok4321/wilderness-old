package ketok.wilderness.data.server;

import com.mojang.datafixers.util.Pair;
import com.teamabnormals.blueprint.common.world.modification.ModdedBiomeSliceProvider;
import com.teamabnormals.blueprint.core.registry.BlueprintBiomes;
import com.teamabnormals.blueprint.core.util.BiomeUtil;
import com.teamabnormals.blueprint.core.util.registry.BiomeSubRegistryHelper.KeyedBiome;
import ketok.wilderness.Wilderness;
import ketok.wilderness.registry.worldgen.WdBiomes;
import ketok.wilderness.util.ParameterPointBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class WdModdedBiomeSlicesProvider extends ModdedBiomeSliceProvider {
    public WdModdedBiomeSlicesProvider(DataGenerator dataGenerator) {
        super(dataGenerator, Wilderness.MOD_ID);
    }

    @Override
    protected void registerSlices() {
        OverlayModdedBiomeProviderBuilder overworldSlice = new OverlayModdedBiomeProviderBuilder();

        overworldSlice.overlay(BiomeTags.IS_FOREST)
                .withBiome(WdBiomes.MIXED_FOREST, at -> at.temperature(-1.0F))
                .withBiome(WdBiomes.OLD_GROWTH_FOREST, at -> at.temperature(1.0F))
                .withBiome(BlueprintBiomes.ORIGINAL_SOURCE_MARKER,
                        at -> at.weirdness(1.0F),
                        at -> at.erosion(-1.0F, -OverworldBiomeBuilder.PEAK_START).offset(0.2F)
                );

        registerSlice("overworld_slice", 5, overworldSlice.build(), new ResourceLocation("minecraft:overworld"));
    }

    protected static class OverlayModdedBiomeProviderBuilder {
        private final List<OverlayBuilder> biomes = new ArrayList<>();
        private final Registry<Biome> biomeReg = RegistryAccess.BUILTIN.get().registryOrThrow(Registry.BIOME_REGISTRY);

        @SafeVarargs
        public final OverlayBuilder overlay(Holder<Biome>... matchesBiomes) {
            return overlay(HolderSet.direct(matchesBiomes));
        }

        public OverlayBuilder overlay(TagKey<Biome> matchesBiomes) {
            return overlay(biomeReg.getOrCreateTag(matchesBiomes));
        }

        public OverlayBuilder overlay(HolderSet<Biome> matchesBiomes) {
            OverlayBuilder builder = new OverlayBuilder(matchesBiomes, biomeReg);
            biomes.add(builder);
            return builder;
        }

        public static class OverlayBuilder {
            private final HolderSet<Biome> matchesBiomes;
            private final List<Pair<Climate.ParameterPoint, Holder<Biome>>> parameterList = new ArrayList<>();
            private final Registry<Biome> biomeReg;

            public OverlayBuilder(HolderSet<Biome> matchesBiomes, Registry<Biome> biomeReg) {
                this.matchesBiomes = matchesBiomes;
                this.biomeReg = biomeReg;
            }

            @SafeVarargs
            public final OverlayBuilder withBiome(KeyedBiome biome, Consumer<ParameterPointBuilder>... at) {
                return withBiome(biome.getKey(), at);
            }

            @SafeVarargs
            public final OverlayBuilder withBiome(ResourceKey<Biome> biome, Consumer<ParameterPointBuilder>... at) {
                for(Consumer<ParameterPointBuilder> parameterPointBuilder : at) {
                    ParameterPointBuilder parameterPoint = new ParameterPointBuilder();
                    parameterPointBuilder.accept(parameterPoint);

                    parameterList.add(Pair.of(parameterPoint.build(), biomeReg.getHolderOrThrow(biome)));
                }
                return this;
            }

            public Pair<HolderSet<Biome>, BiomeSource> build() {
                return Pair.of(matchesBiomes, new MultiNoiseBiomeSource(new Climate.ParameterList<>(parameterList)));
            }
        }

        public BiomeUtil.OverlayModdedBiomeProvider build() {
            return new BiomeUtil.OverlayModdedBiomeProvider(biomes.stream().map(OverlayBuilder::build).collect(Collectors.toList()));
        }
    }
}