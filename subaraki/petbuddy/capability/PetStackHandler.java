package subaraki.petbuddy.capability;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.ItemStackHandler;

public class PetStackHandler extends ItemStackHandler {

	public static final int SLOTS = 15;

	public PetStackHandler() {
		super(SLOTS);
	}

	public NonNullList<ItemStack> getStacks() {
		return stacks;
	}
}
