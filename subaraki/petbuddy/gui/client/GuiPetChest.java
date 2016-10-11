package subaraki.petbuddy.gui.client;

import lib.util.DrawEntityOnScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import subaraki.petbuddy.capability.PetInventory;
import subaraki.petbuddy.gui.server.ContainerPetChest;
import subaraki.petbuddy.mod.PetBuddy;

public class GuiPetChest extends GuiContainer {

	private static final ResourceLocation gui = new ResourceLocation(PetBuddy.MODID, "textures/gui/petchest.png"); 
	private float oldMouseX;
	private float oldMouseY;
	EntityLivingBase petToRender = null;
	public GuiPetChest(EntityPlayer player) {
		super(new ContainerPetChest(player));
		petToRender = PetInventory.get(player).getPet(player);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

		this.mc.renderEngine.bindTexture(gui);
		int posX = (this.width - xSize) / 2;
		int posY = (this.height - ySize) / 2;
		drawTexturedModalRect(posX, posY, 0, 0, xSize, ySize);
		drawString(fontRendererObj, "Pet Chest", posX+7, posY+6, 0xffffff);

		if(petToRender != null)
			DrawEntityOnScreen.drawEntityOnScreen(
					posX + 142, 
					posY + 60, 
					50,
					(float)(posX + 142)-oldMouseX, 
					(float)(posY + 30)-oldMouseY,
					petToRender);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.oldMouseX = (float)mouseX;
		this.oldMouseY = (float)mouseY;
	}
}
