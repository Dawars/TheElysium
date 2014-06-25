package hu.hundevelopers.elysium.world.biome;

import java.util.Random;

import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.api.Plants;
import net.minecraft.block.BlockFlower;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ElysiumBiomeGenPlain extends BiomeGenBase
{
	public ElysiumBiomeGenPlain(int id)
	{
		super(id);
		this.setHeight(new Height(0.2F, 0.6F));
		this.setTemperatureRainfall(0.5F, 0.4F);

		this.topBlock = Elysium.blockGrass;
		this.fillerBlock = Elysium.blockDirt;
		this.setBiomeName("Elysium Plain");

//		if(CraftingPillars.winter) FIXME
//		{
//			this.setEnableSnow();
//			this.setTemperatureRainfall(0.05F, 0.8F);
//		}

        this.theBiomeDecorator = new BiomeElysiumPlainDecorator(this);
		this.theBiomeDecorator.grassPerChunk = 3;
		this.theBiomeDecorator.flowersPerChunk = 1;

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
}
