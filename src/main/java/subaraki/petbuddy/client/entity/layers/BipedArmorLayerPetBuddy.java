package subaraki.petbuddy.client.entity.layers;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import subaraki.petbuddy.api.PetForms;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

public class BipedArmorLayerPetBuddy extends BipedArmorLayer<PetBuddyEntity, PlayerModel<PetBuddyEntity>, BipedModel<PetBuddyEntity>> {

    public BipedArmorLayerPetBuddy(IEntityRenderer<PetBuddyEntity, PlayerModel<PetBuddyEntity>> parent_renderer, BipedModel<PetBuddyEntity> modelA, BipedModel<PetBuddyEntity> modelB) {

        super(parent_renderer, modelA, modelB);
    }

    @Override
    public void render(MatrixStack p_225628_1_, IRenderTypeBuffer p_225628_2_, int p_225628_3_, PetBuddyEntity entity, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_)
    {

        if (entity instanceof PetBuddyEntity)
        {
            PetBuddyEntity buddy = (PetBuddyEntity) entity;
            if (buddy.getPetForm().equals(PetForms.DEFAULT))
                super.render(p_225628_1_, p_225628_2_, p_225628_3_, entity, p_225628_5_, p_225628_6_, p_225628_7_, p_225628_8_, p_225628_9_, p_225628_10_);
        }

    }
}
