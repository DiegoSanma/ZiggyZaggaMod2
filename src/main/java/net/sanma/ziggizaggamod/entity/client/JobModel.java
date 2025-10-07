package net.sanma.ziggizaggamod.entity.client;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.sanma.ziggizaggamod.ZiggiZaggaMod;

public class JobModel extends EntityModel<JobRenderState> {

    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(ZiggiZaggaMod.MODID,"job"), "main");
    private final ModelPart All;

    public JobModel(ModelPart root) {
        super(root);
        this.All = root.getChild("All");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition All = partdefinition.addOrReplaceChild("All", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition Bottom = All.addOrReplaceChild("Bottom", CubeListBuilder.create().texOffs(30, 18).addBox(0.0F, -5.0F, -7.0F, 1.0F, 4.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(0, 44).addBox(1.0F, -2.0F, -7.0F, 1.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition MidBottom = All.addOrReplaceChild("MidBottom", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -12.0F, -7.0F, 1.0F, 8.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

        PartDefinition MidTop = All.addOrReplaceChild("MidTop", CubeListBuilder.create().texOffs(0, 22).addBox(0.0F, -20.0F, -7.0F, 1.0F, 8.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

        PartDefinition Top = All.addOrReplaceChild("Top", CubeListBuilder.create().texOffs(30, 36).addBox(0.0F, -24.0F, -7.0F, 1.0F, 4.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(30, 0).addBox(-1.0F, -25.0F, -8.0F, 1.0F, 2.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(JobRenderState state) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.animate(state.attackAnimationState,JobAnimations.attack, state.ageInTicks,1f);
        this.applyHeadRotation(state.yRot, state.xRot);
    }

    private void applyHeadRotation(float headYaw, float headPitch) {
        headPitch = Mth.clamp(headPitch, -90f, 90f);

        //Aun no se porque mierda tengo que agregar un +90 (confia)
        this.All.yRot = (headYaw-90) * ((float)Math.PI / 180f);
        //this.All.xRot = headPitch *  ((float)Math.PI / 180f);
    }
}