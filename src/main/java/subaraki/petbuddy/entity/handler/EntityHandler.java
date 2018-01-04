package subaraki.petbuddy.entity.handler;

import java.util.ArrayList;

import lib.entity.EntityRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import subaraki.petbuddy.entity.EntityPetBuddy;
import subaraki.petbuddy.mod.PetBuddy;

public class EntityHandler {

	public EntityHandler() {
		MinecraftForge.EVENT_BUS.register(this);
		init();
	}

	ArrayList<EntityEntryBuilder> entities = new ArrayList<EntityEntryBuilder>();

	private void init()
	{
		EntityRegistry er = new EntityRegistry();

		EntityEntryBuilder petbuddy = er.registerEntity(EntityPetBuddy.class, EntityPetBuddy::new, PetBuddy.MODID, "pet_buddy", 0, 256, 4, true);

		entities.add(petbuddy);
	}

	@SubscribeEvent
	public void registerEntities(RegistryEvent.Register<EntityEntry> e){

		for(EntityEntryBuilder eeb : entities)
			e.getRegistry().register(eeb.build());
	}
}
