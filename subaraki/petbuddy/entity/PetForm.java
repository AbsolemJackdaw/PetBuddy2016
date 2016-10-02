package subaraki.petbuddy.entity;

import net.minecraft.block.Block;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBlaze;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.model.ModelEnderman;
import net.minecraft.client.model.ModelGhast;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.model.ModelPolarBear;
import net.minecraft.client.model.ModelSheep2;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.model.ModelSquid;
import net.minecraft.client.model.ModelVillager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import subaraki.petbuddy.entity.model.ModelBatFix;
import subaraki.petbuddy.entity.model.ModelSkeletonFix;

public class PetForm {

	private static final ModelSheep2 MODEL_SHEEP2 = new ModelSheep2(){
		public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float p_78086_2_, float p_78086_3_, float partialTickTime) {
		}
	};
	private static final ModelPolarBear MODEL_POLARBEAR = new ModelPolarBear(){
		public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
		{
			this.head.rotateAngleX = headPitch * 0.017453292F;
			this.head.rotateAngleY = netHeadYaw * 0.017453292F;
			this.body.rotateAngleX = ((float)Math.PI / 2F);
			this.leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
			this.leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
			this.leg3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
			this.leg4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

			float f = ageInTicks - (float)entityIn.ticksExisted;
			float f1 = 0f;
			f1 = f1 * f1;
			float f2 = 1.0F - f1;
			this.body.rotateAngleX = ((float)Math.PI / 2F) - f1 * (float)Math.PI * 0.35F;
			this.body.rotationPointY = 9.0F * f2 + 11.0F * f1;
			this.leg3.rotationPointY = 14.0F * f2 + -6.0F * f1;
			this.leg3.rotationPointZ = -8.0F * f2 + -4.0F * f1;
			this.leg3.rotateAngleX -= f1 * (float)Math.PI * 0.45F;
			this.leg4.rotationPointY = this.leg3.rotationPointY;
			this.leg4.rotationPointZ = this.leg3.rotationPointZ;
			this.leg4.rotateAngleX -= f1 * (float)Math.PI * 0.45F;
			this.head.rotationPointY = 10.0F * f2 + -12.0F * f1;
			this.head.rotationPointZ = -16.0F * f2 + -3.0F * f1;
			this.head.rotateAngleX += f1 * (float)Math.PI * 0.15F;
		}
	};
	
	private static final ModelBatFix MODEL_BAT = new ModelBatFix();
	private static final ModelSkeletonFix MODEL_SKELETON = new ModelSkeletonFix();
	private static final ModelBiped MODEL_PLAYER = new ModelBiped(0,0,64,64);
	private static final ModelCow MODEL_COW = new ModelCow();
	private static final ModelSquid MODEL_SQUID = new ModelSquid();
	private static final ModelPig MODEL_PIG = new ModelPig();
	private static final ModelChicken MODEL_CHICKEN = new ModelChicken();
	private static final ModelSpider MODEL_SPIDER = new ModelSpider();
	private static final ModelGhast MODEL_GHAST = new ModelGhast();
	private static final ModelBlaze MODEL_BLAZE = new ModelBlaze();
	private static final ModelVillager MODEL_VILLAGER = new ModelVillager(0);
	private static final ModelEnderman MODEL_ENDERMAN = new ModelEnderman(0);
	private static final ModelCreeper MODEL_CREEPER = new ModelCreeper();

	private static final ResourceLocation TEXTURE_SHEEP = new ResourceLocation("textures/entity/sheep/sheep.png");
	private static final ResourceLocation TEXTURE_ZOMBIE = new ResourceLocation("textures/entity/zombie/zombie.png");
	private static final ResourceLocation TEXTURE_HUSK = new ResourceLocation("textures/entity/zombie/husk.png");
	private static final ResourceLocation TEXTURE_SKELETON = new ResourceLocation("textures/entity/skeleton/skeleton.png");
	private static final ResourceLocation TEXTURE_COW = new ResourceLocation("textures/entity/cow/cow.png");
	private static final ResourceLocation TEXTURE_SQUID = new ResourceLocation("textures/entity/squid.png");
	private static final ResourceLocation TEXTURE_PIG = new ResourceLocation("textures/entity/pig/pig.png");
	private static final ResourceLocation TEXTURE_CHICKEN = new ResourceLocation("textures/entity/chicken.png");
	private static final ResourceLocation TEXTURE_SPIDER = new ResourceLocation("textures/entity/spider/spider.png");
	private static final ResourceLocation TEXTURE_CAVE_SPIDER = new ResourceLocation("textures/entity/spider/cave_spider.png");
	private static final ResourceLocation TEXTURE_GHAST = new ResourceLocation("textures/entity/ghast/ghast.png");
	private static final ResourceLocation TEXTURE_BLAZE = new ResourceLocation("textures/entity/blaze.png");
	private static final ResourceLocation TEXTURE_MOOSHROOM = new ResourceLocation("textures/entity/cow/mooshroom.png");
	private static final ResourceLocation TEXTURE_BAT = new ResourceLocation("textures/entity/bat.png");
	private static final ResourceLocation TEXTURE_POLARBEAR = new ResourceLocation("textures/entity/bear/polarbear.png");
	private static final ResourceLocation TEXTURE_VILLAGER = new ResourceLocation("textures/entity/villager/villager.png");
	private static final ResourceLocation TEXTURE_ENDERMAN = new ResourceLocation("textures/entity/enderman/enderman.png");
	private static final ResourceLocation TEXTURE_CREEPER = new ResourceLocation("textures/entity/creeper/creeper.png");
	
	public static enum EnumPetform{
		STEVE,
		SHEEP,
		ZOMBIE,
		HUSK,
		SKELETON,
		PIG,
		COW,
		SQUID,
		CHICKEN,
		SPIDER,
		CAVESPIDER,
		BLAZE,
		GHAST,
		MOOSHROOM,
		BAT,
		POLARBEAR,
		VILLAGER,
		ENDERMAN,
		CREEPER
	}

	public static ModelBase getModelForForm(EnumPetform form){
		switch (form) {
		case PIG: return MODEL_PIG;
		case SHEEP: return MODEL_SHEEP2;
		case SKELETON: return MODEL_SKELETON;
		case SQUID: return MODEL_SQUID;
		case CHICKEN: return MODEL_CHICKEN;
		case GHAST: return MODEL_GHAST;
		case BLAZE: return MODEL_BLAZE;
		case BAT : return MODEL_BAT;
		case POLARBEAR : return MODEL_POLARBEAR;
		case VILLAGER : return MODEL_VILLAGER;
		case ENDERMAN : return MODEL_ENDERMAN;
		case CREEPER : return MODEL_CREEPER;
		
		case SPIDER:
		case CAVESPIDER: return MODEL_SPIDER;

		case COW:
		case MOOSHROOM: return MODEL_COW;

		case STEVE:
		case ZOMBIE:
		case HUSK: return MODEL_PLAYER;

		default:return null;
		}
	}

	/**
	 * returns the appropriate texture for given form.
	 * EntityPetBuddy argument needed for player skin
	 * */
	public static ResourceLocation getTextureForModel(EnumPetform form, EntityPetBuddy entity){
		switch (form) {
		case COW: return TEXTURE_COW;
		case PIG: return TEXTURE_PIG;
		case SHEEP: return TEXTURE_SHEEP;
		case STEVE: if(entity.getOwner() != null && entity.getOwner() instanceof EntityPlayer)
			if(((EntityPlayer)entity.getOwner()) instanceof AbstractClientPlayer)
				return ((AbstractClientPlayer)entity.getOwner()).getLocationSkin();
		case ZOMBIE: return TEXTURE_ZOMBIE;
		case HUSK: return TEXTURE_HUSK;
		case SKELETON: return TEXTURE_SKELETON;
		case SQUID: return TEXTURE_SQUID;
		case  CHICKEN : return TEXTURE_CHICKEN;
		case SPIDER: return TEXTURE_SPIDER;
		case CAVESPIDER: return TEXTURE_CAVE_SPIDER;
		case GHAST: return TEXTURE_GHAST;
		case BLAZE: return TEXTURE_BLAZE;
		case MOOSHROOM : return TEXTURE_MOOSHROOM;
		case BAT : return TEXTURE_BAT;
		case POLARBEAR : return TEXTURE_POLARBEAR;
		case VILLAGER : return TEXTURE_VILLAGER;
		case ENDERMAN : return TEXTURE_ENDERMAN;
		case CREEPER : return TEXTURE_CREEPER;
		
		default: return null;
		}
	}

	public static float getRenderOffset(EnumPetform form){
		switch (form) {
		case BAT : case SQUID: return 0.35f;
		case BLAZE : return 0.7F;
		case GHAST : return 0.5f;
		default : return 0F;
		}
	}
	public static float[] getRenderSwordOffset(EnumPetform form){
		switch (form) {

		case GHAST : return new float[]{8, 7,-22};
		case BAT : return new float[]{0f, 2,-20}; 
		case PIG :  return new float[]{7,6,-15};
		case CHICKEN : return new float[]{4,2,-16};
		case SPIDER : case CAVESPIDER : return new float[]{5,5,0};
		case BLAZE : return new float[]{8, 2 ,-8};
		case VILLAGER : return new float[]{-0.5f,6.5f,-6.5f};
		case CREEPER : return new float[]{4,3f,-10};
		case ENDERMAN : return new float[]{0, 2, -25};
		default: return new float[]{7,6,-9};
		}
	}

	public static float getScaleForForm(EnumPetform form){
		switch (form) {
		case HUSK:
		case SPIDER :
			return 0.45f;
		case CHICKEN :
			return 0.56f;
		case BAT : case SQUID :
			return 0.25f;

		default:return 0.4F;
		}
	}

	public static EnumPetform getFormFromItem(ItemStack stack){
		if(stack == null)
			return EnumPetform.STEVE;
		if(stack.getItem() == null)
			return EnumPetform.STEVE;

		if(stack.getItem() instanceof ItemBlock){
			Block block = Block.getBlockFromItem(stack.getItem());
			if(block.equals(Blocks.WOOL))
				return EnumPetform.SHEEP;
			if(block.equals(Blocks.BROWN_MUSHROOM) || block.equals(Blocks.RED_MUSHROOM))
				return EnumPetform.MOOSHROOM;
		}
		else{
			Item item = stack.getItem();
			if(item.equals(Items.ROTTEN_FLESH))
				return EnumPetform.ZOMBIE;
			else if (item.equals(Items.LEATHER))
				return EnumPetform.COW;
			else if (item.equals(Items.DYE) && stack.getMetadata() == 0)
				return EnumPetform.SQUID;
			else if (item.equals(Items.PORKCHOP) || item.equals(Items.COOKED_PORKCHOP))
				return EnumPetform.PIG;
			else if (item.equals(Items.FEATHER))
				return EnumPetform.CHICKEN;
			else if (item.equals(Items.STRING) || item.equals(Items.SPIDER_EYE))
				return EnumPetform.SPIDER;
			else if (item.equals(Items.FERMENTED_SPIDER_EYE))
				return EnumPetform.CAVESPIDER;
			else if (item.equals(Items.GHAST_TEAR))
				return EnumPetform.GHAST;
			else if (item.equals(Items.BLAZE_POWDER)|| item.equals(Items.BLAZE_ROD))
				return EnumPetform.BLAZE;
			else if (item.equals(Items.BONE))
				return EnumPetform.SKELETON;
			else if (item.equals(Items.COOKIE))
				return EnumPetform.BAT;
			else if (item.equals(Items.FISH) && (stack.getMetadata() == 0 || stack.getMetadata() == 1))
				return EnumPetform.POLARBEAR;
			else if (item.equals(Items.BOOK))
				return EnumPetform.VILLAGER;
			else if (item.equals(Items.ENDER_PEARL)||item.equals(Items.ENDER_EYE))
				return EnumPetform.ENDERMAN;
			else if (item.equals(Items.GUNPOWDER))
				return EnumPetform.CREEPER;
		}

		return EnumPetform.STEVE;
	}
}
