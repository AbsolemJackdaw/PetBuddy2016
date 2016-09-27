package subaraki.petbuddy.proxy;

import net.minecraftforge.fml.common.registry.EntityRegistry;
import subaraki.petbuddy.entity.EntityPetBuddy;
import subaraki.petbuddy.mod.PetBuddy;

public class ServerProxy {

	public void registerEntities(){
		EntityRegistry.registerModEntity(EntityPetBuddy.class, "pet_buddy", 0, PetBuddy.MODID, 128, 250, false);
		
	}
	public void registerRenders(){};
}
