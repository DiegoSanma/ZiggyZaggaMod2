package net.sanma.ziggizaggamod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.sanma.ziggizaggamod.ZiggiZaggaMod;
import net.sanma.ziggizaggamod.entity.custom.JobEntity;

public class JobRenderer extends MobRenderer<JobEntity, JobRenderState,JobModel> {
    public JobRenderer(EntityRendererProvider.Context context) {
        super(context, new JobModel(context.bakeLayer(JobModel.LAYER_LOCATION)), 0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(JobRenderState entity) {
        return ResourceLocation.fromNamespaceAndPath(ZiggiZaggaMod.MODID, "textures/entity/job.png");
    }

    @Override
    protected void scale(JobRenderState renderState, PoseStack poseStack) {
        float scale = 3.0f;
        poseStack.scale(scale, scale, scale);
        super.scale(renderState, poseStack);
    }

    @Override
    public void render(JobRenderState renderState, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        super.render(renderState, poseStack, bufferSource, packedLight);
    }

    @Override
    public JobRenderState createRenderState() {
        return new JobRenderState();
    }

    @Override
    public void extractRenderState(JobEntity entity, JobRenderState reusedState, float partialTick) {
        super.extractRenderState(entity, reusedState, partialTick);
        reusedState.attackAnimationState.copyFrom(entity.attackAnimation);
    }
}
