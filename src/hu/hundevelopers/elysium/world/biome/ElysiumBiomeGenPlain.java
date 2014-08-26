package hu.hundevelopers.elysium.world.biome;

import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.world.gen.features.ElysiumGenDoublePlant;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ElysiumBiomeGenPlain extends ElysiumBiomeBase
{

	public ElysiumBiomeGenPlain(int id)
	{
		super(id);
//		this.theBiomeDecorator.flowersPerChunk = 5;
//		this.theBiomeDecorator.grassPerChunk = 10;
	}
	
    @Override
    public void decorate(World currentWorld, Random randomGenerator, int chunk_X, int chunk_Z)
    {
		super.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z);
    }
}
