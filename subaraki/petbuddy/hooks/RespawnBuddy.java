package subaraki.petbuddy.hooks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentSelector;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import subaraki.petbuddy.entity.PetBuddyRegistry;
import subaraki.petbuddy.mod.PetBuddy;

public class RespawnBuddy {

	public static final Item coolDown = PetBuddy.coolDownItem;

	public RespawnBuddy() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onPlayerUpdate(LivingUpdateEvent event){

		EntityPlayer player = null;

		if(event.getEntityLiving() instanceof EntityPlayer){
			player = (EntityPlayer)event.getEntityLiving();

			if(PetBuddyRegistry.getBuddyFromPlayer(player) == null){
				if(!player.getCooldownTracker().hasCooldown(coolDown)){//gets set on buddy death
					PetBuddyRegistry.onLogin(player);
					if(PetBuddyRegistry.getBuddyFromPlayer(player) != null)
						player.addChatComponentMessage(new TextComponentString("<"+PetBuddyRegistry.getBuddyFromPlayer(player).getName()+"> I'm Back !"));
				}
			}
		}
	}
}
