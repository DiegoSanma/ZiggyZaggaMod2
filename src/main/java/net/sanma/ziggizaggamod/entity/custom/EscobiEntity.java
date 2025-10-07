package net.sanma.ziggizaggamod.entity.custom;

import net.minecraft.client.model.BlazeModel;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.renderer.entity.BlazeRenderer;
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

public class EscobiEntity extends FlyingMob implements  Enemy {
    public static final AnimationState idleAnimation = new AnimationState();
    public static final AnimationState attackAnimation = new AnimationState();
    private int idleAnimationTimeout = 0;
    private int attackAnimationtimeout = -1;
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
        //this.goalSelector.addGoal(6, new EscobiLookGoal(this));
        //this.goalSelector.addGoal(6,new LookAtPlayerGoal(this,Player.class,20));
        this.goalSelector.addGoal(2,new EscobiRangedAttackGoal(this));
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
        if(this.isAttacking() && attackAnimationtimeout==-1){
            this.attackAnimationtimeout = 80;
            this.attackAnimation.start(this.tickCount);
        }
        if(attackAnimationtimeout>0){
            attackAnimationtimeout--;
            if(attackAnimationtimeout==0){
                attackAnimation.stop();
                attackAnimationtimeout=-1;
            }
        }
    }

    private void shootFireball(double x, double y, double z) {
        // Posición de inicio: el centro del mob, un poco más arriba
        double startX = this.getX();
        double startY = this.getY(0.5D) + 0.5D; // altura del pecho o cabeza
        double startZ = this.getZ();

        // Vector hacia el target
        double dx = x - startX;
        double dy = y - startY;
        double dz = z - startZ;

        // Crea el vector dirección normalizado
        Vec3 direction = new Vec3(dx, dy, dz).normalize();


        // Crear fireball (puedes usar SmallFireball o LargeFireball)
        SmallFireball fireball = new SmallFireball(
                this.level(),
                this,
                direction// Shooter// Dirección de movimiento
        );

        fireball.setPos(startX, startY, startZ); // posición inicial del disparo


        // Agregar la entidad al mundo
        this.level().addFreshEntity(fireball);
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
        //this.setAggressive(this.getTarget() != null);
        if (this.level().isClientSide) {
            setAnimationState();
        }
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
            LivingEntity target = this.escobi.getTarget();
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

    static class EscobiRangedAttackGoal extends Goal{

        public int loadtime;
        private final EscobiEntity escobi;
        private boolean hasShot;
        public double targetX;
        public double targetY;
        public double targetZ;
        public EscobiRangedAttackGoal(EscobiEntity escobi) {
            this.escobi = escobi;
            this.hasShot = false;
            this.setFlags(EnumSet.of(Goal.Flag.LOOK));
        }

        public boolean canUse() {return this.escobi.getTarget() != null;}

        @Override
        public void start() {
            //escobi.attackAnimation.start(escobi.tickCount);
            this.escobi.setAttacking(true);
            this.targetX = this.escobi.getTarget().getX();
            this.targetY = this.escobi.getTarget().getY();
            this.targetZ = this.escobi.getTarget().getZ();
            this.loadtime = 80;
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }

        @Override
        public void stop() {
            this.escobi.setAttacking(false);
            this.loadtime=0;
            this.hasShot = false;
            super.stop();
        }

        @Override
        public void tick() {
            LivingEntity livingentity = this.escobi.getTarget();
            if (livingentity != null) {
                loadtime--;
                if(loadtime >45) {
                    this.escobi.getLookControl().setLookAt(this.targetX, this.targetY, this.targetZ);
                } else if ((loadtime==45)) {
                    escobi.shootFireball(this.targetX,this.targetY,this.targetZ);
                    this.hasShot = true;
                }
                if(this.hasShot){
                    this.escobi.getLookControl().setLookAt(livingentity,60.0F,60.0F);
                    if(loadtime<=0) {
                        this.hasShot = false;
                        loadtime = 80;
                        this.targetX = livingentity.getX();
                        this.targetY = livingentity.getY();
                        this.targetZ = livingentity.getZ();
                    }
                }
            }
        }
    }
}

