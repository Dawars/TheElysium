package hu.hundevelopers.elysium.world.biome;

import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.world.gen.features.ElysiumGenDoublePlant;
import hu.hundevelopers.elysium.world.gen.features.ElysiumGenPlants;
import hu.hundevelopers.elysium.world.gen.structures.ElysiumGenEnderPyramid;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenDesertWells;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ElysiumBiomeGenDesert extends ElysiumBiomeBase
{

	public ElysiumBiomeGenDesert(int id)
	{
		super(id);
		
		this.topBlock = Elysium.blockSand;
        this.fillerBlock = Elysium.blockSand;
        this.theBiomeDecorator.deadBushPerChunk = -10;
        this.theBiomeDecorator.grassPerChunk = 1;
        this.theBiomeDecorator.reedsPerChunk = -10;
        this.theBiomeDecorator.cactiPerChunk = -10;
	}
	
	/**
     * Gets a WorldGen appropriate for this biome.
     */
	@Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand)
    {
		return new WorldGenTallGrass(Elysium.blockCactus, 0);
    }

	public void decorate(World world, Random rand, int chunkX, int chunkZ)
    {
        super.decorate(world, rand, chunkX, chunkZ);

        if (rand.nextInt(1000) == 0)
        {
            int k = chunkX + rand.nextInt(16) + 8;
            int l = chunkZ + rand.nextInt(16) + 8;
            ElysiumGenEnderPyramid worldgenpyramid = new ElysiumGenEnderPyramid();
            worldgenpyramid.generate(world, rand, k, world.getHeightValue(k, l)-1, l);
        }
    }
}
