package mods.elysium.render;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLSync;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
//import fb.jma.model.obj.OBJLoader;
//import fb.jma.model.obj.OBJModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.obj.ObjModelLoader;
import net.minecraftforge.client.model.obj.WavefrontObject;

import mods.elysium.BlockItemIDs;
import mods.elysium.entity.EntityDrachma;

@SideOnly(Side.CLIENT)
public class RenderDrachmaOBJ extends Render
{
	//WavefrontObject model;
//	OBJModel model;
	
	public RenderDrachmaOBJ()
	{
		/*try
		{
			this.model = new WavefrontObject("drachma.obj", new URL("file:"+Minecraft.getMinecraftDir()+"/mods/drachma.obj"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}*/
//		this.model = OBJLoader.loadOBJModel("/resources/elysium/drachma.obj");//TODO
	}
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float f, float f1)
	{
        this.renderDrachma((EntityDrachma)entity, x, y, z, f, f1);
	}

	private void renderDrachma(EntityDrachma entity, double x, double y, double z, float f, float f1)
	{
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		
		GL11.glTranslated(x, y, z);
		GL11.glScalef(.02F, .02F, .02F);
		GL11.glRotatef(entity.rotationYaw, 0F, 1F, 0F);
		GL11.glRotatef(90F, 1F, 0F, 0F);
		
//		FMLClientHandler.instance().getClient().renderEngine.bindTexture("/mods/elysium/textures/model/drachma.png");FIXME
//		this.model.renderAll();
		
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation("/mods/elysium/textures/model/drachma.png");
	}
}
