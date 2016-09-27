package subaraki.petbuddy.hooks;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.Clone;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import subaraki.petbuddy.capability.CapabilityInventoryProvider;
import subaraki.petbuddy.capability.PetInventory;
import subaraki.petbuddy.capability.PetInventoryCapability;
import subaraki.petbuddy.entity.PetBuddyRegistry;

public class PlayerTracker {

	public PlayerTracker() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onPlayerLogin(PlayerLoggedInEvent event){
		if(!event.player.worldObj.isRemote)
			PetBuddyRegistry.onLogin(event.player);
	}

	@SubscribeEvent
	public void onPlayerLoggedOut(PlayerLoggedOutEvent event){
		if(!event.player.worldObj.isRemote)
			PetBuddyRegistry.onLoggedOut(event.player);
	}

	@SubscribeEvent
	public void onPlayerChangedDimension(PlayerChangedDimensionEvent event){
		if(!event.player.worldObj.isRemote)
			PetBuddyRegistry.onPlayerChangedDimension(event.player);
	}

	@SubscribeEvent
	public void onPlayerClone(Clone event){
		//pet buddy inventory should never wither.
		PetInventory inventory = event.getEntityPlayer().getCapability(PetInventoryCapability.CAPABILITY, null);
		PetInventory inventory_original = event.getOriginal().getCapability(PetInventoryCapability.CAPABILITY, null);
		NBTTagCompound tag = (NBTTagCompound) inventory.writeData();
		inventory_original.readData(tag);
	}

	@SubscribeEvent
	public void onCapabilityAttach(AttachCapabilitiesEvent.Entity event){
		final Entity entity = event.getEntity();

		if (entity instanceof EntityPlayer)
			event.addCapability(CapabilityInventoryProvider.KEY, new CapabilityInventoryProvider((EntityPlayer)entity)); 
	}
}