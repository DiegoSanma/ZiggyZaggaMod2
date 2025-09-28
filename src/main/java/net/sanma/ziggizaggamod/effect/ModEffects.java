package net.sanma.ziggizaggamod.effect;


import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.sanma.ziggizaggamod.ZiggiZaggaMod;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, ZiggiZaggaMod.MODID);

    public static final Holder<MobEffect> SPIDERMAN_EFFECT = MOB_EFFECTS.register("spiderman",
            () -> new SpidermanEffect(MobEffectCategory.NEUTRAL, 0x36ebab)
                    .addAttributeModifier(Attributes.SAFE_FALL_DISTANCE,
                            ResourceLocation.fromNamespaceAndPath(ZiggiZaggaMod.MODID,"spiderman"),Integer.MAX_VALUE
                    , AttributeModifier.Operation.ADD_VALUE));


    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
