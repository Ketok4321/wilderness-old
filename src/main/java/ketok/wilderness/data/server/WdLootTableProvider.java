package ketok.wilderness.data.server;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import ketok.wilderness.data.WdDataGenUtil;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static ketok.wilderness.data.WdDataGenUtil.*;

public class WdLootTableProvider extends LootTableProvider {
    private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> lootTables = ImmutableList.of(Pair.of(WdBlockLoot::new, LootContextParamSets.BLOCK));

    public WdLootTableProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        return this.lootTables;
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationtracker) { }

    private static class WdBlockLoot extends BlockLoot {
        @Override
        protected void addTables() {
            WdDataGenUtil.wdBlockItems().forEach(block -> dropSelf(block));
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return wdBlocks()::iterator;
        }
    }
}
