package net.sanma.ziggizaggamod.capability;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class SanmaItem extends HeroItem {

    public SanmaItem(Properties properties) {
        super(properties);
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        Player player = (Player) slotContext.entity();

        player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, -1,0,true,false));
        player.getAttribute(Attributes.SCALE).setBaseValue(0.5);
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        Player player = (Player) slotContext.entity();

        player.removeEffect(MobEffects.NIGHT_VISION);
        player.getAttribute(Attributes.SCALE).setBaseValue(1.0);
    }
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if(Screen.hasShiftDown()){
            tooltipComponents.add(Component.translatable("tooltip.ziggizaggamod.sanma.tooltip"));
        }else {
            tooltipComponents.add(Component.translatable("tooltip.ziggizaggamod.heroitem.tooltip"));
        }
    }
}
