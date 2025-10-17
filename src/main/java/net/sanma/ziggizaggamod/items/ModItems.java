package net.sanma.ziggizaggamod.items;

import net.minecraft.client.gui.Gui;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.sanma.ziggizaggamod.ZiggiZaggaMod;
import net.sanma.ziggizaggamod.block.ModBlocks;
import net.sanma.ziggizaggamod.capability.*;
import net.sanma.ziggizaggamod.entity.ModEntity;
import net.sanma.ziggizaggamod.items.custom.AlcoholItem;
import net.sanma.ziggizaggamod.items.custom.PineappleItem;

import javax.swing.plaf.basic.BasicComboBoxUI;


public class ModItems {
    //Deffered Register for Items
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ZiggiZaggaMod.MODID);

    public static final DeferredItem<Item> TENKAICHI_DOG_TAG =
            ITEMS.registerItem("tenkaichidogtag", TenkaichiItem::new,new Item.Properties());
    public static final DeferredItem<Item> SANMA_GLASSES =
            ITEMS.registerItem("sanmaglasses", SanmaItem::new,new Item.Properties());
    public static final DeferredItem<Item> MIGUEL_STACHE =
            ITEMS.registerItem("miguelstache", MiguelItem::new,new Item.Properties());
    public static final DeferredItem<Item> DFEO_VBUCK =
            ITEMS.registerItem("dfeovbuck", DfeoItem::new,new Item.Properties());
    public static final DeferredItem<Item> PEPA_SKULL =
            ITEMS.registerItem("pepaskull", PepaItem::new,new Item.Properties());
    public static final DeferredItem<Item> YONYE_BREAD =
            ITEMS.registerItem("yonyebread", YonyeItem::new,new Item.Properties());
    public static final DeferredItem<Item> GABO_LIVER =
            ITEMS.registerItem("gaboliver", GaboItem::new,new Item.Properties());
    public static final DeferredItem<Item> DONVITO_MANJARATE =
            ITEMS.registerItem("donvitomanjarate", DonvitoItem::new,new Item.Properties());
    public static final DeferredItem<Item> ZIGGIZITE_NUGGET =
            ITEMS.registerItem("ziggizite_nugget",Item::new,new Item.Properties());
    public static final DeferredItem<Item> ZIGGIZITE =
            ITEMS.registerItem("ziggizite",Item::new,new Item.Properties());
    public static final DeferredItem<Item> ZAGGAZITE_NUGGET =
            ITEMS.registerItem("zaggazite_nugget",Item::new,new Item.Properties());
    public static final DeferredItem<Item> ZAGGAZITE =
            ITEMS.registerItem("zaggazite",Item::new,new Item.Properties());
    public static final DeferredItem<SwordItem> ZIGGIZITE_SWORD =
            ITEMS.registerItem("ziggizite_sword", (properties) -> new SwordItem(ModToolTiers.ZIGGIZITE,3f,-1.0f,properties));
    public static final DeferredItem<PickaxeItem> ZIGGIZITE_PICKAXE =
            ITEMS.registerItem("ziggizite_pickaxe",(properties) -> new PickaxeItem(ModToolTiers.ZIGGIZITE,1f,-2.8f,properties));
    public static final DeferredItem<AxeItem> ZIGGIZITE_AXE =
            ITEMS.registerItem("ziggizite_axe",(properties) -> new AxeItem(ModToolTiers.ZIGGIZITE,6f,-3.0f,properties));
    public static final DeferredItem<ShovelItem> ZIGGIZITE_SHOVEL =
            ITEMS.registerItem("ziggizite_shovel",(properties) -> new ShovelItem(ModToolTiers.ZIGGIZITE,1.5f,-2.8f,properties));
    public static final DeferredItem<HoeItem> ZIGGIZITE_HOE =
            ITEMS.registerItem("ziggizite_hoe",(properties) -> new HoeItem(ModToolTiers.ZIGGIZITE,0f,-3.0f,properties));

    public static final DeferredItem<SwordItem> ZAGGAZITE_SWORD =
            ITEMS.registerItem("zaggazite_sword", (properties) -> new SwordItem(ModToolTiers.ZAGGAZITE,3f,-1.0f,properties));
    public static final DeferredItem<PickaxeItem> ZAGGAZITE_PICKAXE =
            ITEMS.registerItem("zaggazite_pickaxe",(properties) -> new PickaxeItem(ModToolTiers.ZAGGAZITE,1f,-2.8f,properties));
    public static final DeferredItem<AxeItem> ZAGGAZITE_AXE =
            ITEMS.registerItem("zaggazite_axe",(properties) -> new AxeItem(ModToolTiers.ZAGGAZITE,6f,-3.0f,properties));
    public static final DeferredItem<ShovelItem> ZAGGAZITE_SHOVEL =
            ITEMS.registerItem("zaggazite_shovel",(properties) -> new ShovelItem(ModToolTiers.ZAGGAZITE,1.5f,-2.8f,properties));
    public static final DeferredItem<HoeItem> ZAGGAZITE_HOE =
            ITEMS.registerItem("zaggazite_hoe",(properties) -> new HoeItem(ModToolTiers.ZAGGAZITE,0f,-3.0f,properties));

    public static final DeferredItem<Item> ESCOBI_SPAWN_EGG = ITEMS.registerItem("escobi_spawn_egg",
            (properties) -> new SpawnEggItem(ModEntity.ESCOBI.get(), properties));
    public static final DeferredItem<Item> ANGEL_SPAWN_EGG = ITEMS.registerItem("angel_spawn_egg",
            (properties) -> new SpawnEggItem(ModEntity.ANGEL.get(), properties));

    public static final DeferredItem<Item> PINEAPPLE = ITEMS.registerItem("pineapple",
            (properties) -> new PineappleItem(ModBlocks.PINEAPPLE_BUSH.get(), properties.food(ModFoodProperties.PINEAPPLE)));
    public static final DeferredItem<Item> PINEAPPLE_SLICE = ITEMS.registerItem("pineapple_slice",
            (properties -> new Item(properties.food(ModFoodProperties.PINEAPPLE_SLICE))));
    public static final DeferredItem<Item> EMPANADA = ITEMS.registerItem("empanada",
        (properties -> new Item(properties.food(ModFoodProperties.EMPANADA))));
    public static final DeferredItem<Item> GRAPE = ITEMS.registerItem("grape",
            (properties -> new Item(properties.food(ModFoodProperties.GRAPE))));
    public static final DeferredItem<Item> TERREMOTO = ITEMS.registerItem("terremoto",
            (properties -> new AlcoholItem(properties.food(ModFoodProperties.TERREMOTO,ModFoodProperties.TERREMOTO_EFFECT))));


    public static void register(IEventBus event){
        ITEMS.register(event);
    }
}
