package subaraki.petbuddy.petform;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import subaraki.petbuddy.api.IPetFormBase;
import subaraki.petbuddy.api.LayerPetFormBase;
import subaraki.petbuddy.client.entity.RenderEntityPetBuddy;
import subaraki.petbuddy.client.entity.model.BatBuddyModel;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

public class BatForm implements IPetFormBase {

    private BatBuddyModel model;

    public BatForm() {

        model = new BatBuddyModel();
    }

    @Override
    public float getScale()
    {

        return 0.25f;
    }

    @Override
    public Item getFormItem()
    {

        return Items.STONE;
    }

    @Override
    public boolean canVisuallySwimLikePlayer()
    {

        return false;
    }

    @Override
    public void heldItemRotationAndOffset(MatrixStack stack)
    {

        if(model.parts() instanceof ImmutableList)
        {
           
            ModelRenderer part = ((ImmutableList<ModelRenderer>)model.parts()).get(1);
            part.translateAndRotate(stack);
            stack.translate(0, 0.8, -0.4);
            stack.scale(4,4,4);
        }
       
    }

    @Override
    public String getID()
    {

        return "bat";
    }

    @Override
    public float getBob(PetBuddyEntity buddy, float tickCount)
    {

        return buddy.tickCount + tickCount;
    }

    @Override
    public void tick(PetBuddyEntity buddy)
    {

    }

    @Override
    public float getNameRenderOffset()
    {

        return -0.1f;
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

            private final ResourceLocation BAT_LOCATION = new ResourceLocation("textures/entity/bat.png");

            @Override
            protected ResourceLocation getTextureLocation(PetBuddyEntity p_229139_1_)
            {

                return BAT_LOCATION;
            }

            @Override
            public IPetFormBase getForm()
            {

                return BatForm.this;
            }
        };
    }

}
