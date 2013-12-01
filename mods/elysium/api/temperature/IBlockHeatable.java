package mods.elysium.api.temperature;

import java.util.Random;

import net.minecraft.world.World;

public interface IBlockHeatable
{
	/**
	 * Used to update the blocks temperature. Called randomly if a player nearby based on the blocks tickRate(World world). 
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 */
	public void updateTemperature(World world, int x, int y, int z, Random random);
}
