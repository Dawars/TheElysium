package mods.elysium.entity.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fluids.IFluidContainerItem;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;
import mods.elysium.Elysium;
import mods.elysium.entity.tileentity.ElysianTileEntity;

public class TileEntityFancyTank extends ElysianTileEntity implements IFluidHandler {

	private static final int MAX_Fluid = FluidContainerRegistry.BUCKET_VOLUME * 10;
	public final FluidTank tank = new FluidTank((int) MAX_Fluid);


	public void updateEntity() {
		if(tank.getFluid() != null && !worldObj.isRemote)
			System.out.println(tank.getFluid().amount + " " + FluidRegistry.getFluidName(tank.getFluid()));
	}
	/* SAVING & LOADING */
	@Override
	public void readFromNBT(NBTTagCompound data) {
		super.readFromNBT(data);
		FluidStack liquid = new FluidStack(0, 0);

		if (data.hasKey("stored") && data.hasKey("FluidId")) {
			liquid = new FluidStack(data.getInteger("FluidId"),
					data.getInteger("stored"));
		} else {
			liquid = FluidStack.loadFluidStackFromNBT(data
					.getCompoundTag("tank"));
		}
		tank.setFluid(liquid);
	}

	@Override
	public void writeToNBT(NBTTagCompound data) {
		super.writeToNBT(data);
		if (tank.getFluid() != null) {
			data.setTag("tank", tank.getFluid().writeToNBT(new NBTTagCompound()));
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
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		return this.tank.fill(resource, doFill);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		return this.drain(from, resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return this.tank.drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[]{tank.getInfo()};
	}

}
