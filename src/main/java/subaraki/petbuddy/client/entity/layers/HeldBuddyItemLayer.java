package subaraki.petbuddy.client.entity.layers;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

public class HeldBuddyItemLayer extends LayerRenderer<PetBuddyEntity, PlayerModel<PetBuddyEntity>> {

    public HeldBuddyItemLayer(IEntityRenderer<PetBuddyEntity, PlayerModel<PetBuddyEntity>> parent_renderer) {

        super(parent_renderer);
    }

    public void render(MatrixStack stack, IRenderTypeBuffer buffer, int the_int, PetBuddyEntity buddy, float f1, float f2, float f3, float f4, float f5, float f6)
    {

        if (buddy.getPetForm().getDefaultModel() instanceof IHasArm)
        {
            boolean flag = buddy.getMainArm() == HandSide.RIGHT;
            ItemStack itemstack = flag ? buddy.getOffhandItem() : buddy.getMainHandItem();
            ItemStack itemstack1 = flag ? buddy.getMainHandItem() : buddy.getOffhandItem();
            if (!itemstack.isEmpty() || !itemstack1.isEmpty())
            {
                stack.pushPose();
                if (this.getParentModel().young)
                {
                    stack.translate(0.0D, 0.75D, 0.0D);
                    stack.scale(0.5F, 0.5F, 0.5F);
                }

                this.renderArmWithItem(buddy, itemstack1, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, HandSide.RIGHT, stack, buffer, the_int);
                this.renderArmWithItem(buddy, itemstack, ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, HandSide.LEFT, stack, buffer, the_int);
                stack.popPose();
            }
        }
        else
        {
            ItemStack itemstack = buddy.getInventory().getStackInSlot(1);
            if (!itemstack.isEmpty())
            {
                float scale = buddy.getPetForm().getScale();
                
                stack.pushPose();
               
                buddy.getPetForm().heldItemRotationAndOffset(stack);
                stack.scale(scale, scale, scale);
                stack.mulPose(new Quaternion(0, -90, 90, true));
                
                Minecraft.getInstance().getItemRenderer().renderStatic(buddy, itemstack, TransformType.FIXED, false, stack, buffer, buddy.level, the_int,
                        OverlayTexture.NO_OVERLAY);
                stack.popPose();

            }
        }
    }

    private void renderArmWithItem(LivingEntity p_229135_1_, ItemStack p_229135_2_, ItemCameraTransforms.TransformType p_229135_3_, HandSide p_229135_4_, MatrixStack p_229135_5_, IRenderTypeBuffer p_229135_6_, int p_229135_7_)
    {

        if (!p_229135_2_.isEmpty())
        {

            p_229135_5_.pushPose();
            ((IHasArm) this.getParentModel()).translateToHand(p_229135_4_, p_229135_5_);
            p_229135_5_.mulPose(Vector3f.XP.rotationDegrees(-90.0F));
            p_229135_5_.mulPose(Vector3f.YP.rotationDegrees(180.0F));
            boolean flag = p_229135_4_ == HandSide.LEFT;
            p_229135_5_.translate((double) ((float) (flag ? -1 : 1) / 16.0F), 0.125D, -0.625D);
            Minecraft.getInstance().getItemInHandRenderer().renderItem(p_229135_1_, p_229135_2_, p_229135_3_, flag, p_229135_5_, p_229135_6_, p_229135_7_);
            p_229135_5_.popPose();

        }
    }
}