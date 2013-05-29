package mods.elysium.dimension.portal;

import mods.elysium.DefaultProps;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;

public class ElysiumTileEntityPortal extends TileEntity
{
	public boolean canstay = false;
	public ElysiumPortalPosition coords;
	byte tick;
	public int timebeforetp = -1;
	public boolean wasCollided = false;
	public int ticksWithoutColliding = 0;
	
	@Override
	public void updateEntity()
	{
		if(!wasCollided) ticksWithoutColliding++;
		if(wasCollided) ticksWithoutColliding = 0;
		if(ticksWithoutColliding > 5)
		{
			timebeforetp = -1;
		}
		if(timebeforetp > 0)
		{
			timebeforetp--;
			//System.out.println(timebeforetp);
		}
		wasCollided = false;
		
		if(coords == null)
		{
			coords = new ElysiumPortalPosition(worldObj.provider.dimensionId, xCoord, yCoord, zCoord);
		}
		
		tick++;
		if(tick >= DefaultProps.ticksbeforeportalcheck)
		{
			tick = 0;
			canstay = canStayPortal();
		}
		
		if(canstay)
		{
			if(!ElysiumTeleporter.portals.contains(coords))
			{
				ElysiumTeleporter.portals.add(coords);
			}
			if(worldObj.getBlockMetadata(xCoord, yCoord, zCoord) == 0)
			{
				worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 0);
			}
		}
		else
		{
			if(ElysiumTeleporter.portals.contains(coords))
			{
				ElysiumTeleporter.portals.remove(coords);
			}
			if(worldObj.getBlockMetadata(xCoord, yCoord, zCoord) == 1)
			{
				worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 0);
			}
		}
	}
	
	public boolean canStayPortal()
	{
		boolean canstay = true;
		
		for(int i=-2; i<=2; i++)
		{
			for(int j=-2; j<=2; j++)
			{
				if(worldObj.getBlockId(xCoord+i, yCoord-2, zCoord+j) != Block.blockNetherQuartz.blockID) canstay = false;
				if(worldObj.getBlockMetadata(xCoord+i, yCoord-2, zCoord+j) != 0) canstay = false;
				if(worldObj.getBlockId(xCoord+i, yCoord-8, zCoord+j) != Block.blockNetherQuartz.blockID) canstay = false;
				if(worldObj.getBlockMetadata(xCoord+i, yCoord-8, zCoord+j) != 0) canstay = false;
			}
		}
		for(int i=-1; i<=1; i++)
		{
			for(int j=-1; j<=1; j++)
			{
				if(worldObj.getBlockId(xCoord+i, yCoord-1, zCoord+j) != Block.blockNetherQuartz.blockID) canstay = false;
				if(worldObj.getBlockMetadata(xCoord+i, yCoord-1, zCoord+j) != 1) canstay = false;
				
				if(worldObj.getBlockId(xCoord+i, yCoord-3, zCoord+j) != Block.blockNetherQuartz.blockID) canstay = false;
				if(worldObj.getBlockMetadata(xCoord+i, yCoord-3, zCoord+j) != 2) canstay = false;
				if(worldObj.getBlockId(xCoord+i, yCoord-4, zCoord+j) != Block.blockNetherQuartz.blockID) canstay = false;
				if(worldObj.getBlockMetadata(xCoord+i, yCoord-4, zCoord+j) != 2) canstay = false;
				
				if(worldObj.getBlockId(xCoord+i, yCoord-5, zCoord+j) != Block.blockGold.blockID) canstay = false;
				
				if(worldObj.getBlockId(xCoord+i, yCoord-6, zCoord+j) != Block.blockNetherQuartz.blockID) canstay = false;
				if(worldObj.getBlockMetadata(xCoord+i, yCoord-6, zCoord+j) != 2) canstay = false;
				if(worldObj.getBlockId(xCoord+i, yCoord-7, zCoord+j) != Block.blockNetherQuartz.blockID) canstay = false;
				if(worldObj.getBlockMetadata(xCoord+i, yCoord-7, zCoord+j) != 2) canstay = false;
			}
		}
		
		return canstay;
	}
}
