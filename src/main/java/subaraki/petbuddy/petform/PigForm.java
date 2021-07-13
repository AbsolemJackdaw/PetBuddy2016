package subaraki.petbuddy.petform;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.PigModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;
import subaraki.petbuddy.api.IPetFormBase;
import subaraki.petbuddy.api.LayerPetFormBase;
import subaraki.petbuddy.client.entity.RenderEntityPetBuddy;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

public class PigForm implements IPetFormBase {

    protected final PigModel<PetBuddyEntity> model;

    public PigForm() {

        model = new PigModel<>();
    }

    @Override
    public float getScale()
    {

        return 0.35F;
    }

    @Override
    public Item getFormItem()
    {

        return Items.PORKCHOP;
    }

    @Override
    public boolean canVisuallySwimLikePlayer()
    {

        return false;
    }

    @Override
    public void heldItemRotationAndOffset(MatrixStack stack)
    {

        stack.translate(0, 0.6, 0);
        stack.mulPose(new Quaternion(0, 45, 90f, true));
        stack.scale(2, 2, 2);
    }

    @Override
    public String getID()
    {

        return "pig";
    }

    @Override
    public float getNameRenderOffset()
    {

        return -0.4f;
    }

    @Override
    public EntityModel<PetBuddyEntity> getDefaultModel()
    {

        return model;
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
    public LayerRenderer<PetBuddyEntity, PlayerModel<PetBuddyEntity>> getLayer(RenderEntityPetBuddy parent_renderer)
    {

        return new LayerPetFormBase(parent_renderer) {

            private final ResourceLocation PIG_LOCATION = new ResourceLocation("textures/entity/pig/pig.png");

            @Override
            protected ResourceLocation getTextureLocation(PetBuddyEntity p_229139_1_)
            {

                return PIG_LOCATION;
            }

            @Override
            public IPetFormBase getForm()
            {

                return PigForm.this;
            }
        };
    }

}
