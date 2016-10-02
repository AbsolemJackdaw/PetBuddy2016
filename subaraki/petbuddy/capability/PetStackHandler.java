package subaraki.petbuddy.capability;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class PetStackHandler extends ItemStackHandler{

	public static final int SLOTS = 15;
	public PetStackHandler() {
		super(new ItemStack[SLOTS]);
	}

	public ItemStack[] getStacks(){
		return stacks;
	}
	
}
