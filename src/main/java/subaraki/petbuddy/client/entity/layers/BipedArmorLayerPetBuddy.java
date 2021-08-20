package subaraki.petbuddy.client.entity.layers;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.IHasHead;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import subaraki.petbuddy.client.entity.RenderEntityPetBuddy;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

public class BipedArmorLayerPetBuddy extends BipedArmorLayer<PetBuddyEntity, PlayerModel<PetBuddyEntity>, BipedModel<PetBuddyEntity>> {

    public BipedArmorLayerPetBuddy(RenderEntityPetBuddy parent_renderer, BipedModel<PetBuddyEntity> modelA, BipedModel<PetBuddyEntity> modelB) {

        super(parent_renderer, modelA, modelB);
    }

    @Override
    public void render(MatrixStack stack, IRenderTypeBuffer p_225628_2_, int p_225628_3_, PetBuddyEntity entity, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_)
    {

        if (entity.getPetForm().getDefaultModel() instanceof IHasHead)
        {
            stack.pushPose();
            if (entity.getPetForm().getID().equals("villager"))
                stack.translate(0, -0.065f, 0);
            if (entity.getPetForm().getID().equals("enderman"))
                stack.translate(0, -0.0625 * 13, 0);

            super.render(stack, p_225628_2_, p_225628_3_, entity, p_225628_5_, p_225628_6_, p_225628_7_, p_225628_8_, p_225628_9_, p_225628_10_);
            stack.popPose();
        }

    }
}
