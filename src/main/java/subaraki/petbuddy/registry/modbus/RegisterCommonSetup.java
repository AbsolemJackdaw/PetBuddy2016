package subaraki.petbuddy.registry.modbus;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import subaraki.petbuddy.capability.BuddyCapability;
import subaraki.petbuddy.mod.PetBuddy;
import subaraki.petbuddy.network.NetworkHandler;

@EventBusSubscriber(modid = PetBuddy.MODID, bus = Bus.MOD)
public class RegisterCommonSetup {

    @SubscribeEvent
    public static void register(final FMLCommonSetupEvent event)
    {

        new BuddyCapability().register();
        new NetworkHandler();
    }

}
