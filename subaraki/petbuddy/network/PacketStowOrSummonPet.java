package subaraki.petbuddy.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import subaraki.petbuddy.capability.PetInventory;
import subaraki.petbuddy.entity.EntityPetBuddy;
import subaraki.petbuddy.hooks.StowOrSummonLogic;

public class PacketStowOrSummonPet implements IMessage{

	public PacketStowOrSummonPet() {
	}

	@Override
	public void fromBytes(ByteBuf buf) {
	}

	@Override
	public void toBytes(ByteBuf buf) {

	}

	public static class PacketStowOrSummonPetHandler implements IMessageHandler<PacketStowOrSummonPet, IMessage>{

		@Override
		public IMessage onMessage(PacketStowOrSummonPet message, MessageContext ctx) {
			((WorldServer)ctx.getServerHandler().playerEntity.worldObj).addScheduledTask(() -> {
				EntityPlayerMP player_mp = ctx.getServerHandler().playerEntity;
				PetInventory inventory = PetInventory.get((EntityPlayer)player_mp);
				World world = player_mp.worldObj;

				Integer petid = inventory.getPetID();

				if(petid == null)
					if(inventory.hasCooldown()){
						float ticks = inventory.getCooldown();
						float seconds = ticks/20f;
						float minutes = seconds >= 60 ? seconds / 60f : 0;
						float remainingSeconds = seconds < 60 ? seconds : seconds - ((int)minutes*60);
						int[] time = new int[]{(int)minutes, (int)remainingSeconds};
						player_mp.addChatComponentMessage(new TextComponentString(TextFormatting.RED+inventory.getPetName()+" is still trying to resurrect ... ~" + time[0]+":"+time[1]));
					}else{
						StowOrSummonLogic.givePet(player_mp);
						player_mp.addChatComponentMessage(new TextComponentString(TextFormatting.BLUE+"Summoned "+inventory.getPetName()));
					}
				else
					//stow pet
					if(world.getEntityByID(petid) != null && world.getEntityByID(petid) instanceof EntityPetBuddy){
						EntityPetBuddy pet = (EntityPetBuddy) world.getEntityByID(petid);
						inventory.setPetHealth(pet.getHealth());
						pet.setDead();
						inventory.setPetID((Integer)null);
						player_mp.addChatComponentMessage(new TextComponentString(TextFormatting.GREEN+"Stowed "+inventory.getPetName()));
					}else{
						StowOrSummonLogic.givePet(player_mp);
						player_mp.addChatComponentMessage(new TextComponentString(TextFormatting.BLUE+"Summoned "+inventory.getPetName()));
					}
			});
			return null;
		}
	}
}
