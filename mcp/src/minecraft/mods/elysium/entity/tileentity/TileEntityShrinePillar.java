package mods.elysium.entity.tileentity;

import mods.elysium.Elysium;
import mods.elysium.inventory.ContainerShrinePillar;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityShrinePillar extends TileEntity
{
	public ContainerShrinePillar container;
	public ItemStack[] stuff;
	public float rot;
	
	public TileEntityShrinePillar()
	{
		this.container = new ContainerShrinePillar();
		this.stuff = new ItemStack[10];
		this.rot = 0F;
	}
	
	@Override
	public void updateEntity()
	{
		this.rot += 0.1F;
		if(this.rot >= 360F) this.rot -= 360F;
		
		super.updateEntity();
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		
		NBTTagList nbtlist = nbt.getTagList("Items");
		this.stuff = new ItemStack[9];
		
		for(int i = 0; i < nbtlist.tagCount(); i++)
		{
			NBTTagCompound nbtslot = (NBTTagCompound) nbtlist.tagAt(i);
			int j = nbtslot.getByte("Slot") & 255;
			
			if((j >= 0) && (j < 9))
				this.stuff[j] = ItemStack.loadItemStackFromNBT(nbtslot);
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		
		NBTTagList nbtlist = new NBTTagList();
		
		for(int i = 0; i < 9; i++)
		{
			if(this.stuff[i] != null)
			{
				NBTTagCompound nbtslot = new NBTTagCompound();
				nbtslot.setByte("Slot", (byte)i);
				this.stuff[i].writeToNBT(nbtslot);
				nbtlist.appendTag(nbtslot);
			}
		}
		
		nbt.setTag("Items", nbtlist);
	}
	
	public void onChange()
	{
		for(int i = 0; i < 9; i++)
			this.container.craftMatrix.setInventorySlotContents(i, this.stuff[i]);
		this.stuff[10] = CraftingManager.getInstance().findMatchingRecipe(this.container.craftMatrix, this.worldObj);
	}
	
	public void craftItem()
	{
		EntityItem itemCrafted = new EntityItem(this.worldObj, this.xCoord+0.5D, this.yCoord+1D, this.zCoord+0.5D, this.stuff[10]);
		itemCrafted.motionX = itemCrafted.motionY = itemCrafted.motionZ = 0D;
		
		if(!this.worldObj.isRemote)
			this.worldObj.spawnEntityInWorld(itemCrafted);
		
		onChange();
	}
}
