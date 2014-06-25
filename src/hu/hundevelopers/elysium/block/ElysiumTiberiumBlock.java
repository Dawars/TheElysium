package hu.hundevelopers.elysium.block;

import net.minecraft.block.material.Material;

public class ElysiumTiberiumBlock extends ElysiumBlock
{

	public ElysiumTiberiumBlock(Material mat) 
	{
		super(mat);
		this.setLightOpacity(1);
	}
	
	@Override
	public int getRenderBlockPass()
	{
		return 1;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

}
