package mods.elysium.render;

import static org.lwjgl.opengl.GL11.*;

import java.util.Random;

import javax.swing.Renderer;

import org.lwjgl.opengl.GL11;

import com.google.common.primitives.SignedBytes;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;

import mods.elysium.Elysium;
import mods.elysium.entity.tileentity.TileFancyWorkbench;
import mods.elysium.model.ModelWorkPillar;

public class RenderFancyWorkbench extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler
{
	public static ModelBase model = new ModelBase()
	{
		
	};
	
	private ModelRenderer bottom;
	private ModelRenderer pillarbottom;
	private ModelRenderer pillar;
	private ModelRenderer pillartop;
	private ModelRenderer top;
	
    private Random random;
	private RenderBlocks renderBlocks;
    private RenderItem itemRenderer;
	
	public RenderFancyWorkbench()
	{
		random = new Random();
        renderBlocks = new RenderBlocks();
        itemRenderer = new RenderItem() {
            @Override
            public byte getMiniBlockCount(ItemStack stack) {
                return SignedBytes.saturatedCast(Math.min(stack.stackSize / 32, 15) + 1);
            }
            @Override
            public byte getMiniItemCount(ItemStack stack) {
                return SignedBytes.saturatedCast(Math.min(stack.stackSize / 32, 7) + 1);
            }
            @Override
            public boolean shouldBob() {
                return false;
            }
            @Override
            public boolean shouldSpreadItems() {
                return false;
            }
        };
        itemRenderer.setRenderManager(RenderManager.instance);
        
        
		model.textureWidth = 128;
		model.textureHeight = 64;
		
		bottom = new ModelRenderer(model, 0, 0);
		bottom.addBox(-8F, -1F, -8F, 16, 2, 16);
		bottom.setRotationPoint(0F, 23F, 0F);
		bottom.setTextureSize(128, 64);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		pillarbottom = new ModelRenderer(model, 0, 18);
		pillarbottom.addBox(-7F, 0F, -7F, 14, 1, 14);
		pillarbottom.setRotationPoint(0F, 21F, 0F);
		pillarbottom.setTextureSize(128, 64);
		pillarbottom.mirror = true;
		setRotation(pillarbottom, 0F, 0F, 0F);
		pillar = new ModelRenderer(model, 0, 33);
		pillar.addBox(-6F, 0F, -6F, 12, 10, 12);
		pillar.setRotationPoint(0F, 11F, 0F);
		pillar.setTextureSize(128, 64);
		pillar.mirror = true;
		setRotation(pillar, 0F, 0F, 0F);
		pillartop = new ModelRenderer(model, 0, 18);
		pillartop.addBox(-7F, 0F, -7F, 14, 1, 14);
		pillartop.setRotationPoint(0F, 10F, 0F);
		pillartop.setTextureSize(128, 64);
		pillartop.mirror = true;
		setRotation(pillartop, 0F, 0F, 0F);
		top = new ModelRenderer(model, 64, 0);
		top.addBox(-8F, -1F, -8F, 16, 2, 16);
		top.setRotationPoint(0F, 9F, 0F);
		top.setTextureSize(128, 64);
		top.mirror = true;
		setRotation(top, 0F, 0F, 0F);
	}
	
	public void render(TileEntity tileentity, float f)
	{
		bottom.render(f);
		pillarbottom.render(f);
		pillar.render(f);
		pillartop.render(f);
		top.render(f);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f)
	{
		TileFancyWorkbench tile = (TileFancyWorkbench)tileentity;
		
		float timeD = (float) (360.0 * (double) (System.currentTimeMillis() & 0x3FFFL) / (double) 0x3FFFL);
        float blockScale = 0.60F;
        
        float shiftX;
        float shiftY;
        float shiftZ;
        int shift = 0;
        
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
        	
            	if(tile.inventory[j + i * 3] != null){
        	
		        	glPushMatrix();
		        		glTranslated(x, y+1, z);
		
		    			glTranslatef(4 / 16F + i * 4 / 16F, 0.1F, 4 / 16F + j * 4 / 16F);
		        			
						glRotatef(timeD, 0.0F, 1.0F, 0.0F);
				     	glScalef(blockScale, blockScale, blockScale);
				     
				     	EntityItem customitem = new EntityItem(tileEntityRenderer.worldObj);
				     	customitem.hoverStart = 0f;
				     
				     	customitem.setEntityItemStack(new ItemStack(tile.inventory[j + i * 3].getItem(), tile.inventory[j + i * 3].stackSize));
			     
				     	itemRenderer.doRenderItem(customitem, 0, 0, 0, 0, 0);
				
			     	glPopMatrix();
            	}
            }
        }
         glPushMatrix();
			glTranslated(x+0.5D, y+1.5D, z+0.5D);
			glScaled(0.0625D, 0.0625D, 0.0625D);
			glRotatef(180F, 1F, 0F, 0F);
			this.bindTextureByName("/mods/elysium/textures/models/elysianWorkpillar.png");
			render(tile, 1F);
		glPopMatrix();
		
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		glPushMatrix();
			glTranslated(0, 1.0D, 0);
			glScaled(0.0625D, 0.0625D, 0.0625D);
			glRotatef(180F, 1F, 0F, 0F);
			FMLClientHandler.instance().getClient().renderEngine.bindTexture("/mods/elysium/textures/models/elysianWorkpillar.png");
			render(null, 1F);
		glPopMatrix();
	}

	@Override //No TileEntity here can't use
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		
		return false;
	}
	
	@Override
	public boolean shouldRender3DInInventory() {
		return true;
	}

	@Override
	public int getRenderId() {
		return Elysium.fancyWorkbenchRenderID;
	}
}
