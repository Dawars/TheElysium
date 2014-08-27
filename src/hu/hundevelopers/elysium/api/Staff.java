package hu.hundevelopers.elysium.api;

import hu.hundevelopers.elysium.Elysium;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class Staff
{
	private static Map<Block, Float> throwableBlocks = new HashMap<Block, Float>();

	/**
	 * Getting damage from block when colliding to mobs
	 * @param block
	 * @return
	 */
	public static float getDamageForBlock(Block block)
	{
		Float damage = throwableBlocks.get(block);
		return damage == null ? 0 : damage;
	}
	
	/**
	 * Registering throwable blocks with damage value (Earth Staff)
	 * @param block
	 * @param damage - how much does it hurt
	 */
	public static void registerThrowableBlock(Block block, float damage)
	{
		throwableBlocks.put(block, damage);
	}
}
