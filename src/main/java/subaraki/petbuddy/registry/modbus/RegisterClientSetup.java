package subaraki.petbuddy.registry.modbus;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.entity.model.CodModel;
import net.minecraft.client.renderer.entity.model.PufferFishBigModel;
import net.minecraft.client.renderer.entity.model.SalmonModel;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
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
import subaraki.petbuddy.petform.BatForm;
import subaraki.petbuddy.petform.BeeForm;
import subaraki.petbuddy.petform.CatForm;
import subaraki.petbuddy.petform.ChickenForm;
import subaraki.petbuddy.petform.CowForm;
import subaraki.petbuddy.petform.DolphinForm;
import subaraki.petbuddy.petform.EnderManForm;
import subaraki.petbuddy.petform.FishForm;
import subaraki.petbuddy.petform.FoxForm;
import subaraki.petbuddy.petform.HorseForm;
import subaraki.petbuddy.petform.IronGolemForm;
import subaraki.petbuddy.petform.LlamaForm;
import subaraki.petbuddy.petform.MooshroomForm;
import subaraki.petbuddy.petform.ParrotForm;
import subaraki.petbuddy.petform.PigForm;
import subaraki.petbuddy.petform.RabbitForm;
import subaraki.petbuddy.petform.SheepForm;
import subaraki.petbuddy.petform.SkinForm;
import subaraki.petbuddy.petform.SnowManForm;
import subaraki.petbuddy.petform.SnowManPumpkinForm;
import subaraki.petbuddy.petform.SpiderForm;
import subaraki.petbuddy.petform.SquidForm;
import subaraki.petbuddy.petform.StriderForm;
import subaraki.petbuddy.petform.TropicalFishForm;
import subaraki.petbuddy.petform.TurtleForm;
import subaraki.petbuddy.petform.VillagerForm;

@EventBusSubscriber(modid = PetBuddy.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class RegisterClientSetup {

    public static KeyBinding keySummonPet;

    public static void registerKey()
    {

        keySummonPet = new KeyBinding("Summon Your PetBuddy", GLFW.GLFW_KEY_P, "PetBuddy");
        ClientRegistry.registerKeyBinding(keySummonPet);
    }

    private static final Item[] allWool = new Item[] { Items.WHITE_WOOL, Items.ORANGE_WOOL, Items.MAGENTA_WOOL, Items.LIGHT_BLUE_WOOL, Items.YELLOW_WOOL,
            Items.LIME_WOOL, Items.PINK_WOOL, Items.GRAY_WOOL, Items.LIGHT_GRAY_WOOL, Items.CYAN_WOOL, Items.PURPLE_WOOL, Items.BLUE_WOOL, Items.BROWN_WOOL,
            Items.GREEN_WOOL, Items.RED_WOOL, Items.BLACK_WOOL };

    private static final Item[] allCarpet = new Item[] { Items.WHITE_CARPET, Items.ORANGE_CARPET, Items.MAGENTA_CARPET, Items.LIGHT_BLUE_CARPET,
            Items.YELLOW_CARPET, Items.LIME_CARPET, Items.PINK_CARPET, Items.GRAY_CARPET, Items.LIGHT_GRAY_CARPET, Items.CYAN_CARPET, Items.PURPLE_CARPET,
            Items.BLUE_CARPET, Items.BROWN_CARPET, Items.GREEN_CARPET, Items.RED_CARPET, Items.BLACK_CARPET };

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
        PetForms.addForm(new MooshroomForm(Items.RED_MUSHROOM, "red_mooshroom"));
        PetForms.addForm(new MooshroomForm(Items.BROWN_MUSHROOM, "brown_mooshroom"));
        PetForms.addForm(new SnowManForm());
        PetForms.addForm(new SnowManPumpkinForm());
        PetForms.addForm(new TurtleForm());
        PetForms.addForm(new BatForm());
        PetForms.addForm(new CatForm());
        PetForms.addForm(new FishForm(Items.COD, "cod", new CodModel<>()));
        PetForms.addForm(new FishForm(Items.SALMON, "salmon", new SalmonModel<>()));
        PetForms.addForm(new FishForm(Items.PUFFERFISH, "pufferfish", new PufferFishBigModel<>()));
        PetForms.addForm(new TropicalFishForm());

        for (Item wool : allWool)
            PetForms.addForm(new SheepForm(wool));

        PetForms.addForm(new SquidForm());
        PetForms.addForm(new RabbitForm());
        PetForms.addForm(new StriderForm());
        PetForms.addForm(new HorseForm());
        
        PetForms.addForm(new ParrotForm());
        PetForms.addForm(new VillagerForm());

        PetForms.addForm(new BeeForm());
        PetForms.addForm(new SpiderForm());
        PetForms.addForm(new DolphinForm());
        PetForms.addForm(new EnderManForm());
        PetForms.addForm(new IronGolemForm());

        for (Item carpet : allCarpet)
            PetForms.addForm(new LlamaForm(carpet));
    }

}
