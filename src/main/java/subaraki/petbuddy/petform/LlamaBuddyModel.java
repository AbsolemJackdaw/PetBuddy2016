package subaraki.petbuddy.petform;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

@OnlyIn(Dist.CLIENT)
public class LlamaBuddyModel extends EntityModel<PetBuddyEntity> {

    private final ModelRenderer head;
    private final ModelRenderer body;
    private final ModelRenderer leg0;
    private final ModelRenderer leg1;
    private final ModelRenderer leg2;
    private final ModelRenderer leg3;

    public LlamaBuddyModel(float p_i47226_1_) {

        this.texWidth = 128;
        this.texHeight = 64;
        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-2.0F, -14.0F, -10.0F, 4.0F, 4.0F, 9.0F, p_i47226_1_);
        this.head.setPos(0.0F, 7.0F, -6.0F);
        this.head.texOffs(0, 14).addBox(-4.0F, -16.0F, -6.0F, 8.0F, 18.0F, 6.0F, p_i47226_1_);
        this.head.texOffs(17, 0).addBox(-4.0F, -19.0F, -4.0F, 3.0F, 3.0F, 2.0F, p_i47226_1_);
        this.head.texOffs(17, 0).addBox(1.0F, -19.0F, -4.0F, 3.0F, 3.0F, 2.0F, p_i47226_1_);
        this.body = new ModelRenderer(this, 29, 0);
        this.body.addBox(-6.0F, -10.0F, -7.0F, 12.0F, 18.0F, 10.0F, p_i47226_1_);
        this.body.setPos(0.0F, 5.0F, 2.0F);
        this.leg0 = new ModelRenderer(this, 29, 29);
        this.leg0.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, p_i47226_1_);
        this.leg0.setPos(-2.5F, 10.0F, 6.0F);
        this.leg1 = new ModelRenderer(this, 29, 29);
        this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, p_i47226_1_);
        this.leg1.setPos(2.5F, 10.0F, 6.0F);
        this.leg2 = new ModelRenderer(this, 29, 29);
        this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, p_i47226_1_);
        this.leg2.setPos(-2.5F, 10.0F, -4.0F);
        this.leg3 = new ModelRenderer(this, 29, 29);
        this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, p_i47226_1_);
        this.leg3.setPos(2.5F, 10.0F, -4.0F);
        --this.leg0.x;
        ++this.leg1.x;
        this.leg0.z += 0.0F;
        this.leg1.z += 0.0F;
        --this.leg2.x;
        ++this.leg3.x;
        --this.leg2.z;
        --this.leg3.z;
    }

    public void setupAnim(PetBuddyEntity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_)
    {

        this.head.xRot = p_225597_6_ * ((float) Math.PI / 180F);
        this.head.yRot = p_225597_5_ * ((float) Math.PI / 180F);
        this.body.xRot = ((float) Math.PI / 2F);
        this.leg0.xRot = MathHelper.cos(p_225597_2_ * 0.6662F) * 1.4F * p_225597_3_;
        this.leg1.xRot = MathHelper.cos(p_225597_2_ * 0.6662F + (float) Math.PI) * 1.4F * p_225597_3_;
        this.leg2.xRot = MathHelper.cos(p_225597_2_ * 0.6662F + (float) Math.PI) * 1.4F * p_225597_3_;
        this.leg3.xRot = MathHelper.cos(p_225597_2_ * 0.6662F) * 1.4F * p_225597_3_;
    }

    public void renderToBuffer(MatrixStack p_225598_1_, IVertexBuilder p_225598_2_, int p_225598_3_, int p_225598_4_, float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_)
    {

        if (this.young)
        {
            p_225598_1_.pushPose();
            p_225598_1_.scale(0.71428573F, 0.64935064F, 0.7936508F);
            p_225598_1_.translate(0.0D, 1.3125D, (double) 0.22F);
            this.head.render(p_225598_1_, p_225598_2_, p_225598_3_, p_225598_4_, p_225598_5_, p_225598_6_, p_225598_7_, p_225598_8_);
            p_225598_1_.popPose();
            p_225598_1_.pushPose();
            p_225598_1_.scale(0.625F, 0.45454544F, 0.45454544F);
            p_225598_1_.translate(0.0D, 2.0625D, 0.0D);
            this.body.render(p_225598_1_, p_225598_2_, p_225598_3_, p_225598_4_, p_225598_5_, p_225598_6_, p_225598_7_, p_225598_8_);
            p_225598_1_.popPose();
            p_225598_1_.pushPose();
            p_225598_1_.scale(0.45454544F, 0.41322312F, 0.45454544F);
            p_225598_1_.translate(0.0D, 2.0625D, 0.0D);
            ImmutableList.of(this.leg0, this.leg1, this.leg2, this.leg3).forEach((p_228280_8_) -> {
                p_228280_8_.render(p_225598_1_, p_225598_2_, p_225598_3_, p_225598_4_, p_225598_5_, p_225598_6_, p_225598_7_, p_225598_8_);
            });
            p_225598_1_.popPose();
        }
        else
        {
            ImmutableList.of(this.head, this.body, this.leg0, this.leg1, this.leg2, this.leg3).forEach((p_228279_8_) -> {
                p_228279_8_.render(p_225598_1_, p_225598_2_, p_225598_3_, p_225598_4_, p_225598_5_, p_225598_6_, p_225598_7_, p_225598_8_);
            });
        }

    }
}
