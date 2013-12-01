package mods.elysium.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet;

public class PacketCoins extends ElysiumPacket
{
	 /** The entity on the ground that was picked up. */
    public int collectedEntityId;

    /** The entity that picked up the one from the ground. */
    public int collectorEntityId;
    
    public PacketCoins(int par1, int par2)
    {
        this.collectedEntityId = par1;
        this.collectorEntityId = par2;
    }
	@Override
	public int getID() {
		return PacketIds.DRACHMA;
	}

	@Override
	public void readData(DataInputStream data) throws IOException {
		this.collectedEntityId = data.readInt();
        this.collectorEntityId = data.readInt();
	}

	@Override
	public void writeData(DataOutputStream data) throws IOException {
		data.writeInt(this.collectedEntityId);
		data.writeInt(this.collectorEntityId);
	}
   
}
