package mods.elysium.gen;

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

public class ElysiumWorldGen implements IWorldGenerator
{
	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		if(world.provider.dimensionId == Elysium.DimensionID)
		{
			generateElysium(world, rand, chunkX * 16, chunkZ * 16);
		}
	}
	
	private void generateElysium(World world, Random rand, int chunkX, int chunkZ)
	{
		int x = chunkX + rand.nextInt(16);
		int z = chunkZ + rand.nextInt(16);
		int y = world.getTopSolidOrLiquidBlock(x, z);
		
		int num = rand.nextInt(3)+1;
		
		for (int i = 0; i < num; i++)
		{
			int num2 = rand.nextInt(3)+1;//in a group

			WorldGenFostimber fostimber = new WorldGenFostimber(false);
			fostimber.generate(world, rand, x, y, z);
		}
	}
}
