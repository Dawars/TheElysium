package me.dawars.CraftingPillars.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class ChristmasTreeSapling extends BaseFlowerBlock
{

	public static boolean isChristmas = false;

	public ChristmasTreeSapling(int id) {
		super(id);
		float f = 0.3F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.9F, 0.5F + f);
	}

	/**
	 * Ticks the block if it's been scheduled
	 */
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand)
	{
		if (!world.isRemote)
		{
			super.updateTick(world, x, y, z, rand);
		}
	}

	/**
	 * Gets passed in the blockID of the block below and supposed to return true if its allowed to grow on the type of
	 * blockID passed in. Args: blockID
	 */   /*
	@Override
	public boolean canThisPlantGrowOnThisBlockID(int id)
	{
		//    	if(CraftingPillars.modElysium)
		//    		return id == Elysium.blockDirt.blockID || id == Elysium.blockGrass.blockID;
		return id == Block.grass || id == Block.dirt;
	}   */
}
