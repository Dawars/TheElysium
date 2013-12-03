package mods.elysium.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ElysiumGenFlowers extends WorldGenerator
{
	int id, meta, amount, radius;
	
	public ElysiumGenFlowers(int id, int metadata, int amount, int radius)
	{
		this.id = id;
		this.meta = metadata;
		this.amount = amount;
		this.radius = radius;
	}

	@Override
	public boolean generate(World world, Random random, int i, int j, int k)
	{
		for(int a = 0; a < amount; a++)
		{
			int x = i + random.nextInt(this.radius*2)-this.radius;
			int z = i + random.nextInt(this.radius*2)-this.radius;
			int y = world.getTopSolidOrLiquidBlock(x, z)+1;
			
			if(world.isAirBlock(x, y, z) && Block.blocksList[this.id].canBlockStay(world, x, y, z))
			{
				world.setBlock(x, y, z, this.id);
				world.setBlockMetadataWithNotify(x, y, z, this.meta, 0);
			}
		}
		
		return true;
	}
}
