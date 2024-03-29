package ketok.wilderness.data.client;

import ketok.wilderness.Wilderness;
import ketok.wilderness.common.block.BlackberryBushBlock;
import ketok.wilderness.registry.WdBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;

import static ketok.wilderness.data.WdDataGenUtil.*;

public class WdBlockStateProvider extends BlockStateProvider {
    public WdBlockStateProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Wilderness.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        logBlock(WdBlocks.MOSSY_OAK_LOG.get());
        axisBlock(WdBlocks.MOSSY_OAK_WOOD.get(), blockTexture(WdBlocks.MOSSY_OAK_LOG.get()), blockTexture(WdBlocks.MOSSY_OAK_LOG.get()));
        berryBush(WdBlocks.BLACKBERRY_BUSH.get());
    }

    private void berryBush(SweetBerryBushBlock block) {
        getVariantBuilder(block).forAllStatesExcept(state -> {
            String suffix = "_" + state.getValue(SweetBerryBushBlock.AGE);
            return ConfiguredModel.builder().modelFile(models().cross(name(block) + suffix, suffix(blockTexture(block), suffix))).build();
        }, BlackberryBushBlock.CAN_GROW);
    }
}
