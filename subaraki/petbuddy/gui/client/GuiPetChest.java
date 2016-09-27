package subaraki.petbuddy.gui.client;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import subaraki.petbuddy.gui.server.ContainerPetChest;
import subaraki.petbuddy.mod.PetBuddy;

public class GuiPetChest extends GuiContainer {

	private static final ResourceLocation gui = new ResourceLocation(PetBuddy.MODID, "textures/gui/petchest.png"); 
	
	public GuiPetChest(EntityPlayer player) {
		super(new ContainerPetChest(player));
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		
		this.mc.renderEngine.bindTexture(gui);
		int posX = (this.width - xSize) / 2;
		int posY = (this.height - ySize) / 2;
		drawTexturedModalRect(posX, posY, 0, 0, xSize, ySize);
		drawString(fontRendererObj, "Pet Chest", posX+7, posY+8, 0xffffff);
		
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
