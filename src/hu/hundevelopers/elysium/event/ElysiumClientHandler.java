package hu.hundevelopers.elysium.event;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTranslated;
import hu.hundevelopers.elysium.item.ElysiumStaffItem;
import me.dawars.CraftingPillars.renderer.RenderingHelper;
import net.minecraft.block.Block;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderPlayerEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ElysiumClientHandler
{
//	@SubscribeEvent
//	public void guiOpen(GuiOpenEvent event) {
//		if (event.gui instanceof GuiMainMenu && Configs.customGui) {
//			Minecraft.getMinecraft().displayGuiScreen(new ElysiumGuiEmulator(new ElysiumGuiMainMenu(null)));
//			event.setCanceled(true);
//		}
//	}
	
	private RenderingHelper.ItemRender itemRenderer;
	
	public ElysiumClientHandler()
	{
		itemRenderer = new RenderingHelper.ItemRender(false, false);
	}
	
	@SubscribeEvent
	public void onEntityRender(RenderPlayerEvent.Post event)
	{
		EntityPlayerSP player = (EntityPlayerSP) event.entityPlayer;
		
		ItemStack item = player.getCurrentEquippedItem();
				
		if(item != null && item.getItem() instanceof ElysiumStaffItem && item.getItemDamage() == 0)
		{
			Block block = ElysiumStaffItem.getBlockHolding(item); 
			if(block != null)
			{
				glPushMatrix();
					glTranslated(0, -0.3D, 0);
					glRotatef(player.rotationYaw, 0, -1, 0);
					glRotatef(player.rotationPitch, 1, 0, 0);
					
					float f = 1.5F;
					glScalef(f, f, f);
//						glRotatef(90, 0, -1, 0);
								
					EntityItem entityitem = new EntityItem(player.worldObj);
					entityitem.setEntityItemStack(new ItemStack(block));
					entityitem.hoverStart = 0F;
					itemRenderer.render(entityitem, 0, 0, 0.5F, false);

				glPopMatrix();
			
			}
		}
	}
}
