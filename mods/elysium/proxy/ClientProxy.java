package mods.elysium.proxy;

import me.dawars.CraftingPillars.renderer.RenderExtendPillar;
import mods.elysium.Elysium;
import mods.elysium.entity.EntityCatorPillar;
import mods.elysium.entity.EntityDrachma;
import mods.elysium.entity.EntityGerbil;
import mods.elysium.handlers.ElysianClientTickHandler;
import mods.elysium.render.CrystalBlockRendererOBJ;
import mods.elysium.render.RenderCaterPillar;
import mods.elysium.render.RenderChristmasLeaves;
import mods.elysium.render.RenderDrachmaOBJ;
import mods.elysium.render.RenderGerbil;
import mods.elysium.world.portal.ElysianTileEntityPortal;
import mods.elysium.world.portal.ElysianTileEntityPortalRenderer;
import net.minecraft.network.packet.Packet;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
//import mods.elysium.client.gui.menu.*;
//import net.aetherteam.mainmenu_api.MainMenuAPI;

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
		
		Elysium.crystalBlockRenderID = RenderingRegistry.getNextAvailableRenderId();
		Elysium.fostimberLeavesRenderID = RenderingRegistry.getNextAvailableRenderId();
		
		ClientRegistry.bindTileEntitySpecialRenderer(ElysianTileEntityPortal.class, new ElysianTileEntityPortalRenderer());
		
		RenderingRegistry.registerBlockHandler(new RenderChristmasLeaves());
		RenderingRegistry.registerBlockHandler(new CrystalBlockRendererOBJ());
		
		RenderingRegistry.registerEntityRenderingHandler(EntityDrachma.class, new RenderDrachmaOBJ());
		RenderingRegistry.registerEntityRenderingHandler(EntityCatorPillar.class, new RenderCaterPillar());
		RenderingRegistry.registerEntityRenderingHandler(EntityGerbil.class, new RenderGerbil());
		
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
		return FMLClientHandler.instance().getClient().thePlayer.username;
	}
}
