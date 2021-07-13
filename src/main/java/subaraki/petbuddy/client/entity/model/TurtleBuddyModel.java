package subaraki.petbuddy.client.entity.model;

import net.minecraft.client.renderer.entity.model.QuadrupedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

public class TurtleBuddyModel extends QuadrupedModel<PetBuddyEntity> {

    public TurtleBuddyModel(float p_i48834_1_) {
       super(12, p_i48834_1_, true, 120.0F, 0.0F, 9.0F, 6.0F, 120);
       this.texWidth = 128;
       this.texHeight = 64;
       this.head = new ModelRenderer(this, 3, 0);
       this.head.addBox(-3.0F, -1.0F, -3.0F, 6.0F, 5.0F, 6.0F, 0.0F);
       this.head.setPos(0.0F, 19.0F, -10.0F);
       this.body = new ModelRenderer(this);
       this.body.texOffs(7, 37).addBox(-9.5F, 3.0F, -10.0F, 19.0F, 20.0F, 6.0F, 0.0F);
       this.body.texOffs(31, 1).addBox(-5.5F, 3.0F, -13.0F, 11.0F, 18.0F, 3.0F, 0.0F);
       this.body.setPos(0.0F, 11.0F, -10.0F);
       this.leg0 = new ModelRenderer(this, 1, 23);
       this.leg0.addBox(-2.0F, 0.0F, 0.0F, 4.0F, 1.0F, 10.0F, 0.0F);
       this.leg0.setPos(-3.5F, 22.0F, 11.0F);
       this.leg1 = new ModelRenderer(this, 1, 12);
       this.leg1.addBox(-2.0F, 0.0F, 0.0F, 4.0F, 1.0F, 10.0F, 0.0F);
       this.leg1.setPos(3.5F, 22.0F, 11.0F);
       this.leg2 = new ModelRenderer(this, 27, 30);
       this.leg2.addBox(-13.0F, 0.0F, -2.0F, 13.0F, 1.0F, 5.0F, 0.0F);
       this.leg2.setPos(-5.0F, 21.0F, -4.0F);
       this.leg3 = new ModelRenderer(this, 27, 24);
       this.leg3.addBox(0.0F, 0.0F, -2.0F, 13.0F, 1.0F, 5.0F, 0.0F);
       this.leg3.setPos(5.0F, 21.0F, -4.0F);
    }

    protected Iterable<ModelRenderer> bodyParts() {
        return super.bodyParts();
    }

    public void setupAnim(PetBuddyEntity buddy, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
       super.setupAnim(buddy, p_225597_2_, p_225597_3_, p_225597_4_, p_225597_5_, p_225597_6_);
     
       
       this.leg0.xRot = MathHelper.cos(2.0f * p_225597_2_ * 0.6662F * 0.6F) * 0.5F * p_225597_3_;
       this.leg1.xRot = MathHelper.cos(2.0f * p_225597_2_ * 0.6662F * 0.6F + (float)Math.PI) * 0.5F * p_225597_3_;
       this.leg2.zRot = MathHelper.cos(2.0f * p_225597_2_ * 0.6662F * 0.6F + (float)Math.PI) * 0.5F * p_225597_3_;
       this.leg3.zRot = MathHelper.cos(2.0f * p_225597_2_ * 0.6662F * 0.6F) * 0.5F * p_225597_3_;
       this.leg2.xRot = 0.0F;
       this.leg3.xRot = 0.0F;
       this.leg2.yRot = 0.0F;
       this.leg3.yRot = 0.0F;
       this.leg0.yRot = 0.0F;
       this.leg1.yRot = 0.0F;
       if (!buddy.isInWater() && buddy.isOnGround()) {
           float f =  0.3F;
           float f1 = 0.3F;
           this.leg0.yRot = MathHelper.cos(f * p_225597_2_ * 5.0F + (float)Math.PI) * 3.0F * p_225597_3_;
           this.leg0.xRot = 0.0F;
           this.leg1.yRot = MathHelper.cos(f * p_225597_2_ * 5.0F) * 3.0F * p_225597_3_;
           this.leg1.xRot = 0.0F;
           this.leg2.yRot = MathHelper.cos(f * p_225597_2_ * 5.0F + (float)Math.PI) * 8.0F * p_225597_3_ * f1;
           this.leg2.zRot = 0.0F;
           this.leg3.yRot = MathHelper.cos(f * p_225597_2_ * 5.0F) * 8.0F * p_225597_3_ * f1;
           this.leg3.zRot = 0.0F;
          
       }

    }
 }