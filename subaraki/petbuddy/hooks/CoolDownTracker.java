package subaraki.petbuddy.hooks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import subaraki.petbuddy.capability.PetInventoryCapability;

public class CoolDownTracker {

	public CoolDownTracker() {
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	public void onPlayerEvent(LivingUpdateEvent event){
		if(event.getEntityLiving() instanceof EntityPlayer)
			((EntityPlayer)(event.getEntityLiving())).getCapability(PetInventoryCapability.CAPABILITY, null).updateCooldown();
	}
}
