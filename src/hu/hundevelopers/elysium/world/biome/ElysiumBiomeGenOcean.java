package hu.hundevelopers.elysium.world.biome;

import hu.hundevelopers.elysium.Elysium;

public class ElysiumBiomeGenOcean extends ElysiumBiomeBase
{
	public ElysiumBiomeGenOcean(int id)
	{
		super(id);
		this.setHeight(height_Oceans);
		this.topBlock = Elysium.blockPalestone;
		this.fillerBlock = Elysium.blockPalestone;

//		if(CraftingPillars.winter) FIXME
//		{
//			this.setEnableSnow();
//			this.setTemperatureRainfall(0.05F, 0.8F);
//		}
		this.setBiomeName("Elysium Ocean");
	}
}
