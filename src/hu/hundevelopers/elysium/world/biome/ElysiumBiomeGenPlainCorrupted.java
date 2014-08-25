package hu.hundevelopers.elysium.world.biome;

import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.entity.EntityCatorPillar;
import hu.hundevelopers.elysium.entity.EntitySwan;
import hu.hundevelopers.elysium.world.biome.decorator.BiomeElysiumPlainDecorator;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenVines;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType;

public class ElysiumBiomeGenPlainCorrupted extends ElysiumBiomeBase
{
	public ElysiumBiomeGenPlainCorrupted(int id)
	{
		super(id);
		this.setHeight(height_Plains_Corrupted);
		this.setTemperatureRainfall(0.95F, 0.9F);
		this.setBiomeName("Elysium Plain Corrupted");

//		if(CraftingPillars.winter) FIXME
//		{
//			this.setEnableSnow();
//			this.setTemperatureRainfall(0.05F, 0.8F);
//		}

//        this.theBiomeDecorator = new BiomeElysiumPlainDecorator(this);
		this.theBiomeDecorator.grassPerChunk = 10;
		this.theBiomeDecorator.flowersPerChunk = 1;
		
		this.setDisableRain();//TODO: new texture
		
	}


	/**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random rand)
    {
        return new WorldGenTallGrass(Elysium.blockTallGrass, 0);
    }

    public String func_150572_a(Random p_150572_1_, int p_150572_2_, int p_150572_3_, int p_150572_4_)
    {
        return Elysium.blockFlower.getUnlocalizedName();
    }
    
    @Override
    public void decorate(World currentWorld, Random randomGenerator, int chunk_X, int chunk_Z)
    {
		int grassPerChunk = 3;
		int flowersPerChunk = 1;
		
		int i;
		int j;
		int k;
		// this.generateOres();
		boolean doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, EventType.GRASS);
	
		for(int x = 0; doGen && x < grassPerChunk; ++x)
		{
			i = chunk_X + randomGenerator.nextInt(16) + 8;
			j = randomGenerator.nextInt(128);
			k = chunk_Z + randomGenerator.nextInt(16) + 8;
	
			WorldGenerator curlGrass = new WorldGenTallGrass(Elysium.blockTallGrass, 0);
			curlGrass.generate(currentWorld, randomGenerator, i, j, k);
		}
	
		doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, EventType.FLOWERS);
		for (int x = 0; doGen && x < flowersPerChunk; ++x)
		{
			i = chunk_X + randomGenerator.nextInt(16) + 8;
			j = randomGenerator.nextInt(128);
			k = chunk_Z + randomGenerator.nextInt(16) + 8;
			WorldGenFlowers plantAsphodelGen = new WorldGenFlowers(Elysium.blockFlower);
			plantAsphodelGen.generate(currentWorld, randomGenerator, i, j, k);
	
	//       if (this.randomGenerator.nextInt(4) == 0)
	//       {
	//       	i = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
	//			j = this.randomGenerator.nextInt(128);
	//			k = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
	//           this.plantRedGen.generate(this.currentWorld, this.randomGenerator, i, j, k);
	//       }
	   }
		
//		super.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z);
    }
}
