package subaraki.petbuddy.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelShulkerFix extends ModelBase
{
	public final ModelRenderer base;
	public final ModelRenderer lid;
	public ModelRenderer head;

	public ModelShulkerFix()
	{
		this.textureHeight = 64;
		this.textureWidth = 64;
		this.lid = new ModelRenderer(this);
		this.base = new ModelRenderer(this);
		this.head = new ModelRenderer(this);
		this.lid.setTextureOffset(0, 0).addBox(-8.0F, -16.0F, -8.0F, 16, 12, 16);
		this.lid.setRotationPoint(0.0F, 24.0F, 0.0F);
		this.base.setTextureOffset(0, 28).addBox(-8.0F, -8.0F, -8.0F, 16, 8, 16);
		this.base.setRotationPoint(0.0F, 24.0F, 0.0F);
		this.head.setTextureOffset(0, 52).addBox(-3.0F, 0.0F, -3.0F, 6, 6, 6);
		this.head.setRotationPoint(0.0F, 12.0F, 0.0F);
	}

	/**
	 * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
	 * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
	 * "far" arms and legs can swing at most.
	 */
	float moving;
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
	{
		float f = ageInTicks - (float)entityIn.ticksExisted;

		if((entityIn.prevPosX == entityIn.posX || entityIn.prevPosZ == entityIn.posZ ))
		{
			if(moving < 1f)
			{
				moving += 0.01f;
			}
		}
		
		else
		{
			if(moving > 0.0f)
				moving -= 0.05f;
		}
		
		float f1 = (moving + 0.5f) * (float)Math.PI;
		float f2 = -1.0F + MathHelper.sin(f1);
		float f3 = 0.0F;

		if (f1 > (float)Math.PI)
		{
			f3 = MathHelper.sin(ageInTicks * 0.1F) * 0.7F;
		}

		this.lid.setRotationPoint(0.0F, 16.0F + MathHelper.sin(f1) * 8.0F + f3, 0.0F);

		if (0.8f > 0.3F)
		{
			this.lid.rotateAngleY = f2 * f2 * f2 * f2 * (float)Math.PI * 0.125F;
		}

		this.head.rotateAngleX = headPitch * 0.017453292F;
		this.head.rotateAngleY = netHeadYaw * 0.017453292F;
	}

	/**
	 * Sets the models various rotation angles then renders the model.
	 */
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		this.head.render(scale);
		this.base.render(scale);
		this.lid.render(scale);
	}
}