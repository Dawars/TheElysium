package mods.elysium.render;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

import org.lwjgl.opengl.GL11;

/**
 *
 * @author CovertJaguar <railcraft.wikispaces.com>
 */
public class LiquidRenderer {

//	private static Map<FluidStack, int[]> flowingRenderCache = new HashMap<FluidStack, int[]>();
//	private static Map<FluidStack, int[]> stillRenderCache = new HashMap<FluidStack, int[]>();
//	public static final int DISPLAY_STAGES = 100;
////	private static final BlockInterface FluidBlock = new BlockInterface();
//
//	public static class FluidTextureException extends RuntimeException {
//
//		private final FluidStack Fluid;
//
//		public FluidTextureException(FluidStack Fluid) {
//			super();
//			this.Fluid = Fluid;
//		}
//
//		@Override
//		public String getMessage() {
//			String FluidName = FluidDictionary.findFluidName(Fluid);
//			if (FluidName == null) {
//				FluidName = String.format("ID: %d Meta: %d", Fluid.itemID, Fluid.itemMeta);
//			}
//			return String.format("Fluid %s has no icon. Please contact the author of the mod the Fluid came from.", FluidName);
//		}
//	}
//
//	public static class FluidCanonException extends RuntimeException {
//
//		private final FluidStack Fluid;
//
//		public FluidCanonException(FluidStack Fluid) {
//			super();
//			this.Fluid = Fluid;
//		}
//
//		@Override
//		public String getMessage() {
//			String FluidName = FluidDictionary.findFluidName(Fluid);
//			if (FluidName == null) {
//				FluidName = String.format("ID: %d Meta: %d", Fluid.itemID, Fluid.itemMeta);
//			}
//			return String.format("Fluid %s is not registered with the Fluid Dictionary. Please contact the author of the mod the Fluid came from.", FluidName);
//		}
//	}
//
//	public static Icon getFluidTexture(FluidStack Fluid) {
//		if (Fluid == null || Fluid.itemID <= 0) {
//			return null;
//		}
//		FluidStack canon = Fluid.canonical();
//		if (canon == null) {
//			throw new FluidCanonException(Fluid);
//		}
//		Icon icon = canon.getRenderingIcon();
//		if (icon == null) {
//			throw new FluidTextureException(Fluid);
//		}
//		return icon;
//	}
//	
//	public static String getFluidSheet(FluidStack Fluid) {
//		if (Fluid == null || Fluid.itemID <= 0) {
//			return "/terrain.png";
//		}
//		FluidStack canon = Fluid.canonical();
//		if (canon == null) {
//			throw new FluidCanonException(Fluid);
//		}
//		return canon.getTextureSheet();
//	}
//
//	public static int[] getFluidDisplayLists(FluidStack Fluid, World world, boolean flowing) {
//		if (Fluid == null) {
//			return null;
//		}
//		Fluid = Fluid.canonical();
//		if(Fluid == null){
//			throw new FluidCanonException(Fluid);
//		}
//		Map<FluidStack, int[]> cache = flowing ? flowingRenderCache : stillRenderCache;
//		int[] diplayLists = cache.get(Fluid);
//		if (diplayLists != null) {
//			return diplayLists;
//		}
//
//		diplayLists = new int[DISPLAY_STAGES];
//
////		if (Fluid.itemID < Block.blocksList.length && Block.blocksList[Fluid.itemID] != null) {
////			FluidBlock.baseBlock = Block.blocksList[Fluid.itemID];
////			if (!flowing) {
////				FluidBlock.texture = getFluidTexture(Fluid);
////			}
////		} else if (Item.itemsList[Fluid.itemID] != null) {
////			FluidBlock.baseBlock = Block.waterStill;
////			FluidBlock.texture = getFluidTexture(Fluid);
////		} else {
////			return null;
////		}
//
//		cache.put(Fluid, diplayLists);
//
//		GL11.glDisable(GL11.GL_LIGHTING);
//		GL11.glDisable(GL11.GL_BLEND);
//		GL11.glDisable(GL11.GL_CULL_FACE);
//		ItemStack stack = Fluid.asItemStack();
//		int color = stack.getItem().getColorFromItemStack(stack, 0);
//		float c1 = (float) (color >> 16 & 255) / 255.0F;
//		float c2 = (float) (color >> 8 & 255) / 255.0F;
//		float c3 = (float) (color & 255) / 255.0F;
//		GL11.glColor4f(c1, c2, c3, 1);
//		for (int s = 0; s < DISPLAY_STAGES; ++s) {
//			diplayLists[s] = GLAllocation.generateDisplayLists(1);
//			GL11.glNewList(diplayLists[s], 4864 /*GL_COMPILE*/);
//
////			FluidBlock.minX = 0.01f;
////			FluidBlock.minY = 0;
////			FluidBlock.minZ = 0.01f;
////
////			FluidBlock.maxX = 0.99f;
////			FluidBlock.maxY = (float) s / (float) DISPLAY_STAGES;
////			FluidBlock.maxZ = 0.99f;
////
////			renderBlock(FluidBlock, world, 0, 0, 0, false, true);
//
//			GL11.glEndList();
//		}
//
//		GL11.glColor4f(1, 1, 1, 1);
//		GL11.glEnable(GL11.GL_CULL_FACE);
//		GL11.glEnable(GL11.GL_BLEND);
//		GL11.glEnable(GL11.GL_LIGHTING);
//
//		return diplayLists;
//	}   
//	
//	private static RenderBlocks renderBlocks = new RenderBlocks();
//
//	/*public static void renderBlock(BlockInterface block, IBlockAccess blockAccess, int i, int j, int k, boolean doLight, boolean doTessellating) {
//		float f = 0.5F;
//		float f1 = 1.0F;
//		float f2 = 0.8F;
//		float f3 = 0.6F;
//
//        renderBlocks.renderMaxX = block.maxX;
//        renderBlocks.renderMinX = block.minX;
//        renderBlocks.renderMaxY = block.maxY;
//        renderBlocks.renderMinY = block.minY;
//        renderBlocks.renderMaxZ = block.maxZ;
//        renderBlocks.renderMinZ = block.minZ;
//        renderBlocks.enableAO = false;
//
//
//		Tessellator tessellator = Tessellator.instance;
//
//		if (doTessellating) {
//			tessellator.startDrawingQuads();
//		}
//
//		float f4 = 0, f5 = 0;
//
//		if (doLight) {
//			f4 = block.getBlockBrightness(blockAccess, i, j, k);
//			f5 = block.getBlockBrightness(blockAccess, i, j, k);
//			if (f5 < f4) {
//				f5 = f4;
//			}
//			tessellator.setColorOpaque_F(f * f5, f * f5, f * f5);
//		}
//
//		renderBlocks.renderFaceYNeg(null, 0, 0, 0, block.getBlockTextureFromSide(0));
//
//		if (doLight) {
//			f5 = block.getBlockBrightness(blockAccess, i, j, k);
//			if (f5 < f4) {
//				f5 = f4;
//			}
//			tessellator.setColorOpaque_F(f1 * f5, f1 * f5, f1 * f5);
//		}
//
//		renderBlocks.renderFaceYPos(null, 0, 0, 0, block.getBlockTextureFromSide(1));
//
//		if (doLight) {
//			f5 = block.getBlockBrightness(blockAccess, i, j, k);
//			if (f5 < f4) {
//				f5 = f4;
//			}
//			tessellator.setColorOpaque_F(f2 * f5, f2 * f5, f2 * f5);
//		}
//
//		renderBlocks.renderFaceZNeg(null, 0, 0, 0, block.getBlockTextureFromSide(2));
//
//		if (doLight) {
//			f5 = block.getBlockBrightness(blockAccess, i, j, k);
//			if (f5 < f4) {
//				f5 = f4;
//			}
//			tessellator.setColorOpaque_F(f2 * f5, f2 * f5, f2 * f5);
//		}
//
//		renderBlocks.renderFaceZPos(null, 0, 0, 0, block.getBlockTextureFromSide(3));
//
//		if (doLight) {
//			f5 = block.getBlockBrightness(blockAccess, i, j, k);
//			if (f5 < f4) {
//				f5 = f4;
//			}
//			tessellator.setColorOpaque_F(f3 * f5, f3 * f5, f3 * f5);
//		}
//
//		renderBlocks.renderFaceXNeg(null, 0, 0, 0, block.getBlockTextureFromSide(4));
//
//		if (doLight) {
//			f5 = block.getBlockBrightness(blockAccess, i, j, k);
//			if (f5 < f4) {
//				f5 = f4;
//			}
//			tessellator.setColorOpaque_F(f3 * f5, f3 * f5, f3 * f5);
//		}
//
//		renderBlocks.renderFaceXPos(null, 0, 0, 0, block.getBlockTextureFromSide(5));
//
//		if (doTessellating) {
//			tessellator.draw();
//		}
//	}*/
}
