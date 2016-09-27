package subaraki.petbuddy.gui.server;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import subaraki.petbuddy.capability.PetInventory;

public class SlotPetChest extends SlotItemHandler{

	public SlotPetChest(PetInventory inventory, int index, int posX, int posY) {
		super(inventory.getInventoryHandler(), index, posX, posY);
	}
}
