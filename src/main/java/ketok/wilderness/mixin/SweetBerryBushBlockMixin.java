package ketok.wilderness.mixin;

import ketok.wilderness.common.block.BlackberryBushBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SweetBerryBushBlock.class)
public abstract class SweetBerryBushBlockMixin extends Block {
    public SweetBerryBushBlockMixin(Properties p_49795_) {
        super(p_49795_);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    public void init(CallbackInfo info) {
        this.registerDefaultState(this.defaultBlockState().setValue(BlackberryBushBlock.CAN_GROW, true));
    }

    @Inject(method = "createBlockStateDefinition", at = @At("TAIL"))
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder, CallbackInfo info) {
        builder.add(BlackberryBushBlock.CAN_GROW);
    }

    @Inject(method = "isRandomlyTicking", at = @At("RETURN"), cancellable = true)
    public void isRandomlyTicking(BlockState state, CallbackInfoReturnable<Boolean> info) {
        if(!state.getValue(BlackberryBushBlock.CAN_GROW)) info.setReturnValue(false);
    }
}
