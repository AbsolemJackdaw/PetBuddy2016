package subaraki.petbuddy.petform;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.entity.model.VillagerModel;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import subaraki.petbuddy.api.IPetFormBase;
import subaraki.petbuddy.api.LayerPetFormBase;
import subaraki.petbuddy.client.entity.RenderEntityPetBuddy;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

public class VillagerForm implements IPetFormBase {

    protected VillagerModel<PetBuddyEntity> model = new VillagerModel<>(0.0f);

    @Override
    public float getScale()
    {

        return 0.35f;
    }

    @Override
    public Item getFormItem()
    {

        return Items.EMERALD;
    }

    @Override
    public boolean canVisuallySwimLikePlayer()
    {

        return true;
    }

    @Override
    public void heldItemRotationAndOffset(MatrixStack stack)
    {
        stack.translate(0, 0.2, -0.5f);
        stack.scale(2, 2, 2);
    }

    @Override
    public String getID()
    {

        return "villager";
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

            private final ResourceLocation VILLAGER_BASE_SKIN = new ResourceLocation("textures/entity/villager/villager.png");

            @Override
            protected ResourceLocation getTextureLocation(PetBuddyEntity p_229139_1_)
            {

                return VILLAGER_BASE_SKIN;
            }

            @Override
            public IPetFormBase getForm()
            {

                return VillagerForm.this;
            }
        };
    }

}
