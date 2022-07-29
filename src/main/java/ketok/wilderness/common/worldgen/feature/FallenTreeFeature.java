package ketok.wilderness.common.worldgen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.material.Material;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class FallenTreeFeature extends Feature<FallenTreeFeature.Config> {
    public record Config(BlockStateProvider block, IntProvider length, List<TreeDecorator> decorators) implements FeatureConfiguration {
        public static final Codec<Config> CODEC = RecordCodecBuilder.create(builder ->
                builder
                        .group(
                                BlockStateProvider.CODEC.fieldOf("block").forGetter(config -> config.block),
                                IntProvider.POSITIVE_CODEC.fieldOf("length").forGetter(config -> config.length),
                                Codec.list(TreeDecorator.CODEC).fieldOf("decorators").forGetter(config -> config.decorators)
                        )
                        .apply(builder, Config::new)
        );
    }

    public FallenTreeFeature() {
        super(Config.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<Config> context) {
        WorldGenLevel level = context.level();
        BlockPos startPos = context.origin();
        Config config = context.config();
        Random random = context.random();

        Direction direction = random.nextBoolean() ?
                (random.nextBoolean() ? Direction.EAST : Direction.WEST):
                (random.nextBoolean() ? Direction.NORTH : Direction.SOUTH);

        int length = config.length.sample(random);

        boolean generateStump = random.nextFloat() < 0.75F;

        if(!canPlace(level, startPos, length, direction, generateStump)) {
            direction = getValidDirection(level, startPos, length, generateStump);
            if(direction == null) return false;
        }

        List<BlockPos> placedLogsList = new ArrayList<>(length + (generateStump ? 1 : 0));

        if(generateStump) {
            level.setBlock(startPos, config.block.getState(random, startPos), 2);
            placedLogsList.add(startPos);

            startPos = startPos.relative(direction, 2);
        }

        for(int i = 0; i < length; i++) {
            BlockPos pos = startPos.relative(direction, i);

            BlockState block = config.block.getState(random, pos);

            if(block.getBlock() instanceof RotatedPillarBlock) {
                block = block.setValue(RotatedPillarBlock.AXIS, direction.getAxis());
            }

            level.setBlock(pos, block, 2);
            placedLogsList.add(pos);
        }

        placedLogsList.sort(Comparator.comparingInt(Vec3i::getY));
        config.decorators.forEach((decorator) -> {
            decorator.place(level, (pos, block) -> level.setBlock(pos, block, 19), random, placedLogsList, List.of());
        });

        return true;
    }

    private boolean canPlace(WorldGenLevel level, BlockPos startPos, int length, Direction direction, boolean generateStump) {
        // Is there place for a stump?
        if(generateStump) {
            if(!canPlaceOn(level, startPos)) return false;

            startPos = startPos.relative(direction, 2);
        }

        // Is there place for a log?
        for(int i = 0; i < length; i++) {
            BlockPos pos = startPos.relative(direction, i);

            if(!canPlaceOn(level, pos)) return false;
        }

        return true;
    }

    private boolean canPlaceOn(WorldGenLevel level, BlockPos pos) {
        return isAirOrReplaceablePlant(level, pos) && isGrassOrDirt(level, pos.below()) && isAir(level, pos.above());
    }

    private Direction getValidDirection(WorldGenLevel level, BlockPos startPos, int length, boolean generateStump) {
        for (Direction dir : List.of(Direction.EAST, Direction.WEST, Direction.NORTH, Direction.SOUTH)) {
            if (canPlace(level, startPos, length, dir, generateStump)) {
                return dir;
            }
        }

        return null;
    }

    private boolean isAirOrReplaceablePlant(LevelSimulatedReader level, BlockPos pos) {
        return isAir(level, pos) || level.isStateAtPosition(pos, (p_160551_) -> {
            Material material = p_160551_.getMaterial();
            return material == Material.REPLACEABLE_PLANT;
        });
    }
}
