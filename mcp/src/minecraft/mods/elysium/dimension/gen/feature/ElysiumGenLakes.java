package mods.elysium.dimension.gen.feature;

import java.util.ArrayList;
import java.util.Random;

import mods.elysium.Elysium;
import mods.elysium.block.ElysianBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ElysiumGenLakes extends WorldGenerator
{
	int liquidId;
	int lake[] = new int[2048];
	
	public ElysiumGenLakes(int liquidId)
	{
		this.liquidId = liquidId;
	}
	
	@Override
	public boolean generate(World world, Random random, int x, int y, int z)
	{
		if(random.nextInt((Block.blocksList[this.liquidId].blockMaterial == Material.water) ? 2 : 16) == 0)
		{
			y = world.getTopSolidOrLiquidBlock(x, z);
		}
		
		if(y <= 4)
		{
			return false;
		}
		
		boolean isSurfaceLake = world.canBlockSeeTheSky(x, y, z);
		
		y -= 4;
		for(int j = 0; j < 8; j++)
		{
			if(world.getBlockMaterial(x, y+j, z).isLiquid())
				return false;
		}
		x -= 8;
		z -= 8;
		
		int l = random.nextInt(4) + 4;
		
		int cx;
		int cy;
		int cz;
		
		int i;
		for(i = 0; i < 2048; i++) lake[i] = -1;
		
		for (i = 0; i < l; i++)
		{
			double d0 = random.nextDouble() * 6.0D + 3.0D;
			double d1 = random.nextDouble() * 4.0D + 2.0D;
			double d2 = random.nextDouble() * 6.0D + 3.0D;
			double d3 = random.nextDouble() * (16.0D - d0 - 2.0D) + 1.0D + d0 / 2.0D;
			double d4 = random.nextDouble() * (8.0D - d1 - 4.0D) + 2.0D + d1 / 2.0D;
			double d5 = random.nextDouble() * (16.0D - d2 - 2.0D) + 1.0D + d2 / 2.0D;

			for (cx = 1; cx < 15; cx++)
			{
				for (cz = 1; cz < 15; cz++)
				{
					for (cy = 1; cy < 7; cy++)
					{
						double d6 = ((double)cx - d3) / (d0 / 2.0D);
						double d7 = ((double)cy - d4) / (d1 / 2.0D);
						double d8 = ((double)cz - d5) / (d2 / 2.0D);
						double d9 = d6 * d6 + d7 * d7 + d8 * d8;

						if (d9 < 1.0D)
						{
							lake[(cx * 16 + cz) * 8 + cy] = liquidId;
						}
					}
				}
			}
		}
		
		boolean flag;

		for (cx = 0; cx < 16; cx++)
		{
			for (cz = 0; cz < 16; cz++)
			{
				for (cy = 0; cy < 8; cy++)
				{
					flag = (lake[(cx * 16 + cz) * 8 + cy] == -1) && (cx < 15 && (lake[((cx + 1) * 16 + cz) * 8 + cy] == liquidId) || cx > 0 && (lake[((cx - 1) * 16 + cz) * 8 + cy] == liquidId) || cz < 15 && (lake[(cx * 16 + cz + 1) * 8 + cy] == liquidId) || cz > 0 && (lake[(cx * 16 + (cz - 1)) * 8 + cy] == liquidId) || cy < 7 && (lake[(cx * 16 + cz) * 8 + cy + 1] == liquidId) || cy > 0 && (lake[(cx * 16 + cz) * 8 + (cy - 1)] == liquidId));

					if (flag)
					{
						Material material = world.getBlockMaterial(x + cx, y + cy, z + cz);

						if (cy >= 4 && material.isLiquid())
						{
							return false;
						}

						if (cy < 4 && !material.isSolid() && world.getBlockId(x + cx, y + cy, z + cz) != this.liquidId)
						{
							return false;
						}
					}
				}
			}
		}
		
		for (cx = 0; cx < 16; cx++)
		{
			for (cz = 0; cz < 16; cz++)
			{
				for (cy = 0; cy < 8; cy++)
				{
					if (lake[(cx * 16 + cz) * 8 + cy] == liquidId)
					{
						if(cy >= 4)
						{
							lake[(cx * 16 + cz) * 8 + cy] = 0;
						}
					}
				}
			}
		}
		
		if(Block.blocksList[this.liquidId].blockMaterial == Material.water)
		{
			for (cx = 0; cx < 16; cx++)
			{
				for (cz = 0; cz < 16; cz++)
				{
					for (cy = 0; cy < 8; cy++)
					{
						if(isSurfaceLake && canPlaceLily(cx, cy, cz) && (random.nextInt(16) == 0))
						{
							lake[(cx * 16 + cz) * 8 + cy] = Block.waterlily.blockID;
						}
					}
				}
			}
		}
		
		for (cx = 0; cx < 16; cx++)
		{
			for (cz = 0; cz < 16; cz++)
			{
				for (cy = 0; cy < 8; cy++)
				{
					if((lake[(cx * 16 + cz) * 8 + cy] != -1) && (world.getBlockId(x+cx, y+cy, z+cz) != Block.blockNetherQuartz.blockID) && (world.getBlockId(x+cx, y+cy, z+cz) != Block.blockGold.blockID))
					{
						if(Block.blocksList[world.getBlockId(x+cx, y+cy, z+cz)] instanceof ElysianBlock)
						{
							if(((ElysianBlock)Block.blocksList[world.getBlockId(x+cx, y+cy, z+cz)]).canBeReplacedByLake())
								world.setBlock(x+cx, y+cy, z+cz, lake[(cx * 16 + cz) * 8 + cy]);//FIXME: random crash
						}
						else
						{
							world.setBlock(x+cx, y+cy, z+cz, lake[(cx * 16 + cz) * 8 + cy]);
						}
					}
				}
			}
		}
		
		if(isSurfaceLake && (Block.blocksList[this.liquidId].blockMaterial == Material.water))
		{
			if(random.nextInt(2) == 0)
			{
				new ElysiumGenSand(Elysium.blockLeucosand.blockID, 7).generate(world, random, x+8, y+4, z+8);
			}
			if(random.nextInt(2) == 0)
			{
				new ElysiumGenSand(Elysium.blockRilt.blockID, 3).generate(world, random, x+8, y+4, z+8);
			}
			if(random.nextInt(3) == 0)
			{
				int c = random.nextInt(5);
				for(i = 0; i < c; i++)
				{
					new ElysiumGenLakePillar().generate(world, random, x+random.nextInt(16)-8, y+4, z+random.nextInt(16)-8);
				}
			}
			//System.out.println("Generated lake on surface at: " + (x+8) + " " + (y+4) + " " + (z+8));
		}
		return true;
	}
	
	boolean canPlaceLily(int cx, int cy, int cz)
	{
		if((cy == 0) || (lake[(cx*16 + cz)*8 + cy-1] != liquidId) || (cx == 15) || (lake[((cx+1)*16 + cz)*8 + cy-1] != liquidId) || (cx == 0) || (lake[((cx-1)*16 + cz)*8 + cy-1] != liquidId) || (cz == 15) || (lake[(cx*16 + cz+1)*8 + cy-1] != liquidId) || (cz == 0) || (lake[(cx*16 + cz-1)*8 + cy-1] != liquidId))
		{
			return false;
		}
		
		if((lake[(cx*16 + cz)*8 + cy] != 0) && (lake[(cx*16 + cz)*8 + cy] != -1))
		{
			return false;
		}
		
		if((cx == 15) || ((lake[((cx+1)*16 + cz)*8 + cy] != 0) && (lake[((cx+1)*16 + cz)*8 + cy] != -1)))
		{
			return false;
		}
		
		if((cx == 0) || ((lake[((cx-1)*16 + cz)*8 + cy] != 0) && (lake[((cx-1)*16 + cz)*8 + cy] != -1)))
		{
			return false;
		}
		
		if((cz == 15) || ((lake[(cx*16 + cz+1)*8 + cy] != 0) && (lake[(cx*16 + cz+1)*8 + cy] != -1)))
		{
			return false;
		}
		
		if((cz == 0) || ((lake[(cx*16 + cz-1)*8 + cy] != 0) && (lake[(cx*16 + cz-1)*8 + cy] != -1)))
		{
			return false;
		}
		
		return true;
	}
}
