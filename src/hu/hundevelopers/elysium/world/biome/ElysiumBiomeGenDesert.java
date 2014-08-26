package hu.hundevelopers.elysium.world.biome;

import hu.hundevelopers.elysium.Elysium;

import java.util.Random;

import net.minecraft.world.World;

public class ElysiumBiomeGenDesert extends ElysiumBiomeBase
{

	public ElysiumBiomeGenDesert(int id)
	{
		super(id);
		
		this.topBlock = Elysium.blockSand;
        this.fillerBlock = Elysium.blockSand;
        this.theBiomeDecorator.treesPerChunk = -999;
        this.theBiomeDecorator.deadBushPerChunk = -10;
        this.theBiomeDecorator.reedsPerChunk = -10;
        this.theBiomeDecorator.cactiPerChunk = 10;
		
	}

	@Override
	public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);
    }
}
