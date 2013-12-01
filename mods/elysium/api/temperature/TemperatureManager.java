package mods.elysium.api.temperature;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class TemperatureManager
{
	private static List<Temperature> temps = new ArrayList<Temperature>();
	public static int calculateDistance = 32;
	public static int temperatureDistance = 3;
	
	public static void addBlockTemperature(Temperature temp)
	{
		temps.add(temp);
	}
	
	public static int getBlockTemperatureAt(World world, int x, int y, int z)
	{
		int id = world.getBlockId(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);
		Material mat = world.getBlockMaterial(x, y, z);
		int defTemp = Math.round(world.getBiomeGenForCoords(x, z).getFloatTemperature()*10F) + getTemperatureForHeight(y);
		
		for(Temperature temp : temps)
		{
			if(temp.id == id)
			{
				if(temp instanceof RangedTemperature)
				{
					return defTemp + Math.round(meta*(temp.temp - temp.meta)/15F + temp.meta);
				}
				
				if((temp.meta == -1) || (temp.meta == meta))
				{
					return defTemp + temp.temp;
				}
			}
		}
		
		if(mat == Material.craftedSnow)
		{
			return defTemp - 10;
		}
		
		if(mat == Material.fire)
		{
			return defTemp + 300;
		}
		
		if(mat == Material.ice)
		{
			return defTemp - 50;
		}
		
		if(id == Block.lavaMoving.blockID)
		{
			return defTemp + 700;
		}
		
		if(mat == Material.lava)
		{
			return defTemp + 1200;
		}
		
		if(mat == Material.redstoneLight)
		{
			return defTemp + 40;
		}
		
		if(mat == Material.sand)
		{
			return defTemp + 35;
		}
		
		if(mat == Material.snow)
		{
			return defTemp - 10;
		}
		
		if(mat == Material.water)
		{
			return defTemp + 5;
		}
		
		return defTemp + 20;
	}
	
	public static int getBlockTemperatureAt(IBlockAccess blockAccess, int x, int y, int z)
	{
		int id = blockAccess.getBlockId(x, y, z);
		int meta = blockAccess.getBlockMetadata(x, y, z);
		Material mat = blockAccess.getBlockMaterial(x, y, z);
		int defTemp = Math.round(blockAccess.getBiomeGenForCoords(x, z).getFloatTemperature()*10F) + getTemperatureForHeight(y);
		
		for(Temperature temp : temps)
		{
			if(temp.id == id)
			{
				if(temp instanceof RangedTemperature)
				{
					return defTemp + Math.round(meta*(temp.temp - temp.meta)/15F + temp.meta);
				}
				
				if((temp.meta == -1) || (temp.meta == meta))
				{
					return defTemp + temp.temp;
				}
			}
		}
		
		if(mat == Material.craftedSnow)
		{
			return defTemp - 10;
		}
		
		if(mat == Material.fire)
		{
			return defTemp + 300;
		}
		
		if(mat == Material.ice)
		{
			return defTemp - 50;
		}
		
		if(id == Block.lavaMoving.blockID)
		{
			return defTemp + 700;
		}
		
		if(mat == Material.lava)
		{
			return defTemp + 1200;
		}
		
		if(mat == Material.redstoneLight)
		{
			return defTemp + 40;
		}
		
		if(mat == Material.sand)
		{
			return defTemp + 35;
		}
		
		if(mat == Material.snow)
		{
			return defTemp - 10;
		}
		
		if(mat == Material.water)
		{
			return defTemp + 5;
		}
		
		return defTemp + 20;
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
		return (int)(30 - Math.pow(h, 0.75));
	}
	
	public static int getTemperatureAt(World world, int x, int y, int z)
	{
		int temp = 0;
		double div = 0D;
		
		for(int i = -temperatureDistance; i <= temperatureDistance; i++)
		{
			for(int j = -temperatureDistance; j <= temperatureDistance; j++)
			{
				for(int k = -temperatureDistance; k <= temperatureDistance; k++)
				{
					int d = i*i + j*j + k*k;
					if(d <= temperatureDistance*temperatureDistance)
					{
						temp += getBlockTemperatureAt(world, x+i, y+j, z+k)/Math.sqrt(d+1);
						div += 1/Math.sqrt(d+1);
					}
				}
			}
		}
		
		temp /= div;
		return temp;
	}
}
