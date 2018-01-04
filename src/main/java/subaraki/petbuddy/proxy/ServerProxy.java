package subaraki.petbuddy.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import subaraki.petbuddy.entity.EntityPetBuddy;
import subaraki.petbuddy.mod.PetBuddy;

public class ServerProxy {

	public World getClientWorld(){return null;}
	public void registerRenders(){}
	public EntityPlayer getClientPlayer() {return null;}
	public void registerKey() {}
	public ResourceLocation getFriendSkin() {return null;};
}
