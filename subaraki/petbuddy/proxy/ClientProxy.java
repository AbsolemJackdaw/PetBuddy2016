package subaraki.petbuddy.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import subaraki.petbuddy.entity.EntityPetBuddy;
import subaraki.petbuddy.entity.RenderPetBuddy;
import subaraki.petbuddy.mod.PetBuddy;

public class ClientProxy extends ServerProxy {

	@Override
	public void registerRenders() {
		RenderingRegistry.registerEntityRenderingHandler(EntityPetBuddy.class, RenderPetBuddy::new);
		
		lib.item.ItemRegistry.registerVanillaRender(PetBuddy.coolDownItem, "stick", 0);
	}
	
	@Override
	public World getClientWorld() {
		return Minecraft.getMinecraft().theWorld;
	}
	
	@Override
	public EntityPlayer getClientPlayer() {
		return Minecraft.getMinecraft().thePlayer;
	}
}
