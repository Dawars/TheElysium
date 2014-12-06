package me.dawars.CraftingPillars.blocks;

import me.dawars.CraftingPillars.CraftingPillars;
import me.dawars.CraftingPillars.tiles.TileEntityChristmasPresent;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ChristmasPresentBlock extends BaseBlockContainer
{
	

	public ChristmasPresentBlock(Material mat)
	{
		super(mat);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		if(world.getBlockMetadata(x, y, z) == 1)
			this.setBlockBounds(0F, 0F, 0F, 1F, 7/16F, 1F);
		else
			this.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
	{
		return world.rand.nextInt(2);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float i, float j, float k)
	{
		if(!world.isRemote && player.getCurrentEquippedItem() == null)
		{
			EntityItem item = new EntityItem(world, x+0.5D, y+0.5D, z+0.5D);
			item.setEntityItemStack(CraftingPillars.present_loot[world.rand.nextInt(CraftingPillars.present_loot.length)].copy());
			if(item.getEntityItem().isItemEqual(new ItemStack(CraftingPillars.blockChristmasPresent)))
				player.addStat(CraftingPillars.achievementRecursion3, 1);
			world.spawnEntityInWorld(item);
			world.setBlockToAir(x, y, z);
		}
		
		return super.onBlockActivated(world, x, y, z, player, meta, i, j, k);
	}		

	@Override
	public int getRenderType()
	{
		return CraftingPillars.PresentRenderID;
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
	public TileEntity createTileEntity(World world, int meta)
	{
		TileEntityChristmasPresent tile = new TileEntityChristmasPresent();
		return tile;
	}
}
