package mods.elysium.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.elysium.DefaultProps;
import mods.elysium.Elysium;
import mods.elysium.entity.tileentity.TileEntityFancyTank;
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

public class ElysianBlockFancyTank extends ElysianBlockContainer
{
	public ElysianBlockFancyTank(int id)
	{
		super(id, Material.rock);
	}
	
	@Override
	public int getRenderType()
	{
		return Elysium.fancyTankRenderID;
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
		return true;
	}
	
//	@Override
//	public void breakBlock(World world, int x, int y, int z, int par5, int par6)
//	{
//		TileEntityFancyWorkbench workTile = (TileEntityFancyWorkbench) world.getBlockTileEntity(x, y, z);
//		
//		for(int i = 0; i < 3; i++)
//		{
//			for(int k = 0; k < 3; k++)
//			{
//				if(workTile.getStackInSlot(i*3 + k) != null)
//				{
//					EntityItem itemDropped = new EntityItem(world, x + 0.1875D+i*0.3125D, y+1D, z + 0.1875D+k*0.3125D, workTile.getStackInSlot(i*3 + k));
//					itemDropped.motionX = itemDropped.motionY = itemDropped.motionZ = 0D;
//					
//					if(!world.isRemote)
//						world.spawnEntityInWorld(itemDropped);
//					
//					if(workTile.getStackInSlot(i*3 + k).hasTagCompound())
//					{
//						itemDropped.getEntityItem().setTagCompound((NBTTagCompound)workTile.getStackInSlot(i*3 + k).getTagCompound().copy());
//					}
//				}
//			}
//		}
//		
//		super.breakBlock(world, x, y, z, par5, par6);
//	}

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		TileEntityFancyTank tile = new TileEntityFancyTank();
		return tile;
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister itemIcon)
    {
        this.blockIcon = itemIcon.registerIcon(DefaultProps.modId + ":palestone_pillar_side");
    }
	
	@Override
    @SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2)
	{
		return this.blockIcon;
	}
}
