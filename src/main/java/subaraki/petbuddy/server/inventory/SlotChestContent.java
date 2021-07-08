package subaraki.petbuddy.server.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotChestContent extends SlotItemHandler {

    PetBuddyContainer container;

    public SlotChestContent(PetBuddyContainer container, IItemHandler itemHandler, int index, int xPosition, int yPosition) {

        super(itemHandler, index, xPosition, yPosition);
        this.container = container;
    }

    @Override
    public boolean mayPlace(ItemStack stack)
    {

        return container.getSlot(3).hasItem();
    }

    @Override
    public boolean mayPickup(PlayerEntity playerIn)
    {

        return container.getSlot(3).hasItem();
    }

    @Override
    public boolean isActive()
    {

        return container.getSlot(3).hasItem();
    }

}
