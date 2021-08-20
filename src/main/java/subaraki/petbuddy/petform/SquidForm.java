package subaraki.petbuddy.petform;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.entity.model.SquidModel;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import subaraki.petbuddy.api.IPetFormBase;
import subaraki.petbuddy.api.LayerPetFormBase;
import subaraki.petbuddy.client.entity.RenderEntityPetBuddy;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

public class SquidForm implements IPetFormBase {

    public float tentacleMovement;
    public float oldTentacleMovement;
    public float tentacleAngle;
    public float oldTentacleAngle;
    private float tentacleSpeed;

    protected SquidModel<PetBuddyEntity> model = new SquidModel<>();

    @Override
    public float getScale()
    {

        return 0.35f;
    }

    @Override
    public Item getFormItem()
    {

        return Items.INK_SAC;
    }

    @Override
    public boolean canVisuallySwimLikePlayer()
    {

        return true;
    }

    @Override
    public void heldItemRotationAndOffset(MatrixStack stack)
    {
        stack.translate(-0.4f, 0.5, -0.4f);
        stack.scale(3,3,3);
    }

    @Override
    public String getID()
    {

        return "squid";
    }

    @Override
    public float getBob(PetBuddyEntity buddy, float tickCount)
    {

        if (buddy.isInWaterOrBubble())
            return MathHelper.lerp(tickCount, oldTentacleAngle, tentacleAngle);
        else
        {
            float f = buddy.getDeltaMovement().x !=  0 || buddy.getDeltaMovement().z != 0 ? ((float) buddy.tickCount + tickCount) : 0;
            return 1f + ((float) MathHelper.sin(f) / 5f);
        }
    }

    @Override
    public void tick(PetBuddyEntity buddy)
    {

        this.tentacleSpeed = 1.0F / (rand.nextFloat() + 1.0F) * 0.2F;

        this.oldTentacleMovement = this.tentacleMovement;
        this.oldTentacleAngle = this.tentacleAngle;
        this.tentacleMovement += this.tentacleSpeed;

        if (buddy.isInWater())
        {
            if ((double) this.tentacleMovement > (Math.PI * 2D))
            {
                this.tentacleMovement = (float) ((double) this.tentacleMovement - (Math.PI * 2D));
                if (rand.nextInt(10) == 0)
                {
                    this.tentacleSpeed = 1.0F / (rand.nextFloat() + 1.0F) * 0.2F;
                }
            }

            if (this.tentacleMovement < (float) Math.PI)
            {
                float f = this.tentacleMovement / (float) Math.PI;
                this.tentacleAngle = MathHelper.sin(f * f * (float) Math.PI) * (float) Math.PI * 0.25F;
            }
            else
            {
                this.tentacleAngle = 0.0F;
            }

        }
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

        // TODO Auto-generated method stub
        return new LayerPetFormBase(parent_renderer) {

            private final ResourceLocation SQUID_LOCATION = new ResourceLocation("textures/entity/squid.png");

            @Override
            public void render(MatrixStack stack, IRenderTypeBuffer buffer, int the_int, PetBuddyEntity buddy, float f1, float f2, float f3, float f4, float f5, float f6)
            {

                super.render(stack, buffer, the_int, buddy, f1, f2, f3, f4, f5, f6);
            }

            @Override
            protected ResourceLocation getTextureLocation(PetBuddyEntity p_229139_1_)
            {

                return SQUID_LOCATION;
            }

            @Override
            public IPetFormBase getForm()
            {

                return SquidForm.this;
            }
        };
    }

}
