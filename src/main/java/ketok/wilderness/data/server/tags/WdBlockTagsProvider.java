package ketok.wilderness.data.server.tags;

import ketok.wilderness.Wilderness;
import ketok.wilderness.registry.WdBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class WdBlockTagsProvider extends BlockTagsProvider {
    public WdBlockTagsProvider(DataGenerator pGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, Wilderness.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(BlockTags.OAK_LOGS).add(WdBlocks.MOSSY_OAK_LOG.get(), WdBlocks.MOSSY_OAK_WOOD.get());
    }
}
