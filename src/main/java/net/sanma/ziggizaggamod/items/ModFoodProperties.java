package net.sanma.ziggizaggamod.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

import java.util.List;

public class ModFoodProperties {
    public static final FoodProperties PINEAPPLE =
            new FoodProperties.Builder().nutrition(3).saturationModifier(3.0f).build();

    public static final FoodProperties PINEAPPLE_SLICE =
            new FoodProperties.Builder().nutrition(2).saturationModifier(2.0f).build();

    public static final FoodProperties EMPANADA =
            new FoodProperties.Builder().nutrition(5).saturationModifier(1.0f).build();

    public static final FoodProperties GRAPE =
            new FoodProperties.Builder().nutrition(1).saturationModifier(0.1f).build();

    public static final FoodProperties ALCOHOL =
            new FoodProperties.Builder().nutrition(1).saturationModifier(0.0f).build();

    public static final Consumable TERREMOTO_EFFECT = Consumables.defaultDrink().onConsume(
            new ApplyStatusEffectsConsumeEffect(List.of(new MobEffectInstance(MobEffects.CONFUSION,1000,8),
            new MobEffectInstance(MobEffects.DAMAGE_BOOST,1000,8)))).build();

    public static final Consumable PIPENO_EFFECT = Consumables.defaultDrink().onConsume(
            new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.CONFUSION,1000,8),5.0f)).build();

    public static final Consumable GRANADINA_EFFECT = Consumables.defaultDrink().onConsume(
            new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,1000,5),1.0f)).build();
}
