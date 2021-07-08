package subaraki.petbuddy.network;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import subaraki.petbuddy.mod.PetBuddy;
import subaraki.petbuddy.network.client.CPacketSyncArmorSlots;
import subaraki.petbuddy.network.server.SPacketSummonBuddy;

public class NetworkHandler {

    private static final String PROTOCOL_VERSION = "1";

    public static final SimpleChannel NETWORK = NetworkRegistry.newSimpleChannel(new ResourceLocation(PetBuddy.MODID, "buddychannel"), () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    public NetworkHandler() {

        int id = 0;
        // client handling
        new CPacketSyncArmorSlots().register(id++);

        // Server handling
        new SPacketSummonBuddy().register(id++);
    }
}
