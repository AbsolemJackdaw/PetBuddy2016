package subaraki.petbuddy.petform;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.entity.model.SnowManModel;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;
import subaraki.petbuddy.api.IPetFormBase;
import subaraki.petbuddy.api.LayerPetFormBase;
import subaraki.petbuddy.client.entity.RenderEntityPetBuddy;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

public class SnowManForm implements IPetFormBase {

    protected SnowManModel<PetBuddyEntity> snowmanModel = new SnowManModel<>();

    @Override
    public float getScale()
    {

        return 0.35f;
    }

    @Override
    public boolean canVisuallySwimLikePlayer()
    {

        return true;
    }

    @Override
    public void heldItemRotationAndOffset(MatrixStack stack)
    {

        stack.translate(3, -1, 0.1);
        stack.mulPose(new Quaternion(90, 0, 0, true));
        stack.scale(2, 2, 2);
    }

    @Override
    public float getBob(PetBuddyEntity buddy, float tickCount)
    {

        return 0;
    }

    @Override
    public void tick(PetBuddyEntity buddy)
    {

    }

    @Override
    public float getNameRenderOffset()
    {

        return 0;
    }

    @Override
    public Item getFormItem()
    {

        return Items.SNOWBALL;
    }

    @Override
    public String getID()
    {

        return "snowgolem";
    }

    @Override
    public EntityModel<PetBuddyEntity> getDefaultModel()
    {

        return snowmanModel;
    }

    @Override
    public LayerRenderer<PetBuddyEntity, PlayerModel<PetBuddyEntity>> getLayer(RenderEntityPetBuddy parent_renderer)
    {

        return new LayerSnowGolem(parent_renderer);
    }

    protected class LayerSnowGolem extends LayerPetFormBase {

        private final ResourceLocation SNOW_GOLEM_LOCATION = new ResourceLocation("textures/entity/snow_golem.png");

        public LayerSnowGolem(RenderEntityPetBuddy parent_renderer) {

            super(parent_renderer);

        }

        @Override
        protected ResourceLocation getTextureLocation(PetBuddyEntity p_229139_1_)
        {

            return SNOW_GOLEM_LOCATION;
        }

        @Override
        public IPetFormBase getForm()
        {

            return SnowManForm.this;
        }

        @Override
        public void render(MatrixStack stack, IRenderTypeBuffer buffer, int the_int, PetBuddyEntity buddy, float f1, float f2, float f3, float f4, float f5, float f6)
        {

            snowmanModel.getHead().xRot = this.getParentModel().head.xRot;
            snowmanModel.getHead().yRot = this.getParentModel().head.yRot;
            snowmanModel.getHead().zRot = this.getParentModel().head.zRot;
            super.render(stack, buffer, the_int, buddy, f1, f2, f3, f4, f5, f6);
        }

    }
}
