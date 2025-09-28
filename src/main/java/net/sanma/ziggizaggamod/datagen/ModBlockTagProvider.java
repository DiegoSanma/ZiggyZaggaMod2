package net.sanma.ziggizaggamod.datagen;


import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.sanma.ziggizaggamod.ZiggiZaggaMod;
import net.sanma.ziggizaggamod.block.ModBlocks;
import net.sanma.ziggizaggamod.util.ModTags;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, ZiggiZaggaMod.MODID);
    }
    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.ZIGGIZITE_ORE.get())
                .add(ModBlocks.ZAGGAZITE_ORE.get())
                .add(ModBlocks.ZIGGIZITE_DEEPSLATE_ORE.get())
                .add(ModBlocks.ZAGGAZITE_DEEPSLATE_ORE.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.ZIGGIZITE_ORE.get())
                .add(ModBlocks.ZAGGAZITE_ORE.get())
                .add(ModBlocks.ZIGGIZITE_DEEPSLATE_ORE.get())
                .add(ModBlocks.ZAGGAZITE_DEEPSLATE_ORE.get());
        tag(ModTags.Blocks.NEEDS_ZIGGIZITE_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);
        tag(ModTags.Blocks.NEEDS_ZAGGAZITE_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);
    }
}
