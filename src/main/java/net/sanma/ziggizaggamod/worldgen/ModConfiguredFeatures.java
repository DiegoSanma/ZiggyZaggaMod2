package net.sanma.ziggizaggamod.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.sanma.ziggizaggamod.ZiggiZaggaMod;
import net.sanma.ziggizaggamod.block.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> PINEAPPLE_BUSH_KEY = registerKey("pineapple_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ZIGGIZITE_ORE_KEY = registerKey("ziggizite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ZAGGAZITE_ORE_KEY = registerKey("zaggazite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ZIGGIZITE_LARGE_ORE_KEY = registerKey("ziggizite_large_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ZAGGAZITE_LARGE_ORE_KEY = registerKey("zaggazite_large_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ZIGGIZITE_SMALL_ORE_KEY = registerKey("ziggizite_small_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ZAGGAZITE_SMALL_ORE_KEY = registerKey("zaggazite_small_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ZIGGIZITE_VERY_SMALL_ORE_KEY = registerKey("ziggizite_very_small_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ZAGGAZITE_VERY_SMALL_ORE_KEY = registerKey("zaggazite_very_small_ore");

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(ZiggiZaggaMod.MODID, name));
    }
    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> overworldZiggiziteOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.ZIGGIZITE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.ZIGGIZITE_DEEPSLATE_ORE.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> overworldZaggaziteOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.ZAGGAZITE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.ZAGGAZITE_DEEPSLATE_ORE.get().defaultBlockState()));
        register(context, ZIGGIZITE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldZiggiziteOres, 4));
        register(context, ZAGGAZITE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldZaggaziteOres, 4));
        register(context, ZAGGAZITE_SMALL_ORE_KEY, Feature.ORE, new OreConfiguration(overworldZaggaziteOres, 2));
        register(context, ZIGGIZITE_SMALL_ORE_KEY, Feature.ORE, new OreConfiguration(overworldZiggiziteOres, 2));
        register(context, ZAGGAZITE_VERY_SMALL_ORE_KEY, Feature.ORE, new OreConfiguration(overworldZaggaziteOres, 1));
        register(context, ZIGGIZITE_VERY_SMALL_ORE_KEY, Feature.ORE, new OreConfiguration(overworldZiggiziteOres, 1));
        register(context, ZAGGAZITE_LARGE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldZaggaziteOres, 6));
        register(context, ZIGGIZITE_LARGE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldZiggiziteOres, 6));
        register(context, PINEAPPLE_BUSH_KEY, Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.PINEAPPLE_BUSH.get()
                                .defaultBlockState().setValue(SweetBerryBushBlock.AGE, 3))
                        ), List.of(Blocks.GRASS_BLOCK)));
    }
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
