package net.sanma.ziggizaggamod.entity.custom;

import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import javax.annotation.Nullable;
import java.util.EnumSet;

public class EscobiEntity extends FlyingMob implements  RangedAttackMob {
    public static final AnimationState idleAnimation = new AnimationState();
    public static final AnimationState attackAnimation = new AnimationState();
    private int idleAnimationTimeout = 0;
    private int attackAnimationTimeout = 0;
    private int explosionPower = 5;
    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(EscobiEntity.class, EntityDataSerializers.BOOLEAN);

    public EscobiEntity(EntityType<? extends FlyingMob> entityType, Level level) {
        super(entityType, level);
        //this.moveControl = new EscobiEntityMoveControl(this);
        this.moveControl = new FlyingMoveControl(this,60,true);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(5,new RandomFloatAroundGoal(this));
        this.goalSelector.addGoal(6, new EscobiLookGoal(this));
        this.goalSelector.addGoal(2,new EscobiRangedAttackGoal(this, 1.0D, 20, 10.0F));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, true));

        //this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        //this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, Player.class, 10, true, false, (p_390241_, p_390242_) -> Math.abs(p_390241_.getY() - this.getY()) <= (double) 4.0F));
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 50.0D)
                .add(Attributes.FLYING_SPEED, 0.01D);
    }

    private void setAnimationState() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 80;
            this.idleAnimation.start(this.tickCount);
        } else {
            this.idleAnimationTimeout = this.idleAnimationTimeout - 1;
        }
        if(this.isAttacking() && attackAnimationTimeout <= 0){
            this.attackAnimationTimeout = 80;
            this.attackAnimation.start(this.tickCount);
        }
        else{
            --this.attackAnimationTimeout;
        }
        if(!(this.isAttacking())){
            this.attackAnimation.stop();
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder p_326499_) {
        super.defineSynchedData(p_326499_);
        p_326499_.define(ATTACKING, false);
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    private void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }
    @Override
    public void tick() {
        super.tick();
        this.setAggressive(this.getTarget() != null);
        if (this.level().isClientSide) {
            setAnimationState();
        }
    }


    @Override
    public void performRangedAttack(LivingEntity target, float distanceFactor) {
        double dX = target.getX() - this.getX();
        double dY = target.getY(0.5D) - (this.getY(0.5D));
        double dZ = target.getZ() - this.getZ();

        // crea la bola de fuego
        LargeFireball fireball = new LargeFireball(
                this.level(),
                this,             // owner
                new Vec3(dX, dY, dZ),       // dirección
                1                 // explosion power (1 = blaze, 3 = escobi)
        );

        fireball.setPos(
                this.getX(),
                this.getEyeY() - 0.1D,
                this.getZ()
        );

        this.level().addFreshEntity(fireball);
    }

    static class RandomFloatAroundGoal extends Goal {
        private final EscobiEntity escobi;

        public RandomFloatAroundGoal(EscobiEntity escobi) {
            this.escobi = escobi;
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        public boolean canUse() {
            MoveControl movecontrol = this.escobi.getMoveControl();
            if (!movecontrol.hasWanted()) {
                return true;
            } else {
                double d0 = movecontrol.getWantedX() - this.escobi.getX();
                double d1 = movecontrol.getWantedY() - this.escobi.getY();
                double d2 = movecontrol.getWantedZ() - this.escobi.getZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                return d3 < (double) 1.0F || d3 > (double) 3600.0F;
            }
        }

        public boolean canContinueToUse() {
            return false;
        }

        public void start() {
            RandomSource randomsource = this.escobi.getRandom();
            double d0 = this.escobi.getX() + (double) ((randomsource.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d1 = this.escobi.getY() + (double) ((randomsource.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d2 = this.escobi.getZ() + (double) ((randomsource.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.escobi.getMoveControl().setWantedPosition(d0, d1, d2, (double) 1.0F);
        }
    }

    static class EscobiLookGoal extends Goal {
        private final EscobiEntity escobi;
        public EscobiLookGoal(EscobiEntity escobi) {
            this.escobi = escobi;
            this.setFlags(EnumSet.of(Goal.Flag.LOOK));
        }

        public boolean canUse() {
            return true;
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {
            if (this.escobi.getTarget() == null) {
                Vec3 vec3 = this.escobi.getDeltaMovement();
                this.escobi.setYRot(-((float) Mth.atan2(vec3.x, vec3.z)) * (45F / (float) Math.PI));
                this.escobi.yBodyRot = this.escobi.getYRot();
            } else {
                LivingEntity livingentity = this.escobi.getTarget();
                double d0 = (double) 64.0F;
                if (livingentity.distanceToSqr(this.escobi) < (double) 4096.0F) {
                    double d1 = livingentity.getX() - this.escobi.getX();
                    double d2 = livingentity.getZ() - this.escobi.getZ();
                    this.escobi.setYRot(-((float) Mth.atan2(d1, d2)) * (45F / (float) Math.PI));
                    this.escobi.yBodyRot = this.escobi.getYRot();
                }
            }
        }

    }

    static class EscobiEntityMoveControl extends MoveControl {
        private final EscobiEntity escobi;
        private int floatDuration;

        public EscobiEntityMoveControl(EscobiEntity escobi) {
            super(escobi);
            this.escobi = escobi;
        }

        public void tick() {
            if (this.operation == Operation.MOVE_TO && this.floatDuration-- <= 0) {
                this.floatDuration = this.floatDuration + this.escobi.getRandom().nextInt(5) + 2;
                Vec3 vec3 = new Vec3(this.wantedX - this.escobi.getX(), this.wantedY - this.escobi.getY(), this.wantedZ - this.escobi.getZ());
                double d0 = vec3.length();
                vec3 = vec3.normalize();
                if (this.canReach(vec3, Mth.ceil(d0))) {
                    this.escobi.setDeltaMovement(this.escobi.getDeltaMovement().add(vec3.scale(0.3)));
                } else {
                    this.operation = Operation.WAIT;
                }
            }

        }

        private boolean canReach(Vec3 pos, int length) {
            AABB aabb = this.escobi.getBoundingBox();

            for(int i = 1; i < length; ++i) {
                aabb = aabb.move(pos);
                if (!this.escobi.level().noCollision(this.escobi, aabb)) {
                    return false;
                }
            }

            return true;
        }
    }

    static class EscobiRangedAttackGoal extends RangedAttackGoal{

        private final EscobiEntity escobi;
        public EscobiRangedAttackGoal(RangedAttackMob rangedAttackMob, double speedModifier, int attackInterval, float attackRadius) {
            super(rangedAttackMob, speedModifier, attackInterval, attackRadius);
            escobi = (EscobiEntity) rangedAttackMob;
        }

        @Override
        public void start() {
            super.start();
            this.escobi.setAttacking(true);
        }

        @Override
        public void stop() {
            this.escobi.setAttacking(false);
            super.stop();
        }
    }
}

