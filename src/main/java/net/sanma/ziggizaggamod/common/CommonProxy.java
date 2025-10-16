package net.sanma.ziggizaggamod.common;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.loading.FMLEnvironment;
import net.sanma.ziggizaggamod.entity.client.proxy.ClientProxy;
import net.sanma.ziggizaggamod.entity.custom.AngelEntity;
import net.sanma.ziggizaggamod.sound.AngelBattleMusic;

import java.util.function.Supplier;

public interface CommonProxy {
    CommonProxy INSTANCE = make();

    private static CommonProxy make() {
        if (FMLEnvironment.dist.isClient()) {
            return new ClientProxy();
        } else {
            return new CommonProxy() {};
        }
    }

    default void runOnCLient(Supplier<Runnable> supplier) {}
}
