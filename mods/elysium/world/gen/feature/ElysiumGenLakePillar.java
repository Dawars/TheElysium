package mods.elysium.world.gen.feature;

import java.util.Random;

import mods.elysium.Elysium;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ElysiumGenLakePillar extends WorldGenerator
{
	@Override
	public boolean generate(World world, Random random, int x, int y, int z)
	{
		y = world.getTopSolidOrLiquidBlock(x, z);
		
		if(world.getBlockId(x, y, z) != Elysium.elysianWater.blockID)
			return false;
		
		int h = y + random.nextInt(6)+1;
		
		int j;
		for(j = y; world.getBlockId(x, j, z) == Elysium.elysianWater.blockID; j--)
		{
			world.setBlock(x, j, z, Elysium.blockPalestonePillar.blockID);
			world.setBlockMetadataWithNotify(x, j, z, 2, 0);
		}
		
		for(j = y+1; world.isAirBlock(x, j, z) && (j <= h); j++)
		{
			world.setBlock(x, j, z, Elysium.blockPalestonePillar.blockID);
			world.setBlockMetadataWithNotify(x, j, z, 2, 0);
		}
		
		if(j == h+1)
		{
			//System.out.println("yay");
			int w1 = random.nextInt(4);
			int w2 = w1 + random.nextInt(3)-1;
			
			if(random.nextBoolean())
			{
				for(int i = 1; (i <= w1) && world.isAirBlock(x+i, j, z); i++)
				{
					world.setBlock(x+i, j, z, Elysium.blockPalestonePillar.blockID);
					world.setBlockMetadataWithNotify(x+i, j, z, 3, 0);
				}
				
				for(int i = 0; (i <= w2) && world.isAirBlock(x-i, j, z); i++)
				{
					world.setBlock(x-i, j, z, Elysium.blockPalestonePillar.blockID);
					world.setBlockMetadataWithNotify(x-i, j, z, 3, 0);
				}
			}
			else
			{
				for(int i = 1; (i <= w1) && world.isAirBlock(x, j, z+i); i++)
				{
					world.setBlock(x, j, z+i, Elysium.blockPalestonePillar.blockID);
					world.setBlockMetadataWithNotify(x, j, z+i, 4, 0);
				}
				
				for(int i = 0; (i <= w2) && world.isAirBlock(x, j, z-i); i++)
				{
					world.setBlock(x, j, z-i, Elysium.blockPalestonePillar.blockID);
					world.setBlockMetadataWithNotify(x, j, z-i, 4, 0);
				}
			}
		}
		
		System.out.println("Generated pillar at: " + (x) + " " + (j+1) + " " + (z));
		return true;
	}
}
