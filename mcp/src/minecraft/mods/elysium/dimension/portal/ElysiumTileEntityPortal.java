package mods.elysium.dimension.portal;

import net.minecraft.tileentity.TileEntity;

public class ElysiumTileEntityPortal extends TileEntity
{
	public boolean canstay = false;
	byte tick;
	
	@Override
	public void updateEntity()
	{
		if(worldObj.isRemote)
		{
			tick++;
			if(tick >= 20)
			{
				tick = 0;
				canstay = ElysiumBlockPortalCore.canStayPortal(worldObj, xCoord, yCoord, zCoord);
			}
		}
	}
}
