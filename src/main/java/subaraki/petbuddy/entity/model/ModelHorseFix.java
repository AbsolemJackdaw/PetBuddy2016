package subaraki.petbuddy.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelHorseFix extends ModelBase
{
	private final ModelRenderer head;
	private final ModelRenderer upperMouth;
	private final ModelRenderer lowerMouth;
	private final ModelRenderer horseLeftEar;
	private final ModelRenderer horseRightEar;
	/** The left ear box for the mule model. */
	private final ModelRenderer muleLeftEar;
	/** The right ear box for the mule model. */
	private final ModelRenderer muleRightEar;
	private final ModelRenderer neck;
	/** The box for the horse's ropes on its face. */
	private final ModelRenderer horseFaceRopes;
	private final ModelRenderer mane;
	private final ModelRenderer body;
	private final ModelRenderer tailBase;
	private final ModelRenderer tailMiddle;
	private final ModelRenderer tailTip;
	private final ModelRenderer backLeftLeg;
	private final ModelRenderer backLeftShin;
	private final ModelRenderer backLeftHoof;
	private final ModelRenderer backRightLeg;
	private final ModelRenderer backRightShin;
	private final ModelRenderer backRightHoof;
	private final ModelRenderer frontLeftLeg;
	private final ModelRenderer frontLeftShin;
	private final ModelRenderer frontLeftHoof;
	private final ModelRenderer frontRightLeg;
	private final ModelRenderer frontRightShin;
	private final ModelRenderer frontRightHoof;
	/** The left chest box on the mule model. */
	private final ModelRenderer muleLeftChest;
	/** The right chest box on the mule model. */
	private final ModelRenderer muleRightChest;
	private final ModelRenderer horseSaddleBottom;
	private final ModelRenderer horseSaddleFront;
	private final ModelRenderer horseSaddleBack;
	private final ModelRenderer horseLeftSaddleRope;
	private final ModelRenderer horseLeftSaddleMetal;
	private final ModelRenderer horseRightSaddleRope;
	private final ModelRenderer horseRightSaddleMetal;
	/** The left metal connected to the horse's face ropes. */
	private final ModelRenderer horseLeftFaceMetal;
	/** The right metal connected to the horse's face ropes. */
	private final ModelRenderer horseRightFaceMetal;
	private final ModelRenderer horseLeftRein;
	private final ModelRenderer horseRightRein;

	public ModelHorseFix()
	{
		this.textureWidth = 128;
		this.textureHeight = 128;
		this.body = new ModelRenderer(this, 0, 34);
		this.body.addBox(-5.0F, -8.0F, -19.0F, 10, 10, 24);
		this.body.setRotationPoint(0.0F, 11.0F, 9.0F);
		this.tailBase = new ModelRenderer(this, 44, 0);
		this.tailBase.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 3);
		this.tailBase.setRotationPoint(0.0F, 3.0F, 14.0F);
		this.tailBase.rotateAngleX = -1.134464F;
		this.tailMiddle = new ModelRenderer(this, 38, 7);
		this.tailMiddle.addBox(-1.5F, -2.0F, 3.0F, 3, 4, 7);
		this.tailMiddle.setRotationPoint(0.0F, 3.0F, 14.0F);
		this.tailMiddle.rotateAngleX = -1.134464F;
		this.tailTip = new ModelRenderer(this, 24, 3);
		this.tailTip.addBox(-1.5F, -4.5F, 9.0F, 3, 4, 7);
		this.tailTip.setRotationPoint(0.0F, 3.0F, 14.0F);
		this.tailTip.rotateAngleX = -1.3962634F;
		this.backLeftLeg = new ModelRenderer(this, 78, 29);
		this.backLeftLeg.addBox(-2.5F, -2.0F, -2.5F, 4, 9, 5);
		this.backLeftLeg.setRotationPoint(4.0F, 9.0F, 11.0F);
		this.backLeftShin = new ModelRenderer(this, 78, 43);
		this.backLeftShin.addBox(-2.0F, 0.0F, -1.5F, 3, 5, 3);
		this.backLeftShin.setRotationPoint(4.0F, 16.0F, 11.0F);
		this.backLeftHoof = new ModelRenderer(this, 78, 51);
		this.backLeftHoof.addBox(-2.5F, 5.1F, -2.0F, 4, 3, 4);
		this.backLeftHoof.setRotationPoint(4.0F, 16.0F, 11.0F);
		this.backRightLeg = new ModelRenderer(this, 96, 29);
		this.backRightLeg.addBox(-1.5F, -2.0F, -2.5F, 4, 9, 5);
		this.backRightLeg.setRotationPoint(-4.0F, 9.0F, 11.0F);
		this.backRightShin = new ModelRenderer(this, 96, 43);
		this.backRightShin.addBox(-1.0F, 0.0F, -1.5F, 3, 5, 3);
		this.backRightShin.setRotationPoint(-4.0F, 16.0F, 11.0F);
		this.backRightHoof = new ModelRenderer(this, 96, 51);
		this.backRightHoof.addBox(-1.5F, 5.1F, -2.0F, 4, 3, 4);
		this.backRightHoof.setRotationPoint(-4.0F, 16.0F, 11.0F);
		this.frontLeftLeg = new ModelRenderer(this, 44, 29);
		this.frontLeftLeg.addBox(-1.9F, -1.0F, -2.1F, 3, 8, 4);
		this.frontLeftLeg.setRotationPoint(4.0F, 9.0F, -8.0F);
		this.frontLeftShin = new ModelRenderer(this, 44, 41);
		this.frontLeftShin.addBox(-1.9F, 0.0F, -1.6F, 3, 5, 3);
		this.frontLeftShin.setRotationPoint(4.0F, 16.0F, -8.0F);
		this.frontLeftHoof = new ModelRenderer(this, 44, 51);
		this.frontLeftHoof.addBox(-2.4F, 5.1F, -2.1F, 4, 3, 4);
		this.frontLeftHoof.setRotationPoint(4.0F, 16.0F, -8.0F);
		this.frontRightLeg = new ModelRenderer(this, 60, 29);
		this.frontRightLeg.addBox(-1.1F, -1.0F, -2.1F, 3, 8, 4);
		this.frontRightLeg.setRotationPoint(-4.0F, 9.0F, -8.0F);
		this.frontRightShin = new ModelRenderer(this, 60, 41);
		this.frontRightShin.addBox(-1.1F, 0.0F, -1.6F, 3, 5, 3);
		this.frontRightShin.setRotationPoint(-4.0F, 16.0F, -8.0F);
		this.frontRightHoof = new ModelRenderer(this, 60, 51);
		this.frontRightHoof.addBox(-1.6F, 5.1F, -2.1F, 4, 3, 4);
		this.frontRightHoof.setRotationPoint(-4.0F, 16.0F, -8.0F);
		this.head = new ModelRenderer(this, 0, 0);
		this.head.addBox(-2.5F, -10.0F, -1.5F, 5, 5, 7);
		this.head.setRotationPoint(0.0F, 4.0F, -10.0F);
		this.head.rotateAngleX = 0.5235988F;
		this.upperMouth = new ModelRenderer(this, 24, 18);
		this.upperMouth.addBox(-2.0F, -10.0F, -7.0F, 4, 3, 6);
		this.upperMouth.setRotationPoint(0.0F, 3.95F, -10.0F);
		this.upperMouth.rotateAngleX = 0.5235988F;
		this.lowerMouth = new ModelRenderer(this, 24, 27);
		this.lowerMouth.addBox(-2.0F, -7.0F, -6.5F, 4, 2, 5);
		this.lowerMouth.setRotationPoint(0.0F, 4.0F, -10.0F);
		this.lowerMouth.rotateAngleX = 0.5235988F;
		this.head.addChild(this.upperMouth);
		this.head.addChild(this.lowerMouth);
		this.horseLeftEar = new ModelRenderer(this, 0, 0);
		this.horseLeftEar.addBox(0.45F, -12.0F, 4.0F, 2, 3, 1);
		this.horseLeftEar.setRotationPoint(0.0F, 4.0F, -10.0F);
		this.horseLeftEar.rotateAngleX = 0.5235988F;
		this.horseRightEar = new ModelRenderer(this, 0, 0);
		this.horseRightEar.addBox(-2.45F, -12.0F, 4.0F, 2, 3, 1);
		this.horseRightEar.setRotationPoint(0.0F, 4.0F, -10.0F);
		this.horseRightEar.rotateAngleX = 0.5235988F;
		this.muleLeftEar = new ModelRenderer(this, 0, 12);
		this.muleLeftEar.addBox(-2.0F, -16.0F, 4.0F, 2, 7, 1);
		this.muleLeftEar.setRotationPoint(0.0F, 4.0F, -10.0F);
		this.muleLeftEar.rotateAngleX = 0.5235988F;
		this.muleLeftEar.rotateAngleZ = 0.2617994F;
		this.muleRightEar = new ModelRenderer(this, 0, 12);
		this.muleRightEar.addBox(0.0F, -16.0F, 4.0F, 2, 7, 1);
		this.muleRightEar.setRotationPoint(0.0F, 4.0F, -10.0F);
		this.muleRightEar.rotateAngleX = 0.5235988F;
		this.muleRightEar.rotateAngleZ = -0.2617994F;
		this.neck = new ModelRenderer(this, 0, 12);
		this.neck.addBox(-2.05F, -9.8F, -2.0F, 4, 14, 8);
		this.neck.setRotationPoint(0.0F, 4.0F, -10.0F);
		this.neck.rotateAngleX = 0.5235988F;
		this.muleLeftChest = new ModelRenderer(this, 0, 34);
		this.muleLeftChest.addBox(-3.0F, 0.0F, 0.0F, 8, 8, 3);
		this.muleLeftChest.setRotationPoint(-7.5F, 3.0F, 10.0F);
		this.muleLeftChest.rotateAngleY = ((float)Math.PI / 2F);
		this.muleRightChest = new ModelRenderer(this, 0, 47);
		this.muleRightChest.addBox(-3.0F, 0.0F, 0.0F, 8, 8, 3);
		this.muleRightChest.setRotationPoint(4.5F, 3.0F, 10.0F);
		this.muleRightChest.rotateAngleY = ((float)Math.PI / 2F);
		this.horseSaddleBottom = new ModelRenderer(this, 80, 0);
		this.horseSaddleBottom.addBox(-5.0F, 0.0F, -3.0F, 10, 1, 8);
		this.horseSaddleBottom.setRotationPoint(0.0F, 2.0F, 2.0F);
		this.horseSaddleFront = new ModelRenderer(this, 106, 9);
		this.horseSaddleFront.addBox(-1.5F, -1.0F, -3.0F, 3, 1, 2);
		this.horseSaddleFront.setRotationPoint(0.0F, 2.0F, 2.0F);
		this.horseSaddleBack = new ModelRenderer(this, 80, 9);
		this.horseSaddleBack.addBox(-4.0F, -1.0F, 3.0F, 8, 1, 2);
		this.horseSaddleBack.setRotationPoint(0.0F, 2.0F, 2.0F);
		this.horseLeftSaddleMetal = new ModelRenderer(this, 74, 0);
		this.horseLeftSaddleMetal.addBox(-0.5F, 6.0F, -1.0F, 1, 2, 2);
		this.horseLeftSaddleMetal.setRotationPoint(5.0F, 3.0F, 2.0F);
		this.horseLeftSaddleRope = new ModelRenderer(this, 70, 0);
		this.horseLeftSaddleRope.addBox(-0.5F, 0.0F, -0.5F, 1, 6, 1);
		this.horseLeftSaddleRope.setRotationPoint(5.0F, 3.0F, 2.0F);
		this.horseRightSaddleMetal = new ModelRenderer(this, 74, 4);
		this.horseRightSaddleMetal.addBox(-0.5F, 6.0F, -1.0F, 1, 2, 2);
		this.horseRightSaddleMetal.setRotationPoint(-5.0F, 3.0F, 2.0F);
		this.horseRightSaddleRope = new ModelRenderer(this, 80, 0);
		this.horseRightSaddleRope.addBox(-0.5F, 0.0F, -0.5F, 1, 6, 1);
		this.horseRightSaddleRope.setRotationPoint(-5.0F, 3.0F, 2.0F);
		this.horseLeftFaceMetal = new ModelRenderer(this, 74, 13);
		this.horseLeftFaceMetal.addBox(1.5F, -8.0F, -4.0F, 1, 2, 2);
		this.horseLeftFaceMetal.setRotationPoint(0.0F, 4.0F, -10.0F);
		this.horseLeftFaceMetal.rotateAngleX = 0.5235988F;
		this.horseRightFaceMetal = new ModelRenderer(this, 74, 13);
		this.horseRightFaceMetal.addBox(-2.5F, -8.0F, -4.0F, 1, 2, 2);
		this.horseRightFaceMetal.setRotationPoint(0.0F, 4.0F, -10.0F);
		this.horseRightFaceMetal.rotateAngleX = 0.5235988F;
		this.horseLeftRein = new ModelRenderer(this, 44, 10);
		this.horseLeftRein.addBox(2.6F, -6.0F, -6.0F, 0, 3, 16);
		this.horseLeftRein.setRotationPoint(0.0F, 4.0F, -10.0F);
		this.horseRightRein = new ModelRenderer(this, 44, 5);
		this.horseRightRein.addBox(-2.6F, -6.0F, -6.0F, 0, 3, 16);
		this.horseRightRein.setRotationPoint(0.0F, 4.0F, -10.0F);
		this.mane = new ModelRenderer(this, 58, 0);
		this.mane.addBox(-1.0F, -11.5F, 5.0F, 2, 16, 4);
		this.mane.setRotationPoint(0.0F, 4.0F, -10.0F);
		this.mane.rotateAngleX = 0.5235988F;
		this.horseFaceRopes = new ModelRenderer(this, 80, 12);
		this.horseFaceRopes.addBox(-2.5F, -10.1F, -7.0F, 5, 5, 12, 0.2F);
		this.horseFaceRopes.setRotationPoint(0.0F, 4.0F, -10.0F);
		this.horseFaceRopes.rotateAngleX = 0.5235988F;
	}

	/**
	 * Sets the models various rotation angles then renders the model.
	 */
	 public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		 boolean isMule = false;

		 this.backLeftLeg.render(scale);
		 this.backLeftShin.render(scale);
		 this.backLeftHoof.render(scale);
		 this.backRightLeg.render(scale);
		 this.backRightShin.render(scale);
		 this.backRightHoof.render(scale);
		 this.frontLeftLeg.render(scale);
		 this.frontLeftShin.render(scale);
		 this.frontLeftHoof.render(scale);
		 this.frontRightLeg.render(scale);
		 this.frontRightShin.render(scale);
		 this.frontRightHoof.render(scale);

		 this.body.render(scale);
		 this.tailBase.render(scale);
		 this.tailMiddle.render(scale);
		 this.tailTip.render(scale);
		 this.neck.render(scale);
		 this.mane.render(scale);

		 if (isMule)
		 {
			 this.muleLeftEar.render(scale);
			 this.muleRightEar.render(scale);
		 }
		 else
		 {
			 this.horseLeftEar.render(scale);
			 this.horseRightEar.render(scale);
		 }

		 this.head.render(scale);

		 if (isChild)
		 {
			 GlStateManager.popMatrix();
		 }
	}

	 /**
	  * Fixes and offsets a rotation in the ModelHorse class.
	  */
	 private float updateHorseRotation(float p_110683_1_, float p_110683_2_, float p_110683_3_)
	 {
		 float f;

		 for (f = p_110683_2_ - p_110683_1_; f < -180.0F; f += 360.0F)
		 {
			 ;
		 }

		 while (f >= 180.0F)
		 {
			 f -= 360.0F;
		 }

		 return p_110683_1_ + p_110683_3_ * f;
	 }

	 /**
	  * Used for easily adding entity-dependent animations. The second and third float params here are the same second
	  * and third as in the setRotationAngles method.
	  */
	 public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime)
	 {
		 super.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTickTime);
		 float f = this.updateHorseRotation(entitylivingbaseIn.prevRenderYawOffset, entitylivingbaseIn.renderYawOffset, partialTickTime);
		 float f1 = this.updateHorseRotation(entitylivingbaseIn.prevRotationYawHead, entitylivingbaseIn.rotationYawHead, partialTickTime);
		 float f2 = entitylivingbaseIn.prevRotationPitch + (entitylivingbaseIn.rotationPitch - entitylivingbaseIn.prevRotationPitch) * partialTickTime;
		 float f3 = f1 - f;
		 float f4 = f2 * 0.017453292F;

		 if (f3 > 20.0F)
		 {
			 f3 = 20.0F;
		 }

		 if (f3 < -20.0F)
		 {
			 f3 = -20.0F;
		 }

		 if (limbSwingAmount > 0.2F)
		 {
			 f4 += MathHelper.cos(limbSwing * 0.4F) * 0.15F * limbSwingAmount;
		 }

		 float f9 = (float)entitylivingbaseIn.ticksExisted + partialTickTime;
		 float f10 = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI);
		 float f11 = f10 * 0.8F * limbSwingAmount;
		 this.head.rotationPointY = 4.0F;
		 this.head.rotationPointZ = -10.0F;
		 this.tailBase.rotationPointY = 3.0F;
		 this.tailMiddle.rotationPointZ = 14.0F;
		 this.muleRightChest.rotationPointY = 3.0F;
		 this.muleRightChest.rotationPointZ = 10.0F;
		 this.body.rotateAngleX = 0.0F;
		 this.head.rotateAngleX = 0.5235988F + f4;
		 this.head.rotateAngleY = f3 * 0.017453292F;
		 this.horseLeftEar.rotationPointY = this.head.rotationPointY;
		 this.horseRightEar.rotationPointY = this.head.rotationPointY;
		 this.muleLeftEar.rotationPointY = this.head.rotationPointY;
		 this.muleRightEar.rotationPointY = this.head.rotationPointY;
		 this.neck.rotationPointY = this.head.rotationPointY;
		 this.upperMouth.rotationPointY = 0.02F;
		 this.lowerMouth.rotationPointY = 0.0F;
		 this.mane.rotationPointY = this.head.rotationPointY;
		 this.horseLeftEar.rotationPointZ = this.head.rotationPointZ;
		 this.horseRightEar.rotationPointZ = this.head.rotationPointZ;
		 this.muleLeftEar.rotationPointZ = this.head.rotationPointZ;
		 this.muleRightEar.rotationPointZ = this.head.rotationPointZ;
		 this.neck.rotationPointZ = this.head.rotationPointZ;
		 this.mane.rotationPointZ = this.head.rotationPointZ;
		 this.horseLeftEar.rotateAngleX = this.head.rotateAngleX;
		 this.horseRightEar.rotateAngleX = this.head.rotateAngleX;
		 this.muleLeftEar.rotateAngleX = this.head.rotateAngleX;
		 this.muleRightEar.rotateAngleX = this.head.rotateAngleX;
		 this.neck.rotateAngleX = this.head.rotateAngleX;
		 this.mane.rotateAngleX = this.head.rotateAngleX;
		 this.horseLeftEar.rotateAngleY = this.head.rotateAngleY;
		 this.horseRightEar.rotateAngleY = this.head.rotateAngleY;
		 this.muleLeftEar.rotateAngleY = this.head.rotateAngleY;
		 this.muleRightEar.rotateAngleY = this.head.rotateAngleY;
		 this.neck.rotateAngleY = this.head.rotateAngleY;
		 this.upperMouth.rotateAngleY = 0.0F;
		 this.lowerMouth.rotateAngleY = 0.0F;
		 this.mane.rotateAngleY = this.head.rotateAngleY;
		 this.muleLeftChest.rotateAngleX = f11 / 5.0F;
		 this.muleRightChest.rotateAngleX = -f11 / 5.0F;
		 float f13 = MathHelper.cos(f9 * 0.6F + (float)Math.PI);
		 this.frontRightLeg.rotationPointY = this.frontLeftLeg.rotationPointY;
		 this.frontRightLeg.rotationPointZ = this.frontLeftLeg.rotationPointZ;
		 this.backLeftHoof.rotateAngleX = this.backLeftShin.rotateAngleX;
		 this.backRightHoof.rotateAngleX = this.backRightShin.rotateAngleX;
		 this.frontLeftHoof.rotateAngleX = this.frontLeftShin.rotateAngleX;
		 this.frontRightHoof.rotateAngleX = this.frontRightShin.rotateAngleX;
		 this.backLeftHoof.rotationPointY = this.backLeftShin.rotationPointY;
		 this.backLeftHoof.rotationPointZ = this.backLeftShin.rotationPointZ;
		 this.backRightHoof.rotationPointY = this.backRightShin.rotationPointY;
		 this.backRightHoof.rotationPointZ = this.backRightShin.rotationPointZ;
		 this.frontLeftHoof.rotationPointY = this.frontLeftShin.rotationPointY;
		 this.frontLeftHoof.rotationPointZ = this.frontLeftShin.rotationPointZ;
		 this.frontRightHoof.rotationPointY = this.frontRightShin.rotationPointY;
		 this.frontRightHoof.rotationPointZ = this.frontRightShin.rotationPointZ;

		 this.tailBase.rotateAngleY = 0.0F;

		 this.tailMiddle.rotateAngleY = this.tailBase.rotateAngleY;
		 this.tailTip.rotateAngleY = this.tailBase.rotateAngleY;
		 this.tailMiddle.rotationPointY = this.tailBase.rotationPointY;
		 this.tailTip.rotationPointY = this.tailBase.rotationPointY;
		 this.tailMiddle.rotationPointZ = this.tailBase.rotationPointZ;
		 this.tailTip.rotationPointZ = this.tailBase.rotationPointZ;
	 }
}