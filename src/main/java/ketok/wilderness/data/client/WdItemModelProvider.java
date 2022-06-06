package ketok.wilderness.data.client;

import ketok.wilderness.Wilderness;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import static ketok.wilderness.data.WdDataGenUtil.*;

public class WdItemModelProvider extends ItemModelProvider {
    public WdItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Wilderness.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        wdBlockItems().forEach(block -> withExistingParent(name(block), prefix("block/", block.getRegistryName())));
    }
}
