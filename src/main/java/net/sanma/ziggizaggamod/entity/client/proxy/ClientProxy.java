package net.sanma.ziggizaggamod.entity.client.proxy;

import net.minecraft.client.Minecraft;
import net.sanma.ziggizaggamod.common.CommonProxy;
import net.sanma.ziggizaggamod.entity.custom.AngelEntity;
import net.sanma.ziggizaggamod.sound.AngelBattleMusic;

import java.util.function.Supplier;

public class ClientProxy implements CommonProxy {

    @Override
    public void runOnCLient(Supplier<Runnable> supplier) {
        supplier.get().run();
    }
}
