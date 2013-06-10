package mods.elysium.proxy;

import mods.elysium.DefaultProps;
import mods.elysium.dimension.portal.ElysianTileEntityPortal;
import mods.elysium.dimension.portal.ElysianTileEntityPortalRenderer;
import mods.elysium.entity.ElysianEntityDrachma;
import mods.elysium.entity.EntityCatorPillar;
import mods.elysium.handlers.ElysianSoundHandler;
import mods.elysium.render.*;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class ClientProxy extends CommonProxy
{
	@Override
	public Object getClient()
	{
		return FMLClientHandler.instance().getClient();
	}
	
	@Override
	public World getClientWorld()
	{
		return FMLClientHandler.instance().getClient().theWorld;
	}
	
	@Override
	public void RegisterRenders()
	{
		RenderingRegistry.registerEntityRenderingHandler(ElysianEntityDrachma.class, new RenderDrachmaOBJ());
		ClientRegistry.bindTileEntitySpecialRenderer(ElysianTileEntityPortal.class, new ElysianTileEntityPortalRenderer());
		
		RenderingRegistry.registerEntityRenderingHandler(EntityCatorPillar.class, new RenderCaterPillar());
	}
	
	public static void addSoundHandler(Object handler)
	{
		MinecraftForge.EVENT_BUS.register(handler);
	}
}
