package subaraki.petbuddy.petform;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;
import subaraki.petbuddy.api.IPetFormBase;
import subaraki.petbuddy.client.entity.RenderEntityPetBuddy;
import subaraki.petbuddy.client.entity.layers.LayerPetFormBase;
import subaraki.petbuddy.client.entity.model.FoxBuddyModel;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

public class FoxForm implements IPetFormBase {

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
        stack.translate(1.9, -.5, 0.4);
        stack.mulPose(new Quaternion(0, 0, -45f, true));
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
    public LayerRenderer<PetBuddyEntity, PlayerModel<PetBuddyEntity>> getLayer(RenderEntityPetBuddy parent_renderer)
    {

        return new LayerFox(parent_renderer);
    }

    private class LayerFox extends LayerPetFormBase {

        private final ResourceLocation RED_FOX_TEXTURE = new ResourceLocation("textures/entity/fox/fox.png");

        public LayerFox(IEntityRenderer<PetBuddyEntity, PlayerModel<PetBuddyEntity>> parent_renderer) {

            super(parent_renderer);
        }

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

        @Override
        public EntityModel<PetBuddyEntity> getModel()
        {

            return new FoxBuddyModel<PetBuddyEntity>();
        }

    }

}
