package subaraki.petbuddy.client.inventory;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import subaraki.petbuddy.capability.BuddyData;
import subaraki.petbuddy.client.ItemRendererTransparent;
import subaraki.petbuddy.mod.PetBuddy;
import subaraki.petbuddy.server.entity.PetBuddyEntity;
import subaraki.petbuddy.server.inventory.PetBuddyContainer;

public class PetBuddyScreen extends ContainerScreen<PetBuddyContainer> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(PetBuddy.MODID, "textures/gui/buddy_inventory.png");

    private final ItemStack helm, sword, chest, lapis;
    private ItemRendererTransparent renderer;

    public PetBuddyScreen(PetBuddyContainer pbc, PlayerInventory inventory, ITextComponent text) {

        super(pbc, inventory, text);
        this.imageWidth = 176;
        this.imageHeight = 166;

        renderer = new ItemRendererTransparent(Minecraft.getInstance().textureManager, Minecraft.getInstance().getModelManager(),
                Minecraft.getInstance().getItemColors());

        this.helm = new ItemStack(Items.NETHERITE_HELMET);
        this.sword = new ItemStack(Items.NETHERITE_SWORD);
        this.chest = new ItemStack(Blocks.BARREL);
        this.lapis = new ItemStack(Items.NAME_TAG);

    }

    @Override
    protected void renderBg(MatrixStack stack, float partialTicks, int mouseX, int mouseY)
    {

        int xStart = (this.width - this.imageWidth) / 2;
        int yStart = (this.height - this.imageHeight) / 2;

        // draw the background
        this.minecraft.getTextureManager().bind(TEXTURE);
        this.blit(stack, xStart, yStart, 0, 0, this.imageWidth, this.imageHeight);

        if (menu.getSlot(3).hasItem())
            this.blit(stack, xStart + 7, yStart + 15, 0, this.imageHeight, 72, 54);

        int i = this.leftPos;
        int j = this.topPos;

        BuddyData.get(minecraft.player).ifPresent((data) -> {

            Entity e = minecraft.level.getEntity(data.getBuddyID());

            if (e instanceof PetBuddyEntity)
                InventoryScreen.renderEntityInInventory(i + 145, j + 65, 60, (float) (i + 145) - mouseX, (float) (j + 75 - 50) - mouseY, (PetBuddyEntity) e);
        });

        for (int the_slot = 0; the_slot < 4; the_slot++)
            if (this.menu.getSlot(the_slot).getItem().isEmpty())
            {
                this.renderItem(stack, menu.getSlot(the_slot), the_slot == 0 ? helm : the_slot == 1 ? sword : the_slot == 2 ? lapis : chest);
            }
    }

    @Override
    public void render(MatrixStack stack, int p_230430_2_, int p_230430_3_, float p_230430_4_)
    {

        super.render(stack, p_230430_2_, p_230430_3_, p_230430_4_);
        this.renderTooltip(stack, p_230430_2_, p_230430_3_);

    }

    private void renderItem(MatrixStack stack, Slot slot, ItemStack itemstack)
    {

        int i = slot.x + (this.width - this.imageWidth) / 2;
        int j = slot.y + (this.height - this.imageHeight) / 2;


        RenderSystem.enableDepthTest();
        renderer.renderAndDecorateItem(this.minecraft.player, itemstack, i, j);

    }
}
