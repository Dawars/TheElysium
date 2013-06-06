package mods.elysium.dimension.biome;

import mods.elysium.Elysium;
import net.minecraft.world.biome.BiomeGenBase;

public class ElysiumBiomeGenPlain extends BiomeGenBase
{
	public ElysiumBiomeGenPlain(int par1)
	{
		super(par1);
		this.minHeight = 0.1F;
		this.maxHeight = 0.6F;
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.topBlock = ((byte) Elysium.blockGrass.blockID);
		this.fillerBlock = ((byte) Elysium.blockDirt.blockID);

		this.setBiomeName("Elysium Plain");
		
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