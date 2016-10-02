package subaraki.petbuddy.mod;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import subaraki.petbuddy.capability.PetInventoryCapability;
import subaraki.petbuddy.gui.GuiHandler;
import subaraki.petbuddy.gui.handler.KeyHandler;
import subaraki.petbuddy.hooks.PetDeathTracker;
import subaraki.petbuddy.hooks.PlayerTracker;
import subaraki.petbuddy.hooks.StowOrSummonLogic;
import subaraki.petbuddy.network.NetworkHandler;
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
		proxy.registerKey();
		
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		new PetInventoryCapability().register();
		new StowOrSummonLogic();
		new PlayerTracker();
		new NetworkHandler();
		new PetDeathTracker();
		
		new KeyHandler();
	}
}
