package net.sanma.ziggizaggamod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.sanma.ziggizaggamod.ZiggiZaggaMod;
import net.sanma.ziggizaggamod.entity.custom.EscobiEntity;

public class EscobiRenderer extends MobRenderer<EscobiEntity, EscobiRenderState,EscobiModel> {
    public EscobiRenderer(EntityRendererProvider.Context context) {
        super(context, new EscobiModel(context.bakeLayer(EscobiModel.LAYER_LOCATION)), 0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(EscobiRenderState entity) {
        return ResourceLocation.fromNamespaceAndPath(ZiggiZaggaMod.MODID, "textures/entity/escobi.png");
    }

    @Override
    protected void scale(EscobiRenderState renderState, PoseStack poseStack) {
        float scale = 3.0f;
        poseStack.scale(scale, scale, scale);
        super.scale(renderState, poseStack);
    }

    @Override
    public void render(EscobiRenderState renderState, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        super.render(renderState, poseStack, bufferSource, packedLight);
    }

    @Override
    public EscobiRenderState createRenderState() {
        return new EscobiRenderState();
    }

    @Override
    public void extractRenderState(EscobiEntity entity, EscobiRenderState reusedState, float partialTick) {
        super.extractRenderState(entity, reusedState, partialTick);
        reusedState.isAttacking = entity.isAttacking();
        reusedState.idleAnimationState.copyFrom(entity.idleAnimation);
        reusedState.attackAnimationState.copyFrom(entity.attackAnimation);
    }
}
