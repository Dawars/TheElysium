package hu.hundevelopers.elysium.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemMultiTexture;

public class ElysiumFenceItemBlock extends ItemMultiTexture
{
	public ElysiumFenceItemBlock(Block block)
	{
		super(block, block, ElysiumQuartzFence.texture_names);
	}
}