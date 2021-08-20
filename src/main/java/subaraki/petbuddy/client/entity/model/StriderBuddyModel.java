package subaraki.petbuddy.client.entity.model;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

@OnlyIn(Dist.CLIENT)
public class StriderBuddyModel extends SegmentedModel<PetBuddyEntity> {

    private final ModelRenderer rightLeg;
    private final ModelRenderer leftLeg;
    public final ModelRenderer body;
    private final ModelRenderer bristle0;
    private final ModelRenderer bristle1;
    private final ModelRenderer bristle2;
    private final ModelRenderer bristle3;
    private final ModelRenderer bristle4;
    private final ModelRenderer bristle5;

    public StriderBuddyModel() {

        this.texWidth = 64;
        this.texHeight = 128;
        this.rightLeg = new ModelRenderer(this, 0, 32);
        this.rightLeg.setPos(-4.0F, 8.0F, 0.0F);
        this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 16.0F, 4.0F, 0.0F);
        this.leftLeg = new ModelRenderer(this, 0, 55);
        this.leftLeg.setPos(4.0F, 8.0F, 0.0F);
        this.leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 16.0F, 4.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setPos(0.0F, 1.0F, 0.0F);
        this.body.addBox(-8.0F, -6.0F, -8.0F, 16.0F, 14.0F, 16.0F, 0.0F);
        this.bristle0 = new ModelRenderer(this, 16, 65);
        this.bristle0.setPos(-8.0F, 4.0F, -8.0F);
        this.bristle0.addBox(-12.0F, 0.0F, 0.0F, 12.0F, 0.0F, 16.0F, 0.0F, true);
        this.setRotationAngle(this.bristle0, 0.0F, 0.0F, -1.2217305F);
        this.bristle1 = new ModelRenderer(this, 16, 49);
        this.bristle1.setPos(-8.0F, -1.0F, -8.0F);
        this.bristle1.addBox(-12.0F, 0.0F, 0.0F, 12.0F, 0.0F, 16.0F, 0.0F, true);
        this.setRotationAngle(this.bristle1, 0.0F, 0.0F, -1.134464F);
        this.bristle2 = new ModelRenderer(this, 16, 33);
        this.bristle2.setPos(-8.0F, -5.0F, -8.0F);
        this.bristle2.addBox(-12.0F, 0.0F, 0.0F, 12.0F, 0.0F, 16.0F, 0.0F, true);
        this.setRotationAngle(this.bristle2, 0.0F, 0.0F, -0.87266463F);
        this.bristle3 = new ModelRenderer(this, 16, 33);
        this.bristle3.setPos(8.0F, -6.0F, -8.0F);
        this.bristle3.addBox(0.0F, 0.0F, 0.0F, 12.0F, 0.0F, 16.0F, 0.0F);
        this.setRotationAngle(this.bristle3, 0.0F, 0.0F, 0.87266463F);
        this.bristle4 = new ModelRenderer(this, 16, 49);
        this.bristle4.setPos(8.0F, -2.0F, -8.0F);
        this.bristle4.addBox(0.0F, 0.0F, 0.0F, 12.0F, 0.0F, 16.0F, 0.0F);
        this.setRotationAngle(this.bristle4, 0.0F, 0.0F, 1.134464F);
        this.bristle5 = new ModelRenderer(this, 16, 65);
        this.bristle5.setPos(8.0F, 3.0F, -8.0F);
        this.bristle5.addBox(0.0F, 0.0F, 0.0F, 12.0F, 0.0F, 16.0F, 0.0F);
        this.setRotationAngle(this.bristle5, 0.0F, 0.0F, 1.2217305F);
        this.body.addChild(this.bristle0);
        this.body.addChild(this.bristle1);
        this.body.addChild(this.bristle2);
        this.body.addChild(this.bristle3);
        this.body.addChild(this.bristle4);
        this.body.addChild(this.bristle5);
    }

    public void setupAnim(PetBuddyEntity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_)
    {

        p_225597_3_ = Math.min(0.25F, p_225597_3_);
        if (p_225597_1_.getPassengers().size() <= 0)
        {
            this.body.xRot = p_225597_6_ * ((float) Math.PI / 180F);
            this.body.yRot = p_225597_5_ * ((float) Math.PI / 180F);
        }
        else
        {
            this.body.xRot = 0.0F;
            this.body.yRot = 0.0F;
        }

        this.body.zRot = 0.1F * MathHelper.sin(p_225597_2_ * 1.5F) * 4.0F * p_225597_3_;
        this.body.y = 2.0F;
        this.body.y -= 2.0F * MathHelper.cos(p_225597_2_ * 1.5F) * 2.0F * p_225597_3_;
        this.leftLeg.xRot = MathHelper.sin(p_225597_2_ * 1.5F * 0.5F) * 2.0F * p_225597_3_;
        this.rightLeg.xRot = MathHelper.sin(p_225597_2_ * 1.5F * 0.5F + (float) Math.PI) * 2.0F * p_225597_3_;
        this.leftLeg.zRot = 0.17453292F * MathHelper.cos(p_225597_2_ * 1.5F * 0.5F) * p_225597_3_;
        this.rightLeg.zRot = 0.17453292F * MathHelper.cos(p_225597_2_ * 1.5F * 0.5F + (float) Math.PI) * p_225597_3_;
        this.leftLeg.y = 8.0F + 2.0F * MathHelper.sin(p_225597_2_ * 1.5F * 0.5F + (float) Math.PI) * 2.0F * p_225597_3_;
        this.rightLeg.y = 8.0F + 2.0F * MathHelper.sin(p_225597_2_ * 1.5F * 0.5F) * 2.0F * p_225597_3_;
        this.bristle0.zRot = -1.2217305F;
        this.bristle1.zRot = -1.134464F;
        this.bristle2.zRot = -0.87266463F;
        this.bristle3.zRot = 0.87266463F;
        this.bristle4.zRot = 1.134464F;
        this.bristle5.zRot = 1.2217305F;
        float f1 = MathHelper.cos(p_225597_2_ * 1.5F + (float) Math.PI) * p_225597_3_;
        this.bristle0.zRot += f1 * 1.3F;
        this.bristle1.zRot += f1 * 1.2F;
        this.bristle2.zRot += f1 * 0.6F;
        this.bristle3.zRot += f1 * 0.6F;
        this.bristle4.zRot += f1 * 1.2F;
        this.bristle5.zRot += f1 * 1.3F;
        this.bristle0.zRot += 0.05F * MathHelper.sin(p_225597_4_ * 1.0F * -0.4F);
        this.bristle1.zRot += 0.1F * MathHelper.sin(p_225597_4_ * 1.0F * 0.2F);
        this.bristle2.zRot += 0.1F * MathHelper.sin(p_225597_4_ * 1.0F * 0.4F);
        this.bristle3.zRot += 0.1F * MathHelper.sin(p_225597_4_ * 1.0F * 0.4F);
        this.bristle4.zRot += 0.1F * MathHelper.sin(p_225597_4_ * 1.0F * 0.2F);
        this.bristle5.zRot += 0.05F * MathHelper.sin(p_225597_4_ * 1.0F * -0.4F);
    }

    public void setRotationAngle(ModelRenderer p_239127_1_, float p_239127_2_, float p_239127_3_, float p_239127_4_)
    {

        p_239127_1_.xRot = p_239127_2_;
        p_239127_1_.yRot = p_239127_3_;
        p_239127_1_.zRot = p_239127_4_;
    }

    public Iterable<ModelRenderer> parts()
    {

        return ImmutableList.of(this.body, this.leftLeg, this.rightLeg);
    }
}
