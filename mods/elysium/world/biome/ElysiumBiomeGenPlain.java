package mods.elysium.world.biome;

import me.dawars.CraftingPillars.CraftingPillars;
import mods.elysium.Elysium;

public class ElysiumBiomeGenPlain extends ElysiumBiomeGenBase
{
	public ElysiumBiomeGenPlain(int id)
	{
		super(id);
		this.minHeight = 0.1F;
		this.maxHeight = 0.6F;
		this.setTemperatureRainfall(0.5F, 0.4F);

		this.topBlock = ((byte) Elysium.blockGrass.blockID);
		this.fillerBlock = ((byte) Elysium.blockDirt.blockID);
		this.setBiomeName("Elysium Plain");

		if(CraftingPillars.winter)
		{
			this.setEnableSnow();
			this.setTemperatureRainfall(0.05F, 0.8F);
		}
		
        this.theBiomeDecorator = new BiomeElysiumPlainDecorator(this);
		this.theBiomeDecorator.grassPerChunk = 3;
		this.theBiomeDecorator.flowersPerChunk = 1;
		
	}
	
	
	
//	@Override
//    public void decorate(World world, Random rand, int i, int k){
//		super.decorate(world, rand, i, k);
		
//		if (rand.nextInt(50) == 0)
//        {
//            int x = i + rand.nextInt(16) + 8;
//            int y = k + rand.nextInt(16) + 8;
//            WorldGenDesertWells worldgendesertwells = new WorldGenDesertWells();
//            worldgendesertwells.generate(world, rand, x, world.getHeightValue(x, y) + 1, y);
//        }
//	}
}