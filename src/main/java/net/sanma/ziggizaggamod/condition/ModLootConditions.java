package net.sanma.ziggizaggamod.condition;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.sanma.ziggizaggamod.ZiggiZaggaMod;

public class ModLootConditions {

    //public static final DeferredRegister<LootItemConditionType> conditions = DeferredRegister.(ZiggiZaggaMod.MODID);

    public static void register() {
        TamedWolfCondition.TYPE = new LootItemConditionType(TamedWolfCondition.MAP_CODEC);
        // Ya está listo para usar en datagen

    }
}
