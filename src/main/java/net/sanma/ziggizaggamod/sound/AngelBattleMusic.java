package net.sanma.ziggizaggamod.sound;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundSource;
import net.sanma.ziggizaggamod.entity.custom.AngelEntity;


public class AngelBattleMusic  extends AbstractTickableSoundInstance {
    private final AngelEntity entity;
    public AngelBattleMusic(AngelEntity entity) {
        super(ModSounds.ANGEL_MUSIC.get(), SoundSource.HOSTILE, SoundInstance.createUnseededRandom());
        this.entity = entity;
        this.looping = true;
        //this.delay = 0;
        this.volume = 0.4F;
        this.pitch = 0.5F;
        System.out.println("Se creo la instancia");
    }

    @Override
    public void tick() {
        System.out.println("AngelBattleMusic");
        if (!entity.isAlive()) {
            System.out.println("AngelBattleMusic is dead");
            this.stop();
        } else {
            this.x = (float) entity.getX();
            this.y = (float) entity.getY();
            this.z = (float) entity.getZ();
        }
    }
}
