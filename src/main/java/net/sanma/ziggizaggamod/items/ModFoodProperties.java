package net.sanma.ziggizaggamod.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

public class ModFoodProperties {
    public static final FoodProperties PINEAPPLE_SLICE =
            new FoodProperties.Builder().nutrition(2).saturationModifier(2.0f).build();

    public static final FoodProperties EMPANADA =
            new FoodProperties.Builder().nutrition(5).saturationModifier(1.0f).build();

    public static final FoodProperties GRAPE =
            new FoodProperties.Builder().nutrition(1).saturationModifier(0.1f).build();

    public static final FoodProperties TERREMOTO =
            new FoodProperties.Builder().nutrition(1).saturationModifier(0.0f).build();

    public static final Consumable TERREMOTO_EFFECT = Consumables.defaultDrink().onConsume(
            new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.CONFUSION,1000,5),5.0f)).build();
}
