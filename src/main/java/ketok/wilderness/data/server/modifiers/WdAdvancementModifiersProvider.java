package ketok.wilderness.data.server.modifiers;

import com.teamabnormals.blueprint.common.advancement.modification.AdvancementModifierProvider;
import com.teamabnormals.blueprint.common.advancement.modification.modifiers.CriteriaModifier;
import ketok.wilderness.Wilderness;
import ketok.wilderness.registry.WdItems;
import ketok.wilderness.registry.worldgen.WdBiomes;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.ConsumeItemTrigger;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.LocationTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;

import static ketok.wilderness.data.WdDataGenUtil.*;

public class WdAdvancementModifiersProvider extends AdvancementModifierProvider {
    public WdAdvancementModifiersProvider(DataGenerator generator) {
        super(generator, Wilderness.MOD_ID);
    }

    @Override
    protected void registerEntries() {
        entrySelects("adventure/adventuring_time").addModifier(createAdventuringTime());
        entrySelects("husbandry/balanced_diet").addModifier(createBalancedDiet());
    }

    private EntryBuilder<Advancement.Builder, Void, DeserializationContext> entrySelects(String name) {
        return entry(name).selects(name);
    }

    private CriteriaModifier createAdventuringTime() {
        CriteriaModifier.Builder builder = CriteriaModifier.builder(this.modId);

        WdBiomes.HELPER.getDeferredRegister().getEntries()
                .forEach(biome -> builder.addCriterion(name(biome.get()), LocationTrigger.TriggerInstance.located(LocationPredicate.inBiome(biome.getKey()))));

        return builder.requirements(RequirementsStrategy.AND).build();
    }

    private CriteriaModifier createBalancedDiet() {
        CriteriaModifier.Builder builder = CriteriaModifier.builder(this.modId);

        entries(WdItems.HELPER)
                .filter(Item::isEdible)
                .forEach(item -> builder.addCriterion(name(item), ConsumeItemTrigger.TriggerInstance.usedItem(item)));

        return builder.requirements(RequirementsStrategy.AND).build();
    }
}
