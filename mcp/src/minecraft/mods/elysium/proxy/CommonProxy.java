package mods.elysium.proxy;

import mods.elysium.DefaultProps;
import mods.elysium.network.ElysiumPacket;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.network.packet.Packet;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class CommonProxy
{
	
	public void registerRenderers()
	{
		
	}
	
	public void installSounds()
	{
		
	}
	
	public String getMinecraftVersion() {
		return Loader.instance().getMinecraftModContainer().getVersion();
	}

	/* INSTANCES */
	public Object getClient() {
		return null;
	}

	public World getClientWorld() {
		return null;
	}

	/* SIMULATION */
	public boolean isSimulating(World world) {
		return !world.isRemote;
	}

	public boolean isRenderWorld(World world) {
		return world.isRemote;
	}

	public void sendToPlayer(EntityPlayer entityplayer, ElysiumPacket packet) {
		EntityPlayerMP player = (EntityPlayerMP) entityplayer;
		player.playerNetServerHandler.sendPacketToPlayer(packet.getPacket());
	}
	
	public void sendToPlayers(Packet packet, World world, int x, int y, int z, int maxDistance)
	{
		if(packet != null)
		{
			for (int j = 0; j < world.playerEntities.size(); j++)
			{
				EntityPlayerMP player = (EntityPlayerMP) world.playerEntities.get(j);
				if((player.posX - x)*(player.posX - x) + (player.posY - y)*(player.posY - y) + (player.posZ - z)*(player.posZ - z) <= maxDistance*maxDistance)
				{
					player.playerNetServerHandler.sendPacketToPlayer(packet);
				}
			}
		}
	}
	
	public void sendToServer(Packet packet) {
	}
	
	public String playerName() {
		return "";
	}
}
