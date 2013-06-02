package mods.elysium.dimension.gen.feature;

import java.util.Random;

import javax.sound.midi.SysexMessage;

import mods.elysium.Elysium;
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
		
	}
}
