package mods.elysium.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ElysianEntityDrachma2 extends Entity
{
	private int size;
	private int tick;
	
	public ElysianEntityDrachma2(World world, double x, double y, double z, int size) 
	{
		super(world);
		this.posX = x;
		this.posY = y;
		this.posZ = z;
		this.size = size;
		this.tick = 0;
	}
	
	@Override
	public void onUpdate()
	{
		this.tick++;
		if(tick >= 360) tick = 0;
		this.setRotation(0, tick);
		super.onUpdate();
	}

	@Override
	protected void entityInit()
	{
		
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound nbtTagCompound)
	{
		this.size = nbtTagCompound.getInteger("size");
	}
	
	@Override
	protected void writeEntityToNBT(NBTTagCompound nbtTagCompound)
	{
		nbtTagCompound.setInteger("size", this.size);
	}
}
