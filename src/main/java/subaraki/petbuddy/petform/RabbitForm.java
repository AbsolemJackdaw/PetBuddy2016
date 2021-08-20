package subaraki.petbuddy.petform;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Quaternion;
import subaraki.petbuddy.api.IChangeableForm;
import subaraki.petbuddy.api.IPetFormBase;
import subaraki.petbuddy.api.LayerPetFormBase;
import subaraki.petbuddy.client.entity.RenderEntityPetBuddy;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

public class RabbitForm implements IPetFormBase, IChangeableForm {

    protected RabbitBuddyModel model = new RabbitBuddyModel();
    private int jumpTicks;
    private int jumpDuration;

    private int textureIndex;

    @Override
    public float getScale()
    {

        return 1.0f;
    }

    @Override
    public Item getFormItem()
    {

        return Items.RABBIT_FOOT;
    }

    @Override
    public boolean canVisuallySwimLikePlayer()
    {

        return false;
    }

    @Override
    public void heldItemRotationAndOffset(MatrixStack stack)
    {

        stack.translate(0, 1.15, 0.2);
        stack.mulPose(new Quaternion(-20, 90, -90, true));
        stack.scale(0.75f, 0.75f, 0.75f);
    }

    @Override
    public String getID()
    {

        return "rabbit";
    }

    @Override
    public float getBob(PetBuddyEntity buddy, float tickCount)
    {

        return 0;
    }

    @Override
    public void tick(PetBuddyEntity buddy)
    {

        if (this.jumpTicks != this.jumpDuration)
        {
            ++this.jumpTicks;
        }
        else
            if (this.jumpDuration != 0)
            {
                this.jumpTicks = 0;
                this.jumpDuration = 0;
            }

        if ((buddy.getDeltaMovement().x != 0 || buddy.getDeltaMovement().z != 0) && jumpDuration == 0)
        {
            startJumping();
        }
    }

    @Override
    public float getNameRenderOffset()
    {

        return 0f;
    }

    @Override
    public EntityModel<PetBuddyEntity> getDefaultModel()
    {

        return model;
    }

    @Override
    public LayerRenderer<PetBuddyEntity, PlayerModel<PetBuddyEntity>> getLayer(RenderEntityPetBuddy parent_renderer)
    {

        return new LayerPetFormBase(parent_renderer) {

            private final ResourceLocation[] RABBIT = new ResourceLocation[] { new ResourceLocation("textures/entity/rabbit/brown.png"),
                    new ResourceLocation("textures/entity/rabbit/white.png"), new ResourceLocation("textures/entity/rabbit/black.png"),
                    new ResourceLocation("textures/entity/rabbit/gold.png"), new ResourceLocation("textures/entity/rabbit/salt.png"),
                    new ResourceLocation("textures/entity/rabbit/white_splotched.png"), new ResourceLocation("textures/entity/rabbit/toast.png"),
                    new ResourceLocation("textures/entity/rabbit/caerbannog.png") };

            @Override
            protected ResourceLocation getTextureLocation(PetBuddyEntity p_229139_1_)
            {

                return RABBIT[textureIndex];
            }

            @Override
            public IPetFormBase getForm()
            {

                return RabbitForm.this;
            }
        };
    }

    @Override
    public void onSlotInsert()
    {

        textureIndex = rand.nextInt(8);
    }

    public float getJumpCompletion(float p_175521_1_)
    {

        return this.jumpDuration == 0 ? 0.0F : ((float) this.jumpTicks + p_175521_1_) / (float) this.jumpDuration;
    }

    public void startJumping()
    {

        this.jumpDuration = 2;
        this.jumpTicks = 0;
    }

    protected class RabbitBuddyModel extends EntityModel<PetBuddyEntity> {

        private final ModelRenderer rearFootLeft = new ModelRenderer(this, 26, 24);
        private final ModelRenderer rearFootRight;
        private final ModelRenderer haunchLeft;
        private final ModelRenderer haunchRight;
        private final ModelRenderer body;
        private final ModelRenderer frontLegLeft;
        private final ModelRenderer frontLegRight;
        private final ModelRenderer head;
        private final ModelRenderer earRight;
        private final ModelRenderer earLeft;
        private final ModelRenderer tail;
        private final ModelRenderer nose;
        private float jumpRotation;

        public RabbitBuddyModel() {

            this.rearFootLeft.addBox(-1.0F, 5.5F, -3.7F, 2.0F, 1.0F, 7.0F);
            this.rearFootLeft.setPos(3.0F, 17.5F, 3.7F);
            this.rearFootLeft.mirror = true;
            this.setRotation(this.rearFootLeft, 0.0F, 0.0F, 0.0F);
            this.rearFootRight = new ModelRenderer(this, 8, 24);
            this.rearFootRight.addBox(-1.0F, 5.5F, -3.7F, 2.0F, 1.0F, 7.0F);
            this.rearFootRight.setPos(-3.0F, 17.5F, 3.7F);
            this.rearFootRight.mirror = true;
            this.setRotation(this.rearFootRight, 0.0F, 0.0F, 0.0F);
            this.haunchLeft = new ModelRenderer(this, 30, 15);
            this.haunchLeft.addBox(-1.0F, 0.0F, 0.0F, 2.0F, 4.0F, 5.0F);
            this.haunchLeft.setPos(3.0F, 17.5F, 3.7F);
            this.haunchLeft.mirror = true;
            this.setRotation(this.haunchLeft, -0.34906584F, 0.0F, 0.0F);
            this.haunchRight = new ModelRenderer(this, 16, 15);
            this.haunchRight.addBox(-1.0F, 0.0F, 0.0F, 2.0F, 4.0F, 5.0F);
            this.haunchRight.setPos(-3.0F, 17.5F, 3.7F);
            this.haunchRight.mirror = true;
            this.setRotation(this.haunchRight, -0.34906584F, 0.0F, 0.0F);
            this.body = new ModelRenderer(this, 0, 0);
            this.body.addBox(-3.0F, -2.0F, -10.0F, 6.0F, 5.0F, 10.0F);
            this.body.setPos(0.0F, 19.0F, 8.0F);
            this.body.mirror = true;
            this.setRotation(this.body, -0.34906584F, 0.0F, 0.0F);
            this.frontLegLeft = new ModelRenderer(this, 8, 15);
            this.frontLegLeft.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F);
            this.frontLegLeft.setPos(3.0F, 17.0F, -1.0F);
            this.frontLegLeft.mirror = true;
            this.setRotation(this.frontLegLeft, -0.17453292F, 0.0F, 0.0F);
            this.frontLegRight = new ModelRenderer(this, 0, 15);
            this.frontLegRight.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F);
            this.frontLegRight.setPos(-3.0F, 17.0F, -1.0F);
            this.frontLegRight.mirror = true;
            this.setRotation(this.frontLegRight, -0.17453292F, 0.0F, 0.0F);
            this.head = new ModelRenderer(this, 32, 0);
            this.head.addBox(-2.5F, -4.0F, -5.0F, 5.0F, 4.0F, 5.0F);
            this.head.setPos(0.0F, 16.0F, -1.0F);
            this.head.mirror = true;
            this.setRotation(this.head, 0.0F, 0.0F, 0.0F);
            this.earRight = new ModelRenderer(this, 52, 0);
            this.earRight.addBox(-2.5F, -9.0F, -1.0F, 2.0F, 5.0F, 1.0F);
            this.earRight.setPos(0.0F, 16.0F, -1.0F);
            this.earRight.mirror = true;
            this.setRotation(this.earRight, 0.0F, -0.2617994F, 0.0F);
            this.earLeft = new ModelRenderer(this, 58, 0);
            this.earLeft.addBox(0.5F, -9.0F, -1.0F, 2.0F, 5.0F, 1.0F);
            this.earLeft.setPos(0.0F, 16.0F, -1.0F);
            this.earLeft.mirror = true;
            this.setRotation(this.earLeft, 0.0F, 0.2617994F, 0.0F);
            this.tail = new ModelRenderer(this, 52, 6);
            this.tail.addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 2.0F);
            this.tail.setPos(0.0F, 20.0F, 7.0F);
            this.tail.mirror = true;
            this.setRotation(this.tail, -0.3490659F, 0.0F, 0.0F);
            this.nose = new ModelRenderer(this, 32, 9);
            this.nose.addBox(-0.5F, -2.5F, -5.5F, 1.0F, 1.0F, 1.0F);
            this.nose.setPos(0.0F, 16.0F, -1.0F);
            this.nose.mirror = true;
            this.setRotation(this.nose, 0.0F, 0.0F, 0.0F);
        }

        private void setRotation(ModelRenderer p_178691_1_, float p_178691_2_, float p_178691_3_, float p_178691_4_)
        {

            p_178691_1_.xRot = p_178691_2_;
            p_178691_1_.yRot = p_178691_3_;
            p_178691_1_.zRot = p_178691_4_;
        }

        public void renderToBuffer(MatrixStack p_225598_1_, IVertexBuilder p_225598_2_, int p_225598_3_, int p_225598_4_, float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_)
        {

            if (this.young)
            {
                p_225598_1_.pushPose();
                p_225598_1_.scale(0.56666666F, 0.56666666F, 0.56666666F);
                p_225598_1_.translate(0.0D, 1.375D, 0.125D);
                ImmutableList.of(this.head, this.earLeft, this.earRight, this.nose).forEach((p_228292_8_) -> {
                    p_228292_8_.render(p_225598_1_, p_225598_2_, p_225598_3_, p_225598_4_, p_225598_5_, p_225598_6_, p_225598_7_, p_225598_8_);
                });
                p_225598_1_.popPose();
                p_225598_1_.pushPose();
                p_225598_1_.scale(0.4F, 0.4F, 0.4F);
                p_225598_1_.translate(0.0D, 2.25D, 0.0D);
                ImmutableList.of(this.rearFootLeft, this.rearFootRight, this.haunchLeft, this.haunchRight, this.body, this.frontLegLeft, this.frontLegRight,
                        this.tail).forEach((p_228291_8_) -> {
                            p_228291_8_.render(p_225598_1_, p_225598_2_, p_225598_3_, p_225598_4_, p_225598_5_, p_225598_6_, p_225598_7_, p_225598_8_);
                        });
                p_225598_1_.popPose();
            }
            else
            {
                p_225598_1_.pushPose();
                p_225598_1_.scale(0.6F, 0.6F, 0.6F);
                p_225598_1_.translate(0.0D, 1.0D, 0.0D);
                ImmutableList.of(this.rearFootLeft, this.rearFootRight, this.haunchLeft, this.haunchRight, this.body, this.frontLegLeft, this.frontLegRight,
                        this.head, this.earRight, this.earLeft, this.tail, this.nose).forEach((p_228290_8_) -> {
                            p_228290_8_.render(p_225598_1_, p_225598_2_, p_225598_3_, p_225598_4_, p_225598_5_, p_225598_6_, p_225598_7_, p_225598_8_);
                        });
                p_225598_1_.popPose();
            }

        }

        public void setupAnim(PetBuddyEntity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_)
        {

            float f = p_225597_4_ - (float) p_225597_1_.tickCount;
            this.nose.xRot = p_225597_6_ * ((float) Math.PI / 180F);
            this.head.xRot = p_225597_6_ * ((float) Math.PI / 180F);
            this.earRight.xRot = p_225597_6_ * ((float) Math.PI / 180F);
            this.earLeft.xRot = p_225597_6_ * ((float) Math.PI / 180F);
            this.nose.yRot = p_225597_5_ * ((float) Math.PI / 180F);
            this.head.yRot = p_225597_5_ * ((float) Math.PI / 180F);
            this.earRight.yRot = this.nose.yRot - 0.2617994F;
            this.earLeft.yRot = this.nose.yRot + 0.2617994F;
            this.jumpRotation = MathHelper.sin(getJumpCompletion(f) * (float) Math.PI);
            this.haunchLeft.xRot = (this.jumpRotation * 50.0F - 21.0F) * ((float) Math.PI / 180F);
            this.haunchRight.xRot = (this.jumpRotation * 50.0F - 21.0F) * ((float) Math.PI / 180F);
            this.rearFootLeft.xRot = this.jumpRotation * 50.0F * ((float) Math.PI / 180F);
            this.rearFootRight.xRot = this.jumpRotation * 50.0F * ((float) Math.PI / 180F);
            this.frontLegLeft.xRot = (this.jumpRotation * -40.0F - 11.0F) * ((float) Math.PI / 180F);
            this.frontLegRight.xRot = (this.jumpRotation * -40.0F - 11.0F) * ((float) Math.PI / 180F);
        }

        public void prepareMobModel(PetBuddyEntity p_212843_1_, float p_212843_2_, float p_212843_3_, float p_212843_4_)
        {

            super.prepareMobModel(p_212843_1_, p_212843_2_, p_212843_3_, p_212843_4_);
            this.jumpRotation = MathHelper.sin(getJumpCompletion(p_212843_4_) * (float) Math.PI);
        }
    }
}
