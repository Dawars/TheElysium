package mods.elysium.render;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import org.lwjgl.opengl.GL11;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.model.obj.ObjModelLoader;
import net.minecraftforge.client.model.obj.WavefrontObject;

import mods.elysium.DefaultProps;
import mods.elysium.api.obj.*;
import mods.elysium.entity.ElysianEntityDrachma;

public class RenderDrachmaOBJ extends Render
{
	WavefrontObject model;
	
	public RenderDrachmaOBJ()
	{
		try
		{
			this.model = new WavefrontObject("drachma2.obj", new URL("file:"+Minecraft.getMinecraftDir()+"/mods/drachma2.obj"));
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
		GL11.glDisable(GL11.GL_LIGHTING);
		
		GL11.glTranslated(x, y, z);
		GL11.glScalef(.005F, .005F, .005F);
		GL11.glRotatef(entity.rotationYaw, 0F, 1F, 0F);
		GL11.glRotatef(90F, 1F, 0F, 0F);
		
		FMLClientHandler.instance().getClient().renderEngine.bindTexture("/mods/elysium/textures/misc/drachma_diffuse.png");
		this.model.renderAll();
		
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
        
	}
}
