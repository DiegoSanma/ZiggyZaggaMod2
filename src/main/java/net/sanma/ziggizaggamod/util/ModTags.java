package net.sanma.ziggizaggamod.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.sanma.ziggizaggamod.ZiggiZaggaMod;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_ZIGGIZITE_TOOL = createTag("needs_ziggizite_tool");
        public static final TagKey<Block> INCORRECT_FOR_ZIGGIZITE_TOOL = createTag("incorrect_for_ziggizite_tool");
        public static final TagKey<Block> NEEDS_ZAGGAZITE_TOOL = createTag("needs_zaggazite_tool");
        public static final TagKey<Block> INCORRECT_FOR_ZAGGAZITE_TOOL = createTag("incorrect_for_zaggazite_tool");


        private static TagKey<Block> createTag(String id) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(ZiggiZaggaMod.MODID, id));
        }
    }

        public static class Items {
            public static final TagKey<Item> ZIGGIZITE_TOOL_MATERIALS = createTag("ziggizite_tool_materials");
            public static final TagKey<Item> ZAGGAZITE_TOOL_MATERIALS = createTag("zaggazite_tool_materials");

            private static TagKey<Item> createTag(String id) {
                return ItemTags.create(ResourceLocation.fromNamespaceAndPath(ZiggiZaggaMod.MODID, id));
            }
        }
}
