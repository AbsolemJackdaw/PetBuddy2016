package subaraki.petbuddy.petform;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.CowModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;
import subaraki.petbuddy.api.IPetFormBase;
import subaraki.petbuddy.client.entity.RenderEntityPetBuddy;
import subaraki.petbuddy.client.entity.layers.LayerPetFormBase;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

public class CowForm implements IPetFormBase{

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
        stack.translate(0.9, .1, 0.8);
        stack.mulPose(new Quaternion(0, 0, -45f, true));
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
    public LayerRenderer<PetBuddyEntity, PlayerModel<PetBuddyEntity>> getLayer(RenderEntityPetBuddy parent_renderer)
    {

        return new LayerCow(parent_renderer);
    }
    
    private class LayerCow extends LayerPetFormBase{

        private final ResourceLocation COW_LOCATION = new ResourceLocation("textures/entity/cow/cow.png");

        public LayerCow(IEntityRenderer<PetBuddyEntity, PlayerModel<PetBuddyEntity>> parent_renderer) {

            super(parent_renderer);
        }
        
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

        @Override
        public EntityModel<PetBuddyEntity> getModel()
        {

            return new CowModel<>();
        }
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

}
