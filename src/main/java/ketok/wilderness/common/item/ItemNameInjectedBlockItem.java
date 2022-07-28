package ketok.wilderness.common.item;

import com.teamabnormals.blueprint.common.item.InjectedBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ItemNameInjectedBlockItem extends InjectedBlockItem {
    public ItemNameInjectedBlockItem(Item followItem, Block block, Properties builder) {
        super(followItem, block, builder);
    }

    @Override
    public String getDescriptionId() {
        return this.getOrCreateDescriptionId();
    }
}
