package mods.elysium.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.elysium.DefaultProps;
import mods.elysium.Elysium;
import mods.elysium.entity.tileentity.TileEntityFancyWorkbench;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ElysianFancyWorkbench extends ElysianBlockContainer
{
	public ElysianFancyWorkbench(int id)
	{
		super(id, Material.rock);
	}
	
	@Override
	public int getRenderType()
	{
		return Elysium.fancyWorkbenchRenderID;
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
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player)
	{
		TileEntityFancyWorkbench workTile = (TileEntityFancyWorkbench) world.getBlockTileEntity(x, y, z);
		
		if(workTile.getStackInSlot(workTile.getSizeInventory()) != null)
		{
			workTile.craftItem(player);
		}
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entity, ItemStack stack)
	{
		int meta = determineOrientation(world, x, y, z, entity);
		
		/*if(!world.isRemote)
			System.out.println(meta);*/
		
		world.setBlockMetadataWithNotify(x, y, z, meta, 0);
	}
	
	public static int determineOrientation(World world, int x, int y, int z, EntityLiving entity)
	{
		//Used for up and down orientation
		
		/*if (MathHelper.abs((float)entity.posX - (float)x) < 2.0F && MathHelper.abs((float)entity.posZ - (float)z) < 2.0F)
		{
			double d0 = entity.posY + 1.82D - (double)entity.yOffset;

			if (d0 - (double)y > 2.0D)
			{
				return 1;
			}

			if ((double)y - d0 > 0.0D)
			{
				return 0;
			}
		}*/

		int l = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		//Direction numbers
		//return l == 0 ? 2 : (l == 1 ? 5 : (l == 2 ? 3 : (l == 3 ? 4 : 0)));
		return l;
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if(hitY == 1F)
		{
			TileEntityFancyWorkbench workTile = (TileEntityFancyWorkbench) world.getBlockTileEntity(x, y, z);
			
			if(player.isSneaking())
			{
				hitX = (float) Math.floor(hitX/0.33F);
				hitZ = (float) Math.floor(hitZ/0.33F);
				int i = (int) (hitX*3 + hitZ);
				
				if(workTile.getStackInSlot(i) != null)
				{
					workTile.dropItemFromSlot(i);
				}
			}
			if(player.getCurrentEquippedItem() != null)
			{
				hitX = (float) Math.floor(hitX/0.33F);
				hitZ = (float) Math.floor(hitZ/0.33F);
				int i = (int) (hitX*3 + hitZ);
				
				if(workTile.getStackInSlot(i) == null)
				{
					if(!player.capabilities.isCreativeMode)
						player.getCurrentEquippedItem().stackSize--;
					
					ItemStack in = new ItemStack(player.getCurrentEquippedItem().itemID, 1, player.getCurrentEquippedItem().getItemDamage());
					workTile.setInventorySlotContents(i, in);
				}
				else if((workTile.getStackInSlot(i).itemID == player.getCurrentEquippedItem().itemID) && (workTile.getStackInSlot(i).stackSize < workTile.getStackInSlot(i).getMaxStackSize()))
				{
					if(!player.capabilities.isCreativeMode)
						player.getCurrentEquippedItem().stackSize--;
					
					workTile.decrStackSize(i, -1);
					workTile.onInventoryChanged();
				}
			}
		}
		
		return true;
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6)
	{
		TileEntityFancyWorkbench workTile = (TileEntityFancyWorkbench) world.getBlockTileEntity(x, y, z);
		
		for(int i = 0; i < 3; i++)
		{
			for(int k = 0; k < 3; k++)
			{
				if(workTile.getStackInSlot(i*3 + k) != null)
				{
					EntityItem itemDropped = new EntityItem(world, x + 0.1875D+i*0.3125D, y+1D, z + 0.1875D+k*0.3125D, workTile.getStackInSlot(i*3 + k));
					itemDropped.motionX = itemDropped.motionY = itemDropped.motionZ = 0D;
					
					if(!world.isRemote)
						world.spawnEntityInWorld(itemDropped);
					
					if(workTile.getStackInSlot(i*3 + k).hasTagCompound())
					{
						itemDropped.getEntityItem().setTagCompound((NBTTagCompound)workTile.getStackInSlot(i*3 + k).getTagCompound().copy());
					}
				}
			}
		}
		
		super.breakBlock(world, x, y, z, par5, par6);
	}

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		TileEntityFancyWorkbench tile = new TileEntityFancyWorkbench();
		return tile;
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister itemIcon)
    {
        this.blockIcon = itemIcon.registerIcon(DefaultProps.modId + ":fancy_workbench_side");
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2) {
		return this.blockIcon;
	}
}
