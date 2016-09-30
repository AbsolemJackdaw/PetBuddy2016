package subaraki.petbuddy.entity;

import java.util.HashMap;
import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import subaraki.petbuddy.capability.PetInventory;
import subaraki.petbuddy.capability.PetInventoryCapability;
import subaraki.petbuddy.hooks.RespawnBuddy;

public class PetBuddyRegistry {

	private static HashMap<UUID, Integer> buddyRegistry = new HashMap();

	public static EntityPetBuddy getBuddyFromPlayer(EntityPlayer player){
		EntityPetBuddy pet = null;

		if(buddyRegistry.containsKey(player.getUniqueID())){
			Integer id = buddyRegistry.get(player.getUniqueID());
			if(id != null){
				Entity e = player.worldObj.getEntityByID(id);
				if(e instanceof EntityPetBuddy)
					pet = (EntityPetBuddy)e;
			}
		}

		return pet;
	}

	public static void onBuddyDeath(EntityPlayer player){
		if(buddyRegistry.containsKey(player.getUniqueID())){
			player.getCooldownTracker().setCooldown(RespawnBuddy.coolDown, 10+player.worldObj.rand.nextInt(10));
			buddyRegistry.put(player.getUniqueID(), (Integer)null);
		}
	}

	public static void onLogin(EntityPlayer player){
		//when logging in, the player is new to the world.
		//make a new pet, spawn it, and add it to the map, bound to the player.

		if(buddyRegistry.containsKey(player.getUniqueID()))
			if(buddyRegistry.get(player.getUniqueID())!=null)
				if(player.worldObj.getEntityByID(buddyRegistry.get(player.getUniqueID())) != null)
					if(player.worldObj.getEntityByID(buddyRegistry.get(player.getUniqueID())) instanceof EntityPetBuddy)
						return;

		EntityPetBuddy pet = new EntityPetBuddy(player.worldObj);
		PetInventory inventory = player.getCapability(PetInventoryCapability.CAPABILITY, null);	

		pet.setOwnerId(player.getUniqueID());
		pet.setTamed(true);
		pet.setLocationAndAngles(player.posX, player.posY, player.posZ, player.getRotationYawHead(), player.rotationPitch);
		pet.setItemStackToSlot(EntityEquipmentSlot.HEAD, inventory.getInventoryHandler().getStackInSlot(12));
		pet.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, inventory.getInventoryHandler().getStackInSlot(13));

		if(inventory.getPetName() != null && inventory.getPetName().length() > 1)
			pet.setCustomNameTag(inventory.getPetName());
		else
			pet.setCustomNameTag(player.getName());

		if(!player.worldObj.isRemote)
			player.worldObj.spawnEntityInWorld(pet);

		buddyRegistry.put(player.getUniqueID(), pet.getEntityId());
	}

	public static void onLoggedOut(EntityPlayer player){
		//when logging out, you quit the game.
		//check if the pet is alive in the same dimension, then kill it.
		//remove player from registry.

		if(buddyRegistry.containsKey(player.getUniqueID())){
			//cannot set an entity dead on loggin out
			EntityTameable e = (EntityTameable) player.worldObj.getEntityByID(buddyRegistry.get(player.getUniqueID()));
			if(e != null)
				e.setHealth(-1);
			buddyRegistry.remove(player.getUniqueID());
		}
	}

	public static void onPlayerChangedDimension(EntityPlayer player){
		//when changing dimensions, the pet in the old dimension will set itself dead.
		//remove the player's name from the regitry temporarily to remove the entity bound to it,
		//and respawn entity at player, and map both again
		if(buddyRegistry.containsKey(player.getUniqueID())){
			buddyRegistry.remove(player.getUniqueID());
			onLogin(player);
		}
	}
}
