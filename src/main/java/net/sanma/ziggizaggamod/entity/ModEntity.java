package net.sanma.ziggizaggamod.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.sanma.ziggizaggamod.ZiggiZaggaMod;
import net.sanma.ziggizaggamod.entity.custom.AngelEntity;
import net.sanma.ziggizaggamod.entity.custom.EscobiEntity;
import net.sanma.ziggizaggamod.entity.custom.JobEntity;

import java.util.function.Supplier;

public class ModEntity {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, ZiggiZaggaMod.MODID);

    public static ResourceKey<EntityType<?>> ESCOBI_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("escobi"));
    public static final Supplier<EntityType<EscobiEntity>> ESCOBI =
            ENTITY_TYPES.register("escobi", () -> EntityType.Builder.of(EscobiEntity::new, MobCategory.MONSTER)
                    .sized(0.5f, 2.5f).build(ESCOBI_KEY));

    public static ResourceKey<EntityType<?>> JOB_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("job"));
    public static final Supplier<EntityType<JobEntity>> JOB =
            ENTITY_TYPES.register("job", () -> EntityType.Builder.of(JobEntity::new, MobCategory.CREATURE)
                    .sized(0.5f, 4.0f).build(JOB_KEY));

    public static ResourceKey<EntityType<?>> ANGEL_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("angel"));
    public static final Supplier<EntityType<AngelEntity>> ANGEL =
            ENTITY_TYPES.register("angel", () -> EntityType.Builder.of(AngelEntity::new, MobCategory.MONSTER)
                    .sized(2.5f, 4.0f).build(ANGEL_KEY));



    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}