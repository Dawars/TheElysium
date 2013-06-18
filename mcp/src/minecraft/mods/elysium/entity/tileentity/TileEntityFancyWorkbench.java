package mods.elysium.entity.tileentity;

import static org.lwjgl.opengl.GL11.glTranslated;

import java.util.Random;

import mods.elysium.inventory.ContainerShrinePillar;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.stats.AchievementList;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;

public class TileEntityFancyWorkbench extends ElysianTileEntity implements IInventory, ISidedInventory
{
	private Random random;
	private ContainerShrinePillar container;
	private ItemStack[] inventory;
	public float rot;
	
	public TileEntityFancyWorkbench(World world)
	{
		this.worldObj = world;
		this.random = new Random(this.worldObj.getSeed());
		this.container = new ContainerShrinePillar();
		this.inventory = new ItemStack[10];
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
		this.inventory = new ItemStack[this.getSizeInventory()+1];
		
		for(int i = 0; i < nbtlist.tagCount(); i++)
		{
			NBTTagCompound nbtslot = (NBTTagCompound) nbtlist.tagAt(i);
			int j = nbtslot.getByte("Slot") & 255;
			
			if((j >= 0) && (j < this.getSizeInventory()))
				this.inventory[j] = ItemStack.loadItemStackFromNBT(nbtslot);
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		
		NBTTagList nbtlist = new NBTTagList();
		
		for(int i = 0; i < this.getSizeInventory(); i++)
		{
			if(this.inventory[i] != null)
			{
				NBTTagCompound nbtslot = new NBTTagCompound();
				nbtslot.setByte("Slot", (byte)i);
				this.inventory[i].writeToNBT(nbtslot);
				nbtlist.appendTag(nbtslot);
			}
		}
		
		nbt.setTag("Items", nbtlist);
	}
	
	
	
	@Override
	public void onInventoryChanged()
	{
		super.onInventoryChanged();
		
		rotateCraftingGrid();
		
		this.inventory[this.getSizeInventory()] = CraftingManager.getInstance().findMatchingRecipe(this.container.craftMatrix, this.worldObj);
	}
	
	public void rotateCraftingGrid()
	{
		int meta = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
		
		if(!this.worldObj.isRemote)
			System.out.println(meta);
		
		for(int i = 0; i < 3; i++)
		{
			for(int k = 0; k < 3; k++)
			{
				if(meta == 0)
				{
					this.container.craftMatrix.setInventorySlotContents(8 - k*3 - i, this.getStackInSlot(i*3 + k));
				}
				else if(meta == 1)
				{
					this.container.craftMatrix.setInventorySlotContents(i*3 + k, this.getStackInSlot(i*3 + k));
				}
				else if(meta == 2)
				{
					this.container.craftMatrix.setInventorySlotContents(k*3 + i, this.getStackInSlot(i*3 + k));
				}
				else
				{
					this.container.craftMatrix.setInventorySlotContents(8 - i*3 - k, this.getStackInSlot(i*3 + k));
				}
			}
		}
	}
	
	public void craftItem(EntityPlayer player)
	{
		EntityItem itemCrafted = new EntityItem(this.worldObj, this.xCoord+0.5D, this.yCoord+1.5D, this.zCoord+0.5D, this.inventory[this.getSizeInventory()]);
		itemCrafted.motionX = random.nextDouble()/4 - 0.125D;
		itemCrafted.motionZ = random.nextDouble()/4 - 0.125D;
		itemCrafted.motionY = random.nextDouble()/4;
		
		if(!this.worldObj.isRemote)
			this.worldObj.spawnEntityInWorld(itemCrafted);
		
		this.onCrafting(player, this.inventory[this.getSizeInventory()]);
		
		for(int i = 0; i < this.getSizeInventory(); i++)
		{
			ItemStack itemstack1 = this.getStackInSlot(i);
			
			if(itemstack1 != null)
			{
				this.decrStackSize(i, 1);
				
				if(itemstack1.getItem().hasContainerItem())
				{
					ItemStack itemstack2 = itemstack1.getItem().getContainerItemStack(itemstack1);
					
					if(itemstack2.isItemStackDamageable() && itemstack2.getItemDamage() > itemstack2.getMaxDamage())
					{
						MinecraftForge.EVENT_BUS.post(new PlayerDestroyItemEvent(player, itemstack2));
						itemstack2 = null;
					}
					
					if(itemstack2 != null && (!itemstack1.getItem().doesContainerItemLeaveCraftingGrid(itemstack1) || !player.inventory.addItemStackToInventory(itemstack2)))
					{
						if(this.getStackInSlot(i) == null)
						{
							this.setInventorySlotContents(i, itemstack2);
						}
						else
						{
							player.dropPlayerItem(itemstack2);
						}
					}
				}
			}
		}
	}
	
	public void onCrafting(EntityPlayer player, ItemStack stack)
	{
		stack.onCrafting(this.worldObj, player, this.inventory[this.getSizeInventory()].stackSize);
		
		if (stack.itemID == Block.workbench.blockID)
		{
			player.addStat(AchievementList.buildWorkBench, 1);
		}
		else if (stack.itemID == Item.pickaxeWood.itemID)
		{
			player.addStat(AchievementList.buildPickaxe, 1);
		}
		else if (stack.itemID == Block.furnaceIdle.blockID)
		{
			player.addStat(AchievementList.buildFurnace, 1);
		}
		else if (stack.itemID == Item.hoeWood.itemID)
		{
			player.addStat(AchievementList.buildHoe, 1);
		}
		else if (stack.itemID == Item.bread.itemID)
		{
			player.addStat(AchievementList.makeBread, 1);
		}
		else if (stack.itemID == Item.cake.itemID)
		{
			player.addStat(AchievementList.bakeCake, 1);
		}
		else if (stack.itemID == Item.pickaxeStone.itemID)
		{
			player.addStat(AchievementList.buildBetterPickaxe, 1);
		}
		else if (stack.itemID == Item.swordWood.itemID)
		{
			player.addStat(AchievementList.buildSword, 1);
		}
		else if (stack.itemID == Block.enchantmentTable.blockID)
		{
			player.addStat(AchievementList.enchantments, 1);
		}
		else if (stack.itemID == Block.bookShelf.blockID)
		{
			player.addStat(AchievementList.bookcase, 1);
		}
	}
	
	public void dropItemFromSlot(int slot)
	{
		if(this.getStackInSlot(slot) != null)
		{
			EntityItem droppedItem = new EntityItem(this.worldObj, this.xCoord + 0.5D, this.yCoord + 1.5D, this.zCoord + 0.5D);
			droppedItem.setEntityItemStack(this.decrStackSize(slot, 1));
			
			droppedItem.motionX = random.nextDouble()/4 - 0.125D;
			droppedItem.motionZ = random.nextDouble()/4 - 0.125D;
			droppedItem.motionY = random.nextDouble()/4;
			
			if(!this.worldObj.isRemote)
				this.worldObj.spawnEntityInWorld(droppedItem);
		}
	}
	
	

	@Override
	public int getSizeInventory()
	{
		return this.inventory.length-1;
	}

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return this.inventory[slot];
	}
	
	@Override
	public void setInventorySlotContents(int slot, ItemStack itemstack)
	{
		this.inventory[slot] = itemstack;
		
		if(itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
		{
			itemstack.stackSize = this.getInventoryStackLimit();
		}
		
		this.onInventoryChanged();
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount)
	{
		ItemStack stack = null;
		
		if(this.inventory[slot] != null)
		{
			if(this.inventory[slot].stackSize <= amount)
			{
				stack = this.inventory[slot];
				this.inventory[slot] = null;
				this.onInventoryChanged();
			}
			else
			{
				stack = this.inventory[slot].splitStack(amount);
				
				if(this.inventory[slot].stackSize == 0)
				{
					this.inventory[slot] = null;
				}
				
				this.onInventoryChanged();
			}
		}
		
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		ItemStack stack = getStackInSlot(slot);
		if(stack != null)
		{
			this.setInventorySlotContents(slot, null);
		}
		
		return stack;
	}

	@Override
	public String getInvName()
	{
		return "Fancy Workbench";
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
	public void openChest(){}

	@Override
	public void closeChest(){}

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack)
	{
		return false;
	}
	
	
	
	@Override
	public int[] getAccessibleSlotsFromSide(int side)
	{
		return null;
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack itemstack, int side)
	{
		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack itemstack, int side)
	{
		return false;
	}
}
