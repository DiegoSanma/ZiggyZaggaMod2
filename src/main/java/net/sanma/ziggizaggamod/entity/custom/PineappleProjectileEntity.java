package net.sanma.ziggizaggamod.entity.custom;

import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.sanma.ziggizaggamod.entity.ModEntity;
import net.sanma.ziggizaggamod.items.ModItems;
import net.minecraft.world.phys.Vec2;

import javax.annotation.Nullable;

public class PineappleProjectileEntity extends AbstractArrow {
    private float rotation;
    public Vec2 groundedOffset;
    public PineappleProjectileEntity(EntityType<? extends AbstractArrow> p_331098_, Level p_331626_) {
        super(p_331098_, p_331626_);
    }

    public PineappleProjectileEntity(LivingEntity shooter, Level level) {
        super(ModEntity.PINEAPPLE_PROJECTILE.get(), shooter,level,new ItemStack(ModItems.PINEAPPLE.get()),null);
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(ModItems.PINEAPPLE.get());
    }

    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.HONEY_BLOCK_HIT;
    }

    @Override
    public void setSoundEvent(SoundEvent soundEvent) {
    }

    public float getRenderingRotation() {
        rotation += 0.5f;
        if(rotation >= 360) {
            rotation = 0;
        }
        return rotation;
    }



    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), 4);

        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
            int dropCount = this.level().random.nextIntBetweenInclusive(2, 4);

            for (int i = 0; i < dropCount; i++) {
                this.level().addFreshEntity(new ItemEntity(
                        this.level(),
                        this.getX(), this.getY(), this.getZ(),
                        new ItemStack(ModItems.PINEAPPLE_SLICE.get()) // tu ítem a soltar
                ));
            }
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        if (!this.level().isClientSide) {
            this.level().addFreshEntity(new ItemEntity(
                    this.level(),
                    this.getX(), this.getY(), this.getZ(),
                    new ItemStack(ModItems.PINEAPPLE.get()) // el ítem que quieras soltar
            ));
            this.discard(); // elimina el proyectil
        }
    }
}
