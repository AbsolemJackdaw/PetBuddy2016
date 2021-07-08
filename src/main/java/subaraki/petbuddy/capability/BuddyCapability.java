package subaraki.petbuddy.capability;

import java.util.concurrent.Callable;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class BuddyCapability {

    /*
     * This field will contain the forge-allocated Capability class. This instance
     * will be initialized internally by Forge, upon calling register.
     */
    @CapabilityInject(BuddyData.class)
    public static Capability<BuddyData> CAPABILITY;

    /*
     * This registers our capability to the manager
     */
    public void register() {

        CapabilityManager.INSTANCE.register(

                // This is the class the capability works with
                BuddyData.class,

                // This is a helper for users to save and load
                new StorageHelper(),

                // This is a factory for default instances
                new DefaultInstanceFactory());
    }

    /*
     * This class handles saving and loading the data.
     */
    public static class StorageHelper implements Capability.IStorage<BuddyData> {

        @Override
        public INBT writeNBT(Capability<BuddyData> capability, BuddyData instance, Direction side) {

            return instance.writeData();
        }

        @Override
        public void readNBT(Capability<BuddyData> capability, BuddyData instance, Direction side, INBT nbt) {

            instance.readData(nbt);
        }
    }

    /*
     * This class handles constructing new instances for this capability
     */
    public static class DefaultInstanceFactory implements Callable<BuddyData> {

        @Override
        public BuddyData call() throws Exception {

            return new BuddyData();
        }
    }
}
