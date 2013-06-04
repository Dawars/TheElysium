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
		ElysianEntityDrachma coin = new ElysianEntityDrachma(
				CommonProxy.proxy.getClientWorld(), (double) packet.posX, (double) packet.posY, (double) packet.posZ, packet.xpValue);
		coin.serverPosX = packet.posX;
		coin.serverPosY = packet.posY;
		coin.serverPosZ = packet.posZ;
		coin.rotationYaw = 0.0F;
		coin.rotationPitch = 0.0F;
		coin.entityId = packet.entityId;
		CommonProxy.proxy.getClientWorld().addEntityToWorld(packet.entityId, coin);
	}

//	private void handleRandom(Packet250CustomPayload packet) {
//		DataInputStream inputStream = new DataInputStream(
//				new ByteArrayInputStream(packet.data));
//
//		int randomInt1;
//		int randomInt2;
//
//		try {
//			randomInt1 = inputStream.readInt();
//			randomInt2 = inputStream.readInt();
//		} catch (IOException e) {
//			e.printStackTrace();
//			return;
//		}
//
//		System.out.println(randomInt1 + " " + randomInt2);
//	}

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		DataInputStream data = new DataInputStream(new ByteArrayInputStream(packet.data));
		try {
//			if (packet.channel.equals("GenericRandom")) {
//				handleRandom(packet);
//			}
			
			
//			int packetID = data.read();
//			switch (packetID) {
//				case PacketIds.DRACHMA:
//					handleCoins(packet);
//				break;
//			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
