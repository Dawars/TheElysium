package mods.elysium.block;

import mods.elysium.client.gui.GuiElysianCrafting;
import mods.elysium.entity.tileentity.TileEntityElysianWorkbench;
import mods.elysium.entity.tileentity.TileEntityShrinePillar;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ElysianBlockShrinePillar extends ElysianBlockContainer
{
	public ElysianBlockShrinePillar(int id)
	{
		super(id, Material.rock);
	}
	
	@Override
	public int getRenderType()
	{
		return -1;
	}
	
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		//ModLoader.openGUI(player, new GuiElysianCrafting(player.inventory, (IInventory)world.getBlockTileEntity(x, y, z)));
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityShrinePillar();
	}
}
