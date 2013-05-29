package mods.elysium.dimension.portal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import mods.elysium.Elysium;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.Direction;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.PortalPosition;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class ElysiumTeleporter extends Teleporter
{
	private final WorldServer worldServer;
    private final Random random;
	
    /** Stores successful portal placement locations for rapid lookup. */
	private final LongHashMap destinationCoordinateCache = new LongHashMap();
	/**
	 * A list of valid keys for the destinationCoordainteCache. These are based
	 * on the X & Z of the players initial location.
	 */
	private final List destinationCoordinateKeys = new ArrayList();
	
	public ElysiumTeleporter(WorldServer worldServer)
	{
		super(worldServer);
		this.worldServer = worldServer;
		this.random = new Random(worldServer.getSeed());
	}
	
	@Override
	public void placeInPortal(Entity entity, double par2, double par4, double par6, float par8)
	{
		if(!this.placeInExistingPortal(entity, par2, par4, par6, par8))
		{
			this.makePortal(entity);
			this.placeInExistingPortal(entity, par2, par4, par6, par8);
		}
	}
	
	@Override
	public boolean placeInExistingPortal(Entity entity, double par2, double par4, double par6, float par8)
	{
		int i = MathHelper.floor_double(entity.posX);
		int k = MathHelper.floor_double(entity.posZ);
		int j = 0;
		
		long chunkPair = ChunkCoordIntPair.chunkXZ2Int(i, k);
		boolean flag = true;
		
		if (this.destinationCoordinateCache.containsItem(chunkPair)) {
			ElysiumPortalPosition portalposition = (ElysiumPortalPosition) this.destinationCoordinateCache.getValueByKey(chunkPair);

			i = portalposition.posX;
			j = portalposition.posY;
			k = portalposition.posZ;
			portalposition.lastUpdateTime = this.worldServer.getTotalWorldTime();
			flag = false;
		} else {
			if (flag) {
				this.destinationCoordinateCache.add(chunkPair, new ElysiumPortalPosition(this, i, j, k, this.worldServer.getTotalWorldTime()));
				this.destinationCoordinateKeys.add(Long.valueOf(chunkPair));
			}
		}
		
		for(j = worldServer.getActualHeight()-1; (j >= 0) && (worldServer.isAirBlock(i, j, k)); j--);
		if(worldServer.getBlockId(i, j, k) != Elysium.portalCore.blockID)
		{
			return false;
		}
		entity.motionX = entity.motionY = entity.motionZ = 0.0D;
		entity.setPosition(i+0.5D, j+1, k+0.5D);
		return true;
	}
	
	@Override
	public boolean makePortal(Entity entity)
	{
		int x = MathHelper.floor_double(entity.posX);
		int z = MathHelper.floor_double(entity.posZ);
		int y;
		for(y = worldServer.getActualHeight()-1; (y >= 0) && (worldServer.isAirBlock(x, y, z)); y--);
		
		worldServer.setBlock(x, y+9, z, Elysium.portalCore.blockID);
		worldServer.setBlockMetadataWithNotify(x, y+9, z, 1, 0);
		for(int i=-1; i <= 1; i++)
		{
			for(int j=-1; j <= 1; j++)
			{
				worldServer.setBlock(x+i, y+8, z+j, Block.blockNetherQuartz.blockID);
				worldServer.setBlockMetadataWithNotify(x+i, y+8, z+j, 1, 0);
				
				worldServer.setBlock(x+i, y+6, z+j, Block.blockNetherQuartz.blockID);
				worldServer.setBlockMetadataWithNotify(x+i, y+6, z+j, 2, 0);
				worldServer.setBlock(x+i, y+5, z+j, Block.blockNetherQuartz.blockID);
				worldServer.setBlockMetadataWithNotify(x+i, y+5, z+j, 2, 0);
				
				worldServer.setBlock(x+i, y+4, z+j, Block.blockGold.blockID);
				
				worldServer.setBlock(x+i, y+3, z+j, Block.blockNetherQuartz.blockID);
				worldServer.setBlockMetadataWithNotify(x+i, y+3, z+j, 2, 0);
				worldServer.setBlock(x+i, y+2, z+j, Block.blockNetherQuartz.blockID);
				worldServer.setBlockMetadataWithNotify(x+i, y+2, z+j, 2, 0);
			}
		}
		for(int i=-2; i <= 2; i++)
		{
			for(int j=-2; j <= 2; j++)
			{
				worldServer.setBlock(x+i, y+7, z+j, Block.blockNetherQuartz.blockID);
				worldServer.setBlock(x+i, y+1, z+j, Block.blockNetherQuartz.blockID);
			}
		}
		
		return true;
	}
}
