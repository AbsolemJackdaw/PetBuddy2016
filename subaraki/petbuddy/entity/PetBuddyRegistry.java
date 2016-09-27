package subaraki.petbuddy.entity;

import java.util.HashMap;
import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;

public class PetBuddyRegistry {

	private static HashMap<UUID, Integer> buddyRegistry = new HashMap();

	public static void onLogin(EntityPlayer player){
		//when logging in, the player is new to the world.
		//make a new pet, spawn it, and add it to the map, bound to the player.

		//		EntityWolf wolf = new EntityWolf(player.worldObj);
		//		wolf.setOwnerId(player.getUniqueID());
		//		wolf.setLocationAndAngles(player.posX, player.posY, player.posZ, player.getRotationYawHead(), player.rotationPitch);
		//		wolf.setCustomNameTag(player.getName());
		//		wolf.setTamed(true);
		//		if(!player.worldObj.isRemote)
		//			player.worldObj.spawnEntityInWorld(wolf);

		EntityPetBuddy pet = new EntityPetBuddy(player.worldObj);
		pet.setOwnerId(player.getUniqueID());
		pet.setLocationAndAngles(player.posX, player.posY, player.posZ, player.getRotationYawHead(), player.rotationPitch);
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
