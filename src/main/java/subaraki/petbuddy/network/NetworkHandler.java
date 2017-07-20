package subaraki.petbuddy.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import subaraki.petbuddy.network.PacketStowOrSummonPet.PacketStowOrSummonPetHandler;
import subaraki.petbuddy.network.PacketSyncOtherInventory.PacketSyncOtherInventoryHandler;
import subaraki.petbuddy.network.PacketSyncOwnInventory.PacketSyncOwnInventoryHandler;
import subaraki.petbuddy.network.PacketSyncPetRenderData.PacketSyncPetRenderDataHandler;

public class NetworkHandler {
	public static final String CHANNEL = "petbuddy_channel";
	public static final SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(CHANNEL);

	public NetworkHandler() {
		NETWORK.registerMessage(PacketSyncOtherInventoryHandler.class, PacketSyncOtherInventory.class, 0, Side.CLIENT);
		NETWORK.registerMessage(PacketSyncOwnInventoryHandler.class, PacketSyncOwnInventory.class, 1, Side.CLIENT);
		NETWORK.registerMessage(PacketStowOrSummonPetHandler.class, PacketStowOrSummonPet.class, 2, Side.SERVER);
		NETWORK.registerMessage(PacketSyncPetRenderDataHandler.class, PacketSyncPetRenderData.class, 3, Side.SERVER);

	}
}
