package subaraki.petbuddy.registry.forgebus;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent.KeyInputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import subaraki.petbuddy.mod.PetBuddy;
import subaraki.petbuddy.network.NetworkHandler;
import subaraki.petbuddy.network.server.SPacketSummonBuddy;
import subaraki.petbuddy.registry.modbus.RegisterClientSetup;

@EventBusSubscriber(bus = Bus.FORGE, modid = PetBuddy.MODID, value = Dist.CLIENT)
public class KeyTracker {

    @SubscribeEvent
    public static void keys(KeyInputEvent evt)
    {

        if (RegisterClientSetup.keySummonPet.consumeClick())
        {
            NetworkHandler.NETWORK.sendToServer(new SPacketSummonBuddy());
        }
    }
}
