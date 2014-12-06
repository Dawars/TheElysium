package me.dawars.CraftingPillars.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.dawars.CraftingPillars.CraftingPillars;
import me.dawars.CraftingPillars.tiles.TileEntityShowOffPillar;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ShowOffPillarBlock extends BaseBlockContainer
{
	public ShowOffPillarBlock(Material mat)
	{
		super(mat);
	}

	@Override
	public int getRenderType()
	{
		return CraftingPillars.showOffPillarRenderID;
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
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack)
	{
		world.setBlockMetadataWithNotify(x, y, z, determineOrientation(world, x, y, z, entity), 0);
	}

	public static int determineOrientation(World world, int x, int y, int z, EntityLivingBase entity)
	{
		return MathHelper.floor_double(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if(world.isRemote)
			return true;

		TileEntityShowOffPillar pillarTile = (TileEntityShowOffPillar) world.getTileEntity(x, y, z);

		if(hitY < 1F && !player.isSneaking())
		{
			pillarTile.showNum = !pillarTile.showNum;
			pillarTile.onInventoryChanged();
		}

		if(hitY == 1F)
		{
			if(player.isSneaking())//pick out
			{
				pillarTile.dropItemFromSlot(0, 1, player);
			}
			else if(player.getCurrentEquippedItem() != null)
			{//put in
//				if(player.getCurrentEquippedItem().isItemEqual(new ItemStack(this)))
//					player.addStat(CraftingPillars.achievementShowoff, 1);
				if(pillarTile.getStackInSlot(0) == null)
				{//slot empty
					if(!player.capabilities.isCreativeMode)
						player.getCurrentEquippedItem().stackSize--;

					ItemStack in = player.getCurrentEquippedItem().copy();
					in.stackSize = 1;
					pillarTile.setInventorySlotContents(0, in);
				}
				else if((pillarTile.getStackInSlot(0).isItemEqual(player.getCurrentEquippedItem())) && (pillarTile.getStackInSlot(0).stackSize < pillarTile.getStackInSlot(0).getMaxStackSize()))
				{//slot not empty
					if(!player.capabilities.isCreativeMode)
						player.getCurrentEquippedItem().stackSize--;

					pillarTile.decrStackSize(0, -1);
					pillarTile.onInventoryChanged();
				}
			}
		}
		return true;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int par6)
	{
		if(!world.isRemote)
		{
			TileEntityShowOffPillar workTile = (TileEntityShowOffPillar) world.getTileEntity(x, y, z);

			if(workTile.getStackInSlot(0) != null)
			{
				EntityItem itemDropped = new EntityItem(world, x + 0.1875D, y + 1D, z + 0.1875D, workTile.getStackInSlot(0));
				itemDropped.motionX = itemDropped.motionY = itemDropped.motionZ = 0D;

				if(workTile.getStackInSlot(0).hasTagCompound())
					itemDropped.getEntityItem().setTagCompound((NBTTagCompound) workTile.getStackInSlot(0).getTagCompound().copy());

				world.spawnEntityInWorld(itemDropped);
			}
		}

		super.breakBlock(world, x, y, z, block, par6);
	}

	@Override
	public TileEntity createTileEntity(World world, int meta)
	{
		TileEntityShowOffPillar tile = new TileEntityShowOffPillar();
		return tile;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister itemIcon)
	{
		this.blockIcon = itemIcon.registerIcon(CraftingPillars.ID + ":craftingPillar_side");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2)
	{
		return this.blockIcon;
	}
}
