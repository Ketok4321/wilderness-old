package ketok.wilderness.common.worldgen.feature.treedecorators;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import ketok.wilderness.registry.worldgen.WdFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

public class BlockOnFallenLogDecorator extends TreeDecorator {
    public static final Codec<BlockOnFallenLogDecorator> CODEC = RecordCodecBuilder.create(builder ->
            builder
                    .group(
                            BlockState.CODEC.fieldOf("block").forGetter(config -> config.block),
                            Codec.floatRange(0.0F, 1.0F).fieldOf("chance").forGetter(config -> config.chance)
                    )
                    .apply(builder, BlockOnFallenLogDecorator::new)
    );

    public final BlockState block;
    public final float chance;

    public BlockOnFallenLogDecorator(Block block, float chance) {
        this(block.defaultBlockState(), chance);
    }

    public BlockOnFallenLogDecorator(BlockState block, float chance) {
        this.block = block;
        this.chance = chance;
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return WdFeatures.TreeDecorators.BLOCK_ON_FALLEN_LOG.get();
    }

    @Override
    public void place(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, Random random, List<BlockPos> logPositions, List<BlockPos> leafPositions) {
        for (BlockPos pos : logPositions) {
            if(!level.isStateAtPosition(pos.above(), (state) -> state.isAir())) continue;
            if(random.nextFloat() < chance) blockSetter.accept(pos.above(), block);
        }
    }
}
