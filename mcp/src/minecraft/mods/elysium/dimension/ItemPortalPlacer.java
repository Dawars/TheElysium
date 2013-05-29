package mods.elysium.dimension;

import mods.elysium.Elysium;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemPortalPlacer extends Item {
	public ItemPortalPlacer(int par1) {
		super(par1);
		setCreativeTab(CreativeTabs.tabTools);
	}

	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer entity, World world, int X, int Y, int Z, int par7, float par8, float par9, float par10) {
		int x = MathHelper.floor_double(entity.posX);
		int z = MathHelper.floor_double(entity.posZ);
		int y;
		for(y = world.getActualHeight()-1; (y >= 0) && (world.isAirBlock(x, y, z)); y--);
		
		world.setBlock(x, y+9, z, Elysium.portalCore.blockID);
		world.setBlockMetadataWithNotify(x, y+9, z, 1, 2);
		for(int i=-1; i <= 1; i++)
		{
			for(int j=-1; j <= 1; j++)
			{
				world.setBlock(x+i, y+8, z+j, Block.blockNetherQuartz.blockID);
				world.setBlockMetadataWithNotify(x+i, y+8, z+j, 1, 0);
				
				world.setBlock(x+i, y+6, z+j, Block.blockNetherQuartz.blockID);
				world.setBlockMetadataWithNotify(x+i, y+6, z+j, 2, 0);
				world.setBlock(x+i, y+5, z+j, Block.blockNetherQuartz.blockID);
				world.setBlockMetadataWithNotify(x+i, y+5, z+j, 2, 0);
				
				world.setBlock(x+i, y+4, z+j, Block.blockGold.blockID);
				
				world.setBlock(x+i, y+3, z+j, Block.blockNetherQuartz.blockID);
				world.setBlockMetadataWithNotify(x+i, y+3, z+j, 2, 0);
				world.setBlock(x+i, y+2, z+j, Block.blockNetherQuartz.blockID);
				world.setBlockMetadataWithNotify(x+i, y+2, z+j, 2, 0);
			}
		}
		for(int i=-2; i <= 2; i++)
		{
			for(int j=-2; j <= 2; j++)
			{
				world.setBlock(x+i, y+7, z+j, Block.blockNetherQuartz.blockID);
				world.setBlock(x+i, y+1, z+j, Block.blockNetherQuartz.blockID);
			}
		}
		
		return true;
	}
}