package subaraki.petbuddy.client.entity;

import java.util.Map;
import java.util.UUID;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.culling.ClippingHelper;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.layers.HeadLayer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

public class RenderEntityPetBuddy extends LivingRenderer<PetBuddyEntity, PlayerModel<PetBuddyEntity>> {

    static final PlayerModel<PetBuddyEntity> model = new PlayerModel<>(0.0f, false);
    static final PlayerModel<PetBuddyEntity> model_slim = new PlayerModel<>(0.0f, true);

    public RenderEntityPetBuddy(EntityRendererManager manager) {

        super(manager, model, 0.25f);
        this.addLayer(new BipedArmorLayer<>(this, new BipedModel<PetBuddyEntity>(0.5f), new BipedModel<PetBuddyEntity>(1.0f)));
        this.addLayer(new HeldItemLayer<>(this));
        this.addLayer(new Deadmau5HeadLayerBuddy(this));
        this.addLayer(new HeadLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(PetBuddyEntity buddy)
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

        return new ResourceLocation("minecraft", "textures/entity/steve.png");
    }

    @Override
    protected void scale(PetBuddyEntity buddy, MatrixStack stack, float p_225620_3_)
    {

        float scale = 0.35f;
        stack.scale(scale, scale, scale);
        super.scale(buddy, stack, p_225620_3_);
    }

    @Override
    public void render(PetBuddyEntity entity, float unused, float partialRenderTick, MatrixStack stack, IRenderTypeBuffer buffer, int light)
    {

        if (entity.isCrouching())
            model.crouching = true;
        else
            model.crouching = false;

        super.render(entity, unused, partialRenderTick, stack, buffer, light);

    }

    @Override
    public boolean shouldRender(PetBuddyEntity p_225626_1_, ClippingHelper p_225626_2_, double p_225626_3_, double p_225626_5_, double p_225626_7_)
    {

        // TODO if owner is invisible, don't render ?
        return super.shouldRender(p_225626_1_, p_225626_2_, p_225626_3_, p_225626_5_, p_225626_7_);
    }

    @Override
    protected void renderNameTag(PetBuddyEntity buddy, ITextComponent text, MatrixStack matrix, IRenderTypeBuffer buffer, int light)
    {

        double d0 = this.entityRenderDispatcher.distanceToSqr(buddy);
        if (net.minecraftforge.client.ForgeHooksClient.isNameplateInRenderDistance(buddy, d0))
        {
            boolean flag = !buddy.isDiscrete();
            float f = buddy.getBbHeight() + 0.5F;
            int i = "deadmau5".equals(text.getString()) ? -10 : 0;
            matrix.pushPose();
            matrix.translate(0.0D, (double) f, 0.0D);
            matrix.mulPose(this.entityRenderDispatcher.cameraOrientation());
            matrix.scale(-0.025F, -0.025F, 0.025F);
            Matrix4f matrix4f = matrix.last().pose();
            float f1 = Minecraft.getInstance().options.getBackgroundOpacity(0.25F);
            int j = (int) (f1 * 255.0F) << 24;
            FontRenderer fontrenderer = this.getFont();
            TextFormatting health_color = buddy.getHealth() <= buddy.getMaxHealth() * 0.25 ? TextFormatting.RED
                    : buddy.getHealth() <= buddy.getMaxHealth() * 0.5 ? TextFormatting.GOLD
                            : buddy.getHealth() <= buddy.getMaxHealth() * 0.75 ? TextFormatting.DARK_GREEN : TextFormatting.GREEN;
            IFormattableTextComponent colored_text = new StringTextComponent(text.getString())
                    .setStyle(Style.EMPTY.withColor(Color.fromLegacyFormat(health_color)));
            float f2 = (float) (-fontrenderer.width(text) / 2);
            fontrenderer.drawInBatch(colored_text, f2, (float) i, 553648127, false, matrix4f, buffer, flag, j, light);
            if (flag)
            {
                fontrenderer.drawInBatch(colored_text, f2, (float) i, -1, false, matrix4f, buffer, false, 0, light);
            }

            matrix.popPose();
        }
    }

    @Override
    protected void setupRotations(PetBuddyEntity p_225621_1_, MatrixStack p_225621_2_, float p_225621_3_, float p_225621_4_, float p_225621_5_)
    {

        float swim_ammount = p_225621_1_.getSwimAmount(p_225621_5_);

        if (swim_ammount > 0.0F)
        {
            super.setupRotations(p_225621_1_, p_225621_2_, p_225621_3_, p_225621_4_, p_225621_5_);
            float f3 = p_225621_1_.isInWater() ? -90.0F - p_225621_1_.xRot : -90.0F;
            float f4 = MathHelper.lerp(swim_ammount, 0.0F, f3);
            p_225621_2_.mulPose(Vector3f.XP.rotationDegrees(f4));
            if (p_225621_1_.isVisuallySwimming())
            {
                p_225621_2_.translate(0.0D, -0.3D, (double) 0.35F);
            }
        }
        else

            super.setupRotations(p_225621_1_, p_225621_2_, p_225621_3_, p_225621_4_, p_225621_5_);
    }
}
