package net.sanma.ziggizaggamod;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.common.NeoForgeConfig;
import net.sanma.ziggizaggamod.block.ModBlocks;
import net.sanma.ziggizaggamod.capability.HeroItem;
import net.sanma.ziggizaggamod.common.CommonProxy;
import net.sanma.ziggizaggamod.common.network.NeoForgePacketHandler;
import net.sanma.ziggizaggamod.effect.ModEffects;
import net.sanma.ziggizaggamod.entity.ModEntity;
import net.sanma.ziggizaggamod.entity.client.angel.AngelRenderer;
import net.sanma.ziggizaggamod.entity.client.escobi.EscobiRenderer;
import net.sanma.ziggizaggamod.entity.client.job.JobRenderer;
import net.sanma.ziggizaggamod.items.ModCreativeModeTabs;
import net.sanma.ziggizaggamod.items.ModItems;
import net.sanma.ziggizaggamod.sound.ModSounds;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(ZiggiZaggaMod.MODID)
public class ZiggiZaggaMod {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "ziggizaggamod";
    //public static CommonProxy proxy;

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public ZiggiZaggaMod(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        //modEventBus.addListener(NeoForgePacketHandler::registerPayloads);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        //NeoForgePacketHandler.registerPayloads(modEventBus);

        //this.proxy = CommonProxy.INSTANCE;

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModEntity.register(modEventBus);
        ModSounds.register(modEventBus);
        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        ModEffects.register(modEventBus);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        //HeroItem.register(ModItems.TENKAICHI_MEDALLION.get());
        HeroItem.registerPredicate();
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModItems.ZAGGAZITE_NUGGET);
            event.accept(ModItems.ZIGGIZITE_NUGGET);
            event.accept(ModItems.ZAGGAZITE);
            event.accept(ModItems.ZIGGIZITE);
            event.accept(ModBlocks.ZIGGIZITE_ORE);
            event.accept(ModBlocks.ZAGGAZITE_ORE);
            event.accept(ModBlocks.ZIGGIZITE_DEEPSLATE_ORE);
            event.accept(ModBlocks.ZAGGAZITE_DEEPSLATE_ORE);
            event.accept(ModItems.ZIGGIZITE_AXE);
            event.accept(ModItems.ZIGGIZITE_HOE);
            event.accept(ModItems.ZIGGIZITE_SWORD);
            event.accept(ModItems.ZIGGIZITE_PICKAXE);
            event.accept(ModItems.ZIGGIZITE_SHOVEL);
            event.accept(ModItems.ZAGGAZITE_AXE);
            event.accept(ModItems.ZAGGAZITE_HOE);
            event.accept(ModItems.ZAGGAZITE_SWORD);
            event.accept(ModItems.ZAGGAZITE_PICKAXE);
            event.accept(ModItems.ZAGGAZITE_SHOVEL);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntity.ESCOBI.get(), EscobiRenderer::new);
            EntityRenderers.register(ModEntity.JOB.get(), JobRenderer::new);
            EntityRenderers.register(ModEntity.ANGEL.get(), AngelRenderer::new);
        }
    }
}
