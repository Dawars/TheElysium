package mods.elysium.render;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLSync;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fb.jma.model.obj.OBJLoader;
import fb.jma.model.obj.OBJModel;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.obj.ObjModelLoader;
import net.minecraftforge.client.model.obj.WavefrontObject;

import mods.elysium.DefaultProps;
import mods.elysium.Elysium;
import mods.elysium.entity.EntityDrachma;

@SideOnly(Side.CLIENT)
public class CrystalBlockRendererOBJ implements ISimpleBlockRenderingHandler
{
	private static final ResourceLocation TEXTURE_CRYSTAL = new ResourceLocation("/mods/elysium/textures/models/crystal.png");
	private static final ResourceLocation TEXTURE_TERRAIN = new ResourceLocation("/terrain.png");
	//WavefrontObject model;
	OBJModel model;
	
	public CrystalBlockRendererOBJ()
	{
//		this.model = OBJLoader.loadOBJModel(Minecraft.getMinecraftDir()+"/resources/elysium/crystal_block.obj");
	}

	private void render(double x, double y, double z)
	{
		GL11.glPushMatrix();
//		GL11.glDisable(GL11.GL_LIGHTING);
		
		GL11.glTranslated(x, y, z);
		GL11.glScalef(.025F, .025F, .025F);
//		GL11.glRotatef(90F, 1F, 0F, 0F);
		
		FMLClientHandler.instance().getClient().renderEngine.func_110577_a(TEXTURE_CRYSTAL);
//		this.model.renderAll();
		
//		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
		FMLClientHandler.instance().getClient().renderEngine.func_110577_a(TEXTURE_TERRAIN);//FIXME

	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		render(0D, -0.45D, 0D);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		
		renderer.renderBlockAllFaces(Elysium.blockPalestone, x, y, z);
		GL11.glPushMatrix();
//		GL11.glDisable(GL11.GL_LIGHTING);
		
		GL11.glTranslated(x, y, z);
		GL11.glScalef(.025F, .025F, .025F);
//		GL11.glRotatef(90F, 1F, 0F, 0F);
		
		FMLClientHandler.instance().getClient().renderEngine.func_110577_a(TEXTURE_CRYSTAL);
		renderer.renderBlockAllFaces(Elysium.blockPalestone, x, y, z);
		//this.model.renderAll();
		
//		GL11.glEnable(GL11.GL_LIGHTING);
		FMLClientHandler.instance().getClient().renderEngine.func_110577_a(TEXTURE_TERRAIN);

		GL11.glPopMatrix();

		return false;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return true;
	}

	@Override
	public int getRenderId() {
		return Elysium.crystalBlockRenderID;
	}
}
