package hu.hundevelopers.elysium.world.biome;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.world.biome.decorator.BiomeElysiumForestDecorator;
import hu.hundevelopers.elysium.world.gen.features.ElysiumGenDarkFostimber;


public class ElysiumBiomeGenForest extends ElysiumBiomeBase
{
	public ElysiumBiomeGenForest(int id)
	{
		super(id);
		this.setHeight(height_Forest);
//		this.setTemperatureRainfall(0.5F, 0.4F);

		this.topBlock = Elysium.blockGrass;
		this.fillerBlock = Elysium.blockDirt;
		this.setBiomeName("Elysium Forest");

//		if(CraftingPillars.winter) FIXME
//		{
			this.setEnableSnow();
			this.setTemperatureRainfall(0.1F, 0.8F);
//		}

        this.theBiomeDecorator = new BiomeElysiumForestDecorator(this);
		this.theBiomeDecorator.grassPerChunk = 5;
		this.theBiomeDecorator.flowersPerChunk = 2;
		
		this.setDisableRain();//TODO: new texture
	}

	@Override
	public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);
    }
}
