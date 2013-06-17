package mods.elysium.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class CustomInventory implements IInventory
{
	private String name;
	private ItemStack[] inventory;
	
	public CustomInventory(String name, int size)
	{
		this.name = name;
		this.inventory = new ItemStack[size];
	}
	
	@Override
	public int getSizeInventory()
	{
		return this.inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int i)
	{
		return this.inventory[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j)
	{
		ItemStack ret;
		ret = this.inventory[i];
		
		if(ret != null)
		{
			if(ret.stackSize <= j)
			{
				this.inventory[i] = null;
				this.onInventoryChanged();
			}
			else
			{
				ret = ret.splitStack(j);
				this.onInventoryChanged();
			}
		}
			
		return ret;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i)
	{
		return this.getStackInSlot(i);
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack)
	{
		this.inventory[i] = itemstack;
		
		if((itemstack != null) && (itemstack.stackSize > this.getInventoryStackLimit()))
			itemstack.stackSize = this.getInventoryStackLimit();
		
		this.onInventoryChanged();
	}

	@Override
	public String getInvName()
	{
		return this.name;
	}

	@Override
	public boolean isInvNameLocalized()
	{
		return true;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public void onInventoryChanged()
	{
		
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer)
	{
		return true;
	}

	@Override
	public void openChest()
	{
		
	}

	@Override
	public void closeChest()
	{
		
	}

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack)
	{
		return true;
	}
	
}
