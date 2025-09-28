package net.sanma.ziggizaggamod.capability;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

public class MiguelItem extends HeroItem{

    public MiguelItem(Properties properties){
        super(properties);
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        Player player = (Player) slotContext.entity();

        player.addEffect(new MobEffectInstance(MobEffects.LUCK, Integer.MAX_VALUE,5,true,false));
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        Player player = (Player) slotContext.entity();

        player.removeEffect(MobEffects.LUCK);
    }
}
