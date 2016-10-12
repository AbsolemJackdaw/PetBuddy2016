package subaraki.petbuddy.entity;

import java.util.Map;
import java.util.UUID;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import com.mojang.realmsclient.dto.PlayerInfo;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBlaze;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.model.ModelEnderMite;
import net.minecraft.client.model.ModelEnderman;
import net.minecraft.client.model.ModelGhast;
import net.minecraft.client.model.ModelIronGolem;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.model.ModelPolarBear;
import net.minecraft.client.model.ModelSheep2;
import net.minecraft.client.model.ModelSilverfish;
import net.minecraft.client.model.ModelSnowMan;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.model.ModelSquid;
import net.minecraft.client.model.ModelVillager;
import net.minecraft.client.model.ModelWitch;
import net.minecraft.client.model.ModelWolf;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import subaraki.petbuddy.entity.model.ModelBatFix;
import subaraki.petbuddy.entity.model.ModelOcelotFix;
import subaraki.petbuddy.entity.model.ModelRabbitFix;
import subaraki.petbuddy.entity.model.ModelSkeletonFix;
import subaraki.petbuddy.entity.model.ModelZombieVillagerFix;

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

	private static final ModelIronGolem MODEL_IRONGOLEM = new ModelIronGolem(){
		public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float p_78086_2_, float p_78086_3_, float partialTickTime) {
			if(entitylivingbaseIn.getHeldItemMainhand() != null){
				this.ironGolemRightArm.rotateAngleX = -0.8F + 0.025F * this.triangleWave(400f, 70.0F);
				this.ironGolemLeftArm.rotateAngleX = 0.0F;
			}else{
				this.ironGolemRightArm.rotateAngleX = (-0.2F + 1.5F * this.triangleWave(p_78086_2_, 13.0F)) * p_78086_3_;
				this.ironGolemLeftArm.rotateAngleX = (-0.2F - 1.5F * this.triangleWave(p_78086_2_, 13.0F)) * p_78086_3_;
			}
		}
		private float triangleWave(float p_78172_1_, float p_78172_2_){
			return (Math.abs(p_78172_1_ % p_78172_2_ - p_78172_2_ * 0.5F) - p_78172_2_ * 0.25F) / (p_78172_2_ * 0.25F);
		}
	};

	private static final ModelWolf MODEL_DOGE = new ModelWolf(){
		public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float p_78086_2_, float p_78086_3_, float partialTickTime) {
			this.wolfBody.setRotationPoint(0.0F, 14.0F, 2.0F);
			this.wolfBody.rotateAngleX = ((float)Math.PI / 2F);

			if(this.boxList.size() >= 8){
				this.boxList.get(2).setRotationPoint(-1.0F, 14.0F, -3.0F);
				this.boxList.get(2).rotateAngleX = this.wolfBody.rotateAngleX;
				this.boxList.get(7).setRotationPoint(-1.0F, 12.0F, 8.0F);
			}
			this.wolfLeg1.setRotationPoint(-2.5F, 16.0F, 7.0F);
			this.wolfLeg2.setRotationPoint(0.5F, 16.0F, 7.0F);
			this.wolfLeg3.setRotationPoint(-2.5F, 16.0F, -4.0F);
			this.wolfLeg4.setRotationPoint(0.5F, 16.0F, -4.0F);
			this.wolfLeg1.rotateAngleX = MathHelper.cos(p_78086_2_ * 0.6662F) * 1.4F * p_78086_3_;
			this.wolfLeg2.rotateAngleX = MathHelper.cos(p_78086_2_ * 0.6662F + (float)Math.PI) * 1.4F * p_78086_3_;
			this.wolfLeg3.rotateAngleX = MathHelper.cos(p_78086_2_ * 0.6662F + (float)Math.PI) * 1.4F * p_78086_3_;
			this.wolfLeg4.rotateAngleX = MathHelper.cos(p_78086_2_ * 0.6662F) * 1.4F * p_78086_3_;

		};
	};


	private static final ModelBatFix MODEL_BAT = new ModelBatFix();
	private static final ModelSkeletonFix MODEL_SKELETON = new ModelSkeletonFix();
	private static final ModelBiped MODEL_BIPED = new ModelBiped(0,0,64,64);
	public static final ModelPlayer MODEL_PLAYER_STEVE = new ModelPlayer(0, false);
	public static final ModelPlayer MODEL_PLAYER_ALEX = new ModelPlayer(0, true);
	private static final ModelZombieVillagerFix MODEL_VILLAGER_ZOMBIE = new ModelZombieVillagerFix();
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
	private static final ModelRabbitFix MODEL_RABBIT = new ModelRabbitFix();
	private static final ModelEnderMite MODEL_ENDERMITE = new ModelEnderMite();
	private static final ModelSilverfish MODEL_SILVERFISH = new ModelSilverfish();
	private static final ModelSnowMan MODEL_SNOWMAN = new ModelSnowMan();
	private static final ModelOcelotFix MODEL_CATE = new ModelOcelotFix();
	private static final ModelWitch MODEL_WITCH = new ModelWitch(0);

	private static final ResourceLocation TEXTURE_SHEEP = new ResourceLocation("textures/entity/sheep/sheep.png");
	private static final ResourceLocation TEXTURE_HUSK = new ResourceLocation("textures/entity/zombie/husk.png");
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
	private static final ResourceLocation TEXTURE_ENDERMAN = new ResourceLocation("textures/entity/enderman/enderman.png");
	private static final ResourceLocation TEXTURE_CREEPER = new ResourceLocation("textures/entity/creeper/creeper.png");
	private static final ResourceLocation TEXTURE_PIGMAN = new ResourceLocation("textures/entity/zombie_pigman.png");
	private static final ResourceLocation TEXTURE_ENDERMITE = new ResourceLocation("textures/entity/endermite.png");
	private static final ResourceLocation TEXTURE_SILVERFISH = new ResourceLocation("textures/entity/silverfish.png");
	private static final ResourceLocation TEXTURE_IRON_GOLEM= new ResourceLocation("textures/entity/iron_golem.png");
	private static final ResourceLocation TEXTURE_SNOW_MAN = new ResourceLocation("textures/entity/snowman.png");
	private static final ResourceLocation TEXTURE_WITCH = new ResourceLocation("textures/entity/witch.png");
	private static final ResourceLocation TEXTURE_DOGE = new ResourceLocation("textures/entity/wolf/wolf.png");
	public static ResourceLocation FRIENDSKIN = DefaultPlayerSkin.getDefaultSkinLegacy();;

	public static final ResourceLocation[] TEXTURE_RABBIT = new ResourceLocation[]{
			new ResourceLocation("textures/entity/rabbit/brown.png"),
			new ResourceLocation("textures/entity/rabbit/white.png"),
			new ResourceLocation("textures/entity/rabbit/black.png"),
			new ResourceLocation("textures/entity/rabbit/gold.png"),
			new ResourceLocation("textures/entity/rabbit/salt.png"),
			new ResourceLocation("textures/entity/rabbit/white_splotched.png")
	};
	public static final ResourceLocation[] TEXTURE_ZOMBIE = new ResourceLocation[]{
			new ResourceLocation("textures/entity/zombie_villager/zombie_villager.png"),
			new ResourceLocation("textures/entity/zombie_villager/zombie_farmer.png"),
			new ResourceLocation("textures/entity/zombie_villager/zombie_librarian.png"),
			new ResourceLocation("textures/entity/zombie_villager/zombie_priest.png"),
			new ResourceLocation("textures/entity/zombie_villager/zombie_smith.png"),
			new ResourceLocation("textures/entity/zombie_villager/zombie_butcher.png"),
			new ResourceLocation("textures/entity/zombie/zombie.png"),
			new ResourceLocation("textures/entity/zombie/husk.png")
	};
	public static final ResourceLocation TEXTURE_SKELETON[] = new ResourceLocation[]{
			new ResourceLocation("textures/entity/skeleton/skeleton.png"),
			new ResourceLocation("textures/entity/skeleton/wither_skeleton.png"),
			new ResourceLocation("textures/entity/skeleton/stray.png")
	};
	public static final ResourceLocation[] TEXTURE_VILLAGER = new ResourceLocation[]{
			new ResourceLocation("textures/entity/villager/villager.png"),
			new ResourceLocation("textures/entity/villager/farmer.png"),
			new ResourceLocation("textures/entity/villager/librarian.png"),
			new ResourceLocation("textures/entity/villager/priest.png"),
			new ResourceLocation("textures/entity/villager/smith.png"),
			new ResourceLocation("textures/entity/villager/butcher.png")
	};
	public static final ResourceLocation[] TEXTURE_CATE = new ResourceLocation[]{
			new ResourceLocation("textures/entity/cat/black.png"),
			new ResourceLocation("textures/entity/cat/ocelot.png"),
			new ResourceLocation("textures/entity/cat/red.png"),
			new ResourceLocation("textures/entity/cat/siamese.png")
	};

	public static enum EnumPetform{
		STEVE,
		SHEEP,
		ZOMBIE,
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
		CREEPER,
		RABBIT,
		ZOMBIEPIGMAN,
		ENDERMITE,
		SILVERFISH,
		IRONGOLEM,
		SNOWMAN,
		CATE,
		WITCH,
		DOGE,
		FRIEND
	}

	public static ModelBase getModelForForm(EntityPetBuddy entity){
		switch (entity.getForm()){
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
		case RABBIT : return MODEL_RABBIT;
		case SILVERFISH : return MODEL_SILVERFISH;
		case ENDERMITE : return MODEL_ENDERMITE;
		case IRONGOLEM : return MODEL_IRONGOLEM;
		case SNOWMAN : return MODEL_SNOWMAN;
		case CATE : return MODEL_CATE;
		case WITCH : return MODEL_WITCH;
		case DOGE : return MODEL_DOGE;

		case SPIDER:
		case CAVESPIDER: return MODEL_SPIDER;

		case COW:
		case MOOSHROOM: return MODEL_COW;

		case ZOMBIEPIGMAN:
			return MODEL_BIPED;
		case ZOMBIE :
			return entity.getTextureIndex() >= 6 ? MODEL_BIPED : MODEL_VILLAGER_ZOMBIE;

		case STEVE:
		case FRIEND:
			return entity.getModelType().equals("default") ? MODEL_PLAYER_STEVE : MODEL_PLAYER_ALEX;

		default:return null;
		}
	}

	/**
	 * returns the appropriate texture for given form.
	 * EntityPetBuddy argument needed for player skin
	 * */
	public static ResourceLocation getTextureForModel(EntityPetBuddy entity){
		switch (entity.getForm()) {
		case COW: return TEXTURE_COW;
		case PIG: return TEXTURE_PIG;
		case SHEEP: return TEXTURE_SHEEP;
		case ZOMBIE: return TEXTURE_ZOMBIE[entity.getTextureIndex()];
		case SKELETON: return TEXTURE_SKELETON[entity.getTextureIndex()];
		case SQUID: return TEXTURE_SQUID;
		case  CHICKEN : return TEXTURE_CHICKEN;
		case SPIDER: return TEXTURE_SPIDER;
		case CAVESPIDER: return TEXTURE_CAVE_SPIDER;
		case GHAST: return TEXTURE_GHAST;
		case BLAZE: return TEXTURE_BLAZE;
		case MOOSHROOM : return TEXTURE_MOOSHROOM;
		case BAT : return TEXTURE_BAT;
		case POLARBEAR : return TEXTURE_POLARBEAR;
		case VILLAGER : return TEXTURE_VILLAGER[entity.getTextureIndex()];
		case ENDERMAN : return TEXTURE_ENDERMAN;
		case CREEPER : return TEXTURE_CREEPER;
		case RABBIT : return TEXTURE_RABBIT[entity.getTextureIndex()];
		case ZOMBIEPIGMAN : return TEXTURE_PIGMAN;
		case IRONGOLEM : return TEXTURE_IRON_GOLEM;
		case ENDERMITE : return TEXTURE_ENDERMITE;
		case SILVERFISH : return TEXTURE_SILVERFISH;
		case SNOWMAN : return TEXTURE_SNOW_MAN;
		case CATE : return TEXTURE_CATE[entity.getTextureIndex()];
		case WITCH : return TEXTURE_WITCH;
		case DOGE : return TEXTURE_DOGE;

		case FRIEND :
			return FRIENDSKIN;
		case STEVE: 
			if(entity.getOwner() != null && entity.getOwner() instanceof EntityPlayer)
				if(((EntityPlayer)entity.getOwner()) instanceof AbstractClientPlayer)
					return ((AbstractClientPlayer)entity.getOwner()).getLocationSkin();

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
		case VILLAGER : case WITCH : return new float[]{-0.5f,6.5f,-6.5f};
		case CREEPER : return new float[]{4,3f,-10};
		case ENDERMAN : return new float[]{0, 2, -25};
		case RABBIT : return new float[]{3,0,-20};
		case ENDERMITE : case SILVERFISH : return new float[]{0,0,-18};
		case SNOWMAN : return new float[]{10,2,-3};
		case IRONGOLEM : return new float[]{11,4,-25};
		case CATE : return new float[]{0,-1,-12};
		case DOGE : return new float[]{4,0,-12};

		default: return new float[]{7,6,-9};
		}
	}

	public static float getScaleForForm(EnumPetform form){
		switch (form) {
		case SPIDER :
			return 0.45f;
		case ENDERMITE : case SILVERFISH : case CHICKEN :
			return 0.56f;
		case RABBIT :
			return 0.6f;
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
			else if(block.equals(Blocks.BROWN_MUSHROOM) || block.equals(Blocks.RED_MUSHROOM))
				return EnumPetform.MOOSHROOM;
			else if(block.equals(Blocks.END_STONE))
				return EnumPetform.ENDERMITE;
			else if(block.equals(Blocks.STONEBRICK))
				return EnumPetform.SILVERFISH;
			else if(block.equals(Blocks.RED_FLOWER))
				return EnumPetform.IRONGOLEM;
			else if (block.equals(Blocks.SNOW))
				return EnumPetform.SNOWMAN;
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
			else if (item.equals(Items.ARROW))
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
			else if (item.equals(Items.RABBIT_FOOT))
				return EnumPetform.RABBIT;
			else if (item.equals(Items.NETHER_WART))
				return EnumPetform.ZOMBIEPIGMAN;
			else if (item.equals(Items.COOKED_FISH) && (stack.getMetadata() == 0 || stack.getMetadata() == 1))
				return EnumPetform.CATE;
			else if (item.equals(Items.POTIONITEM))
				return EnumPetform.WITCH;
			else if (item.equals(Items.BONE))
				return EnumPetform.DOGE;
			else if (item.equals(Items.NAME_TAG))
				return EnumPetform.FRIEND;
		}

		return EnumPetform.STEVE;
	}
}
