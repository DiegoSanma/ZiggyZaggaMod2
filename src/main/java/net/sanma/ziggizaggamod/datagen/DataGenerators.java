package net.sanma.ziggizaggamod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.sanma.ziggizaggamod.ZiggiZaggaMod;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
@EventBusSubscriber(modid = ZiggiZaggaMod.MODID,bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event) {
        DataGenerator gen = event.getGenerator();
        PackOutput output = gen.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        // --------------------
        // Loot tables
        // --------------------
        gen.addProvider(true,
                new LootTableProvider(
                        output,
                        Collections.emptySet(),
                        List.of(new LootTableProvider.SubProviderEntry(
                                ModBlockLootTableProvider::new,
                                LootContextParamSets.BLOCK)),
                        lookupProvider
                )
        );

        // --------------------
        // Recipes
        // --------------------
        gen.addProvider(true,
                new ModRecipeDataProvider(output, lookupProvider)
        );
        gen.addProvider(true, new ModDatapackProvider(output, lookupProvider));

        // --------------------
        // Block tags
        // --------------------
        BlockTagsProvider blockTagsProvider = new ModBlockTagProvider(output, lookupProvider);
        gen.addProvider(true, blockTagsProvider);

        // --------------------
        // Item tags (puede depender de blockTagsProvider)
        // --------------------
        gen.addProvider(true,
                new ModItemTagProvider(output, lookupProvider, blockTagsProvider.contentsGetter())
        );
        // --------------------
        // Models (client-side)
        // --------------------
        gen.addProvider(true,
                new ModModelProvider(output)
        );

        gen.addProvider(true,
                new CuriosSlotProvider(ZiggiZaggaMod.MODID,output,lookupProvider));
    }
    @SubscribeEvent
    public static void gatherServerData(GatherDataEvent.Server event) {
        DataGenerator gen = event.getGenerator();
        PackOutput output = gen.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        // --------------------
        // Loot tables
        // --------------------
        gen.addProvider(true,
                new LootTableProvider(
                        output,
                        Collections.emptySet(),
                        List.of(new LootTableProvider.SubProviderEntry(
                                ModBlockLootTableProvider::new,
                                LootContextParamSets.BLOCK)),
                        lookupProvider
                )
        );

        // --------------------
        // Recipes
        // --------------------
        gen.addProvider(true,
                new ModRecipeDataProvider(output, lookupProvider)
        );

        // --------------------
        // Block tags
        // --------------------
        BlockTagsProvider blockTagsProvider = new ModBlockTagProvider(output, lookupProvider);
        gen.addProvider(true, blockTagsProvider);

        // --------------------
        // Item tags (puede depender de blockTagsProvider)
        // --------------------
        gen.addProvider(true,
                new ModItemTagProvider(output, lookupProvider, blockTagsProvider.contentsGetter())
        );
        gen.addProvider(true, new ModDatapackProvider(output, lookupProvider));
        // --------------------
        // Models (client-side)
        // --------------------
        gen.addProvider(true,
                new ModModelProvider(output)
        );

        gen.addProvider(true,
                new CuriosSlotProvider(ZiggiZaggaMod.MODID,output,lookupProvider));
    }
}


