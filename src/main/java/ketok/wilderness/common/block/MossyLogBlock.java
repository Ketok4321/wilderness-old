package ketok.wilderness.common.block;

import com.teamabnormals.blueprint.common.block.wood.LogBlock;
import com.teamabnormals.blueprint.core.util.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;
import java.util.function.Supplier;

public class MossyLogBlock extends LogBlock implements BonemealableBlock {
    public final Supplier<Block> logBlock;

    public MossyLogBlock(Supplier<Block> logBlock, Properties properties) {
        super(logBlock, properties);
        this.logBlock = logBlock;
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter getter, BlockPos pos, BlockState state, boolean p_50900_) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel world, Random random, BlockPos pos, BlockState state) {
        Direction.Axis axis = state.getValue(AXIS);
        Direction direction = Direction.fromAxisAndDirection(axis, random.nextBoolean() ? Direction.AxisDirection.POSITIVE : Direction.AxisDirection.NEGATIVE);

        BlockPos growPos = pos.relative(direction);

        BlockState growBlock = world.getBlockState(growPos);

        if(growBlock.getBlock() == logBlock.get()){
            world.setBlock(growPos, BlockUtil.transferAllBlockStates(growBlock, this.defaultBlockState()), 2);
        }
        else if (growBlock.getBlock() == this){
            performBonemeal(world, random, growPos, growBlock);
        }
    }
}