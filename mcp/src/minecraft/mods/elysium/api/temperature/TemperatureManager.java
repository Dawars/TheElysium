package mods.elysium.api.temperature;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class TemperatureManager
{
	private static List<Temperature> temps = new ArrayList<Temperature>();
	
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
					return meta*(temp.temp - temp.meta)/15 + temp.meta;
				}
				
				if((temp.meta == -1) || (temp.meta == meta))
				{
					return temp.temp;
				}
			}
		}
		
		if(mat == Material.craftedSnow)
		{
			return -20;
		}
		
		if(mat == Material.fire)
		{
			return 300;
		}
		
		if(mat == Material.ice)
		{
			return -1000;
		}
		
		if(mat == Material.lava)
		{
			return 1000;
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
			return -20;
		}
		
		if(mat == Material.water)
		{
			return 5;
		}
		
		return 20;
	}
	
	public static int getBlockMetadataFromTemperature(int id, int temperature)
	{
		for(Temperature temp : temps)
		{
			if(temp.id == id)
			{
				int meta = (temperature - temp.meta)*15/(temp.temp - temp.meta);
				if(meta < 0) meta = 0;
				if(meta > 15) meta = 15;
				return meta;
			}
		}
		
		return 0;
	}
}
