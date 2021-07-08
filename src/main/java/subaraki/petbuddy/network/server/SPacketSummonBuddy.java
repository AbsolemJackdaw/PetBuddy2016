package subaraki.petbuddy.network.server;

import java.util.function.Supplier;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent.Context;
import subaraki.petbuddy.capability.BuddyData;
import subaraki.petbuddy.network.IPacketBase;
import subaraki.petbuddy.network.NetworkHandler;

public class SPacketSummonBuddy implements IPacketBase {

    public SPacketSummonBuddy() {

    }

    public SPacketSummonBuddy(PacketBuffer buf) {

        decode(buf);
    }

    @Override
    public void encode(PacketBuffer buf)
    {

    }

    @Override
    public void decode(PacketBuffer buf)
    {

    }

    @Override
    public void handle(Supplier<Context> context)
    {

        context.get().enqueueWork(() -> {
            ServerPlayerEntity player = context.get().getSender();
            BuddyData.get(player).ifPresent((data) -> {
                data.summonBuddy((ServerWorld) player.level, player);
            });
        });
        context.get().setPacketHandled(true);

    }

    @Override
    public void register(int id)
    {

        NetworkHandler.NETWORK.registerMessage(id, SPacketSummonBuddy.class, SPacketSummonBuddy::encode, SPacketSummonBuddy::new, SPacketSummonBuddy::handle);

    }

}
