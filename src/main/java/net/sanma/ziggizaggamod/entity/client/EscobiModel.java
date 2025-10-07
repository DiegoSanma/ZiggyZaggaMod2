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
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(ZiggiZaggaMod.MODID,"escobi"), "main");
    private final ModelPart All;


    public EscobiModel(ModelPart root) {
        super(root);
        this.All = root.getChild("All");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition All = partdefinition.addOrReplaceChild("All", CubeListBuilder.create(), PartPose.offset(-2.0F, 16.0F, -2.0F));

        PartDefinition Head2 = All.addOrReplaceChild("Head2", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -11.0F, -1.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 42).addBox(-4.0F, -5.0F, 0.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 8.0F, -2.0F));

        PartDefinition Eyes = Head2.addOrReplaceChild("Eyes", CubeListBuilder.create().texOffs(0, 33).addBox(1.0F, -9.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(6, 33).addBox(1.0F, -9.0F, 3.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition HeadSet = All.addOrReplaceChild("HeadSet", CubeListBuilder.create().texOffs(18, 19).addBox(-2.0F, -6.0F, -4.0F, 2.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(12, 33).addBox(-2.0F, -5.0F, -5.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(34, 0).addBox(-2.0F, -5.0F, 4.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(30, 28).addBox(-2.0F, -2.0F, -6.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(18, 32).addBox(-2.0F, -2.0F, 3.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 1.0F, 0.0F));

        PartDefinition CatEarLeft = HeadSet.addOrReplaceChild("CatEarLeft", CubeListBuilder.create().texOffs(32, 37).addBox(-2.0F, -14.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(4, 37).addBox(-2.0F, -15.0F, -2.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 7.0F, -2.0F));

        PartDefinition CatEarRight = HeadSet.addOrReplaceChild("CatEarRight", CubeListBuilder.create().texOffs(36, 37).addBox(-2.0F, -14.0F, 4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(8, 37).addBox(-2.0F, -15.0F, 5.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 7.0F, -2.0F));

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
                .texOffs(28, 34).addBox(-6.0F, 1.0F, -3.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -2.0F, -1.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(EscobiRenderState state) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.animate(state.idleAnimationState, EscobiAnimations.idle, state.ageInTicks, 1f);
        this.animate(state.attackAnimationState,EscobiAnimations.attack, state.ageInTicks,1f);
        this.applyHeadRotation(state.yRot, state.xRot);
    }

    private void applyHeadRotation(float headYaw, float headPitch) {
        headPitch = Mth.clamp(headPitch, -90f, 90f);

        //Aun no se porque mierda tengo que agregar un +90 (confia)
        this.All.yRot = (headYaw+90) * ((float)Math.PI / 180f);
        //this.All.xRot = headPitch *  ((float)Math.PI / 180f);
    }

}
