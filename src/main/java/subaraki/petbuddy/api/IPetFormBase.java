package subaraki.petbuddy.api;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.item.Item;
import subaraki.petbuddy.client.entity.RenderEntityPetBuddy;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

public interface IPetFormBase {

    public float getScale();

    public Item getFormItem();

    public boolean canVisuallySwimLikePlayer();

    public void heldItemRotationAndOffset(MatrixStack stack);

    public String getID();

    public float getBob(PetBuddyEntity buddy, float tickCount);

    public void tick(PetBuddyEntity buddy);
    
    public float getNameRenderOffset();

    public LayerRenderer<PetBuddyEntity, PlayerModel<PetBuddyEntity>> getLayer(RenderEntityPetBuddy parent_renderer);

}
