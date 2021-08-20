package subaraki.petbuddy.petform;

import java.util.Random;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.AbstractTropicalFishModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.entity.model.TropicalFishAModel;
import net.minecraft.client.renderer.entity.model.TropicalFishBModel;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import subaraki.petbuddy.api.IChangeableForm;
import subaraki.petbuddy.api.IPetFormBase;
import subaraki.petbuddy.api.LayerPetFormBase;
import subaraki.petbuddy.client.entity.RenderEntityPetBuddy;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

public class TropicalFishForm extends FishForm implements IChangeableForm {

    private Random rand = new Random();
    private final static TropicalFishAModel<PetBuddyEntity> modelA = new TropicalFishAModel<>(0.0F);
    private final static TropicalFishBModel<PetBuddyEntity> modelB = new TropicalFishBModel<>(0.0F);

    private static final ResourceLocation[] BASE_TEXTURE_LOCATIONS = new ResourceLocation[] { new ResourceLocation("textures/entity/fish/tropical_a.png"),
            new ResourceLocation("textures/entity/fish/tropical_b.png") };
    private static final ResourceLocation[] PATTERN_A_TEXTURE_LOCATIONS = new ResourceLocation[] {
            new ResourceLocation("textures/entity/fish/tropical_a_pattern_1.png"), new ResourceLocation("textures/entity/fish/tropical_a_pattern_2.png"),
            new ResourceLocation("textures/entity/fish/tropical_a_pattern_3.png"), new ResourceLocation("textures/entity/fish/tropical_a_pattern_4.png"),
            new ResourceLocation("textures/entity/fish/tropical_a_pattern_5.png"), new ResourceLocation("textures/entity/fish/tropical_a_pattern_6.png") };
    private static final ResourceLocation[] PATTERN_B_TEXTURE_LOCATIONS = new ResourceLocation[] {
            new ResourceLocation("textures/entity/fish/tropical_b_pattern_1.png"), new ResourceLocation("textures/entity/fish/tropical_b_pattern_2.png"),
            new ResourceLocation("textures/entity/fish/tropical_b_pattern_3.png"), new ResourceLocation("textures/entity/fish/tropical_b_pattern_4.png"),
            new ResourceLocation("textures/entity/fish/tropical_b_pattern_5.png"), new ResourceLocation("textures/entity/fish/tropical_b_pattern_6.png") };

    private int modelForm = 0;
    private int texturePattern = 0;
    private float[] baseColor = new float[3];
    private float[] pattColor = new float[3];

    public TropicalFishForm() {

        super(Items.TROPICAL_FISH, "tropical_fish", modelA);
        setFishColorForm();

    }

    @Override
    public EntityModel<PetBuddyEntity> getDefaultModel()
    {

        return modelForm == 0 ? modelA : modelB;
    }

    @Override
    public void onSlotInsert()
    {

        setFishColorForm();
    }

    private void setFishColorForm()
    {

        modelForm = rand.nextInt(2);
        texturePattern = rand.nextInt(6);
        baseColor = DyeColor.byId(rand.nextInt(16)).getTextureDiffuseColors();
        pattColor = DyeColor.byId(rand.nextInt(16)).getTextureDiffuseColors();
    }

    @Override
    public LayerRenderer<PetBuddyEntity, PlayerModel<PetBuddyEntity>> getLayer(RenderEntityPetBuddy parent_renderer)
    {

        return new LayerPetFormBase(parent_renderer) {

            {
                parent_renderer.addLayer(new LayerRenderer<PetBuddyEntity, PlayerModel<PetBuddyEntity>>(parent_renderer) {

                    final TropicalFishAModel<PetBuddyEntity> overlayA = new TropicalFishAModel<>(0.008F);
                    final TropicalFishBModel<PetBuddyEntity> overlayB = new TropicalFishBModel<>(0.008F);

                    @Override
                    public void render(MatrixStack stack, IRenderTypeBuffer buffer, int the_int, PetBuddyEntity buddy, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_)
                    {

                        if (!buddy.getPetForm().getID().equals(getForm().getID()))
                            return;
                        coloredCutoutModelCopyLayerRender(
                                this.getParentModel(),
                                modelForm == 0 ? overlayA : overlayB,
                                modelForm == 0 ? PATTERN_A_TEXTURE_LOCATIONS[texturePattern] : PATTERN_B_TEXTURE_LOCATIONS[texturePattern],
                                        stack,
                                        buffer,
                                the_int, 
                                buddy,
                                p_225628_5_,
                                p_225628_6_,
                                p_225628_8_,
                                p_225628_9_,
                                p_225628_10_,
                                p_225628_7_,
                                pattColor[0], pattColor[1],
                                pattColor[2]);

                    }
                });
            }

            @Override
            protected ResourceLocation getTextureLocation(PetBuddyEntity p_229139_1_)
            {

                return BASE_TEXTURE_LOCATIONS[modelForm];
            }

            @Override
            public IPetFormBase getForm()
            {

                return TropicalFishForm.this;
            }

            private AbstractTropicalFishModel<PetBuddyEntity> getModel()
            {

                return modelForm == 0 ? modelA : modelB;
            }

            @Override
            public void render(MatrixStack stack, IRenderTypeBuffer buffer, int the_int, PetBuddyEntity buddy, float f1, float f2, float f3, float f4, float f5, float f6)
            {

                getModel().setColor(baseColor[0], baseColor[1], baseColor[2]);
                super.render(stack, buffer, the_int, buddy, f1, f2, f3, f4, f5, f6);
                getModel().setColor(1.0F, 1.0F, 1.0F);
            }
        };
    }

}
