package subaraki.petbuddy.server.inventory;

import net.minecraft.block.BarrelBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ChestBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotChest extends SlotItemHandler {

    public SlotChest(IItemHandler itemHandler, int index, int xPosition, int yPosition) {

        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(ItemStack stack)
    {

        boolean isChest = Block.byItem(stack.getItem()) instanceof ChestBlock;
        boolean isBarrel = Block.byItem(stack.getItem()) instanceof BarrelBlock;
        return isChest | isBarrel;
    }

    @Override
    public boolean mayPickup(PlayerEntity playerIn)
    {

        return true;
    }
}
