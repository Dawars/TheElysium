package mods.elysium.client.gui;

import static org.lwjgl.opengl.GL11.*;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;

@SideOnly(Side.CLIENT)
public class GuiElyisanOverlay
{
	private Minecraft mc;
	
	public GuiElyisanOverlay(Minecraft mc)
	{
		this.mc = mc;
	}
	
	@ForgeSubscribe(priority = EventPriority.NORMAL)
	public void render(RenderGameOverlayEvent event)
	{
		
	}
}