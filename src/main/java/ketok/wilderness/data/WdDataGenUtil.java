package ketok.wilderness.data;

import ketok.wilderness.registry.WdBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.minecraftforge.registries.RegistryObject;

import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

public class WdDataGenUtil {
    public static Stream<Block> wdBlocks() {
        return WdBlocks.HELPER.getDeferredRegister().getEntries().stream().map(RegistryObject::get);
    }

    public static Stream<Block> wdBlockItems() {
        return wdBlocks().filter(block -> block.asItem() != Items.AIR);
    }

    public static String name(ForgeRegistryEntry<?> regEntry) {
        return requireNonNull(regEntry.getRegistryName()).getPath();
    }

    public static ResourceLocation prefix(String prefix, ResourceLocation rl) {
        return new ResourceLocation(rl.getNamespace(), prefix + rl.getPath());
    }

    public static ResourceLocation suffix(ResourceLocation rl, String suffix) {
        return new ResourceLocation(rl.getNamespace(), rl.getPath() + suffix);
    }
}
