package subaraki.petbuddy.proxy;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import subaraki.petbuddy.entity.EntityPetBuddy;
import subaraki.petbuddy.entity.RenderPetBuddy;

public class ClientProxy extends ServerProxy {

	public static KeyBinding summonPet;
	
	@Override
	public void registerKey() {
		summonPet = new KeyBinding("Summon/Dismiss Pet", Keyboard.KEY_P, "Pet Buddy");
		ClientRegistry.registerKeyBinding(summonPet);
	}
	
	@Override
	public void registerRenders() {
		RenderingRegistry.registerEntityRenderingHandler(EntityPetBuddy.class, RenderPetBuddy::new);
	}
	
	@Override
	public World getClientWorld() {
		return Minecraft.getMinecraft().theWorld;
	}
	
	@Override
	public EntityPlayer getClientPlayer() {
		return Minecraft.getMinecraft().thePlayer;
	}
}
