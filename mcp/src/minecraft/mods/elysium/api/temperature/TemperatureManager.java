package mods.elysium.api.temperature;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class TemperatureManager
{
	private static List<Temperature> temps = new ArrayList<Temperature>();
	public static int calculateDistance = 32;
	public static int temperatureDistance = 2;
	
	public static void addBlockTemperature(Temperature temp)
	{
		temps.add(temp);
	}
	
	public static int getBlockTemperature(int id, int meta, Material mat)
	{
		for(Temperature temp : temps)
		{
			if(temp.id == id)
			{
				if(temp instanceof RangedTemperature)
				{
					return Math.round(meta*(temp.temp - temp.meta)/15F + temp.meta);
				}
				
				if((temp.meta == -1) || (temp.meta == meta))
				{
					return temp.temp;
				}
			}
		}
		
		if(mat == Material.craftedSnow)
		{
			return -10;
		}
		
		if(mat == Material.fire)
		{
			return 300;
		}
		
		if(mat == Material.ice)
		{
			return -50;
		}
		
		if(id == Block.lavaMoving.blockID)
		{
			return 700;
		}
		
		if(mat == Material.lava)
		{
			return 1200;
		}
		
		if(mat == Material.redstoneLight)
		{
			return 40;
		}
		
		if(mat == Material.sand)
		{
			return 35;
		}
		
		if(mat == Material.snow)
		{
			return -10;
		}
		
		if(mat == Material.water)
		{
			return 5;
		}
		
		return 30;
	}
	
	public static int getBlockMetadataFromTemperature(int id, int temperature)
	{
		for(Temperature temp : temps)
		{
			if(temp.id == id)
			{
				int meta = (int)Math.round((double)(temperature - temp.meta)*15D/(double)(temp.temp - temp.meta));
				if(meta < 0) meta = 0;
				if(meta > 15) meta = 15;
				return meta;
			}
		}
		
		return 0;
	}
	
	public static int getTemperatureForHeight(int h)
	{
		return 100-h;
	}
}
