package hu.hundevelopers.elysium.world.biome;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenVines;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType;
import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.world.biome.decorator.BiomeElysiumForestDecorator;
import hu.hundevelopers.elysium.world.gen.features.ElysiumGenDarkFostimber;


public class ElysiumBiomeGenForestCorrupted extends ElysiumBiomeBase
{
	public ElysiumBiomeGenForestCorrupted(int id)
	{
		super(id);
		this.setHeight(height_Forest_Corrupted);
		this.setTemperatureRainfall(0.95F, 0.9F);

		this.topBlock = Elysium.blockGrass;
		this.fillerBlock = Elysium.blockDirt;
		this.setBiomeName("Elysium Forest Corrupted");

//		if(CraftingPillars.winter) FIXME
//		{
//			this.setEnableSnow();
//			this.setTemperatureRainfall(0.1F, 0.8F);
//		}

        this.theBiomeDecorator = new BiomeElysiumForestDecorator(this);
		this.theBiomeDecorator.grassPerChunk = 10;
		this.theBiomeDecorator.flowersPerChunk = 2;
		
		this.setDisableRain();//TODO: new texture
	}

	@Override
	public void decorate(World currentWorld, Random randomGenerator, int chunk_X, int chunk_Z)
    {
		int i;
		int j;
		int k;
		// this.generateOres();
		boolean doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, EventType.GRASS);

		for(int x = 0; doGen && x < this.theBiomeDecorator.grassPerChunk; ++x)
		{

			i = chunk_X + randomGenerator.nextInt(16) + 8;
			j = randomGenerator.nextInt(128);
			k = chunk_Z + randomGenerator.nextInt(16) + 8;

			WorldGenerator curlGrass = new WorldGenTallGrass(Elysium.blockTallGrass, 0);
			curlGrass.generate(currentWorld, randomGenerator, i, j, k);
		}

        super.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z);
    }
}
