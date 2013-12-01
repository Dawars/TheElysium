package mods.elysium.network;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import mods.elysium.DefaultProps;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;

public abstract class ElysiumPacket {

	protected boolean isChunkDataPacket = false; //don't know that does but everything uses true
	protected String channel = DefaultProps.NET_CHANNEL_NAME;

	public abstract int getID();

	public Packet getPacket() {

		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		DataOutputStream data = new DataOutputStream(bytes);
		try {
			data.writeByte(getID());
			writeData(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = channel;
		packet.data = bytes.toByteArray();
		packet.length = packet.data.length;
		packet.isChunkDataPacket = this.isChunkDataPacket;
		return packet;
	}

	public abstract void readData(DataInputStream data) throws IOException;

	public abstract void writeData(DataOutputStream data) throws IOException;
}