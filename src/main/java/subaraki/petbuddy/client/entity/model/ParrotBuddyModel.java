package subaraki.petbuddy.client.entity.model;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

@OnlyIn(Dist.CLIENT)
public class ParrotBuddyModel extends SegmentedModel<PetBuddyEntity> {

    private final ModelRenderer body;
    private final ModelRenderer tail;
    private final ModelRenderer wingLeft;
    private final ModelRenderer wingRight;
    private final ModelRenderer head;
    private final ModelRenderer head2;
    private final ModelRenderer beak1;
    private final ModelRenderer beak2;
    private final ModelRenderer feather;
    private final ModelRenderer legLeft;
    private final ModelRenderer legRight;

    public ParrotBuddyModel() {

        this.texWidth = 32;
        this.texHeight = 32;
        this.body = new ModelRenderer(this, 2, 8);
        this.body.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F);
        this.body.setPos(0.0F, 16.5F, -3.0F);
        this.tail = new ModelRenderer(this, 22, 1);
        this.tail.addBox(-1.5F, -1.0F, -1.0F, 3.0F, 4.0F, 1.0F);
        this.tail.setPos(0.0F, 21.07F, 1.16F);
        this.wingLeft = new ModelRenderer(this, 19, 8);
        this.wingLeft.addBox(-0.5F, 0.0F, -1.5F, 1.0F, 5.0F, 3.0F);
        this.wingLeft.setPos(1.5F, 16.94F, -2.76F);
        this.wingRight = new ModelRenderer(this, 19, 8);
        this.wingRight.addBox(-0.5F, 0.0F, -1.5F, 1.0F, 5.0F, 3.0F);
        this.wingRight.setPos(-1.5F, 16.94F, -2.76F);
        this.head = new ModelRenderer(this, 2, 2);
        this.head.addBox(-1.0F, -1.5F, -1.0F, 2.0F, 3.0F, 2.0F);
        this.head.setPos(0.0F, 15.69F, -2.76F);
        this.head2 = new ModelRenderer(this, 10, 0);
        this.head2.addBox(-1.0F, -0.5F, -2.0F, 2.0F, 1.0F, 4.0F);
        this.head2.setPos(0.0F, -2.0F, -1.0F);
        this.head.addChild(this.head2);
        this.beak1 = new ModelRenderer(this, 11, 7);
        this.beak1.addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F);
        this.beak1.setPos(0.0F, -0.5F, -1.5F);
        this.head.addChild(this.beak1);
        this.beak2 = new ModelRenderer(this, 16, 7);
        this.beak2.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F);
        this.beak2.setPos(0.0F, -1.75F, -2.45F);
        this.head.addChild(this.beak2);
        this.feather = new ModelRenderer(this, 2, 18);
        this.feather.addBox(0.0F, -4.0F, -2.0F, 0.0F, 5.0F, 4.0F);
        this.feather.setPos(0.0F, -2.15F, 0.15F);
        this.head.addChild(this.feather);
        this.legLeft = new ModelRenderer(this, 14, 18);
        this.legLeft.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F);
        this.legLeft.setPos(1.0F, 22.0F, -1.05F);
        this.legRight = new ModelRenderer(this, 14, 18);
        this.legRight.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F);
        this.legRight.setPos(-1.0F, 22.0F, -1.05F);
    }

    public Iterable<ModelRenderer> parts()
    {

        return ImmutableList.of(this.body, this.wingLeft, this.wingRight, this.tail, this.head, this.legLeft, this.legRight);
    }

    public void setupAnim(PetBuddyEntity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_)
    {

        this.head.xRot = p_225597_6_ * ((float) Math.PI / 180F);
        this.head.yRot = p_225597_5_ * ((float) Math.PI / 180F);
        this.head.zRot = 0.0F;
        this.head.x = 0.0F;
        this.body.x = 0.0F;
        this.tail.x = 0.0F;
        this.tail.xRot = 1.0f;
        this.wingRight.x = -1.5F;
        this.wingLeft.x = 1.5F;
        this.legLeft.xRot += MathHelper.cos(p_225597_2_ * 0.6662F) * 1.4F * p_225597_3_;
        this.legRight.xRot += MathHelper.cos(p_225597_2_ * 0.6662F + (float) Math.PI) * 1.4F * p_225597_3_;
        this.wingLeft.zRot = -p_225597_4_;
        this.wingRight.zRot = p_225597_4_;

    }

    public void prepareMobModel(PetBuddyEntity p_212843_1_, float p_212843_2_, float p_212843_3_, float p_212843_4_)
    {

        this.feather.xRot = -0.2214F;
        this.body.xRot = 0.4937F;
        this.wingLeft.xRot = -0.6981F;
        this.wingLeft.yRot = -(float) Math.PI;
        this.wingRight.xRot = -0.6981F;
        this.wingRight.yRot = -(float) Math.PI;
        this.legLeft.xRot = -0.0299F;
        this.legRight.xRot = -0.0299F;
        this.legLeft.y = 22.0F;
        this.legRight.y = 22.0F;
        this.legLeft.zRot = 0.0F;
        this.legRight.zRot = 0.0F;
    }
}
