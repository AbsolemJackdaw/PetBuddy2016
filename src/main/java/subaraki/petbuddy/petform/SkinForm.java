package subaraki.petbuddy.petform;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import subaraki.petbuddy.api.IPetFormBase;
import subaraki.petbuddy.client.entity.RenderEntityPetBuddy;
import subaraki.petbuddy.client.entity.layers.LayerRenderWithRegularArms;
import subaraki.petbuddy.server.entity.PetBuddyEntity;


public class SkinForm implements IPetFormBase {

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
    public void heldItemRotationAndOffset(MatrixStack stack)
    {

    }

    @Override
    public String getID()
    {

        return "skin";
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
    
    @Override
    public float getNameRenderOffset()
    {

        return 0;
    }

}
