package mods.elysium.world.biome;

import me.dawars.CraftingPillars.CraftingPillars;
import net.minecraft.world.biome.BiomeGenBase;

public class ElysiumBiomeGenBase extends BiomeGenBase
{
	public ElysiumBiomeGenBase(int biomeID) {
		super(biomeID, true);
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		if(CraftingPillars.winter)
		{
			this.setEnableSnow();
			this.setTemperatureRainfall(0.05F, 0.8F);
		}
	}

}
