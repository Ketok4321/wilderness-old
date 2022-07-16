package ketok.wilderness.data;

import com.teamabnormals.blueprint.core.util.registry.AbstractSubRegistryHelper;
import ketok.wilderness.registry.WdBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.RegistryObject;

import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

public class WdDataGenUtil {
    public static <T extends IForgeRegistryEntry<T>> Stream<T> entries(AbstractSubRegistryHelper<T> helper) {
        return helper.getDeferredRegister().getEntries().stream().map(RegistryObject::get);
    }

    public static Stream<Block> wdBlockItems() {
        return entries(WdBlocks.HELPER).filter(block -> block.asItem().getRegistryName().equals(block.getRegistryName()));
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
