package subaraki.petbuddy.server.inventory;

import net.minecraft.block.BarrelBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ChestBlock;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.IContainerProvider;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import subaraki.petbuddy.capability.BuddyData;
import subaraki.petbuddy.mod.PetBuddy;

public class PetBuddyContainer extends Container {

    /********************** FACTORIES **********************/

    /** Container factory for opening the container clientside **/
    public static PetBuddyContainer registerClientContainer(int id, PlayerInventory playerInventory)
    {

        return new PetBuddyContainer(id, playerInventory);
    }

    /** Get the server container provider for NetworkHooks.openGui */
    public static IContainerProvider getServerContainerProvider()
    {

        return (id, playerInventory, serverPlayer) -> new PetBuddyContainer(id, playerInventory);
    }

    /*******************************************************/

    public static final ITextComponent TITLE = new TranslationTextComponent("container.petbuddy.buddy_container");

    PlayerEntity player = null;

    public PetBuddyContainer(int id, PlayerInventory playerInventory) {

        super(PetBuddy.PETBUDDY_CONTAINER_TYPE.get(), id);

        player = playerInventory.player;

        BuddyData.get(player).ifPresent((data) -> {

            this.addSlot(new SlotHelmet(data.getPetInventory(), 0, 100, 16));
            this.addSlot(new SlotSword(data.getPetInventory(), 1, 100, 16 + 18));

            this.addSlot(new SlotForm(data.getPetInventory(), 2, 100, 16 + (2 * 18)));
            this.addSlot(new SlotChest(data.getPetInventory(), 3, 100 - 20, 16));

            for (int x = 0; x < 4; x++)
            {
                for (int y = 0; y < 3; y++)
                {
                    this.addSlot(new SlotChestContent(this, data.getPetInventory(), 4 + (x * 3 + y), 8 + (x * 18), 16 + (y * 18)));
                }
            }
        });

        for (int x = 0; x < 9; ++x)
        {
            this.addSlot(new Slot(playerInventory, x, 8 + x * 18, 142));
        }

        for (int y = 0; y < 3; ++y)
        {
            for (int x = 0; x < 9; ++x)
            {
                this.addSlot(new Slot(playerInventory, x + (y + 1) * 9, 8 + x * 18, 84 + y * 18));
            }
        }

    }

    @Override
    public boolean stillValid(PlayerEntity player)
    {

        return true;
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity player, int slotnumber)
    {

        // Shift clicked the players inventory
        int indexPlayerInventory = slotnumber - 16; // 16 is size of custom inv
        if ((indexPlayerInventory) >= 0)
        {
            ItemStack stack_temp = player.inventory.getItem(indexPlayerInventory);

            if (MobEntity.isValidSlotForItem(EquipmentSlotType.HEAD, stack_temp))
            {
                if (!getSlot(0).getItem().isEmpty())
                    return toChest(slotnumber);
                player.inventory.setCarried(player.inventory.getItem(indexPlayerInventory));
                player.inventory.setItem(indexPlayerInventory, ItemStack.EMPTY);
                this.clicked(0, 0, ClickType.PICKUP, player);
            }

            else
                if (stack_temp.getAttributeModifiers(EquipmentSlotType.MAINHAND).containsKey(Attributes.ATTACK_DAMAGE))
                {
                    if (!getSlot(1).getItem().isEmpty())
                        return toChest(slotnumber);
                    player.inventory.setCarried(player.inventory.getItem(indexPlayerInventory));
                    player.inventory.setItem(indexPlayerInventory, ItemStack.EMPTY);
                    this.clicked(1, 0, ClickType.PICKUP, player);
                }
                else
                    if (Block.byItem(stack_temp.getItem()) instanceof ChestBlock | Block.byItem(stack_temp.getItem()) instanceof BarrelBlock)
                    {
                        if (!getSlot(3).getItem().isEmpty())
                            return toChest(slotnumber);
                        player.inventory.setCarried(player.inventory.getItem(indexPlayerInventory));
                        player.inventory.setItem(indexPlayerInventory, ItemStack.EMPTY);
                        this.clicked(3, 0, ClickType.PICKUP, player);
                    }
                    else
                        return toChest(slotnumber);

        }
        else
        {
            int the_slot = 0;
            for (ItemStack stack : player.inventory.items)
            {
                if (stack.isEmpty())
                {
                    player.inventory.setItem(the_slot, getSlot(slotnumber).getItem());
                    getSlot(slotnumber).set(ItemStack.EMPTY);
                    return ItemStack.EMPTY;
                }
                the_slot++;
            }
        }
        return ItemStack.EMPTY;
    }

    private ItemStack toChest(int givenSlot)
    {

        if (getSlot(4).isActive())

            if (givenSlot >= 16 && givenSlot < 52)
                if (!this.moveItemStackTo(getSlot(givenSlot).getItem(), 4, 16, false))
                    return ItemStack.EMPTY;

        return ItemStack.EMPTY;
    }
}
