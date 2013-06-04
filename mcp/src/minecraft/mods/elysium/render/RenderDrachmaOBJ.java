package mods.elysium.render;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

import mods.elysium.DefaultProps;
import mods.elysium.api.obj.*;
import mods.elysium.entity.ElysianEntityDrachma;

public class RenderDrachmaOBJ extends Render
{
	OBJModel model;
	
	public RenderDrachmaOBJ()
	{
		try
		{
			this.model = OBJLoader.loadModel(Minecraft.getMinecraftDir()+"/mods/square.obj");
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float f, float f1)
	{
        this.renderDrachma((ElysianEntityDrachma)entity, x, y, z, f, f1);
	}

	private void renderDrachma(ElysianEntityDrachma entity, double x, double y, double z, float f, float f1)
	{
		GL11.glPushMatrix();
		//GL11.glDisable(GL11.GL_LIGHTING);
		
		GL11.glTranslated(x, y, z);
		GL11.glScalef(.225F, .25F, .25F);
		GL11.glRotatef(180F, 1F, 0F, 0F);
		GL11.glRotatef(entity.rotationYaw, 0F, 1F, 0F);
		
		//FMLClientHandler.instance().getClient().renderEngine.bindTexture("/mods/elysium/textures/misc/Drachma.png");
		this.model.render();

		//GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
        
	}
}
