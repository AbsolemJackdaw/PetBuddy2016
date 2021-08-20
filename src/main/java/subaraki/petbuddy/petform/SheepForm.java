package subaraki.petbuddy.petform;

import java.util.Map;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.entity.model.QuadrupedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Quaternion;
import subaraki.petbuddy.api.IPetFormBase;
import subaraki.petbuddy.api.LayerPetFormBase;
import subaraki.petbuddy.client.entity.RenderEntityPetBuddy;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

public class SheepForm implements IPetFormBase {

    protected final SheepBuddyModel model = new SheepBuddyModel();
    protected final SheepBuddyWoolModel modelWool = new SheepBuddyWoolModel();

    protected int eatAnimationTick = 0;

    protected Item item;

    private static final Map<IItemProvider, DyeColor> ITEM_BY_DYE = Util.make(Maps.newHashMap(), (map) -> {
        map.put(Items.WHITE_WOOL, DyeColor.WHITE);
        map.put(Items.ORANGE_WOOL, DyeColor.ORANGE);
        map.put(Items.MAGENTA_WOOL, DyeColor.MAGENTA);
        map.put(Items.LIGHT_BLUE_WOOL, DyeColor.LIGHT_BLUE);
        map.put(Items.YELLOW_WOOL, DyeColor.YELLOW);
        map.put(Items.LIME_WOOL, DyeColor.LIME);
        map.put(Items.PINK_WOOL, DyeColor.PINK);
        map.put(Items.GRAY_WOOL, DyeColor.GRAY);
        map.put(Items.LIGHT_GRAY_WOOL, DyeColor.LIGHT_GRAY);
        map.put(Items.CYAN_WOOL, DyeColor.CYAN);
        map.put(Items.PURPLE_WOOL, DyeColor.PURPLE);
        map.put(Items.BLUE_WOOL, DyeColor.BLUE);
        map.put(Items.BROWN_WOOL, DyeColor.BROWN);
        map.put(Items.GREEN_WOOL, DyeColor.GREEN);
        map.put(Items.RED_WOOL, DyeColor.RED);
        map.put(Items.BLACK_WOOL, DyeColor.BLACK);
    });

    public SheepForm(Item item) {

        this.item = item;
    }

    @Override
    public float getScale()
    {

        return 0.5f;
    }

    @Override
    public Item getFormItem()
    {

        return item;
    }

    @Override
    public boolean canVisuallySwimLikePlayer()
    {

        return false;
    }

    @Override
    public void heldItemRotationAndOffset(MatrixStack stack)
    {

        stack.translate(-0.05f, 0.2f, 0);
        stack.mulPose(new Quaternion(0, 45, 90, true));
        stack.scale(1.5f, 1.5f, 1.5f);
    }

    @Override
    public String getID()
    {

        return "sheep";
    }

    @Override
    public float getBob(PetBuddyEntity buddy, float tickCount)
    {

        return 0;
    }

    @Override
    public void tick(PetBuddyEntity buddy)
    {

        this.eatAnimationTick = Math.max(0, this.eatAnimationTick - 1);

        // like 8 times a day ?
        if (rand.nextInt(8000)+4000 == 0 && eatAnimationTick == 0)
        {
            eatAnimationTick = 40;
        }
    }

    @Override
    public float getNameRenderOffset()
    {

        return 0.2f;
    }

    public float getHeadEatAngleScale(PetBuddyEntity buddy, float p_70890_1_)
    {

        if (this.eatAnimationTick > 4 && this.eatAnimationTick <= 36)
        {
            float f = ((float) (this.eatAnimationTick - 4) - p_70890_1_) / 32.0F;
            return ((float) Math.PI / 5F) + 0.21991149F * MathHelper.sin(f * 28.7F);
        }
        else
        {
            return this.eatAnimationTick > 0 ? ((float) Math.PI / 5F) : buddy.xRot * ((float) Math.PI / 180F);
        }
    }

    public float getHeadEatPositionScale(float p_70894_1_)
    {

        if (this.eatAnimationTick <= 0)
        {
            return 0.0F;
        }
        else
            if (this.eatAnimationTick >= 4 && this.eatAnimationTick <= 36)
            {
                return 1.0F;
            }
            else
            {
                return this.eatAnimationTick < 4 ? ((float) this.eatAnimationTick - p_70894_1_) / 4.0F
                        : -((float) (this.eatAnimationTick - 40) - p_70894_1_) / 4.0F;
            }
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

            private final ResourceLocation SHEEP_LOCATION = new ResourceLocation("textures/entity/sheep/sheep.png");
            private final ResourceLocation SHEEP_FUR_LOCATION = new ResourceLocation("textures/entity/sheep/sheep_fur.png");

            {

                parent_renderer.addLayer(new LayerRenderer<PetBuddyEntity, PlayerModel<PetBuddyEntity>>(parent_renderer) {

                    @Override
                    public void render(MatrixStack p_225628_1_, IRenderTypeBuffer p_225628_2_, int p_225628_3_, PetBuddyEntity buddy, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_)
                    {

                        // check for wool in slot to prevent rendering the same layer 16 times
                        if (!buddy.getPetForm().equals(SheepForm.this) && !getFormItem().equals(buddy.getInventory().getStackInSlot(2).getItem()))
                            return;

                        if (!buddy.isInvisible())
                        {
                            float f;
                            float f1;
                            float f2;
                            if (buddy.hasCustomName() && "jeb_".equals(buddy.getName().getContents()))
                            {
                                int i = buddy.tickCount / 25 + buddy.getId();
                                int j = DyeColor.values().length;
                                int k = i % j;
                                int l = (i + 1) % j;
                                float f3 = ((float) (buddy.tickCount % 25) + p_225628_7_) / 25.0F;
                                float[] afloat1 = SheepEntity.getColorArray(DyeColor.byId(k));
                                float[] afloat2 = SheepEntity.getColorArray(DyeColor.byId(l));
                                f = afloat1[0] * (1.0F - f3) + afloat2[0] * f3;
                                f1 = afloat1[1] * (1.0F - f3) + afloat2[1] * f3;
                                f2 = afloat1[2] * (1.0F - f3) + afloat2[2] * f3;
                            }
                            else
                            {
                                float[] afloat = ITEM_BY_DYE.get(getFormItem()).getTextureDiffuseColors();
                                f = afloat[0];
                                f1 = afloat[1];
                                f2 = afloat[2];
                            }

                            coloredCutoutModelCopyLayerRender(this.getParentModel(), modelWool, SHEEP_FUR_LOCATION, p_225628_1_, p_225628_2_, p_225628_3_,
                                    buddy, p_225628_5_, p_225628_6_, p_225628_8_, p_225628_9_, p_225628_10_, p_225628_7_, f, f1, f2);
                        }

                    }
                });
            }

            @Override
            protected ResourceLocation getTextureLocation(PetBuddyEntity p_229139_1_)
            {

                return SHEEP_LOCATION;
            }

            @Override
            public IPetFormBase getForm()
            {

                return SheepForm.this;
            }
        };
    }

    private class SheepBuddyModel extends QuadrupedModel<PetBuddyEntity> {

        private float headXRot;

        public SheepBuddyModel() {

            super(12, 0.0F, false, 8.0F, 4.0F, 2.0F, 2.0F, 24);
            this.head = new ModelRenderer(this, 0, 0);
            this.head.addBox(-3.0F, -4.0F, -6.0F, 6.0F, 6.0F, 8.0F, 0.0F);
            this.head.setPos(0.0F, 6.0F, -8.0F);
            this.body = new ModelRenderer(this, 28, 8);
            this.body.addBox(-4.0F, -10.0F, -7.0F, 8.0F, 16.0F, 6.0F, 0.0F);
            this.body.setPos(0.0F, 5.0F, 2.0F);
        }

        public void prepareMobModel(PetBuddyEntity buddy, float f1, float f2, float f3)
        {

            super.prepareMobModel(buddy, f1, f2, f3);
            this.head.y = 6.0F + getHeadEatPositionScale(f3) * 9.0F;
            this.headXRot = getHeadEatAngleScale(buddy, f3);
        }

        public void setupAnim(PetBuddyEntity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_)
        {

            super.setupAnim(p_225597_1_, p_225597_2_, p_225597_3_, p_225597_4_, p_225597_5_, p_225597_6_);
            this.head.xRot = this.headXRot;
        }
    }

    private class SheepBuddyWoolModel extends QuadrupedModel<PetBuddyEntity> {

        private float headXRot;

        public SheepBuddyWoolModel() {

            super(12, 0.0F, false, 8.0F, 4.0F, 2.0F, 2.0F, 24);
            this.head = new ModelRenderer(this, 0, 0);
            this.head.addBox(-3.0F, -4.0F, -4.0F, 6.0F, 6.0F, 6.0F, 0.6F);
            this.head.setPos(0.0F, 6.0F, -8.0F);
            this.body = new ModelRenderer(this, 28, 8);
            this.body.addBox(-4.0F, -10.0F, -7.0F, 8.0F, 16.0F, 6.0F, 1.75F);
            this.body.setPos(0.0F, 5.0F, 2.0F);
            this.leg0 = new ModelRenderer(this, 0, 16);
            this.leg0.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.5F);
            this.leg0.setPos(-3.0F, 12.0F, 7.0F);
            this.leg1 = new ModelRenderer(this, 0, 16);
            this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.5F);
            this.leg1.setPos(3.0F, 12.0F, 7.0F);
            this.leg2 = new ModelRenderer(this, 0, 16);
            this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.5F);
            this.leg2.setPos(-3.0F, 12.0F, -5.0F);
            this.leg3 = new ModelRenderer(this, 0, 16);
            this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.5F);
            this.leg3.setPos(3.0F, 12.0F, -5.0F);
        }

        public void prepareMobModel(PetBuddyEntity buddy, float f1, float f2, float f3)
        {

            super.prepareMobModel(buddy, f1, f2, f3);
            this.head.y = 6.0F + getHeadEatPositionScale(f3) * 9.0F;
            this.headXRot = getHeadEatAngleScale(buddy, f3);
        }

        public void setupAnim(PetBuddyEntity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_)
        {

            super.setupAnim(p_225597_1_, p_225597_2_, p_225597_3_, p_225597_4_, p_225597_5_, p_225597_6_);
            this.head.xRot = this.headXRot;
        }
    }
}
