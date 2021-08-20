package subaraki.petbuddy.client.entity.model;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

@OnlyIn(Dist.CLIENT)
public class BeeBuddyModel extends AgeableModel<PetBuddyEntity> {

    private final ModelRenderer bone;
    private final ModelRenderer body;
    private final ModelRenderer rightWing;
    private final ModelRenderer leftWing;
    private final ModelRenderer frontLeg;
    private final ModelRenderer midLeg;
    private final ModelRenderer backLeg;
    private final ModelRenderer stinger;
    private final ModelRenderer leftAntenna;
    private final ModelRenderer rightAntenna;

    public BeeBuddyModel() {

        super(false, 24.0F, 0.0F);
        this.texWidth = 64;
        this.texHeight = 64;
        this.bone = new ModelRenderer(this);
        this.bone.setPos(0.0F, 19.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setPos(0.0F, 0.0F, 0.0F);
        this.bone.addChild(this.body);
        this.body.addBox(-3.5F, -4.0F, -5.0F, 7.0F, 7.0F, 10.0F, 0.0F);
        this.stinger = new ModelRenderer(this, 26, 7);
        this.stinger.addBox(0.0F, -1.0F, 5.0F, 0.0F, 1.0F, 2.0F, 0.0F);
        this.body.addChild(this.stinger);
        this.leftAntenna = new ModelRenderer(this, 2, 0);
        this.leftAntenna.setPos(0.0F, -2.0F, -5.0F);
        this.leftAntenna.addBox(1.5F, -2.0F, -3.0F, 1.0F, 2.0F, 3.0F, 0.0F);
        this.rightAntenna = new ModelRenderer(this, 2, 3);
        this.rightAntenna.setPos(0.0F, -2.0F, -5.0F);
        this.rightAntenna.addBox(-2.5F, -2.0F, -3.0F, 1.0F, 2.0F, 3.0F, 0.0F);
        this.body.addChild(this.leftAntenna);
        this.body.addChild(this.rightAntenna);
        this.rightWing = new ModelRenderer(this, 0, 18);
        this.rightWing.setPos(-1.5F, -4.0F, -3.0F);
        this.rightWing.xRot = 0.0F;
        this.rightWing.yRot = -0.2618F;
        this.rightWing.zRot = 0.0F;
        this.bone.addChild(this.rightWing);
        this.rightWing.addBox(-9.0F, 0.0F, 0.0F, 9.0F, 0.0F, 6.0F, 0.001F);
        this.leftWing = new ModelRenderer(this, 0, 18);
        this.leftWing.setPos(1.5F, -4.0F, -3.0F);
        this.leftWing.xRot = 0.0F;
        this.leftWing.yRot = 0.2618F;
        this.leftWing.zRot = 0.0F;
        this.leftWing.mirror = true;
        this.bone.addChild(this.leftWing);
        this.leftWing.addBox(0.0F, 0.0F, 0.0F, 9.0F, 0.0F, 6.0F, 0.001F);
        this.frontLeg = new ModelRenderer(this);
        this.frontLeg.setPos(1.5F, 3.0F, -2.0F);
        this.bone.addChild(this.frontLeg);
        this.frontLeg.addBox("frontLegBox", -5.0F, 0.0F, 0.0F, 7, 2, 0, 0.0F, 26, 1);
        this.midLeg = new ModelRenderer(this);
        this.midLeg.setPos(1.5F, 3.0F, 0.0F);
        this.bone.addChild(this.midLeg);
        this.midLeg.addBox("midLegBox", -5.0F, 0.0F, 0.0F, 7, 2, 0, 0.0F, 26, 3);
        this.backLeg = new ModelRenderer(this);
        this.backLeg.setPos(1.5F, 3.0F, 2.0F);
        this.bone.addChild(this.backLeg);
        this.backLeg.addBox("backLegBox", -5.0F, 0.0F, 0.0F, 7, 2, 0, 0.0F, 26, 5);
    }

    public void prepareMobModel(PetBuddyEntity p_212843_1_, float p_212843_2_, float p_212843_3_, float p_212843_4_)
    {

        super.prepareMobModel(p_212843_1_, p_212843_2_, p_212843_3_, p_212843_4_);
    }

    public void setupAnim(PetBuddyEntity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_)
    {

        this.rightWing.xRot = 0.0F;
        this.leftAntenna.xRot = 0.0F;
        this.rightAntenna.xRot = 0.0F;
        this.bone.xRot = 0.0F;
        this.bone.y = 8.0F;
        float f = p_225597_4_ * 2.1F;
        this.rightWing.yRot = 0.0F;
        this.rightWing.zRot = MathHelper.cos(f) * (float) Math.PI * 0.15F;
        this.leftWing.xRot = this.rightWing.xRot;
        this.leftWing.yRot = this.rightWing.yRot;
        this.leftWing.zRot = -this.rightWing.zRot;
        this.frontLeg.xRot = ((float) Math.PI / 4F);
        this.midLeg.xRot = ((float) Math.PI / 4F);
        this.backLeg.xRot = ((float) Math.PI / 4F);
        this.bone.xRot = 0.0F;
        this.bone.yRot = 0.0F;
        this.bone.zRot = 0.0F;

        float f1 = MathHelper.cos(p_225597_4_ * 0.18F);
        this.bone.xRot = 0.1F + f1 * (float) Math.PI * 0.025F;
        this.leftAntenna.xRot = f1 * (float) Math.PI * 0.03F;
        this.rightAntenna.xRot = f1 * (float) Math.PI * 0.03F;
        this.frontLeg.xRot = -f1 * (float) Math.PI * 0.1F + ((float) Math.PI / 8F);
        this.backLeg.xRot = -f1 * (float) Math.PI * 0.05F + ((float) Math.PI / 4F);
        this.bone.y = 8.0F - MathHelper.cos(p_225597_4_ * 0.18F) * 0.9F;
    }

    protected Iterable<ModelRenderer> headParts()
    {

        return ImmutableList.of();
    }

    public Iterable<ModelRenderer> bodyParts()
    {

        return ImmutableList.of(this.bone);
    }
}
