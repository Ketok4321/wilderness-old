package ketok.wilderness.data.server.modifiers;

import com.teamabnormals.blueprint.common.advancement.modification.AdvancementModifierProvider;
import com.teamabnormals.blueprint.common.advancement.modification.modifiers.CriteriaModifier;
import ketok.wilderness.Wilderness;
import ketok.wilderness.registry.worldgen.WdBiomes;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.LocationTrigger;
import net.minecraft.data.DataGenerator;

public class WdAdvancementModifiersProvider extends AdvancementModifierProvider {
    public WdAdvancementModifiersProvider(DataGenerator dataGenerator) {
        super(dataGenerator, Wilderness.MOD_ID);
    }

    @Override
    protected void registerEntries() {
        entrySelects("adventure/adventuring_time").addModifier(createAdventuringTime());
    }

    private EntryBuilder<Advancement.Builder, Void, DeserializationContext> entrySelects(String name) {
        return entry(name).selects(name);
    }

    private CriteriaModifier createAdventuringTime() {
        CriteriaModifier.Builder builder = CriteriaModifier.builder(this.modId);

        WdBiomes.HELPER.getDeferredRegister().getEntries().forEach((biome) -> builder.addCriterion(biome.get().getRegistryName().getPath(), LocationTrigger.TriggerInstance.located(LocationPredicate.inBiome(biome.getKey()))));
        return builder.requirements(RequirementsStrategy.AND).build();
    }
}
