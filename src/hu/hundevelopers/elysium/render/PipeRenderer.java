package hu.hundevelopers.elysium.render;

import static org.lwjgl.opengl.GL11.*;
import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.block.ElysiumPipeBlock;
import hu.hundevelopers.elysium.tile.TilePipe;
import me.dawars.CraftingPillars.tiles.TileEntityAnvilPillar;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class PipeRenderer extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler
{

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
	{
		//item
		glPushMatrix();
		glPushAttrib(GL_ENABLE_BIT);
		glEnable(GL_DEPTH_TEST);
		glTranslated(0, 1.0D, 0);
//		glRotatef(180F, 1F, 0F, 0F);
		
//		FMLClientHandler.instance().getClient().renderEngine.bindTexture(this.TEXTURE_ANVILPILLAR);
		renderPipe(null, renderer);
		
		glPopAttrib();
		glPopMatrix();
	}

	private void renderPipe(TileEntity tile, RenderBlocks renderer)
	{
		
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		//connected pipes
		
		TilePipe pipe = (TilePipe)world.getTileEntity(x, y, z);
		
		glPushMatrix();
			glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
			glRotatef(180F, 1F, 0F, 0F);
			
//			Minecraft.getMinecraft().renderEngine.bindTexture(this.TEXTURE_ANVILPILLAR);
			renderPipe(pipe, renderer);
		glPopMatrix();
		return true;
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f)
	{
		//blobs
		System.out.println("pipe render");
		
		
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId)
	{
		return true;
	}

	@Override
	public int getRenderId()
	{
		return Elysium.pipeStoneReinderingID;
	}
}
