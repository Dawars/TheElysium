package mods.elysium.client.gui;

import static org.lwjgl.opengl.GL11.*;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mods.elysium.inventory.ContainerElysianWorkbench;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class GuiElysianCrafting extends GuiContainer
{
	public GuiElysianCrafting(World world, int x, int y, int z, InventoryPlayer playerInv, IInventory workInv)
	{
		super(new ContainerElysianWorkbench(world, x, y, z, playerInv, workInv));
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		this.fontRenderer.drawString(StatCollector.translateToLocal("container.crafting"), 28, 6, 4210752);
		this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float tick, int mx, int my)
	{
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		
		glColor3f(1F, 1F, 1F);
		glBindTexture(GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/mods/elysium/textures/gui/crafting.png"));
		this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
	}
}
