package subaraki.petbuddy.petform;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import subaraki.petbuddy.api.IPetFormBase;
import subaraki.petbuddy.api.LayerPetFormBase;
import subaraki.petbuddy.client.entity.RenderEntityPetBuddy;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

public class FishForm implements IPetFormBase {

    private final SegmentedModel<PetBuddyEntity> model;
    private final String id;
    private final Item item;

    public FishForm(Item item, String id, SegmentedModel<PetBuddyEntity> model) {

        this.model = model;
        this.id = id;
        this.item = item;
    }

    @Override
    public float getScale()
    {

        return 0.65f;
    }

    @Override
    public Item getFormItem()
    {

        return item;
    }

    @Override
    public boolean canVisuallySwimLikePlayer()
    {

        return false;
    }

    @Override
    public void heldItemRotationAndOffset(MatrixStack stack)
    {

        stack.translate(-0.1f, 1.5f, -0.1f);
        stack.mulPose(new Quaternion(30, 0, 0, true));
    }

    @Override
    public String getID()
    {

        return id;
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

            private final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/fish/" + id + ".png");

            @Override
            public void render(MatrixStack stack, IRenderTypeBuffer buffer, int the_int, PetBuddyEntity buddy, float f1, float f2, float f3, float f4, float f5, float f6)
            {

                if (!buddy.getPetForm().getID().equals(getForm().getID()))
                    return;

                float f = 4.3F * MathHelper.sin(0.6F * getBob(buddy, f3));
                stack.mulPose(Vector3f.YP.rotationDegrees(f));

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

                return FishForm.this;
            }
        };
    }

}
