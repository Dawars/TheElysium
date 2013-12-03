package mods.elysium.world.biome;

import me.dawars.CraftingPillars.CraftingPillars;
import mods.elysium.Elysium;

public class ElysiumBiomeGenOcean extends ElysiumBiomeGenBase
{
	public ElysiumBiomeGenOcean(int id)
	{
		super(id);
		this.minHeight = -1F;
		this.maxHeight = 0.4F;
		this.topBlock = ((byte) Elysium.blockGrass.blockID);
		this.fillerBlock = ((byte) Elysium.blockDirt.blockID);
		
		if(CraftingPillars.winter)
		{
			this.setEnableSnow();
			this.setTemperatureRainfall(0.05F, 0.8F);
		}
		this.setBiomeName("Elysium Ocean");
	}
}