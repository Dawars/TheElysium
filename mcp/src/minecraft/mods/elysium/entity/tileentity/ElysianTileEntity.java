package mods.elysium.entity.tileentity;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import mods.elysium.Elysium;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class ElysianTileEntity extends TileEntity {
	protected Random random;

	@Override
	public void setWorldObj(World world) {
		this.worldObj = world;
		this.random = new Random(this.worldObj.getSeed());
	}

	public boolean isUseableByPlayer(EntityPlayer player) {
		return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this == player
				.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
	}
}