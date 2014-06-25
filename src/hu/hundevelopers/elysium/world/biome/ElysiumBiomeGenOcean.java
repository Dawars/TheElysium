package hu.hundevelopers.elysium.world.biome;

import hu.hundevelopers.elysium.Elysium;
import net.minecraft.world.biome.BiomeGenBase;

public class ElysiumBiomeGenOcean extends BiomeGenBase
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
