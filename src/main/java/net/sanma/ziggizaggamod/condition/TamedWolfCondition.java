package net.sanma.ziggizaggamod.condition;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;


public class TamedWolfCondition implements LootItemCondition {

    // Codec simple, sin parámetros
    public static final MapCodec<TamedWolfCondition> MAP_CODEC = MapCodec.unit(TamedWolfCondition::new);
    public static LootItemConditionType TYPE;
    @Override
    public boolean test(LootContext context) {
        // Obtenemos la entidad desde el contexto
        if (context.getParameter(LootContextParams.THIS_ENTITY) instanceof Wolf wolf) {
            return wolf.isTame(); // Solo lobos domesticados
        }
        return false;
    }

    @Override
    public LootItemConditionType getType() {
        // Registro usando BuiltinRegistries
        return TYPE;
    }
}
