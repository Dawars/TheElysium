package mods.elysium.dimension.gen.feature;

import java.util.Random;

import mods.elysium.Elysium;
import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ElysiumGenMinable
{
	/** The block ID of the ore to be placed using this generator. */
	private int minableBlockId;
	private int minableBlockMeta = 0;

	/** The number of blocks to generate. */
	private int numberOfBlocks;
	private int target;

	public ElysiumGenMinable(int id, int amount)
	{
		this(id, amount, Elysium.blockPalestone.blockID);
	}

	public ElysiumGenMinable(int id, int amount, int target)
	{
		this.minableBlockId = id;
		this.numberOfBlocks = amount;
		this.target = target;
	}

	public ElysiumGenMinable(int id, int meta, int amount, int target)
	{
		this(id, amount, target);
		minableBlockMeta = meta;
	}

	public boolean generate(World world, Random random, int x, int y, int z)
	{
		boolean placedBlock = false;
		
		float f = random.nextFloat() * (float)Math.PI;
		double randX1 = (double)((float)(x + 8) + MathHelper.sin(f) * (float)this.numberOfBlocks / 8.0F);
		double randX2 = (double)((float)(x + 8) - MathHelper.sin(f) * (float)this.numberOfBlocks / 8.0F);
		double randZ1 = (double)((float)(z + 8) + MathHelper.cos(f) * (float)this.numberOfBlocks / 8.0F);
		double randZ2 = (double)((float)(z + 8) - MathHelper.cos(f) * (float)this.numberOfBlocks / 8.0F);
		double randY1 = (double)(y + random.nextInt(3) - 2);
		double randY2 = (double)(y + random.nextInt(3) - 2);

		for (int i = 0; i <= this.numberOfBlocks; i++)
		{
			double d6 = randX1 + (randX2 - randX1) * (double)i / (double)this.numberOfBlocks;
			double d7 = randY1 + (randY2 - randY1) * (double)i / (double)this.numberOfBlocks;
			double d8 = randZ1 + (randZ2 - randZ1) * (double)i / (double)this.numberOfBlocks;
			double d9 = random.nextDouble() * (double)this.numberOfBlocks / 16.0D;
			double d10 = (double)(MathHelper.sin((float)i * (float)Math.PI / (float)this.numberOfBlocks) + 1.0F) * d9 + 1.0D;
			double d11 = (double)(MathHelper.sin((float)i * (float)Math.PI / (float)this.numberOfBlocks) + 1.0F) * d9 + 1.0D;
			int minX = MathHelper.floor_double(d6 - d10 / 2.0D);
			int minY = MathHelper.floor_double(d7 - d11 / 2.0D);
			int minZ = MathHelper.floor_double(d8 - d10 / 2.0D);
			int maxX = MathHelper.floor_double(d6 + d10 / 2.0D);
			int maxY = MathHelper.floor_double(d7 + d11 / 2.0D);
			int maxZ = MathHelper.floor_double(d8 + d10 / 2.0D);

			for (int cx = minX; cx <= maxX; cx++)
			{
				double d12 = ((double)cx + 0.5D - d6) / (d10 / 2.0D);

				if (d12 * d12 < 1.0D)
				{
					for (int cy = minY; cy <= maxY; cy++)
					{
						double d13 = ((double)cy + 0.5D - d7) / (d11 / 2.0D);

						if (d12 * d12 + d13 * d13 < 1.0D)
						{
							for (int cz = minZ; cz <= maxZ; cz++)
							{
								double d14 = ((double)cz + 0.5D - d8) / (d10 / 2.0D);

								if (d12 * d12 + d13 * d13 + d14 * d14 < 1.0D && (world.getBlockId(cx, cy, cz) == target))
								{
									world.setBlock(cx, cy, cz, this.minableBlockId, minableBlockMeta, 2);
									placedBlock = true;
								}
							}
						}
					}
				}
			}
		}

		return placedBlock;
	}
}
