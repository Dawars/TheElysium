package clickme.nocubes.test;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import clickme.nocubes.NoCubes;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class SmoothBlockRenderer2 implements ISimpleBlockRenderingHandler {
    // BIG!!!!!
    
	@Override
	public int getRenderId() {
		return 0;
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess access, int x, int y, int z,
			Block block, int i, RenderBlocks renderer) {
		int color = block.colorMultiplier(renderer.blockAccess, x, y, z);
		float r = (float) (color >> 16 & 255) / 255.0F;
		float g = (float) (color >> 8 & 255) / 255.0F;
		float b = (float) (color & 255) / 255.0F;

		if (EntityRenderer.anaglyphEnable) {
			float r1 = (r * 30.0F + g * 59.0F + b * 11.0F) / 100.0F;
			float g1 = (r * 30.0F + g * 70.0F) / 100.0F;
			float b1 = (r * 30.0F + b * 70.0F) / 100.0F;
			r = r1;
			g = g1;
			b = b1;
		}

		return Minecraft.isAmbientOcclusionEnabled()
				&& block.getLightValue() == 0 ? renderStandardBlockWithAmbientOcclusion(
				renderer, block, x, y, z, r, g, b) : renderSmoothBlock(
				renderer, block, x, y, z, r, g, b);
	}

	public static float getSmoothBlockHeight(IBlockAccess access, Block block,
			int x, int y, int z) {
		long i = (long) (x * 3129871) ^ (long) y * 116129781L ^ (long) z;
		i = i * i * 42317861L + i * 11L;
		float f = ((float) (i >> 16 & 15L) / 15.0F - 0.5F) * 0.8F;

		if (block == Blocks.snow_layer) {
			f -= 0.75F;
		}

		boolean flag = false;

		for (int j = 0; j < 4; ++j) {
			int k = x - (j & 1);
			int l = z - (j >> 1 & 1);

			Material material = access.getBlock(k, y + 1, l).getMaterial();

			if (material != Material.air && material != Material.vine
					&& material != Material.plants) {
				return 1.0F;
			}

			Block block1 = access.getBlock(k, y, l);
			Material material1 = block1.getMaterial();

			if (material1 == Material.air || material1 == Material.vine
					|| material1 == Material.plants) {
				flag = true;
			} else if (block1 != block) {
				return 1.0F;
			}
		}

		return flag ? 0.0F : 1.0F + f;
	}

	public static float getSmoothBlockHeightForCollision(IBlockAccess access,
			Block block, int x, int y, int z) {
		boolean flag = false;

		for (int j = 0; j < 4; ++j) {
			int k = x - (j & 1);
			int l = z - (j >> 1 & 1);

			Material material = access.getBlock(k, y + 1, l).getMaterial();

			if (material != Material.air && material != Material.vine
					&& material != Material.plants && material != Material.water) {
				return 1.0F;
			}

			Block block1 = access.getBlock(k, y, l);
			Material material1 = block1.getMaterial();

			if (material1 == Material.air || material1 == Material.vine
					|| material1 == Material.plants || material1 == Material.water) {
				flag = true;
			} else if (block1 != block) {
				return 1.0F;
			}
		}

		return flag ? 0.5F : 1.0F;
	}

	public boolean renderSmoothBlock(RenderBlocks renderer, Block block, int x,
			int y, int z, float r, float g, float b) {
		Tessellator tessellator = Tessellator.instance;
		boolean rendered = false;

		float f3 = 0.5F;
		float f4 = 1.0F;
		float f5 = 0.8F;
		float f6 = 0.6F;
		float f7 = f4 * r;
		float f8 = f4 * g;
		float f9 = f4 * b;
		float f10 = f3 * r;
		float f11 = f5 * r;
		float f12 = f6 * r;
		float f13 = f3 * g;
		float f14 = f5 * g;
		float f15 = f6 * g;
		float f16 = f3 * b;
		float f17 = f5 * b;
		float f18 = f6 * b;

		float h0 = getSmoothBlockHeight(renderer.blockAccess, block, x, y, z);
		float h1 = getSmoothBlockHeight(renderer.blockAccess, block, x, y,
				z + 1);
		float h2 = getSmoothBlockHeight(renderer.blockAccess, block, x + 1, y,
				z + 1);
		float h3 = getSmoothBlockHeight(renderer.blockAccess, block, x + 1, y,
				z);

		if (renderer.renderAllFaces
				|| block.shouldSideBeRendered(renderer.blockAccess, x, y - 1,
						z, 0)) {
			tessellator.setBrightness(block.getMixedBrightnessForBlock(
					renderer.blockAccess, x, y - 1, z));
			tessellator.setColorOpaque_F(f10, f13, f16);

			IIcon icon = renderer.getBlockIcon(block, renderer.blockAccess, x,
					y, z, 0);
			double u0 = (double) icon.getMinU();
			double u1 = (double) icon.getMaxU();
			double v0 = (double) icon.getMinV();
			double v1 = (double) icon.getMaxV();
			double u2 = u1;
			double u3 = u0;
			double v2 = v0;
			double v3 = v1;

			double x0 = (double) x;
			double x1 = (double) x + 1.0D;
			double y0 = (double) y;
			double z0 = (double) z;
			double z1 = (double) z + 1.0D;

			tessellator.addVertexWithUV(x0, y0, z1, u3, v3);
			tessellator.addVertexWithUV(x0, y0, z0, u0, v0);
			tessellator.addVertexWithUV(x1, y0, z0, u2, v2);
			tessellator.addVertexWithUV(x1, y0, z1, u1, v1);

			rendered = true;
		}

		if (renderer.renderAllFaces
				|| block.shouldSideBeRendered(renderer.blockAccess, x, y + 1,
						z, 1)) {
			int brightness = block.getMixedBrightnessForBlock(
					renderer.blockAccess, x, y + 1, z);
			if (h0 < 1.0F) {
				brightness -= 4194304;
			}
			tessellator.setBrightness(brightness);
			tessellator.setColorOpaque_F(f7, f8, f9);

			IIcon icon = renderer.getBlockIcon(block, renderer.blockAccess, x,
					y, z, 1);
			double u0 = (double) icon.getMinU();
			double u1 = (double) icon.getMaxU();
			double v0 = (double) icon.getMinV();
			double v1 = (double) icon.getMaxV();
			double u2 = u1;
			double u3 = u0;
			double v2 = v0;
			double v3 = v1;

			double x0 = (double) x;
			double x1 = (double) x + 1.0D;
			double y0 = (double) y;
			double z0 = (double) z;
			double z1 = (double) z + 1.0D;

			tessellator.addVertexWithUV(x1, y0 + (double) h2, z1, u1, v1);
			tessellator.addVertexWithUV(x1, y0 + (double) h3, z0, u2, v2);
			tessellator.addVertexWithUV(x0, y0 + (double) h0, z0, u0, v0);
			tessellator.addVertexWithUV(x0, y0 + (double) h1, z1, u3, v3);

			rendered = true;
		}

		if (!(h0 == 0.0F && h3 == 0.0F)
				&& (renderer.renderAllFaces || block.shouldSideBeRendered(
						renderer.blockAccess, x, y, z - 1, 2))) {
			tessellator.setBrightness(block.getMixedBrightnessForBlock(
					renderer.blockAccess, x, y, z - 1));
			tessellator.setColorOpaque_F(f11, f14, f17);

			IIcon icon = renderer.getBlockIcon(block, renderer.blockAccess, x,
					y, z, 2);
			double u0 = (double) icon.getMinU();
			double u1 = (double) icon.getMaxU();
			double v0 = (double) icon.getMinV();
			double v1 = (double) icon.getMaxV();
			double u2 = u1;
			double u3 = u0;
			double v2 = v0;
			double v3 = v1;

			if (h3 == 0.0F) {
				v0 = v1;
			}

			double x0 = (double) x;
			double x1 = (double) x + 1.0D;
			double y0 = (double) y;
			double z0 = (double) z;

			tessellator.addVertexWithUV(x0, y0 + (double) h0, z0, u2, v2);
			tessellator.addVertexWithUV(x1, y0 + (double) h3, z0, u0, v0);
			tessellator.addVertexWithUV(x1, y0, z0, u3, v3);
			tessellator.addVertexWithUV(x0, y0, z0, u1, v1);

			rendered = true;
		}

		if (!(h1 == 0.0F && h2 == 0.0F)
				&& (renderer.renderAllFaces || block.shouldSideBeRendered(
						renderer.blockAccess, x, y, z + 1, 3))) {
			tessellator.setBrightness(block.getMixedBrightnessForBlock(
					renderer.blockAccess, x, y, z + 1));
			tessellator.setColorOpaque_F(f11, f14, f17);

			IIcon icon = renderer.getBlockIcon(block, renderer.blockAccess, x,
					y, z, 3);
			double u0 = (double) icon.getMinU();
			double u1 = (double) icon.getMaxU();
			double v0 = (double) icon.getMinV();
			double v1 = (double) icon.getMaxV();
			double u2 = u1;
			double u3 = u0;
			double v2 = v0;
			double v3 = v1;

			if (h2 == 0.0F) {
				v2 = v1;
			}

			double x0 = (double) x;
			double x1 = (double) x + 1.0D;
			double y0 = (double) y;
			double z1 = (double) z + 1.0D;

			tessellator.addVertexWithUV(x0, y0 + (double) h1, z1, u0, v0);
			tessellator.addVertexWithUV(x0, y0, z1, u3, v3);
			tessellator.addVertexWithUV(x1, y0, z1, u1, v1);
			tessellator.addVertexWithUV(x1, y0 + (double) h2, z1, u2, v2);

			rendered = true;
		}

		if (!(h0 == 0.0F && h1 == 0.0F)
				&& (renderer.renderAllFaces || block.shouldSideBeRendered(
						renderer.blockAccess, x - 1, y, z, 4))) {
			tessellator.setBrightness(block.getMixedBrightnessForBlock(
					renderer.blockAccess, x - 1, y, z));
			tessellator.setColorOpaque_F(f12, f15, f18);

			IIcon icon = renderer.getBlockIcon(block, renderer.blockAccess, x,
					y, z, 4);
			double u0 = (double) icon.getMinU();
			double u1 = (double) icon.getMaxU();
			double v0 = (double) icon.getMinV();
			double v1 = (double) icon.getMaxV();
			double u2 = u1;
			double u3 = u0;
			double v2 = v0;
			double v3 = v1;

			if (h0 == 0.0F) {
				u0 = u1;
			}

			double x0 = (double) x;
			double y0 = (double) y;
			double z0 = (double) z;
			double z1 = (double) z + 1.0D;

			tessellator.addVertexWithUV(x0, y0 + (double) h1, z1, u3, v3);
			tessellator.addVertexWithUV(x0, y0 + (double) h0, z0, u0, v0);
			tessellator.addVertexWithUV(x0, y0, z0, u2, v2);
			tessellator.addVertexWithUV(x0, y0, z1, u1, v1);

			rendered = true;
		}

		if (!(h2 == 0.0F && h3 == 0.0F)
				&& (renderer.renderAllFaces || block.shouldSideBeRendered(
						renderer.blockAccess, x + 1, y, z, 5))) {
			tessellator.setBrightness(block.getMixedBrightnessForBlock(
					renderer.blockAccess, x + 1, y, z));
			tessellator.setColorOpaque_F(f12, f15, f18);

			IIcon icon = renderer.getBlockIcon(block, renderer.blockAccess, x,
					y, z, 5);
			double u0 = (double) icon.getMinU();
			double u1 = (double) icon.getMaxU();
			double v0 = (double) icon.getMinV();
			double v1 = (double) icon.getMaxV();
			double u2 = u1;
			double u3 = u0;
			double v2 = v0;
			double v3 = v1;

			if (h2 == 0.0F) {
				v0 = v1;
			}

			double x1 = (double) x + 1.0D;
			double y0 = (double) y;
			double z0 = (double) z;
			double z1 = (double) z + 1.0D;

			tessellator.addVertexWithUV(x1, y0, z1, u3, v3);
			tessellator.addVertexWithUV(x1, y0, z0, u1, v1);
			tessellator.addVertexWithUV(x1, y0 + (double) h3, z0, u2, v2);
			tessellator.addVertexWithUV(x1, y0 + (double) h2, z1, u0, v0);

			rendered = true;
		}

		return rendered;
	}

	public boolean renderStandardBlockWithAmbientOcclusion(
			RenderBlocks renderer, Block block, int x, int y, int z, float r,
			float g, float b) {
		Tessellator tessellator = Tessellator.instance;
		boolean rendered = false;

		float h0 = getSmoothBlockHeight(renderer.blockAccess, block, x, y, z);
		float h1 = getSmoothBlockHeight(renderer.blockAccess, block, x, y,
				z + 1);
		float h2 = getSmoothBlockHeight(renderer.blockAccess, block, x + 1, y,
				z + 1);
		float h3 = getSmoothBlockHeight(renderer.blockAccess, block, x + 1, y,
				z);

		if (renderer.renderAllFaces
				|| block.shouldSideBeRendered(renderer.blockAccess, x, y - 1,
						z, 0)) {
			renderer.aoBrightnessXYNN = block.getMixedBrightnessForBlock(
					renderer.blockAccess, x - 1, y - 1, z);
			renderer.aoBrightnessYZNN = block.getMixedBrightnessForBlock(
					renderer.blockAccess, x, y - 1, z - 1);
			renderer.aoBrightnessYZNP = block.getMixedBrightnessForBlock(
					renderer.blockAccess, x, y - 1, z + 1);
			renderer.aoBrightnessXYPN = block.getMixedBrightnessForBlock(
					renderer.blockAccess, x + 1, y - 1, z);
			renderer.aoLightValueScratchXYNN = renderer.blockAccess.getBlock(
					x - 1, y - 1, z).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchYZNN = renderer.blockAccess.getBlock(x,
					y - 1, z - 1).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchYZNP = renderer.blockAccess.getBlock(x,
					y - 1, z + 1).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchXYPN = renderer.blockAccess.getBlock(
					x + 1, y - 1, z).getAmbientOcclusionLightValue();
			boolean flag2 = renderer.blockAccess.getBlock(x + 1, y - 2, z)
					.getCanBlockGrass();
			boolean flag3 = renderer.blockAccess.getBlock(x - 1, y - 2, z)
					.getCanBlockGrass();
			boolean flag4 = renderer.blockAccess.getBlock(x, y - 2, z + 1)
					.getCanBlockGrass();
			boolean flag5 = renderer.blockAccess.getBlock(x, y - 2, z - 1)
					.getCanBlockGrass();
			if (!flag5 && !flag3) {
				renderer.aoLightValueScratchXYZNNN = renderer.aoLightValueScratchXYNN;
				renderer.aoBrightnessXYZNNN = renderer.aoBrightnessXYNN;
			} else {
				renderer.aoLightValueScratchXYZNNN = renderer.blockAccess
						.getBlock(x - 1, y - 1, z - 1)
						.getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZNNN = block.getMixedBrightnessForBlock(
						renderer.blockAccess, x - 1, y - 1, z - 1);
			}
			if (!flag4 && !flag3) {
				renderer.aoLightValueScratchXYZNNP = renderer.aoLightValueScratchXYNN;
				renderer.aoBrightnessXYZNNP = renderer.aoBrightnessXYNN;
			} else {
				renderer.aoLightValueScratchXYZNNP = renderer.blockAccess
						.getBlock(x - 1, y - 1, z + 1)
						.getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZNNP = block.getMixedBrightnessForBlock(
						renderer.blockAccess, x - 1, y - 1, z + 1);
			}
			if (!flag5 && !flag2) {
				renderer.aoLightValueScratchXYZPNN = renderer.aoLightValueScratchXYPN;
				renderer.aoBrightnessXYZPNN = renderer.aoBrightnessXYPN;
			} else {
				renderer.aoLightValueScratchXYZPNN = renderer.blockAccess
						.getBlock(x + 1, y - 1, z - 1)
						.getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZPNN = block.getMixedBrightnessForBlock(
						renderer.blockAccess, x + 1, y - 1, z - 1);
			}
			if (!flag4 && !flag2) {
				renderer.aoLightValueScratchXYZPNP = renderer.aoLightValueScratchXYPN;
				renderer.aoBrightnessXYZPNP = renderer.aoBrightnessXYPN;
			} else {
				renderer.aoLightValueScratchXYZPNP = renderer.blockAccess
						.getBlock(x + 1, y - 1, z + 1)
						.getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZPNP = block.getMixedBrightnessForBlock(
						renderer.blockAccess, x + 1, y - 1, z + 1);
			}
			int i1 = block.getMixedBrightnessForBlock(renderer.blockAccess, x,
					y - 1, z);
			float f7 = renderer.blockAccess.getBlock(x, y - 1, z)
					.getAmbientOcclusionLightValue();
			float f3 = (renderer.aoLightValueScratchXYZNNP
					+ renderer.aoLightValueScratchXYNN
					+ renderer.aoLightValueScratchYZNP + f7) / 4.0F;
			float f6 = (renderer.aoLightValueScratchYZNP + f7
					+ renderer.aoLightValueScratchXYZPNP + renderer.aoLightValueScratchXYPN) / 4.0F;
			float f5 = (f7 + renderer.aoLightValueScratchYZNN
					+ renderer.aoLightValueScratchXYPN + renderer.aoLightValueScratchXYZPNN) / 4.0F;
			float f4 = (renderer.aoLightValueScratchXYNN
					+ renderer.aoLightValueScratchXYZNNN + f7 + renderer.aoLightValueScratchYZNN) / 4.0F;
			renderer.brightnessTopLeft = renderer.getAoBrightness(
					renderer.aoBrightnessXYZNNP, renderer.aoBrightnessXYNN,
					renderer.aoBrightnessYZNP, i1);
			renderer.brightnessTopRight = renderer.getAoBrightness(
					renderer.aoBrightnessYZNP, renderer.aoBrightnessXYZPNP,
					renderer.aoBrightnessXYPN, i1);
			renderer.brightnessBottomRight = renderer.getAoBrightness(
					renderer.aoBrightnessYZNN, renderer.aoBrightnessXYPN,
					renderer.aoBrightnessXYZPNN, i1);
			renderer.brightnessBottomLeft = renderer.getAoBrightness(
					renderer.aoBrightnessXYNN, renderer.aoBrightnessXYZNNN,
					renderer.aoBrightnessYZNN, i1);

			renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = r * 0.5F;
			renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = g * 0.5F;
			renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = b * 0.5F;
			renderer.colorRedTopLeft *= f3;
			renderer.colorGreenTopLeft *= f3;
			renderer.colorBlueTopLeft *= f3;
			renderer.colorRedBottomLeft *= f4;
			renderer.colorGreenBottomLeft *= f4;
			renderer.colorBlueBottomLeft *= f4;
			renderer.colorRedBottomRight *= f5;
			renderer.colorGreenBottomRight *= f5;
			renderer.colorBlueBottomRight *= f5;
			renderer.colorRedTopRight *= f6;
			renderer.colorGreenTopRight *= f6;
			renderer.colorBlueTopRight *= f6;

			IIcon icon = renderer.getBlockIcon(block, renderer.blockAccess, x,
					y, z, 0);
			double u0 = (double) icon.getMinU();
			double u1 = (double) icon.getMaxU();
			double v0 = (double) icon.getMinV();
			double v1 = (double) icon.getMaxV();
			double u2 = u1;
			double u3 = u0;
			double v2 = v0;
			double v3 = v1;

			double x0 = (double) x;
			double x1 = (double) x + 1.0D;
			double y0 = (double) y;
			double z0 = (double) z;
			double z1 = (double) z + 1.0D;

			tessellator.setColorOpaque_F(renderer.colorRedTopLeft,
					renderer.colorGreenTopLeft, renderer.colorBlueTopLeft);
			tessellator.setBrightness(renderer.brightnessTopLeft);
			tessellator.addVertexWithUV(x0, y0, z1, u3, v3);
			tessellator
					.setColorOpaque_F(renderer.colorRedBottomLeft,
							renderer.colorGreenBottomLeft,
							renderer.colorBlueBottomLeft);
			tessellator.setBrightness(renderer.brightnessBottomLeft);
			tessellator.addVertexWithUV(x0, y0, z0, u0, v0);
			tessellator.setColorOpaque_F(renderer.colorRedBottomRight,
					renderer.colorGreenBottomRight,
					renderer.colorBlueBottomRight);
			tessellator.setBrightness(renderer.brightnessBottomRight);
			tessellator.addVertexWithUV(x1, y0, z0, u2, v2);
			tessellator.setColorOpaque_F(renderer.colorRedTopRight,
					renderer.colorGreenTopRight, renderer.colorBlueTopRight);
			tessellator.setBrightness(renderer.brightnessTopRight);
			tessellator.addVertexWithUV(x1, y0, z1, u1, v1);

			rendered = true;
		}

		if (renderer.renderAllFaces
				|| block.shouldSideBeRendered(renderer.blockAccess, x, y + 1,
						z, 1)) {
			renderer.aoBrightnessXYNP = block.getMixedBrightnessForBlock(
					renderer.blockAccess, x - 1, y + 1, z);
			renderer.aoBrightnessXYPP = block.getMixedBrightnessForBlock(
					renderer.blockAccess, x + 1, y + 1, z);
			renderer.aoBrightnessYZPN = block.getMixedBrightnessForBlock(
					renderer.blockAccess, x, y + 1, z - 1);
			renderer.aoBrightnessYZPP = block.getMixedBrightnessForBlock(
					renderer.blockAccess, x, y + 1, z + 1);
			renderer.aoLightValueScratchXYNP = renderer.blockAccess.getBlock(
					x - 1, y + 1, z).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchXYPP = renderer.blockAccess.getBlock(
					x + 1, y + 1, z).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchYZPN = renderer.blockAccess.getBlock(x,
					y + 1, z - 1).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchYZPP = renderer.blockAccess.getBlock(x,
					y + 1, z + 1).getAmbientOcclusionLightValue();
			boolean flag2 = renderer.blockAccess.getBlock(x + 1, y + 2, z)
					.getCanBlockGrass();
			boolean flag3 = renderer.blockAccess.getBlock(x - 1, y + 2, z)
					.getCanBlockGrass();
			boolean flag4 = renderer.blockAccess.getBlock(x, y + 2, z + 1)
					.getCanBlockGrass();
			boolean flag5 = renderer.blockAccess.getBlock(x, y + 2, z - 1)
					.getCanBlockGrass();
			if (!flag5 && !flag3) {
				renderer.aoLightValueScratchXYZNPN = renderer.aoLightValueScratchXYNP;
				renderer.aoBrightnessXYZNPN = renderer.aoBrightnessXYNP;
			} else {
				renderer.aoLightValueScratchXYZNPN = renderer.blockAccess
						.getBlock(x - 1, y + 1, z - 1)
						.getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZNPN = block.getMixedBrightnessForBlock(
						renderer.blockAccess, x - 1, y + 1, z - 1);
			}
			if (!flag5 && !flag2) {
				renderer.aoLightValueScratchXYZPPN = renderer.aoLightValueScratchXYPP;
				renderer.aoBrightnessXYZPPN = renderer.aoBrightnessXYPP;
			} else {
				renderer.aoLightValueScratchXYZPPN = renderer.blockAccess
						.getBlock(x + 1, y + 1, z - 1)
						.getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZPPN = block.getMixedBrightnessForBlock(
						renderer.blockAccess, x + 1, y + 1, z - 1);
			}
			if (!flag4 && !flag3) {
				renderer.aoLightValueScratchXYZNPP = renderer.aoLightValueScratchXYNP;
				renderer.aoBrightnessXYZNPP = renderer.aoBrightnessXYNP;
			} else {
				renderer.aoLightValueScratchXYZNPP = renderer.blockAccess
						.getBlock(x - 1, y + 1, z + 1)
						.getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZNPP = block.getMixedBrightnessForBlock(
						renderer.blockAccess, x - 1, y + 1, z + 1);
			}
			if (!flag4 && !flag2) {
				renderer.aoLightValueScratchXYZPPP = renderer.aoLightValueScratchXYPP;
				renderer.aoBrightnessXYZPPP = renderer.aoBrightnessXYPP;
			} else {
				renderer.aoLightValueScratchXYZPPP = renderer.blockAccess
						.getBlock(x + 1, y + 1, z + 1)
						.getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZPPP = block.getMixedBrightnessForBlock(
						renderer.blockAccess, x + 1, y + 1, z + 1);
			}
			int i1 = block.getMixedBrightnessForBlock(renderer.blockAccess, x,
					y + 1, z);
			float f7 = renderer.blockAccess.getBlock(x, y + 1, z)
					.getAmbientOcclusionLightValue();
			float f6 = (renderer.aoLightValueScratchXYZNPP
					+ renderer.aoLightValueScratchXYNP
					+ renderer.aoLightValueScratchYZPP + f7) / 4.0F;
			float f3 = (renderer.aoLightValueScratchYZPP + f7
					+ renderer.aoLightValueScratchXYZPPP + renderer.aoLightValueScratchXYPP) / 4.0F;
			float f4 = (f7 + renderer.aoLightValueScratchYZPN
					+ renderer.aoLightValueScratchXYPP + renderer.aoLightValueScratchXYZPPN) / 4.0F;
			float f5 = (renderer.aoLightValueScratchXYNP
					+ renderer.aoLightValueScratchXYZNPN + f7 + renderer.aoLightValueScratchYZPN) / 4.0F;
			renderer.brightnessTopRight = renderer.getAoBrightness(
					renderer.aoBrightnessXYZNPP, renderer.aoBrightnessXYNP,
					renderer.aoBrightnessYZPP, i1);
			renderer.brightnessTopLeft = renderer.getAoBrightness(
					renderer.aoBrightnessYZPP, renderer.aoBrightnessXYZPPP,
					renderer.aoBrightnessXYPP, i1);
			renderer.brightnessBottomLeft = renderer.getAoBrightness(
					renderer.aoBrightnessYZPN, renderer.aoBrightnessXYPP,
					renderer.aoBrightnessXYZPPN, i1);
			renderer.brightnessBottomRight = renderer.getAoBrightness(
					renderer.aoBrightnessXYNP, renderer.aoBrightnessXYZNPN,
					renderer.aoBrightnessYZPN, i1);

			renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = r;
			renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = g;
			renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = b;
			renderer.colorRedTopLeft *= f3;
			renderer.colorGreenTopLeft *= f3;
			renderer.colorBlueTopLeft *= f3;
			renderer.colorRedBottomLeft *= f4;
			renderer.colorGreenBottomLeft *= f4;
			renderer.colorBlueBottomLeft *= f4;
			renderer.colorRedBottomRight *= f5;
			renderer.colorGreenBottomRight *= f5;
			renderer.colorBlueBottomRight *= f5;
			renderer.colorRedTopRight *= f6;
			renderer.colorGreenTopRight *= f6;
			renderer.colorBlueTopRight *= f6;

			IIcon icon = renderer.getBlockIcon(block, renderer.blockAccess, x,
					y, z, 1);
			double u0 = (double) icon.getMinU();
			double u1 = (double) icon.getMaxU();
			double v0 = (double) icon.getMinV();
			double v1 = (double) icon.getMaxV();
			double u2 = u1;
			double u3 = u0;
			double v2 = v0;
			double v3 = v1;

			double x0 = (double) x;
			double x1 = (double) x + 1.0D;
			double y0 = (double) y;
			double z0 = (double) z;
			double z1 = (double) z + 1.0D;

			tessellator.setColorOpaque_F(renderer.colorRedTopLeft,
					renderer.colorGreenTopLeft, renderer.colorBlueTopLeft);
			tessellator.setBrightness(renderer.brightnessTopLeft);
			tessellator.addVertexWithUV(x1, y0 + (double) h2, z1, u1, v1);
			tessellator
					.setColorOpaque_F(renderer.colorRedBottomLeft,
							renderer.colorGreenBottomLeft,
							renderer.colorBlueBottomLeft);
			tessellator.setBrightness(renderer.brightnessBottomLeft);
			tessellator.addVertexWithUV(x1, y0 + (double) h3, z0, u2, v2);
			tessellator.setColorOpaque_F(renderer.colorRedBottomRight,
					renderer.colorGreenBottomRight,
					renderer.colorBlueBottomRight);
			tessellator.setBrightness(renderer.brightnessBottomRight);
			tessellator.addVertexWithUV(x0, y0 + (double) h0, z0, u0, v0);
			tessellator.setColorOpaque_F(renderer.colorRedTopRight,
					renderer.colorGreenTopRight, renderer.colorBlueTopRight);
			tessellator.setBrightness(renderer.brightnessTopRight);
			tessellator.addVertexWithUV(x0, y0 + (double) h1, z1, u3, v3);

			rendered = true;
		}

		if (!(h0 == 0.0F && h3 == 0.0F)
				&& (renderer.renderAllFaces || block.shouldSideBeRendered(
						renderer.blockAccess, x, y, z - 1, 2))) {
			renderer.aoLightValueScratchXZNN = renderer.blockAccess.getBlock(
					x - 1, y, z - 1).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchYZNN = renderer.blockAccess.getBlock(x,
					y - 1, z - 1).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchYZPN = renderer.blockAccess.getBlock(x,
					y + 1, z - 1).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchXZPN = renderer.blockAccess.getBlock(
					x + 1, y, z - 1).getAmbientOcclusionLightValue();
			renderer.aoBrightnessXZNN = block.getMixedBrightnessForBlock(
					renderer.blockAccess, x - 1, y, z - 1);
			renderer.aoBrightnessYZNN = block.getMixedBrightnessForBlock(
					renderer.blockAccess, x, y - 1, z - 1);
			renderer.aoBrightnessYZPN = block.getMixedBrightnessForBlock(
					renderer.blockAccess, x, y + 1, z - 1);
			renderer.aoBrightnessXZPN = block.getMixedBrightnessForBlock(
					renderer.blockAccess, x + 1, y, z - 1);
			boolean flag2 = renderer.blockAccess.getBlock(x + 1, y, z - 2)
					.getCanBlockGrass();
			boolean flag3 = renderer.blockAccess.getBlock(x - 1, y, z - 2)
					.getCanBlockGrass();
			boolean flag4 = renderer.blockAccess.getBlock(x, y + 1, z - 2)
					.getCanBlockGrass();
			boolean flag5 = renderer.blockAccess.getBlock(x, y - 1, z - 2)
					.getCanBlockGrass();
			if (!flag3 && !flag5) {
				renderer.aoLightValueScratchXYZNNN = renderer.aoLightValueScratchXZNN;
				renderer.aoBrightnessXYZNNN = renderer.aoBrightnessXZNN;
			} else {
				renderer.aoLightValueScratchXYZNNN = renderer.blockAccess
						.getBlock(x - 1, y - 1, z - 1)
						.getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZNNN = block.getMixedBrightnessForBlock(
						renderer.blockAccess, x - 1, y - 1, z - 1);
			}
			if (!flag3 && !flag4) {
				renderer.aoLightValueScratchXYZNPN = renderer.aoLightValueScratchXZNN;
				renderer.aoBrightnessXYZNPN = renderer.aoBrightnessXZNN;
			} else {
				renderer.aoLightValueScratchXYZNPN = renderer.blockAccess
						.getBlock(x - 1, y + 1, z - 1)
						.getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZNPN = block.getMixedBrightnessForBlock(
						renderer.blockAccess, x - 1, y + 1, z - 1);
			}
			if (!flag2 && !flag5) {
				renderer.aoLightValueScratchXYZPNN = renderer.aoLightValueScratchXZPN;
				renderer.aoBrightnessXYZPNN = renderer.aoBrightnessXZPN;
			} else {
				renderer.aoLightValueScratchXYZPNN = renderer.blockAccess
						.getBlock(x + 1, y - 1, z - 1)
						.getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZPNN = block.getMixedBrightnessForBlock(
						renderer.blockAccess, x + 1, y - 1, z - 1);
			}
			if (!flag2 && !flag4) {
				renderer.aoLightValueScratchXYZPPN = renderer.aoLightValueScratchXZPN;
				renderer.aoBrightnessXYZPPN = renderer.aoBrightnessXZPN;
			} else {
				renderer.aoLightValueScratchXYZPPN = renderer.blockAccess
						.getBlock(x + 1, y + 1, z - 1)
						.getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZPPN = block.getMixedBrightnessForBlock(
						renderer.blockAccess, x + 1, y + 1, z - 1);
			}
			int i1 = block.getMixedBrightnessForBlock(renderer.blockAccess, x,
					y, z - 1);
			float f7 = renderer.blockAccess.getBlock(x, y, z - 1)
					.getAmbientOcclusionLightValue();
			float f3 = (renderer.aoLightValueScratchXZNN
					+ renderer.aoLightValueScratchXYZNPN + f7 + renderer.aoLightValueScratchYZPN) / 4.0F;
			float f4 = (f7 + renderer.aoLightValueScratchYZPN
					+ renderer.aoLightValueScratchXZPN + renderer.aoLightValueScratchXYZPPN) / 4.0F;
			float f5 = (renderer.aoLightValueScratchYZNN + f7
					+ renderer.aoLightValueScratchXYZPNN + renderer.aoLightValueScratchXZPN) / 4.0F;
			float f6 = (renderer.aoLightValueScratchXYZNNN
					+ renderer.aoLightValueScratchXZNN
					+ renderer.aoLightValueScratchYZNN + f7) / 4.0F;
			renderer.brightnessTopLeft = renderer.getAoBrightness(
					renderer.aoBrightnessXZNN, renderer.aoBrightnessXYZNPN,
					renderer.aoBrightnessYZPN, i1);
			renderer.brightnessBottomLeft = renderer.getAoBrightness(
					renderer.aoBrightnessYZPN, renderer.aoBrightnessXZPN,
					renderer.aoBrightnessXYZPPN, i1);
			renderer.brightnessBottomRight = renderer.getAoBrightness(
					renderer.aoBrightnessYZNN, renderer.aoBrightnessXYZPNN,
					renderer.aoBrightnessXZPN, i1);
			renderer.brightnessTopRight = renderer.getAoBrightness(
					renderer.aoBrightnessXYZNNN, renderer.aoBrightnessXZNN,
					renderer.aoBrightnessYZNN, i1);

			renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = r * 0.8F;
			renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = g * 0.8F;
			renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = b * 0.8F;
			renderer.colorRedTopLeft *= f3;
			renderer.colorGreenTopLeft *= f3;
			renderer.colorBlueTopLeft *= f3;
			renderer.colorRedBottomLeft *= f4;
			renderer.colorGreenBottomLeft *= f4;
			renderer.colorBlueBottomLeft *= f4;
			renderer.colorRedBottomRight *= f5;
			renderer.colorGreenBottomRight *= f5;
			renderer.colorBlueBottomRight *= f5;
			renderer.colorRedTopRight *= f6;
			renderer.colorGreenTopRight *= f6;
			renderer.colorBlueTopRight *= f6;

			IIcon icon = renderer.getBlockIcon(block, renderer.blockAccess, x,
					y, z, 2);
			double u0 = (double) icon.getMinU();
			double u1 = (double) icon.getMaxU();
			double v0 = (double) icon.getMinV();
			double v1 = (double) icon.getMaxV();
			double u2 = u1;
			double u3 = u0;
			double v2 = v0;
			double v3 = v1;

			if (h3 == 0.0F) {
				v0 = v1;
			}

			double x0 = (double) x;
			double x1 = (double) x + 1.0D;
			double y0 = (double) y;
			double z0 = (double) z;

			tessellator.setColorOpaque_F(renderer.colorRedTopLeft,
					renderer.colorGreenTopLeft, renderer.colorBlueTopLeft);
			tessellator.setBrightness(renderer.brightnessTopLeft);
			tessellator.addVertexWithUV(x0, y0 + (double) h0, z0, u2, v2);
			tessellator
					.setColorOpaque_F(renderer.colorRedBottomLeft,
							renderer.colorGreenBottomLeft,
							renderer.colorBlueBottomLeft);
			tessellator.setBrightness(renderer.brightnessBottomLeft);
			tessellator.addVertexWithUV(x1, y0 + (double) h3, z0, u0, v0);
			tessellator.setColorOpaque_F(renderer.colorRedBottomRight,
					renderer.colorGreenBottomRight,
					renderer.colorBlueBottomRight);
			tessellator.setBrightness(renderer.brightnessBottomRight);
			tessellator.addVertexWithUV(x1, y0, z0, u3, v3);
			tessellator.setColorOpaque_F(renderer.colorRedTopRight,
					renderer.colorGreenTopRight, renderer.colorBlueTopRight);
			tessellator.setBrightness(renderer.brightnessTopRight);
			tessellator.addVertexWithUV(x0, y0, z0, u1, v1);

			rendered = true;
		}

		if (!(h1 == 0.0F && h2 == 0.0F)
				&& (renderer.renderAllFaces || block.shouldSideBeRendered(
						renderer.blockAccess, x, y, z + 1, 3))) {
			renderer.aoLightValueScratchXZNP = renderer.blockAccess.getBlock(
					x - 1, y, z + 1).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchXZPP = renderer.blockAccess.getBlock(
					x + 1, y, z + 1).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchYZNP = renderer.blockAccess.getBlock(x,
					y - 1, z + 1).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchYZPP = renderer.blockAccess.getBlock(x,
					y + 1, z + 1).getAmbientOcclusionLightValue();
			renderer.aoBrightnessXZNP = block.getMixedBrightnessForBlock(
					renderer.blockAccess, x - 1, y, z + 1);
			renderer.aoBrightnessXZPP = block.getMixedBrightnessForBlock(
					renderer.blockAccess, x + 1, y, z + 1);
			renderer.aoBrightnessYZNP = block.getMixedBrightnessForBlock(
					renderer.blockAccess, x, y - 1, z + 1);
			renderer.aoBrightnessYZPP = block.getMixedBrightnessForBlock(
					renderer.blockAccess, x, y + 1, z + 1);
			boolean flag2 = renderer.blockAccess.getBlock(x + 1, y, z + 2)
					.getCanBlockGrass();
			boolean flag3 = renderer.blockAccess.getBlock(x - 1, y, z + 2)
					.getCanBlockGrass();
			boolean flag4 = renderer.blockAccess.getBlock(x, y + 1, z + 2)
					.getCanBlockGrass();
			boolean flag5 = renderer.blockAccess.getBlock(x, y - 1, z + 2)
					.getCanBlockGrass();
			if (!flag3 && !flag5) {
				renderer.aoLightValueScratchXYZNNP = renderer.aoLightValueScratchXZNP;
				renderer.aoBrightnessXYZNNP = renderer.aoBrightnessXZNP;
			} else {
				renderer.aoLightValueScratchXYZNNP = renderer.blockAccess
						.getBlock(x - 1, y - 1, z + 1)
						.getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZNNP = block.getMixedBrightnessForBlock(
						renderer.blockAccess, x - 1, y - 1, z + 1);
			}
			if (!flag3 && !flag4) {
				renderer.aoLightValueScratchXYZNPP = renderer.aoLightValueScratchXZNP;
				renderer.aoBrightnessXYZNPP = renderer.aoBrightnessXZNP;
			} else {
				renderer.aoLightValueScratchXYZNPP = renderer.blockAccess
						.getBlock(x - 1, y + 1, z + 1)
						.getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZNPP = block.getMixedBrightnessForBlock(
						renderer.blockAccess, x - 1, y + 1, z + 1);
			}
			if (!flag2 && !flag5) {
				renderer.aoLightValueScratchXYZPNP = renderer.aoLightValueScratchXZPP;
				renderer.aoBrightnessXYZPNP = renderer.aoBrightnessXZPP;
			} else {
				renderer.aoLightValueScratchXYZPNP = renderer.blockAccess
						.getBlock(x + 1, y - 1, z + 1)
						.getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZPNP = block.getMixedBrightnessForBlock(
						renderer.blockAccess, x + 1, y - 1, z + 1);
			}
			if (!flag2 && !flag4) {
				renderer.aoLightValueScratchXYZPPP = renderer.aoLightValueScratchXZPP;
				renderer.aoBrightnessXYZPPP = renderer.aoBrightnessXZPP;
			} else {
				renderer.aoLightValueScratchXYZPPP = renderer.blockAccess
						.getBlock(x + 1, y + 1, z + 1)
						.getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZPPP = block.getMixedBrightnessForBlock(
						renderer.blockAccess, x + 1, y + 1, z + 1);
			}
			int i1 = block.getMixedBrightnessForBlock(renderer.blockAccess, x,
					y, z + 1);
			float f7 = renderer.blockAccess.getBlock(x, y, z + 1)
					.getAmbientOcclusionLightValue();
			float f3 = (renderer.aoLightValueScratchXZNP
					+ renderer.aoLightValueScratchXYZNPP + f7 + renderer.aoLightValueScratchYZPP) / 4.0F;
			float f6 = (f7 + renderer.aoLightValueScratchYZPP
					+ renderer.aoLightValueScratchXZPP + renderer.aoLightValueScratchXYZPPP) / 4.0F;
			float f5 = (renderer.aoLightValueScratchYZNP + f7
					+ renderer.aoLightValueScratchXYZPNP + renderer.aoLightValueScratchXZPP) / 4.0F;
			float f4 = (renderer.aoLightValueScratchXYZNNP
					+ renderer.aoLightValueScratchXZNP
					+ renderer.aoLightValueScratchYZNP + f7) / 4.0F;
			renderer.brightnessTopLeft = renderer.getAoBrightness(
					renderer.aoBrightnessXZNP, renderer.aoBrightnessXYZNPP,
					renderer.aoBrightnessYZPP, i1);
			renderer.brightnessTopRight = renderer.getAoBrightness(
					renderer.aoBrightnessYZPP, renderer.aoBrightnessXZPP,
					renderer.aoBrightnessXYZPPP, i1);
			renderer.brightnessBottomRight = renderer.getAoBrightness(
					renderer.aoBrightnessYZNP, renderer.aoBrightnessXYZPNP,
					renderer.aoBrightnessXZPP, i1);
			renderer.brightnessBottomLeft = renderer.getAoBrightness(
					renderer.aoBrightnessXYZNNP, renderer.aoBrightnessXZNP,
					renderer.aoBrightnessYZNP, i1);

			renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = r * 0.8F;
			renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = g * 0.8F;
			renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = b * 0.8F;
			renderer.colorRedTopLeft *= f3;
			renderer.colorGreenTopLeft *= f3;
			renderer.colorBlueTopLeft *= f3;
			renderer.colorRedBottomLeft *= f4;
			renderer.colorGreenBottomLeft *= f4;
			renderer.colorBlueBottomLeft *= f4;
			renderer.colorRedBottomRight *= f5;
			renderer.colorGreenBottomRight *= f5;
			renderer.colorBlueBottomRight *= f5;
			renderer.colorRedTopRight *= f6;
			renderer.colorGreenTopRight *= f6;
			renderer.colorBlueTopRight *= f6;

			IIcon icon = renderer.getBlockIcon(block, renderer.blockAccess, x,
					y, z, 3);
			double u0 = (double) icon.getMinU();
			double u1 = (double) icon.getMaxU();
			double v0 = (double) icon.getMinV();
			double v1 = (double) icon.getMaxV();
			double u2 = u1;
			double u3 = u0;
			double v2 = v0;
			double v3 = v1;

			if (h2 == 0.0F) {
				v2 = v1;
			}

			double x0 = (double) x;
			double x1 = (double) x + 1.0D;
			double y0 = (double) y;
			double z1 = (double) z + 1.0D;

			tessellator.setColorOpaque_F(renderer.colorRedTopLeft,
					renderer.colorGreenTopLeft, renderer.colorBlueTopLeft);
			tessellator.setBrightness(renderer.brightnessTopLeft);
			tessellator.addVertexWithUV(x0, y0 + (double) h1, z1, u0, v0);
			tessellator
					.setColorOpaque_F(renderer.colorRedBottomLeft,
							renderer.colorGreenBottomLeft,
							renderer.colorBlueBottomLeft);
			tessellator.setBrightness(renderer.brightnessBottomLeft);
			tessellator.addVertexWithUV(x0, y0, z1, u3, v3);
			tessellator.setColorOpaque_F(renderer.colorRedBottomRight,
					renderer.colorGreenBottomRight,
					renderer.colorBlueBottomRight);
			tessellator.setBrightness(renderer.brightnessBottomRight);
			tessellator.addVertexWithUV(x1, y0, z1, u1, v1);
			tessellator.setColorOpaque_F(renderer.colorRedTopRight,
					renderer.colorGreenTopRight, renderer.colorBlueTopRight);
			tessellator.setBrightness(renderer.brightnessTopRight);
			tessellator.addVertexWithUV(x1, y0 + (double) h2, z1, u2, v2);

			rendered = true;
		}

		if (!(h0 == 0.0F && h1 == 0.0F)
				&& (renderer.renderAllFaces || block.shouldSideBeRendered(
						renderer.blockAccess, x - 1, y, z, 4))) {
			renderer.aoLightValueScratchXYNN = renderer.blockAccess.getBlock(
					x - 1, y - 1, z).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchXZNN = renderer.blockAccess.getBlock(
					x - 1, y, z - 1).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchXZNP = renderer.blockAccess.getBlock(
					x - 1, y, z + 1).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchXYNP = renderer.blockAccess.getBlock(
					x - 1, y + 1, z).getAmbientOcclusionLightValue();
			renderer.aoBrightnessXYNN = block.getMixedBrightnessForBlock(
					renderer.blockAccess, x - 1, y - 1, z);
			renderer.aoBrightnessXZNN = block.getMixedBrightnessForBlock(
					renderer.blockAccess, x - 1, y, z - 1);
			renderer.aoBrightnessXZNP = block.getMixedBrightnessForBlock(
					renderer.blockAccess, x - 1, y, z + 1);
			renderer.aoBrightnessXYNP = block.getMixedBrightnessForBlock(
					renderer.blockAccess, x - 1, y + 1, z);
			boolean flag2 = renderer.blockAccess.getBlock(x - 2, y + 1, z)
					.getCanBlockGrass();
			boolean flag3 = renderer.blockAccess.getBlock(x - 2, y - 1, z)
					.getCanBlockGrass();
			boolean flag4 = renderer.blockAccess.getBlock(x - 2, y, z - 1)
					.getCanBlockGrass();
			boolean flag5 = renderer.blockAccess.getBlock(x - 2, y, z + 1)
					.getCanBlockGrass();
			if (!flag4 && !flag3) {
				renderer.aoLightValueScratchXYZNNN = renderer.aoLightValueScratchXZNN;
				renderer.aoBrightnessXYZNNN = renderer.aoBrightnessXZNN;
			} else {
				renderer.aoLightValueScratchXYZNNN = renderer.blockAccess
						.getBlock(x - 1, y - 1, z - 1)
						.getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZNNN = block.getMixedBrightnessForBlock(
						renderer.blockAccess, x - 1, y - 1, z - 1);
			}
			if (!flag5 && !flag3) {
				renderer.aoLightValueScratchXYZNNP = renderer.aoLightValueScratchXZNP;
				renderer.aoBrightnessXYZNNP = renderer.aoBrightnessXZNP;
			} else {
				renderer.aoLightValueScratchXYZNNP = renderer.blockAccess
						.getBlock(x - 1, y - 1, z + 1)
						.getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZNNP = block.getMixedBrightnessForBlock(
						renderer.blockAccess, x - 1, y - 1, z + 1);
			}
			if (!flag4 && !flag2) {
				renderer.aoLightValueScratchXYZNPN = renderer.aoLightValueScratchXZNN;
				renderer.aoBrightnessXYZNPN = renderer.aoBrightnessXZNN;
			} else {
				renderer.aoLightValueScratchXYZNPN = renderer.blockAccess
						.getBlock(x - 1, y + 1, z - 1)
						.getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZNPN = block.getMixedBrightnessForBlock(
						renderer.blockAccess, x - 1, y + 1, z - 1);
			}
			if (!flag5 && !flag2) {
				renderer.aoLightValueScratchXYZNPP = renderer.aoLightValueScratchXZNP;
				renderer.aoBrightnessXYZNPP = renderer.aoBrightnessXZNP;
			} else {
				renderer.aoLightValueScratchXYZNPP = renderer.blockAccess
						.getBlock(x - 1, y + 1, z + 1)
						.getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZNPP = block.getMixedBrightnessForBlock(
						renderer.blockAccess, x - 1, y + 1, z + 1);
			}
			int i1 = block.getMixedBrightnessForBlock(renderer.blockAccess,
					x - 1, y, z);
			float f7 = renderer.blockAccess.getBlock(x - 1, y, z)
					.getAmbientOcclusionLightValue();
			float f6 = (renderer.aoLightValueScratchXYNN
					+ renderer.aoLightValueScratchXYZNNP + f7 + renderer.aoLightValueScratchXZNP) / 4.0F;
			float f3 = (f7 + renderer.aoLightValueScratchXZNP
					+ renderer.aoLightValueScratchXYNP + renderer.aoLightValueScratchXYZNPP) / 4.0F;
			float f4 = (renderer.aoLightValueScratchXZNN + f7
					+ renderer.aoLightValueScratchXYZNPN + renderer.aoLightValueScratchXYNP) / 4.0F;
			float f5 = (renderer.aoLightValueScratchXYZNNN
					+ renderer.aoLightValueScratchXYNN
					+ renderer.aoLightValueScratchXZNN + f7) / 4.0F;
			renderer.brightnessTopRight = renderer.getAoBrightness(
					renderer.aoBrightnessXYNN, renderer.aoBrightnessXYZNNP,
					renderer.aoBrightnessXZNP, i1);
			renderer.brightnessTopLeft = renderer.getAoBrightness(
					renderer.aoBrightnessXZNP, renderer.aoBrightnessXYNP,
					renderer.aoBrightnessXYZNPP, i1);
			renderer.brightnessBottomLeft = renderer.getAoBrightness(
					renderer.aoBrightnessXZNN, renderer.aoBrightnessXYZNPN,
					renderer.aoBrightnessXYNP, i1);
			renderer.brightnessBottomRight = renderer.getAoBrightness(
					renderer.aoBrightnessXYZNNN, renderer.aoBrightnessXYNN,
					renderer.aoBrightnessXZNN, i1);

			renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = r * 0.6F;
			renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = g * 0.6F;
			renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = b * 0.6F;
			renderer.colorRedTopLeft *= f3;
			renderer.colorGreenTopLeft *= f3;
			renderer.colorBlueTopLeft *= f3;
			renderer.colorRedBottomLeft *= f4;
			renderer.colorGreenBottomLeft *= f4;
			renderer.colorBlueBottomLeft *= f4;
			renderer.colorRedBottomRight *= f5;
			renderer.colorGreenBottomRight *= f5;
			renderer.colorBlueBottomRight *= f5;
			renderer.colorRedTopRight *= f6;
			renderer.colorGreenTopRight *= f6;
			renderer.colorBlueTopRight *= f6;

			IIcon icon = renderer.getBlockIcon(block, renderer.blockAccess, x,
					y, z, 4);
			double u0 = (double) icon.getMinU();
			double u1 = (double) icon.getMaxU();
			double v0 = (double) icon.getMinV();
			double v1 = (double) icon.getMaxV();
			double u2 = u1;
			double u3 = u0;
			double v2 = v0;
			double v3 = v1;

			if (h0 == 0.0F) {
				u0 = u1;
			}

			double x0 = (double) x;
			double y0 = (double) y;
			double z0 = (double) z;
			double z1 = (double) z + 1.0D;

			tessellator.setColorOpaque_F(renderer.colorRedTopLeft,
					renderer.colorGreenTopLeft, renderer.colorBlueTopLeft);
			tessellator.setBrightness(renderer.brightnessTopLeft);
			tessellator.addVertexWithUV(x0, y0 + (double) h1, z1, u3, v3);
			tessellator
					.setColorOpaque_F(renderer.colorRedBottomLeft,
							renderer.colorGreenBottomLeft,
							renderer.colorBlueBottomLeft);
			tessellator.setBrightness(renderer.brightnessBottomLeft);
			tessellator.addVertexWithUV(x0, y0 + (double) h0, z0, u0, v0);
			tessellator.setColorOpaque_F(renderer.colorRedBottomRight,
					renderer.colorGreenBottomRight,
					renderer.colorBlueBottomRight);
			tessellator.setBrightness(renderer.brightnessBottomRight);
			tessellator.addVertexWithUV(x0, y0, z0, u2, v2);
			tessellator.setColorOpaque_F(renderer.colorRedTopRight,
					renderer.colorGreenTopRight, renderer.colorBlueTopRight);
			tessellator.setBrightness(renderer.brightnessTopRight);
			tessellator.addVertexWithUV(x0, y0, z1, u1, v1);

			rendered = true;
		}

		if (renderer.renderAllFaces
				|| block.shouldSideBeRendered(renderer.blockAccess, x + 1, y,
						z, 5)) {
			renderer.aoLightValueScratchXYPN = renderer.blockAccess.getBlock(
					x + 1, y - 1, z).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchXZPN = renderer.blockAccess.getBlock(
					x + 1, y, z - 1).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchXZPP = renderer.blockAccess.getBlock(
					x + 1, y, z + 1).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchXYPP = renderer.blockAccess.getBlock(
					x + 1, y + 1, z).getAmbientOcclusionLightValue();
			renderer.aoBrightnessXYPN = block.getMixedBrightnessForBlock(
					renderer.blockAccess, x + 1, y - 1, z);
			renderer.aoBrightnessXZPN = block.getMixedBrightnessForBlock(
					renderer.blockAccess, x + 1, y, z - 1);
			renderer.aoBrightnessXZPP = block.getMixedBrightnessForBlock(
					renderer.blockAccess, x + 1, y, z + 1);
			renderer.aoBrightnessXYPP = block.getMixedBrightnessForBlock(
					renderer.blockAccess, x + 1, y + 1, z);
			boolean flag2 = renderer.blockAccess.getBlock(x + 2, y + 1, z)
					.getCanBlockGrass();
			boolean flag3 = renderer.blockAccess.getBlock(x + 2, y - 1, z)
					.getCanBlockGrass();
			boolean flag4 = renderer.blockAccess.getBlock(x + 2, y, z + 1)
					.getCanBlockGrass();
			boolean flag5 = renderer.blockAccess.getBlock(x + 2, y, z - 1)
					.getCanBlockGrass();
			if (!flag3 && !flag5) {
				renderer.aoLightValueScratchXYZPNN = renderer.aoLightValueScratchXZPN;
				renderer.aoBrightnessXYZPNN = renderer.aoBrightnessXZPN;
			} else {
				renderer.aoLightValueScratchXYZPNN = renderer.blockAccess
						.getBlock(x + 1, y - 1, z - 1)
						.getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZPNN = block.getMixedBrightnessForBlock(
						renderer.blockAccess, x + 1, y - 1, z - 1);
			}
			if (!flag3 && !flag4) {
				renderer.aoLightValueScratchXYZPNP = renderer.aoLightValueScratchXZPP;
				renderer.aoBrightnessXYZPNP = renderer.aoBrightnessXZPP;
			} else {
				renderer.aoLightValueScratchXYZPNP = renderer.blockAccess
						.getBlock(x + 1, y - 1, z + 1)
						.getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZPNP = block.getMixedBrightnessForBlock(
						renderer.blockAccess, x + 1, y - 1, z + 1);
			}
			if (!flag2 && !flag5) {
				renderer.aoLightValueScratchXYZPPN = renderer.aoLightValueScratchXZPN;
				renderer.aoBrightnessXYZPPN = renderer.aoBrightnessXZPN;
			} else {
				renderer.aoLightValueScratchXYZPPN = renderer.blockAccess
						.getBlock(x + 1, y + 1, z - 1)
						.getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZPPN = block.getMixedBrightnessForBlock(
						renderer.blockAccess, x + 1, y + 1, z - 1);
			}
			if (!flag2 && !flag4) {
				renderer.aoLightValueScratchXYZPPP = renderer.aoLightValueScratchXZPP;
				renderer.aoBrightnessXYZPPP = renderer.aoBrightnessXZPP;
			} else {
				renderer.aoLightValueScratchXYZPPP = renderer.blockAccess
						.getBlock(x + 1, y + 1, z + 1)
						.getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZPPP = block.getMixedBrightnessForBlock(
						renderer.blockAccess, x + 1, y + 1, z + 1);
			}
			int i1 = block.getMixedBrightnessForBlock(renderer.blockAccess,
					x + 1, y, z);
			float f7 = renderer.blockAccess.getBlock(x + 1, y, z)
					.getAmbientOcclusionLightValue();
			float f3 = (renderer.aoLightValueScratchXYPN
					+ renderer.aoLightValueScratchXYZPNP + f7 + renderer.aoLightValueScratchXZPP) / 4.0F;
			float f4 = (renderer.aoLightValueScratchXYZPNN
					+ renderer.aoLightValueScratchXYPN
					+ renderer.aoLightValueScratchXZPN + f7) / 4.0F;
			float f5 = (renderer.aoLightValueScratchXZPN + f7
					+ renderer.aoLightValueScratchXYZPPN + renderer.aoLightValueScratchXYPP) / 4.0F;
			float f6 = (f7 + renderer.aoLightValueScratchXZPP
					+ renderer.aoLightValueScratchXYPP + renderer.aoLightValueScratchXYZPPP) / 4.0F;
			renderer.brightnessTopLeft = renderer.getAoBrightness(
					renderer.aoBrightnessXYPN, renderer.aoBrightnessXYZPNP,
					renderer.aoBrightnessXZPP, i1);
			renderer.brightnessTopRight = renderer.getAoBrightness(
					renderer.aoBrightnessXZPP, renderer.aoBrightnessXYPP,
					renderer.aoBrightnessXYZPPP, i1);
			renderer.brightnessBottomRight = renderer.getAoBrightness(
					renderer.aoBrightnessXZPN, renderer.aoBrightnessXYZPPN,
					renderer.aoBrightnessXYPP, i1);
			renderer.brightnessBottomLeft = renderer.getAoBrightness(
					renderer.aoBrightnessXYZPNN, renderer.aoBrightnessXYPN,
					renderer.aoBrightnessXZPN, i1);

			renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = r * 0.6F;
			renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = g * 0.6F;
			renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = b * 0.6F;
			renderer.colorRedTopLeft *= f3;
			renderer.colorGreenTopLeft *= f3;
			renderer.colorBlueTopLeft *= f3;
			renderer.colorRedBottomLeft *= f4;
			renderer.colorGreenBottomLeft *= f4;
			renderer.colorBlueBottomLeft *= f4;
			renderer.colorRedBottomRight *= f5;
			renderer.colorGreenBottomRight *= f5;
			renderer.colorBlueBottomRight *= f5;
			renderer.colorRedTopRight *= f6;
			renderer.colorGreenTopRight *= f6;
			renderer.colorBlueTopRight *= f6;

			IIcon icon = renderer.getBlockIcon(block, renderer.blockAccess, x,
					y, z, 5);
			double u0 = (double) icon.getMinU();
			double u1 = (double) icon.getMaxU();
			double v0 = (double) icon.getMinV();
			double v1 = (double) icon.getMaxV();
			double u2 = u1;
			double u3 = u0;
			double v2 = v0;
			double v3 = v1;

			if (h2 == 0.0F) {
				v0 = v1;
			}

			double x1 = (double) x + 1.0D;
			double y0 = (double) y;
			double z0 = (double) z;
			double z1 = (double) z + 1.0D;

			tessellator.setColorOpaque_F(renderer.colorRedTopLeft,
					renderer.colorGreenTopLeft, renderer.colorBlueTopLeft);
			tessellator.setBrightness(renderer.brightnessTopLeft);
			tessellator.addVertexWithUV(x1, y0, z1, u3, v3);
			tessellator
					.setColorOpaque_F(renderer.colorRedBottomLeft,
							renderer.colorGreenBottomLeft,
							renderer.colorBlueBottomLeft);
			tessellator.setBrightness(renderer.brightnessBottomLeft);
			tessellator.addVertexWithUV(x1, y0, z0, u1, v1);
			tessellator.setColorOpaque_F(renderer.colorRedBottomRight,
					renderer.colorGreenBottomRight,
					renderer.colorBlueBottomRight);
			tessellator.setBrightness(renderer.brightnessBottomRight);
			tessellator.addVertexWithUV(x1, y0 + (double) h3, z0, u2, v2);
			tessellator.setColorOpaque_F(renderer.colorRedTopRight,
					renderer.colorGreenTopRight, renderer.colorBlueTopRight);
			tessellator.setBrightness(renderer.brightnessTopRight);
			tessellator.addVertexWithUV(x1, y0 + (double) h2, z1, u0, v0);

			rendered = true;
		}

		renderer.enableAO = false;
		return rendered;
	}

	@Override
	public void renderInventoryBlock(Block block, int meta, int model,
			RenderBlocks renderer) {
		// TODO: Find function by Minecraft
		Tessellator tessellator = Tessellator.instance;
		if (renderer.useInventoryTint) {
			int color = block.getRenderColor(meta);
			float red = (float) (color >> 16 & 255) / 255.0F;
			float green = (float) (color >> 8 & 255) / 255.0F;
			float blue = (float) (color & 255) / 255.0F;
			GL11.glColor4f(red, green, blue, 1.0F);
		}
		block.setBlockBoundsForItemRender();
		renderer.setRenderBoundsFromBlock(block);
		GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1.0F, 0.0F);
		renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D,
				renderer.getBlockIconFromSideAndMetadata(block, 0, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D,
				renderer.getBlockIconFromSideAndMetadata(block, 1, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1.0F);
		renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D,
				renderer.getBlockIconFromSideAndMetadata(block, 2, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D,
				renderer.getBlockIconFromSideAndMetadata(block, 3, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(-1.0F, 0.0F, 0.0F);
		renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D,
				renderer.getBlockIconFromSideAndMetadata(block, 4, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D,
				renderer.getBlockIconFromSideAndMetadata(block, 5, meta));
		tessellator.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}

	@Override
	public boolean shouldRender3DInInventory(int meta) {
		return true;
	}
}
