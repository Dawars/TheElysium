package hu.hundevelopers.elysium.world.biome;

import hu.hundevelopers.elysium.Elysium;

import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ElysiumBiomeBase extends BiomeGenBase
{   

	public ElysiumBiomeBase(int id)
	{
		super(id, true);
        this.flowers.add(new FlowerEntry(Elysium.blockFlower, 0, 20));
        
        this.setDisableRain();
        
        this.topBlock = Elysium.blockGrass;
		this.fillerBlock = Elysium.blockDirt;
		
        this.spawnableCreatureList.clear();
		this.spawnableCaveCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
	}
	
	/**
     * Gets a WorldGen appropriate for this biome.
     */
	@Override
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return new WorldGenTallGrass(Elysium.blockTallGrass, 0);
    }

}
