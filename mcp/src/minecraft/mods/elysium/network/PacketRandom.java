package mods.elysium.network;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Random;

import mods.elysium.DefaultProps;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class PacketRandom extends ElysiumPacket{
	
	public int randomNum;

	public PacketRandom(int randomNum){
		this.randomNum = randomNum;
	}
	
	public PacketRandom(){}
	
	@Override
	public int getID() {
		return PacketIds.RANDOM;
	}

	@Override
	public void readData(DataInputStream data) throws IOException {

		this.randomNum = data.readInt();
	}

	@Override
	public void writeData(DataOutputStream data) throws IOException {
		data.writeInt(randomNum);
	}

}
