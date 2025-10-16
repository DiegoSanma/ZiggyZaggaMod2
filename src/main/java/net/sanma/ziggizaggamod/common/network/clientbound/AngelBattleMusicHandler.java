package net.sanma.ziggizaggamod.common.network.clientbound;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.handling.IPayloadHandler;
import net.sanma.ziggizaggamod.entity.custom.AngelEntity;
import net.sanma.ziggizaggamod.sound.AngelBattleMusic;

public class AngelBattleMusicHandler implements IPayloadHandler<AngelBattleMusicPacket> {
    @Override
    public void handle(AngelBattleMusicPacket angelBattleMusicPacket, IPayloadContext iPayloadContext) {
        iPayloadContext.enqueueWork(() -> {
            System.out.println("Entré al handler");
            Minecraft mc = Minecraft.getInstance();
            if (mc.player != null && mc.level != null) {
                System.out.println("Debería comenzar la música");
                AngelEntity boss = (AngelEntity) mc.level.getEntity(angelBattleMusicPacket.angelEntityId());
                System.out.println(boss);
                mc.getSoundManager().play(new AngelBattleMusic(boss));
                System.out.println("Debería estar sonando la música");
            }
        }).exceptionally(e -> {
            // Handle exception
            iPayloadContext.disconnect(Component.translatable("my_mod.networking.failed", e.getMessage()));
            return null;
        });
    }
}
