package net.sanma.ziggizaggamod.common.network.clientbound;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.sanma.ziggizaggamod.ZiggiZaggaMod;

public record AngelBattleMusicPacket(String soundEventId,int angelEntityId) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<AngelBattleMusicPacket> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(ZiggiZaggaMod.MODID,"start_angel_battle_music"));

    public static final StreamCodec<FriendlyByteBuf, AngelBattleMusicPacket> STREAM_CODEC =
        StreamCodec.composite(
                ByteBufCodecs.STRING_UTF8,
                AngelBattleMusicPacket::soundEventId,
                ByteBufCodecs.VAR_INT,
                AngelBattleMusicPacket::angelEntityId,
                AngelBattleMusicPacket::new
        );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}

