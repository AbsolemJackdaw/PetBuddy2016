package subaraki.petbuddy.network.client;

import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent.Context;
import subaraki.petbuddy.capability.BuddyData;
import subaraki.petbuddy.client.ClientReferences;
import subaraki.petbuddy.network.IPacketBase;
import subaraki.petbuddy.network.NetworkHandler;

public class CPacketSyncArmorSlots implements IPacketBase {

    public CPacketSyncArmorSlots() {

    }

    private ItemStack equipment = ItemStack.EMPTY;
    private int slot = 0;
    private int entityID;

    public CPacketSyncArmorSlots(ItemStack equipment, int slot, int entityID) {

        this.equipment = equipment;
        this.slot = slot;
        this.entityID = entityID;
    }

    public CPacketSyncArmorSlots(PacketBuffer buf) {

        decode(buf);
    }

    @Override
    public void encode(PacketBuffer buf)
    {

        buf.writeItemStack(equipment, false);
        buf.writeInt(slot);
        buf.writeInt(entityID);
    }

    @Override
    public void decode(PacketBuffer buf)
    {

        equipment = buf.readItem();
        slot = buf.readInt();
        entityID = buf.readInt();
    }

    @Override
    public void handle(Supplier<Context> context)
    {

        PlayerEntity player = ClientReferences.getClientPlayer();
        if (player != null)
            BuddyData.get(player).ifPresent((data) -> {

                if (slot < 2)
                    data.setClientArmorSlot(equipment, slot, entityID);

                if (slot == 2)
                    data.setPetForm(equipment);

            });

        context.get().setPacketHandled(true);
    }

    @Override
    public void register(int id)
    {

        NetworkHandler.NETWORK.registerMessage(id, CPacketSyncArmorSlots.class, CPacketSyncArmorSlots::encode, CPacketSyncArmorSlots::new,
                CPacketSyncArmorSlots::handle);
    }

}
