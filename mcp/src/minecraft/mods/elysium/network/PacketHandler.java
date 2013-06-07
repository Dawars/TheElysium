package mods.elysium.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import mods.elysium.entity.ElysianEntityDrachma;
import mods.elysium.proxy.CommonProxy;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.network.packet.Packet26EntityExpOrb;

public class PacketHandler implements IPacketHandler {
	/**
	 * Handle a entity experience orb packet.
	 */
	public void handleCoins(PacketCoins packet) {
//		ElysianEntityDrachma coin = new ElysianEntityDrachma(
//				CommonProxy.proxy.getClientWorld(), (double) packet.posX, (double) packet.posY, (double) packet.posZ, packet.xpValue);
//		coin.serverPosX = packet.posX;
//		coin.serverPosY = packet.posY;
//		coin.serverPosZ = packet.posZ;
//		coin.rotationYaw = 0.0F;
//		coin.rotationPitch = 0.0F;
//		coin.entityId = packet.entityId;
//		CommonProxy.proxy.getClientWorld().addEntityToWorld(packet.entityId, coin);
	}

	private void handleRandom(PacketRandom packetT) {
		int randomNum = packetT.randomNum;
		
		System.out.println("Random num: " + randomNum);
	}

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		DataInputStream data = new DataInputStream(new ByteArrayInputStream(packet.data));
		try {
			int packetID = data.read();
			switch (packetID) {
			case PacketIds.RANDOM:
				
				PacketRandom packetT = new PacketRandom();
				packetT.readData(data);
				handleRandom(packetT);
				
			break;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
