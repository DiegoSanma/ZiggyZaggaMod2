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
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(ZiggiZaggaMod.MODID, "angel"), "main");
    private final ModelPart Head;
    private final ModelPart Body;
    private final ModelPart Outer;

    public AngelModel(ModelPart root) {
        super(root);
        this.Head = root.getChild("Head");
        this.Body = root.getChild("Body");
        //this.Wings = this.Body.getChild("Wings");
        //this.Torso = this.Body.getChild("Torso");
        //this.Bottom = this.Torso.getChild("Bottom");
        this.Outer = root.getChild("Outer");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 11).addBox(-6.0F, -12.0F, -5.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 14.0F, 4.0F));

        PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -17.0F, -2.0F, 10.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(32, 11).addBox(-6.0F, -18.0F, -1.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(52, 28).addBox(-5.0F, -16.0F, -2.0F, 8.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(58, 34).addBox(-5.0F, -16.0F, 7.0F, 8.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(72, 70).addBox(-6.0F, -16.0F, 7.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(12, 72).addBox(-6.0F, -16.0F, -2.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(10, 61).addBox(-4.0F, -15.0F, 8.0F, 5.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(62, 0).addBox(-4.0F, -15.0F, -3.0F, 5.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(22, 61).addBox(0.0F, -11.0F, 7.0F, 3.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(62, 4).addBox(0.0F, -11.0F, -2.0F, 3.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(16, 70).addBox(4.0F, -16.0F, -1.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 71).addBox(4.0F, -16.0F, 6.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(40, 68).addBox(3.0F, -16.0F, -2.0F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(54, 68).addBox(3.0F, -16.0F, 7.0F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(62, 68).addBox(-7.0F, -16.0F, 7.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(58, 68).addBox(-7.0F, -16.0F, -2.0F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(20, 73).addBox(-4.0F, -11.0F, -2.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(10, 58).addBox(-4.0F, -11.0F, 7.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(70, 28).addBox(-3.0F, -11.0F, 7.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(70, 30).addBox(-3.0F, -11.0F, -2.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(32, 20).addBox(2.0F, -18.0F, -1.0F, 2.0F, 10.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 41).addBox(4.0F, -17.0F, 0.0F, 1.0F, 11.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(14, 52).addBox(-7.0F, -16.0F, -1.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(44, 60).addBox(-7.0F, -15.0F, 0.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(58, 45).addBox(-7.0F, -14.0F, 1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(34, 73).addBox(-7.0F, -13.0F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 58).addBox(5.0F, -14.0F, 1.0F, 1.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(56, 60).addBox(4.0F, -6.0F, 1.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(66, 70).addBox(5.0F, -16.0F, 2.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(64, 11).addBox(4.0F, -18.0F, 1.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(52, 34).addBox(4.0F, -2.0F, 2.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(30, 73).addBox(4.0F, 0.0F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 69).addBox(5.0F, -7.0F, 2.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, -4.0F));

        PartDefinition Halo = Head.addOrReplaceChild("Halo", CubeListBuilder.create().texOffs(40, 9).addBox(-3.0F, -23.0F, -4.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(70, 4).addBox(-3.0F, -23.0F, 11.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 9).addBox(-6.0F, -23.0F, -3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(6, 70).addBox(-6.0F, -23.0F, 10.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(64, 16).addBox(0.0F, -23.0F, -3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(64, 18).addBox(0.0F, -23.0F, 10.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(70, 32).addBox(3.0F, -23.0F, -2.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(24, 73).addBox(3.0F, -23.0F, 9.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(74, 0).addBox(5.0F, -23.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(74, 10).addBox(-8.0F, -23.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(64, 45).addBox(6.0F, -23.0F, -1.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(66, 60).addBox(-9.0F, -23.0F, -1.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(64, 55).addBox(6.0F, -23.0F, 5.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(10, 65).addBox(-9.0F, -23.0F, 5.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(72, 24).addBox(5.0F, -23.0F, 8.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(74, 2).addBox(-8.0F, -23.0F, 8.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(64, 50).addBox(7.0F, -23.0F, 2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(44, 66).addBox(-10.0F, -23.0F, 2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(72, 20).addBox(-8.0F, -23.0F, -2.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(72, 22).addBox(-8.0F, -23.0F, 9.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, -5.0F));

        PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create(), PartPose.offset(0.0F, 14.0F, 4.0F));

        PartDefinition LeftArmBottom = Body.addOrReplaceChild("LeftArmBottom", CubeListBuilder.create().texOffs(0, 114).addBox(-2.0F, 0.0F, -2.0F, 3.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -4.0F, -5.0F));

        PartDefinition RightArmBottom = Body.addOrReplaceChild("RightArmBottom", CubeListBuilder.create().texOffs(42, 82).addBox(-2.0F, 0.0F, -1.0F, 3.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -4.0F, 3.0F));

        PartDefinition Wings = Body.addOrReplaceChild("Wings", CubeListBuilder.create(), PartPose.offset(-2.0F, -1.0F, -1.0F));

        PartDefinition WingRight = Wings.addOrReplaceChild("WingRight", CubeListBuilder.create().texOffs(33, 110).addBox(1.0F, -0.0007F, -6.1657F, 1.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(121, 67).addBox(2.0F, -4.0007F, -17.1657F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(103, 62).addBox(2.0F, -3.0007F, -17.1657F, 1.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(105, 81).addBox(2.0F, -1.0007F, -15.1657F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(85, 40).addBox(1.0F, 0.9993F, -16.1657F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(51, 86).addBox(1.0F, -0.0007F, -18.1657F, 1.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(121, 109).addBox(1.0F, -3.0007F, -5.1657F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(81, 121).addBox(1.0F, 1.9993F, -12.1657F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 78).addBox(1.0F, -4.0007F, -19.1657F, 1.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(121, 120).addBox(1.0F, -1.0007F, -2.1657F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 110).addBox(1.0F, -2.0007F, -18.1657F, 1.0F, 2.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(33, 105).addBox(1.0F, -6.0007F, -20.1657F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(121, 94).addBox(1.0F, -7.0007F, -20.1657F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(45, 99).addBox(1.0F, -5.0007F, -20.1657F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(93, 0).addBox(2.0F, -1.0007F, -11.1657F, 1.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -1.0F, -3.0F));

        PartDefinition WingLeft = Wings.addOrReplaceChild("WingLeft", CubeListBuilder.create().texOffs(46, 86).addBox(0.0F, 0.0F, -2.0F, 1.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(121, 91).addBox(1.0F, -4.0F, 15.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 99).addBox(1.0F, -3.0F, 9.0F, 1.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(107, 40).addBox(1.0F, -1.0F, 11.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(67, 93).addBox(0.0F, 1.0F, 6.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(77, 80).addBox(0.0F, 0.0F, 6.0F, 1.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(0, 88).addBox(0.0F, -3.0F, 3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(95, 115).addBox(0.0F, 2.0F, 6.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(98, 0).addBox(0.0F, -4.0F, 5.0F, 1.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(10, 89).addBox(0.0F, -1.0F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(15, 79).addBox(0.0F, -2.0F, 2.0F, 1.0F, 2.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(111, 29).addBox(0.0F, -6.0F, 16.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(121, 97).addBox(0.0F, -7.0F, 18.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(89, 93).addBox(0.0F, -5.0F, 10.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(91, 104).addBox(1.0F, -1.0F, 3.0F, 1.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -1.0F, 3.0F));

        PartDefinition Torso = Body.addOrReplaceChild("Torso", CubeListBuilder.create().texOffs(10, 98).addBox(-4.0F, -10.0F, 0.0F, 5.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(44, 114).addBox(-4.0F, -7.0F, -1.0F, 5.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(54, 78).addBox(-5.0F, -14.0F, -1.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(79, 53).addBox(-5.0F, -14.0F, 3.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(57, 90).addBox(-6.0F, -13.0F, 4.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(14, 45).addBox(-6.0F, -13.0F, 0.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.0F, -4.0F));

        PartDefinition Bottom = Torso.addOrReplaceChild("Bottom", CubeListBuilder.create().texOffs(62, 107).addBox(-5.0F, -5.0F, -2.0F, 6.0F, 4.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(67, 93).addBox(-4.0F, -1.0F, 1.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(76, 39).addBox(1.0F, -5.0F, -1.0F, 1.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(77, 63).addBox(-3.0F, -1.0F, 1.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition SmallLeft = Bottom.addOrReplaceChild("SmallLeft", CubeListBuilder.create().texOffs(0, 32).addBox(-2.0F, -4.0F, 8.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(23, 33).addBox(-2.0F, -3.0F, 8.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition SmallRight = Bottom.addOrReplaceChild("SmallRight", CubeListBuilder.create().texOffs(10, 99).addBox(-2.0F, -4.0F, -12.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(89, 29).addBox(-2.0F, -3.0F, -11.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 7.0F));

        PartDefinition Outer = partdefinition.addOrReplaceChild("Outer", CubeListBuilder.create(), PartPose.offset(0.0F, 17.0F, 3.0F));

        PartDefinition Cross1 = Outer.addOrReplaceChild("Cross1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 6.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-1.0F, 8.0F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-1.0F, 8.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-17.0F, -10.0F, -2.0F));

        PartDefinition Cross2 = Outer.addOrReplaceChild("Cross2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(9.0F, 6.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 0).mirror().addBox(9.0F, 8.0F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 0).mirror().addBox(9.0F, 8.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, -10.0F, -2.0F));

        PartDefinition Cross3 = Outer.addOrReplaceChild("Cross3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3.0F, 6.0F, 6.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 0).mirror().addBox(-5.0F, 8.0F, 6.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 0).mirror().addBox(-1.0F, 8.0F, 6.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.0F, -10.0F, 7.0F));

        PartDefinition Cross4 = Outer.addOrReplaceChild("Cross4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(1.0F, 6.0F, -7.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 0).mirror().addBox(3.0F, 8.0F, -7.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 0).mirror().addBox(-1.0F, 8.0F, -7.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.0F, -10.0F, -12.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(AngelRenderState state) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.animate(state.idleAnimationState, AngelAnimations.idle, state.ageInTicks, 1f);
        this.animate(state.attack1AnimationState,AngelAnimations.attack1, state.ageInTicks,1f);
        this.animate(state.attack2AnimationState,AngelAnimations.attack2, state.ageInTicks,1f);
        this.animate(state.attack3AnimationState,AngelAnimations.attack3, state.ageInTicks,1f);
        this.animate(state.attack4AnimationState,AngelAnimations.attack4, state.ageInTicks,1f);
        this.applyHeadRotation(state.yRot, state.xRot);
    }

    private void applyHeadRotation(float headYaw, float headPitch) {
        headPitch = Mth.clamp(headPitch, -90f, 90f);

        //Aun no se porque mierda tengo que agregar un +90 (confia)
        this.Head.yRot = (headYaw-90) * ((float)Math.PI / 180f);
        this.Body.yRot = (headYaw-90) * ((float)Math.PI / 180f);
        //this.All.xRot = headPitch *  ((float)Math.PI / 180f);
    }
}
