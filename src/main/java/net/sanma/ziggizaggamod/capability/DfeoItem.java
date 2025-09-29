package net.sanma.ziggizaggamod.capability;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.sanma.ziggizaggamod.effect.ModEffects;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class DfeoItem extends HeroItem {

    public DfeoItem(Properties properties) {
        super(properties);
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        Player player = (Player) slotContext.entity();

        player.addEffect(new MobEffectInstance(ModEffects.SPIDERMAN_EFFECT, -1,0,true,false));
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        Player player = (Player) slotContext.entity();

        player.removeEffect(ModEffects.SPIDERMAN_EFFECT);
    }
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if(Screen.hasShiftDown()){
            tooltipComponents.add(Component.translatable("tooltip.ziggizaggamod.dfeo.tooltip"));
        }else {
            tooltipComponents.add(Component.translatable("tooltip.ziggizaggamod.heroitem.tooltip"));
        }
    }
}
