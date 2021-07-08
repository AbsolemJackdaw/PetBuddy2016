package subaraki.petbuddy.capability;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import subaraki.petbuddy.mod.PetBuddy;

public class CapabilityBuddyProvider implements ICapabilitySerializable<CompoundNBT> {

    /**
     * Unique key to identify the attached provider from others
     */
    public static final ResourceLocation KEY = new ResourceLocation(PetBuddy.MODID, "petbuddy_data");

    /**
     * The instance that we are providing
     */
    final BuddyData data = new BuddyData();

    /**
     * gets called before world is initiated. player.worldObj will return null here
     * !
     */
    public CapabilityBuddyProvider(PlayerEntity player) {

        data.setPlayer(player);
    }

    @Override
    public CompoundNBT serializeNBT() {

        return (CompoundNBT) BuddyCapability.CAPABILITY.writeNBT(data, null);
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {

        BuddyCapability.CAPABILITY.readNBT(data, null, nbt);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {

        if (cap == BuddyCapability.CAPABILITY)
            return (LazyOptional<T>) LazyOptional.of(this::getImpl);

        return LazyOptional.empty();
    }
    
    private BuddyData getImpl() {

        if (data != null) {
            return data;
        }
        return new BuddyData();
    }

}
