package net.sanma.ziggizaggamod.capability;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class GaboItem extends HeroItem {

    public GaboItem(Properties properties) {
        super(properties);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity entity = slotContext.entity();
        if (entity.hasEffect(MobEffects.POISON)) {
            entity.removeEffect(MobEffects.POISON);
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if(Screen.hasShiftDown()){
            tooltipComponents.add(Component.translatable("tooltip.ziggizaggamod.gabo.tooltip"));
        }else {
            tooltipComponents.add(Component.translatable("tooltip.ziggizaggamod.heroitem.tooltip"));
        }
    }
}
