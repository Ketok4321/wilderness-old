package ketok.wilderness.data.client;

import ketok.wilderness.Wilderness;
import ketok.wilderness.registry.WdItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
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

        generated(WdItems.BLACKBERRIES.get());
    }

    private void generated(Item item) {
        withExistingParent(name(item), "item/generated").texture("layer0", new ResourceLocation(this.modid, "item/" + name(item)));
    }
}
