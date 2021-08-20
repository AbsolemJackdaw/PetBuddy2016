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
import subaraki.petbuddy.client.entity.model.GolemBuddyModel;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

public class IronGolemForm implements IPetFormBase {

    protected GolemBuddyModel model = new GolemBuddyModel();

    @Override
    public float getScale()
    {

        return 0.35f;
    }

    @Override
    public Item getFormItem()
    {

        return Items.POPPY;
    }

    @Override
    public boolean canVisuallySwimLikePlayer()
    {

        return false;
    }

    @Override
    public void heldItemRotationAndOffset(MatrixStack stack)
    {

        model.getFlowerHoldingArm().translateAndRotate(stack);
        
        stack.translate(-0.7, 1.7f, -0.4f);
        stack.mulPose(new Quaternion(45, 0, 0, true));
        stack.scale(3,3,3);
        
        
    }

    @Override
    public String getID()
    {

        return "iron_golem";
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

            final ResourceLocation GOLEM_LOCATION = new ResourceLocation("textures/entity/iron_golem/iron_golem.png");

            @Override
            protected ResourceLocation getTextureLocation(PetBuddyEntity p_229139_1_)
            {

                return GOLEM_LOCATION;
            }

            @Override
            public IPetFormBase getForm()
            {

                return IronGolemForm.this;
            }
        };
    }

}
