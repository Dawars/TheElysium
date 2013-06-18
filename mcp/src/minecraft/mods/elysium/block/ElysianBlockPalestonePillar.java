package mods.elysium.block;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.elysium.DefaultProps;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.MinecraftForge;

public class ElysianBlockPalestonePillar extends ElysianBlock
{
	@SideOnly(Side.CLIENT)
	private Icon iconTop;
	@SideOnly(Side.CLIENT)
	private Icon iconSide;
	
	public ElysianBlockPalestonePillar(int id, Material mat)
	{
		super(id, mat);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta)
	{
		if(((meta == 2) || (meta == 0)) && ((side == 0) || (side == 1)))
			return this.iconTop;
		
		if((meta == 4) && ((side == 2) || (side == 3)))
			return this.iconTop;
		
		if((meta == 3) && ((side == 4) || (side == 5)))
			return this.iconTop;
		
		return this.iconSide;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.iconTop = par1IconRegister.registerIcon(DefaultProps.modId + ":palestone_pillar_top");
		this.iconSide = par1IconRegister.registerIcon(DefaultProps.modId + ":palestone_pillar_side");
	}
	
	public int getRenderType()
	{
		return Block.blockNetherQuartz.getRenderType();
	}
	
	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
	{
		if((side == 0) || (side == 1))
		{
			meta = 2;
		}
		
		if((side == 2) || (side == 3))
		{
			meta = 4;
		}
		
		if((side == 4) || (side == 5))
		{
			meta = 3;
		}

		return meta;
	}
}
