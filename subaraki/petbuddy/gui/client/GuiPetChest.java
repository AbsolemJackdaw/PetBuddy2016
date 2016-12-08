package subaraki.petbuddy.gui.client;

import java.io.IOException;

import lib.util.DrawEntityOnScreen;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import subaraki.petbuddy.capability.PetInventory;
import subaraki.petbuddy.entity.EntityPetBuddy;
import subaraki.petbuddy.gui.server.ContainerPetChest;
import subaraki.petbuddy.mod.PetBuddy;
import subaraki.petbuddy.network.NetworkHandler;
import subaraki.petbuddy.network.PacketSyncPetRenderData;

public class GuiPetChest extends GuiContainer {

	private static final ResourceLocation gui = new ResourceLocation(PetBuddy.MODID, "textures/gui/petchest.png"); 
	private float oldMouseX;
	private float oldMouseY;
	EntityPetBuddy petToRender = null;
	public GuiPetChest(EntityPlayer player) {
		super(new ContainerPetChest(player));
		petToRender = PetInventory.get(player).getPet(player);
	}

	@Override
	public void initGui() {
		super.initGui();
		tryToDrawModeltypeButton();
	}
	
	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		super.mouseReleased(mouseX, mouseY, state);
		tryToDrawModeltypeButton();
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	private void tryToDrawModeltypeButton(){
		ItemStack form = this.inventorySlots.getSlot(14).getStack();
		if(!form.isEmpty() && form.hasDisplayName() && form.getItem().equals(Items.NAME_TAG)){
			buttonList.clear();

			int posX = (this.width - xSize) / 2;
			int posY = (this.height - ySize) / 2;

			buttonList.add(new GuiButton(0, this.guiLeft+162, this.guiTop + 61, 6, 6,""));
		}else{
			buttonList.clear();
		}
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		switch (button.id) {
		case 0:
			String type= petToRender.getModelType().equals("default") ? "slim" : "default";
			petToRender.setModelType(type);
			petToRender.setForceRender(true);
			NetworkHandler.NETWORK.sendToServer(new PacketSyncPetRenderData(type));
			break;
		}
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
