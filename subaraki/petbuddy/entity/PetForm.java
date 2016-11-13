package subaraki.petbuddy.entity;

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
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
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

	public static ModelBase getModelForForm(EntityPetBuddy entity){
		switch (entity.getForm()){
		case ZOMBIE :
			return entity.getTextureIndex() >= 6 ? MODEL_BIPED : MODEL_VILLAGER_ZOMBIE;
		case STEVE:
		case FRIEND:
			return entity.getModelType().equals("default") ? MODEL_PLAYER_STEVE : MODEL_PLAYER_ALEX;

		default:return entity.getForm().getModel();
		}
	}

	/**
	 * returns the appropriate texture for given form.
	 * EntityPetBuddy argument needed for player skin
	 * */
	public static ResourceLocation getTextureForModel(EntityPetBuddy entity){
		switch (entity.getForm()) {
		case ZOMBIE: return TEXTURE_ZOMBIE[entity.getTextureIndex()];
		case SKELETON : return TEXTURE_SKELETON[entity.getTextureIndex()];
		case VILLAGER : return TEXTURE_VILLAGER[entity.getTextureIndex()];
		case RABBIT : return TEXTURE_RABBIT[entity.getTextureIndex()];
		case CATE : return TEXTURE_CATE[entity.getTextureIndex()];
		case FRIEND : return entity.FRIENDSKIN;
		case STEVE: 
			if(entity.getOwner() != null && ((EntityPlayer)entity.getOwner()) instanceof AbstractClientPlayer)
				return ((AbstractClientPlayer)entity.getOwner()).getLocationSkin();

		default : return entity.getForm().getResloc();
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
		default : 
			return 0.4F;
		}
	}

	public static EnumPetform getFormFromItem(ItemStack stack){
		if(stack == null || stack.getItem() == null)
			return EnumPetform.STEVE;

		for(EnumPetform form : EnumPetform.values()){
			if(form.itemstacks == null) continue;
			for(ItemStack identifier : form.itemstacks){
				if(ItemStack.areItemsEqual(identifier, stack))
					return form;
			}
		}

		return EnumPetform.STEVE;
	}

	public static enum EnumPetform{
		STEVE(null, MODEL_BIPED, null, (ItemStack[])null),
		SHEEP(TEXTURE_SHEEP, MODEL_SHEEP2, new float[]{7,6,-9}, new ItemStack[]{new ItemStack(Blocks.WOOL)}),
		ZOMBIE(null, MODEL_BIPED, null, new ItemStack[]{new ItemStack(Items.ROTTEN_FLESH)}),
		SKELETON(null, MODEL_SKELETON, null,  new ItemStack[]{new ItemStack(Items.ARROW)}),
		PIG(TEXTURE_PIG, MODEL_PIG, new float[]{7,6,-15}, new ItemStack[]{new ItemStack(Items.PORKCHOP)}),
		COW(TEXTURE_COW, MODEL_COW, new float[]{7,6,-9}, new ItemStack[]{new ItemStack(Items.LEATHER), new ItemStack(Items.BEEF)}),
		SQUID(TEXTURE_SQUID, MODEL_SQUID, new float[]{7,6,-9}, new ItemStack[]{new ItemStack(Items.DYE,1,0)}),
		CHICKEN(TEXTURE_CHICKEN, MODEL_CHICKEN, new float[]{4,2,-16}, new ItemStack[]{new ItemStack(Items.FEATHER), new ItemStack(Items.CHICKEN)}),
		SPIDER(TEXTURE_SPIDER, MODEL_SPIDER, new float[]{5,5,0}, new ItemStack[]{new ItemStack(Items.SPIDER_EYE)}),
		CAVESPIDER(TEXTURE_CAVE_SPIDER, MODEL_SPIDER, new float[]{5,5,0}, new ItemStack[]{new ItemStack(Items.FERMENTED_SPIDER_EYE)}),
		BLAZE(TEXTURE_BLAZE, MODEL_BLAZE, new float[]{8, 2 ,-8}, new ItemStack[]{new ItemStack(Items.BLAZE_ROD)}),
		GHAST(TEXTURE_GHAST, MODEL_GHAST, new float[]{8, 7,-22}, new ItemStack[]{new ItemStack(Items.GHAST_TEAR)}),
		MOOSHROOM(TEXTURE_MOOSHROOM, MODEL_COW, new float[]{7,6,-9}, new ItemStack[]{new ItemStack(Blocks.BROWN_MUSHROOM), new ItemStack(Blocks.RED_MUSHROOM)}),
		BAT(TEXTURE_BAT, MODEL_BAT, new float[]{0f, 2,-20}, new ItemStack[]{new ItemStack(Items.COOKIE)}),
		POLARBEAR(TEXTURE_POLARBEAR, MODEL_POLARBEAR, new float[]{7,6,-9}, new ItemStack[]{new ItemStack(Items.FISH,1,0), new ItemStack(Items.FISH, 1, 1)}),
		VILLAGER(null, MODEL_VILLAGER, new float[]{-0.5f,6.5f,-6.5f}, new ItemStack[]{new ItemStack(Items.BOOK)}),
		ENDERMAN(TEXTURE_ENDERMAN, MODEL_ENDERMAN, new float[]{0, 2, -25}, new ItemStack[]{new ItemStack(Items.ENDER_PEARL), new ItemStack(Items.ENDER_EYE)}),
		CREEPER(TEXTURE_CREEPER, MODEL_CREEPER, new float[]{4,3f,-10}, new ItemStack[]{new ItemStack(Items.GUNPOWDER)}),
		RABBIT(null, MODEL_RABBIT, new float[]{3,0,-20}, new ItemStack[]{new ItemStack(Items.RABBIT_FOOT)}),
		ZOMBIEPIGMAN(TEXTURE_PIGMAN, MODEL_BIPED, null, new ItemStack[]{new ItemStack(Items.NETHER_WART)}),
		ENDERMITE(TEXTURE_ENDERMITE, MODEL_ENDERMITE, new float[]{0,0,-18}, new ItemStack[]{new ItemStack(Blocks.END_STONE)}),
		SILVERFISH(TEXTURE_SILVERFISH, MODEL_SILVERFISH, new float[]{0,0,-18}, new ItemStack[]{new ItemStack(Blocks.STONEBRICK)}),
		IRONGOLEM(TEXTURE_IRON_GOLEM, MODEL_IRONGOLEM, new float[]{11,4,-25}, new ItemStack[]{new ItemStack(Blocks.RED_FLOWER)}),
		SNOWMAN(TEXTURE_SNOW_MAN, MODEL_SNOWMAN, new float[]{10,2,-3}, new ItemStack[]{new ItemStack(Blocks.SNOW)}),
		CATE(null, MODEL_CATE, new float[]{0,-1,-12}, new ItemStack[]{new ItemStack(Items.COOKED_FISH,1,0), new ItemStack(Items.COOKED_FISH, 1, 1)}),
		WITCH(TEXTURE_WITCH, MODEL_WITCH, new float[]{-0.5f,6.5f,-6.5f}, new ItemStack[]{new ItemStack(Items.POTIONITEM)}),
		DOGE(TEXTURE_DOGE, MODEL_DOGE, new float[]{4,0,-12}, new ItemStack[]{new ItemStack(Items.BONE)}),
		FRIEND(null, null, null, new ItemStack[]{new ItemStack(Items.NAME_TAG)});

		private ResourceLocation resloc;
		private ModelBase model;
		private ItemStack[] itemstacks;
		private float[] swordoffset;

		private EnumPetform(ResourceLocation resloc, ModelBase model, float[] swordOffset, ItemStack... stack){
			this.resloc = resloc;
			this.model = model;
			this.itemstacks = stack;
			this.swordoffset = swordOffset;
		}

		public ResourceLocation getResloc() {
			return resloc;
		}

		public ModelBase getModel() {
			return model;
		}

		public ItemStack[] getItemStacks() {
			return itemstacks;
		}

		public float[] getSwordOffset() {
			return swordoffset;
		}
	}
}
