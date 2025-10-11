package net.sanma.ziggizaggamod.entity.custom;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;
import java.util.Optional;
import java.util.Random;


public class AngelEntity extends Monster implements Enemy {
    public static final AnimationState idleAnimation = new AnimationState();
    private int idleAnimationTimeout = 0;
    public static final AnimationState attack1Animation = new AnimationState();
    public static final AnimationState attack2Animation = new AnimationState();
    public static final AnimationState attack3Animation = new AnimationState();
    public static final AnimationState attack4Animation = new AnimationState();
    public static int attackCooldown = 0;

    private int lastAttackState = -1;

    private static final EntityDataAccessor<Integer> ATTACK_STATE =
            SynchedEntityData.defineId(AngelEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> ATTACK_COOLDOWN =
            SynchedEntityData.defineId(AngelEntity.class, EntityDataSerializers.BOOLEAN);

    public AngelEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new FlyingMoveControl(this,10,false);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder p_326499_) {
        super.defineSynchedData(p_326499_);
        p_326499_.define(ATTACK_STATE, 0); // 0 = sin ataque
        p_326499_.define(ATTACK_COOLDOWN,false); // Cooldown de lightinng
    }
    public int getAttackState(){
        return this.entityData.get(ATTACK_STATE);
    }

    public void setAttackState(int state){
        this.entityData.set(ATTACK_STATE, state);
    }

    public boolean getAttackCooldown(){
        return this.entityData.get(ATTACK_COOLDOWN);
    }

    public void setAttackCooldown(boolean cooldown){
        this.entityData.set(ATTACK_COOLDOWN, cooldown);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0,new FloatGoal(this));
        this.goalSelector.addGoal(2,new DualAngelAttackGoal(this));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomFlyingGoal(this,1.0));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 16.0F));

        this.targetSelector.addGoal(1,new NearestAttackableTargetGoal<>(this, Player.class, true));
    }
    private void setAnimationState() {
        int currentState = this.getAttackState();

        // Solo cambiar animación si el estado realmente cambió
        if (currentState != lastAttackState) {
            lastAttackState = currentState;

            switch (currentState) {
                case 0 -> idleAnimation.start(this.tickCount);
                case 1 -> attack1Animation.start(this.tickCount);
                case 2 -> attack2Animation.start(this.tickCount);
                case 3 -> attack3Animation.start(this.tickCount);
                case 4 -> attack4Animation.start(this.tickCount);
            }
        }
    }

    protected PathNavigation createNavigation(Level p_186262_) {
        FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, p_186262_);
        flyingpathnavigation.setCanOpenDoors(false);
        flyingpathnavigation.setCanFloat(true);
        return flyingpathnavigation;
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 2000.0D)
                .add(Attributes.ATTACK_DAMAGE, 15.0D)
                .add(Attributes.ATTACK_KNOCKBACK,4.0D)
                .add(Attributes.FLYING_SPEED, 2.0D)
                .add(Attributes.FOLLOW_RANGE, 48.0D);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) {
            setAnimationState();
        }
    }


    @Override
    public void aiStep() {
        // Movimiento base con ligera fricción vertical
        Vec3 vec3 = this.getDeltaMovement().multiply(1.0F, 0.8F, 1.0F);
        Entity entity = this.getTarget();
        double d0 = vec3.y;
        if (!this.level().isClientSide && entity != null) {
            if (this.getY() < entity.getY() ||this.getY() < entity.getY() + (double)5.0F) {
                d0 = Math.max((double)0.0F, d0);
                d0 += 0. - d0 * (double)0.6F;
            }
            vec3 = new Vec3(vec3.x, d0, vec3.z);
            //Dos casos, lightning charge ya realizado y mientras carga
            //Primero, me acerco bruscamente a el jugador
            Vec3 vec31 = new Vec3(entity.getX() - this.getX(), (double) 0.0F, entity.getZ() - this.getZ());
            if (vec31.horizontalDistanceSqr() > (double) 5.0F) {
                Vec3 vec32 = vec31.normalize();
                vec3 = vec3.add(vec32.x * 0.3 - vec3.x * 0.6, (double) 0.0F, vec32.z * 0.3 - vec3.z * 0.6);
            }
            //Rodeo al jugador
            if(!this.getAttackCooldown()){
                double radius = 3.5 + this.getRandom().nextDouble() * 1.5; // 3.5-5 bloques
                double angle = this.tickCount * 0.05; // girando suavemente alrededor
                double offsetX = Math.cos(angle) * radius;
                double offsetZ = Math.sin(angle) * radius;
                vec3 = vec3.add(offsetX, 1.0D, offsetZ);
                vec3 = vec3.scale(0.1D);
            }
        }

        // Aplicar movimiento
        this.setDeltaMovement(vec3);
        // Rotación del mob hacia la dirección del movimiento
        super.aiStep();
    }

    public void LightningAttack(LivingEntity target) {
        if (target == null || level().isClientSide()) return; // solo en servidor

        ServerLevel serverLevel = (ServerLevel) level();
        RandomSource random = level().getRandom();

        for (int i = 0; i < 5; i++) {
            LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(this.level(),EntitySpawnReason.TRIGGERED);
            if (lightning != null) {
                // Posición del target con pequeño desplazamiento aleatorio
                double offsetX = (random.nextDouble() - 0.5) * 1.0; // ±0.5 bloques X
                double offsetZ = (random.nextDouble() - 0.5) * 1.0; // ±0.5 bloques Z
                Vec3 pos = target.position().add(offsetX, 0, offsetZ);

                lightning.moveTo(pos.x, pos.y, pos.z);
                serverLevel.addFreshEntity(lightning);
                // Efecto de daño adicional o sonido opcional
                target.level().playSound(
                        null,
                        target.blockPosition(),
                        SoundEvents.LIGHTNING_BOLT_THUNDER,
                        SoundSource.WEATHER,
                        2.0F,
                        1.0F
                );
            }
        }

    }

    public class DualAngelAttackGoal extends Goal {
        private final AngelEntity angel;
        private int lightingCharge;
        private int motionTimeout;
        private int attackSpeed;

        public DualAngelAttackGoal(AngelEntity angel) {
            this.angel = angel;
            this.lightingCharge = 0;
            this.motionTimeout = 0;
            this.attackSpeed = -1;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            LivingEntity target = angel.getTarget();
            return target != null && target.isAlive();
        }

        @Override
        public void tick() {
            LivingEntity target = angel.getTarget();
            if (target == null) return;

            angel.getLookControl().setLookAt(target);
            lightingCharge--;
            if (lightingCharge == 20) {
                angel.setAttackCooldown(false);
            }
            if (this.motionTimeout > 0) {
                this.motionTimeout--;
                if (motionTimeout == this.attackSpeed) {
                    switch (angel.getAttackState()) {
                        case 1 -> {
                            angel.LightningAttack(target);
                        }
                        case 2 -> {
                            System.out.println("Spawning minions");
                        }
                        case 3, 4 -> {
                            this.WingAttack(target);
                        }
                    }
                }
                if (this.motionTimeout == 0) {
                    angel.setAttackState(0);
                }
            } else {
                double distance = angel.distanceToSqr(target);
                // Ataque a distancia si está lejos
                if (distance > 36 && lightingCharge <= 0) {
                    angel.setAttackState(1);
                    angel.setAttackCooldown(true);
                    this.motionTimeout = 40;
                    this.attackSpeed = 20;
                    lightingCharge = 180; // 9 segundos
                }
                // Ataque cuerpo a cuerpo si está cerca
                else if (distance <= 36 && angel.distanceTo(target) < angel.getBbWidth() + 2.0D) {
                    angel.setAttackState(angel.random.nextInt(3, 5));
                    this.motionTimeout = 30;
                    this.attackSpeed = 15;
                }
            }
        }

        public void WingAttack(LivingEntity target) {
            Vec3 direction = target.position().subtract(angel.position()).normalize();
            direction = direction.scale(3.0);
            if (angel.level() instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(
                        ParticleTypes.SWEEP_ATTACK,
                        target.getX() - direction.x, target.getY() + 2 - direction.y, target.getZ() - direction.z,
                        20,      // cantidad
                        0.5, 0.5, 0.5, // offsets aproximando dirección
                        0.0     // velocidad extra
                );
            }
            angel.doHurtTarget(getServerLevel(this.angel), target);
        }
    }

}