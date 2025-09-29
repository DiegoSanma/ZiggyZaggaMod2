package net.sanma.ziggizaggamod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.VanillaItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.sanma.ziggizaggamod.ZiggiZaggaMod;
import net.sanma.ziggizaggamod.items.ModItems;
import net.sanma.ziggizaggamod.util.ModTags;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags) {
        super(output, lookupProvider, blockTags, ZiggiZaggaMod.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(ModTags.Items.HEROES_ITEM)
                .add(ModItems.TENKAICHI_DOG_TAG.get())
                .add(ModItems.SANMA_GLASSES.get())
                .add(ModItems.MIGUEL_STACHE.get())
                .add(ModItems.DFEO_VBUCK.get())
                .add(ModItems.GABO_LIVER.get())
                .add(ModItems.DONVITO_MANJARATE.get())
                .add(ModItems.YONYE_BREAD.get())
                .add(ModItems.PEPA_SKULL.get());

    }
}
