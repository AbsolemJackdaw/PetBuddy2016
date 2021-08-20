package subaraki.petbuddy.petform;

import java.util.ArrayList;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import subaraki.petbuddy.api.IChangeableForm;
import subaraki.petbuddy.api.IPetFormBase;
import subaraki.petbuddy.api.LayerPetFormBase;
import subaraki.petbuddy.client.entity.RenderEntityPetBuddy;
import subaraki.petbuddy.client.entity.model.HorseBuddyModel;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

public class HorseForm implements IPetFormBase, IChangeableForm {

    protected HorseBuddyModel model = new HorseBuddyModel(0.0f);
    protected int index = 0;
    protected int index_markings = 0;

    protected final ArrayList<ResourceLocation> TEXTURES = Util.make(Lists.newArrayList(), (list) -> {
        list.add(new ResourceLocation("textures/entity/horse/horse_white.png"));
        list.add(new ResourceLocation("textures/entity/horse/horse_creamy.png"));
        list.add(new ResourceLocation("textures/entity/horse/horse_chestnut.png"));
        list.add(new ResourceLocation("textures/entity/horse/horse_brown.png"));
        list.add(new ResourceLocation("textures/entity/horse/horse_black.png"));
        list.add(new ResourceLocation("textures/entity/horse/horse_gray.png"));
        list.add(new ResourceLocation("textures/entity/horse/horse_darkbrown.png"));
        list.add(new ResourceLocation("textures/entity/horse/donkey.png"));
        list.add(new ResourceLocation("textures/entity/horse/mule.png"));
        list.add(new ResourceLocation("textures/entity/horse/horse_zombie.png"));
        list.add(new ResourceLocation("textures/entity/horse/horse_skeleton.png"));
    });

    protected final ArrayList<ResourceLocation> MARKINGS = Util.make(Lists.newArrayList(), (list) -> {
        list.add((ResourceLocation) null);
        list.add(new ResourceLocation("textures/entity/horse/horse_markings_white.png"));
        list.add(new ResourceLocation("textures/entity/horse/horse_markings_whitefield.png"));
        list.add(new ResourceLocation("textures/entity/horse/horse_markings_whitedots.png"));
        list.add(new ResourceLocation("textures/entity/horse/horse_markings_blackdots.png"));
    });

    @Override
    public float getScale()
    {

        return 0.35f;
    }

    @Override
    public Item getFormItem()
    {

        return Items.SADDLE;
    }

    @Override
    public boolean canVisuallySwimLikePlayer()
    {

        return false;
    }

    @Override
    public void heldItemRotationAndOffset(MatrixStack stack)
    {

        stack.translate(-0.35, 0.25, -0.4);
        stack.scale(2, 2, 2);
    }

    @Override
    public String getID()
    {

        return "horse";
    }

    @Override
    public float getBob(PetBuddyEntity buddy, float tickCount)
    {

        return 0;
    }

    @Override
    public void tick(PetBuddyEntity buddy)
    {

    }

    @Override
    public float getNameRenderOffset()
    {

        return 0;
    }

    @Override
    public EntityModel<PetBuddyEntity> getDefaultModel()
    {

        return model;
    }

    @Override
    public void onSlotInsert()
    {

        index = rand.nextInt(TEXTURES.size());
        index_markings = rand.nextInt(MARKINGS.size());
    }

    @Override
    public LayerRenderer<PetBuddyEntity, PlayerModel<PetBuddyEntity>> getLayer(RenderEntityPetBuddy parent_renderer)
    {

        return new LayerPetFormBase(parent_renderer) {

            {
                parent_renderer.addLayer(new HorseBuddyMarkingsLayer(parent_renderer));
            }

            @Override
            protected ResourceLocation getTextureLocation(PetBuddyEntity p_229139_1_)
            {

                return TEXTURES.get(index);
            }

            @Override
            public IPetFormBase getForm()
            {

                return HorseForm.this;
            }
        };
    }

    protected class HorseBuddyMarkingsLayer extends LayerRenderer<PetBuddyEntity, PlayerModel<PetBuddyEntity>> {

        public HorseBuddyMarkingsLayer(IEntityRenderer<PetBuddyEntity, PlayerModel<PetBuddyEntity>> p_i232476_1_) {

            super(p_i232476_1_);
        }

        public void render(MatrixStack stack, IRenderTypeBuffer buffer, int the_int, PetBuddyEntity buddy, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_)
        {

            if (!buddy.getPetForm().equals(HorseForm.this))
                return;

            ResourceLocation resourcelocation = MARKINGS.get(index_markings);
            if (resourcelocation != null && !buddy.isInvisible())
            {

                coloredCutoutModelCopyLayerRender(this.getParentModel(), new HorseBuddyModel(0.08f), resourcelocation, stack, buffer, the_int, buddy,
                        p_225628_5_, p_225628_6_, p_225628_8_, p_225628_9_, p_225628_10_, p_225628_7_, 1f, 1f, 1f);

            }
        }
    }

}
