package mods.elysium.render;

import static org.lwjgl.opengl.GL11.*;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.entity.Entity;

import mods.elysium.model.ModelWorkPillar;

public class RenderTileEntityShrinePillar extends TileEntitySpecialRenderer
{
	ModelWorkPillar model = new ModelWorkPillar();
	
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f)
	{
		glPushMatrix();
			glTranslated(x+0.5D, y+1.5D, z+0.5D);
			glScaled(0.0625D, 0.0625D, 0.0625D);
			glRotatef(180F, 1F, 0F, 0F);
			this.bindTextureByName("/mods/elysium/textures/models/elysianWorkpillar.png");
			model.render(tile, 1F);
		glPopMatrix();
	}
}
