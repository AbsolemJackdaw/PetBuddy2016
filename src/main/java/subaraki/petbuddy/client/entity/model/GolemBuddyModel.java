package subaraki.petbuddy.client.entity.model;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

@OnlyIn(Dist.CLIENT)
public class GolemBuddyModel extends SegmentedModel<PetBuddyEntity> {

    private final ModelRenderer head;
    private final ModelRenderer body;
    private final ModelRenderer arm0;
    private final ModelRenderer arm1;
    private final ModelRenderer leg0;
    private final ModelRenderer leg1;

    public GolemBuddyModel() {

        this.head = (new ModelRenderer(this)).setTexSize(128, 128);
        this.head.setPos(0.0F, -7.0F, -2.0F);
        this.head.texOffs(0, 0).addBox(-4.0F, -12.0F, -5.5F, 8.0F, 10.0F, 8.0F, 0.0F);
        this.head.texOffs(24, 0).addBox(-1.0F, -5.0F, -7.5F, 2.0F, 4.0F, 2.0F, 0.0F);
        this.body = (new ModelRenderer(this)).setTexSize(128, 128);
        this.body.setPos(0.0F, -7.0F, 0.0F);
        this.body.texOffs(0, 40).addBox(-9.0F, -2.0F, -6.0F, 18.0F, 12.0F, 11.0F, 0.0F);
        this.body.texOffs(0, 70).addBox(-4.5F, 10.0F, -3.0F, 9.0F, 5.0F, 6.0F, 0.5F);
        this.arm0 = (new ModelRenderer(this)).setTexSize(128, 128);
        this.arm0.setPos(0.0F, -7.0F, 0.0F);
        this.arm0.texOffs(60, 21).addBox(-13.0F, -2.5F, -3.0F, 4.0F, 30.0F, 6.0F, 0.0F);
        this.arm1 = (new ModelRenderer(this)).setTexSize(128, 128);
        this.arm1.setPos(0.0F, -7.0F, 0.0F);
        this.arm1.texOffs(60, 58).addBox(9.0F, -2.5F, -3.0F, 4.0F, 30.0F, 6.0F, 0.0F);
        this.leg0 = (new ModelRenderer(this, 0, 22)).setTexSize(128, 128);
        this.leg0.setPos(-4.0F, 11.0F, 0.0F);
        this.leg0.texOffs(37, 0).addBox(-3.5F, -3.0F, -3.0F, 6.0F, 16.0F, 5.0F, 0.0F);
        this.leg1 = (new ModelRenderer(this, 0, 22)).setTexSize(128, 128);
        this.leg1.mirror = true;
        this.leg1.texOffs(60, 0).setPos(5.0F, 11.0F, 0.0F);
        this.leg1.addBox(-3.5F, -3.0F, -3.0F, 6.0F, 16.0F, 5.0F, 0.0F);
    }

    public Iterable<ModelRenderer> parts()
    {

        return ImmutableList.of(this.head, this.body, this.leg0, this.leg1, this.arm0, this.arm1);
    }

    public void setupAnim(PetBuddyEntity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_)
    {

        this.head.yRot = p_225597_5_ * ((float) Math.PI / 180F);
        this.head.xRot = p_225597_6_ * ((float) Math.PI / 180F);
        this.leg0.xRot = -1.5F * MathHelper.triangleWave(p_225597_2_, 13.0F) * p_225597_3_;
        this.leg1.xRot = 1.5F * MathHelper.triangleWave(p_225597_2_, 13.0F) * p_225597_3_;
        this.leg0.yRot = 0.0F;
        this.leg1.yRot = 0.0F;
    }

    public void prepareMobModel(PetBuddyEntity p_212843_1_, float p_212843_2_, float p_212843_3_, float p_212843_4_)
    {

        this.arm0.xRot = (-0.2F + 1.5F * MathHelper.triangleWave(p_212843_2_, 13.0F)) * p_212843_3_;
        this.arm1.xRot = (-0.2F - 1.5F * MathHelper.triangleWave(p_212843_2_, 13.0F)) * p_212843_3_;

    }

    public ModelRenderer getFlowerHoldingArm()
    {

        return this.arm0;
    }
}
