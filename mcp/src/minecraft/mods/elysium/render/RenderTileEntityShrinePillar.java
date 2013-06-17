package mods.elysium.render;

import static org.lwjgl.opengl.GL11.*;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;

import mods.elysium.Elysium;
import mods.elysium.client.gui.menu.MiscWorld;
import mods.elysium.entity.tileentity.TileEntityShrinePillar;
import mods.elysium.model.ModelWorkPillar;

public class RenderTileEntityShrinePillar extends TileEntitySpecialRenderer
{
	ModelWorkPillar model = new ModelWorkPillar();
	RenderItem itemrenderer = new RenderItem();
	
	public RenderTileEntityShrinePillar()
	{
		itemrenderer.setRenderManager(RenderManager.instance);
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTick)
	{
		glPushMatrix();
			glTranslated(x+0.5D, y+1.5D, z+0.5D);
			glScaled(0.0625D, 0.0625D, 0.0625D);
			glRotatef(180F, 1F, 0F, 0F);
			
			glBindTexture(GL_TEXTURE_2D, Minecraft.getMinecraft().renderEngine.getTexture("/mods/elysium/textures/models/elysianWorkpillar2.png"));
			model.render(tile, 1F);
		glPopMatrix();
		
		TileEntityShrinePillar workTile = (TileEntityShrinePillar) tile;
		
		glPushMatrix();
			glTranslated(x, y, z);
			for(int i = 0; i < 3; i++)
			{
				for(int k = 0; k < 3; k++)
				{
					if(workTile.stuff[i*3 + k] != null)
					{
						EntityItem citem = new EntityItem(tile.worldObj);
						citem.hoverStart = workTile.rot;
						citem.setEntityItemStack(workTile.stuff[i*3 + k]);
						glPushMatrix();
							glTranslated(0.1875D + i*0.3125D, 1D + 0.1875D/3D, 0.1875D + k*0.3125D);
							glScalef(0.5F, 0.5F, 0.5F);
							itemrenderer.doRenderItem(citem, 0, 0, 0, 0F, 0F);
						glPopMatrix();
					}
				}
			}
		glPopMatrix();
	}
}
