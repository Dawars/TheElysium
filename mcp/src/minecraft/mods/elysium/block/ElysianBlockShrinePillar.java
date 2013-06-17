package mods.elysium.block;

import mods.elysium.client.gui.GuiElysianCrafting;
import mods.elysium.entity.tileentity.TileEntityElysianWorkbench;
import mods.elysium.entity.tileentity.TileEntityShrinePillar;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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
		if(hitY == 1F)
		{
			TileEntityShrinePillar workTile = (TileEntityShrinePillar) world.getBlockTileEntity(x, y, z);
			
			if(player.isSneaking() && (workTile.stuff[10] != null))
			{
				workTile.craftItem();
			}
			else if(player.getCurrentEquippedItem() != null)
			{
				hitX = (float) Math.floor(hitX/0.33F);
				hitZ = (float) Math.floor(hitZ/0.33F);
				int i = (int) (hitX*3 + hitZ);
				
				if(workTile.stuff[i] == null)
				{
					workTile.stuff[i] = player.getCurrentEquippedItem();
					workTile.stuff[i].stackSize = 1;
					if(!player.capabilities.isCreativeMode)
						player.getCurrentEquippedItem().stackSize--;
					workTile.onChange();
				}
				else if((workTile.stuff[i].itemID == player.getCurrentEquippedItem().itemID) && (workTile.stuff[i].stackSize < workTile.stuff[i].getMaxStackSize()))
				{
					workTile.stuff[i].stackSize++;
					if(!player.capabilities.isCreativeMode)
						player.getCurrentEquippedItem().stackSize--;
					workTile.onChange();
				}
			}
		}
		
		return true;
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6)
	{
		super.breakBlock(world, x, y, z, par5, par6);
		
		TileEntityShrinePillar workTile = (TileEntityShrinePillar) world.getBlockTileEntity(x, y, z);
		
		for(int i = 0; i < 3; i++)
		{
			for(int k = 0; k < 3; k++)
			{
				if((workTile != null) && (workTile.stuff != null) && (workTile.stuff[i*3 + k] != null))
				{
					EntityItem itemDropped = new EntityItem(world, x + 0.1875D+i*0.3125D, y+1D, z + 0.1875D+k*0.3125D, workTile.stuff[i*3 + k]);
					itemDropped.motionX = itemDropped.motionY = itemDropped.motionZ = 0D;
					
					if(!world.isRemote)
						world.spawnEntityInWorld(itemDropped);
					
					/*if(workTile.stuff[i*3 + k].hasTagCompound())
					{
						itemDropped.getEntityItem().setTagCompound((NBTTagCompound)workTile.stuff[i*3 + k].getTagCompound().copy());
					}*/
				}
			}
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityShrinePillar();
	}
}
