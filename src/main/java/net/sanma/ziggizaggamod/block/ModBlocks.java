package net.sanma.ziggizaggamod.block;


import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.sanma.ziggizaggamod.ZiggiZaggaMod;
import net.sanma.ziggizaggamod.block.custom.PineappleBushBlock;
import net.sanma.ziggizaggamod.items.ModItems;

import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(ZiggiZaggaMod.MODID);

    //Registery of Blocks

    public static final DeferredBlock<Block> ZIGGIZITE_ORE =
          registerBlock("ziggizite_ore",(properties) -> new DropExperienceBlock(UniformInt.of(2,4), properties.strength(4f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> ZAGGAZITE_ORE = registerBlock("zaggazite_ore",
            (properties) -> new DropExperienceBlock(UniformInt.of(2, 4),
                    properties.strength(3f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> ZAGGAZITE_DEEPSLATE_ORE = registerBlock("ziggizite_deepslate_ore",
            (properties) -> new DropExperienceBlock(UniformInt.of(2, 4),
                    properties.strength(4f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> ZIGGIZITE_DEEPSLATE_ORE = registerBlock("zaggazite_deepslate_ore",
            (properties) -> new DropExperienceBlock(UniformInt.of(2, 4),
                    properties.strength(4f).requiresCorrectToolForDrops()));

    public static  final DeferredBlock<Block> PINEAPPLE_BUSH = registerBlock("pineapple_bush",
            (properties) -> new PineappleBushBlock(properties.mapColor(MapColor.PLANT).strength(0.2F)
                    .randomTicks().sound(SoundType.CROP).noCollission().pushReaction(PushReaction.DESTROY)));
    //End of Registery of Blocks
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> function) {
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, function);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.registerItem(name, (properties) -> new BlockItem(block.get(), properties.useBlockDescriptionPrefix()));
    }

    public static void register(IEventBus event) {
        BLOCKS.register(event);
    }
}
