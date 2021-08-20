package subaraki.petbuddy.petform;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;
import subaraki.petbuddy.api.IPetFormBase;
import subaraki.petbuddy.api.LayerPetFormBase;
import subaraki.petbuddy.client.entity.RenderEntityPetBuddy;
import subaraki.petbuddy.client.entity.model.BeeBuddyModel;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

public class BeeForm implements IPetFormBase {

    protected BeeBuddyModel model = new BeeBuddyModel();

    @Override
    public float getScale()
    {

        return 0.5f;
    }

    @Override
    public Item getFormItem()
    {

        return Items.HONEYCOMB;
    }

    @Override
    public boolean canVisuallySwimLikePlayer()
    {

        return false;
    }

    @Override
    public void heldItemRotationAndOffset(MatrixStack stack)
    {

        model.bodyParts().forEach(part -> {
            part.translateAndRotate(stack);
        });
        
        stack.translate(0, -0.25f, -0.1f);
        stack.mulPose(new Quaternion(0, 0, 90, true));

    }

    @Override
    public String getID()
    {

        return "bee";
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

            private final ResourceLocation BEE_TEXTURE = new ResourceLocation("textures/entity/bee/bee.png");

            @Override
            protected ResourceLocation getTextureLocation(PetBuddyEntity p_229139_1_)
            {

                return BEE_TEXTURE;
            }

            @Override
            public IPetFormBase getForm()
            {

                return BeeForm.this;
            }
        };
    }

}
