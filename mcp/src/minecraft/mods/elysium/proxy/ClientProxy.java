package mods.elysium.proxy;

import mods.elysium.DefaultProps;
import mods.elysium.dimension.portal.ElysianTileEntityPortal;
import mods.elysium.dimension.portal.ElysianTileEntityPortalRenderer;
import mods.elysium.entity.ElysianEntityDrachma;
import mods.elysium.entity.ElysianEntityDrachma2;
import mods.elysium.handlers.ElysianSoundHandler;
import mods.elysium.render.RenderDrachma;
import mods.elysium.render.RenderDrachma2;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class ClientProxy extends CommonProxy{

	/* INSTANCES */
	public Object getClient() {
		return FMLClientHandler.instance().getClient();
	}

	public World getClientWorld() {
		return FMLClientHandler.instance().getClient().theWorld;
	}
	
	@Override
	public void RegisterRenders()
	{
//		Suggestions.SlimeBlockRenderId = RenderingRegistry.getNextAvailableRenderId();
//
//		RenderingRegistry.registerBlockHandler(new RenderSlimeBlock());
		RenderingRegistry.registerEntityRenderingHandler(ElysianEntityDrachma.class, new RenderDrachma());
		RenderingRegistry.registerEntityRenderingHandler(ElysianEntityDrachma2.class, new RenderDrachma2());
		ClientRegistry.bindTileEntitySpecialRenderer(ElysianTileEntityPortal.class, new ElysianTileEntityPortalRenderer());
	}
	
	public static void registerBlock(Block block){
		GameRegistry.registerBlock(block, DefaultProps.modId + block.getUnlocalizedName2());
	}
	
	public static void addSoundHandler(Object handler){
		MinecraftForge.EVENT_BUS.register(handler);
	}
}
