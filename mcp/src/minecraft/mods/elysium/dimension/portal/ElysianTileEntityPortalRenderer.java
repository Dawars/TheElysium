package mods.elysium.dimension.portal;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.elysium.DefaultProps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDragonEgg;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import static org.lwjgl.opengl.GL11.*;

@SideOnly(Side.CLIENT)
public class ElysianTileEntityPortalRenderer extends TileEntitySpecialRenderer
{
	public void renderTileEntityPortalAt(ElysianTileEntityPortal tile)
	{
		/*if(bright > 0.0F)
        {
            Tessellator tessellator = Tessellator.instance;
            this.bindTextureByName("/mods/" + DefaultProps.modId + "/textures/misc/beam.png");
            glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, 10497.0F);
            glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, 10497.0F);
            glDisable(GL_LIGHTING);
            glDisable(GL_CULL_FACE);
            glDisable(GL_BLEND);
            glDepthMask(true);
            glEnable (GL_BLEND);
            glBlendFunc (GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
            float f2 = (float)tile.getWorldObj().getTotalWorldTime() + par8;
            float f3 = -f2 * 0.2F - (float)MathHelper.floor_float(-f2 * 0.1F);
            float radius = (float)(Math.sin(System.currentTimeMillis()/180) + 6);//radius
            double d3 = (double)f2 * 0.025D * (1.0D - (double)((byte)radius & 1) * 2.5D);
            tessellator.startDrawingQuads();
            int alpha = (int)(Math.sin(System.currentTimeMillis()/250)*80 + 175);
			tessellator.setColorRGBA(255, 255, 255, alpha);
            double d4 = (double)radius * 0.2D;
            double d5 = 0.5D + Math.cos(d3 + 2.356194490192345D) * d4;
            double d6 = 0.5D + Math.sin(d3 + 2.356194490192345D) * d4;
            double d7 = 0.5D + Math.cos(d3 + (Math.PI / 4D)) * d4;
            double d8 = 0.5D + Math.sin(d3 + (Math.PI / 4D)) * d4;
            double d9 = 0.5D + Math.cos(d3 + 3.9269908169872414D) * d4;
            double d10 = 0.5D + Math.sin(d3 + 3.9269908169872414D) * d4;
            double d11 = 0.5D + Math.cos(d3 + 5.497787143782138D) * d4;
            double d12 = 0.5D + Math.sin(d3 + 5.497787143782138D) * d4;
            double d13 = (double)(256.0F * bright);
            double d14 = 0.0D;
            double d15 = 1.0D;
            double d16 = (double)(-1.0F + f3);
            double d17 = (double)(256.0F * bright) * (0.5D / d4) + d16;
            tessellator.addVertexWithUV(x + d5, y + d13, z + d6, d15, d17);
            tessellator.addVertexWithUV(x + d5, y, z + d6, d15, d16);
            tessellator.addVertexWithUV(x + d7, y, z + d8, d14, d16);
            tessellator.addVertexWithUV(x + d7, y + d13, z + d8, d14, d17);
            tessellator.addVertexWithUV(x + d11, y + d13, z + d12, d15, d17);
            tessellator.addVertexWithUV(x + d11, y, z + d12, d15, d16);
            tessellator.addVertexWithUV(x + d9, y, z + d10, d14, d16);
            tessellator.addVertexWithUV(x + d9, y + d13, z + d10, d14, d17);
            tessellator.addVertexWithUV(x + d7, y + d13, z + d8, d15, d17);
            tessellator.addVertexWithUV(x + d7, y, z + d8, d15, d16);
            tessellator.addVertexWithUV(x + d11, y, z + d12, d14, d16);
            tessellator.addVertexWithUV(x + d11, y + d13, z + d12, d14, d17);
            tessellator.addVertexWithUV(x + d9, y + d13, z + d10, d15, d17);
            tessellator.addVertexWithUV(x + d9, y, z + d10, d15, d16);
            tessellator.addVertexWithUV(x + d5, y, z + d6, d14, d16);
            tessellator.addVertexWithUV(x + d5, y + d13, z + d6, d14, d17);
            tessellator.draw();
            glEnable(GL_BLEND);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
            glDepthMask(false);
            tessellator.startDrawingQuads();
            tessellator.setColorRGBA(255, 255, 255, 32);
            double d18 = 0.2D;
            double d19 = 0.2D;
            double d20 = 0.8D;
            double d21 = 0.2D;
            double d22 = 0.2D;
            double d23 = 0.8D;
            double d24 = 0.8D;
            double d25 = 0.8D;
            double d26 = (double)(256.0F * bright);
            double d27 = 0.0D;
            double d28 = 1.0D;
            double d29 = (double)(-1.0F + f3);
            double d30 = (double)(256.0F * bright) + d29;
            tessellator.addVertexWithUV(x + d18, y + d26, z + d19, d28, d30);
            tessellator.addVertexWithUV(x + d18, y, z + d19, d28, d29);
            tessellator.addVertexWithUV(x + d20, y, z + d21, d27, d29);
            tessellator.addVertexWithUV(x + d20, y + d26, z + d21, d27, d30);
            tessellator.addVertexWithUV(x + d24, y + d26, z + d25, d28, d30);
            tessellator.addVertexWithUV(x + d24, y, z + d25, d28, d29);
            tessellator.addVertexWithUV(x + d22, y, z + d23, d27, d29);
            tessellator.addVertexWithUV(x + d22, y + d26, z + d23, d27, d30);
            tessellator.addVertexWithUV(x + d20, y + d26, z + d21, d28, d30);
            tessellator.addVertexWithUV(x + d20, y, z + d21, d28, d29);
            tessellator.addVertexWithUV(x + d24, y, z + d25, d27, d29);
            tessellator.addVertexWithUV(x + d24, y + d26, z + d25, d27, d30);
            tessellator.addVertexWithUV(x + d22, y + d26, z + d23, d28, d30);
            tessellator.addVertexWithUV(x + d22, y, z + d23, d28, d29);
            tessellator.addVertexWithUV(x + d18, y, z + d19, d27, d29);
            tessellator.addVertexWithUV(x + d18, y + d26, z + d19, d27, d30);
            tessellator.draw();
            glEnable(GL_LIGHTING);
            glEnable(GL_TEXTURE_2D);
            glDepthMask(true);
        }*/
		
		glDisable(GL_LIGHTING);
		glDisable(GL_CULL_FACE);
		glEnable (GL_BLEND);
        glBlendFunc (GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
        glColor4f(1, 1, 1, tile.alpha);
		this.bindTextureByName("/mods/" + DefaultProps.modId + "/textures/misc/beam.png");
		
		glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex3d(-tile.radius, -0.5, -tile.radius);
			glTexCoord2f(0, 100);
			glVertex3d(-tile.radius, 99.5, -tile.radius);
			glTexCoord2f(1, 100);
			glVertex3d(-tile.radius, 99.5, tile.radius);
			glTexCoord2f(1, 0);
			glVertex3d(-tile.radius, -0.5, tile.radius);
		glEnd();
		
		glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex3d(tile.radius, -0.5, -tile.radius);
			glTexCoord2f(0, 100);
			glVertex3d(tile.radius, 99.5, -tile.radius);
			glTexCoord2f(1, 100);
			glVertex3d(tile.radius, 99.5, tile.radius);
			glTexCoord2f(1, 0);
			glVertex3d(tile.radius, -0.5, tile.radius);
		glEnd();
	
		glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex3d(-tile.radius, -0.5, -tile.radius);
			glTexCoord2f(0, 100);
			glVertex3d(-tile.radius, 99.5, -tile.radius);
			glTexCoord2f(1, 100);
			glVertex3d(tile.radius, 99.5, -tile.radius);
			glTexCoord2f(1, 0);
			glVertex3d(tile.radius, -0.5, -tile.radius);
		glEnd();
	
		glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex3d(-tile.radius, -0.5, tile.radius);
			glTexCoord2f(0, 100);
			glVertex3d(-tile.radius, 99.5, tile.radius);
			glTexCoord2f(1, 100);
			glVertex3d(tile.radius, 99.5, tile.radius);
			glTexCoord2f(1, 0);
			glVertex3d(tile.radius, -0.5, tile.radius);
		glEnd();
		
		glDisable(GL_BLEND);
		glEnable(GL_CULL_FACE);
		glEnable(GL_LIGHTING);
	}
	
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float par8)
	{
		ElysianTileEntityPortal portalTile = (ElysianTileEntityPortal) tile;
		
		if(portalTile.canstay)
		{
			glPushMatrix();
				glTranslated(x+0.5, y, z+0.5);
				glRotatef(portalTile.rotation, 0, 1, 0);
				this.renderTileEntityPortalAt(portalTile);
			glPopMatrix();
		}
	}
}
