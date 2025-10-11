package net.sanma.ziggizaggamod.entity.client.angel;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.sanma.ziggizaggamod.ZiggiZaggaMod;
import net.sanma.ziggizaggamod.entity.custom.AngelEntity;

public class AngelRenderer extends MobRenderer<AngelEntity,AngelRenderState,AngelModel> {
    public AngelRenderer(EntityRendererProvider.Context context) {
        super(context, new AngelModel(context.bakeLayer(AngelModel.LAYER_LOCATION)), 0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(AngelRenderState entity) {
        return ResourceLocation.fromNamespaceAndPath(ZiggiZaggaMod.MODID, "textures/entity/angel.png");
    }

    @Override
    protected void scale(AngelRenderState renderState, PoseStack poseStack) {
        float scale = 3.0f;
        poseStack.scale(scale, scale, scale);
        super.scale(renderState, poseStack);
    }

    @Override
    public void render(AngelRenderState renderState, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        super.render(renderState, poseStack, bufferSource, packedLight);
    }

    @Override
    public AngelRenderState createRenderState() {
        return new AngelRenderState();
    }

    @Override
    public void extractRenderState(AngelEntity entity, AngelRenderState reusedState, float partialTick) {
        super.extractRenderState(entity, reusedState, partialTick);
        reusedState.idleAnimationState.copyFrom(entity.idleAnimation);
        reusedState.attack1AnimationState.copyFrom(entity.attack1Animation);
        reusedState.attack2AnimationState.copyFrom(entity.attack2Animation);
        reusedState.attack3AnimationState.copyFrom(entity.attack3Animation);
        reusedState.attack4AnimationState.copyFrom(entity.attack4Animation);
    }
}
