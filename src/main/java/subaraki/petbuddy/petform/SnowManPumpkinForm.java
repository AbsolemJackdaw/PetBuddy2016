package subaraki.petbuddy.petform;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.entity.model.SnowManModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.vector.Vector3f;
import subaraki.petbuddy.client.entity.RenderEntityPetBuddy;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

public class SnowManPumpkinForm extends SnowManForm {

    private SnowManModel<PetBuddyEntity> snowmanModel = new SnowManModel<>();

    @Override
    public Item getFormItem()
    {

        return Items.CARVED_PUMPKIN;
    }

    @Override
    public String getID()
    {

        return "snowgolem_pumpkin";
    }

    @Override
    public LayerRenderer<PetBuddyEntity, PlayerModel<PetBuddyEntity>> getLayer(RenderEntityPetBuddy parent_renderer)
    {

        return new LayerSnowGolemPumkin(parent_renderer);
    }

    protected class LayerSnowGolemPumkin extends LayerSnowGolem {

        public LayerSnowGolemPumkin(RenderEntityPetBuddy parent_renderer) {

            super(parent_renderer);
            parent_renderer.addLayer(new PumpkinHeadLayer(parent_renderer));
        }

    }

    private class PumpkinHeadLayer extends LayerRenderer<PetBuddyEntity, PlayerModel<PetBuddyEntity>> {

        public PumpkinHeadLayer(IEntityRenderer<PetBuddyEntity, PlayerModel<PetBuddyEntity>> p_i50926_1_) {

            super(p_i50926_1_);
        }

        @Override
        public void render(MatrixStack stack, IRenderTypeBuffer buffer, int the_int, PetBuddyEntity buddy, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_)
        {

            if (!buddy.getPetForm().equals(SnowManPumpkinForm.this))
                return;
            snowmanModel.getHead().xRot = this.getParentModel().head.xRot;
            snowmanModel.getHead().yRot = this.getParentModel().head.yRot;
            snowmanModel.getHead().zRot = this.getParentModel().head.zRot;
            stack.pushPose();
            snowmanModel.getHead().translateAndRotate(stack);
            stack.translate(0.0D, -0.34375D, 0.0D);
            stack.mulPose(Vector3f.YP.rotationDegrees(180.0F));
            stack.scale(0.625F, -0.625F, -0.625F);
            ItemStack itemstack = new ItemStack(Blocks.CARVED_PUMPKIN);
            Minecraft.getInstance().getItemRenderer().renderStatic(buddy, itemstack, ItemCameraTransforms.TransformType.HEAD, false, stack, buffer, buddy.level,
                    the_int, LivingRenderer.getOverlayCoords(buddy, 0.0F));
            stack.popPose();

        }

    }

}
