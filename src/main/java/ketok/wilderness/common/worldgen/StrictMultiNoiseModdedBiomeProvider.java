package ketok.wilderness.common.worldgen;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamabnormals.blueprint.core.other.tags.BlueprintBiomeTags;
import com.teamabnormals.blueprint.core.util.BiomeUtil;
import com.teamabnormals.blueprint.core.util.TagUtil;
import ketok.wilderness.Wilderness;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record StrictMultiNoiseModdedBiomeProvider (List<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> biomes) implements BiomeUtil.ModdedBiomeProvider {
    public static void register() {
        BiomeUtil.registerBiomeProvider(Wilderness.REGISTRY_HELPER.prefix("strict_multi_noise"), StrictMultiNoiseModdedBiomeProvider.CODEC);
    }

    public static final Codec<Climate.ParameterPoint> STRICT_PARAMETER_CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(
                    Climate.Parameter.CODEC.fieldOf("temperature").forGetter(Climate.ParameterPoint::temperature),
                    Climate.Parameter.CODEC.fieldOf("humidity").forGetter(Climate.ParameterPoint::temperature),
                    Climate.Parameter.CODEC.fieldOf("continentalness").forGetter(Climate.ParameterPoint::temperature),
                    Climate.Parameter.CODEC.fieldOf("erosion").forGetter(Climate.ParameterPoint::temperature),
                    Climate.Parameter.CODEC.fieldOf("weirdness").forGetter(Climate.ParameterPoint::temperature)
            ).apply(instance, (temperature, humidity, continentalness, erosion, weirdness) -> new Climate.ParameterPoint(temperature, humidity, continentalness, erosion, Climate.Parameter.point(0), weirdness, 0))
    );

    public static final Codec<StrictMultiNoiseModdedBiomeProvider> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(
                    ExtraCodecs.nonEmptyList(RecordCodecBuilder.<Pair<Climate.ParameterPoint, ResourceKey<Biome>>>create((pairInstance) ->
                            pairInstance.group(STRICT_PARAMETER_CODEC.fieldOf("parameters").forGetter(Pair::getFirst), ResourceKey.codec(Registry.BIOME_REGISTRY).fieldOf("biome").forGetter(Pair::getSecond)).apply(pairInstance, Pair::of)
                    ).listOf()).fieldOf("biomes").forGetter(sampler -> sampler.biomes)
            ).apply(instance, StrictMultiNoiseModdedBiomeProvider::new)
    );

    public Codec<? extends BiomeUtil.ModdedBiomeProvider> codec() {
        return CODEC;
    }

    @Override
    @SuppressWarnings("deprecation")
    public Holder<Biome> getNoiseBiome(int x, int y, int z, Climate.Sampler sampler, BiomeSource original, Registry<Biome> registry) {
        Holder<Biome> originalBiome = original.getNoiseBiome(x, y, z, sampler);
        if(Biome.getBiomeCategory(originalBiome) == Biome.BiomeCategory.UNDERGROUND || TagUtil.isTagged(originalBiome.value(), BlueprintBiomeTags.IS_UNDERGROUND)) return originalBiome;
        if(Biome.getBiomeCategory(originalBiome) == Biome.BiomeCategory.RIVER || TagUtil.isTagged(originalBiome.value(), BiomeTags.IS_RIVER)) return originalBiome;

        Climate.TargetPoint point = sampler.sample(x, 64, z);
        for(Pair<Climate.ParameterPoint, ResourceKey<Biome>> pair : this.biomes) {
            Climate.ParameterPoint parameter = pair.getFirst();

            if(
                    parameter.temperature().distance(point.temperature()) == 0 &&
                            parameter.humidity().distance(point.humidity()) == 0 &&
                            parameter.continentalness().distance(point.continentalness()) == 0 &&
                            parameter.erosion().distance(point.erosion()) == 0 &&
                            //parameter.depth().distance(point.depth()) == 0 &&
                            parameter.weirdness().distance(point.weirdness()) == 0
            ) {
                return registry.getHolderOrThrow(pair.getSecond());
            }
        }
        return originalBiome;
    }

    @Override
    public Set<Holder<Biome>> getAdditionalPossibleBiomes(Registry<Biome> registry) {
        return this.biomes.stream().map(pair -> registry.getHolderOrThrow(pair.getSecond())).collect(Collectors.toSet());
    }
}