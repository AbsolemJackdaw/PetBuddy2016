package subaraki.petbuddy.server.inventory;

import net.minecraft.item.ItemStack;
import net.minecraft.item.NameTagItem;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotForm extends SlotItemHandler {

    public SlotForm(IItemHandler itemHandler, int index, int xPosition, int yPosition) {

        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(ItemStack stack)
    {
    
        return stack.getItem() instanceof NameTagItem;
    }
    
}
