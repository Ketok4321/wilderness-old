package ketok.wilderness.data.server.tags;

import ketok.wilderness.Wilderness;
import ketok.wilderness.registry.WdItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class WdItemTagsProvider extends ItemTagsProvider {
    public WdItemTagsProvider(DataGenerator generator, BlockTagsProvider blockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, blockTagsProvider, Wilderness.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        copy(BlockTags.OAK_LOGS, ItemTags.OAK_LOGS);

        tag(ItemTags.FOX_FOOD).add(WdItems.BLACKBERRIES.get());
    }
}
