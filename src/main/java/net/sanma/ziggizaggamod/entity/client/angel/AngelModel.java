package net.sanma.ziggizaggamod.entity.client.angel;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.sanma.ziggizaggamod.ZiggiZaggaMod;


public class AngelModel extends EntityModel<AngelRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(ZiggiZaggaMod.MODID, "angel"), "main");
    private final ModelPart Head;
    private final ModelPart Body;
    private final ModelPart Outer;

    public AngelModel(ModelPart root) {
        super(root);
        this.Head = root.getChild("Head");
        this.Body = root.getChild("Body");
        this.Outer = root.getChild("Outer");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(34, 27).addBox(-6.0F, -12.0F, -5.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 14.0F, 4.0F));

        PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(34, 16).addBox(-7.0F, -17.0F, -2.0F, 10.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(32, 59).addBox(-6.0F, -18.0F, -1.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(94, 85).addBox(-5.0F, -16.0F, -2.0F, 8.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(96, 20).addBox(-5.0F, -16.0F, 7.0F, 8.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(12, 106).addBox(-6.0F, -16.0F, 7.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(16, 115).addBox(-6.0F, -16.0F, -2.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(108, 51).addBox(-4.0F, -15.0F, 8.0F, 5.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(114, 101).addBox(-4.0F, -16.0F, 8.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 117).addBox(1.0F, -14.0F, 8.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(108, 55).addBox(-4.0F, -15.0F, -3.0F, 5.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(64, 83).addBox(0.0F, -11.0F, 7.0F, 3.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(108, 59).addBox(0.0F, -11.0F, -2.0F, 3.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(86, 114).addBox(4.0F, -16.0F, -1.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(114, 89).addBox(4.0F, -16.0F, 6.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(92, 109).addBox(3.0F, -16.0F, -2.0F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(96, 109).addBox(3.0F, -16.0F, 7.0F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(100, 113).addBox(-7.0F, -16.0F, 7.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(22, 110).addBox(-7.0F, -16.0F, -2.0F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(32, 115).addBox(-4.0F, -11.0F, -2.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(108, 66).addBox(-4.0F, -11.0F, 7.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(22, 84).addBox(-3.0F, -11.0F, 7.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(64, 90).addBox(-3.0F, -11.0F, -2.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(30, 68).addBox(2.0F, -18.0F, -1.0F, 2.0F, 10.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(50, 83).addBox(4.0F, -17.0F, 0.0F, 1.0F, 11.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(64, 92).addBox(-7.0F, -16.0F, -1.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(112, 76).addBox(-8.0F, -16.0F, 0.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(116, 62).addBox(-8.0F, -15.0F, 1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(116, 62).addBox(-8.0F, -17.0F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(16, 104).addBox(-7.0F, -15.0F, 0.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(10, 115).addBox(-7.0F, -14.0F, 1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(116, 9).addBox(-7.0F, -13.0F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 100).addBox(5.0F, -14.0F, 1.0F, 1.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(90, 101).addBox(4.0F, -6.0F, 1.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(114, 0).addBox(5.0F, -16.0F, 2.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(110, 105).addBox(4.0F, -18.0F, 1.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(104, 113).addBox(4.0F, -2.0F, 2.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(114, 103).addBox(4.0F, 0.0F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(46, 105).addBox(5.0F, -7.0F, 2.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(80, 11).addBox(-5.0F, -19.0F, 3.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(102, 7).addBox(-3.0F, -19.0F, 0.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(72, 113).addBox(1.0F, -19.0F, 1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(64, 117).addBox(-4.0F, -19.0F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, -4.0F));

        PartDefinition Halo = Head.addOrReplaceChild("Halo", CubeListBuilder.create().texOffs(66, 41).addBox(-3.0F, 0.0F, -8.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(112, 18).addBox(-3.0F, 0.0F, 7.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(76, 41).addBox(-6.0F, 0.0F, -7.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(112, 87).addBox(-6.0F, 0.0F, 6.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(80, 14).addBox(0.0F, 0.0F, -7.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(112, 16).addBox(0.0F, 0.0F, 6.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(114, 4).addBox(3.0F, 0.0F, -6.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(90, 14).addBox(3.0F, 0.0F, 5.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(4, 117).addBox(5.0F, 0.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(60, 117).addBox(-8.0F, 0.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(110, 110).addBox(6.0F, 0.0F, -5.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(112, 71).addBox(-9.0F, 0.0F, -5.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 112).addBox(6.0F, 0.0F, 1.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(112, 11).addBox(-9.0F, 0.0F, 1.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(116, 59).addBox(5.0F, 0.0F, 4.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(56, 117).addBox(-8.0F, 0.0F, 4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(46, 111).addBox(7.0F, 0.0F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(112, 66).addBox(-10.0F, 0.0F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(114, 24).addBox(-8.0F, 0.0F, -6.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(114, 95).addBox(-8.0F, 0.0F, 5.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -19.0F, -1.0F));

        PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create(), PartPose.offset(0.0F, 14.0F, 4.0F));

        PartDefinition Wings = Body.addOrReplaceChild("Wings", CubeListBuilder.create(), PartPose.offset(-4.0F, -1.0F, -1.0F));

        PartDefinition WingRight = Wings.addOrReplaceChild("WingRight", CubeListBuilder.create().texOffs(0, 85).addBox(1.0F, -0.0007F, -6.1657F, 1.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(36, 86).addBox(0.0F, -0.0007F, -5.1657F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(108, 26).addBox(0.0F, 1.9993F, -3.1657F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(116, 115).addBox(2.0F, -4.0007F, -17.1657F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(90, 59).addBox(2.0F, -3.0007F, -17.1657F, 1.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(72, 108).addBox(2.0F, -1.0007F, -15.1657F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 74).addBox(1.0F, 0.9993F, -16.1657F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(64, 43).addBox(1.0F, -0.0007F, -18.1657F, 1.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(26, 115).addBox(1.0F, -3.0007F, -5.1657F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(100, 91).addBox(1.0F, 1.9993F, -12.1657F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(34, 43).addBox(1.0F, -4.0007F, -19.1657F, 1.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(86, 41).addBox(1.0F, -1.0007F, -2.1657F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 16).addBox(1.0F, -2.0007F, -18.1657F, 1.0F, 2.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(62, 108).addBox(1.0F, -6.0007F, -20.1657F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(110, 115).addBox(1.0F, -7.0007F, -20.1657F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(72, 81).addBox(1.0F, -5.0007F, -20.1657F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(90, 27).addBox(2.0F, -1.0007F, -11.1657F, 1.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(112, 81).addBox(2.0F, -7.0007F, -20.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(36, 105).addBox(3.0F, -1.0007F, -6.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(36, 105).addBox(3.0F, -2.0007F, -10.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(36, 105).addBox(3.0F, -3.0007F, -14.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(36, 105).addBox(3.0F, -5.0007F, -18.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(112, 81).addBox(3.0F, -6.0007F, -20.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(108, 32).addBox(4.0F, -1.0007F, -8.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(108, 39).addBox(4.0F, -2.0007F, -13.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(108, 32).addBox(4.0F, -4.0007F, -18.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(114, 97).addBox(4.0F, -5.0007F, -20.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -1.0F, -3.0F));

        PartDefinition WingLeft = Wings.addOrReplaceChild("WingLeft", CubeListBuilder.create().texOffs(18, 86).addBox(0.0F, 0.0F, -2.0F, 1.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 97).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(0, 106).addBox(-1.0F, 2.0F, -2.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(116, 6).addBox(1.0F, -4.0F, 15.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(90, 49).addBox(1.0F, -3.0F, 9.0F, 1.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(82, 109).addBox(1.0F, -1.0F, 11.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(74, 16).addBox(0.0F, 1.0F, 6.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(64, 56).addBox(0.0F, 0.0F, 6.0F, 1.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(44, 116).addBox(0.0F, -3.0F, 3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(100, 98).addBox(0.0F, 2.0F, 6.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(50, 0).addBox(0.0F, -4.0F, 5.0F, 1.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(56, 111).addBox(0.0F, -1.0F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 34).addBox(0.0F, -2.0F, 2.0F, 1.0F, 2.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(12, 110).addBox(0.0F, -6.0F, 16.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(50, 116).addBox(0.0F, -7.0F, 18.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(80, 0).addBox(0.0F, -5.0F, 10.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(90, 38).addBox(1.0F, -1.0F, 3.0F, 1.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(36, 105).addBox(1.0F, 0.0F, -1.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(112, 81).addBox(1.0F, -7.0F, 17.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(36, 105).addBox(2.0F, -1.0F, 0.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(36, 105).addBox(2.0F, -2.0F, 4.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(36, 105).addBox(2.0F, -3.0F, 8.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(36, 105).addBox(2.0F, -5.0F, 12.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(112, 81).addBox(2.0F, -6.0F, 16.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(108, 32).addBox(3.0F, -1.0F, 2.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(108, 32).addBox(3.0F, -2.0F, 7.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(108, 32).addBox(3.0F, -4.0F, 12.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(36, 113).addBox(3.0F, -5.0F, 15.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -1.0F, 3.0F));

        PartDefinition Torso = Body.addOrReplaceChild("Torso", CubeListBuilder.create().texOffs(72, 69).addBox(-4.0F, -10.0F, 0.0F, 5.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(94, 69).addBox(-4.0F, -14.0F, -1.0F, 5.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(94, 77).addBox(-4.0F, -14.0F, 3.0F, 5.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(114, 20).addBox(-5.0F, -13.0F, 4.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(80, 114).addBox(-5.0F, -13.0F, 0.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.0F, -4.0F));

        PartDefinition Bottom2 = Torso.addOrReplaceChild("Bottom2", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -3.3269F, -5.0F, 15.0F, 6.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(50, 69).addBox(7.0F, -1.3269F, -4.0F, 3.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(66, 27).addBox(-11.0F, -1.3269F, -4.0F, 4.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 52).addBox(-6.0F, -1.3269F, -8.0F, 11.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 63).addBox(-5.0F, 1.6731F, -3.0F, 9.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 52).addBox(-6.0F, -1.3269F, 3.0F, 11.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(16, 98).addBox(4.0F, 3.6731F, -2.0F, 5.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(16, 98).addBox(-10.0F, 3.6731F, -2.0F, 5.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(34, 100).addBox(-3.0F, 4.6731F, -5.0F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(34, 100).addBox(-3.0F, 4.6731F, 1.0F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(82, 92).addBox(5.0F, -0.3269F, -7.0F, 5.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(82, 92).addBox(-11.0F, -0.3269F, -7.0F, 5.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(96, 11).addBox(5.0F, -0.3269F, 3.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(96, 11).addBox(-10.0F, -0.3269F, 3.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(62, 101).addBox(6.0F, -2.3269F, -2.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(102, 0).addBox(-10.0F, -2.3269F, -2.0F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(76, 101).addBox(-2.0F, -2.3269F, -8.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(76, 101).addBox(-2.0F, -2.3269F, 4.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(100, 105).addBox(10.0F, 0.6731F, -2.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(100, 105).addBox(-12.0F, 0.6731F, -2.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(108, 46).addBox(-3.0F, 0.6731F, -9.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(36, 94).addBox(-3.0F, 0.6731F, 7.0F, 5.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(16, 98).addBox(2.0F, 4.6731F, -6.0F, 5.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(16, 98).addBox(-8.0F, 4.6731F, -6.0F, 5.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(16, 98).addBox(2.0F, 4.6731F, 2.0F, 5.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(16, 98).addBox(-8.0F, 4.6731F, 2.0F, 5.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.6731F, 3.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition Outer = partdefinition.addOrReplaceChild("Outer", CubeListBuilder.create(), PartPose.offset(0.0F, 17.0F, 3.0F));

        PartDefinition Cross1 = Outer.addOrReplaceChild("Cross1", CubeListBuilder.create().texOffs(22, 74).addBox(-1.0F, 6.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(56, 113).addBox(-1.0F, 8.0F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(56, 113).addBox(-1.0F, 8.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-17.0F, -10.0F, -2.0F));

        PartDefinition Cross2 = Outer.addOrReplaceChild("Cross2", CubeListBuilder.create().texOffs(28, 105).addBox(9.0F, 6.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(64, 113).addBox(9.0F, 8.0F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(64, 113).addBox(9.0F, 8.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -10.0F, -2.0F));

        PartDefinition Cross3 = Outer.addOrReplaceChild("Cross3", CubeListBuilder.create().texOffs(28, 105).addBox(-3.0F, 6.0F, 6.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(64, 113).addBox(-5.0F, 8.0F, 6.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(64, 113).addBox(-1.0F, 8.0F, 6.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -10.0F, 7.0F));

        PartDefinition Cross4 = Outer.addOrReplaceChild("Cross4", CubeListBuilder.create().texOffs(28, 105).addBox(1.0F, 6.0F, -7.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(64, 113).addBox(3.0F, 8.0F, -7.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(64, 113).addBox(-1.0F, 8.0F, -7.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -10.0F, -12.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(AngelRenderState state) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.animate(state.idleAnimationState, AngelAnimations.idle, state.ageInTicks, 1f);
        this.animate(state.attack1AnimationState, AngelAnimations.attack1, state.ageInTicks, 1f);
        this.animate(state.attack2AnimationState, AngelAnimations.attack2, state.ageInTicks, 1f);
        this.animate(state.attack3AnimationState, AngelAnimations.attack3, state.ageInTicks, 1f);
        this.animate(state.attack4AnimationState, AngelAnimations.attack4, state.ageInTicks, 1f);
        this.applyHeadRotation(state.yRot, state.xRot);
    }

    private void applyHeadRotation(float headYaw, float headPitch) {
        headPitch = Mth.clamp(headPitch, -90f, 90f);

        //Aun no se porque mierda tengo que agregar un +90 (confia)
        this.Head.yRot = (headYaw - 90) * ((float) Math.PI / 180f);
        this.Body.yRot = (headYaw - 90) * ((float) Math.PI / 180f);
    }
}
