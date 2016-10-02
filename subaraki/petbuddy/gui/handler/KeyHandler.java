package subaraki.petbuddy.gui.handler;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import subaraki.petbuddy.capability.PetInventory;
import subaraki.petbuddy.hooks.StowOrSummonLogic;
import subaraki.petbuddy.mod.PetBuddy;
import subaraki.petbuddy.network.NetworkHandler;
import subaraki.petbuddy.network.PacketStowOrSummonPet;
import subaraki.petbuddy.proxy.ClientProxy;

public class KeyHandler {

	public KeyHandler() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void keys(KeyInputEvent evt) {
		if(ClientProxy.summonPet.isPressed()){
			NetworkHandler.NETWORK.sendToServer(new PacketStowOrSummonPet());
		}
	}
}