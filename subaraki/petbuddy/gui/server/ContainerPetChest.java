package subaraki.petbuddy.gui.server;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import subaraki.petbuddy.capability.PetInventory;
import subaraki.petbuddy.capability.PetInventoryCapability;
import subaraki.petbuddy.entity.EntityPetBuddy;
import subaraki.petbuddy.entity.PetForm;

public class ContainerPetChest extends Container{

	public ContainerPetChest(EntityPlayer player) {
		PetInventory inventory = player.getCapability(PetInventoryCapability.CAPABILITY, null);

		int index = 0;
		for(int y = 0; y < 3; y++)
			for(int x = 0; x < 4; x++)
				addSlotToContainer(new SlotPetChest(inventory, index++, 8+ x*18, 16 + y*18));

		for(int y = 0; y < 3; y++)
			addSlotToContainer(new SlotPetChest.Armory(inventory, index++, 100, 16 + y*18));

		for (int row = 0; row < 9; ++row) {
			this.addSlotToContainer(new Slot(player.inventory, row,
					8 + (row * 18), 142));
		}
		// players inventory
		for (int y = 0; y < 3; ++y) {
			for (int x = 0; x < 9; ++x) {
				this.addSlotToContainer(new Slot(player.inventory,
						(x + ((y + 1) * 9)), 8 + (x * 18),
						84 + (y * 18)));
			}
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = null;
		Slot slot = (Slot)this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (index < 12)
			{
				if (!this.mergeItemStack(itemstack1, 12, this.inventorySlots.size(), true))
				{
					return null;
				}
			}
			else if (!this.mergeItemStack(itemstack1, 0, 12, false))
			{
				return null;
			}

			if (itemstack1.stackSize == 0)
			{
				slot.putStack((ItemStack)null);
			}
			else
			{
				slot.onSlotChanged();
			}
		}

		return itemstack;
	}

	@Override
	public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player) {

		ItemStack clicked = super.slotClick(slotId, dragType, clickTypeIn, player);
		
		if(slotId == 14){
			ItemStack stack = getSlot(slotId).getStack();
			EntityPetBuddy e = PetInventory.get(player).getPet(player);
			if(e != null && stack != null){
				if(stack.getItem().equals(Items.RABBIT_FOOT))
					e.setIndex(PetForm.TEXTURE_RABBIT.length);
				else if(stack.getItem().equals(Items.ARROW))
					e.setIndex(PetForm.TEXTURE_SKELETON.length);
				else if(stack.getItem().equals(Items.ROTTEN_FLESH))
					e.setIndex(PetForm.TEXTURE_ZOMBIE.length);
				else if (stack.getItem().equals(Items.BOOK))
					e.setIndex(PetForm.TEXTURE_VILLAGER.length);
				else if (stack.getItem().equals(Items.COOKED_FISH))
					e.setIndex(PetForm.TEXTURE_CATE.length);
					
				else{
					e.setIndex(0);
				}
			}
		}
		return clicked;
	}
}
