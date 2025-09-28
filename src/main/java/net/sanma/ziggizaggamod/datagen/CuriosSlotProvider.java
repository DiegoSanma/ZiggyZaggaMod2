package net.sanma.ziggizaggamod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.sanma.ziggizaggamod.ZiggiZaggaMod;
import top.theillusivec4.curios.api.CuriosDataProvider;

import java.util.concurrent.CompletableFuture;

public class CuriosSlotProvider extends CuriosDataProvider {
    public CuriosSlotProvider(String modId, PackOutput output,
                              CompletableFuture<HolderLookup.Provider> registries) {
        super(modId, output, registries);
    }

    @Override
    public void generate(HolderLookup.Provider registries) {
        this.createSlot("hero_slot")
                .icon(ResourceLocation.fromNamespaceAndPath(ZiggiZaggaMod.MODID,"slot/hero_slot"))
                .addValidator(ResourceLocation.fromNamespaceAndPath(ZiggiZaggaMod.MODID,"hero_validator"));

        this.createEntities("hero_slot")
                .addPlayer()
                .addSlots("hero_slot");
    }
}
