package subaraki.petbuddy.server.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.items.ItemStackHandler;
import subaraki.petbuddy.api.IChangeableForm;
import subaraki.petbuddy.capability.BuddyData;
import subaraki.petbuddy.network.NetworkHandler;
import subaraki.petbuddy.network.client.CPacketSyncArmorSlots;
import subaraki.petbuddy.server.entity.PetBuddyEntity;

public class PetBuddyInventory extends ItemStackHandler {

    PetBuddyEntity buddy = null;

    public PetBuddyInventory(PetBuddyEntity entity) {

        super(16);
        buddy = entity;
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate)
    {

        if (slot == 2)
            if (buddy != null && buddy.level.isClientSide())
            {

                PlayerEntity owner = buddy.level.getPlayerByUUID(buddy.getOwnerUUID());

                if (owner != null)

                    BuddyData.get(owner).ifPresent((data) -> {
                        data.setPetForm(stack);

                        if (buddy.getPetForm() instanceof IChangeableForm)
                            ((IChangeableForm) buddy.getPetForm()).onSlotInsert();
                    });
            }

        return super.insertItem(slot, stack, simulate);
    }

    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate)
    {

        if (slot == 2)
            if (buddy != null && buddy.level.isClientSide())
            {

                PlayerEntity owner = buddy.level.getPlayerByUUID(buddy.getOwnerUUID());

                if (owner != null)

                    BuddyData.get(owner).ifPresent((data) -> {
                        data.setPetForm(ItemStack.EMPTY);
                    });
            }
        return super.extractItem(slot, amount, simulate);
    }

    @Override
    protected void onContentsChanged(int slot)
    {

        super.onContentsChanged(slot);

        if (slot < 3)
            if (buddy != null)
            {
                if (!buddy.level.isClientSide())
                {

                    PlayerEntity owner = buddy.level.getPlayerByUUID(buddy.getOwnerUUID());
                    ItemStack equipment = getStackInSlot(slot);

                    if (owner != null)
                    {

                        BuddyData.get(owner).ifPresent((data) -> {

                            data.reinstateGoals(slot);
                            NetworkHandler.NETWORK.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) owner),
                                    new CPacketSyncArmorSlots(equipment, slot, buddy.getId()));
                        });
                    }
                }
            }

    }

    @Override
    public int getSlotLimit(int slot)
    {

        if (slot < 4)
            return 1;

        return 64;
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack)
    {

        return true;
    }
}
