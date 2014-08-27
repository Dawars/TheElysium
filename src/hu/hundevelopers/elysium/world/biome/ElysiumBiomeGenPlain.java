package hu.hundevelopers.elysium.world.biome;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FLOWERS;
import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.block.ElysiumBlock;
import hu.hundevelopers.elysium.block.ElysiumBlockFlower;
import hu.hundevelopers.elysium.world.gen.features.ElysiumGenDoublePlant;

import java.util.Random;

import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;

public class ElysiumBiomeGenPlain extends ElysiumBiomeBase
{

	public ElysiumBiomeGenPlain(int id)
	{
		super(id);
		this.theBiomeDecorator.flowersPerChunk = -1;
	}
	
	@Override
	//get flower with chance
	public String func_150572_a(Random rand, int x, int y, int z)
	{
		return rand.nextInt(3) > 0 ? ElysiumBlockFlower.names[0] : ElysiumBlockFlower.names[1];
	}

	 
    @Override
    public void decorate(World currentWorld, Random randomGenerator, int chunk_X, int chunk_Z)
    {
		super.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z);
		int flowersPerChunk = 5;
		
		boolean doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, FLOWERS);
			for (int j = 0; doGen && j < flowersPerChunk; ++j)
			{
				int k = chunk_X + randomGenerator.nextInt(16) + 8;
	            int l = chunk_Z + randomGenerator.nextInt(16) + 8;
	            int i1 = randomGenerator.nextInt(currentWorld.getHeightValue(k, l) + 32);
	            
	            String s = this.func_150572_a(randomGenerator, k, i1, l);

                this.theBiomeDecorator.yellowFlowerGen.func_150550_a(Elysium.blockFlower, ElysiumBlockFlower.getMetaFromName(s));
                this.theBiomeDecorator.yellowFlowerGen.generate(currentWorld, randomGenerator, k, i1, l);
	        }
    }
}
