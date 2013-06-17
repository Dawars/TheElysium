package mods.elysium.entity.tileentity;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import mods.elysium.Elysium;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;

public abstract class ElysianTileEntity extends TileEntity{

//	@SuppressWarnings("rawtypes")
//	private static Map<Class, TilePacketWrapper> updateWrappers = new HashMap<Class, TilePacketWrapper>();
//	@SuppressWarnings("rawtypes")
//	private static Map<Class, TilePacketWrapper> descriptionWrappers = new HashMap<Class, TilePacketWrapper>();

//	private final TilePacketWrapper descriptionPacket;
//	private final TilePacketWrapper updatePacket;

//	private boolean init = false;

	public ElysianTileEntity() {
//		if (!updateWrappers.containsKey(this.getClass()))
//			updateWrappers.put(this.getClass(), new TilePacketWrapper(this.getClass()));
//
//		if (!descriptionWrappers.containsKey(this.getClass()))
//			descriptionWrappers.put(this.getClass(), new TilePacketWrapper(this.getClass()));
//
//		updatePacket = updateWrappers.get(this.getClass());
//		descriptionPacket = descriptionWrappers.get(this.getClass());

	}

	public boolean isUseableByPlayer(EntityPlayer player) {
		return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this == player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
	}
	
//	@Override
//	public void updateEntity() {
//		if (!init && !isInvalid()) {
//			initialize();
//			init = true;
//		}
//	}
//
//	@Override
//	public void invalidate()
//	{
//		init = false;
//		super.invalidate();
//	}

//	public void initialize() {
//		Utils.handleBufferedDescription(this);
//	}

//	public void destroy() {
//
//	}

//	public void sendNetworkUpdate() {
//		if(Elysium.proxy.isSimulating(worldObj))
//			Elysium.proxy.sendToPlayers(getUpdatePacket(), worldObj, xCoord, yCoord, zCoord, DefaultProps.NETWORK_UPDATE_RANGE);
//	}

//	@Override
//	public Packet getDescriptionPacket() {
//		return new PacketTileUpdate(this).getPacket();
//	}

//	@Override
//	public PacketPayload getPacketPayload() {
//		return updatePacket.toPayload(this);
//	}
//
//	@Override
//	public Packet getUpdatePacket() {
//		return new PacketTileUpdate(this).getPacket();
//	}
//
//	@Override
//	public void handleDescriptionPacket(PacketUpdate packet) {
//		descriptionPacket.fromPayload(this, packet.payload);
//	}
//
//	@Override
//	public void handleUpdatePacket(PacketUpdate packet) {
//		updatePacket.fromPayload(this, packet.payload);
//	}
//
//	@Override
//	public void postPacketHandling(PacketUpdate packet) {
//
//	}

}