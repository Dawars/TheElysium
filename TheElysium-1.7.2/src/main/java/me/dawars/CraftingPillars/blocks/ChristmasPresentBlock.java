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
	private static ItemStack[] presents;

	public static void init()
	{
		// TODO presents
		presents = new ItemStack[]{
//				new ItemStack(CraftingPillars.blockChristmasPresent, 1, 0),
//				new ItemStack(CraftingPillars.blockChristmasPresent, 1, 1),
//				new ItemStack(CraftingPillars.itemWinterFood2013, 2, 0),
//				new ItemStack(CraftingPillars.itemWinterFood2013, 3, 1),
//				new ItemStack(CraftingPillars.itemWinterFood2013, 4, 2),
//				new ItemStack(CraftingPillars.itemWinterFood2013, 3, 3),
//				new ItemStack(CraftingPillars.itemWinterFood2013, 5, 4),
//				new ItemStack(CraftingPillars.itemWinterFood2013, 4, 5),
//				new ItemStack(CraftingPillars.itemWinterFood2013, 2, 6),
//				new ItemStack(CraftingPillars.itemWinterFood2013, 1, 7),
//				new ItemStack(CraftingPillars.itemWinterFood2013, 4, 8),
//				new ItemStack(CraftingPillars.itemElysiumLoreBook, 1, 0),
				new ItemStack(CraftingPillars.blockCraftingPillar, 1, 0),
				new ItemStack(CraftingPillars.blockBasePillar, 3, 0)
//				new ItemStack(CraftingPillars.itemRibbonDiamond, 2, 0),
//				new ItemStack(CraftingPillars.itemDiscElysium, 1, 0)
		};
	}

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
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player)
	{
		if(!world.isRemote)
		{
			EntityItem item = new EntityItem(world, x+0.5D, y+0.5D, z+0.5D);
			item.setEntityItemStack(presents[world.rand.nextInt(presents.length)].copy());
//			if(item.getEntityItem().isItemEqual(new ItemStack(CraftingPillars.blockChristmasPresent)))
//				player.addStat(CraftingPillars.achievementRecursion3, 1);
			world.spawnEntityInWorld(item);
			world.setBlockToAir(x, y, z);
		}
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
