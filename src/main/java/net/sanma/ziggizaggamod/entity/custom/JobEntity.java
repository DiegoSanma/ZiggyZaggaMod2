package net.sanma.ziggizaggamod.entity.custom;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class JobEntity extends PathfinderMob {
    public static final AnimationState attackAnimation = new AnimationState();
    private int attackAnimationtimeout = -1;
    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(JobEntity.class, EntityDataSerializers.BOOLEAN);

    public JobEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType,level);
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
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, (double)1.0F));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(4,new JobAttackGoal(this, 1.0D, true));
        this.targetSelector.addGoal(1,new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2,new NearestAttackableTargetGoal(this, Villager.class,10, true,false,
                (livingEntity, serverLevel) -> {
                    if (livingEntity instanceof Villager villager) {
                        return villager.getVillagerData().getProfession() == VillagerProfession.NONE;
                    }
                    return false;
                }));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.5D)
                .add(Attributes.JUMP_STRENGTH,0.75D)
                .add(Attributes.ATTACK_DAMAGE,10.0D);
    }

    private void setAnimationState() {
        if(this.isAggressive() && this.attackAnimationtimeout==-1) {
            attackAnimationtimeout = 80;
            attackAnimation.start(this.tickCount);
        }
        if(this.attackAnimationtimeout>0){
            this.attackAnimationtimeout--;
        } else if (this.attackAnimationtimeout==0) {
            if(this.isAggressive()) {
                attackAnimationtimeout+=80;
            }
            else {
                attackAnimation.stop();
                attackAnimationtimeout=-1;
            }
        }
    }
    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) {
            setAnimationState();
        }
    }

    public class JobAttackGoal extends MeleeAttackGoal {
        private final JobEntity job;
        public JobAttackGoal(JobEntity mob, double speed, boolean follow) {
            super(mob,speed,follow);
            this.job = mob;
        }

        @Override
        public void start() {
            super.start();
        }

        @Override
        public void stop() {
            super.stop();
        }
    }
}
