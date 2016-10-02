package subaraki.petbuddy.hooks;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import subaraki.petbuddy.capability.PetInventory;
import subaraki.petbuddy.entity.EntityPetBuddy;
import subaraki.petbuddy.network.NetworkHandler;
import subaraki.petbuddy.network.PacketSyncOwnInventory;

public class StowOrSummonLogic {

	public static void checkPet(EntityPlayer player){
		if(player == null)
			return;
		if(player.worldObj == null)
			return;

		PetInventory inventory = PetInventory.get(player);

		if(inventory.getPetID() != null){//player has a registered pet id
			boolean hasPet = false;
			//check for any loaded pets
			for(Entity loadedEntity : player.worldObj.loadedEntityList)
				if(loadedEntity instanceof EntityPetBuddy){
					EntityPetBuddy epb = (EntityPetBuddy)loadedEntity;
					if(epb.getOwnerId() != null && epb.getOwnerId().equals(player.getUniqueID())){
						if(epb.getEntityId() == (int)inventory.getPetID())//if the entity is the one registered to the player, keep it.
							hasPet = true;
						//don't set pet dead here. it removes itself in his update method.
					}
				}
			if(!hasPet)//if no pets were found, give the player a new pet
				givePet(player);
		}else//if no id was registered, give the player a new pet
			givePet(player);
	}

	public static void givePet(EntityPlayer player){
		EntityPetBuddy pet = new EntityPetBuddy(player.worldObj);
		PetInventory inventory = PetInventory.get(player);	

		pet.setOwnerId(player.getUniqueID());
		pet.setTamed(true);
		pet.setLocationAndAngles(player.posX, player.posY, player.posZ, player.getRotationYawHead(), player.rotationPitch);
		pet.setItemStackToSlot(EntityEquipmentSlot.HEAD, inventory.getInventoryHandler().getStackInSlot(12));
		pet.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, inventory.getInventoryHandler().getStackInSlot(13));

		if(inventory.getPetName() != null && inventory.getPetName().length() > 1)
			pet.setCustomNameTag(inventory.getPetName());
		else
			pet.setCustomNameTag(player.getName());

		inventory.setPetID(pet.getEntityId());

		if(!player.worldObj.isRemote)//sync up entity id data
			NetworkHandler.NETWORK.sendTo(new PacketSyncOwnInventory(player), (EntityPlayerMP)player);

		if(!player.worldObj.isRemote)
			player.worldObj.spawnEntityInWorld(pet);
	}
}
