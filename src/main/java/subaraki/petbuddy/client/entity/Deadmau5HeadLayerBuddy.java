package subaraki.petbuddy.client.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import subaraki.petbuddy.client.entity.model.ModelEar;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

@OnlyIn(Dist.CLIENT)
public class Deadmau5HeadLayerBuddy extends LayerRenderer<PetBuddyEntity, PlayerModel<PetBuddyEntity>> {

    private ModelEar<PetBuddyEntity> earModel = new ModelEar<PetBuddyEntity>(0.0f);

    public Deadmau5HeadLayerBuddy(IEntityRenderer<PetBuddyEntity, PlayerModel<PetBuddyEntity>> p_i50945_1_) {

        super(p_i50945_1_);
    }

    public void render(MatrixStack renderstack, IRenderTypeBuffer buffer, int light, PetBuddyEntity buddy, float float_5, float float_6, float float_7, float p_225628_8_, float p_225628_9_, float p_225628_10_)
    {

        if (buddy.getSkinForm() != null && buddy.getSkinForm().getName().equals("deadmau5") && !buddy.isInvisible())
        {

            IVertexBuilder ivertexbuilder = buffer
                    .getBuffer(RenderType.entitySolid(Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(buddy).getTextureLocation(buddy)));
            int i = LivingRenderer.getOverlayCoords(buddy, 0.0F);

            for (int ear_index = 0; ear_index < 2; ++ear_index)
            {

                renderstack.pushPose();

                // thanks to Its_Meow from the MMD server for this rotation aid !
                renderstack.translate(this.getParentModel().head.x / 16.0F, this.getParentModel().head.y / 16.0F, this.getParentModel().head.z / 16.0F);
                renderstack.mulPose(Vector3f.ZP.rotation(this.getParentModel().head.zRot));
                renderstack.mulPose(Vector3f.YP.rotation(this.getParentModel().head.yRot));
                renderstack.mulPose(Vector3f.XP.rotation(this.getParentModel().head.xRot));

                renderstack.translate((double) (0.375F * (float) (ear_index * 2 - 1)), 0.0D, 0.0D);

                renderstack.translate(0.0D, -0.375D, 0.0D);

                float scale = 0.0625f * (16f + 3f);
                renderstack.scale(scale, scale, scale);

                this.getParentModel().head.addChild(earModel.ear);
                earModel.ear.render(renderstack, ivertexbuilder, light, i, 1f, 1f, 1f, 1f);
                renderstack.popPose();
            }

        }
    }
}
