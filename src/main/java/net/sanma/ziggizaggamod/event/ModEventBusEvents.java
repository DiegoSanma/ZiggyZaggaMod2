package net.sanma.ziggizaggamod.event;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.sanma.ziggizaggamod.ZiggiZaggaMod;
import net.sanma.ziggizaggamod.entity.ModEntity;
import net.sanma.ziggizaggamod.entity.client.angel.AngelModel;
import net.sanma.ziggizaggamod.entity.client.escobi.EscobiModel;
import net.sanma.ziggizaggamod.entity.client.job.JobModel;
import net.sanma.ziggizaggamod.entity.client.pineapple.PineappleProjectileModel;
import net.sanma.ziggizaggamod.entity.custom.AngelEntity;
import net.sanma.ziggizaggamod.entity.custom.EscobiEntity;
import net.sanma.ziggizaggamod.entity.custom.JobEntity;
import net.sanma.ziggizaggamod.items.ModItems;

@EventBusSubscriber(modid = ZiggiZaggaMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(EscobiModel.LAYER_LOCATION, EscobiModel::createBodyLayer);
        event.registerLayerDefinition(JobModel.LAYER_LOCATION, JobModel::createBodyLayer);
        event.registerLayerDefinition(AngelModel.LAYER_LOCATION, AngelModel::createBodyLayer);
        event.registerLayerDefinition(PineappleProjectileModel.LAYER_LOCATION, PineappleProjectileModel::createBodyLayer);
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


    @SubscribeEvent
    public static void onWolfRightClick(PlayerInteractEvent.EntityInteract event) {
        // Solo si el jugador está usando la mano principal
        if (event.getHand() != InteractionHand.MAIN_HAND) return;

        if (!(event.getTarget() instanceof Wolf wolf)) return;
        if (!wolf.isTame()) return; // solo lobos domesticados

        Player player = event.getEntity();
        ItemStack heldItem = player.getMainHandItem();

        // Verifica que tenga el item especial en la mano
        if (!heldItem.is(ModItems.WOLF_SOUL.get())) return;

        Level level = wolf.level();
        if (!level.isClientSide) {
            ServerLevel serverLevel = (ServerLevel) level;
            for (int i = 0; i < 20; i++) {
                double dx = wolf.getRandomX(0.5);
                double dy = wolf.getRandomY();
                double dz = wolf.getRandomZ(0.5);
                serverLevel.sendParticles(ParticleTypes.SOUL, dx,dy, dz, 20,0,0,0,1);
            }
            serverLevel.playSound(null, wolf.blockPosition(),
                    SoundEvents.WOLF_DEATH, SoundSource.NEUTRAL,
                    1.0F, 1.0F);
            // Crea el drop
            ItemStack dropStack = new ItemStack(ModItems.DFEO_VBUCK.get());
            ItemEntity drop = new ItemEntity(level, wolf.getX(), wolf.getY(), wolf.getZ(), dropStack);
            level.addFreshEntity(drop);

            // Mata al lobo
            wolf.discard();

            // Consume el ítem si no es creativo
            if (!player.isCreative()) {
                heldItem.shrink(1);
            }
        }

        // Cancela la interacción base (para que no se siente el lobo)
        event.setCanceled(true);
    }
}
