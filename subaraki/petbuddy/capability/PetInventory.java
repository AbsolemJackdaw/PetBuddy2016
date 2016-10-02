package subaraki.petbuddy.capability;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.items.ItemStackHandler;
import subaraki.petbuddy.entity.EntityPetBuddy;

public class PetInventory {

	private PetStackHandler inventory;
	private EntityPlayer player;
	private String petname = null;
	private Integer currentPetID;
	private int cooldown = 0;

	private boolean holdsChest = false;

	public PetInventory(){
		inventory = new PetStackHandler();
	}

	public EntityPlayer getPlayer() { 
		return player; 
	}

	public void setPlayer(EntityPlayer newPlayer){
		this.player = newPlayer;
	}

	/**
	 * This method is an ease of access to an instance of this capability for given player
	 */
	public static PetInventory get(EntityPlayer player){
		if(player != null)
			return (PetInventory) player.getCapability(PetInventoryCapability.CAPABILITY, null);
		return null;
	}
	/*
	 * Internal method used by IStorage in the capability
	 */
	public ItemStackHandler getInventoryHandler(){return inventory;}

	///////////////Saving data//////////////////////////////////////////////////////////////////////////
	public NBTBase writeData(){
		//hook into the tagcompound of the ItemStackHandler
		NBTTagCompound tag = inventory.serializeNBT();
		//add our own tags
		tag.setBoolean("chest", canAccesStorage());
		tag.setString("petname", getPetName());
		if(getPetID() != null)
			tag.setInteger("petid", getPetID());
		tag.setInteger("cooldown", getCooldown());
		//save mix of itemstacks and personal tags
		return tag;
	}

	public void readData(NBTBase nbt){
		NBTTagCompound tag = ((NBTTagCompound)nbt);
		inventory.deserializeNBT((NBTTagCompound)nbt);
		holdsChest = tag.getBoolean("chest");
		setPetName(tag.getString("petname"));
		setPetID(tag.hasKey("petid") ? tag.getInteger("petid") : null);
		setCooldown(tag.getInteger("cooldown"));
	}

	/////////////////Getters and Setters////////////////////////////////////////////////////////////////////////
	public void setHoldingChest(){
		holdsChest = true;
	}

	public boolean canAccesStorage(){
		return holdsChest;
	}

	public String getPetName(){
		return petname;
	}

	public void setPetName(String name){
		petname = name;
	}

	public Integer getPetID(){
		return currentPetID;
	}

	public void setPetID(Integer id){
		currentPetID = id;
	}

	/**can be null*/
	public EntityPetBuddy getPet(EntityPlayer player){
		if(player != null)
			if(getPetID() != null)
				return (EntityPetBuddy)player.worldObj.getEntityByID(getPetID());
		return null;
	}

	public void updateCooldown(){
		if(hasCooldown())
			cooldown--;
	}

	public boolean hasCooldown(){
		return cooldown > 0;
	}

	public int getCooldown(){
		return cooldown;
	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}
}
