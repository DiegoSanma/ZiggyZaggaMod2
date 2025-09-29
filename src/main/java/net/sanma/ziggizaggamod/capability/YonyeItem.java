package net.sanma.ziggizaggamod.capability;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

public class YonyeItem extends HeroItem{

    public YonyeItem(Properties properties) {
        super(properties);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity entity = slotContext.entity();

        entity.addEffect(new MobEffectInstance(MobEffects.HUNGER, -1));
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        LivingEntity entity = slotContext.entity();

        entity.getAttribute(Attributes.ATTACK_KNOCKBACK).setBaseValue(5);
        entity.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(5);
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        LivingEntity entity = slotContext.entity();

        entity.getAttribute(Attributes.ATTACK_KNOCKBACK).setBaseValue(0);
        entity.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(2);

        entity.removeEffect(MobEffects.HUNGER);
    }
}
