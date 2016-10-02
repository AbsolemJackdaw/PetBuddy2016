package subaraki.petbuddy.hooks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.StartTracking;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import subaraki.petbuddy.capability.PetInventory;
import subaraki.petbuddy.capability.PetInventoryCapability;
import subaraki.petbuddy.entity.EntityPetBuddy;

public class PetDeathTracker {

	public PetDeathTracker() {
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	public void onPlayerEvent(LivingUpdateEvent event){
		if(event.getEntityLiving() instanceof EntityPlayer)
			((EntityPlayer)(event.getEntityLiving())).getCapability(PetInventoryCapability.CAPABILITY, null).updateCooldown();
	}
}
