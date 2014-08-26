package hu.hundevelopers.elysium.world.gen.features;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ElysiumGenPlants extends WorldGenerator
{
	Block block;
	
	public ElysiumGenPlants(Block block)
	{
		this.block = block;
	}

	public boolean generate(World world, Random rand, int x, int y, int z)
    {
        for (int l = 0; l < 10; ++l)
        {
            int i = x + rand.nextInt(8) - rand.nextInt(8);
            int k = z + rand.nextInt(8) - rand.nextInt(8);
            int j = world.getTopSolidOrLiquidBlock(i, k)+1;

            if (world.isAirBlock(i, j, k))
            {
                if (block.canBlockStay(world, i, j, k))
                {
                    world.setBlock(i, j, k, block, 0, 2);
                }
            }
        }

        return true;
    }
}
