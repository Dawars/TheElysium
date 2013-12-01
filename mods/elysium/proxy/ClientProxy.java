package mods.elysium.proxy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import mods.elysium.DefaultProps;
import mods.elysium.Elysium;
//import mods.elysium.client.gui.menu.*;
import mods.elysium.dimension.portal.ElysianTileEntityPortal;
import mods.elysium.dimension.portal.ElysianTileEntityPortalRenderer;
import mods.elysium.entity.EntityDrachma;
import mods.elysium.entity.EntityCatorPillar;
import mods.elysium.entity.EntityGerbil;
import mods.elysium.handlers.ElysianClientTickHandler;
import mods.elysium.network.ElysiumPacket;
import mods.elysium.render.*;
//import net.aetherteam.mainmenu_api.MainMenuAPI;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.packet.Packet;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

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
		
		ClientRegistry.bindTileEntitySpecialRenderer(ElysianTileEntityPortal.class, new ElysianTileEntityPortalRenderer());
		
		RenderingRegistry.registerBlockHandler(new CrystalBlockRendererOBJ());
		
		RenderingRegistry.registerEntityRenderingHandler(EntityDrachma.class, new RenderDrachmaOBJ());
		RenderingRegistry.registerEntityRenderingHandler(EntityCatorPillar.class, new RenderCaterPillar());
		RenderingRegistry.registerEntityRenderingHandler(EntityGerbil.class, new RenderGerbil());
		
		TickRegistry.registerTickHandler(new ElysianClientTickHandler(), Side.CLIENT);
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
