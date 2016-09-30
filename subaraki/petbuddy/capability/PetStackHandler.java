package subaraki.petbuddy.capability;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.items.ItemStackHandler;
import subaraki.petbuddy.entity.EntityPetBuddy;
import subaraki.petbuddy.entity.PetBuddyRegistry;

public class PetStackHandler extends ItemStackHandler{

	public static final int SLOTS = 15;
	public PetStackHandler() {
		super(new ItemStack[SLOTS]);
	}

	public ItemStack[] getStacks(){
		return stacks;
	}
	
}
