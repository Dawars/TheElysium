package mods.elysium.dimension.portal;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mods.elysium.Elysium;
import mods.elysium.block.ElysiumBlockContainer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ElysiumBlockPortalCore extends ElysiumBlockContainer
{
	public ElysiumBlockPortalCore(int id, Material mat)
	{
		super(id, mat);
		this.setBlockBounds(0, 0, 0, 1, 0.9375F, 1);
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return Elysium.GracePrismItem.itemID;
	}
	
	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player)
    {
        dropBlockAsItem(world, x, y, z, 0, 0);
        world.setBlock(x, y, z, Block.dragonEgg.blockID);
    }
	
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if((world.getBlockMetadata(x, y, z) == 1) && (entity.riddenByEntity == null) && (entity.ridingEntity == null))
		{
			if(entity instanceof EntityPlayerMP)
			{
				EntityPlayerMP player = (EntityPlayerMP) entity;
				ElysiumTileEntityPortal tile = (ElysiumTileEntityPortal)world.getBlockTileEntity(x, y, z);
				
				if(tile.timebeforetp == 0)
				{
					tile.timebeforetp = -1;
					
					if(player.dimension == Elysium.DimensionID)
					{
						player.mcServer.getConfigurationManager().transferPlayerToDimension(player, 0, new ElysiumTeleporter(player.mcServer.worldServerForDimension(0)));
					}
					else
					{
						player.mcServer.getConfigurationManager().transferPlayerToDimension(player, Elysium.DimensionID, new ElysiumTeleporter(player.mcServer.worldServerForDimension(Elysium.DimensionID)));
					}
				}
				else if(player.prevPosY == player.posY)
				{
					tile.wasCollided = true;
					if(tile.timebeforetp == -1) tile.timebeforetp = 200;
				}
			}
			else
			{
				//System.err.println("error");
			}
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new ElysiumTileEntityPortal();
	}
}
