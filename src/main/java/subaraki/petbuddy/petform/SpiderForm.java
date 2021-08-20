package subaraki.petbuddy.petform;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.entity.model.SpiderModel;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;
import subaraki.petbuddy.api.IChangeableForm;
import subaraki.petbuddy.api.IPetFormBase;
import subaraki.petbuddy.api.LayerPetFormBase;
import subaraki.petbuddy.client.entity.RenderEntityPetBuddy;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

public class SpiderForm implements IPetFormBase, IChangeableForm {

    protected SpiderModel<PetBuddyEntity> model = new SpiderModel<>();
    protected final ResourceLocation CAVE_SPIDER = new ResourceLocation("textures/entity/spider/cave_spider.png");
    protected final ResourceLocation SPIDER = new ResourceLocation("textures/entity/spider/spider.png");

    protected int index = 0;

    @Override
    public float getScale()
    {

        return index == 0 ? 0.5f : 0.35f;
    }

    @Override
    public Item getFormItem()
    {

        return Items.SPIDER_EYE;
    }

    @Override
    public boolean canVisuallySwimLikePlayer()
    {

        return false;
    }

    @Override
    public void heldItemRotationAndOffset(MatrixStack stack)
    {
        stack.translate(0, 0.65f, 0.5);
        stack.scale(2, 2, 2);
        stack.mulPose(new Quaternion(0,0,90,true));
    }

    @Override
    public String getID()
    {

        return "spider";
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

        return index == 0 ? 0f : -0.2f;
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

            @Override
            protected ResourceLocation getTextureLocation(PetBuddyEntity p_229139_1_)
            {

                return index == 0 ? SPIDER : CAVE_SPIDER;
            }

            @Override
            public IPetFormBase getForm()
            {

                return SpiderForm.this;
            }
        };
    }

    @Override
    public void onSlotInsert()
    {

        index = rand.nextInt(2);
    }

}
