package ketok.wilderness.data.client;

import ketok.wilderness.Wilderness;
import ketok.wilderness.registry.WdBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class WdBlockStateProvider extends BlockStateProvider {
    public WdBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Wilderness.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        logBlock(WdBlocks.MOSSY_OAK_LOG.get());
        axisBlock(WdBlocks.MOSSY_OAK_WOOD.get(), blockTexture(WdBlocks.MOSSY_OAK_LOG.get()), blockTexture(WdBlocks.MOSSY_OAK_LOG.get()));
    }
}
