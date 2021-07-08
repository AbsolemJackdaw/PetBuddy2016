package subaraki.petbuddy.mod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import subaraki.petbuddy.server.entity.PetBuddyEntity;
import subaraki.petbuddy.server.inventory.PetBuddyContainer;

@Mod(value = PetBuddy.MODID)
@EventBusSubscriber(bus = Bus.MOD, modid = PetBuddy.MODID)
public class PetBuddy {

    public static final String MODID = "petbuddy";
    public static final String NAME = "pet Buddy";
    public static final String VERSION = "$version";

    public static Logger log = LogManager.getLogger(MODID);

    private static final DeferredRegister<EntityType<?>> ENTITY = DeferredRegister.create(ForgeRegistries.ENTITIES, MODID);
    private static final DeferredRegister<ContainerType<?>> CONTAINER = DeferredRegister.create(ForgeRegistries.CONTAINERS, MODID);

    public static final RegistryObject<EntityType<PetBuddyEntity>> ENTITY_PETBUDDY_TYPE = ENTITY.register("petbuddy_entity",
            () -> EntityType.Builder.of(PetBuddyEntity::new, EntityClassification.CREATURE).sized(0.4f, 0.75f).build(new ResourceLocation(MODID, "pbe").toString()));

    public static final RegistryObject<ContainerType<PetBuddyContainer>> PETBUDDY_CONTAINER_TYPE = CONTAINER.register("petbuddy_container",
            () -> new ContainerType<>(PetBuddyContainer::registerClientContainer));

    public PetBuddy() {

        ModLoadingContext modLoadingContext = ModLoadingContext.get();

        modLoadingContext.registerConfig(ModConfig.Type.SERVER, ConfigHandler.SERVER_SPEC);
        modLoadingContext.registerConfig(ModConfig.Type.CLIENT, ConfigHandler.CLIENT_SPEC);
        
        ENTITY.register(FMLJavaModLoadingContext.get().getModEventBus());
        CONTAINER.register(FMLJavaModLoadingContext.get().getModEventBus());

    }
}
