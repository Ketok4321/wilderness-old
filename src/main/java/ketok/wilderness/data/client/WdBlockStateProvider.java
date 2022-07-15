package ketok.wilderness.data.client;

import ketok.wilderness.Wilderness;
import ketok.wilderness.registry.WdBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;

import static ketok.wilderness.data.WdDataGenUtil.name;
import static ketok.wilderness.data.WdDataGenUtil.suffix;

public class WdBlockStateProvider extends BlockStateProvider {
    public WdBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Wilderness.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        logBlock(WdBlocks.MOSSY_OAK_LOG.get());
        axisBlock(WdBlocks.MOSSY_OAK_WOOD.get(), blockTexture(WdBlocks.MOSSY_OAK_LOG.get()), blockTexture(WdBlocks.MOSSY_OAK_LOG.get()));
        berryBush(WdBlocks.BLACKBERRY_BUSH.get());
    }

    private void berryBush(SweetBerryBushBlock block) {
        getVariantBuilder(block).forAllStates(state -> {
            String suffix = "_" + state.getValue(SweetBerryBushBlock.AGE);
            return ConfiguredModel.builder().modelFile(models().cross(name(block) + suffix, suffix(blockTexture(block), suffix))).build();
        });
    }
}
