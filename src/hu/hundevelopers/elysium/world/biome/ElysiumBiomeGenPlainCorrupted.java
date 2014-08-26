package hu.hundevelopers.elysium.world.biome;

import java.util.Random;

import net.minecraft.world.World;

public class ElysiumBiomeGenPlainCorrupted extends ElysiumBiomeBase
{
	public ElysiumBiomeGenPlainCorrupted(int id)
	{
		super(id);

		this.theBiomeDecorator.grassPerChunk = 10;
		this.theBiomeDecorator.flowersPerChunk = 1;
		
		this.setDisableRain();
	}

    
    @Override
    public void decorate(World currentWorld, Random randomGenerator, int chunk_X, int chunk_Z)
    {
		super.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z);
    }
}
