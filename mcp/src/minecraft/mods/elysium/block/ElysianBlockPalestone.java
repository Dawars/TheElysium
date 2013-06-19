package mods.elysium.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.elysium.DefaultProps;
import mods.elysium.Elysium;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ElysianBlockPalestone extends ElysianBlock
{
	public ElysianBlockPalestone(int id, Material mat)
	{
		super(id, mat);
	}
	
	@Override
	/**
	 * Returns the ID of the items to drop on destruction.
	 */
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return Elysium.blockCobblePalestone.blockID;
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		
	}
	
	/*@Override
	@SideOnly(Side.CLIENT)
	public int getBlockColor()
	{
		double d0 = 0.5D;
		double d1 = 1.0D;
		return ColorizerFoliage.getFoliageColor(d0, d1);
	}*/
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int par1)
	{
		return (par1 & 3) == 1 ? ColorizerFoliage.getFoliageColorPine() : ((par1 & 3) == 2 ? ColorizerFoliage.getFoliageColorBirch() : ColorizerFoliage.getFoliageColorBasic());
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess blockAcces, int x, int y, int z)
	{
		int l = blockAcces.getBlockMetadata(x, y, z);

		if ((l & 3) == 1)
		{
			return ColorizerFoliage.getFoliageColorPine();
		}
		else if ((l & 3) == 2)
		{
			return ColorizerFoliage.getFoliageColorBirch();
		}
		else
		{
			int i1 = 0;
			int j1 = 0;
			int k1 = 0;

			for (int l1 = -1; l1 <= 1; ++l1)
			{
				for (int i2 = -1; i2 <= 1; ++i2)
				{
					int j2 = blockAcces.getBiomeGenForCoords(x + i2, z + l1).getBiomeFoliageColor();
					i1 += (j2 & 16711680) >> 16;
					j1 += (j2 & 65280) >> 8;
					k1 += j2 & 255;
				}
			}

			return (i1 / 9 & 255) << 16 | (j1 / 9 & 255) << 8 | k1 / 9 & 255;
		}
	}
}
