package mods.elysium.dimension.portal;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mods.elysium.DefaultProps;
import mods.elysium.Elysium;
import mods.elysium.block.ElysianBlockContainer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class ElysianBlockPortalCore extends ElysianBlockContainer
{
	public ElysianBlockPortalCore(int id, Material mat)
	{
		super(id, mat);
		this.setBlockBounds(0.5F, 1F, 0.5F, 0.5F, 1F, 0.5F);
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World wolrd, int x, int y, int z)
	{
		return null;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if((entity.riddenByEntity == null) && (entity.ridingEntity == null))
		{
			if(entity instanceof EntityPlayerMP)
			{
				EntityPlayerMP player = (EntityPlayerMP) entity;
				ElysianTileEntityPortal tile = (ElysianTileEntityPortal)world.getBlockTileEntity(x, y, z);
				
				if(tile.timebeforetp == 0)
				{
					tile.timebeforetp = -1;
					
					if(player.dimension == Elysium.DimensionID)
					{
						player.mcServer.getConfigurationManager().transferPlayerToDimension(player, 0, new ElysianTeleporter(player.mcServer.worldServerForDimension(0)));
					}
					else
					{
						player.mcServer.getConfigurationManager().transferPlayerToDimension(player, Elysium.DimensionID, new ElysianTeleporter(player.mcServer.worldServerForDimension(Elysium.DimensionID)));
					}
				}
				else if(player.prevPosY == player.posY)
				{
					tile.wasCollided = true;
					if(tile.timebeforetp == -1) tile.timebeforetp = DefaultProps.ticksbeforeportalteleport;
				}
			}
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new ElysianTileEntityPortal();
	}
}
