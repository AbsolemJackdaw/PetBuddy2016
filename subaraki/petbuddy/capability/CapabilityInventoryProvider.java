package subaraki.petbuddy.capability;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import subaraki.petbuddy.mod.PetBuddy;

public class CapabilityInventoryProvider implements ICapabilitySerializable<NBTTagCompound>
{
    /**
     * Unique key to identify the attached provider from others
     */
    public static final ResourceLocation KEY = new ResourceLocation(PetBuddy.MODID, "pet_inventory");

    /**
     * The instance that we are providing
     */
    final PetInventory pet_inventory = new PetInventory();

    /**gets called before world is initiated. player.worldObj will return null here !*/
    public CapabilityInventoryProvider(EntityPlayer player){
        pet_inventory.setPlayer(player);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing)
    {
        if (capability == PetInventoryCapability.CAPABILITY)
            return true;
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing){
        if (capability == PetInventoryCapability.CAPABILITY)
            return (T)pet_inventory;
        return null;
    }

    @Override
    public NBTTagCompound serializeNBT(){
        return (NBTTagCompound) PetInventoryCapability.CAPABILITY.writeNBT(pet_inventory, null);
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt){
    	PetInventoryCapability.CAPABILITY.readNBT(pet_inventory, null, nbt);
    }
}
