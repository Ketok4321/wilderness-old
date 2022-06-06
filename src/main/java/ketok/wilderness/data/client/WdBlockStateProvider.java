package ketok.wilderness.data.client;

import ketok.wilderness.Wilderness;
import ketok.wilderness.registry.WdBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import static ketok.wilderness.data.WdDataGenUtil.*;

public class WdBlockStateProvider extends BlockStateProvider {
    public WdBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Wilderness.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        axisBlock(WdBlocks.MOSSY_OAK_LOG.get(), blockTexture(WdBlocks.MOSSY_OAK_LOG.get()), suffix(blockTexture(Blocks.OAK_LOG), "_top"));
    }
}
