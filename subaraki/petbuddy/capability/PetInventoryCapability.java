package subaraki.petbuddy.capability;

import java.util.concurrent.Callable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class PetInventoryCapability {

	/*
	 * This field will contain the forge-allocated Capability class.
	 * This instance will be initialized internally by Forge, upon calling register.
	 */
	@CapabilityInject(PetInventory.class)
	public static Capability<PetInventory> CAPABILITY;

	/*
	 * This registers our capability to the manager
	 */
	public void register(){
		CapabilityManager.INSTANCE.register(

				// This is the class the capability works with
				PetInventory.class,

				// This is a helper for users to save and load
				new StorageHelper(),

				// This is a factory for default instances
				new DefaultInstanceFactory()
				);
	}

	/*
	 * This class handles saving and loading the data.
	 */
	public static class StorageHelper implements Capability.IStorage<PetInventory>{

		@Override
		public NBTBase writeNBT(Capability<PetInventory> capability, PetInventory instance, EnumFacing side)		{
			return instance.writeData();
		}

		@Override
		public void readNBT(Capability<PetInventory> capability, PetInventory instance, EnumFacing side, NBTBase nbt){
			instance.readData(nbt);
		}
	}

	/*
	 * This class handles constructing new instances for this capability
	 */
	public static class DefaultInstanceFactory implements Callable<PetInventory>{
		@Override
		public PetInventory call() throws Exception{
			return new PetInventory();
		}
	}
}
