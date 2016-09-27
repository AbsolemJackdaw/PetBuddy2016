package subaraki.petbuddy.entity;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderPetBuddy extends RenderBiped<EntityPetBuddy> implements IRenderFactory{
	private static final ResourceLocation ZOMBIE_TEXTURES = new ResourceLocation("textures/entity/zombie/zombie.png");

	public RenderPetBuddy(RenderManager renderManager) {
		super(renderManager, new ModelBiped(0,0,64,64), 0.25f, 0.5f);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityPetBuddy entity) {
		if(entity.getOwner() != null && entity.getOwner() instanceof EntityPlayer)
			if(((EntityPlayer)entity.getOwner()) instanceof AbstractClientPlayer)
				return ((AbstractClientPlayer)entity.getOwner()).getLocationSkin();
		return ZOMBIE_TEXTURES;
	}

	@Override
	public Render createRenderFor(RenderManager manager) {
		return this;
	}
	
	@Override
	public void doRender(EntityPetBuddy entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}
	
	@Override
	protected void preRenderCallback(EntityPetBuddy entitylivingbaseIn, float partialTickTime) {
		float scale = 0.35f;
		GlStateManager.scale(scale,scale,scale);
		super.preRenderCallback(entitylivingbaseIn, partialTickTime);
	}
}
