package subaraki.petbuddy.hooks;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import subaraki.petbuddy.capability.PetInventory;
import subaraki.petbuddy.entity.EntityPetBuddy;
import subaraki.petbuddy.network.NetworkHandler;
import subaraki.petbuddy.network.PacketSyncOwnInventory;

public class StowOrSummonLogic {

	public static void checkPet(EntityPlayer player){
		//no point in checking
		//pets despawn when their id doesn't match the owner's saved data, and it's always different wher reloading a world
		
		//TODO check configuration wether to spawn petbuddy on login or not
	}

	public static void givePet(EntityPlayer player){
		EntityPetBuddy pet = new EntityPetBuddy(player.worldObj);
		PetInventory inventory = PetInventory.get(player);	

		pet.setOwnerId(player.getUniqueID());
		pet.setTamed(true);
		pet.setLocationAndAngles(player.posX, player.posY, player.posZ, player.getRotationYawHead(), player.rotationPitch);
		pet.setItemStackToSlot(EntityEquipmentSlot.HEAD, inventory.getInventoryHandler().getStackInSlot(12));
		pet.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, inventory.getInventoryHandler().getStackInSlot(13));

		pet.setModelType(inventory.getPetmodeltype());
		
		ItemStack stack = inventory.getInventoryHandler().getStackInSlot(14);
		String tagName = stack != null && stack.hasDisplayName() && Items.NAME_TAG.equals(stack.getItem()) ? stack.getDisplayName() : "";
		pet.setNameForNameTag(tagName);
		pet.setForceRender(true);

		if(inventory.getPetName() != null && inventory.getPetName().length() > 1)
			pet.setCustomNameTag(inventory.getPetName());
		else
			pet.setCustomNameTag(player.getName());

		inventory.setPetID(pet.getEntityId());

		pet.setHealth(inventory.getPetHealth() > 0 ? inventory.getPetHealth() : pet.getMaxHealth()); //should default to 30 if no health is saved

		if(!player.worldObj.isRemote)
			player.worldObj.spawnEntityInWorld(pet);

		if(!player.worldObj.isRemote)//sync up entity id data
			NetworkHandler.NETWORK.sendTo(new PacketSyncOwnInventory(player), (EntityPlayerMP)player);

	}
}
