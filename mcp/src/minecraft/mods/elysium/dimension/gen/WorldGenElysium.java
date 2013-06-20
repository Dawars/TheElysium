package mods.elysium.dimension.gen;

import java.util.Random;

import javax.sound.midi.SysexMessage;

import mods.elysium.Elysium;
import mods.elysium.dimension.gen.feature.ElysiumGenLakes;
import mods.elysium.dimension.gen.feature.ElysiumGenMinable;
import net.minecraft.block.Block;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenElysium implements IWorldGenerator
{
	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		if(world.provider.dimensionId == Elysium.DimensionID)
		{
			generateElysium(world, rand, chunkX * 16, chunkZ * 16);
		}
		if(world.provider.dimensionId == 0)
		{
			//generateOverworld(world, rand, chunkX * 16, chunkZ * 16);
		}
	}
	
	private void generateOverworld(World world, Random random, int blockX, int blockZ)
	{
		int x = blockX + random.nextInt(16);
		int z = blockX + random.nextInt(16);
		int y = world.getTopSolidOrLiquidBlock(x, z);
		
		new ElysiumGenLakes(Elysium.waterStill.blockID).generate(world, random, x, y, z);
	}
	
	private void generateElysium(World world, Random random, int blockX, int blockZ)
	{
		for(int i = 0; i < 8; i++)
		{
			new ElysiumGenMinable(Elysium.oreSulphure.blockID, 16).generate(world, random, blockX + random.nextInt(16), random.nextInt(128), blockZ + random.nextInt(16));
		}
		
		for(int i = 0; i < 8; i++)
		{
			new ElysiumGenMinable(Elysium.oreCobalt.blockID, 8).generate(world, random, blockX + random.nextInt(16), random.nextInt(64), blockZ + random.nextInt(16));
		}
		
		for(int i = 0; i < 2; i++)
		{
			new ElysiumGenMinable(Elysium.oreIridium.blockID, 8).generate(world, random, blockX + random.nextInt(16), random.nextInt(32), blockZ + random.nextInt(16));
		}
		
		for(int i = 0; i < 3; i++)
		{
			new ElysiumGenMinable(Elysium.oreSilicon.blockID, 8).generate(world, random, blockX + random.nextInt(16), random.nextInt(16), blockZ + random.nextInt(16));
		}
		
		new ElysiumGenMinable(Elysium.oreJade.blockID, 8).generate(world, random, blockX + random.nextInt(16), random.nextInt(32), blockZ + random.nextInt(16));
		
		new ElysiumGenMinable(Elysium.oreTourmaline.blockID, 8).generate(world, random, blockX + random.nextInt(16), random.nextInt(16), blockZ + random.nextInt(16));
		
		if(world.getBiomeGenForCoords(blockX, blockZ) == Elysium.elysianBiomeOcean)
		{
			int x = blockX + random.nextInt(16);
			int z = blockZ + random.nextInt(16);
			
			int y;
			for(y = world.getTopSolidOrLiquidBlock(x, z); world.getBlockId(x, y, z) == Elysium.waterStill.blockID; y--);
			
			if(world.getBlockId(x, y+1, z) == Elysium.waterStill.blockID)
			{
				world.setBlock(x, y, z, Elysium.oreBeryl.blockID);
			}
		}
		
		for(int i = 0; i < 16; i++)
			for(int k = 0; k < 16; k++)
				for(int j = 0; j < 128; j++)
					if(world.getBlockId(blockX+i, j, blockZ+k) == Elysium.blockPalestone.blockID)
						world.setBlockMetadataWithNotify(blockX+i, j, blockZ+k, 7, 0);
	}
}
