package subaraki.petbuddy.registry.forgebus;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import subaraki.petbuddy.capability.CapabilityBuddyProvider;
import subaraki.petbuddy.mod.PetBuddy;

@EventBusSubscriber(bus = Bus.FORGE, modid = PetBuddy.MODID)
public class CapabilityRegistry {

    @SubscribeEvent
    public static void onAttachEventEntity(AttachCapabilitiesEvent<Entity> event)
    {

        final Object entity = event.getObject();

        if (entity instanceof PlayerEntity)
            event.addCapability(CapabilityBuddyProvider.KEY, new CapabilityBuddyProvider((PlayerEntity) entity));
    }
}
