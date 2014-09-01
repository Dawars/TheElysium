package me.dawars.CraftingPillars.properties;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class CalendarPlayerProps2013 implements IExtendedEntityProperties
{
	public static final String name = "PillarPlayerExtension";

	public static void register(Entity entity)
	{
		entity.registerExtendedProperties(name, new CalendarPlayerProps2013(entity));
	}

	public static CalendarPlayerProps2013 get(Entity entity)
	{
		return (CalendarPlayerProps2013)entity.getExtendedProperties(name);
	}



	public Entity player;
	public boolean[] discovered;
	public int data;

	public CalendarPlayerProps2013(Entity entity)
	{
		this.player = entity;
		this.discovered = new boolean[24];
	}

	public void setData(int data)
	{
		this.data = data;
		for(int i = 0; i < 24; i++)
			this.discovered[i] = (this.data/(int)Math.pow(2, i))%2 == 1;
	}

	public void setDiscovered(int slot)
	{
		this.discovered[slot] = true;
		this.data = 0;
		for(int i = 0; i < 24; i++)
			if(this.discovered[i])
				this.data += Math.pow(2, i);
	}

	@Override
	public void init(Entity entity, World world)
	{

	}

	@Override
	public void saveNBTData(NBTTagCompound compound)
	{
		NBTTagCompound props = new NBTTagCompound();
		props.setInteger("discovered2013", this.data);
		compound.setTag(name, props);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound)
	{
		NBTTagCompound props = (NBTTagCompound)compound.getTag(name);
		if(props.hasKey("discovered2013"))
		{
			this.setData(props.getInteger("discovered2013"));
		}
		else
		{
			this.discovered = new boolean[24];
			this.data = 0;
		}
	}
}
