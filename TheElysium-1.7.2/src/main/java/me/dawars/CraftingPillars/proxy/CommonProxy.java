package me.dawars.CraftingPillars.proxy;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.server.FMLServerHandler;

public class CommonProxy
{

	public void initIconRegistry()
	{

	}

	public void initTileRenderer()
	{

	}

	public String getMinecraftVersion()
	{
		return Loader.instance().getMinecraftModContainer().getVersion();
	}

	public Object getClient()
	{
		return null;
	}
	
	public Object getPlayer()
	{
		return null;
	}

	public World getClientWorld()
	{
		return null;
	}

	public void sendToPlayer(EntityPlayerMP player, Packet packet)
	{
		player.playerNetServerHandler.sendPacket(packet);
	}

	public void sendToPlayers(Packet packet, World world, int x, int y, int z, int maxDistance)
	{
		if(world == null)
		{
			WorldServer worlds[] = FMLServerHandler.instance().getServer().worldServers;
			for(int i = 0; i < worlds.length; i++)
				for(int j = 0; j < worlds[i].playerEntities.size(); j++)
					this.sendToPlayer((EntityPlayerMP)worlds[i].playerEntities.get(j), packet);
		}
		else
		{
			for(int i = 0; i < world.playerEntities.size(); i++)
			{
				EntityPlayerMP player = (EntityPlayerMP)world.playerEntities.get(i);
				if(((int)player.posX-x)*((int)player.posX-x) + ((int)player.posY-y)*((int)player.posY-y) + ((int)player.posY-y)*((int)player.posY-y) <= maxDistance*maxDistance)
					this.sendToPlayer(player, packet);
			}
		}
	}

	public void sendToServer(Packet packet)
	{

	}

	public String playerName()
	{
		return "";
	}
}
