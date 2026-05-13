package net.sanma.ziggizaggamod.entity.custom;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.sanma.ziggizaggamod.common.network.NeoForgePacketHandler;
import net.sanma.ziggizaggamod.sound.ModSounds;

import java.util.EnumSet;
import java.util.List;


public class AngelEntity extends Monster implements Enemy {
    public static final AnimationState idleAnimation = new AnimationState();
    public static final AnimationState attack1Animation = new AnimationState();
    public static final AnimationState attack2Animation = new AnimationState();
    public static final AnimationState attack3Animation = new AnimationState();
    public static final AnimationState attack4Animation = new AnimationState();

    private int lastAttackState = -1;
    private boolean phase2 = false;
    private boolean phase3 = false;

    // Server-side cooldown timers, ticked down in aiStep()
    int lightningCooldown = 0;
    int spawnCooldown = 0;
    int wingCooldown = 0;

    // Erratic movement state
    private int erraticTimer = 0;
    private double erraticImpulseX = 0;
    private double erraticImpulseZ = 0;

    private static final EntityDataAccessor<Integer> ATTACK_STATE =
            SynchedEntityData.defineId(AngelEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> ATTACK_COOLDOWN =
            SynchedEntityData.defineId(AngelEntity.class, EntityDataSerializers.BOOLEAN);

    private final ServerBossEvent bossEvent =
            new ServerBossEvent(Component.literal("The Fallen Angel"), BossEvent.BossBarColor.YELLOW, BossEvent.BossBarOverlay.NOTCHED_10);

    public AngelEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new FlyingMoveControl(this, 10, false);
        this.xpReward = 30000;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder p_326499_) {
        super.defineSynchedData(p_326499_);
        p_326499_.define(ATTACK_STATE, 0);
        p_326499_.define(ATTACK_COOLDOWN, false);
    }

    public int getAttackState() {
        return this.entityData.get(ATTACK_STATE);
    }

    public void setAttackState(int state) {
        this.entityData.set(ATTACK_STATE, state);
    }

    public boolean getAttackCooldown() {
        return this.entityData.get(ATTACK_COOLDOWN);
    }

    public void setAttackCooldown(boolean cooldown) {
        this.entityData.set(ATTACK_COOLDOWN, cooldown);
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    public boolean causeFallDamage(float p_147187_, float p_147188_, DamageSource p_147189_) {
        return false;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new AngelSpawnMinionsGoal(this));
        this.goalSelector.addGoal(3, new AngelLightningAttackGoal(this));
        this.goalSelector.addGoal(4, new AngelWingAttackGoal(this));
        this.goalSelector.addGoal(5, new AngelIdleGoal(this));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomFlyingGoal(this, 1.0));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 16.0F));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    private void setAnimationState() {
        int currentState = this.getAttackState();
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
                .add(Attributes.ATTACK_DAMAGE, 10.0D)
                .add(Attributes.ATTACK_KNOCKBACK, 4.0D)
                .add(Attributes.FLYING_SPEED, 0.25D)
                .add(Attributes.MOVEMENT_SPEED, 0.01D)
                .add(Attributes.FOLLOW_RANGE, 64.0D);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) {
            setAnimationState();
        }
    }

    // Sets head look and body yaw toward target each goal tick, overriding
    // the movement-based body rotation that orbital movement would otherwise cause.
    void faceTarget(LivingEntity target) {
        this.getLookControl().setLookAt(target);
        double dx = target.getX() - this.getX();
        double dz = target.getZ() - this.getZ();
        this.setYBodyRot((float)(Math.toDegrees(Math.atan2(dz, dx))) - 90.0F);
    }

    @Override
    public void aiStep() {
        Vec3 vec3 = this.getDeltaMovement().multiply(0.91F, 0.85F, 0.91F);
        Entity target = this.getTarget();

        if (!this.level().isClientSide && target != null) {
            double toTargetX = target.getX() - this.getX();
            double toTargetZ = target.getZ() - this.getZ();
            double horizDist = Math.sqrt(toTargetX * toTargetX + toTargetZ * toTargetZ);

            // Two incommensurate sine waves on radius and a wobbling orbit angle make
            // the path never a clean circle, while periodic random lurches add erratic bursts.
            double desiredRadius = 22.0 + Math.sin(this.tickCount * 0.013) * 5.0
                                        + Math.sin(this.tickCount * 0.031) * 3.0;
            double desiredHeight = 9.0 + Math.sin(this.tickCount * 0.019) * 3.0
                                       + Math.sin(this.tickCount * 0.011) * 2.0;
            double orbitAngle = this.tickCount * 0.04 + Math.sin(this.tickCount * 0.007) * 3.0;

            double desiredX = target.getX() + Math.cos(orbitAngle) * desiredRadius;
            double desiredY = target.getY() + desiredHeight;
            double desiredZ = target.getZ() + Math.sin(orbitAngle) * desiredRadius;

            // Periodic random lurch: every 40-100 ticks pick a new impulse direction
            erraticTimer--;
            if (erraticTimer <= 0) {
                erraticTimer = 40 + this.random.nextInt(60);
                double lurchAngle = this.random.nextDouble() * Math.PI * 2;
                erraticImpulseX = Math.cos(lurchAngle) * 0.35;
                erraticImpulseZ = Math.sin(lurchAngle) * 0.35;
            }
            erraticImpulseX *= 0.92;
            erraticImpulseZ *= 0.92;

            double errX = desiredX - this.getX();
            double errY = desiredY - this.getY();
            double errZ = desiredZ - this.getZ();
            double errLen = Math.sqrt(errX * errX + errY * errY + errZ * errZ);
            if (errLen > 0.5) {
                double attract = Math.min(errLen * 0.04, 0.25);
                vec3 = vec3.add(errX / errLen * attract + erraticImpulseX,
                                errY / errLen * attract * 0.6,
                                errZ / errLen * attract + erraticImpulseZ);
            }

            double minRadius = 14.0;
            if (horizDist < minRadius && horizDist > 0.01) {
                double fleePower = (minRadius - horizDist) / minRadius * 0.55;
                vec3 = vec3.add(-toTargetX / horizDist * fleePower, 0.05, -toTargetZ / horizDist * fleePower);
            }

            double hSpeed = vec3.horizontalDistance();
            if (hSpeed > 0.65) {
                vec3 = new Vec3(vec3.x / hSpeed * 0.65, vec3.y, vec3.z / hSpeed * 0.65);
            }
            if (Math.abs(vec3.y) > 0.5) {
                vec3 = new Vec3(vec3.x, Math.copySign(0.5, vec3.y), vec3.z);
            }

            // Tick down cooldown timers; clear the lightning cooldown indicator at 20 ticks remaining
            if (lightningCooldown > 0) {
                lightningCooldown--;
                if (lightningCooldown == 20) setAttackCooldown(false);
            }
            if (spawnCooldown > 0) spawnCooldown--;
            if (wingCooldown > 0) wingCooldown--;
        }

        this.setDeltaMovement(vec3);
        super.aiStep();

        // FlyingMoveControl writes yRot from movement direction inside super.aiStep(),
        // then updateBodyRotation() copies it into yBodyRot — overriding faceTarget().
        // Re-apply the correct facing after all vanilla processing is done.
        if (!this.level().isClientSide) {
            LivingEntity lTarget = this.getTarget();
            if (lTarget != null) {
                double dx = lTarget.getX() - this.getX();
                double dz = lTarget.getZ() - this.getZ();
                float faceAngle = (float)(Math.toDegrees(Math.atan2(dz, dx))) - 90.0F;
                this.setYBodyRot(faceAngle);
                this.setYHeadRot(faceAngle);
            }
        }

        float healthRatio = this.getHealth() / this.getMaxHealth();
        this.bossEvent.setProgress(healthRatio);
        if (healthRatio <= 0.5f && !phase2) {
            phase2 = true;
            onPhaseTwoStart();
        } else if (healthRatio <= 0.25f && !phase3) {
            phase3 = true;
            onPhaseThreeStart();
        }
    }

    public void onPhaseTwoStart() {
        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(15.0D);
        if (this.level() instanceof ServerLevel serverLevel) {
            for (int i = 0; i < 5; i++) {
                double offsetX = (this.random.nextDouble() - 0.5) * 8;
                double offsetZ = (this.random.nextDouble() - 0.5) * 8;
                LightningBolt bolt = EntityType.LIGHTNING_BOLT.create(serverLevel, EntitySpawnReason.TRIGGERED);
                if (bolt != null) {
                    bolt.moveTo(this.getX() + offsetX, this.getY(), this.getZ() + offsetZ);
                    serverLevel.addFreshEntity(bolt);
                }
            }
            serverLevel.playSound(null, this.blockPosition(), ModSounds.PHASEONE.get(), SoundSource.HOSTILE);
        }
    }

    public void onPhaseThreeStart() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.06D);
        if (this.level() instanceof ServerLevel serverLevel) {
            double radius = 12.0D;
            List<Player> nearbyPlayers = serverLevel.getEntitiesOfClass(Player.class,
                    this.getBoundingBox().inflate(radius));
            for (Player player : nearbyPlayers) {
                if (this.distanceTo(player) <= radius) {
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1));
                    player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 60, 0));
                    serverLevel.sendParticles(ParticleTypes.SMOKE,
                            player.getX(), player.getY() + 1.0, player.getZ(),
                            20, 0.3, 0.3, 0.3, 0.01);
                }
            }
            serverLevel.playSound(null, this.blockPosition(), ModSounds.PHASETWO.get(), SoundSource.HOSTILE);
            spawnMinionsAttack(serverLevel, 6);
        }
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ModSounds.ANGEL_HURT.get();
    }

    @Override
    protected void playHurtSound(DamageSource source) {
        this.level().playSound(
                null,
                this.getX(), this.getY(), this.getZ(),
                this.getHurtSound(source),
                this.getSoundSource(),
                0.3F,
                1.0F
        );
    }

    public void LightningAttack(LivingEntity target) {
        if (target == null || level().isClientSide()) return;
        ServerLevel serverLevel = (ServerLevel) level();
        RandomSource random = level().getRandom();
        for (int i = 0; i < 5; i++) {
            LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(this.level(), EntitySpawnReason.TRIGGERED);
            if (lightning != null) {
                double offsetX = (random.nextDouble() - 0.5) * 1.0;
                double offsetZ = (random.nextDouble() - 0.5) * 1.0;
                Vec3 pos = target.position().add(offsetX, 0, offsetZ);
                lightning.moveTo(pos.x, pos.y, pos.z);
                serverLevel.addFreshEntity(lightning);
                target.level().playSound(null, target.blockPosition(),
                        SoundEvents.LIGHTNING_BOLT_THUNDER, SoundSource.WEATHER, 2.0F, 1.0F);
            }
        }
    }

    private void spawnMinionsAttack(ServerLevel level, int numBackups) {
        for (int i = 0; i < numBackups; i++) {
            double angle = Math.toRadians(level.random.nextInt(360));
            double radius = 6.0D + level.random.nextDouble() * 2.0D;
            double spawnX = this.getX() + Math.cos(angle) * radius;
            double spawnY = this.getY();
            double spawnZ = this.getZ() + Math.sin(angle) * radius;
            Vex vex = EntityType.VEX.create(level, EntitySpawnReason.TRIGGERED);
            if (vex != null) {
                vex.moveTo(spawnX, spawnY, spawnZ, level.random.nextFloat() * 360.0F, 0.0F);
                level.addFreshEntity(vex);
                level.sendParticles(ParticleTypes.SMOKE,
                        vex.getX(), vex.getY() + 1.0, vex.getZ(), 30, 0.5, 1.0, 0.5, 0.05);
            }
        }
        level.playSound(null, this.blockPosition(), SoundEvents.EVOKER_PREPARE_SUMMON, SoundSource.HOSTILE, 1.5F, 1.0F);
    }

    /* ------------------------------------------------------------------ */
    /*  GOALS                                                               */
    /* ------------------------------------------------------------------ */

    // Runs between attacks: faces the player and holds attackState = 0
    public class AngelIdleGoal extends Goal {
        private final AngelEntity angel;

        public AngelIdleGoal(AngelEntity angel) {
            this.angel = angel;
            this.setFlags(EnumSet.of(Goal.Flag.LOOK));
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
            angel.setAttackState(0);
            angel.faceTarget(target);
        }
    }

    // Summons vexes — highest attack priority, long cooldown
    public class AngelSpawnMinionsGoal extends Goal {
        private final AngelEntity angel;
        private int motionTimeout = 0;
        private static final int DURATION = 40;
        private static final int HIT_TICK = 20;
        private static final int COOLDOWN = 300;
        private static final int BACKUPS = 4;

        public AngelSpawnMinionsGoal(AngelEntity angel) {
            this.angel = angel;
            this.setFlags(EnumSet.of(Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            LivingEntity target = angel.getTarget();
            return target != null && target.isAlive() && angel.spawnCooldown <= 0;
        }

        @Override
        public boolean canContinueToUse() {
            return motionTimeout > 0;
        }

        @Override
        public void start() {
            angel.setAttackState(2);
            angel.setAttackCooldown(true);
            motionTimeout = DURATION;
            angel.spawnCooldown = COOLDOWN;
        }

        @Override
        public void tick() {
            LivingEntity target = angel.getTarget();
            if (target != null) angel.faceTarget(target);
            motionTimeout--;
            if (motionTimeout == HIT_TICK) {
                if (angel.level() instanceof ServerLevel serverLevel) {
                    angel.spawnMinionsAttack(serverLevel, BACKUPS);
                }
            }
        }

        @Override
        public void stop() {
            angel.setAttackState(0);
            motionTimeout = 0;
        }
    }

    // Calls lightning when the player is far away
    public class AngelLightningAttackGoal extends Goal {
        private final AngelEntity angel;
        private int motionTimeout = 0;
        private static final int DURATION = 40;
        private static final int HIT_TICK = 20;
        private static final int COOLDOWN = 180;

        public AngelLightningAttackGoal(AngelEntity angel) {
            this.angel = angel;
            this.setFlags(EnumSet.of(Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            LivingEntity target = angel.getTarget();
            return target != null && target.isAlive()
                    && angel.distanceToSqr(target) > 100
                    && angel.lightningCooldown <= 0;
        }

        @Override
        public boolean canContinueToUse() {
            return motionTimeout > 0;
        }

        @Override
        public void start() {
            angel.setAttackState(1);
            angel.setAttackCooldown(true);
            motionTimeout = DURATION;
            angel.lightningCooldown = COOLDOWN;
        }

        @Override
        public void tick() {
            LivingEntity target = angel.getTarget();
            if (target != null) angel.faceTarget(target);
            motionTimeout--;
            if (motionTimeout == HIT_TICK) {
                LivingEntity t = angel.getTarget();
                if (t != null) angel.LightningAttack(t);
            }
        }

        @Override
        public void stop() {
            angel.setAttackState(0);
            motionTimeout = 0;
        }
    }

    // Fires a wing-blast while orbiting at close range
    public class AngelWingAttackGoal extends Goal {
        private final AngelEntity angel;
        private int motionTimeout = 0;
        private Vec3 wingAttackTarget = null;
        private static final int DURATION = 30;
        private static final int HIT_TICK = 20;
        private static final int COOLDOWN = 60;

        public AngelWingAttackGoal(AngelEntity angel) {
            this.angel = angel;
            this.setFlags(EnumSet.of(Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            LivingEntity target = angel.getTarget();
            return target != null && target.isAlive() && angel.wingCooldown <= 0;
        }

        @Override
        public boolean canContinueToUse() {
            return motionTimeout > 0;
        }

        @Override
        public void start() {
            LivingEntity target = angel.getTarget();
            if (target == null) return;
            angel.setAttackState(angel.random.nextInt(3, 5));
            motionTimeout = DURATION;
            angel.wingCooldown = COOLDOWN;
            wingAttackTarget = new Vec3(target.getX(), target.getY() + target.getBbHeight() * 0.5, target.getZ());
        }

        @Override
        public void tick() {
            LivingEntity target = angel.getTarget();
            if (target != null) angel.faceTarget(target);
            motionTimeout--;
            if (motionTimeout == HIT_TICK) {
                LivingEntity t = angel.getTarget();
                if (t != null) doWingAttack(t);
            }
        }

        @Override
        public void stop() {
            angel.setAttackState(0);
            motionTimeout = 0;
            wingAttackTarget = null;
        }

        private void doWingAttack(LivingEntity target) {
            if (!(angel.level() instanceof ServerLevel serverLevel)) return;
            if (wingAttackTarget == null) return;

            Vec3 angelCenter = angel.position().add(0, angel.getBbHeight() * 0.5, 0);
            Vec3 impactPos = wingAttackTarget;
            Vec3 direction = impactPos.subtract(angelCenter).normalize();
            double distance = angelCenter.distanceTo(impactPos);

            int steps = Math.max(4, (int)(distance * 2.5));
            Vec3 perp = new Vec3(-direction.z, 0, direction.x);
            for (int j = 0; j <= steps; j++) {
                double t = (double) j / steps;
                double px = angelCenter.x + direction.x * distance * t;
                double py = angelCenter.y + direction.y * distance * t;
                double pz = angelCenter.z + direction.z * distance * t;
                serverLevel.sendParticles(ParticleTypes.CLOUD, px, py, pz, 2, 0.1, 0.2, 0.1, 0.03);
                serverLevel.sendParticles(ParticleTypes.POOF,
                        px + perp.x * 0.6, py, pz + perp.z * 0.6, 1, 0.05, 0.1, 0.05, 0.01);
                serverLevel.sendParticles(ParticleTypes.POOF,
                        px - perp.x * 0.6, py, pz - perp.z * 0.6, 1, 0.05, 0.1, 0.05, 0.01);
            }

            serverLevel.sendParticles(ParticleTypes.CLOUD,
                    impactPos.x, impactPos.y, impactPos.z, 20, 0.6, 0.6, 0.6, 0.06);
            serverLevel.sendParticles(ParticleTypes.POOF,
                    impactPos.x, impactPos.y, impactPos.z, 15, 0.5, 0.5, 0.5, 0.05);

            double hitRadius = 3.5;
            if (target.distanceToSqr(impactPos) <= hitRadius * hitRadius) {
                float baseDamage = (float) angel.getAttributeValue(Attributes.ATTACK_DAMAGE);
                target.hurt(serverLevel.damageSources().mobAttack(angel), baseDamage);
                target.knockback(3.0, target.getX() - angel.getX(), target.getZ() - angel.getZ());
            }

            wingAttackTarget = null;
        }
    }

    /* ------------------------------------------------------------------ */
    /*  BOSS BAR                                                            */
    /* ------------------------------------------------------------------ */

    @Override
    public void startSeenByPlayer(ServerPlayer serverPlayer) {
        super.startSeenByPlayer(serverPlayer);
        this.bossEvent.addPlayer(serverPlayer);
        NeoForgePacketHandler.sendBattleMusicToPlayer(serverPlayer, this.getId());
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer serverPlayer) {
        super.stopSeenByPlayer(serverPlayer);
        this.bossEvent.removePlayer(serverPlayer);
        NeoForgePacketHandler.sendBattleMusicToPlayer(serverPlayer, this.getId());
    }
}