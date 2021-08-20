package subaraki.petbuddy.petform;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import subaraki.petbuddy.api.IPetFormBase;
import subaraki.petbuddy.api.LayerPetFormBase;
import subaraki.petbuddy.client.entity.RenderEntityPetBuddy;
import subaraki.petbuddy.client.entity.model.StriderBuddyModel;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

public class StriderForm implements IPetFormBase {

    protected StriderBuddyModel model = new StriderBuddyModel();

    @Override
    public float getScale()
    {

        return 0.35f;
    }

    @Override
    public Item getFormItem()
    {

        return Items.WARPED_FUNGUS_ON_A_STICK;
    }

    @Override
    public boolean canVisuallySwimLikePlayer()
    {

        return false;
    }

    @Override
    public void heldItemRotationAndOffset(MatrixStack stack)
    {
        model.body.translateAndRotate(stack);
        stack.translate(-0.6, 0, 0);
        stack.scale(3,3,3);
    }

    @Override
    public String getID()
    {

        return "strider";
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
    public EntityModel<PetBuddyEntity> getDefaultModel()
    {

        return model;
    }

    @Override
    public LayerRenderer<PetBuddyEntity, PlayerModel<PetBuddyEntity>> getLayer(RenderEntityPetBuddy parent_renderer)
    {

        return new LayerPetFormBase(parent_renderer) {

            private final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/strider/strider.png");

            @Override
            public void render(MatrixStack stack, IRenderTypeBuffer buffer, int the_int, PetBuddyEntity buddy, float f1, float f2, float f3, float f4, float f5, float f6)
            {

                super.render(stack, buffer, the_int, buddy, f1, f2, f3, f4, f5, f6);
            }

            @Override
            protected ResourceLocation getTextureLocation(PetBuddyEntity p_229139_1_)
            {

                return TEXTURE;
            }

            @Override
            public IPetFormBase getForm()
            {

                return StriderForm.this;
            }
        };
    }

}
