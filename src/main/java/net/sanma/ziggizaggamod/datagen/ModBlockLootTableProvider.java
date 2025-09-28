package net.sanma.ziggizaggamod.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.sanma.ziggizaggamod.block.ModBlocks;
import net.sanma.ziggizaggamod.items.ModItems;

import java.util.Set;
import java.util.stream.Collectors;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider pRegistries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), pRegistries);
    }

    @Override
    protected void generate() {
        this.add(ModBlocks.ZIGGIZITE_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.ZIGGIZITE_ORE.get(), ModItems.ZIGGIZITE_NUGGET.get(), 1f, 3f));
        this.add(ModBlocks.ZAGGAZITE_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.ZAGGAZITE_ORE.get(), ModItems.ZAGGAZITE_NUGGET.get(), 1f, 3f));
        this.add(ModBlocks.ZIGGIZITE_DEEPSLATE_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.ZIGGIZITE_DEEPSLATE_ORE.get(), ModItems.ZIGGIZITE_NUGGET.get(),1f,3f));
        this.add(ModBlocks.ZAGGAZITE_DEEPSLATE_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.ZAGGAZITE_DEEPSLATE_ORE.get(), ModItems.ZAGGAZITE_NUGGET.get(),1f,3f));
    }

    protected LootTable.Builder createMultipleOreDrops(Block p_251306_, Item item, float min, float max) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(
                p_251306_,
                (LootPoolEntryContainer.Builder<?>)this.applyExplosionDecay(
                        p_251306_,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))
                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
