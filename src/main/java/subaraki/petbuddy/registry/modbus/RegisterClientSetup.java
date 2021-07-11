package subaraki.petbuddy.registry.modbus;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import subaraki.petbuddy.api.PetForms;
import subaraki.petbuddy.client.entity.RenderEntityPetBuddy;
import subaraki.petbuddy.client.inventory.PetBuddyScreen;
import subaraki.petbuddy.mod.PetBuddy;
import subaraki.petbuddy.petform.ChickenForm;
import subaraki.petbuddy.petform.CowForm;
import subaraki.petbuddy.petform.FoxForm;
import subaraki.petbuddy.petform.PigForm;
import subaraki.petbuddy.petform.SkinForm;

@EventBusSubscriber(modid = PetBuddy.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class RegisterClientSetup {

    public static KeyBinding keySummonPet;

    public static void registerKey()
    {

        keySummonPet = new KeyBinding("Summon Your PetBuddy", GLFW.GLFW_KEY_P, "PetBuddy");
        ClientRegistry.registerKeyBinding(keySummonPet);
    }

    @SubscribeEvent
    public static void register(final FMLClientSetupEvent event)
    {

        RegisterClientSetup.registerKey();
        RenderingRegistry.registerEntityRenderingHandler(PetBuddy.ENTITY_PETBUDDY_TYPE.get(), RenderEntityPetBuddy::new);
        ScreenManager.register(PetBuddy.PETBUDDY_CONTAINER_TYPE.get(), PetBuddyScreen::new);

        PetForms.addForm(new SkinForm());
        PetForms.addForm(new ChickenForm());
        PetForms.addForm(new PigForm());
        PetForms.addForm(new CowForm());
        PetForms.addForm(new FoxForm());

    }

}
