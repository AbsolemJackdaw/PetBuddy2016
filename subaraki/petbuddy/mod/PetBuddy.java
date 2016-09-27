package subaraki.petbuddy.mod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import subaraki.petbuddy.capability.PetInventoryCapability;
import subaraki.petbuddy.gui.GuiHandler;
import subaraki.petbuddy.hooks.PlayerTracker;
import subaraki.petbuddy.proxy.ServerProxy;

@Mod(modid = PetBuddy.MODID, name = PetBuddy.NAME, version = PetBuddy.VERSION, dependencies = PetBuddy.DEPENDENCY)
public class PetBuddy {

	public static final String MODID = "petbuddy";
	public static final String NAME = "Pet Buddy";
	public static final String VERSION = "1.10.2 v1";
	public static final String DEPENDENCY = "required-after:subcommonlib";

	private static final String client = "subaraki.petbuddy.proxy.ClientProxy";
	private static final String server = "subaraki.petbuddy.proxy.ServerProxy";

	@SidedProxy(clientSide = PetBuddy.client, serverSide = PetBuddy.server)
	public static ServerProxy proxy;

	public static PetBuddy instance;
	
	@EventHandler
	public void preinit(FMLPreInitializationEvent event){
		
		instance = this;
		
		proxy.registerEntities();
		proxy.registerRenders();
		
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		new PetInventoryCapability().register();
		new PlayerTracker();
	}
}
