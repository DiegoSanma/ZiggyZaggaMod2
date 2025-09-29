package net.sanma.ziggizaggamod.capability;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class DonvitoItem extends HeroItem{

    public DonvitoItem(Properties properties) {
        super(properties);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        Player player = (Player) slotContext.entity();

        player.getFoodData().setFoodLevel(20);
        if(player.tickCount % 60 == 0){
            float current_saturation = player.getFoodData().getSaturationLevel();
            if (current_saturation < 20.0f) {
                player.getFoodData().setSaturation(current_saturation + 1);
            }
        }
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        LivingEntity entity = slotContext.entity();
        entity.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.08);

    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        LivingEntity entity = slotContext.entity();
        entity.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.1);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if(Screen.hasShiftDown()){
            tooltipComponents.add(Component.translatable("tooltip.ziggizaggamod.donvito.tooltip"));
        }else {
            tooltipComponents.add(Component.translatable("tooltip.ziggizaggamod.heroitem.tooltip"));
        }
    }
}
