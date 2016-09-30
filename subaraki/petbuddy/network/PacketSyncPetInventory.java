package subaraki.petbuddy.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import subaraki.petbuddy.entity.EntityPetBuddy;

public class PacketSyncPetInventory implements IMessage{

	public int petID;
	public ItemStack helm;
	public ItemStack sword;
	public ItemStack form;

	public PacketSyncPetInventory() {
	}	

	public PacketSyncPetInventory(int entityId, ItemStack helm, ItemStack sword, ItemStack form) {
		petID = entityId;
		this.helm = helm;
		this.sword = sword;
		this.form = form;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		petID = buf.readInt();
		helm = ByteBufUtils.readItemStack(buf);
		sword = ByteBufUtils.readItemStack(buf);
		form = ByteBufUtils.readItemStack(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(petID);
		ByteBufUtils.writeItemStack(buf, helm);
		ByteBufUtils.writeItemStack(buf, sword);
		ByteBufUtils.writeItemStack(buf, form);
	}

	public static class PacketSyncPetInventoryHandler implements IMessageHandler<PacketSyncPetInventory, IMessage>{

		@Override
		public IMessage onMessage(PacketSyncPetInventory message, MessageContext ctx) {
			Entity e = ctx.getServerHandler().playerEntity.worldObj.getEntityByID(message.petID);
			EntityPetBuddy epb = null;
			if(e instanceof EntityPetBuddy)
				epb = (EntityPetBuddy)e;
			
			if(epb != null){
				epb.setHeldItem(EnumHand.MAIN_HAND, message.sword);
				epb.setItemStackToSlot(EntityEquipmentSlot.HEAD, message.helm);
			}
				return null;
		}
	}

}
