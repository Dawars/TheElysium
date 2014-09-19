package hu.hundevelopers.elysium.block;

import hu.hundevelopers.elysium.Elysium;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;

public class ElysiumQuartzFence extends BlockFence
{

	public ElysiumQuartzFence(String texture, Material mat)
	{
		super(texture, mat);
		this.setCreativeTab(Elysium.tabElysium);
	}
}
