package subaraki.petbuddy.api;

import java.util.Random;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import subaraki.petbuddy.client.entity.RenderEntityPetBuddy;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

@OnlyIn(Dist.CLIENT)
public interface IPetFormBase {

    public Random rand = new Random();

    public float getScale();

    public Item getFormItem();

    public boolean canVisuallySwimLikePlayer();

    public void heldItemRotationAndOffset(MatrixStack stack);

    public String getID();

    public float getBob(PetBuddyEntity buddy, float tickCount);

    public void tick(PetBuddyEntity buddy);

    public float getNameRenderOffset();

    public EntityModel<PetBuddyEntity> getDefaultModel();

    public LayerRenderer<PetBuddyEntity, PlayerModel<PetBuddyEntity>> getLayer(RenderEntityPetBuddy parent_renderer);

}
