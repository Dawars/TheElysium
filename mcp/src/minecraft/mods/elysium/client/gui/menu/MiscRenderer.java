package mods.elysium.client.gui.menu;

import static org.lwjgl.opengl.GL11.*;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;

public class MiscRenderer 
{
	static int displayListSky;
	static int[] displayLists = new int[6];
	static int renderDistance = 16;
	
	static void render(int x, int z)
	{
		glColor3f(1F, 1F, 1F);
		for(int i = x-renderDistance; i < x+renderDistance; i++)
		{
			for(int k = z-renderDistance; k < z+renderDistance; k++)
			{
				if((i >= 0) && (i < 64) && (k >= 0) && (k < 64))
				{
					for(int j = 0; j < 16; j++)
					{
						glPushMatrix();
							glTranslatef(i, j, k);
							renderBlock(i, j, k);
						glPopMatrix();
					}
				}
			}
		}
	}
	
	static void renderBlock(int x, int y, int z)
	{
		if(Block.blocksList[MiscWorld.getBlockId(x, y, z)] == null)
			return;
		
		String texture;
		for(int i = 0; i < 6; i++)
		{
			if(((i == 0) && (y > 0) && (MiscWorld.getBlockId(x, y-1, z) == 0))
				|| ((i == 1) && (y < 15) && (MiscWorld.getBlockId(x, y+1, z) == 0))
				|| ((i == 2) && (x < 63) && (MiscWorld.getBlockId(x+1, y, z) == 0))
				|| ((i == 3) && (x > 0) && (MiscWorld.getBlockId(x-1, y, z) == 0))
				|| ((i == 4) && (z > 0) && (MiscWorld.getBlockId(x, y, z-1) == 0))
				|| ((i == 5) && (z < 63) && (MiscWorld.getBlockId(x, y, z+1) == 0)))
			{
				if(Block.blocksList[MiscWorld.getBlockId(x, y, z)].getIcon(i, MiscWorld.getBlockMetadata(x, y, z)) != null)
				{
					texture = Block.blocksList[MiscWorld.getBlockId(x, y, z)].getIcon(i, MiscWorld.getBlockMetadata(x, y, z)).getIconName().replaceAll(":", "/");
					texture = "/mods/" + texture.substring(0, texture.lastIndexOf("/")+1) + "textures/blocks/" + texture.substring(texture.lastIndexOf("/")+1)+".png";
					glBindTexture(GL_TEXTURE_2D, Minecraft.getMinecraft().renderEngine.getTexture(texture));
				}
				glCallList(displayLists[i]);
			}
		}
	}
	
	static void load()
	{
		//Top
		displayLists[1] = glGenLists(1);
		glNewList(displayLists[1], GL_COMPILE);
			glBegin(GL_QUADS);
				glTexCoord2f(0F, 0F);
				glVertex3f(-0.5F, 0.5F, -0.5F);
				glTexCoord2f(0F, 1F);
				glVertex3f(-0.5F, 0.5F, 0.5F);
				glTexCoord2f(1F, 1F);
				glVertex3f(0.5F, 0.5F, 0.5F);
				glTexCoord2f(1F, 0F);
				glVertex3f(0.5F, 0.5F, -0.5F);
			glEnd();
		glEndList();
		
		//Bottom
		displayLists[0] = glGenLists(1);
		glNewList(displayLists[0], GL_COMPILE);
			glBegin(GL_QUADS);
				glTexCoord2f(0F, 0F);
				glVertex3f(0.5F, -0.5F, -0.5F);
				glTexCoord2f(0F, 1F);
				glVertex3f(0.5F, -0.5F, 0.5F);
				glTexCoord2f(1F, 1F);
				glVertex3f(-0.5F, -0.5F, 0.5F);
				glTexCoord2f(1F, 0F);
				glVertex3f(-0.5F, -0.5F, -0.5F);
			glEnd();
		glEndList();
		
		//East
		displayLists[2] = glGenLists(1);
		glNewList(displayLists[2], GL_COMPILE);
			glBegin(GL_QUADS);
				glTexCoord2f(0F, 0F);
				glVertex3f(0.5F, 0.5F, 0.5F);
				glTexCoord2f(0F, 1F);
				glVertex3f(0.5F, -0.5F, 0.5F);
				glTexCoord2f(1F, 1F);
				glVertex3f(0.5F, -0.5F, -0.5F);
				glTexCoord2f(1F, 0F);
				glVertex3f(0.5F, 0.5F, -0.5F);
			glEnd();
		glEndList();
		
		//West
		displayLists[3] = glGenLists(1);
		glNewList(displayLists[3], GL_COMPILE);
			glBegin(GL_QUADS);
				glTexCoord2f(0F, 0F);
				glVertex3f(-0.5F, 0.5F, -0.5F);
				glTexCoord2f(0F, 1F);
				glVertex3f(-0.5F, -0.5F, -0.5F);
				glTexCoord2f(1F, 1F);
				glVertex3f(-0.5F, -0.5F, 0.5F);
				glTexCoord2f(1F, 0F);
				glVertex3f(-0.5F, 0.5F, 0.5F);
			glEnd();
		glEndList();
		
		//North
		displayLists[4] = glGenLists(1);
		glNewList(displayLists[4], GL_COMPILE);
			glBegin(GL_QUADS);
				glTexCoord2f(0F, 0F);
				glVertex3f(0.5F, 0.5F, -0.5F);
				glTexCoord2f(0F, 1F);
				glVertex3f(0.5F, -0.5F, -0.5F);
				glTexCoord2f(1F, 1F);
				glVertex3f(-0.5F, -0.5F, -0.5F);
				glTexCoord2f(1F, 0F);
				glVertex3f(-0.5F, 0.5F, -0.5F);
			glEnd();
		glEndList();
		
		//South
		displayLists[5] = glGenLists(1);
		glNewList(displayLists[5], GL_COMPILE);
			glBegin(GL_QUADS);
				glTexCoord2f(0F, 0F);
				glVertex3f(-0.5F, 0.5F, 0.5F);
				glTexCoord2f(0F, 1F);
				glVertex3f(-0.5F, -0.5F, 0.5F);
				glTexCoord2f(1F, 1F);
				glVertex3f(0.5F, -0.5F, 0.5F);
				glTexCoord2f(1F, 0F);
				glVertex3f(0.5F, 0.5F, 0.5F);
			glEnd();
		glEndList();
		
		displayListSky = glGenLists(1);
		glNewList(displayListSky, GL_COMPILE);
			glBegin(GL_QUADS);
				//North
				glVertex3f(-renderDistance, renderDistance, -renderDistance);
				glVertex3f(-renderDistance, -renderDistance, -renderDistance);
				glVertex3f(renderDistance, -renderDistance, -renderDistance);
				glVertex3f(renderDistance, renderDistance, -renderDistance);
				
				//South
				glVertex3f(renderDistance, renderDistance, renderDistance);
				glVertex3f(renderDistance, -renderDistance, renderDistance);
				glVertex3f(-renderDistance, -renderDistance, renderDistance);
				glVertex3f(-renderDistance, renderDistance, renderDistance);
				
				//East
				glVertex3f(-renderDistance, renderDistance, renderDistance);
				glVertex3f(-renderDistance, -renderDistance, renderDistance);
				glVertex3f(-renderDistance, -renderDistance, -renderDistance);
				glVertex3f(-renderDistance, renderDistance, -renderDistance);
				
				//West
				glVertex3f(renderDistance, renderDistance, -renderDistance);
				glVertex3f(renderDistance, -renderDistance, -renderDistance);
				glVertex3f(renderDistance, -renderDistance, renderDistance);
				glVertex3f(renderDistance, renderDistance, renderDistance);
				
				//Top
				glVertex3f(-renderDistance, renderDistance, renderDistance);
				glVertex3f(-renderDistance, renderDistance, -renderDistance);
				glVertex3f(renderDistance, renderDistance, -renderDistance);
				glVertex3f(renderDistance, renderDistance, renderDistance);
				
				//Bottom
				glVertex3f(renderDistance, -renderDistance, renderDistance);
				glVertex3f(renderDistance, -renderDistance, -renderDistance);
				glVertex3f(-renderDistance, -renderDistance, -renderDistance);
				glVertex3f(-renderDistance, -renderDistance, renderDistance);
			glEnd();
		glEndList();
	}
}
