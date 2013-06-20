package mods.elysium.entity.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.liquids.LiquidTank;
import mods.elysium.Elysium;
import mods.elysium.entity.tileentity.ElysianTileEntity;

public class TileEntityFancyTank extends ElysianTileEntity implements ITankContainer {

	private static final int MAX_LIQUID = LiquidContainerRegistry.BUCKET_VOLUME * 10;
	public final ILiquidTank tank = new LiquidTank((int) MAX_LIQUID);

	public TileEntityFancyTank() {
		((LiquidTank) this.tank).setTankPressure(1);
	}

	/* SAVING & LOADING */
	@Override
	public void readFromNBT(NBTTagCompound data) {
		super.readFromNBT(data);
		LiquidStack liquid = new LiquidStack(0, 0, 0);

		if (data.hasKey("stored") && data.hasKey("liquidId")) {
			liquid = new LiquidStack(data.getInteger("liquidId"),
					data.getInteger("stored"), 0);
		} else {
			liquid = LiquidStack.loadLiquidStackFromNBT(data
					.getCompoundTag("tank"));
		}
		((LiquidTank) tank).setLiquid(liquid);

	}

	@Override
	public void writeToNBT(NBTTagCompound data) {
		super.writeToNBT(data);
		if (((LiquidTank) tank).containsValidLiquid()) {
			data.setTag("tank",
					tank.getLiquid().writeToNBT(new NBTTagCompound()));
		}
	}

	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
		NBTTagCompound nbt = pkt.customParam1;
		this.readFromNBT(nbt);
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return new Packet132TileEntityData(xCoord, yCoord, zCoord, 0, nbt);
	}

	@Override
	public void onInventoryChanged() {
		super.onInventoryChanged();

		if (!this.worldObj.isRemote) {
			Elysium.proxy.sendToPlayers(this.getDescriptionPacket(),
					this.worldObj, this.xCoord, this.yCoord, this.zCoord, 128);
		}
	}

	@Override
	public int fill(ForgeDirection from, LiquidStack resource, boolean doFill) {
		if (doFill)
			onInventoryChanged();
		return this.tank.fill(resource, doFill);
	}

	@Override
	public int fill(int tankIndex, LiquidStack resource, boolean doFill) {
		return this.fill(ForgeDirection.UNKNOWN, resource, doFill);
	}

	@Override
	public LiquidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		// return from != ForgeDirection.UP && from != ForgeDirection.DOWN ?
		// this.tank.drain(maxDrain, doDrain) : null;
		if (doDrain)
			onInventoryChanged();
		return this.tank.drain(maxDrain, doDrain);
	}

	@Override
	public LiquidStack drain(int tankIndex, int maxDrain, boolean doDrain) {
		return this.drain(ForgeDirection.UNKNOWN, maxDrain, doDrain);
	}

	@Override
	public ILiquidTank[] getTanks(ForgeDirection direction) {
		return new ILiquidTank[] { this.tank };
	}

	@Override
	public ILiquidTank getTank(ForgeDirection direction, LiquidStack type) {
		return this.tank;
	}
}
