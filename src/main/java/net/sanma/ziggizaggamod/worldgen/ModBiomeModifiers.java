package net.sanma.ziggizaggamod.worldgen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.sanma.ziggizaggamod.ZiggiZaggaMod;
import net.sanma.ziggizaggamod.entity.ModEntity;

import java.util.List;

import static net.sanma.ziggizaggamod.worldgen.ModConfiguredFeatures.registerKey;

public class ModBiomeModifiers {

    public static final ResourceKey<BiomeModifier> SPAWN_ESCOBI = registerKey("spawn_escobi");
    public static final ResourceKey<BiomeModifier> ADD_PINEAPPLE_BUSH = registerKey("add_pineapple_bush");
    public static final ResourceKey<BiomeModifier> ADD_ZIGGIZITE_ORE = registerKey("add_ziggizite_ore");
    public static final ResourceKey<BiomeModifier> ADD_ZAGGAZITE_ORE = registerKey("add_zaggazite_ore");
    public static final ResourceKey<BiomeModifier> ADD_LARGE_ZIGGIZITE_ORE = registerKey("add_large_ziggizite_ore");
    public static final ResourceKey<BiomeModifier> ADD_LARGE_ZAGGAZITE_ORE = registerKey("add_large_zaggazite_ore");
    public static final ResourceKey<BiomeModifier> ADD_SMALL_ZIGGIZITE_ORE = registerKey("add_small_ziggizite_ore");
    public static final ResourceKey<BiomeModifier> ADD_SMALL_ZAGGAZITE_ORE = registerKey("add_small_zaggazite_ore");
    public static final ResourceKey<BiomeModifier> ADD_VERY_SMALL_ZIGGIZITE_ORE = registerKey("add_very_small_ziggizite_ore");
    public static final ResourceKey<BiomeModifier> ADD_VERY_SMALL_ZAGGAZITE_ORE = registerKey("add_very_small_zaggazite_ore");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(SPAWN_ESCOBI, new BiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.SWAMP), biomes.getOrThrow(Biomes.PLAINS)),
                List.of(new MobSpawnSettings.SpawnerData(ModEntity.ESCOBI.get(), 20, 1, 1))));

        context.register(ADD_PINEAPPLE_BUSH, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.JUNGLE), biomes.getOrThrow(Biomes.SAVANNA),biomes.getOrThrow(Biomes.SAVANNA_PLATEAU)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.PINEAPPLE_BUSH_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_ZIGGIZITE_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ZIGGIZITE_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_ZAGGAZITE_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ZAGGAZITE_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_LARGE_ZAGGAZITE_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ZAGGAZITE_LARGE_ORE_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_LARGE_ZIGGIZITE_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ZIGGIZITE_LARGE_ORE_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_SMALL_ZAGGAZITE_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ZAGGAZITE_SMALL_ORE_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_SMALL_ZIGGIZITE_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ZIGGIZITE_SMALL_ORE_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_VERY_SMALL_ZIGGIZITE_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ZIGGIZITE_VERY_SMALL_ORE_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_VERY_SMALL_ZAGGAZITE_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ZAGGAZITE_VERY_SMALL_ORE_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

    }
    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(ZiggiZaggaMod.MODID, name));
    }
}
