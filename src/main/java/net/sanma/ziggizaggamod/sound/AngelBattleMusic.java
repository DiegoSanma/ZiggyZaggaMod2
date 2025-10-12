package net.sanma.ziggizaggamod.sound;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundSource;
import net.sanma.ziggizaggamod.entity.custom.AngelEntity;

import javax.swing.text.html.parser.Entity;

public class AngelBattleMusic  extends AbstractTickableSoundInstance {
    private final AngelEntity entity;
    public AngelBattleMusic(AngelEntity entity) {
        super(ModSounds.ANGEL_MUSIC.get(), SoundSource.HOSTILE, SoundInstance.createUnseededRandom());
        this.entity = entity;
        this.looping = true;
        this.delay = 0;
        this.volume = 1.0F;
        System.out.println("Se creo la instancia");
    }

    public static void play(AngelEntity entity) {
        Minecraft.getInstance().getSoundManager().play(new AngelBattleMusic(entity));
        System.out.println("Music playing");
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
