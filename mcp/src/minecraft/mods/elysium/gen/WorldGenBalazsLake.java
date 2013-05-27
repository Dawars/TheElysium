package mods.elysium.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenBalazsLake extends WorldGenerator {
	void genLake(World world, Random random, int x, int y, int z, char from) {
		world.setBlock(x, y, z, Block.waterStill.blockID);
		if ((from != 'n') && (random.nextInt(2) == 0)) {
			genLake(world, random, x, y, z - 1, 's');
		}
		if ((from != 's') && (random.nextInt(2) == 0)) {
			genLake(world, random, x, y, z + 1, 'n');
		}
		if ((from != 'e') && (random.nextInt(2) == 0)) {
			genLake(world, random, x + 1, y, z, 'w');
		}
		if ((from != 'w') && (random.nextInt(2) == 0)) {
			genLake(world, random, x - 1, y, z, 'e');
		}
		if (random.nextInt(2) == 0) {
			genLake(world, random, x, y - 1, z, 'u');
		}
	}

	void genLake(World world, int x, int y, int z) {
		world.setBlock(x, y, z, Block.waterStill.blockID);
		if (world.isAirBlock(x + 1, y, z)) {
			genLake(world, x + 1, y, z);
		}
		if (world.isAirBlock(x - 1, y, z)) {
			genLake(world, x - 1, y, z);
		}
		if (world.isAirBlock(x, y, z + 1)) {
			genLake(world, x, y, z + 1);
		}
		if (world.isAirBlock(x, y, z - 1)) {
			genLake(world, x, y, z - 1);
		}
		if (world.isAirBlock(x, y - 1, z)) {
			genLake(world, x, y - 1, z);
		}
	}

	@Override
	public boolean generate(World world, Random random, int x, int y, int z) {
		{
			world.setBlock(x, y, z, Block.waterStill.blockID);
			if (world.getBlockId(x + 1, y, z) == 0) {
				genLake(world, x + 1, y, z);
			}
			if (world.getBlockId(x - 1, y, z) == 0) {
				genLake(world, x - 1, y, z);
			}
			if (world.getBlockId(x, y, z + 1) == 0) {
				genLake(world, x, y, z + 1);
			}
			if (world.getBlockId(x, y, z - 1) == 0) {
				genLake(world, x, y, z - 1);
			}
			if (world.getBlockId(x, y - 1, z) == 0) {
				genLake(world, x, y - 1, z);
			}
		}
		return true;
	}
}
