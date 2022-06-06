package ketok.wilderness.data.server.tags;

import ketok.wilderness.Wilderness;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class WdItemTagsProvider extends ItemTagsProvider {
    public WdItemTagsProvider(DataGenerator pGenerator, BlockTagsProvider pBlockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, pBlockTagsProvider, Wilderness.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        copy(BlockTags.OAK_LOGS, ItemTags.OAK_LOGS);
    }
}
