package subaraki.petbuddy.petform;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import subaraki.petbuddy.api.IChangeableForm;
import subaraki.petbuddy.api.IPetFormBase;
import subaraki.petbuddy.api.LayerPetFormBase;
import subaraki.petbuddy.client.entity.RenderEntityPetBuddy;
import subaraki.petbuddy.client.entity.model.ParrotBuddyModel;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

public class ParrotForm implements IPetFormBase, IChangeableForm {

    protected ParrotBuddyModel model = new ParrotBuddyModel();
    protected int index = 0;
    public final ResourceLocation[] PARROT_LOCATIONS = new ResourceLocation[] { new ResourceLocation("textures/entity/parrot/parrot_red_blue.png"),
            new ResourceLocation("textures/entity/parrot/parrot_blue.png"), new ResourceLocation("textures/entity/parrot/parrot_green.png"),
            new ResourceLocation("textures/entity/parrot/parrot_yellow_blue.png"), new ResourceLocation("textures/entity/parrot/parrot_grey.png") };

    // chicken animation
    public float flap;
    public float flapSpeed;
    public float oFlapSpeed;
    public float oFlap;
    public float flapping = 1.0F;

    @Override
    public float getScale()
    {

        return 1f;
    }

    @Override
    public Item getFormItem()
    {

        return Items.WHEAT_SEEDS;
    }

    @Override
    public boolean canVisuallySwimLikePlayer()
    {

        return false;
    }

    @Override
    public void heldItemRotationAndOffset(MatrixStack stack)
    {

        stack.translate(-0.1, 1.2, -0.25);
        stack.scale(0.5f, 0.5f, 0.5f);
    }

    @Override
    public String getID()
    {

        return "parrot";
    }

    @Override
    public float getBob(PetBuddyEntity buddy, float tick)
    {

        float f = MathHelper.lerp(tick, oFlap, flap);
        float f1 = MathHelper.lerp(tick, oFlapSpeed, flapSpeed);
        return (MathHelper.sin(f) + 1.0F) * f1;
    }

    @Override
    public void tick(PetBuddyEntity buddy)
    {

        // chicken animation
        this.oFlap = this.flap;
        this.oFlapSpeed = this.flapSpeed;
        this.flapSpeed = (float) ((double) this.flapSpeed + (double) (buddy.isOnGround() ? -1 : 2f) * 0.3D);
        this.flapSpeed = MathHelper.clamp(this.flapSpeed, 0.0F, 1.0F);

        if ((!buddy.isOnGround() || buddy.isSwimming()) && this.flapping < 1.0F)
        {
            this.flapping = 1.0F;
        }
        this.flap += this.flapping * 2.0F;
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

            @Override
            protected ResourceLocation getTextureLocation(PetBuddyEntity p_229139_1_)
            {

                return PARROT_LOCATIONS[index];
            }

            @Override
            public IPetFormBase getForm()
            {

                return ParrotForm.this;
            }
        };
    }

    @Override
    public void onSlotInsert()
    {

        index = rand.nextInt(PARROT_LOCATIONS.length);
    }

}
