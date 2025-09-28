package net.sanma.ziggizaggamod.capability;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

public class TenkaichiItem extends HeroItem{

    public TenkaichiItem(Properties properties) {
        super(properties);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        Player player = (Player) slotContext.entity();
        if (player.tickCount % 20 == 0) { // 20 ticks = 1 segundo
            player.giveExperiencePoints(1);
        }
    }

}
