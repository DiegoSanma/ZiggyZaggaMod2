package net.sanma.ziggizaggamod.entity.client.pineapple;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.sanma.ziggizaggamod.ZiggiZaggaMod;
import net.sanma.ziggizaggamod.entity.custom.PineappleProjectileEntity;

public class PineappleProjectileRenderer extends EntityRenderer<PineappleProjectileEntity, EntityRenderState> {
    private PineappleProjectileModel model;

    public PineappleProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new PineappleProjectileModel(context.bakeLayer(PineappleProjectileModel.LAYER_LOCATION));
    }

    @Override
    public EntityRenderState createRenderState() {
        return new EntityRenderState();
    }

    @Override
    public void render(EntityRenderState state, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        float rotation = (state.ageInTicks * 20.0f) % 360.0f; // velocidad de giro ajustable

        // 💫 Aplicar rotación (eje Y para girar horizontalmente o X/Z según quieras)
        poseStack.mulPose(Axis.YP.rotationDegrees(rotation));
        poseStack.mulPose(Axis.XP.rotationDegrees(rotation * 0.5f));
        VertexConsumer vertexconsumer = ItemRenderer.getFoilBuffer(
                buffer, this.model.renderType(this.getTextureLocation()),false, false);
        this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
        super.render(state, poseStack, buffer, packedLight);
    }

    public ResourceLocation getTextureLocation() {
        return ResourceLocation.fromNamespaceAndPath(ZiggiZaggaMod.MODID, "textures/entity/pineapple.png");
    }
}
