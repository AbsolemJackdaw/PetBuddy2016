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
import subaraki.petbuddy.client.entity.model.FoxBuddyModel;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

public class FoxForm implements IPetFormBase {

    private final FoxBuddyModel<PetBuddyEntity> model = new FoxBuddyModel<PetBuddyEntity>();

    public FoxForm() {

    }

    @Override
    public float getScale()
    {

        return 0.5f;
    }

    @Override
    public Item getFormItem()
    {

        return Items.SWEET_BERRIES;
    }

    @Override
    public boolean canVisuallySwimLikePlayer()
    {

        return false;
    }

    @Override
    public void heldItemRotationAndOffset(MatrixStack stack)
    {

        model.head.translateAndRotate(stack);
        stack.translate(0.15, 0.3, -0.5);
        stack.mulPose(new Quaternion(0, -45, 90, true));
        stack.scale(1.5f, 1.5f, 1.5f);
    }

    @Override
    public String getID()
    {

        return "fox";
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

        return -0.3f;
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

            private final ResourceLocation RED_FOX_TEXTURE = new ResourceLocation("textures/entity/fox/fox.png");

            @Override
            protected ResourceLocation getTextureLocation(PetBuddyEntity p_229139_1_)
            {

                return RED_FOX_TEXTURE;
            }

            @Override
            public IPetFormBase getForm()
            {

                return FoxForm.this;
            }
        };
    }

}
