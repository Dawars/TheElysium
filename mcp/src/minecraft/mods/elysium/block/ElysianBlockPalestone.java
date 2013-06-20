package mods.elysium.block;

import java.util.Random;

import mods.elysium.Elysium;
import net.minecraft.block.material.Material;

public class ElysianBlockPalestone extends ElysianBlockHeatable
{
	public ElysianBlockPalestone(int id, Material mat)
	{
		super(id, mat, -273, 300);
		this.setTickRandomly(true);
	}
	
	@Override
	public int idDropped(int par1, Random random, int par3)
	{
		return Elysium.blockCobblePalestone.blockID;
	}
}
