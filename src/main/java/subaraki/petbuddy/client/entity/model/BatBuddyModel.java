package subaraki.petbuddy.client.entity.model;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

public class BatBuddyModel extends SegmentedModel<PetBuddyEntity> {

    private final ModelRenderer head;
    private final ModelRenderer body;
    private final ModelRenderer rightWing;
    private final ModelRenderer leftWing;
    private final ModelRenderer rightWingTip;
    private final ModelRenderer leftWingTip;

    public BatBuddyModel() {

        this.texWidth = 64;
        this.texHeight = 64;
        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F);
        ModelRenderer modelrenderer = new ModelRenderer(this, 24, 0);
        modelrenderer.addBox(-4.0F, -6.0F, -2.0F, 3.0F, 4.0F, 1.0F);
        this.head.addChild(modelrenderer);
        ModelRenderer modelrenderer1 = new ModelRenderer(this, 24, 0);
        modelrenderer1.mirror = true;
        modelrenderer1.addBox(1.0F, -6.0F, -2.0F, 3.0F, 4.0F, 1.0F);
        this.head.addChild(modelrenderer1);
        this.body = new ModelRenderer(this, 0, 16);
        this.body.addBox(-3.0F, 4.0F, -3.0F, 6.0F, 12.0F, 6.0F);
        this.body.texOffs(0, 34).addBox(-5.0F, 16.0F, 0.0F, 10.0F, 6.0F, 1.0F);
        this.rightWing = new ModelRenderer(this, 42, 0);
        this.rightWing.addBox(-12.0F, 1.0F, 1.5F, 10.0F, 16.0F, 1.0F);
        this.rightWingTip = new ModelRenderer(this, 24, 16);
        this.rightWingTip.setPos(-12.0F, 1.0F, 1.5F);
        this.rightWingTip.addBox(-8.0F, 1.0F, 0.0F, 8.0F, 12.0F, 1.0F);
        this.leftWing = new ModelRenderer(this, 42, 0);
        this.leftWing.mirror = true;
        this.leftWing.addBox(2.0F, 1.0F, 1.5F, 10.0F, 16.0F, 1.0F);
        this.leftWingTip = new ModelRenderer(this, 24, 16);
        this.leftWingTip.mirror = true;
        this.leftWingTip.setPos(12.0F, 1.0F, 1.5F);
        this.leftWingTip.addBox(0.0F, 1.0F, 0.0F, 8.0F, 12.0F, 1.0F);
        this.body.addChild(this.rightWing);
        this.body.addChild(this.leftWing);
        this.rightWing.addChild(this.rightWingTip);
        this.leftWing.addChild(this.leftWingTip);
    }

    public Iterable<ModelRenderer> parts()
    {

        return ImmutableList.of(this.head, this.body);
    }

    @Override
    public void setupAnim(PetBuddyEntity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_)
    {

        {
            this.head.xRot = p_225597_6_ * ((float) Math.PI / 180F);
            this.head.yRot = p_225597_5_ * ((float) Math.PI / 180F);
            this.head.zRot = 0.0F;
            this.head.setPos(0.0F, 0.0F, 0.0F);
            this.rightWing.setPos(0.0F, 0.0F, 0.0F);
            this.leftWing.setPos(0.0F, 0.0F, 0.0F);
            this.body.xRot = ((float) Math.PI / 4F) + MathHelper.cos(p_225597_4_ * 0.1F) * 0.15F;
            this.body.yRot = 0.0F;
            this.rightWing.yRot = MathHelper.cos(p_225597_4_ * 1.3F) * (float) Math.PI * 0.25F;
            this.leftWing.yRot = -this.rightWing.yRot;
            this.rightWingTip.yRot = this.rightWing.yRot * 0.5F;
            this.leftWingTip.yRot = -this.rightWing.yRot * 0.5F;
        }

    }
}
