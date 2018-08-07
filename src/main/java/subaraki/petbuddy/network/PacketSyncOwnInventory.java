package subaraki.petbuddy.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import subaraki.petbuddy.capability.PetInventory;
import subaraki.petbuddy.capability.PetInventoryCapability;
import subaraki.petbuddy.entity.EntityPetBuddy;
import subaraki.petbuddy.mod.PetBuddy;

public class PacketSyncOwnInventory implements IMessage {

	public ItemStack stack[] = new ItemStack[3];
	public String petid;
	public int skinIndex;
	public boolean [] upgrades = new boolean [2];

	public PacketSyncOwnInventory() {
	}

	public PacketSyncOwnInventory(EntityPlayer player) {
		PetInventory data = player.getCapability(PetInventoryCapability.CAPABILITY, null);

		petid = data.getPetID() == null ? "null" : Integer.toString(data.getPetID());
		skinIndex = data.getSkinIndex();
		upgrades[0] = data.getHealthUpgrade_1();
		upgrades[1] = data.getHealthUpgrade_2();
		
		for(int i = 0; i < stack.length; i ++)
			stack[i] = data.getInventoryHandler().getStackInSlot(12+i);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		petid = ByteBufUtils.readUTF8String(buf);
		skinIndex = buf.readInt();
		for (int i = 0; i < stack.length; i++){
			stack[i] = ByteBufUtils.readItemStack(buf);
		}
		upgrades = new boolean[] {buf.readBoolean(), buf.readBoolean()};
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, petid);
		buf.writeInt(skinIndex);
		for (int i = 0; i < stack.length; i++) {
			ByteBufUtils.writeItemStack(buf, stack[i]);
		}
		buf.writeBoolean(upgrades[0]);
		buf.writeBoolean(upgrades[1]);
	}

	public static class PacketSyncOwnInventoryHandler implements IMessageHandler<PacketSyncOwnInventory, IMessage>{

		@Override
		public IMessage onMessage(PacketSyncOwnInventory message,MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
				EntityPlayer player = PetBuddy.proxy.getClientPlayer();

				if(player == null)
					return;

				PetInventory data = player.getCapability(PetInventoryCapability.CAPABILITY, null);

				data.setSkinIndex(message.skinIndex);
				
				String id = message.petid;
				if(id.equals("null"))
					data.setPetID(null);
				else{
					data.setPetID(Integer.parseInt(id));
					Entity e = player.world.getEntityByID(Integer.parseInt(id));

					if(e instanceof EntityPetBuddy){
						((EntityPetBuddy)e).forceIndex(data.getSkinIndex());
					}
				}
				for (int i = 0; i < message.stack.length; i++){
					data.setStackInSlot(12+i,message.stack[i]);
				}
				
				if(message.upgrades[0])
					data.upgradeHealth_1();
				if(message.upgrades[1])
					data.upgradeHealth_2();
			});
			return null;
		}
	}


}
