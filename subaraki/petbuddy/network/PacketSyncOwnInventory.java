package subaraki.petbuddy.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import subaraki.petbuddy.capability.PetInventory;
import subaraki.petbuddy.capability.PetInventoryCapability;
import subaraki.petbuddy.mod.PetBuddy;

public class PacketSyncOwnInventory implements IMessage {

	public ItemStack stack[] = new ItemStack[3];
	public String petid;

	public PacketSyncOwnInventory() {
	}

	public PacketSyncOwnInventory(EntityPlayer player) {
		PetInventory inv = player.getCapability(PetInventoryCapability.CAPABILITY, null);

		petid = inv.getPetID() == null ? "null" : Integer.toString(inv.getPetID());

		for(int i = 0; i < stack.length; i ++)
			stack[i] = inv.getInventoryHandler().getStackInSlot(12+i);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		petid = ByteBufUtils.readUTF8String(buf);
		for (int i = 0; i < stack.length; i++){
			stack[i] = ByteBufUtils.readItemStack(buf);
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, petid);
		for (int i = 0; i < stack.length; i++) {
			ByteBufUtils.writeItemStack(buf, stack[i]);
		}
	}

	public static class PacketSyncOwnInventoryHandler implements IMessageHandler<PacketSyncOwnInventory, IMessage>{

		@Override
		public IMessage onMessage(PacketSyncOwnInventory message,MessageContext ctx) {
			EntityPlayer player = PetBuddy.proxy.getClientPlayer();

			if(player == null)
				return null;

			PetInventory inv = player.getCapability(PetInventoryCapability.CAPABILITY, null);

			String id = message.petid;
			if(id.equals("null"))
				inv.setPetID(null);
			else
				inv.setPetID(Integer.parseInt(id));

			for (int i = 0; i < message.stack.length; i++){
				inv.getInventoryHandler().setStackInSlot(12+i,message.stack[i]);
			}
			return null;
		}
	}


}
