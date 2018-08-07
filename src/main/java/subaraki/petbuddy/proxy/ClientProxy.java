package subaraki.petbuddy.proxy;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
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
import net.minecraft.client.model.ModelParrot;
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
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import subaraki.petbuddy.entity.EntityPetBuddy;
import subaraki.petbuddy.entity.RenderPetBuddy;
import subaraki.petbuddy.entity.model.ModelBatFix;
import subaraki.petbuddy.entity.model.ModelGuardianFix;
import subaraki.petbuddy.entity.model.ModelLlamaFix;
import subaraki.petbuddy.entity.model.ModelOcelotFix;
import subaraki.petbuddy.entity.model.ModelRabbitFix;
import subaraki.petbuddy.entity.model.ModelShulkerFix;
import subaraki.petbuddy.entity.model.ModelSkeletonFix;
import subaraki.petbuddy.entity.model.ModelZombieVillagerFix;

public class ClientProxy extends ServerProxy {

	public static KeyBinding summonPet;
	
	@Override
	public void registerKey() {
		summonPet = new KeyBinding("Summon/Dismiss Pet", Keyboard.KEY_P, "Pet Buddy");
		ClientRegistry.registerKeyBinding(summonPet);
	}
	
	@Override
	public void registerRenders() {
		RenderingRegistry.registerEntityRenderingHandler(EntityPetBuddy.class, RenderPetBuddy::new);
	}
	
	@Override
	public World getClientWorld() {
		return Minecraft.getMinecraft().world;
	}
	
	@Override
	public EntityPlayer getClientPlayer() {
		return Minecraft.getMinecraft().player;
	}
	
	@Override
	public ResourceLocation getFriendSkin() {
		return DefaultPlayerSkin.getDefaultSkinLegacy();
	}



	
	
	

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
			if(!entitylivingbaseIn.getHeldItemMainhand().isEmpty()){
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
	private static final ModelShulkerFix MODEL_SHULKER = new ModelShulkerFix();
	private static final ModelParrot MODEL_PARROT = new ModelParrot();
	private static final ModelLlamaFix MODEL_LLAMA= new ModelLlamaFix(0.7f);
	private static final ModelGuardianFix MODEL_GUARDIAN= new ModelGuardianFix();

	public ModelBase getSheep() {return MODEL_SHEEP2;}
	public ModelBase getPolarBear() {return MODEL_POLARBEAR;}
	public ModelBase getIronGolem() {return MODEL_IRONGOLEM;}
	public ModelBase getDoge() {return MODEL_DOGE;}
	public ModelBase getBat() {return MODEL_BAT;}
	public ModelBase getSkeleton() {return MODEL_SKELETON;}
	public ModelBase getBiped() {return MODEL_BIPED;}
	public ModelBase getSteve() {return MODEL_PLAYER_STEVE;}
	public ModelBase getAlex() {return MODEL_PLAYER_ALEX;}
	public ModelBase getVillagerZombie() {return MODEL_VILLAGER_ZOMBIE;}
	public ModelBase getCow() {return MODEL_COW;}
	public ModelBase getSquid() {return MODEL_SQUID;}
	public ModelBase getPig() {return MODEL_PIG;}
	public ModelBase getChicken() {return MODEL_CHICKEN;}
	public ModelBase getSpider() {return MODEL_SPIDER;}
	public ModelBase getGhast() {return MODEL_GHAST;}
	public ModelBase getBlaze() {return MODEL_BLAZE;}
	public ModelBase getVillager() {return MODEL_VILLAGER;}
	public ModelBase getEnderman() {return MODEL_ENDERMAN;}
	public ModelBase getCreeper() {return MODEL_CREEPER;}
	public ModelBase getRabbit() {return MODEL_RABBIT;}
	public ModelBase getEndermite() {return MODEL_ENDERMITE;}
	public ModelBase getSilverfish() {return MODEL_SILVERFISH;}
	public ModelBase getSnowman() {return MODEL_SNOWMAN;}
	public ModelBase getCate() {return MODEL_CATE;}
	public ModelBase getPolarWitch() {return MODEL_WITCH;}
	public ModelBase getShulker() {return MODEL_SHULKER;}
	public ModelBase getParrot() {return MODEL_PARROT;}
	public ModelBase getLLama() {return MODEL_LLAMA;}
	public ModelBase getGuardian() {return MODEL_GUARDIAN;}
}