package subaraki.petbuddy.api;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

public abstract class LayerPetFormBase extends LayerRenderer<PetBuddyEntity, PlayerModel<PetBuddyEntity>> {

    public LayerPetFormBase(IEntityRenderer<PetBuddyEntity, PlayerModel<PetBuddyEntity>> parent_renderer) {

        super(parent_renderer);
        this.getParentModel().setAllVisible(false);
    }

    public abstract IPetFormBase getForm();

    @Override
    public void render(MatrixStack stack, IRenderTypeBuffer buffer, int the_int, PetBuddyEntity buddy, float f1, float f2, float f3, float f4, float f5, float f6)
    {

        if (!buddy.getPetForm().getID().equals(getForm().getID()))
            return;

        EntityModel<PetBuddyEntity> modelToRender = buddy.getPetForm().getDefaultModel();
        modelToRender.young = false;
        
        modelToRender.prepareMobModel(buddy, f1, f2, f3);
        modelToRender.setupAnim(buddy, f1, f2, f4, f5, f6);
        IVertexBuilder ivertexbuilder = buffer.getBuffer(RenderType.entityCutoutNoCull(getTextureLocation(buddy)));
        modelToRender.renderToBuffer(stack, ivertexbuilder, the_int, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }

}
