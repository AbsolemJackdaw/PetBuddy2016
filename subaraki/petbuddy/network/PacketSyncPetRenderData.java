package subaraki.petbuddy.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import subaraki.petbuddy.capability.PetInventory;
import subaraki.petbuddy.capability.PetInventoryCapability;
import subaraki.petbuddy.entity.EntityPetBuddy;

public class PacketSyncPetRenderData implements IMessage {

	public PacketSyncPetRenderData() {
	}

	public String type = "default";

	public PacketSyncPetRenderData(String type) {
		this.type = type;
	}

	public PacketSyncPetRenderData(boolean force) {
		this.type = "skip";
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		type = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, type);
	}

	public static class PacketSyncPetRenderDataHandler implements IMessageHandler<PacketSyncPetRenderData, IMessage> {

		@Override
		public IMessage onMessage(PacketSyncPetRenderData message, MessageContext ctx) {
			((WorldServer) ctx.getServerHandler().playerEntity.world).addScheduledTask(() -> {

				EntityPlayer player = ctx.getServerHandler().playerEntity;
				PetInventory inventory = player.getCapability(PetInventoryCapability.CAPABILITY, null);
				inventory.setPetmodeltype(message.type);

				Entity e = player.world.getEntityByID(inventory.getPetID());
				if (e != null && e instanceof EntityPetBuddy) {
					if (!"skip".equals(message.type)) {
						((EntityPetBuddy) e).setModelType(message.type);
					} else {
						((EntityPetBuddy) e).setForceRender(false);
					}
				}

			});
			return null;
		}
	}

}
