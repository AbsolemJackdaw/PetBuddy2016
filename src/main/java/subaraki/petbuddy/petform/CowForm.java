package subaraki.petbuddy.petform;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.CowModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;
import subaraki.petbuddy.api.IPetFormBase;
import subaraki.petbuddy.api.LayerPetFormBase;
import subaraki.petbuddy.client.entity.RenderEntityPetBuddy;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

public class CowForm implements IPetFormBase {

    protected CowModel<PetBuddyEntity> cowModel = new CowModel<>();

    public CowForm() {

        cowModel = new CowModel<>();
    }

    @Override
    public float getScale()
    {

        return 0.5f;
    }

    @Override
    public Item getFormItem()
    {

        return Items.LEATHER;
    }

    @Override
    public boolean canVisuallySwimLikePlayer()
    {

        return false;
    }

    @Override
    public void heldItemRotationAndOffset(MatrixStack stack)
    {

        stack.translate(-0.4f, 0.4, 0);
        stack.mulPose(new Quaternion(45, 0, 0, true));
        stack.scale(2, 2, 2);
    }

    @Override
    public String getID()
    {

        return "cow";
    }

    @Override
    public float getNameRenderOffset()
    {

        return 0.1f;
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
    public EntityModel<PetBuddyEntity> getDefaultModel()
    {

        return cowModel;
    }

    @Override
    public LayerRenderer<PetBuddyEntity, PlayerModel<PetBuddyEntity>> getLayer(RenderEntityPetBuddy parent_renderer)
    {

        return new LayerPetFormBase(parent_renderer) {

            private final ResourceLocation COW_LOCATION = new ResourceLocation("textures/entity/cow/cow.png");

            @Override
            protected ResourceLocation getTextureLocation(PetBuddyEntity p_229139_1_)
            {

                return COW_LOCATION;
            }

            @Override
            public IPetFormBase getForm()
            {

                return CowForm.this;
            }
        };
    }
}
