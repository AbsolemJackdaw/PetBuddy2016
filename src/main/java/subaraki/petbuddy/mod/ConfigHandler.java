package subaraki.petbuddy.mod;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;

public class ConfigHandler {

    public static final ServerConfig SERVER;
    public static final ForgeConfigSpec SERVER_SPEC;

    static
    {
        final Pair<ServerConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ServerConfig::new);
        SERVER_SPEC = specPair.getRight();
        SERVER = specPair.getLeft();
    }

    public static final ClientConfig CLIENT;
    public static final ForgeConfigSpec CLIENT_SPEC;

    static
    {
        final Pair<ClientConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ClientConfig::new);
        CLIENT_SPEC = specPair.getRight();
        CLIENT = specPair.getLeft();
    }

    // SERVER
    public static boolean allowAttackProvoked = true;
    public static int timer = 60;

    public static class ServerConfig {

        public final ForgeConfigSpec.BooleanValue allowAttackProvoked;
        public final ForgeConfigSpec.IntValue timer;

        ServerConfig(ForgeConfigSpec.Builder builder) {

            builder.push("Pet attacks when owner is attacked");
            allowAttackProvoked = builder.comment("Allow your buddy to attack entities attacking the owner. ").translation("config.pet.provoke")
                    .define("Pet Attacks When Provoked", false);
            builder.pop();

            builder.push("Death Timer Cooldown");
            timer = builder.comment("Set the timer (in seconds) your pet needs to be able to respawn.").translation("config.pet.timer")
                    .defineInRange("Cooldown timer", 60, 1, 3600);
            builder.pop();
        }
    }

    public static class ClientConfig {

        public final ForgeConfigSpec.BooleanValue allowParticles;

        ClientConfig(ForgeConfigSpec.Builder builder) {

            builder.push("Rendering");
            allowParticles = builder.comment("Some people find them annoying. Feel free to disable them here if needed")
                    .translation("config.particles.telepad.allow").define("Allow Particle Spawning", true);
            builder.pop();

        }
    }

    public static void refreshServer()
    {

        allowAttackProvoked = SERVER.allowAttackProvoked.get();
        timer = SERVER.timer.get();

    }

    public static void refreshClient()
    {

    }
}
