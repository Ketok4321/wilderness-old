package ketok.wilderness.data.server.tags;

import ketok.wilderness.Wilderness;
import ketok.wilderness.registry.WdBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class WdBlockTagsProvider extends BlockTagsProvider {
    public WdBlockTagsProvider(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, Wilderness.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(BlockTags.OAK_LOGS).add(WdBlocks.MOSSY_OAK_LOG.get(), WdBlocks.MOSSY_OAK_WOOD.get());

        tag(BlockTags.BEE_GROWABLES).add(WdBlocks.BLACKBERRY_BUSH.get());
        tag(BlockTags.FALL_DAMAGE_RESETTING).add(WdBlocks.BLACKBERRY_BUSH.get());
    }
}
