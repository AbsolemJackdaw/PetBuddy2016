package subaraki.petbuddy.client.entity.model;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class ModelEar<T extends LivingEntity> extends BipedModel<T>{

    public final ModelRenderer ear;

    public ModelEar(float size) {

        super(RenderType::entityCutoutNoCull, size, 0.0F, 64, 64);
        this.ear = new ModelRenderer(this, 24, 0);
        this.ear.addBox(-3.0F, -6.0F, -1.0F, 6.0F, 6.0F, 1.0F, size);
    }

}
