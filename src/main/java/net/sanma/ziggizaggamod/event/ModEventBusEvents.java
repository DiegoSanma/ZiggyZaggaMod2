package net.sanma.ziggizaggamod.event;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.sanma.ziggizaggamod.ZiggiZaggaMod;
import net.sanma.ziggizaggamod.entity.ModEntity;
import net.sanma.ziggizaggamod.entity.client.angel.AngelModel;
import net.sanma.ziggizaggamod.entity.client.escobi.EscobiModel;
import net.sanma.ziggizaggamod.entity.client.job.JobModel;
import net.sanma.ziggizaggamod.entity.custom.AngelEntity;
import net.sanma.ziggizaggamod.entity.custom.EscobiEntity;
import net.sanma.ziggizaggamod.entity.custom.JobEntity;

@EventBusSubscriber(modid = ZiggiZaggaMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(EscobiModel.LAYER_LOCATION, EscobiModel::createBodyLayer);
        event.registerLayerDefinition(JobModel.LAYER_LOCATION, JobModel::createBodyLayer);
        event.registerLayerDefinition(AngelModel.LAYER_LOCATION, AngelModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntity.ESCOBI.get(), EscobiEntity.createAttributes().build());
        event.put(ModEntity.JOB.get(), JobEntity.createAttributes().build());
        event.put(ModEntity.ANGEL.get(), AngelEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(ModEntity.ESCOBI.get(), SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMobSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(ModEntity.JOB.get(),SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Mob::checkMobSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }
}
