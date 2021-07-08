package subaraki.petbuddy.registry.forgebus;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerChangedDimensionEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import subaraki.petbuddy.capability.BuddyData;
import subaraki.petbuddy.mod.PetBuddy;

@EventBusSubscriber(bus = Bus.FORGE, modid = PetBuddy.MODID)
public class PlayerTracker {

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event)
    {

        BuddyData.get(event.getOriginal()).ifPresent(data -> {
            BuddyData.get(event.getPlayer()).ifPresent(newdata -> {
                newdata.readData(data.writeData());
            });
        });

    }

    @SubscribeEvent
    public static void onEntityLeaveWorld(PlayerLoggedOutEvent event)
    {

        if (!event.getPlayer().level.isClientSide())
        {
            BuddyData.get(event.getPlayer()).ifPresent((data) -> {
                data.removeBuddyFromWorld();
            });
        }
    }

    @SubscribeEvent
    public static void onEntityDimensionChange(PlayerChangedDimensionEvent event)
    {

        if (!event.getPlayer().level.isClientSide())
        {
            BuddyData.get(event.getPlayer()).ifPresent((data) -> {
                data.removeBuddyFromWorld();
            });
        }
    }

    @SubscribeEvent
    public static void onPlayerEvent(LivingUpdateEvent event)
    {

        if (event.getEntityLiving() instanceof PlayerEntity)
        {
            PlayerEntity player = (PlayerEntity) event.getEntityLiving();
            BuddyData.get(player).ifPresent((data) -> {
                data.coolDown();
            });
        }

    }

}
