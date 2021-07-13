package subaraki.petbuddy.client.entity;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.culling.ClippingHelper;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.HeadLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
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
import subaraki.petbuddy.api.IPetFormBase;
import subaraki.petbuddy.api.PetForms;
import subaraki.petbuddy.client.entity.layers.BipedArmorLayerPetBuddy;
import subaraki.petbuddy.client.entity.layers.Deadmau5HeadLayerBuddy;
import subaraki.petbuddy.client.entity.layers.HeldBuddyItemLayer;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

public class RenderEntityPetBuddy extends LivingRenderer<PetBuddyEntity, PlayerModel<PetBuddyEntity>> {

    static final PlayerModel<PetBuddyEntity> model = new PlayerModel<>(0.0f, false);

    public RenderEntityPetBuddy(EntityRendererManager manager) {

        super(manager, model, 0.25f);

        for (IPetFormBase form : PetForms.all())
        {
            this.addLayer(form.getLayer(this));
        }

        this.addLayer(new BipedArmorLayerPetBuddy(this, new BipedModel<>(0.5f), new BipedModel<>(1.0F)));
        this.addLayer(new HeldBuddyItemLayer(this));

        this.addLayer(new Deadmau5HeadLayerBuddy(this));
        this.addLayer(new HeadLayer<>(this));
        model.setAllVisible(false);
    }

    @Override
    public PlayerModel<PetBuddyEntity> getModel()
    {

        return super.getModel();
    }

    @Override
    public ResourceLocation getTextureLocation(PetBuddyEntity buddy)
    {

        return new ResourceLocation("minecraft", "textures/entity/steve.png");
    }

    @Override
    protected void scale(PetBuddyEntity buddy, MatrixStack stack, float p_225620_3_)
    {

        float scale = buddy.getPetForm().getScale();
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
            float f = (buddy.getBbHeight() + 0.25f ) + buddy.getPetForm().getNameRenderOffset();
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
    protected void setupRotations(PetBuddyEntity buddy, MatrixStack stack, float f1, float f2, float f3)
    {

        float swim_ammount = buddy.getSwimAmount(f3);

        if (swim_ammount > 0.0F && buddy.getPetForm().canVisuallySwimLikePlayer())
        {
            super.setupRotations(buddy, stack, f1, f2, f3);
            float rotationX = buddy.isInWater() ? -90.0F - buddy.xRot : -90.0F;
            float swim_lerp = MathHelper.lerp(swim_ammount, 0.0F, rotationX);
            stack.mulPose(Vector3f.XP.rotationDegrees(swim_lerp));
            if (buddy.isVisuallySwimming())
            {
                stack.translate(0.0D, -0.3D, (double) 0.35F);
            }
        }
        else

            super.setupRotations(buddy, stack, f1, f2, f3);
    }

    @Override
    protected float getBob(PetBuddyEntity buddy, float tick)
    {

        buddy.getPetForm().getBob(buddy, tick);

        return 0;
    }
}
