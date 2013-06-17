package mods.elysium.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.world.World;

import mods.elysium.entity.tileentity.TileEntityElysianWorkbench;

public class ContainerElysianWorkbench extends Container
{
	IInventory crafting;
	IInventory result;
	World world;
	int posX, posY, posZ;
	
	public ContainerElysianWorkbench(World world, int posX, int posY, int posZ, InventoryPlayer playerInv, IInventory workInv)
	{
		this.world = world;
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
		
		this.crafting = workInv;
		this.result = new CustomInventory("Result", 9);
		this.crafting = new InventoryCrafting(this, 3, 3);
		/*for(int i = 0; i < 9; i++)
			this.crafting.setInventorySlotContents(i, workInv.getStackInSlot(i));*/
		
		//this.addSlotToContainer(new SlotCrafting(playerInv.player, this.crafting, this.result, 4, 98 + 18, 17 + 18));
		
		for(int x = 0; x < 3; x++)
		{
			for(int y = 0; y < 3; y++)
			{
				this.addSlotToContainer(new Slot(this.crafting, y*3+x, 26 + x*18, 17 + y*18));
				//this.addSlotToContainer(new Slot(this.result, y*3+x, 98 + x*18, 17 + y*18));
				//this.addSlotToContainer(new SlotCrafting(playerInv.player, this.crafting, this.result, y*3+x, 98 + x*18, 17 + y*18));
			}
		}
		
		for(int x = 0; x < 9; x++)
		{
			for(int y = 0; y < 3; y++)
			{
				this.addSlotToContainer(new Slot(playerInv, 9 + x + y*9, 8 + x*18, 84 + y*18));
			}
		}
		
		for(int x = 0; x < 9; x++)
		{
			this.addSlotToContainer(new Slot(playerInv, x, 8 + x*18, 142));
		}
		
		//this.onCraftMatrixChanged(this.crafting);
	}
	
	/*@Override
	public void onCraftMatrixChanged(IInventory inventory)
	{
		this.result.setInventorySlotContents(4, CraftingManager.getInstance().findMatchingRecipe(this.crafting, this.world));
	}*/
	
	@Override
	public void onCraftGuiClosed(EntityPlayer player)
	{
		/*for(int i = 0; i < 9; i++)
			((TileEntityElysianWorkbench)this.world.getBlockTileEntity(this.posX, this.posY, this.posZ)).setInventorySlotContents(i, this.crafting.getStackInSlotOnClosing(i));*/
		//super.onCraftGuiClosed(player);
		this.crafting.closeChest();
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return true;
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int i)
	{
		ItemStack stack = null;
		Slot slot = (Slot)this.inventorySlots.get(i);

		if (slot != null && slot.getHasStack())
		{
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();

			if (i == 0)
			{
				if (!this.mergeItemStack(stack1, 10, 46, true))
				{
					return null;
				}

				slot.onSlotChange(stack1, stack);
			}
			else if (i >= 10 && i < 37)
			{
				if (!this.mergeItemStack(stack1, 37, 46, false))
				{
					return null;
				}
			}
			else if (i >= 37 && i < 46)
			{
				if (!this.mergeItemStack(stack1, 10, 37, false))
				{
					return null;
				}
			}
			else if (!this.mergeItemStack(stack1, 10, 46, false))
			{
				return null;
			}

			if (stack1.stackSize == 0)
			{
				slot.putStack((ItemStack)null);
			}
			else
			{
				slot.onSlotChanged();
			}

			if (stack1.stackSize == stack.stackSize)
			{
				return null;
			}

			slot.onPickupFromSlot(player, stack1);
		}

		return stack;
	}
}
