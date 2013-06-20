package mods.elysium.block;

import java.awt.Color;
import java.util.Random;

import mods.elysium.Elysium;
import mods.elysium.api.temperature.IBlockHeatable;
import mods.elysium.api.temperature.RangedTemperature;
import mods.elysium.api.temperature.TemperatureManager;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ElysianBlockHeatable extends ElysianBlock implements IBlockHeatable
{
	public int minTemp = -273;
	public int maxTemp = 300;
	
	public ElysianBlockHeatable(int id, Material mat, int minTemp, int maxTemp)
	{
		super(id, mat);
		this.setTickRandomly(true);
		this.minTemp = minTemp;
		this.maxTemp = maxTemp;
		TemperatureManager.addBlockTemperature(new RangedTemperature(id, minTemp, maxTemp));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		for(int i = 0; i < (world.getBlockMetadata(x, y, z)-7)/2; i++)
		{
			world.spawnParticle("smoke", x+random.nextDouble(), y+random.nextDouble(), z+random.nextDouble(), 0, 0, 0);
		}
	}
	
	@Override
	public void onEntityWalking(World world, int x, int y, int z, Entity entity)
	{
		entity.attackEntityFrom(DamageSource.onFire, Math.abs(TemperatureManager.getBlockTemperature(this.blockID, world.getBlockMetadata(x, y, z), this.blockMaterial)-20)*(world.difficultySetting+1)/100);
	}
	
	@Override
	public int tickRate(World world)
	{
		return 20;
	}
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		int meta = TemperatureManager.getBlockMetadataFromTemperature(this.blockID, TemperatureManager.getTemperatureForHeight(y));
		if(meta < 0) meta = 0;
		if(meta > 15) meta = 15;
		world.setBlockMetadataWithNotify(x, y, z, meta, 2);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z)
	{
		int moss = 0;
		if(blockAccess.getBlockMaterial(x+1, y, z) == Material.water)
			moss += 20;
		if(blockAccess.getBlockMaterial(x-1, y, z) == Material.water)
			moss += 20;
		if(blockAccess.getBlockMaterial(x, y+1, z) == Material.water)
			moss += 20;
		if(blockAccess.getBlockMaterial(x, y-1, z) == Material.water)
			moss += 20;
		if(blockAccess.getBlockMaterial(x, y, z+1) == Material.water)
			moss += 20;
		if(blockAccess.getBlockMaterial(x, y, z-1) == Material.water)
			moss += 20;
		
		int heat = TemperatureManager.getBlockTemperature(this.blockID, blockAccess.getBlockMetadata(x, y, z), this.blockMaterial) - 33;
		
		int red = 255 + heat - moss;
		if(red < 0) red = 0;
		if(red > 255) red = 255;
		
		int green = 255 - Math.abs(heat);
		if(green < 0) green = 0;
		if(green > 255) green = 255;
		
		int blue = 255 - heat - moss;
		if(blue < 0) blue = 0;
		if(blue > 255) blue = 255;
		
		Color color = new Color(red, green, blue);
		
		if(Elysium.isAprilFools)
			return (super.colorMultiplier(blockAccess, x, y, z) + Integer.parseInt(Integer.toHexString(color.getRGB()).substring(2), 16)*2)/3;
		else
			return Integer.parseInt(Integer.toHexString(color.getRGB()).substring(2), 16);
	}
	
	@Override
	public void updateTemperature(World world, int x, int y, int z, Random random)
	{
		int temperature = 0;
		double div = 0D;
		
		for(int i = -TemperatureManager.temperatureDistance; i <= TemperatureManager.temperatureDistance; i++)
		{
			for(int j = -TemperatureManager.temperatureDistance; j <= TemperatureManager.temperatureDistance; j++)
			{
				for(int k = -TemperatureManager.temperatureDistance; k <= TemperatureManager.temperatureDistance; k++)
				{
					int d = i*i + j*j + k*k;
					if(d <= TemperatureManager.temperatureDistance*TemperatureManager.temperatureDistance)
					{
						int temp = TemperatureManager.getBlockTemperature(world.getBlockId(x+i, y+j, z+k), world.getBlockMetadata(x+i, y+j, z+k), world.getBlockMaterial(x+i, y+j, z+k));
						temperature += temp/Math.sqrt(d+1);
						div += 1/Math.sqrt(d+1);
					}
				}
			}
		}
		
		temperature /= div;
		world.setBlockMetadataWithNotify(x, y, z, TemperatureManager.getBlockMetadataFromTemperature(this.blockID, temperature), 2);
		
		if((temperature > this.maxTemp) && (random.nextInt(5) == 0))
		{
			world.setBlock(x, y, z, Block.lavaStill.blockID);
		}
		
		if((temperature < this.minTemp) && (random.nextInt(5) == 0))
		{
			world.setBlock(x, y, z, Block.ice.blockID);
		}
	}
}
