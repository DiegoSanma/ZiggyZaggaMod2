package net.sanma.ziggizaggamod.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.sanma.ziggizaggamod.ZiggiZaggaMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.sanma.ziggizaggamod.block.ModBlocks;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ZiggiZaggaMod.MODID);

    public static final Supplier<CreativeModeTab> ZiggiZaggaTab =
            CREATIVE_MODE_TABS.register("ziggizagga_tab",
                    () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.TENKAICHI_MEDALLION.get()))
                            .title(Component.translatable("creativetab.ziggizagga_tab"))
                            .displayItems((itemDisplayParameters, output) -> {
                                output.accept(ModItems.TENKAICHI_MEDALLION.get());
                                output.accept(ModItems.SANMA_GLASSES.get());
                                output.accept(ModItems.MIGUEL_STACHE.get());
                                output.accept(ModItems.DFEO_GREEN_CARD.get());
                                output.accept(ModItems.GABO_LIVER.get());
                                output.accept(ModItems.PEPA_SKULL.get());
                                output.accept(ModItems.YONYE_BREAD.get());
                                output.accept(ModItems.DONVITO_MANJARATE.get());
                                output.accept(ModItems.ZAGGAZITE_NUGGET.get());
                                output.accept(ModItems.ZIGGIZITE_NUGGET.get());
                                output.accept(ModItems.ZAGGAZITE.get());
                                output.accept(ModItems.ZIGGIZITE.get());
                                output.accept(ModBlocks.ZIGGIZITE_ORE.get());
                                output.accept(ModBlocks.ZIGGIZITE_DEEPSLATE_ORE.get());
                                output.accept(ModBlocks.ZAGGAZITE_DEEPSLATE_ORE.get());
                                output.accept(ModItems.ZIGGIZITE_AXE.get());
                                output.accept(ModItems.ZIGGIZITE_HOE.get());
                                output.accept(ModItems.ZIGGIZITE_SWORD.get());
                                output.accept(ModItems.ZIGGIZITE_PICKAXE.get());
                                output.accept(ModItems.ZIGGIZITE_SHOVEL.get());
                                output.accept(ModItems.ZAGGAZITE_AXE.get());
                                output.accept(ModItems.ZAGGAZITE_HOE.get());
                                output.accept(ModItems.ZAGGAZITE_SWORD.get());
                                output.accept(ModItems.ZAGGAZITE_PICKAXE.get());
                                output.accept(ModItems.ZAGGAZITE_SHOVEL.get());
                            }).build());
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
