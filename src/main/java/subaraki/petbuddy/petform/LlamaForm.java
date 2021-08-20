package subaraki.petbuddy.petform;

import java.util.Map;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.math.vector.Quaternion;
import subaraki.petbuddy.api.IChangeableForm;
import subaraki.petbuddy.api.IPetFormBase;
import subaraki.petbuddy.api.LayerPetFormBase;
import subaraki.petbuddy.client.entity.RenderEntityPetBuddy;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

public class LlamaForm implements IPetFormBase, IChangeableForm {

    protected LlamaBuddyModel model = new LlamaBuddyModel(0.0f);
    protected LlamaBuddyModel modelCarpet = new LlamaBuddyModel(0.5f);
    protected int index = 0;
    protected Item carpet;

    private final ResourceLocation[] LLAMA_LOCATIONS = new ResourceLocation[] { new ResourceLocation("textures/entity/llama/creamy.png"),
            new ResourceLocation("textures/entity/llama/white.png"), new ResourceLocation("textures/entity/llama/brown.png"),
            new ResourceLocation("textures/entity/llama/gray.png") };

    private static final ResourceLocation[] LLAMA_DECOR = new ResourceLocation[] { new ResourceLocation("textures/entity/llama/decor/white.png"),
            new ResourceLocation("textures/entity/llama/decor/orange.png"), new ResourceLocation("textures/entity/llama/decor/magenta.png"),
            new ResourceLocation("textures/entity/llama/decor/light_blue.png"), new ResourceLocation("textures/entity/llama/decor/yellow.png"),
            new ResourceLocation("textures/entity/llama/decor/lime.png"), new ResourceLocation("textures/entity/llama/decor/pink.png"),
            new ResourceLocation("textures/entity/llama/decor/gray.png"), new ResourceLocation("textures/entity/llama/decor/light_gray.png"),
            new ResourceLocation("textures/entity/llama/decor/cyan.png"), new ResourceLocation("textures/entity/llama/decor/purple.png"),
            new ResourceLocation("textures/entity/llama/decor/blue.png"), new ResourceLocation("textures/entity/llama/decor/brown.png"),
            new ResourceLocation("textures/entity/llama/decor/green.png"), new ResourceLocation("textures/entity/llama/decor/red.png"),
            new ResourceLocation("textures/entity/llama/decor/black.png") };

    private static final Map<IItemProvider, DyeColor> ITEM_BY_DYE = Util.make(Maps.newHashMap(), (map) -> {
        map.put(Items.WHITE_CARPET, DyeColor.WHITE);
        map.put(Items.ORANGE_CARPET, DyeColor.ORANGE);
        map.put(Items.MAGENTA_CARPET, DyeColor.MAGENTA);
        map.put(Items.LIGHT_BLUE_CARPET, DyeColor.LIGHT_BLUE);
        map.put(Items.YELLOW_CARPET, DyeColor.YELLOW);
        map.put(Items.LIME_CARPET, DyeColor.LIME);
        map.put(Items.PINK_CARPET, DyeColor.PINK);
        map.put(Items.GRAY_CARPET, DyeColor.GRAY);
        map.put(Items.LIGHT_GRAY_CARPET, DyeColor.LIGHT_GRAY);
        map.put(Items.CYAN_CARPET, DyeColor.CYAN);
        map.put(Items.PURPLE_CARPET, DyeColor.PURPLE);
        map.put(Items.BLUE_CARPET, DyeColor.BLUE);
        map.put(Items.BROWN_CARPET, DyeColor.BROWN);
        map.put(Items.GREEN_CARPET, DyeColor.GREEN);
        map.put(Items.RED_CARPET, DyeColor.RED);
        map.put(Items.BLACK_CARPET, DyeColor.BLACK);
    });

    public LlamaForm(Item carpet) {

        this.carpet = carpet;
    }

    @Override
    public float getScale()
    {

        return 0.5f;
    }

    @Override
    public Item getFormItem()
    {

        return carpet;
    }

    @Override
    public boolean canVisuallySwimLikePlayer()
    {

        return false;
    }

    @Override
    public void heldItemRotationAndOffset(MatrixStack stack)
    {
        stack.translate(0, 0.05, 0.2);
        stack.mulPose(new Quaternion(0, 0, 90, true));
    }

    @Override
    public String getID()
    {

        return "llama";
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

        return 0.5f;
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

            {
                parent_renderer.addLayer(new LayerRenderer<PetBuddyEntity, PlayerModel<PetBuddyEntity>>(parent_renderer) {

                    @Override
                    protected ResourceLocation getTextureLocation(PetBuddyEntity p_229139_1_)
                    {

                        return LLAMA_DECOR[ITEM_BY_DYE.get(getFormItem()).getId()];
                    }

                    @Override
                    public void render(MatrixStack p_225628_1_, IRenderTypeBuffer p_225628_2_, int p_225628_3_, PetBuddyEntity buddy, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_)
                    {

                        if (!buddy.getPetForm().equals(LlamaForm.this))
                            return;

                        coloredCutoutModelCopyLayerRender(this.getParentModel(), modelCarpet, getTextureLocation(buddy), p_225628_1_, p_225628_2_, p_225628_3_,
                                buddy, p_225628_5_, p_225628_6_, p_225628_8_, p_225628_9_, p_225628_10_, p_225628_7_, 1, 1, 1);
                    }
                });
            }

            @Override
            public void render(MatrixStack stack, IRenderTypeBuffer buffer, int the_int, PetBuddyEntity buddy, float f1, float f2, float f3, float f4, float f5, float f6)
            {
                if (buddy.getInventory().getStackInSlot(2).getItem().equals(getFormItem()))
                    super.render(stack, buffer, the_int, buddy, f1, f2, f3, f4, f5, f6);
            }

            @Override
            protected ResourceLocation getTextureLocation(PetBuddyEntity p_229139_1_)
            {

                return LLAMA_LOCATIONS[index];
            }

            @Override
            public IPetFormBase getForm()
            {

                return LlamaForm.this;
            }
        };
    }

    @Override
    public void onSlotInsert()
    {

        index = rand.nextInt(4);
    }

}
