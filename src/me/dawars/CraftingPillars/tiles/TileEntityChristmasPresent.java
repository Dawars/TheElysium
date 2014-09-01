package me.dawars.CraftingPillars.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class TileEntityChristmasPresent extends BaseTileEntity
{
	public int color;
	//public boolean model;

	public static int[] colors = new int[]{
		0x186a1b,
		0xc04340,
		0x105793,
		0xdbdb24
	};

	@Override
	public void setWorldObj(World world)
	{
		super.setWorldObj(world);
		this.color = this.random.nextInt(colors.length/2);
		//if(world != null)
		//world.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, (this.model ? 1 : 0), 2);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		this.color = nbt.getInteger("color");
		//this.model = nbt.getBoolean("model");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setInteger("color", this.color);
		//nbt.setBoolean("model", this.model);
	}
}
