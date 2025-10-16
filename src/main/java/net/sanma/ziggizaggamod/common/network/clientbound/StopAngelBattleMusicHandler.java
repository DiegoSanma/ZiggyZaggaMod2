package net.sanma.ziggizaggamod.common.network.clientbound;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.handling.IPayloadHandler;
import net.sanma.ziggizaggamod.entity.custom.AngelEntity;
import net.sanma.ziggizaggamod.sound.AngelBattleMusic;
import net.sanma.ziggizaggamod.sound.ModSounds;

public class StopAngelBattleMusicHandler implements IPayloadHandler<AngelBattleMusicPacket> {
    @Override
    public void handle(AngelBattleMusicPacket angelBattleMusicPacket, IPayloadContext iPayloadContext) {
        iPayloadContext.enqueueWork(() -> {
            Minecraft mc = Minecraft.getInstance();
            if (mc.player != null && mc.level != null) {
                //AngelEntity boss = (AngelEntity) mc.level.getEntity(angelBattleMusicPacket.angelEntityId());
                mc.getSoundManager().stop(ModSounds.ANGEL_MUSIC.get().location(), SoundSource.HOSTILE);
            }
        }).exceptionally(e -> {
            // Handle exception
            iPayloadContext.disconnect(Component.translatable("my_mod.networking.failed", e.getMessage()));
            return null;
        });
    }
}
