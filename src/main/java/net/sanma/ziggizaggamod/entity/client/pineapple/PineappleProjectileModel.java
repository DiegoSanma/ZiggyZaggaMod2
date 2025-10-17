package net.sanma.ziggizaggamod.entity.client.pineapple;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.sanma.ziggizaggamod.ZiggiZaggaMod;

public class PineappleProjectileModel extends EntityModel<EntityRenderState> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(ZiggiZaggaMod.MODID, "pineapple_projectile"), "main");
    private final ModelPart Pineapple;
    //private final ModelPart body;
    //private final ModelPart leaf;

    public PineappleProjectileModel(ModelPart root) {
        super(root);
        this.Pineapple = root.getChild("Pineapple");
        //this.body = this.Pineapple.getChild("body");
        //this.leaf = this.Pineapple.getChild("leaf");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Pineapple = partdefinition.addOrReplaceChild("Pineapple", CubeListBuilder.create(), PartPose.offset(0.0F, 17.0F, 0.0F));

        PartDefinition body = Pineapple.addOrReplaceChild("body", CubeListBuilder.create().texOffs(10, 24).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(16, 7).addBox(-1.0F, -2.0F, -2.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-1.0F, -5.0F, -3.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 9).addBox(-1.0F, -8.0F, -3.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(16, 17).addBox(-2.0F, -7.0F, -2.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 18).addBox(1.0F, -7.0F, -2.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(16, 12).addBox(-1.0F, -9.0F, -2.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition leaf = Pineapple.addOrReplaceChild("leaf", CubeListBuilder.create().texOffs(16, 0).addBox(-1.0F, -10.0F, -3.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(18, 24).addBox(-1.0F, -11.0F, -4.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(26, 23).addBox(-2.0F, -10.0F, -3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(26, 26).addBox(-2.0F, -10.0F, 1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(8, 27).addBox(-3.0F, -10.0F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 28).addBox(1.0F, -10.0F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(10, 18).addBox(1.0F, -10.0F, -2.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(10, 22).addBox(1.0F, -10.0F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(28, 7).addBox(-1.0F, -10.0F, 3.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(28, 10).addBox(-1.0F, -10.0F, -4.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 25).addBox(-1.0F, -11.0F, 2.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(26, 17).addBox(-2.0F, -11.0F, 0.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(14, 27).addBox(-2.0F, -12.0F, -2.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(28, 13).addBox(-1.0F, -12.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(20, 27).addBox(0.0F, -12.0F, 1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(26, 20).addBox(0.0F, -11.0F, -2.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(EntityRenderState state) {

    }
}
