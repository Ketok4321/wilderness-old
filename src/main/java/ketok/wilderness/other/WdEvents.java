package ketok.wilderness.other;

import ketok.wilderness.Wilderness;
import ketok.wilderness.registry.WdBlocks;
import ketok.wilderness.registry.WdSounds;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Wilderness.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class WdEvents {
    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getWorld();
        BlockPos pos = event.getPos();
        BlockState block = level.getBlockState(pos);
        ItemStack item = event.getItemStack();

        if(item.canPerformAction(ToolActions.AXE_STRIP)) {
            if(block.getBlock() == WdBlocks.MOSSY_OAK_LOG.get() || block.getBlock() == WdBlocks.MOSSY_OAK_WOOD.get()) {
                BlockState unmossedBlock = block.getBlock().getToolModifiedState(block, new UseOnContext(event.getPlayer(), event.getHand(), event.getHitVec()), ToolActions.AXE_STRIP, false);
                if(unmossedBlock == null) return;

                level.playSound(event.getPlayer(), pos, WdSounds.UNMOSS.get(), SoundSource.BLOCKS, 1.0F, 1.0F);

                if (event.getPlayer() instanceof ServerPlayer serverPlayer) {
                    CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, pos, item);
                }

                level.setBlock(pos, unmossedBlock, 11);
                item.hurtAndBreak(1, event.getPlayer(), (p_150686_) -> {
                    p_150686_.broadcastBreakEvent(event.getHand());
                });

                event.setCanceled(true);
                event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide));
            }
        }
    }
}
