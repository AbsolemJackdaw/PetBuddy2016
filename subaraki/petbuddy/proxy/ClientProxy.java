package subaraki.petbuddy.proxy;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import subaraki.petbuddy.entity.EntityPetBuddy;
import subaraki.petbuddy.entity.RenderPetBuddy;

public class ClientProxy extends ServerProxy {

	@Override
	public void registerRenders() {
		RenderingRegistry.registerEntityRenderingHandler(EntityPetBuddy.class, RenderPetBuddy::new);
	}
}
