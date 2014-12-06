package me.dawars.CraftingPillars.renderer;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import me.dawars.CraftingPillars.CraftingPillars;
import me.dawars.CraftingPillars.tiles.TileEntityFurnacePillar;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.crafting.FurnaceRecipes;

public class RenderFurnacePillar extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler
{
	private ResourceLocation TEXTURE_FURNACEPILLAR;

	public static ModelBase model = new ModelBase()
	{

	};

	private ModelRenderer CraftingBottom;
	private ModelRenderer CraftingBotSlab;
	private ModelRenderer Pillar1;
	private ModelRenderer WorkbenchSlab;
	private ModelRenderer WorkbenchTop;
	private ModelRenderer Pillar2;
	private ModelRenderer Pillar3;
	private ModelRenderer Pillar4;

	private ModelRenderer BunnyTail1;
	private ModelRenderer BunnyTail2;
	private ModelRenderer BunnyTail3;
	private ModelRenderer BunnyEar1;
	private ModelRenderer BunnyEar2;

	private ModelRenderer Icicle1A;
	private ModelRenderer Icicle1B;
	private ModelRenderer Icicle1C;
	private ModelRenderer Icicle2A;
	private ModelRenderer Icicle2C;
	private ModelRenderer Icicle2B;
	private ModelRenderer Icicle3A;
	private ModelRenderer Icicle3B;
	private ModelRenderer Icicle3C;
	private ModelRenderer Icicle3D;
	private ModelRenderer Icicle4A;
	private ModelRenderer Icicle4B;

	private RenderingHelper.ItemRender itemRenderer;
	private RenderingHelper.ItemRender resultRenderer;

	public RenderFurnacePillar()
	{
		if (CraftingPillars.winter)
			this.TEXTURE_FURNACEPILLAR = new ResourceLocation(CraftingPillars.ID + ":textures/models/furnacePillarFrozen.png");
		else
			this.TEXTURE_FURNACEPILLAR = new ResourceLocation(CraftingPillars.ID + ":textures/models/furnacePillar.png");

		this.itemRenderer = new RenderingHelper.ItemRender(false, true);
		this.resultRenderer = new RenderingHelper.ItemRender(false, false);

		model.textureWidth = 128;
		model.textureHeight = 64;

		this.CraftingBottom = new ModelRenderer(model, 0, 0);
		this.CraftingBottom.addBox(0F, 0F, 0F, 16, 2, 16);
		this.CraftingBottom.setRotationPoint(-8F, 22F, -8F);
		this.CraftingBottom.setTextureSize(128, 64);
		this.CraftingBottom.mirror = true;
		this.setRotation(this.CraftingBottom, 0F, 0F, 0F);
		this.CraftingBotSlab = new ModelRenderer(model, 0, 18);
		this.CraftingBotSlab.addBox(0F, 0F, 0F, 14, 1, 14);
		this.CraftingBotSlab.setRotationPoint(-7F, 21F, -7F);
		this.CraftingBotSlab.setTextureSize(128, 64);
		this.CraftingBotSlab.mirror = true;
		this.setRotation(this.CraftingBotSlab, 0F, 0F, 0F);
		this.Pillar1 = new ModelRenderer(model, 2, 43);
		this.Pillar1.addBox(0F, 0F, 0F, 2, 10, 2);
		this.Pillar1.setRotationPoint(-6F, 11F, -6F);
		this.Pillar1.setTextureSize(128, 64);
		this.Pillar1.mirror = true;
		this.setRotation(this.Pillar1, 0F, 0F, 0F);
		this.WorkbenchSlab = new ModelRenderer(model, 0, 18);
		this.WorkbenchSlab.addBox(0F, 0F, 0F, 14, 1, 14);
		this.WorkbenchSlab.setRotationPoint(-7F, 10F, -7F);
		this.WorkbenchSlab.setTextureSize(128, 64);
		this.WorkbenchSlab.mirror = true;
		this.setRotation(this.WorkbenchSlab, 0F, 0F, 0F);
		this.WorkbenchTop = new ModelRenderer(model, 64, 0);
		this.WorkbenchTop.addBox(0F, 0F, 0F, 16, 2, 16);
		this.WorkbenchTop.setRotationPoint(-8F, 8F, -8F);
		this.WorkbenchTop.setTextureSize(128, 64);
		this.WorkbenchTop.mirror = true;
		this.setRotation(this.WorkbenchTop, 0F, 0F, 0F);
		this.Pillar2 = new ModelRenderer(model, 2, 43);
		this.Pillar2.addBox(-2F, 0F, -2F, 2, 10, 2);
		this.Pillar2.setRotationPoint(6F, 11F, -6F);
		this.Pillar2.setTextureSize(128, 64);
		this.Pillar2.mirror = true;
		this.setRotation(this.Pillar2, 0F, 1.570796F, 0F);
		this.Pillar3 = new ModelRenderer(model, 2, 43);
		this.Pillar3.addBox(0F, 0F, 0F, 2, 10, 2);
		this.Pillar3.setRotationPoint(-6F, 11F, 6F);
		this.Pillar3.setTextureSize(128, 64);
		this.Pillar3.mirror = true;
		this.setRotation(this.Pillar3, 0F, 1.570796F, 0F);
		this.Pillar4 = new ModelRenderer(model, 2, 43);
		this.Pillar4.addBox(-2F, 0F, -2F, 2, 10, 2);
		this.Pillar4.setRotationPoint(6F, 11F, 6F);
		this.Pillar4.setTextureSize(128, 64);
		this.Pillar4.mirror = true;
		this.setRotation(this.Pillar4, 0F, 0F, 0F);

		if (CraftingPillars.easter)
		{
			BunnyTail1 = new ModelRenderer(model, 0, 35);
			BunnyTail1.addBox(0F, 0F, 0F, 2, 4, 2);
			BunnyTail1.setRotationPoint(-1F, 18F, 7F);
			BunnyTail1.setTextureSize(128, 64);
			BunnyTail1.mirror = true;
			setRotation(BunnyTail1, 0F, 0F, 0F);
			BunnyTail2 = new ModelRenderer(model, 0, 33);
			BunnyTail2.addBox(0F, 0F, 0F, 4, 2, 2);
			BunnyTail2.setRotationPoint(-2F, 19F, 7F);
			BunnyTail2.setTextureSize(128, 64);
			BunnyTail2.mirror = true;
			setRotation(BunnyTail2, 0F, 0F, 0F);
			BunnyTail3 = new ModelRenderer(model, 0, 36);
			BunnyTail3.addBox(0F, 0F, 0F, 2, 2, 4);
			BunnyTail3.setRotationPoint(-1F, 19F, 6F);
			BunnyTail3.setTextureSize(128, 64);
			BunnyTail3.mirror = true;
			setRotation(BunnyTail3, 0F, 0F, 0F);
			BunnyEar1 = new ModelRenderer(model, 1, 18);
			BunnyEar1.addBox(-1.5F, -9F, -1F, 3, 10, 1);
			BunnyEar1.setRotationPoint(3.5F, 9F, 9F);
			BunnyEar1.setTextureSize(128, 64);
			BunnyEar1.mirror = true;
			setRotation(BunnyEar1, 0F, 0F, 0F);
			BunnyEar2 = new ModelRenderer(model, 1, 18);
			BunnyEar2.addBox(-1.5F, -9F, -1F, 3, 10, 1);
			BunnyEar2.setRotationPoint(-3.5F, 9F, 9F);
			BunnyEar2.setTextureSize(128, 64);
			BunnyEar2.mirror = true;
			setRotation(BunnyEar2, 0F, 0F, 0F);
		}

		this.Icicle1A = new ModelRenderer(model, 122, 38);
		this.Icicle1A.addBox(0F, 0F, 0F, 2, 1, 1);
		this.Icicle1A.setRotationPoint(5F, 11F, 6F);
		this.Icicle1A.setTextureSize(128, 64);
		this.Icicle1A.mirror = true;
		this.setRotation(this.Icicle1A, 0F, 0F, 0F);
		this.Icicle1B = new ModelRenderer(model, 122, 40);
		this.Icicle1B.addBox(0F, 0F, 0F, 1, 2, 1);
		this.Icicle1B.setRotationPoint(6F, 12F, 6F);
		this.Icicle1B.setTextureSize(128, 64);
		this.Icicle1B.mirror = true;
		this.setRotation(this.Icicle1B, 0F, 0F, 0F);
		this.Icicle1C = new ModelRenderer(model, 116, 52);
		this.Icicle1C.addBox(0F, 0F, 0F, 1, 1, 2);
		this.Icicle1C.setRotationPoint(6F, 11F, 4F);
		this.Icicle1C.setTextureSize(128, 64);
		this.Icicle1C.mirror = true;
		this.setRotation(this.Icicle1C, 0F, 0F, 0F);
		this.Icicle2A = new ModelRenderer(model, 122, 60);
		this.Icicle2A.addBox(0F, 0F, 0F, 1, 2, 2);
		this.Icicle2A.setRotationPoint(6F, 11F, -6F);
		this.Icicle2A.setTextureSize(128, 64);
		this.Icicle2A.mirror = true;
		this.setRotation(this.Icicle2A, 0F, 0F, 0F);
		this.Icicle2B = new ModelRenderer(model, 122, 38);
		this.Icicle2B.addBox(0F, 0F, 0F, 2, 1, 1);
		this.Icicle2B.setRotationPoint(5F, 11F, -7F);
		this.Icicle2B.setTextureSize(128, 64);
		this.Icicle2B.mirror = true;
		this.setRotation(this.Icicle2B, 0F, 0F, 0F);
		this.Icicle2C = new ModelRenderer(model, 122, 44);
		this.Icicle2C.addBox(0F, 0F, 0F, 1, 1, 1);
		this.Icicle2C.setRotationPoint(6F, 13F, -6F);
		this.Icicle2C.setTextureSize(128, 64);
		this.Icicle2C.mirror = true;
		this.setRotation(this.Icicle2C, 0F, 0F, 0F);
		this.Icicle3A = new ModelRenderer(model, 106, 50);
		this.Icicle3A.addBox(0F, 0F, 0F, 1, 1, 3);
		this.Icicle3A.setRotationPoint(-7F, 11F, -7F);
		this.Icicle3A.setTextureSize(128, 64);
		this.Icicle3A.mirror = true;
		this.setRotation(this.Icicle3A, 0F, 0F, 0F);
		this.Icicle3B = new ModelRenderer(model, 101, 50);
		this.Icicle3B.addBox(0F, 0F, 0F, 1, 1, 2);
		this.Icicle3B.setRotationPoint(-7F, 12F, -7F);
		this.Icicle3B.setTextureSize(128, 64);
		this.Icicle3B.mirror = true;
		this.setRotation(this.Icicle3B, 0F, 0F, 0F);
		this.Icicle3C = new ModelRenderer(model, 106, 50);
		this.Icicle3C.addBox(0F, 0F, 0F, 1, 1, 1);
		this.Icicle3C.setRotationPoint(-6F, 11F, -7F);
		this.Icicle3C.setTextureSize(128, 64);
		this.Icicle3C.mirror = true;
		this.setRotation(this.Icicle3C, 0F, 0F, 0F);
		this.Icicle3D = new ModelRenderer(model, 106, 46);
		this.Icicle3D.addBox(0F, 0F, 0F, 1, 1, 1);
		this.Icicle3D.setRotationPoint(-7F, 13F, -7F);
		this.Icicle3D.setTextureSize(128, 64);
		this.Icicle3D.mirror = true;
		this.setRotation(this.Icicle3D, 0F, 0F, 0F);
		this.Icicle4A = new ModelRenderer(model, 122, 35);
		this.Icicle4A.addBox(0F, 0F, 0F, 1, 1, 1);
		this.Icicle4A.setRotationPoint(-7F, 11F, 6F);
		this.Icicle4A.setTextureSize(128, 64);
		this.Icicle4A.mirror = true;
		this.setRotation(this.Icicle4A, 0F, 0F, 0F);
		this.Icicle4B = new ModelRenderer(model, 117, 43);
		this.Icicle4B.addBox(0F, 0F, 0F, 1, 2, 1);
		this.Icicle4B.setRotationPoint(-6F, 11F, 6F);
		this.Icicle4B.setTextureSize(128, 64);
		this.Icicle4B.mirror = true;
		this.setRotation(this.Icicle4B, 0F, 0F, 0F);
	}

	public void render(float f)
	{
		if (CraftingPillars.winter)
		{
			this.Icicle1A.render(f);
			this.Icicle1B.render(f);
			this.Icicle1C.render(f);
			this.Icicle2A.render(f);
			this.Icicle2C.render(f);
			this.Icicle2B.render(f);
			this.Icicle3A.render(f);
			this.Icicle3B.render(f);
			this.Icicle3C.render(f);
			this.Icicle3D.render(f);
			this.Icicle4A.render(f);
			this.Icicle4B.render(f);
		}

		this.CraftingBottom.render(f);
		this.CraftingBotSlab.render(f);
		this.Pillar1.render(f);
		this.WorkbenchSlab.render(f);
		this.WorkbenchTop.render(f);
		this.Pillar2.render(f);
		this.Pillar3.render(f);
		this.Pillar4.render(f);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f)
	{
		glPushMatrix();
		glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
		glRotatef(180F, 1F, 0F, 0F);

		Minecraft.getMinecraft().renderEngine.bindTexture(this.TEXTURE_FURNACEPILLAR);
		this.render(0.0625F);

		if (CraftingPillars.easter)
		{
			glRotatef(90F * (tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord) - 2), 0F, 1F, 0F);

			f = 0.0625F;
			BunnyTail1.render(f);
			BunnyTail2.render(f);
			BunnyTail3.render(f);
			BunnyEar1.render(f);
			BunnyEar2.render(f);
		}
		glPopMatrix();

		TileEntityFurnacePillar pillarTile = (TileEntityFurnacePillar) tile;
		EntityItem citem = new EntityItem(tile.getWorldObj());

		glPushMatrix();

		glTranslated(x + 0.5D, y, z + 0.5D);
		glRotatef(90F * (tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord) - 2), 0F, 1F, 0F);

		// Input
		if (pillarTile.getStackInSlot(0) != null)
		{
			glPushMatrix();
			citem.hoverStart = 0F;
			citem.setEntityItemStack(pillarTile.getStackInSlot(0));
			this.resultRenderer.render(citem, 0F, 1.125F, 0F, false);
			glPopMatrix();
		}

		// Output
		if (pillarTile.getStackInSlot(2) != null)
		{
			glPushMatrix();
			glTranslatef(0F, 1.75F, 0F);
			citem.hoverStart = 0F;
			citem.setEntityItemStack(pillarTile.getStackInSlot(2));
			this.resultRenderer.render(citem, 0F, 0F, 0F, false);
			glPopMatrix();
		}

		// processed item
		if (pillarTile.canSmelt() && pillarTile.burnTime > 0)
		{
			glPushMatrix();
			glTranslatef(0F, 1.75F - pillarTile.cookTime / 150F, 0F);
			citem.hoverStart = 0F;
			citem.setEntityItemStack(FurnaceRecipes.smelting().getSmeltingResult(pillarTile.getStackInSlot(0)));
			this.resultRenderer.render(citem, 0.01F, 0F, 0.01F, false);
			glPopMatrix();
		}

		// Fuel
		if (pillarTile.getStackInSlot(1) != null)
		{
			citem.hoverStart = 0F;
			citem.setEntityItemStack(pillarTile.getStackInSlot(1));
			this.itemRenderer.render(citem, 0F, 0.3F, 0F, false);
		}
		glPopMatrix();

		if (pillarTile.showNum)
		{
			glPushMatrix();
			glTranslated(x + 0.5D, y, z + 0.5D);

			if (pillarTile.getStackInSlot(0) != null)
			{
				glDisable(GL_LIGHTING);
				RenderingHelper
						.renderFloatingTextWithBackground(0, 1.425F, 0, 0.4F, "" + pillarTile.getStackInSlot(0).stackSize, Color.white.getRGB(), new Color(0F, 0F, 0F, 0.5F));
				glEnable(GL_LIGHTING);
			}
			if (pillarTile.getStackInSlot(1) != null)
			{
				glDisable(GL_LIGHTING);
				RenderingHelper.renderFloatingTextWithBackground(0, 0.7F, 0, 0.4F, "" + pillarTile.getStackInSlot(1).stackSize, Color.white.getRGB(), new Color(0F, 0F, 0F, 0.5F));
				glEnable(GL_LIGHTING);
			}
			if (pillarTile.getStackInSlot(2) != null)
			{
				glDisable(GL_LIGHTING);
				RenderingHelper.renderFloatingTextWithBackground(0, 2.15F, 0, 0.4F, "" + pillarTile.getStackInSlot(2).stackSize, Color.white.getRGB(), new Color(0F, 0F, 0F, 0.5F));
				glEnable(GL_LIGHTING);
			}
			glPopMatrix();
		}
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		glPushMatrix();
		glPushAttrib(GL_ENABLE_BIT);
		glEnable(GL_DEPTH_TEST);
		glTranslated(0, 1.0D, 0);
		glRotatef(180F, 1F, 0F, 0F);
		glRotatef(90F, 0F, 1F, 0F);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(this.TEXTURE_FURNACEPILLAR);
		this.render(0.0625F);
		glPopAttrib();
		glPopMatrix();
	}

	@Override
	// No TileEntity here can't use
			public
			boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{

		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int i)
	{
		return true;
	}

	@Override
	public int getRenderId()
	{
		return CraftingPillars.furnacePillarRenderID;
	}
}
