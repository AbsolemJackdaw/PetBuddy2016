package subaraki.petbuddy.petform;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.client.model.data.EmptyModelData;
import subaraki.petbuddy.api.IPetFormBase;
import subaraki.petbuddy.client.entity.RenderEntityPetBuddy;
import subaraki.petbuddy.client.entity.layers.LayerPetFormBase;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

public class MooshroomRedForm extends CowForm {

    @Override
    public Item getFormItem()
    {

        return Items.RED_MUSHROOM;
    }

    @Override
    public String getID()
    {

        return "mooshroom_red";
    }

    @Override
    public LayerRenderer<PetBuddyEntity, PlayerModel<PetBuddyEntity>> getLayer(RenderEntityPetBuddy parent_renderer)
    {

        return new LayerCow(parent_renderer);
    }

    private class LayerCow extends LayerPetFormBase {

        private final ResourceLocation RED_COW_LOCATION = new ResourceLocation("textures/entity/cow/red_mooshroom.png");

        public LayerCow(RenderEntityPetBuddy parent_renderer) {

            super(parent_renderer);

            parent_renderer.addLayer(new LayerBuddyMushroom(parent_renderer));
        }

        @Override
        protected ResourceLocation getTextureLocation(PetBuddyEntity p_229139_1_)
        {

            return RED_COW_LOCATION;
        }

        @Override
        public IPetFormBase getForm()
        {

            return MooshroomRedForm.this;
        }

        @Override
        public void render(MatrixStack stack, IRenderTypeBuffer buffer, int the_int, PetBuddyEntity buddy, float f1, float f2, float f3, float f4, float f5, float f6)
        {

            cowModel.getHead().xRot = this.getParentModel().head.xRot;
            cowModel.getHead().yRot = this.getParentModel().head.yRot;
            cowModel.getHead().zRot = this.getParentModel().head.zRot;
            super.render(stack, buffer, the_int, buddy, f1, f2, f3, f4, f5, f6);
        }
    }

    private class LayerBuddyMushroom extends LayerRenderer<PetBuddyEntity, PlayerModel<PetBuddyEntity>> {

        public LayerBuddyMushroom(IEntityRenderer<PetBuddyEntity, PlayerModel<PetBuddyEntity>> p_i50926_1_) {

            super(p_i50926_1_);
        }

        @Override
        public void render(MatrixStack stack, IRenderTypeBuffer buffer, int the_int, PetBuddyEntity buddy, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_)
        {

            if (!buddy.getPetForm().equals(MooshroomRedForm.this))
                return;
            cowModel.getHead().xRot = this.getParentModel().head.xRot;
            cowModel.getHead().yRot = this.getParentModel().head.yRot;
            cowModel.getHead().zRot = this.getParentModel().head.zRot;
            BlockRendererDispatcher blockrendererdispatcher = Minecraft.getInstance().getBlockRenderer();
            BlockState blockstate = Blocks.RED_MUSHROOM.defaultBlockState();
            int i = LivingRenderer.getOverlayCoords(buddy, 0.0F);
            stack.pushPose();
            stack.translate((double) 0.2F, (double) -0.35F, 0.5D);
            stack.mulPose(Vector3f.YP.rotationDegrees(-48.0F));
            stack.scale(-1.0F, -1.0F, 1.0F);
            stack.translate(-0.5D, -0.5D, -0.5D);
            blockrendererdispatcher.renderBlock(blockstate, stack, buffer, the_int, i, EmptyModelData.INSTANCE);
            stack.popPose();
            stack.pushPose();
            stack.translate((double) 0.2F, (double) -0.35F, 0.5D);
            stack.mulPose(Vector3f.YP.rotationDegrees(42.0F));
            stack.translate((double) 0.1F, 0.0D, (double) -0.6F);
            stack.mulPose(Vector3f.YP.rotationDegrees(-48.0F));
            stack.scale(-1.0F, -1.0F, 1.0F);
            stack.translate(-0.5D, -0.5D, -0.5D);
            blockrendererdispatcher.renderBlock(blockstate, stack, buffer, the_int, i, EmptyModelData.INSTANCE);
            stack.popPose();
            stack.pushPose();
            cowModel.getHead().translateAndRotate(stack);
            stack.translate(0.0D, (double) -0.7F, (double) -0.2F);
            stack.mulPose(Vector3f.YP.rotationDegrees(-78.0F));
            stack.scale(-1.0F, -1.0F, 1.0F);
            stack.translate(-0.5D, -0.5D, -0.5D);
            blockrendererdispatcher.renderBlock(blockstate, stack, buffer, the_int, i, EmptyModelData.INSTANCE);
            stack.popPose();
        }

    }

}
