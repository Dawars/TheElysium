package mods.elysium.dimension.biome;

import mods.elysium.Elysium;
import net.minecraft.world.biome.BiomeGenBase;

public class ElysiumBiomeGenOcean extends BiomeGenBase
{
	public ElysiumBiomeGenOcean(int id)
	{
		super(id);
		this.temperature = 1.5F;
		this.minHeight = -1F;
		this.maxHeight = 0.4F;
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.topBlock = ((byte) Elysium.blockGrass.blockID);
		this.fillerBlock = ((byte) Elysium.blockDirt.blockID);
		
		this.setBiomeName("Elysium Ocean");
	}
}