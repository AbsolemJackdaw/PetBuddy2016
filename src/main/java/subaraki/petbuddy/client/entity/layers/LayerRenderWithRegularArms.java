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
import subaraki.petbuddy.server.entity.PetBuddyEntity;

public class LayerRenderWithRegularArms extends LayerRenderer<PetBuddyEntity, PlayerModel<PetBuddyEntity>> {

    private final PlayerModel<PetBuddyEntity> modelSlim = new PlayerModel<>(0.0f, true);
    private PlayerModel<PetBuddyEntity> modelToRender = null;

    private RemoteClientPlayerEntity rcpe = null;

    public LayerRenderWithRegularArms(IEntityRenderer<PetBuddyEntity, PlayerModel<PetBuddyEntity>> parent_renderer) {

        super(parent_renderer);
    }

    @Override
    public void render(MatrixStack stack, IRenderTypeBuffer buffer, int the_int, PetBuddyEntity buddy, float f1, float f2, float f3, float f4, float f5, float f6)
    {

        GameProfile profile = buddy.getClientOwnerSkin();

        if (buddy.getSkinForm() != null)
            profile = buddy.getSkinForm();

        if (profile == null || !buddy.getPetForm().getID().equals("skin"))
            return;

        if (buddy.getPetForm().getDefaultModel() instanceof PlayerModel)
        {
            if (shouldRenderWithSmallArms(profile, buddy))
                modelToRender = modelSlim;
            else
                modelToRender = (PlayerModel<PetBuddyEntity>) buddy.getPetForm().getDefaultModel();
        }

        if (modelToRender != null)
        {
            if (buddy.isCrouching())
                modelToRender.crouching = true;
            else
                modelToRender.crouching = false;

            this.getParentModel().copyPropertiesTo(modelToRender);
            modelToRender.jacket.copyFrom(this.getParentModel().jacket);
            modelToRender.leftSleeve.copyFrom(this.getParentModel().leftSleeve);
            modelToRender.rightSleeve.copyFrom(this.getParentModel().rightSleeve);
            modelToRender.leftPants.copyFrom(this.getParentModel().leftPants);
            modelToRender.rightPants.copyFrom(this.getParentModel().rightPants);

            IVertexBuilder ivertexbuilder = buffer.getBuffer(RenderType.entityCutoutNoCull(getTextureLocation(buddy)));
            modelToRender.renderToBuffer(stack, ivertexbuilder, the_int, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }

    /***/
    protected boolean shouldRenderWithSmallArms(GameProfile profile, PetBuddyEntity buddy)
    {

        // if none exists
        if (rcpe == null)
            rcpe = new RemoteClientPlayerEntity((ClientWorld) buddy.level, profile);
        // if buddy changed skin / got a new name tag
        if (!rcpe.getGameProfile().equals(profile))
            rcpe = new RemoteClientPlayerEntity((ClientWorld) buddy.level, profile);

        return rcpe.getModelName().equals("slim");
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
