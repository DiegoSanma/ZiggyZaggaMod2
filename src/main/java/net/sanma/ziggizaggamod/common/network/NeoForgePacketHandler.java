package net.sanma.ziggizaggamod.common.network;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagNetworkSerialization;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.NetworkPayloadSetup;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.sanma.ziggizaggamod.ZiggiZaggaMod;
import net.sanma.ziggizaggamod.common.network.clientbound.AngelBattleMusicHandler;
import net.sanma.ziggizaggamod.common.network.clientbound.AngelBattleMusicPacket;

@EventBusSubscriber(modid = ZiggiZaggaMod.MODID,bus = EventBusSubscriber.Bus.MOD)
public class NeoForgePacketHandler {

    @SubscribeEvent
    public static void registerPayloads(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("start_angel_battle");

        registrar.playToClient(
                AngelBattleMusicPacket.TYPE,
                AngelBattleMusicPacket.STREAM_CODEC,
                new AngelBattleMusicHandler()
        );
    }
    /** Enviar música a un jugador específico */
    public static void sendBattleMusicToPlayer(ServerPlayer player,int AngelId) {
        var payload = new AngelBattleMusicPacket(ResourceLocation.fromNamespaceAndPath(ZiggiZaggaMod.MODID,"start_angel_music").toString(),AngelId);
        System.out.println("Cree el payload y estoy por mandarlo");
        player.connection.send(payload);
    }

    /** Enviar música a todos los jugadores cercanos a una entidad */
    public static void sendBattleMusicToNearbyPlayers(Entity boss) {
        System.out.println("Cree el payload y estoy por mandarlo");
        //if (!(boss.level() instanceof ServerLevel level)) return;
        int AngelId = boss.getId();
        var payload = new AngelBattleMusicPacket(ResourceLocation.fromNamespaceAndPath(ZiggiZaggaMod.MODID,"angel_battle_music").toString(),AngelId);
        PacketDistributor.sendToPlayersTrackingEntity(boss, payload);

    }
}
