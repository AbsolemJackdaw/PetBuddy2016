package subaraki.petbuddy.petform;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.ChickenModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import subaraki.petbuddy.api.IPetFormBase;
import subaraki.petbuddy.client.entity.RenderEntityPetBuddy;
import subaraki.petbuddy.client.entity.layers.LayerPetFormBase;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

public class ChickenForm implements IPetFormBase {

    // chicken animation
    public float flap;
    public float flapSpeed;
    public float oFlapSpeed;
    public float oFlap;
    public float flapping = 1.0F;
    
    @Override
    public float getScale()
    {

        return 0.5F;
    }

    @Override
    public Item getFormItem()
    {

        return Items.FEATHER;
    }

    @Override
    public boolean canVisuallySwimLikePlayer()
    {

        return false;
    }

    @Override
    public void heldItemRotationAndOffset(MatrixStack stack)
    {
        stack.translate(1.7, .6, 0.4);
    }

    @Override
    public String getID()
    {

        return "chicken";
    }

    @Override
    public float getNameRenderOffset()
    {

        return -0.2f;
    }
    
    @Override
    public float getBob(PetBuddyEntity buddy, float tick)
    {

        float f = MathHelper.lerp(tick, oFlap, flap);
        float f1 = MathHelper.lerp(tick, oFlapSpeed, flapSpeed);
        return (MathHelper.sin(f) + 1.0F) * f1;
    }
    
    @Override
    public void tick (PetBuddyEntity buddy) {
        // chicken animation
        this.oFlap = this.flap;
        this.oFlapSpeed = this.flapSpeed;
        this.flapSpeed = (float) ((double) this.flapSpeed + (double) (buddy.isOnGround() ? -1 : 4) * 0.3D);
        this.flapSpeed = MathHelper.clamp(this.flapSpeed, 0.0F, 1.0F);
        
        if ((!buddy.isOnGround() || buddy.isSwimming()) && this.flapping < 1.0F)
        {
            this.flapping = 1.0F;
        }
        this.flap += this.flapping * 2.0F;
    }
    
    @Override
    public LayerRenderer<PetBuddyEntity, PlayerModel<PetBuddyEntity>> getLayer(RenderEntityPetBuddy parent_renderer)
    {

        return new LayerChicken(parent_renderer);
    }

    private class LayerChicken extends LayerPetFormBase {

        private final ResourceLocation CHICKEN_LOCATION = new ResourceLocation("textures/entity/chicken.png");

        public LayerChicken(IEntityRenderer<PetBuddyEntity, PlayerModel<PetBuddyEntity>> parent_renderer) {

            super(parent_renderer);
        }

        @Override
        protected ResourceLocation getTextureLocation(PetBuddyEntity p_229139_1_)
        {

            return CHICKEN_LOCATION;
        }

        @Override
        public IPetFormBase getForm()
        {

            return ChickenForm.this;
        }

        @Override
        public EntityModel<PetBuddyEntity> getModel()
        {

            return new ChickenModel<PetBuddyEntity>();
        }
    }

}