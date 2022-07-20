package ketok.wilderness.other;

import ketok.wilderness.WdConfig;
import ketok.wilderness.Wilderness;
import ketok.wilderness.common.block.BlackberryBushBlock;
import ketok.wilderness.registry.WdBlocks;
import ketok.wilderness.registry.WdSounds;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SweetBerryBushBlock;
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
        Player player = event.getPlayer();
        BlockPos pos = event.getPos();
        BlockState block = level.getBlockState(pos);
        ItemStack item = event.getItemStack();

        if((block.getBlock() == WdBlocks.MOSSY_OAK_LOG.get() || block.getBlock() == WdBlocks.MOSSY_OAK_WOOD.get()) && item.canPerformAction(ToolActions.AXE_STRIP)) {
            BlockState unmossedBlock = block.getBlock().getToolModifiedState(block, new UseOnContext(player, event.getHand(), event.getHitVec()), ToolActions.AXE_STRIP, false);
            if(unmossedBlock == null) return;

            level.playSound(player, pos, WdSounds.UNMOSS.get(), SoundSource.BLOCKS, 1.0F, 1.0F);

            if (player instanceof ServerPlayer serverPlayer) {
                CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, pos, item);
            }

            level.setBlock(pos, unmossedBlock, 11);
            item.hurtAndBreak(1, player, (p_150686_) -> {
                p_150686_.broadcastBreakEvent(event.getHand());
            });

            event.setCanceled(true);
            event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide));
        }

        if(WdConfig.COMMON.shearableBerries.get() && block.getBlock() instanceof SweetBerryBushBlock && item.canPerformAction(ToolActions.SHEARS_HARVEST)) { //TODO: Use different action?
            if (block.getValue(BlackberryBushBlock.CAN_GROW)) {
                if (player instanceof ServerPlayer) {
                    CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)player, pos, item);
                }

                level.playSound(player, pos, SoundEvents.GROWING_PLANT_CROP, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.setBlockAndUpdate(pos, block.setValue(BlackberryBushBlock.CAN_GROW, false));
                item.hurtAndBreak(1, player, (p_186374_) -> {
                    p_186374_.broadcastBreakEvent(event.getHand());
                });

                event.setCanceled(true);
                event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide));
            }
        }
    }
}
