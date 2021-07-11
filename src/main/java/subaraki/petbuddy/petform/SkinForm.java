package subaraki.petbuddy.petform;

import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import subaraki.petbuddy.api.IPetFormBase;
import subaraki.petbuddy.client.entity.RenderEntityPetBuddy;
import subaraki.petbuddy.client.entity.layers.LayerRenderWithRegularArms;
import subaraki.petbuddy.server.entity.PetBuddyEntity;


public class SkinForm implements IPetFormBase {

    public static final String SKIN_ID = "skin";

    @Override
    public float getScale()
    {

        return 0.35f;
    }

    @Override
    public Item getFormItem()
    {

        return Items.NAME_TAG;
    }

    @Override
    public boolean canVisuallySwimLikePlayer()
    {

        return true;
    }

    @Override
    public float heldItemOffset()
    {

        return 0;
    }

    @Override
    public String getID()
    {

        return SKIN_ID;
    }

    @Override
    public LayerRenderer<PetBuddyEntity, PlayerModel<PetBuddyEntity>> getLayer(RenderEntityPetBuddy parent_renderer)
    {

        return new LayerRenderWithRegularArms(parent_renderer);
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
