package hu.hundevelopers.elysium.world.gen;

import java.util.Random;

import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.api.Plants;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenForest;
import net.minecraft.world.biome.BiomeGenJungle;
import net.minecraft.world.biome.BiomeGenSwamp;
import minestrapteam.caveapi.cavetype.CaveType;

public class CaveTypeElysium extends CaveType
{

	public CaveTypeElysium(String name, Block mainCaveBlock)
	{
		super(name, mainCaveBlock);
		
		this.getWallGen().addReplacement(Blocks.dirt, Elysium.blockDirt);
		this.getWallGen().addReplacement(Blocks.gravel, Elysium.blockSand);
		this.getWallGen().addReplacement(Blocks.lava, Elysium.blockElysiumWater);
		
		this.addOre(Elysium.oreBeryl, 14, 3, this.spawnHeight);
	}


	@Override
	public void generateFloorAddons(World world, Random random, int x, int y, int z)
	{
		int rand = random.nextInt(10);
		if (rand < 4)
		{
			setBlock(world, x, y + 1, z, Blocks.sapling, rand);
		}
	}
	
	@Override
	public void generateCeilingAddons(World world, Random random, int x, int y, int z)
	{
//		setBlock(world, x, y, z, ECBlocks.crystals, 2);
//		setBlock(world, x, y - 1, z, ECBlocks.crystals, 2);
	}
	
	@Override
	public void generateFloor(World world, Random random, int x, int y, int z)
	{
		setBlock(world, x, y, z, Elysium.blockGrass);
		setBlock(world, x, y - 1, z, Elysium.blockDirt);
		setBlock(world, x, y - 2, z, Elysium.blockDirt);
		if(random.nextInt(3) == 0) Plants.plantPlant(world, x, y+1, z);

	}
	
	@Override
	public boolean canGenerateInBiome(BiomeGenBase biome)
	{
		return biome instanceof BiomeGenSwamp;
	}
}
