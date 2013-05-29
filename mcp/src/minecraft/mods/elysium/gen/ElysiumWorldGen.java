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
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if(world.provider.dimensionId == Elysium.DimensionID)
			generateElysium(world, rand, chunkX * 16, chunkZ * 16);
			
			
//		switch (world.provider.dimensionId) {
//			case -1:
//				generateNether(world, rand, chunkX * 16, chunkZ * 16);
//			break;
//			case 0:
//				generateSurface(world, rand, chunkX * 16, chunkZ * 16);
//			break;
//			case 1:
//				generateEnd(world, rand, chunkX * 16, chunkZ * 16);
//			break;
//	
//			case Elysium.DimensionID:
//	
//			break;
//		}
	}

//	private void generateEnd(World world, Random rand, int i, int j) {
//	}
	private void generateSurface(){}
	
	private void generateElysium(World world, Random rand, int chunkX, int chunkZ)
	{
		int i = chunkX + rand.nextInt(16);
		int k = chunkZ + rand.nextInt(16);
		int j = world.getTopSolidOrLiquidBlock(i, k);

//        world.setBlock(i, j, k, Elysium.FostimberLogBlock.blockID);
        
		int num = rand.nextInt(3)+1;
		
		ElysiumGenLakes lakes = new ElysiumGenLakes(/*Elysium.waterStill.blockID*/);
		lakes.generate(world, rand, i, j, k);
		
		for (int x = 0; x < num; x++)
		{
			int num2 = rand.nextInt(3)+1;//in a group

			WorldGenFostimber fostimber = new WorldGenFostimber(false);
			fostimber.generate(world, rand, i, j, k);
		}
	}

//		world.setBlock(chunkX*16 + random, 100, chunkZ*16 + random.nextInt(16), 5);
		
//		BiomeGenBase b = world.getBiomeGenForCoords(chunkX, chunkZ);
//        if(b.biomeName.equals("Plains")) {
//                // Then we have plains!
//        }
//	private void generateNether(World world, Random rand, int i, int j) {
//	}

}
