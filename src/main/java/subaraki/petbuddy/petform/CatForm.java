package subaraki.petbuddy.petform;

import java.util.Random;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.OcelotModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;
import subaraki.petbuddy.api.IChangeableForm;
import subaraki.petbuddy.api.IPetFormBase;
import subaraki.petbuddy.api.LayerPetFormBase;
import subaraki.petbuddy.client.entity.RenderEntityPetBuddy;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

public class CatForm implements IPetFormBase, IChangeableForm {

    protected OcelotModel<PetBuddyEntity> model;
    protected int texture_index;

    public CatForm() {

        model = new OcelotModel<>(0.0f);

    }

    @Override
    public float getScale()
    {

        return 0.5f;
    }

    @Override
    public Item getFormItem()
    {

        return Items.COOKED_COD;
    }

    @Override
    public boolean canVisuallySwimLikePlayer()
    {

        return false;
    }

    @Override
    public void heldItemRotationAndOffset(MatrixStack stack)
    {
        stack.translate(0, 0.8, 0);
        stack.mulPose(new Quaternion(0, 45, 90, true));
        stack.scale(1.5f,1.5f,1.5f);
    }

    @Override
    public String getID()
    {

        return "cat";
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

        return -0.2f;
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

            private final ResourceLocation OCELOT_TEXTURES = new ResourceLocation("textures/entity/cat/ocelot.png");

            @Override
            protected ResourceLocation getTextureLocation(PetBuddyEntity p_229139_1_)
            {

                if (texture_index == 11)
                    return OCELOT_TEXTURES;
                else
                    return CatEntity.TEXTURE_BY_TYPE.get(texture_index);
            }

            @Override
            public IPetFormBase getForm()
            {

                return CatForm.this;
            }
        };
    }

    @Override
    public void onSlotInsert()
    {

        texture_index = new Random().nextInt(12);
    }

}
