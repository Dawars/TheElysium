package hu.hundevelopers.elysium.world.biome;

import hu.hundevelopers.elysium.Elysium;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType;
import net.minecraftforge.event.terraingen.TerrainGen;

public class BiomeElysiumPlainDecorator extends BiomeDecorator
{
	 /** Field that holds one of the plantYellow WorldGenFlowers */
   public WorldGenerator plantAsphodelGen;
   
	public BiomeElysiumPlainDecorator(BiomeGenBase biomeGenBase)
	{
		super();
       this.plantAsphodelGen = new WorldGenFlowers(Elysium.blockFlower);
       ((WorldGenFlowers) this.plantAsphodelGen).func_150550_a(Elysium.blockFlower, 0);
	}

	/**
	 * The method that does the work of actually decorating chunks
	 */
	@Override
    protected void genDecorations(BiomeGenBase p_150513_1_)
	{
		System.out.println("Elysium Plain decorator");
		
		int i;
		int j;
		int k;
		// this.generateOres();
		boolean doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, EventType.GRASS);

		for(int x = 0; doGen && x < this.grassPerChunk; ++x)
		{

			i = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			j = this.randomGenerator.nextInt(128);
			k = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;

			WorldGenerator curlGrass = new WorldGenTallGrass(Elysium.blockTallGrass, 0);
			curlGrass.generate(this.currentWorld, this.randomGenerator, i, j, k);
		}


		doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, EventType.FLOWERS);
       for (int x = 0; doGen && x < this.flowersPerChunk; ++x)
       {
       	i = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			j = this.randomGenerator.nextInt(128);
			k = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
           this.plantAsphodelGen.generate(this.currentWorld, this.randomGenerator, i, j, k);

//           if (this.randomGenerator.nextInt(4) == 0)
//           {
//           	i = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
//   			j = this.randomGenerator.nextInt(128);
//   			k = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
//               this.plantRedGen.generate(this.currentWorld, this.randomGenerator, i, j, k);
//           }
       }
	}
}
