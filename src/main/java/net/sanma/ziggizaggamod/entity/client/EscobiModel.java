package net.sanma.ziggizaggamod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.GhastModel;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.GhastRenderer;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Zombie;
import net.sanma.ziggizaggamod.ZiggiZaggaMod;
public class EscobiModel extends EntityModel<EscobiRenderState> {

        // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
        public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(ZiggiZaggaMod.MODID, "escobi"), "main");
        private final ModelPart All;
        private final ModelPart Head2;
        //private final ModelPart Eyes;
        private final ModelPart HeadSet;
        //private final ModelPart CatEarLeft;
        //private final ModelPart CatEarRight;
        //private final ModelPart Hair;

        public EscobiModel(ModelPart root) {
            super(root);
            this.All = root.getChild("All");
            this.Head2 = this.All.getChild("Head2");
            //this.Eyes = this.Head2.getChild("Eyes");
            this.HeadSet = this.All.getChild("HeadSet");
            //this.CatEarLeft = this.HeadSet.getChild("CatEarLeft");
            //this.CatEarRight = this.HeadSet.getChild("CatEarRight");
            //this.Hair = this.All.getChild("Hair");
        }

        public static LayerDefinition createBodyLayer() {
            MeshDefinition meshdefinition = new MeshDefinition();
            PartDefinition partdefinition = meshdefinition.getRoot();

            PartDefinition All = partdefinition.addOrReplaceChild("All", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

            PartDefinition Head2 = All.addOrReplaceChild("Head2", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -11.0F, -1.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
                    .texOffs(0, 42).addBox(-4.0F, -5.0F, 0.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

            PartDefinition Eyes = Head2.addOrReplaceChild("Eyes", CubeListBuilder.create().texOffs(0, 33).addBox(1.0F, -9.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                    .texOffs(6, 33).addBox(1.0F, -9.0F, 3.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

            PartDefinition HeadSet = All.addOrReplaceChild("HeadSet", CubeListBuilder.create().texOffs(18, 19).addBox(-3.0F, -13.0F, -2.0F, 2.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                    .texOffs(12, 33).addBox(-3.0F, -12.0F, -3.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(34, 0).addBox(-3.0F, -12.0F, 6.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(30, 28).addBox(-3.0F, -9.0F, -4.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                    .texOffs(18, 32).addBox(-3.0F, -9.0F, 5.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

            PartDefinition CatEarLeft = HeadSet.addOrReplaceChild("CatEarLeft", CubeListBuilder.create().texOffs(32, 37).addBox(-2.0F, -14.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(4, 37).addBox(-2.0F, -15.0F, -2.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

            PartDefinition CatEarRight = HeadSet.addOrReplaceChild("CatEarRight", CubeListBuilder.create().texOffs(36, 37).addBox(-2.0F, -14.0F, 4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(8, 37).addBox(-2.0F, -15.0F, 5.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

            PartDefinition Hair = All.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(0, 19).addBox(-7.0F, -1.0F, -3.0F, 1.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
                    .texOffs(0, 12).addBox(-6.0F, -2.0F, -2.0F, 7.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                    .texOffs(25, 8).addBox(0.0F, -1.0F, -2.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                    .texOffs(24, 0).addBox(-8.0F, 0.0F, -1.0F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
                    .texOffs(34, 34).addBox(-1.0F, -2.0F, -3.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(0, 37).addBox(-1.0F, -2.0F, 4.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(26, 15).addBox(-6.0F, -2.0F, 4.0F, 5.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(18, 28).addBox(-6.0F, -2.0F, -3.0F, 5.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(34, 7).addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(34, 4).addBox(-6.0F, 1.0F, 4.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(28, 34).addBox(-6.0F, 1.0F, -3.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -10.0F, 1.0F));

            return LayerDefinition.create(meshdefinition, 64, 64);
        }
    @Override
    public void setupAnim(EscobiRenderState state) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        //this.applyHeadRotation(state.yRot, state.xRot);
        this.animateWalk(EscobiAnimations.walk, state.walkAnimationPos, state.walkAnimationSpeed, 2f, 2.5f);
        this.animate(state.idleAnimationState, EscobiAnimations.idle, state.ageInTicks, 1f);
        this.animate(state.attackAnimationState,EscobiAnimations.attack, state.ageInTicks,1f);
        }

    private void applyHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -30f, 30f);
        headPitch = Mth.clamp(headPitch, -25f, 45);

        this.All.yRot = headYaw * ((float)Math.PI / 180f);
        this.All.xRot = headPitch *  ((float)Math.PI / 180f);
    }

}
