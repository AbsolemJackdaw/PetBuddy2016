package subaraki.petbuddy.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import subaraki.petbuddy.capability.PetInventory;
import subaraki.petbuddy.capability.PetInventoryCapability;
import subaraki.petbuddy.mod.PetBuddy;

/**
 * This packet is viewed by another player,
 * who is getting your data to know what you have, so otherUser is you
 */
public class PacketSyncOtherInventory implements IMessage {


	public int otherUser;
	public ItemStack stack[] = new ItemStack[3];

	public PacketSyncOtherInventory() {
		//default constructor is needed else the game crashes
	}

	public PacketSyncOtherInventory(EntityPlayer player) {
		PetInventory inv = player.getCapability(PetInventoryCapability.CAPABILITY, null);

		otherUser = player.getEntityId();

		for (int i = 0; i < stack.length; i++){
			stack[i] = inv.getInventoryHandler().getStackInSlot(12+i);
		}
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		otherUser = buf.readInt();

		for (int i = 0; i < stack.length; i++)
			stack[i] = ByteBufUtils.readItemStack(buf);

	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(otherUser);

		for (int i = 0; i < stack.length; i++)
			ByteBufUtils.writeItemStack(buf, stack[i]);


	}

	public static class PacketSyncOtherInventoryHandler implements IMessageHandler<PacketSyncOtherInventory, IMessage>{

		@Override
		public IMessage onMessage(PacketSyncOtherInventory message,MessageContext ctx) {

			Minecraft.getMinecraft().addScheduledTask(() -> {
				EntityPlayer other = (EntityPlayer)PetBuddy.proxy.getClientWorld().getEntityByID(message.otherUser);

				if(other != null){
					PetInventory inv = other.getCapability(PetInventoryCapability.CAPABILITY, null);
					if(inv == null)
						FMLLog.getLogger().info("packet info. 'inventory' was null. dropping packet");
					else{
						for (int i = 0; i < message.stack.length; i++)
							inv.getInventoryHandler().setStackInSlot(12+i, message.stack[i]);
					}
				}else
					FMLLog.getLogger().info("packet info. 'other' was null. dropping packet");
			});
			return null;
		}
	}
}
