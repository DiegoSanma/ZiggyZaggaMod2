package net.sanma.ziggizaggamod.capability;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import java.util.Random;

public class TenkaichiItem extends HeroItem{

    public TenkaichiItem(Properties properties) {
        super(properties);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        Player player = (Player) slotContext.entity();
        if (player.tickCount % 20 == 0) { // 20 ticks = 1 segundo}
            Random rand = new Random();
            if (player.totalExperience >= 10){
                player.giveExperiencePoints(rand.nextInt(20)-10);
            }
        }
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        Player player = (Player) slotContext.entity();

        player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(player.getMaxHealth()+4);
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        Player player = (Player) slotContext.entity();

        player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(player.getMaxHealth()-4);
    }
}
