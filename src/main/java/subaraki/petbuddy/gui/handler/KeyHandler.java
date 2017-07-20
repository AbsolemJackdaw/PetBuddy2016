package subaraki.petbuddy.gui.handler;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
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