package subaraki.petbuddy.server.inventory;

import net.minecraft.entity.MobEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotHelmet extends SlotItemHandler {

    public SlotHelmet(IItemHandler itemHandler, int index, int xPosition, int yPosition) {

        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(ItemStack stack)
    {

        boolean isHelmet = stack.getItem() instanceof ArmorItem ;
        boolean isHead = MobEntity.getEquipmentSlotForItem(stack).equals(EquipmentSlotType.HEAD);
        
        return isHelmet | isHead;
    }

}
