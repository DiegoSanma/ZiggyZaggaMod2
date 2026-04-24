package net.sanma.ziggizaggamod.effect;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

import net.minecraft.world.phys.Vec3;

public class SpidermanEffect extends MobEffect {
    public SpidermanEffect(MobEffectCategory effectCategoryIn, int colorIn) {
        super(effectCategoryIn, colorIn);
    }

    @Override
    public boolean applyEffectTick(ServerLevel level, LivingEntity livingEntity, int amplifier) {

        if(livingEntity.horizontalCollision) {
            Vec3 motion = livingEntity.getDeltaMovement();
            livingEntity.setDeltaMovement(motion.x, 0.2, motion.z);
            livingEntity.hurtMarked = true;
            return true;
        }



        return super.applyEffectTick(level, livingEntity, amplifier);
    }


    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
