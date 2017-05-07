package subaraki.petbuddy.gui.server;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumHand;
import net.minecraftforge.items.SlotItemHandler;
import subaraki.petbuddy.capability.PetInventory;
import subaraki.petbuddy.entity.EntityPetBuddy;

public class SlotPetChest extends SlotItemHandler {
	private PetInventory petInventory = null;

	public SlotPetChest(PetInventory inventory, int index, int posX, int posY) {
		super(inventory.getInventoryHandler(), index, posX, posY);
		petInventory = inventory;
	}

	public PetInventory getPetInventory() {
		return petInventory;
	}

	public static class Armory extends SlotPetChest {

		private final int slotId;

		public Armory(PetInventory inventory, int index, int posX, int posY) {
			super(inventory, index, posX, posY);
			slotId = index;
		}

		@Override
		public void putStack(ItemStack stack) {
			super.putStack(stack);
		}

		@Override
		public void onSlotChanged() {
			super.onSlotChanged();

			EntityPetBuddy e = null;
			if (getPetInventory() != null) {
				EntityPlayer player = getPetInventory().getPlayer();
				e = PetInventory.get(player).getPet(player);
			}
			if (e == null)
				return;

			if (slotId == 12) {
				e.setItemStackToSlot(EntityEquipmentSlot.HEAD, getStack());// stack
																			// can
																			// be
																			// null.
																			// is
																			// intended
			}

			else if (slotId == 13) {
				e.setHeldItem(EnumHand.MAIN_HAND, getStack());// stack can be
																// null. is
																// intended
			}

			else if (slotId == 14) {
				String tagName = !getStack().isEmpty() && getStack().hasDisplayName()
						&& Items.NAME_TAG.equals(getStack().getItem()) ? getStack().getDisplayName() : "";
				e.setNameForNameTag(tagName);
			}
		}

		@Override
		public int getSlotStackLimit() {
			return 1;
		}

		@Override
		public boolean isItemValid(ItemStack stack) {
			if (stack.isEmpty())
				return false;
			switch (slotId) {
			case 12:
				if (!(stack.getItem() instanceof ItemArmor))
					return false;
				ItemArmor armor = (ItemArmor) stack.getItem();
				return armor.isValidArmor(stack, EntityEquipmentSlot.HEAD, null);//armor.getEquipmentSlot().equals(EntityEquipmentSlot.HEAD);
			case 13:
				if (!(stack.getItem() instanceof ItemSword))
					return false;
				return true;
			case 14:
				return true;

			}
			return super.isItemValid(stack);
		}
	}
}
