package ketok.wilderness.registry;

import com.teamabnormals.blueprint.common.block.wood.LogBlock;
import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import ketok.wilderness.Wilderness;
import ketok.wilderness.common.block.MossyLogBlock;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Wilderness.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WdBlocks {
    public static final BlockSubRegistryHelper HELPER = Wilderness.REGISTRY_HELPER.getBlockSubHelper();

    //TODO: Mossy oak wood
    public static final RegistryObject<LogBlock> MOSSY_OAK_LOG = HELPER.createBlock("mossy_oak_log", () -> new MossyLogBlock(() -> Blocks.OAK_LOG, BlockBehaviour.Properties.copy(Blocks.OAK_LOG)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            DataUtil.registerFlammable(MOSSY_OAK_LOG.get(), 5, 5);
        });
    }
}
