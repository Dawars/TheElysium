package mods.elysium.handlers;

import java.util.EnumSet;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ElysianClientTickHandler implements ITickHandler
{
	@Override
	public String getLabel()
	{
		return "Elysian Client Tick Handler";
	}
	
	@Override
	public EnumSet<TickType> ticks()
	{
		return EnumSet.of(TickType.CLIENT);
	}
	
	public static int tick = 0;
	
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData)
	{
		if(type.equals(EnumSet.of(TickType.CLIENT)))
		{
			tick++;
			if(tick >= 20) tick = 0;
		}
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData)
	{
		
	}
}
