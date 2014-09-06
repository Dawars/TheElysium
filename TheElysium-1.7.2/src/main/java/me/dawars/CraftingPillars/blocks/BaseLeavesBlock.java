package me.dawars.CraftingPillars.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BaseLeavesBlock extends BaseBlock
{

	/**
	 * Used to determine how to display leaves based on the graphics level. May also be used in rendering for
	 * transparency, not sure.
	 */
	public boolean graphicsLevel;

	protected BaseLeavesBlock(Material mat, boolean graph)
	{
		super(mat);
		this.graphicsLevel = graph;
	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
	 * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
	 */
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	/**
	 * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
	 * coordinates.  Args: blockAccess, x, y, z, side
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side)
	{
		Block block = world.getBlock(x, y, side);
		return !this.graphicsLevel && block == this ? false : super.shouldSideBeRendered(world, x, y, z, side);
	}
}
