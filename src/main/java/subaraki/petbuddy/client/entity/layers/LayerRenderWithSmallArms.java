package subaraki.petbuddy.client.entity.layers;

import com.mojang.authlib.GameProfile;

import net.minecraft.client.entity.player.RemoteClientPlayerEntity;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.world.ClientWorld;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

public class LayerRenderWithSmallArms extends LayerRenderWithRegularArms {

    final PlayerModel<PetBuddyEntity> model_slim = new PlayerModel<>(0.0f, true);

    public LayerRenderWithSmallArms(IEntityRenderer<PetBuddyEntity, PlayerModel<PetBuddyEntity>> parent_renderer) {

        super(parent_renderer);
        this.model = model_slim;
    }

    @Override
    protected boolean shouldRenderWithArms(GameProfile profile, PetBuddyEntity buddy)
    {

        return !new RemoteClientPlayerEntity((ClientWorld) buddy.level, profile).getModelName().equals("default");
    }
}
