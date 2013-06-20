package mods.elysium.block;

import java.awt.Color;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.elysium.DefaultProps;
import mods.elysium.Elysium;
import mods.elysium.api.temperature.IBlockHeatable;
import mods.elysium.api.temperature.TemperatureManager;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ElysianBlockPalestone extends ElysianBlock implements IBlockHeatable
{
	public ElysianBlockPalestone(int id, Material mat)
	{
		super(id, mat);
		this.setTickRandomly(true);
	}
	
	@Override
	/**
	 * Returns the ID of the items to drop on destruction.
	 */
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return Elysium.blockCobblePalestone.blockID;
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
		//System.out.println("walk");
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
		int meta = TemperatureManager.getBlockMetadataFromTemperature(this.blockID, 20);
		if(meta < 0) meta = 0;
		if(meta > 15) meta = 15;
		world.setBlockMetadataWithNotify(x, y, z, meta, 2);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z)
	{
		int heat = TemperatureManager.getBlockTemperature(this.blockID, blockAccess.getBlockMetadata(x, y, z), this.blockMaterial) - 20;
		
		int red = 255+heat;
		if(red < 0) red = 0;
		if(red > 255) red = 255;
		
		int green = 255-Math.abs(heat);
		if(green < 0) green = 0;
		if(green > 255) green = 255;
		
		int blue = 255-heat;
		if(blue < 0) blue = 0;
		if(blue > 255) blue = 255;
		
		Color color = new Color(red, green, blue);
		
		if(Elysium.isAprilFools)
			return (super.colorMultiplier(blockAccess, x, y, z) + Integer.parseInt(Integer.toHexString(color.getRGB()).substring(2), 16))/2;
		else
			return Integer.parseInt(Integer.toHexString(color.getRGB()).substring(2), 16);
	}
	
	@Override
	public void updateTemperature(World world, int x, int y, int z)
	{
		int temperature = 0;
		
		for(int i = -1; i <= 1; i++)
			for(int j = -1; j <= 1; j++)
				for(int k = -1; k <= 1; k++)
					if((i != 0) || (j != 0) || (k != 0))
						temperature += TemperatureManager.getBlockTemperature(world.getBlockId(x+i, y+j, z+k), world.getBlockMetadata(x+i, y+j, z+k), world.getBlockMaterial(x+i, y+j, z+k));
		
		temperature /= 25; //26
		
		int meta = world.getBlockMetadata(x, y, z);
		meta += (TemperatureManager.getBlockMetadataFromTemperature(this.blockID, temperature) - meta)/2;
		if(meta < 0) meta = 0;
		if(meta > 15) meta = 15;
		
		world.setBlockMetadataWithNotify(x, y, z, (int)Math.round(meta), 2);
	}
}
