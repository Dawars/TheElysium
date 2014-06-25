package hu.hundevelopers.elysium.proxy;

import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.render.ElysiumTileEntityPortalRenderer;
import hu.hundevelopers.elysium.tile.ElysianTileEntityPortal;
import net.minecraft.network.Packet;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
	/* INSTANCES */
	public Object getClient() {
		return FMLClientHandler.instance().getClient();
	}

	public World getClientWorld() {
		return FMLClientHandler.instance().getClient().theWorld;
	}

	@Override
	public void registerRenderers()
	{
//	FIXME	MainMenuAPI.registerMenu("Elysian Menu", ElysianMenu.class);
//		MainMenuAPI.registerMenu("Misc Elysian Menu", MiscElysianMenu.class);

		Elysium.pipeStoneReinderingID = RenderingRegistry.getNextAvailableRenderId();

		ClientRegistry.bindTileEntitySpecialRenderer(ElysianTileEntityPortal.class, new ElysiumTileEntityPortalRenderer());

//		RenderingRegistry.registerBlockHandler(new CrystalBlockRendererOBJ());

//		RenderingRegistry.registerEntityRenderingHandler(EntityCatorPillar.class, new RenderCaterPillar());
//		RenderingRegistry.registerEntityRenderingHandler(EntityGerbil.class, new RenderGerbil());

//		TickRegistry.registerTickHandler(new ElysianClientTickHandler(), Side.CLIENT);
	}

	/* NETWORKING */
	@Override
	public void sendToServer(Packet packet) {
		FMLClientHandler.instance().getClient().getNetHandler().addToSendQueue(packet);
	}

	/*@Override
	public void sendToPlayer(EntityPlayer entityplayer, ElysiumPacket packet){}
	
	@Override
	public void sendToPlayers(Packet packet, World world, int x, int y, int z, int maxDistance){}*/

	@Override
	public String playerName() {
		return FMLClientHandler.instance().getClient().thePlayer.getCommandSenderName();
	}

}
