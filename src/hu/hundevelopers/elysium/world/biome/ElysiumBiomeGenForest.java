package hu.hundevelopers.elysium.world.biome;

import java.util.Random;

import net.minecraft.world.World;

public class ElysiumBiomeGenForest extends ElysiumBiomeBase
{

	public ElysiumBiomeGenForest(int id)
	{
		super(id);
	}

    @Override
    public void decorate(World currentWorld, Random randomGenerator, int chunk_X, int chunk_Z)
    {
		super.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z);
    }
}
