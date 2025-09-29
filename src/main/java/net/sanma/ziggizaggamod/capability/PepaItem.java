package net.sanma.ziggizaggamod.capability;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

public class PepaItem extends HeroItem{

    public PepaItem(Properties properties) {
        super(properties);
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        Player player = (Player) slotContext.entity();

        player.setInvulnerable(true);
        player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(4);
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        Player player = (Player) slotContext.entity();

        player.setInvulnerable(false);
        player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20);
    }
}
