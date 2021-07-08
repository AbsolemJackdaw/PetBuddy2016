package subaraki.petbuddy.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ClientReferences {

    public static PlayerEntity getClientPlayer()
    {

        return Minecraft.getInstance().player;
    }

    public static World getClientWorld()
    {

        return Minecraft.getInstance().level;
    }

    public static void displayScreen(Screen screen)
    {

        Minecraft.getInstance().setScreen(screen);
    }
}
