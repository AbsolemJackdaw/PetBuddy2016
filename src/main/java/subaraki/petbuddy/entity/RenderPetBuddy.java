package subaraki.petbuddy.entity;

import java.util.Map;
import java.util.UUID;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelEnderman;
import net.minecraft.client.model.ModelIronGolem;
import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelSheep1;
import net.minecraft.client.model.ModelSheep2;
import net.minecraft.client.model.ModelSnowMan;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import subaraki.petbuddy.entity.PetForm.EnumPetform;
import subaraki.petbuddy.entity.model.ModelBatFix;
import subaraki.petbuddy.network.NetworkHandler;
import subaraki.petbuddy.network.PacketSyncPetRenderData;

public class RenderPetBuddy extends RenderBiped<EntityPetBuddy> implements IRenderFactory{
	private static final ResourceLocation ZOMBIE_TEXTURES = new ResourceLocation("textures/entity/zombie/zombie.png");
	private ItemStack previousPetForm = ItemStack.EMPTY;

	public RenderPetBuddy(RenderManager renderManager) {
		super(renderManager, new ModelBiped(0,0,64,64), 0.25f);
		clearLayers();
		initBipedLayer();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityPetBuddy entity) {
		return PetForm.getTextureForModel(entity);
	}

	@Override
	public Render createRenderFor(RenderManager manager) {
		return this;
	}

	@Override
	public void doRender(EntityPetBuddy entity, double x, double y, double z, float entityYaw, float partialTicks) {
		//should be called only once : when the stack in the inventory is changed 
		boolean force =  entity.shouldForceRenderUpdate();
		if(!ItemStack.areItemStacksEqual(previousPetForm, entity.getStackDefiningForm()) || force){

			previousPetForm = entity.getStackDefiningForm();
			//gui rendering client side only fix. packet never gets processed correctly because it is client side only
			entity.setForceRender(false);
			NetworkHandler.NETWORK.sendToServer(new PacketSyncPetRenderData(false));

			//Set texture only once
			if(entity.getNameFromTag().length() > 1){

				GameProfile profile = TileEntitySkull.updateGameProfile(new GameProfile((UUID)null, entity.getNameFromTag()));

				ResourceLocation resourcelocation = DefaultPlayerSkin.getDefaultSkinLegacy();

				if (profile != null)
				{
					SkinManager skinMngr= Minecraft.getMinecraft().getSkinManager();
					Map<Type, MinecraftProfileTexture> map = skinMngr.loadSkinFromCache(profile);

					if (map.containsKey(Type.SKIN))
					{
						resourcelocation = skinMngr.loadSkin((MinecraftProfileTexture)map.get(Type.SKIN), Type.SKIN);
					}
					else
					{
						UUID uuid = EntityPlayer.getUUID(profile);
						resourcelocation = DefaultPlayerSkin.getDefaultSkin(uuid);
					}
				}

				entity.FRIENDSKIN = resourcelocation;
			}

			//clear layers before setting model
			clearLayers();

			//Set new model
			this.mainModel = PetForm.getModelForForm(entity);
			//Set correct layers
			if(mainModel instanceof ModelSheep2)
				initSheepLayer();
			//ender man are too slender to wear armor :/
			else if (mainModel instanceof ModelBiped && !(mainModel instanceof ModelEnderman))
				initBipedLayer();
			else if (entity.getForm().equals(EnumPetform.MOOSHROOM))
				initMooshroomLayer();

			if(!(mainModel instanceof ModelBiped) || (mainModel instanceof ModelEnderman) )
				addLayer(new RenderHeldItemOther(this));

		}
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	protected void preRenderCallback(EntityPetBuddy pet, float partialTickTime) {
		float scale = PetForm.getScaleForForm(pet.getForm());
		float offset = PetForm.getRenderOffset(pet.getForm());
		GlStateManager.translate(0, -offset, 0);
		GlStateManager.scale(scale,scale,scale);
		super.preRenderCallback(pet, partialTickTime);
	}

	@Override
	protected void renderEntityName(EntityPetBuddy entityIn, double x, double y, double z, String name,
			double p_188296_9_) {
		String format = entityIn.getHealth() > entityIn.getMaxHealth()*(2f/3f) ? ChatFormatting.GREEN+"" : entityIn.getHealth() > entityIn.getMaxHealth()*(1f/3f) ? TextFormatting.GOLD+"" : entityIn.getHealth() > 10 ? ""+TextFormatting.DARK_RED : ""+TextFormatting.RED;
		
		super.renderEntityName(entityIn, x, y, z, format+name, p_188296_9_);
	}
	private void clearLayers(){
		this.layerRenderers.clear();
	}

	private void initBipedLayer(){
		this.addLayer(new LayerHeldItem(this));
		this.addLayer(new LayerBipedArmor(this)
		{
			protected void initArmor()
			{
				this.modelLeggings = new ModelBiped(0.5F);
				this.modelArmor = new ModelBiped(1.0F);
			}
		});
	}

	private void initSheepLayer(){
		this.addLayer(new RenderWoolLayer(this));
	}

	private void initMooshroomLayer(){
		this.addLayer(new RenderMushroomLayer(this));
	}

	/////////////////////////////////////

	private static class RenderWoolLayer implements LayerRenderer<EntityPetBuddy>{
		private RenderPetBuddy render;
		private static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/sheep/sheep_fur.png");
		private static final ModelSheep1 MODEL_SHEEP1 = new ModelSheep1(){
			public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float p_78086_2_, float p_78086_3_, float partialTickTime) {
				//keep empty. do nothing here
			}
		};

		public RenderWoolLayer(RenderPetBuddy render){
			this.render = render;
		}

		@Override
		public void doRenderLayer(EntityPetBuddy pet, float limbSwing, float limbSwingAmount,
				float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
			this.render.bindTexture(TEXTURE);
			if(!pet.getStackDefiningForm().isEmpty()){
				float[] color = pet.getColorFromDye(EnumDyeColor.byMetadata(pet.getStackDefiningForm().getMetadata()));
				GlStateManager.color(color[0], color[1], color[2]);
			}
			this.MODEL_SHEEP1.setModelAttributes(this.render.getMainModel());
			this.MODEL_SHEEP1.setLivingAnimations(pet, limbSwing, limbSwingAmount, partialTicks);
			this.MODEL_SHEEP1.render(pet, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		}

		@Override
		public boolean shouldCombineTextures() {
			return true;
		}

	}

	/////////////////////////////////////

	public static class RenderMushroomLayer implements LayerRenderer<EntityPetBuddy>{
		private RenderPetBuddy render;
		public RenderMushroomLayer(RenderPetBuddy render) {
			this.render = render;
		}
		public void doRenderLayer(EntityPetBuddy entitylivingbaseIn, float limbSwing, float limbSwingAmount,float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
			BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
			this.render.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			GlStateManager.enableCull();
			GlStateManager.cullFace(GlStateManager.CullFace.FRONT);
			GlStateManager.pushMatrix();
			GlStateManager.scale(1.0F, -1.0F, 1.0F);
			GlStateManager.translate(0.2F, 0.35F, 0.5F);
			GlStateManager.rotate(42.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.pushMatrix();
			GlStateManager.translate(-0.5F, -0.5F, 0.5F);
			blockrendererdispatcher.renderBlockBrightness(Blocks.RED_MUSHROOM.getDefaultState(), 1.0F);
			GlStateManager.popMatrix();
			GlStateManager.pushMatrix();
			GlStateManager.translate(0.1F, 0.0F, -0.6F);
			GlStateManager.rotate(42.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.translate(-0.5F, -0.5F, 0.5F);
			blockrendererdispatcher.renderBlockBrightness(Blocks.RED_MUSHROOM.getDefaultState(), 1.0F);
			GlStateManager.popMatrix();
			GlStateManager.popMatrix();
			GlStateManager.pushMatrix();
			((ModelQuadruped)this.render.getMainModel()).head.postRender(0.0625F);
			GlStateManager.scale(1.0F, -1.0F, 1.0F);
			GlStateManager.translate(0.0F, 0.7F, -0.2F);
			GlStateManager.rotate(12.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.translate(-0.5F, -0.5F, 0.5F);
			blockrendererdispatcher.renderBlockBrightness(Blocks.RED_MUSHROOM.getDefaultState(), 1.0F);
			GlStateManager.popMatrix();
			GlStateManager.cullFace(GlStateManager.CullFace.BACK);
			GlStateManager.disableCull();
		}

		public boolean shouldCombineTextures(){
			return true;
		}
	}

	////////////////////////////////////////

	public class RenderHeldItemOther implements LayerRenderer<EntityPetBuddy>
	{
		protected final RenderLivingBase<EntityPetBuddy> livingEntityRenderer;

		public RenderHeldItemOther(RenderLivingBase<EntityPetBuddy> livingEntityRendererIn)
		{
			this.livingEntityRenderer = livingEntityRendererIn;
		}

		public void doRenderLayer(EntityPetBuddy entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
		{
			boolean flag = entitylivingbaseIn.getPrimaryHand() == EnumHandSide.RIGHT;
			ItemStack itemstack = flag ? entitylivingbaseIn.getHeldItemOffhand() : entitylivingbaseIn.getHeldItemMainhand();
			ItemStack itemstack1 = flag ? entitylivingbaseIn.getHeldItemMainhand() : entitylivingbaseIn.getHeldItemOffhand();

			if (!itemstack.isEmpty() || !itemstack1.isEmpty())
			{
				GlStateManager.pushMatrix();

				this.renderHeldItem(entitylivingbaseIn, itemstack1, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, EnumHandSide.RIGHT);
				this.renderHeldItem(entitylivingbaseIn, itemstack, ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, EnumHandSide.LEFT);
				GlStateManager.popMatrix();
			}
		}

		private void renderHeldItem(EntityPetBuddy pet, ItemStack stack, ItemCameraTransforms.TransformType cameraTransform, EnumHandSide handSide)
		{
			if (!stack.isEmpty())
			{
				GlStateManager.pushMatrix();
				if(pet.getForm().equals(EnumPetform.BAT))
					((ModelBatFix)livingEntityRenderer.getMainModel()).batBody.postRender(0.0625f);
				if(pet.getForm().equals(EnumPetform.SPIDER) || pet.getForm().equals(EnumPetform.CAVESPIDER))
					((ModelSpider)livingEntityRenderer.getMainModel()).spiderHead.postRender(0.0625f);
				if(pet.getForm().equals(EnumPetform.ENDERMAN))
					((ModelEnderman)livingEntityRenderer.getMainModel()).postRenderArm(0.0625f, EnumHandSide.RIGHT);
				if(pet.getForm().equals(EnumPetform.SNOWMAN))
					((ModelSnowMan)livingEntityRenderer.getMainModel()).body.postRender(0.0625f);
				if(pet.getForm().equals(EnumPetform.IRONGOLEM))
					((ModelIronGolem)livingEntityRenderer.getMainModel()).ironGolemRightArm.postRender(0.0625f);

				GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
				GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
				float scale[] = pet.getForm().getSwordOffset();
				scale = new float[]{-9,0,-16};
				GlStateManager.translate(scale[0]/16.0F,scale[1]/16.0F,scale[2]/16.0F);
				GlStateManager.rotate(35.0F, 1.0F, 0.0F, 0.0F);
				Minecraft.getMinecraft().getItemRenderer().renderItemSide(pet, stack, cameraTransform, false);
				GlStateManager.popMatrix();
			}
		}

		public boolean shouldCombineTextures()
		{
			return false;
		}
	}
	//////////////////////

	private float someFloat = 0;
	@Override
	protected float handleRotationFloat(EntityPetBuddy livingBase, float partialTicks) {
		if(livingBase.getForm().equals(EnumPetform.SQUID))
			return (float)Math.sin(someFloat+=0.4f * 0.25f)/3f + 0.4f; //lastTentacleAngle + (tentacleAngle - lastTentacleAngle) * partialTicks;
		else if (livingBase.getForm().equals(EnumPetform.BLAZE) || livingBase.getForm().equals(EnumPetform.GHAST)|| livingBase.getForm().equals(EnumPetform.BAT)||
				livingBase.getForm().equals(EnumPetform.SILVERFISH) || livingBase.getForm().equals(EnumPetform.ENDERMITE))
			return someFloat+=0.8f;
		else if(livingBase.getForm().equals(EnumPetform.DOGE))
			return 8f;
		else if (livingBase.getForm().equals(EnumPetform.GUARDIAN))
			return someFloat+=0.1f;

		return 0f;
	}
}
