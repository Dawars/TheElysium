package mods.elysium.render;

import mods.elysium.Elysium;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderFancyTank extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler {

	public static ModelBase model = new ModelBase() { };
	private ModelRenderer pillarbottom;
	private ModelRenderer pillartop;
	private ModelRenderer Corner1;
	private ModelRenderer Corner2;
	private ModelRenderer Corner3;
	private ModelRenderer Corner4;
	private ModelRenderer BottomTank;
	private ModelRenderer TopTank;
	private ModelRenderer GlassPane1;
	private ModelRenderer GlassPane2;
	private ModelRenderer GlassPane4;
	private ModelRenderer GlassPane3;
	private ModelRenderer Valve;
  
	public RenderFancyTank()
	{
		model.textureWidth = 128;
		model.textureHeight = 64;
    
		pillarbottom = new ModelRenderer(model, 0, 18);
		pillarbottom.addBox(-7F, 0F, -7F, 14, 1, 14);
		pillarbottom.setRotationPoint(0F, 21F, 0F);
		pillarbottom.setTextureSize(128, 64);
		pillarbottom.mirror = true;
		setRotation(pillarbottom, 0F, 0F, 0F);
		pillartop = new ModelRenderer(model, 0, 18);
		pillartop.addBox(-7F, 0F, -7F, 14, 1, 14);
		pillartop.setRotationPoint(0F, 10F, 0F);
		pillartop.setTextureSize(128, 64);
		pillartop.mirror = true;
		setRotation(pillartop, 0F, 0F, 0F);
		Corner1 = new ModelRenderer(model, 50, 0);
		Corner1.addBox(5F, 0F, 5F, 2, 10, 2);
		Corner1.setRotationPoint(0F, 11F, 0F);
		Corner1.setTextureSize(128, 64);
		Corner1.mirror = true;
		setRotation(Corner1, 0F, 0F, 0F);
		Corner1.mirror = false;
		Corner2 = new ModelRenderer(model, 50, 0);
		Corner2.addBox(-7F, 0F, -7F, 2, 10, 2);
		Corner2.setRotationPoint(0F, 11F, 0F);
		Corner2.setTextureSize(128, 64);
		Corner2.mirror = true;
		setRotation(Corner2, 0F, 0F, 0F);
		Corner2.mirror = false;
		Corner3 = new ModelRenderer(model, 50, 0);
		Corner3.addBox(5F, 0F, -7F, 2, 10, 2);
		Corner3.setRotationPoint(0F, 11F, 0F);
		Corner3.setTextureSize(128, 64);
		Corner3.mirror = true;
		setRotation(Corner3, 0F, 0F, 0F);
		Corner4 = new ModelRenderer(model, 50, 0);
		Corner4.addBox(-7F, 0F, 5F, 2, 10, 2);
		Corner4.setRotationPoint(0F, 11F, 0F);
		Corner4.setTextureSize(128, 64);
		Corner4.mirror = true;
		setRotation(Corner4, 0F, 0F, 0F);
		BottomTank = new ModelRenderer(model, 64, 36);
		BottomTank.addBox(-8F, 0F, -8F, 16, 2, 16);
		BottomTank.setRotationPoint(0F, 22F, 0F);
		BottomTank.setTextureSize(128, 64);
		BottomTank.mirror = true;
		setRotation(BottomTank, 0F, 0F, 0F);
		BottomTank.mirror = false;
		TopTank = new ModelRenderer(model, 64, 18);
		TopTank.addBox(-8F, 0F, -8F, 16, 2, 16);
		TopTank.setRotationPoint(0F, 8F, 0F);
		TopTank.setTextureSize(128, 64);
		TopTank.mirror = true;
		setRotation(TopTank, 0F, 0F, 0F);
		GlassPane1 = new ModelRenderer(model, 108, 54);
		GlassPane1.addBox(-5F, -4F, -6F, 10, 10, 0);
		GlassPane1.setRotationPoint(0F, 15F, 0F);
		GlassPane1.setTextureSize(128, 64);
		GlassPane1.mirror = true;
		setRotation(GlassPane1, 0F, 0F, 0F);
		GlassPane2 = new ModelRenderer(model, 108, 54);
		GlassPane2.addBox(-5F, -4F, 6F, 10, 10, 0);
		GlassPane2.setRotationPoint(0F, 15F, 0F);
		GlassPane2.setTextureSize(128, 64);
		GlassPane2.mirror = true;
		setRotation(GlassPane2, 0F, 0F, 0F);
		GlassPane4 = new ModelRenderer(model, 108, 54);
		GlassPane4.addBox(-5F, -4F, 6F, 10, 10, 0);
		GlassPane4.setRotationPoint(0F, 15F, 0F);
		GlassPane4.setTextureSize(128, 64);
		GlassPane4.mirror = true;
		setRotation(GlassPane4, 0F, 1.570796F, 0F);
		GlassPane3 = new ModelRenderer(model, 108, 54);
		GlassPane3.addBox(-5F, -4F, -6F, 10, 10, 0);
		GlassPane3.setRotationPoint(0F, 15F, 0F);
		GlassPane3.setTextureSize(128, 64);
		GlassPane3.mirror = true;
		setRotation(GlassPane3, 0F, 1.570796F, 0F);
		Valve = new ModelRenderer(model, 0, 0);
		Valve.addBox(6F, -3F, -3F, 2, 6, 6);
		Valve.setRotationPoint(0F, 15F, 0F);
		Valve.setTextureSize(128, 64);
		Valve.mirror = true;
		setRotation(Valve, 0F, 0F, 0F);
	}
  
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		pillarbottom.render(f5);
		pillartop.render(f5);
	    Corner1.render(f5);
	    Corner2.render(f5);
	    Corner3.render(f5);
	    Corner4.render(f5);
	    BottomTank.render(f5);
	    TopTank.render(f5);
	    GlassPane1.render(f5);
	    GlassPane2.render(f5);
	    GlassPane4.render(f5);
	    GlassPane3.render(f5);
	    Valve.render(f5);
 	 }
  
	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
	    model.rotateAngleY = y;
	    model.rotateAngleZ = z;
	}
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return true;
	}

	@Override
	public int getRenderId() {
		return Elysium.fancyTankRenderID;
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float f) {
		
	}

}
