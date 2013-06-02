package mods.elysium.gen;

import java.util.Random;

import mods.elysium.Elysium;
import mods.elysium.block.ElysiumFlower;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.ForgeDirection;

public class ElysiumGenFostimber extends WorldGenerator
{
	int leavesId,logId;
	boolean fromSapling;
	
	public ElysiumGenFostimber(int leavesId, int logId, boolean fromSapling)
	{
		super(fromSapling);
		this.leavesId = leavesId;
		this.logId = logId;
		this.fromSapling = fromSapling;
	}
	
	public boolean generate(World world, Random random, int x, int y, int z)
	{
		int cap = random.nextInt(2) + 2;
		int trunk = random.nextInt(3) + 3;
		int minTreeHeight = 8;
		
		int treeHeight = trunk + minTreeHeight;
		
		if (!((ElysiumFlower)Elysium.FostimberSaplingBlock).canThisPlantGrowOnThisBlockID(world.getBlockId(x, y-1, z)))
			return false;
		
		if(fromSapling)
		{
			for(int j = 1; j <= treeHeight; j++)
			{
				if(!world.isAirBlock(x, y+j, z))
				return false;
			}
		}
		else
		{
			for(int i = -2; i <= 2; i++)
			{
				for(int k = -2; k <= 2; k++)
				{
					for(int j = 1; j <= treeHeight; j++)
					{
						if(!world.isAirBlock(x+i, y+j, z+k))
							return false;
					}
				}
	        }
		}
		
		int h1 = treeHeight - random.nextInt(2);
		int h2 = treeHeight - random.nextInt(2);
		int h3 = treeHeight - random.nextInt(2);
		int h4 = treeHeight - random.nextInt(2);
		
		for(int i = 0; i < treeHeight + cap; i++)
		{
			
			if(i < treeHeight)
			{
				this.setBlock(world, x, y + i, z, logId);
			}
			
			if(i >= trunk && i < treeHeight + 1)
			{
		    	addLeaves(world, x + 1, y+i, z);
		    	addLeaves(world, x - 1, y+i, z);
		    	addLeaves(world, x, y+i, z + 1);
		    	addLeaves(world, x, y+i, z - 1);
			}
			
			if(i > trunk && i < h1)
		    	addLeaves(world, x + 1, y+i, z + 1);
			if(i > trunk && i < h2)
				addLeaves(world, x - 1, y+i, z - 1);
			if(i > trunk && i < h3)
				addLeaves(world, x - 1, y+i, z + 1);
			if(i > trunk && i < h4)
				addLeaves(world, x + 1, y+i, z - 1);
			
			if(i >= treeHeight)
			{
				addLeaves(world, x, y+i, z);
			}
		}
		
		return true;
	}
	
	private boolean addLeaves(World world, int x, int y, int z)
	{
		int id = world.getBlockId(x, y, z);
	    Block block = Block.blocksList[id];
		if(block == null || block.canBeReplacedByLeaves(world, x, y, z))
		{
			world.setBlock(x, y, z, leavesId);
			return true;
		}
		return false;
	}
}
