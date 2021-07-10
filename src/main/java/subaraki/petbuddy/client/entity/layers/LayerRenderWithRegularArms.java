package subaraki.petbuddy.client.entity.layers;

import java.util.Map;
import java.util.UUID;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.RemoteClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import subaraki.petbuddy.client.entity.EnumPetForm;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

public class LayerRenderWithRegularArms extends LayerRenderer<PetBuddyEntity, PlayerModel<PetBuddyEntity>> {

    public PlayerModel<PetBuddyEntity> model;

    public LayerRenderWithRegularArms(IEntityRenderer<PetBuddyEntity, PlayerModel<PetBuddyEntity>> parent_renderer) {

        super(parent_renderer);
        model = new PlayerModel<>(0.0f, false);
    }

    @Override
    public void render(MatrixStack stack, IRenderTypeBuffer buffer, int the_int, PetBuddyEntity buddy, float f1, float f2, float f3, float f4, float f5, float f6)
    {

        GameProfile profile = buddy.getClientOwnerSkin();

        if (buddy.getSkinForm() != null)
            profile = buddy.getSkinForm();

        if (profile == null || shouldRenderWithArms(profile, buddy) && !buddy.getPetForm().equals(EnumPetForm.SKIN))
            return;

        if (buddy.isCrouching())
            model.crouching = true;
        else
            model.crouching = false;

        this.getParentModel().copyPropertiesTo(model);
        model.jacket.copyFrom(this.getParentModel().jacket);
        model.leftSleeve.copyFrom(this.getParentModel().leftSleeve);
        model.rightSleeve.copyFrom(this.getParentModel().rightSleeve);
        model.leftPants.copyFrom(this.getParentModel().leftPants);
        model.rightPants.copyFrom(this.getParentModel().rightPants);

        IVertexBuilder ivertexbuilder = buffer.getBuffer(RenderType.entityCutoutNoCull(getTextureLocation(buddy)));
        model.renderToBuffer(stack, ivertexbuilder, the_int, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

    }

    protected boolean shouldRenderWithArms(GameProfile profile, PetBuddyEntity buddy)
    {

        return new RemoteClientPlayerEntity((ClientWorld) buddy.level, profile).getModelName().equals("default");
    }

    @Override
    protected ResourceLocation getTextureLocation(PetBuddyEntity buddy)
    {

        UUID uuid = buddy.getOwnerUUID();
        if (uuid != null)
            if (buddy.level != null)
            {
                PlayerEntity owner = buddy.level.getPlayerByUUID(uuid);
                if (owner != null)
                {
                    GameProfile profile = buddy.getClientOwnerSkin();

                    if (buddy.getSkinForm() != null)
                        profile = buddy.getSkinForm();

                    Minecraft minecraft = Minecraft.getInstance();
                    Map<Type, MinecraftProfileTexture> map = minecraft.getSkinManager().getInsecureSkinInformation(profile);
                    return map.containsKey(Type.SKIN) ? minecraft.getSkinManager().registerTexture(map.get(Type.SKIN), Type.SKIN)
                            : DefaultPlayerSkin.getDefaultSkin(PlayerEntity.createPlayerUUID(profile));

                }

            }

        return super.getTextureLocation(buddy);
    }
}
