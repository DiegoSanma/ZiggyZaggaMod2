package net.sanma.ziggizaggamod.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.sanma.ziggizaggamod.ZiggiZaggaMod;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> PINEAPPLE_BUSH_PLACED_KEY = registerKey("pineapple_bush_placed");
    public static final ResourceKey<PlacedFeature> ZIGGIZITE_ORE_PLACED_KEY = registerKey("ziggizite_ore_placed");
    public static final ResourceKey<PlacedFeature> ZAGGAZITE_ORE_PLACED_KEY = registerKey("zaggazite_ore_placed");
    public static final ResourceKey<PlacedFeature> ZIGGIZITE_LARGE_ORE_KEY = registerKey("ziggizite_large_ore_placed");
    public static final ResourceKey<PlacedFeature> ZAGGAZITE_LARGE_ORE_KEY = registerKey("zaggazite_large_ore_placed");
    public static final ResourceKey<PlacedFeature> ZIGGIZITE_SMALL_ORE_KEY = registerKey("ziggizite_small_ore_placed");
    public static final ResourceKey<PlacedFeature> ZAGGAZITE_SMALL_ORE_KEY = registerKey("zaggazite_small_ore_placed");
    public static final ResourceKey<PlacedFeature> ZIGGIZITE_VERY_SMALL_ORE_KEY = registerKey("ziggizite_very_small_ore_placed");
    public static final ResourceKey<PlacedFeature> ZAGGAZITE_VERY_SMALL_ORE_KEY = registerKey("zaggazite_very_small_ore_placed");

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(ZiggiZaggaMod.MODID, name));
    }
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        register(context, PINEAPPLE_BUSH_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PINEAPPLE_BUSH_KEY),
                List.of(RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        register(context, ZIGGIZITE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ZIGGIZITE_ORE_KEY),
                ModOrePlacement.commonOrePlacement(8, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(10))));
        register(context, ZAGGAZITE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ZAGGAZITE_ORE_KEY),
                ModOrePlacement.commonOrePlacement(8, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(10))));
        register(context, ZIGGIZITE_LARGE_ORE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ZIGGIZITE_LARGE_ORE_KEY),
                ModOrePlacement.rareOrePlacement(2, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-10))));
        register(context, ZAGGAZITE_LARGE_ORE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ZAGGAZITE_LARGE_ORE_KEY),
                ModOrePlacement.rareOrePlacement(2, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-10))));
        register(context, ZIGGIZITE_SMALL_ORE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ZIGGIZITE_SMALL_ORE_KEY),
                ModOrePlacement.commonOrePlacement(5, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(30))));
        register(context, ZAGGAZITE_SMALL_ORE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ZAGGAZITE_SMALL_ORE_KEY),
                ModOrePlacement.commonOrePlacement(5, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(30))));
        register(context, ZIGGIZITE_VERY_SMALL_ORE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ZIGGIZITE_VERY_SMALL_ORE_KEY),
                ModOrePlacement.commonOrePlacement(12, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(60))));
        register(context, ZAGGAZITE_VERY_SMALL_ORE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ZAGGAZITE_VERY_SMALL_ORE_KEY),
                ModOrePlacement.commonOrePlacement(12, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(60))));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
