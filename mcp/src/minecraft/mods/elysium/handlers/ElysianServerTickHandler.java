package mods.elysium.handlers;

import java.util.EnumSet;
import java.util.Random;

import mods.elysium.Elysium;
import mods.elysium.api.temperature.IBlockHeatable;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ElysianServerTickHandler implements ITickHandler
{
	@Override
	public String getLabel()
	{
		return "Elysian Common Tick Handler";
	}
	
	@Override
	public EnumSet<TickType> ticks()
	{
		return EnumSet.of(TickType.SERVER);
	}
	
	public static final int calculateDistance = 32;
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData)
	{
		if(type.equals(EnumSet.of(TickType.SERVER)))
		{
			for(WorldServer world : MinecraftServer.getServer().worldServers)
			{
				Random random = new Random(System.currentTimeMillis());
				
				for(Object obj : world.playerEntities)
				{
					Entity entity = (Entity)obj;
					
					for(int x = (int)entity.posX-calculateDistance; x <= (int)entity.posX+calculateDistance; x++)
					{
						for(int z = (int)entity.posZ-calculateDistance; z <= (int)entity.posZ+calculateDistance; z++)
						{
							for(int y = (int)entity.posY-calculateDistance; y <= (int)entity.posY+calculateDistance; y++)
							{
								if((Block.blocksList[world.getBlockId(x, y, z)] instanceof IBlockHeatable) && (random.nextInt(Block.blocksList[world.getBlockId(x, y, z)].tickRate(world)) == 0))
								{
									((IBlockHeatable)Block.blocksList[world.getBlockId(x, y, z)]).updateTemperature(world, x, y, z, random);
								}
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData)
	{
		
	}
}
